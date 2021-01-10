import {Student} from "./Student";

export class Course {
	id: number;
	static nextId = 1;
	title: string
	desc: string;
	students: Student[] = [];

	constructor(title:string, desc: string) {
		this.id = Course.nextId++;
		this.title = title;
		this.desc = desc;
	}

	addStudent(student_: Student) {
		if(this.students) {
			if (!this.students.find(student => student === student_)) {
				this.students.push(student_);
			}
		}
	}

	removeStudent(student_: Student) {
		if(this.students) {
			if (this.students.find(student => student === student_)) {
				this.students.splice( this.students.indexOf(student_),1);
			}
		}
	}
}