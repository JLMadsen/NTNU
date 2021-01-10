// @flow
/* eslint eqeqeq: "off" */

import * as React from 'react';
import { Component } from 'react-simplified';
import { HashRouter, Route, NavLink } from 'react-router-dom';
import ReactDOM from 'react-dom';

class Menu extends Component {
  render() {
    return(
      <nav className="navbar navbar-expand-lg navbar-dark bg-danger">
        <NavLink className="navbar-brand" activeStyle={{color: 'white'}} exact to="/">
            Best Newspaper
        </NavLink>
        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div className="navbar-nav align-baseline">
                <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/"><u>News</u></NavLink>
                <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Sport</NavLink>
                <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Culture</NavLink>
                <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Music</NavLink>
                <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Weather</NavLink>
            </div>
        </div>
      </nav>
    )
  }
}

class Menu2 extends Component {
    render() {
        return(
            <nav className="navbar navbar-expand-md navbar-dark bg-danger">
                <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                    <NavLink className="navbar-brand" activeStyle={{color: 'white'}} exact to="/">
                        Best Newspaper
                    </NavLink>
                    <ul className="navbar-nav mr-auto">
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/"><u>News</u></NavLink>
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Sport</NavLink>
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Culture</NavLink>
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Music</NavLink>
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Weather</NavLink>
                    </ul>
                </div>
                <div className="navbar-collapse collapse w-100 order-3 dual-collapse2">
                    <ul className="navbar-nav ml-auto">
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Log in</NavLink>
                        <NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">Register</NavLink>
                    </ul>
                </div>
            </nav>
        )
    }
}

class Nyhet extends Component <{title: string, content: string, imageLink: string}> {
  render() {
    return (
        <a href={"https://news.ycombinator.com/"} target={"_blank"}>
          <div className="card mb-3" style={{width: "900px"}}>
            <div className="row no-gutters">
              <div className="col-md-4">
                <img src={this.props.imageLink} width={"100px"} height={"250"} className="card-img" alt={this.props.title}></img>
              </div>
              <div className="col-md-8">
                <div className="card-body">
                  <h5 className="card-title">{this.props.title}</h5>
                  <p className="card-text">{this.props.content}</p>
                  <p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
                </div>
              </div>
            </div>
          </div>
        </a>
    );
  }
}

var fakenews = "https://ichef.bbci.co.uk/images/ic/720x405/p05vtkdr.jpg";
var trump = "http://d279m997dpfwgl.cloudfront.net/wp/2019/07/AP_19201004713022-1000x667.jpg";
var news = "https://cdn.ymaws.com/www.itsmfusa.org/resource/resmgr/images/more_images/news-3.jpg";
var frog = "https://thumbnails.cbc.ca/maven_legacy/thumbnails/248/463/pepe_YT_frame_0.jpg";
var kong = "https://ca-times.brightspotcdn.com/dims4/default/bf895da/2147483647/strip/true/crop/5472x3648+0+0/resize/840x560!/quality/90/?url=https%3A%2F%2Fca-times.brightspotcdn.com%2Fbc%2F72%2Fba4641c74d8bb3198d8d30ffc43c%2Fhong-kong-protests-92818.jpg"
var tornado = "https://www.weather.gov/images/safety/Tab1_TornadoPhoto.jpg";

class Home extends Component {
  render() {
    return (
      <div>
        <div className="row" style={{padding: "10px"}}>
            <Nyhet title={"Breaking news!"} content={"The news are broken..."} imageLink={fakenews}></Nyhet>
            <Nyhet title={"Trump chosen president of the world!"} content={"Is this the beginning of world peace? or a world in pieces?"} imageLink={trump}></Nyhet>
            <Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
            <Nyhet title={"Pepe the frog"} content={"Just a frog? or a hate symbol?"} imageLink={frog}></Nyhet>
            <Nyhet title={"Breaking news!"} content={"The news are broken..."} imageLink={fakenews}></Nyhet>
            <Nyhet title={"Hong Kong!"} content={"Shits on fire yo"} imageLink={kong}></Nyhet>
            <Nyhet title={"Weather!"} content={"Wind is fast."} imageLink={tornado}></Nyhet>
            <Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
            <Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
            <Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
            <Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
            <Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
        </div>
          <footer className="page-footer font-small bg-danger">
              <div className="footer-copyright text-center py-3">
                  <a href="https://github.com/jlmadsen/"> <u style={{color: "lightblue"}}>© 2019 Copyright: Jakob Madsen</u></a>
              </div>
          </footer>
    </div>
    );
  }
}

const root = document.getElementById('root');
if (root)
  ReactDOM.render(
    <HashRouter>
      <div>
        <Menu2 />
        <Route exact path="/" component={Home} />
      </div>
    </HashRouter>,
    root
  );
