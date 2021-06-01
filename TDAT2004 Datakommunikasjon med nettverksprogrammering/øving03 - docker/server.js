/* Dockerfile

FROM frolvlad/alpine-gcc

RUN apk add --no-cache g++

COPY . /app
WORKDIR app

RUN g++ Main.cpp -o Main.exe
CMD ./Main.exe

*/

const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const app = express();

app.use(express.static(path.join(__dirname, 'build')));
app.use(bodyParser.json());

console.log('server start');

const util = require('util');
const exec = util.promisify(require('child_process').exec);

async function execCmd(arg) {
    const { stdout, stderr } = await exec(arg);
    console.log('\nCMD EXEC\nstdout:', stdout);
    console.error('stderr:', stderr);
    return stdout;
}

app.post('/compile', async function (req, res) {
    console.log('Compile!');

    // get code
    let code = req.body.code;
    console.log(req.body);

    // write to file
    const fs = require('fs');
    fs.writeFile('Main.cpp', code, (err) => {
        if (err) return res.send('File error');
    });

    // command to run inside container
    let dockerArg = 'docker build -t succeeds --no-cache=true .';
    let runCodeArg = 'docker run --rm succeeds';

    try {
        await execCmd(dockerArg);
        let output = await execCmd(runCodeArg);

        return res.send(output);
    }
    catch (e) {
        console.log(e);
        return res.send('Docker failed');
    }
});

app.listen(process.env.PORT || 8080);