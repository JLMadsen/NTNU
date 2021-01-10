// https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API/Writing_WebSocket_client_applications
import {Component} from "react-simplified";
import * as React from "react";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import {Button} from "react-bootstrap";
import {MidColumn} from "./widgets";
import Form from "react-bootstrap/Form";
import _ from "lodash";

const white = 'white';
const black = 'black';

class Pixel {
    x = null;
    y = null;
    color = white;
    constructor(x, y, color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    reset() {this.color = 'white'}
}

let ws;

export class DrawingBoard extends Component {

    constructor(props) {
        super(props);
        this.state = {
            xlength: 25,
            ylength: 25,
            cells: [],
            selectedColor: black,
            rawPacket: '',
        };

        ws = new WebSocket("ws://localhost:4001");

        // when we get a message we need to decode it
        // format is declared in encodeDraw / decodeDraw function
        ws.onmessage = event => {
            this.setState({rawPacket: this.state.rawPacket + "\n" + event.data})
            this.decodeDraw(event.data);
        };

        ws.onopen = () => this.setState({rawPacket: "Connected to socket."});

        ws.onerror = (err) => this.setState({rawPacket: "Error "+ err});
    }

    render() {
        return (
            <MidColumn cclass="col-sm-6">
                <Card border="info" className="mt-4 p-4">
                    <div className="ml-2 text-center"><h1>Drawing board</h1></div>
                    <p>This is a page where you can draw on, if connected to socket it will send the picture to other clients.</p>

                    {/* Color selection */}
                    <div className="ml-2 mr-2"><h4>Color</h4></div>
                    <Row className="ml-2">

                        <Button
                            className="mr-1"
                            variant={this.state.selectedColor === black? "dark" : "outline-dark" }
                            onClick={() => this.setState({selectedColor: black})}>
                            black
                        </Button>
                        <Button
                            className="mr-1"
                            variant={this.state.selectedColor === white? "secondary" : "outline-secondary" }
                            onClick={() => this.setState({selectedColor: white})}>
                            white
                        </Button>

                    </Row>

                    {/* Grid */}
                    <div
                        style={{display: "grid", gridTemplateColumns: "repeat("+this.state.xlength+", minmax(0, 1fr))", border: "1px solid lightgrey"}}
                        className="m-2"
                    >

                        {this.state.cells.map(row => row.map(cell => (
                            <div
                                className="cell"
                                key={cell.x + '' + cell.y}
                                style={{display: "inline", userSelect: "none", backgroundColor: cell.color}}
                                onClick={_.debounce(() => {this.cellClick(cell)}, 100)}
                                onDragOver={_.debounce(() => {this.cellClick(cell)}, 100)}
                                draggable={"true"}
                            >

                            </div>
                        )))}
                    </div>
                </Card>
                <Card border="info" className="mt-4 mb-4 p-4">
                    <div className="ml-2 text-center"><h1>Socket log</h1></div>

                    <Form.Control
                        readOnly
                        as="textarea"
                        rows="4"
                        controlid="output"
                        defaultValue={this.state.rawPacket}
                    />

                </Card>
            </MidColumn>
        )
    }

    mounted() {
        this.setState({cells: this.createEmptyCanvas()})
    }

    componentWillUnmount() {
        ws.close();
    }

    createEmptyCanvas() {
        let cells = [];
        for(let y=0; y<this.state.ylength; y++)
        {
            cells.push([]);
            for(let x=0; x<this.state.xlength; x++)
            {
                cells[y].push(new Pixel(x, y, 'white'));
            }
        }
        return cells;
    }

    encodeDraw(cell) {
        return cell.x  +','+  cell.y +','+ this.state.selectedColor
    }

    decodeDraw(message) {
        let grid = this.state.cells;
        let arr = message.split(',');
        grid[arr[1]][arr[0]].color = arr[2];
        this.setState({cells: grid});
    }

    cellClick(cell) {
        let grid = this.state.cells;
        cell = grid[cell.y][cell.x];
        cell.color = this.state.selectedColor;
        this.setState({cells: grid});

        // send new grid to socket
        ws.send(this.encodeDraw(cell));
    }
}