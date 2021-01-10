// @flow
/* eslint eqeqeq: "off" */

import * as React from 'react';
import { Component } from 'react-simplified';
import { HashRouter, Route, NavLink } from 'react-router-dom';
import ReactDOM from 'react-dom';
import { Alert, Card, NavBar, Button, Row, Column } from './widgets';
import { TwitterTimelineEmbed, TwitterShareButton, TwitterFollowButton, TwitterHashtagButton, TwitterMentionButton, TwitterTweetEmbed, TwitterMomentShare, TwitterDMButton, TwitterVideoEmbed, TwitterOnAirButton } from 'react-twitter-embed'
import { ReactWeather } from 'react-open-weather'

class TopNyhet extends Component <{title: string, content: string, imageLink: string}> {
	render() {
		return (
			<a href={"https://news.ycombinator.com/"} target={"_blank"}>
				<div className="card mb-3">
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

class LongImgNyhet extends Component <{title: string, content: string, imageLink: string}> {
	render() {
		return (
			<div className="card mb-3">
				<img src={this.props.imageLink} className="card-img-top" alt={this.props.title}></img>
				<div className="card-body">
					<h5 className="card-title">{this.props.title}</h5>
					<p className="card-text">{this.props.content}</p>
					<p className="card-text"><small className="text-muted">Last updated 3 mins ago</small></p>
				</div>
			</div>
		);
	}
}

class Nyhet extends Component <{title: string, content: string, imageLink: string}> {
	render() {
		var link = (this.props.title.includes("ram")) ? "https://downloadmoreram.com" : "https://news.ycombinator.com/";
		return (
			<a href={link} target={"_blank"} style={{width: "50%", height: "100%"}}>
				<div className="card">
					<img className="card-img-top" src={this.props.imageLink} alt={this.props.title} width={100} height={250}></img>
					<div className="card-body">
						<h3 className="card-title">{this.props.title}</h3>
						<p className="card-text">{this.props.content}</p>
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
var society = "https://media.tenor.com/images/e0f1b1fd904c87214d3f82ad6f1fd283/tenor.png";
var ram = "https://cdn.hswstatic.com/gif/add-ram-desktop-2-1.jpg";
var thumb = "https://www.thoughtco.com/thmb/81-Xe9qgEUHFwLMmeT1IX_1GJMg=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/skyward-56a9e25b5f9b58b7d0ffab68.jpg";
var siv = "https://smp.vgc.no/v2/images/485335b8-9c17-4114-b9c0-8adfa518c32f?fit=crop&h=686&w=992&s=354e82f67e2db9299bd6f2e70707cc9437328f9e";
var stonks = "https://i.kym-cdn.com/photos/images/newsfeed/001/499/826/2f0.png"

function trumpTweets() {
	return(
		<TwitterTimelineEmbed
			sourceType="profile"
			screenName="realdonaldtrump"
		/>
	);
}

class Menu extends Component {
	render() {
		return(
			<nav className="navbar navbar-expand-md navbar-dark bg-danger">
				<div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2" >
					<NavLink className="navbar-brand" activeStyle={{color: 'white'}} exact to="/">
						American news!
					</NavLink>
					<ul className="navbar-nav mr-auto">
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">News</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/sport">Sport</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/culture">Culture</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/music">Music</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/weather">Weather</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/economy">Economy</NavLink>
					</ul>
				</div>
				<div className="navbar-collapse collapse w-100 order-3 dual-collapse2">
					<ul className="navbar-nav ml-auto">
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/">New story</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/login">Log in</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/login">Register</NavLink>
					</ul>
				</div>
			</nav>
		)
	}
}

class Home extends Component {
	render() {
		return (
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{width: "50%", margin: "auto", display: "grid", padding: "20px"}}>
					<TopNyhet title={"Trump chosen president of the world!"} content={"Is this the beginning of world peace? or a world in pieces?"} imageLink={trump}></TopNyhet>
					<div className="card-deck">
						<Nyhet title={"Pepe the frog"} content={"Just a frog? or a hate symbol?"} imageLink={frog}></Nyhet>
						<Nyhet title={"Breaking news!"} content={"The news are broken..."} imageLink={fakenews}></Nyhet>
					</div>
					<div className="card-deck">
						<Nyhet title={"Hong Kong!"} content={"Shits on fire yo"} imageLink={kong}></Nyhet>
						<Nyhet title={"Weather!"} content={"Wind is fast."} imageLink={tornado}></Nyhet>
					</div>
					<div className="card-deck">
						<Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
						<Nyhet title={"Need more ram?"} content={"Are you tired of chrome eating all your ram?"} imageLink={ram}></Nyhet>
					</div>
					<div className="card-deck">
						<Nyhet title={"Breaking news!"} content={"The news are broken..."} imageLink={fakenews}></Nyhet>
						<Nyhet title={"Cultural appropriation!"} content={"Is this illegal?"} imageLink={siv}></Nyhet>
					</div>
					<div className="card-deck">
						<Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
						<Nyhet title={"News!"} content={"Important things are happening"} imageLink={news}></Nyhet>
					</div>
					<LongImgNyhet title={"This weeks comic!"} content={"idk"} imageLink={society}></LongImgNyhet>

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

class Sport extends Component {
	render() {
		return(
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{width: "50%", margin: "auto", padding: "30px"}}>
					<iframe width="560" height="315" src="https://www.youtube.com/embed/XesxS5Zn3w0" frameBorder="0"
					        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
					        allowFullScreen></iframe>
				</div>
			</div>
		);
	}
}

class Culture extends Component {
	render() {
		return(
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{width: "50%", margin: "auto"}}>
					{trumpTweets()}
				</div>
			</div>
		);
	}
}

class Music extends Component {
	render() {
		return(
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{width: "50%", margin: "auto", padding: "30px"}}>
					<iframe width="100%" height="300" scrolling="no" frameBorder="no" allow="autoplay"
					        src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/333945804&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true"></iframe>
				</div>
			</div>
		);
	}
}

class Economy extends Component {
	render() {
		return(
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{width: "50%", margin: "auto", padding: "30px"}}>
					<img src={stonks} alt="Economy!"></img>
				</div>
			</div>
		);
	}
}

class Weather extends Component {
	render() {
		return(
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{margin: "auto", padding: "30px"}}>
					<div style={{width: "50%", margin: "auto", padding: "30px"}}>
						<img src="https://www.thoughtco.com/thmb/81-Xe9qgEUHFwLMmeT1IX_1GJMg=/768x0/filters:no_upscale():max_bytes(150000):strip_icc()/skyward-56a9e25b5f9b58b7d0ffab68.jpg"></img>
					</div>
					<div style={{width: "50%", margin: "auto", padding: "30px"}}>
						{ Math.floor(Math.random() * 30) - 10 } grader ish
					</div>
				</div>
			</div>
		);
	}
}

class Login extends Component {
	render() {
		return(
			<div className="bg-light" style={{height: "100vh"}}>
				<div style={{width: "60%", margin: "auto", padding: "30px"}}>
					<div className="col">
						<div className="col-3">
							Enter username and password
						</div>
						<div className="col-1">
							<input></input>
						</div>
						<div className="col-1">
							<input></input>
						</div>
						<div className="col-1">
							<button>Login</button>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

const root = document.getElementById('root');
if (root)
	ReactDOM.render(
		<HashRouter>
			<div>
				<Menu/>
				<Route exact path="/" component={Home} />
				<Route exact path="/sport" component={Sport} />
				<Route exact path="/culture" component={Culture} />
				<Route exact path="/music" component={Music} />
				<Route exact path="/weather" component={Weather} />
				<Route exact path="/economy" component={Economy} />

				<Route path="/login" exact component={Login} />
			</div>
		</HashRouter>,
		root
	);
