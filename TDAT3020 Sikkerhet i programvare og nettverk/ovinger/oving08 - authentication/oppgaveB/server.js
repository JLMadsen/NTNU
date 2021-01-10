// npm install express jsonwebtoken body-parser cors crypto-js

const express = require('express');
const app = express();
const jwt = require('jsonwebtoken');
const bodyParser = require('body-parser');
const cors = require('cors')
const crypto = require("crypto-js");

const port = 1337;
let secret = "hemmelig"
let tokens = [];
let password = "5989418f0314b8c9faddc6975614eef3";

app.use(cors());
app.use(bodyParser.json())

app.post('/auth', (req, res) => {
  console.log('POST / AUTH')
  let pass = req.body.pass;
  let hash = crypto.PBKDF2(pass, secret).toString()
  console.log(hash)
  if(hash === password) {
    let token = jwt.sign({id: 'token'}, secret, {expiresIn: 10});
    tokens.push(token);
    setTimeout(() => {tokens.splice(tokens.indexOf(token), 1);}, 10001)
    res.status(200).send(token);
  } else {
    res.sendStatus(403);
  }
})

app.post("/refresh", (req, res) => {
  console.log('POST / REFRESH')
  let auth = req.headers["x-access-token"] 
  if(tokens.includes(auth) && !isExpired(auth)) {
    let token = jwt.sign({id: 'token'}, secret, {expiresIn: 10});
    tokens.push(token);
    setTimeout(() => {tokens.splice(tokens.indexOf(token), 1);}, 10001)
    res.status(200).send(token);
  } else {
    res.sendStatus(401);
  }
})

isExpired = (token) => {
  if (token && jwt.decode(token)) {
    const exp = jwt.decode(token).exp
    const now = new Date();
    return now.getTime() >= exp * 1000;
  }
  return true
}

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`)
})