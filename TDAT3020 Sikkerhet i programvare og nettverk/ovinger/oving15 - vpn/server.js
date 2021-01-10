const express = require('express');
const app = express();

const port = 1337;

app.get('/', (req, res) => {
    console.log('get /')

    var ip = req.headers['x-forwarded-for'] || req.connection.remoteAddress;

    console.log('from: ' + ip);

    res.sendStatus(200);
  })

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
  })
