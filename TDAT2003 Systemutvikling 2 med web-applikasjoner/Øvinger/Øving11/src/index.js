// @flow
/* eslint eqeqeq: "off" */

import ReactDOM from 'react-dom';
import * as React from 'react';
import { Component } from 'react-simplified';
import { HashRouter, Route, NavLink } from 'react-router-dom';
import { Alert, Card, NavBar, Button, Row, Column } from './widgets';
import { createHashHistory } from 'history';
import { Course } from './Course'
import { Student } from './Student'

const history = createHashHistory(); // Use history.push(...) to programmatically change path, for instance after successfully saving a student

let students = [
	new Student('Ola', 'Jensen', 'ola.jensen@ntnu.no'),
	new Student('Kari', 'Larsen', 'kari.larsen@ntnu.no'),
	new Student('Marius', 'Torbjørnsen', 'xTorbjornsenX@ntnu.no'),
	new Student('Steffen', 'Trømborg', 'PickleRick@ntnu.no'),
	new Student('Jan', 'Loennechen', 'BigBoi93@ntnu.no'),
	new Student('Jakob', 'Madsen', 'l33t@ntnu.no'),
	new Student('Sebastian', 'Ikin', 'Kaczynski@ntnu.no')
];

let courses = [
	new Course("Web", "Nettsider!"),
	new Course("Algdat", "Algoritmer!"),
	new Course("Matte", "ÆSJ!")
];

students.reduce((counter, cur) => cur.addCourses(courses));
updateCourses();

function updateCourses() {
	// students.find(student => student.id == this.props.match.params.id)
	for(let student of students) {
		if(!student.courses){
			for(let course of courses){
				course.removeStudent(student)
			}
		}


		for (let i of Array(courses.length + 1).join(1).split('').map((x, i) => i)) {
			if(student.courses){
				let course = student.courses[i];
				if(course){
					courses[i].addStudent(student);
				}
				else {
					courses[i].removeStudent(student);
				}
			}
		}
	}
}

class Menu extends Component {
	render() {
		return (
			<nav className="navbar navbar-expand-lg navbar-dark bg-danger">
				<NavLink className="navbar-brand" activeStyle={{color: 'white'}} exact to="/">
					Home
				</NavLink>
				<div className="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div className="navbar-nav">
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/students">Students</NavLink>
						<NavLink className="nav-item nav-link" activeStyle={{color: 'white'}} exact to="/courses">Courses</NavLink>
					</div>
				</div>
			</nav>
		);
	}
}

class Home extends Component {
	render() {
		return (
			<Card title="React example with component state">Client-server communication will be covered next week.</Card>
		);
	}
}

class StudentList extends Component {
	render() {
		return (
			<Card title="Students">
				{students.map(student => (
					<Row key={student.id}>
						<Column width={2}>
							<NavLink activeStyle={{ color: 'darkblue' }} exact to={'/students/' + student.id}>
								{student.firstName} {student.lastName}
							</NavLink>
						</Column>
						<Column>
							<NavLink activeStyle={{ color: 'darkblue' }} to={'/students/' + student.id + '/edit'}>
								edit
							</NavLink>
						</Column>
					</Row>
				))}
				<Button.Danger onClick={this.add}>Add student</Button.Danger>
			</Card>
		);
	}
	add() {
		history.push('/studentAdd/');
	}
}

class StudentAdd extends Component {
	firstName = ''; // Always initialize component member variables
	lastName = '';
	email = '';

	render() {
		return (
			<Card title="Add student">
				<form style={{padding: "10px"}}>
					<Row>
						<Column width={2}>First name</Column>
						<Column>
							<input
								type="text"
								value={this.firstName}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.firstName = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column width={2}>Last name</Column>
						<Column>
							<input
								type="text"
								value={this.lastName}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.lastName = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column width={2}>Email</Column>
						<Column>
							<input
								type="text"
								value={this.email}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.email = event.target.value)}
							/>
						</Column>
					</Row>
					<Row style={{padding: "10px"}}>
						<Button.Danger onClick={this.save}>Save</Button.Danger>
						<Button.Danger onClick={this.cancel}>Cancel</Button.Danger>
					</Row>
				</form>
			</Card>
		);
	}

	save() {

		if(this.firstName == '' || this.email == '' || this.lastName == ''){
			Alert.info("Please fill all fields.", 1);
			return;
		}
		let temp = new Student(this.firstName, this.lastName, this.email);
		students.push(temp);

		// Go to StudentDetails after successful save
		history.push('/students/' + temp.id);
	}

	cancel() {
		history.push('/students/');
	}

}

class StudentDetails extends Component<{ match: { params: { id: number } } }> {
	render() {
		let student = students.find(student => student.id == this.props.match.params.id);
		if (!student) {
			Alert.danger('Student not found: ' + this.props.match.params.id);
			return null; // Return empty object (nothing to render)
		}
		return (
			<Card title="Details">
				<Row>
					<Column width={2}>First name</Column>
					<Column>{student.firstName}</Column>
				</Row>
				<Row>
					<Column width={2}>Last name</Column>
					<Column>{student.lastName}</Column>
				</Row>
				<Row>
					<Column width={2}>Email</Column>
					<Column>{student.email}</Column>
				</Row>
				<Row>
					<Column width={2}>Courses</Column>
					<Column>
						{ (student.courses.length !== 0)? student.courses.map(course => (<Row key={course.id}><Column width={2}>{course.title}</Column></Row>)) : "No courses"}
					</Column>
				</Row>
			</Card>
		);
	}
}

class StudentEdit extends Component<{ match: { params: { id: number } } }> {
	firstName = ''; // Always initialize component member variables
	lastName = '';
	email = '';

	render() {
		return (
			<Card title="Edit student">
				<form style={{padding: "10px"}}>
					<Row>
						<Column width={2}>First name</Column>
						<Column>
							<input
								type="text"
								value={this.firstName}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.firstName = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column width={2}>Last name</Column>
						<Column>
							<input
								type="text"
								value={this.lastName}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.lastName = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column width={2}>Email</Column>
						<Column>
							<input
								type="text"
								value={this.email}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.email = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column style={{padding: "10px"}}>
							{courses.map(course => (
								<div className="form-check">
									<input type="checkbox" className="form-check-input" id={"courseBox"+ this.props.match.params.id + course.id}></input>
									<label className="form-check-label" htmlFor="exampleCheck1">{course.title}</label>
								</div>
							))}
						</Column>
					</Row>
					<Row style={{padding: "10px"}}>
						<Button.Danger onClick={this.save}>Save</Button.Danger>
						<Button.Danger onClick={this.delete}>Delete</Button.Danger>
					</Row>
				</form>
			</Card>
		);
	}

	// Initialize component state (firstName, lastName, email) when the component has been inserted into the DOM (mounted)
	mounted() {
		let student = students.find(student => student.id == this.props.match.params.id);
		if (!student) {
			Alert.danger('Student not found: ' + this.props.match.params.id);
			return;
		}

		this.firstName = student.firstName;
		this.lastName = student.lastName;
		this.email = student.email;

		for(let course of courses){
			let box = document.getElementById("courseBox" + this.props.match.params.id + course.id);
			box.checked = false;
		}

		if(student.courses){
			for(let course of student.courses){
				let box = document.getElementById("courseBox" + this.props.match.params.id + course.id);
				box.checked = true;
			}
		}
	}

	save() {
		let student = students.find(student => student.id == this.props.match.params.id);
		if (!student) {
			Alert.danger('Student not found: ' + this.props.match.params.id);
			return;
		}

		student.firstName = this.firstName;
		student.lastName = this.lastName;
		student.email = this.email;

		for(let course of courses) {
			var status = document.getElementById("courseBox" + this.props.match.params.id + course.id).checked;
			if(status) {
				student.addCourse(course);
			} else {
				student.removeCourse(course)
			}
		}

		// Go to StudentDetails after successful save
		history.push('/students/' + student.id);
	}

	delete() {
		let student = students.find(student => student.id == this.props.match.params.id);
		if (!student) {
			Alert.danger('Student not found: ' + this.props.match.params.id);
			return;
		}
		for(let course of student.courses){
			course.removeStudent(student);
		}
		let index = students.indexOf(student);
		console.log("spice: "+ student.firstName +" at "+ index);
		students.splice(index, 1); // pop doesnt work.
		history.push('/students/');
	}
}

class CourseList extends Component {
	render() {
		return (
			<Card title="Courses">
				{courses.map(course => (
					<Row key={course.id}>
						<Column width={2}>
							<NavLink activeStyle={{ color: 'darkblue' }} exact to={'/courses/' + course.id}>
								{course.title}
							</NavLink>
						</Column>
						<Column>
							<NavLink activeStyle={{ color: 'darkblue' }} to={'/courses/' + course.id + '/edit'}>
								edit
							</NavLink>
						</Column>
					</Row>
				))}
				<Button.Danger onClick={this.add}>Add course</Button.Danger>
			</Card>
		);
	}
	add() {
		history.push('/courseAdd/');
	}
}

class CourseAdd extends Component {
	name = ''; // Always initialize component member variables
	desc = '';

	render() {
		return (
			<Card title="Add course">
				<form style={{padding: "10px"}}>
					<Row>
						<Column width={2}>Course name</Column>
						<Column>
							<input
								type="text"
								value={this.name}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.name = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column width={2}>Course description</Column>
						<Column>
							<input
								type="text"
								value={this.desc}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.desc = event.target.value)}
							/>
						</Column>
					</Row>
					<Row style={{padding: "10px"}}>
						<Button.Danger onClick={this.save}>Save</Button.Danger>
						<Button.Danger onClick={this.cancel}>Cancel</Button.Danger>
					</Row>
				</form>
			</Card>
		);
	}

	save() {

		if(this.desc == '' || this.name == ''){
			Alert.info("Please fill all fields.", 1);
			return;
		}
		let temp = new Course(this.name, this.desc);
		courses.push(temp);

		// Go to StudentDetails after successful save
		history.push('/courses/' + temp.id);
	}

	cancel() {
		history.push('/courses/');
	}

}

class CourseDetails extends Component<{ match: { params: { id: number } } }> {
	render() {
		let course = courses.find(course => course.id == this.props.match.params.id);
		if (!course) {
			console.error('Course not found'); // Until we have a warning/error system (next week)
			return null; // Return empty object (nothing to render)
		}
		return (
			<div className="card">
				<ul className="list-group list-group-flush">
					<li className="list-group-item"><b>Course name:</b> {course.title}</li>
					<li className="list-group-item"><b>Description:</b> {course.desc}</li>
					<li className="list-group-item"><b>Students:</b> {students.map(e => <li className="list-group-item">{ e.getName() } </li>)}</li>
				</ul>
			</div>
		);
	}
}

class CourseEdit extends Component<{ match: { params: { id: number } } }> {
	title = ''; // Always initialize component member variables
	desc = '';

	render() {
		return (
			<Card title="Edit course">
				<form>
					<Row>
						<Column width={2}>Title</Column>
						<Column>
							<input
								type="text"
								value={this.title}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.title = event.target.value)}
							/>
						</Column>
					</Row>
					<Row>
						<Column width={2}>Description</Column>
						<Column>
							<input
								type="text"
								value={this.desc}
								onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.desc = event.target.value)}
							/>
						</Column>
					</Row>
					<Button.Danger onClick={this.save}>Save</Button.Danger>
				</form>
			</Card>
		);
	}

	// Initialize component state (firstName, lastName, email) when the component has been inserted into the DOM (mounted)
	mounted() {
		let course = courses.find(course => course.id == this.props.match.params.id);
		if (!course) {
			Alert.danger('Course not found: ' + this.props.match.params.id);
			return;
		}

		this.title = course.title;
		this.desc = course.desc;

	}

	save() {
		let course = courses.find(course => course.id == this.props.match.params.id);
		if (!course) {
			Alert.danger('Course not found: ' + this.props.match.params.id);
			return;
		}

		course.title = this.title;
		course.desc = this.desc;

		// Go to StudentDetails after successful save
		history.push('/courses/' + course.id);
	}
}

const root = document.getElementById('root');
if (root)
	ReactDOM.render(
		<HashRouter>
			<div>
				<Alert />
				<Menu />
				<Route exact path="/" component={Home} />

				<Route path="/students" component={StudentList} />
				<Route exact path="/students/:id" component={StudentDetails} />
				<Route exact path="/students/:id/edit" component={StudentEdit} />

				<Route path="/courses" component={CourseList} />
				<Route exact path="/courses/:id" component={CourseDetails} />
				<Route exact path="/courses/:id/edit" component={CourseEdit} />

				<Route exact path="/studentAdd/" component={StudentAdd} />
				<Route exact path="/courseAdd/" component={CourseAdd} />
			</div>
		</HashRouter>,
		root
	);
