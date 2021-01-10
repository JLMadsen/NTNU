import {Component} from "react-simplified";
import * as React from "react";
import {MidColumn} from "./widgets";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Form from "react-bootstrap/Form";
import axios from 'axios';
import Col from "react-bootstrap/Col";
import pbkdf2 from "crypto-js/pbkdf2"
import Spinner from "react-bootstrap/Spinner";

let time = 0.0;
let timeout;

export class Hashing extends Component {

    constructor(props)
    {
        super(props);
        this.state = {
            passwordInput: "",
            passwordHash: "",
            authToken: "",
            err: "",
            expired: true,
            render: 0,
            loading: false,
        };
    }

    render() {
        return(
            <MidColumn>
                <Card border="warning" className="mt-4 p-4">
                    <h1 className="ml-2 text-center" >Hash3r</h1>
                    <b style={{color: "red"}} className="ml-2 text-center" >{this.state.err}</b>
                    <Row className=" mt-4 text-center">
                        <Col>
                            <Form.Control
                                type="password"
                                placeholder="Enter password"
                                value={this.state.passwordInput}
                                onChange={(e) => this.setState({passwordInput: e.target.value})}
                            />
                        </Col>
                        <Col>
                            <Button
                                variant="primary"
                                onClick={this.sendPass}
                                disabled={this.state.loading}
                            >
                                Hash password
                                {this.state.loading?
                                    <Spinner
                                        className="ml-2"
                                        as="span"
                                        animation="border"
                                        size="sm"
                                        role="status"
                                        aria-hidden="true"/> : null}
                            </Button>
                        </Col>
                    </Row>
                    <Col className="mt-4" >
                        <Row>
                            Token: <b style={{overflow: "hidden"}}>{this.state.authToken}</b>
                        </Row>
                    </Col>
                    <Button
                        className="mt-4"
                        variant="warning"
                        onClick={this.refresh}
                        disabled={this.state.loading}
                    >
                        Refresh token!
                        {this.state.loading?
                            <Spinner
                                className="ml-2"
                                as="span"
                                animation="border"
                                size="sm"
                                role="status"
                                aria-hidden="true"/> : null}
                    </Button>
                    <Col>
                        <p
                            style={{color: this.state.expired? "red":"grey"}}
                            className="text-center mt-2"
                        >
                            Time: {time.toFixed(1)}
                        </p>
                    </Col>
                </Card>
            </MidColumn>
        )
    }

    timer() {
        if (this.state.expired) return;
        if (time<=0) {
            clearTimeout(timeout);
            this.setState({expired: true});
            time = 0.0;
        }
        timeout = setTimeout(() => {
            time -= 0.1;
            this.timer();
            this.setState({render: 1})
        }, 100);
    }

    sendPass() {
        this.setState({err: '', loading: true});
        console.log('Send password: ' + this.state.passwordInput);

        let hashedPass = pbkdf2(this.state.passwordInput, 'secret');
        console.log('hashed: ' + hashedPass);

        axios.post(
            'http://localhost:1337/auth',
            {pass: hashedPass.toString()},
            {headers: {'Content-type': 'Application/json'}})
            .then(response => {
                this.setState({
                    authToken: response.data,
                    expired: false,
                    loading: false},
                    () => {
                    time=10.0;
                    clearTimeout(timeout);
                    this.timer()
                });
                console.log('hashed from server: ' + response.data);
            })
            .catch(err => {
                this.setState({
                    err: err.message,
                    authToken: "",
                    loading: false})
            });
    }

    refresh() {
        this.setState({err: '', loading: true});
        console.log('Refresh token');

        axios.post("http://localhost:1337/refresh",
            {},
            {headers:
                {'x-access-token':
                    this.state.authToken
                }
            }
            )
            .then(response => {
                this.setState({
                    authToken: response.data,
                    expired: false,
                    loading: false
                    }, () => {
                    time=10.0;
                    clearTimeout(timeout);
                    this.timer()
                });
                console.log('new token from server: ' + response.data);
            })
            .catch(err => {
                this.setState({
                    err: err.message,
                    authToken: "",
                    loading: false
                });
            })
    }
}