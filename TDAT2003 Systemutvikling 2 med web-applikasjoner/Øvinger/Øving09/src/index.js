// @flow
/* eslint eqeqeq: "off" */

import * as React from 'react';
import { Component } from 'react-simplified';
import { HashRouter, Route, NavLink } from 'react-router-dom';
import ReactDOM from 'react-dom';

class Student {
  id: number;
  static nextId = 1;

  firstName: string;
  lastName: string;
  email: string;
  courses: Course[];

  constructor(firstName: string, lastName: string, email: string) {
    this.id = Student.nextId++;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  addCourse(courses: Course[]) {
    this.courses = courses;
  }

  getName() {
    return this.firstName +" "+ this.lastName;
  }
}

class Course {
  id: number;
  static nextId = 1;
  title: string
  desc: string;
  students: Student[];

  constructor(title:string, desc: string) {
    this.id = Course.nextId++;
    this.title = title;
    this.desc = desc;
  }
  addStudent(students: Student[]) {
    this.students = students;
  }
}

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
    new Course("Web", "Nettsider!", students),
    new Course("Algdat", "Algoritmer!", students),
    new Course("Matte", "ÆSJ!", students)
];

students.reduce((counter, cur) => cur.addCourse(courses));

class Menu extends Component {
  render() {
    return(
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
    )
  }
}

// veldig enkel implementasjon av Card klasse hvor den tar inn en string
class Card extends Component <{body: string}>{
  render() {
    return (
        <div className="card">
          <div className="card-body">{this.props.body}</div>
        </div>
    )
  }
}

class Home extends Component {
  render() {
    return (
      <div>
        <Card title="Homepage" body={"Dette er ett kort på forsiden"}></Card>
    </div>
    );
  }
}

class StudentList extends Component {
  render() {
    return (
        <div className="card">
          <ul className="list-group-flush">
            {students.map(student => (
              <li className="list-group-item" key={student.id}>
                <NavLink activeStyle={{ color: 'darkblue' }} to={'/students/' + student.id}>
                  {student.firstName} {student.lastName}
                </NavLink>
              </li>
            ))}
          </ul>
        </div>
    );
  }
}

class StudentDetails extends Component<{ match: { params: { id: number } } }> {
  render() {
    let student = students.find(student => student.id == this.props.match.params.id);
    if (!student) {
      console.error('Student not found'); // Until we have a warning/error system (next week)
      return null; // Return empty object (nothing to render)
    }
    return (
      <div className="card">
        <ul className="list-group list-group-flush">
          <li className="list-group-item"><b>First name:</b> {student.firstName}</li>
          <li className="list-group-item"><b>Last name:</b> {student.lastName}</li>
          <li className="list-group-item"><b>Email:</b> {student.email}</li>
          <li className="list-group-item"><b>Courses:</b> {student.courses? student.courses.map(course => <li className="list-group-item">{ course.title }</li>) : "No courses"}</li>
        </ul>
      </div>
    );
  }
}

class CourseList extends Component {
  render() {
    return (
        <ul className="list-group-flush">
          {courses.map(course => (
              <li className="list-group-item" key={course.id}>
                <NavLink activeStyle={{ color: 'darkblue' }} to={'/courses/' + course.id}>
                  {course.title}
                </NavLink>
              </li>
          ))}
        </ul>
    );
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

const root = document.getElementById('root');
if (root)
  ReactDOM.render(
    <HashRouter>
      <div>
        <Menu />
        <Route exact path="/" component={Home} />
        <Route path="/students" component={StudentList} />
        <Route path="/students/:id" component={StudentDetails} />
        <Route path="/courses" component={CourseList} />
        <Route path="/courses/:id" component={CourseDetails} />
      </div>
    </HashRouter>,
    root
  );
