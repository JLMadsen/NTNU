import {Course} from "./Course";

export class Student {
	id: number;
	static nextId = 1;

	firstName: string;
	lastName: string;
	email: string;
	courses: Course[] = [];

	constructor(firstName: string, lastName: string, email: string) {
		this.id = Student.nextId++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	addCourses(courses: Course[]) {
		this.courses = courses;
	}
	addCourse(course_: Course) {
		if(!this.courses.find(course => course.id === course_.id)) {
			this.courses.push(course_);
		}
	}

	removeCourse(course: Course) {
		let c = this.courses.find(co => co.id === course.id);
		if(c){
			this.courses.splice(this.courses.indexOf(c), 1);
		}
	}

	getName() {
		return this.firstName +" "+ this.lastName;
	}
}