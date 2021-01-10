const net = require("net");
const crypto = require("crypto");
const PORT = 4001;

let clients = [];

const server = net.createServer(conn => {
    console.log("Client connected");

    //When the server receives data
    conn.on("data", data => {
        console.log('Data from client:\n' + data.toString());

        // handshake
        if (data.toString()[0] == "G") {
            let key = data.toString().substring(data.toString().indexOf("-Key: ") + 6,data.toString().indexOf("==") + 2);
            let acceptValue = generateAcceptValue(key);

            const responseHeaders = [
                "HTTP/1.1 101 Web Socket Protocol Handshake",
                "Upgrade: websocket",
                "Connection: Upgrade",
                "Sec-WebSocket-Accept:" + acceptValue
            ];

            conn.write(responseHeaders.join("\r\n") + "\r\n\r\n");
            clients.push(conn);
        } else {

            let message = "";
            let length = data[1] & 127;
            let maskStart = 2;
            let dataStart = maskStart + 4;

            for (let i = dataStart; i < dataStart + length; i++) {
                let byte = data[i] ^ data[maskStart + ((i - dataStart) % 4)];
                message += String.fromCharCode(byte);
            }

            console.log('Sending to clients:\n ' + message);
            writeToAll(message, conn);
        }
    });

    conn.on("end", () => {
        console.log("Client disconnected");

        const index = clients.indexOf(conn);
        if (index > -1) {
            clients.splice(index, 1);
        }

    });
});

const writeToAll = (message, origin) => {
    let buffer = Buffer.concat([
        new Buffer.from([
            0x81,
            "0x" +
            (message.length + 0x10000)
                .toString(16)
                .substr(-2)
                .toUpperCase()
        ]),
        Buffer.from(message)
    ]);
    clients.forEach(client => {
        if (client !== origin) client.write(buffer);
    });
};

const generateAcceptValue = acceptKey =>
    crypto
        .createHash("sha1")
        .update(acceptKey + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11")
        .digest("base64");

server.on("error", error => {
    console.error("Error: ", error);
});
server.listen(PORT, () => {
    console.log("WebSocket server listening on port "+ PORT );
});