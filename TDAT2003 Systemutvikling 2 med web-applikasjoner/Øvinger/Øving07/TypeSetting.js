//@flow

// oppgave 1
console.log("\n\n oppgave 1")

let v1 = [1, 2, 3];
let v2 = [4, 5, 6];

console.log('2 + v1:', v1.map(e => 2 + e));
console.log('2 * v1:', v1.map(e => 2 * e));
console.log('mean of v1:', v1.reduce((pre, cur) => cur += pre) / v1.length);
console.log('v1 dot v2:', v1.reduce((sum, cur, currentindex) => sum + v1[currentindex]*v2[currentindex]));
console.log('sum of v1 + 2 * v2:', (v1.reduce((pre, cur) => cur += pre) + 2 * v2.reduce((pre, cur) => cur += pre)));
console.log('v1 as string:', v1.map(e => e.toString()));

// oppgave 2
console.log("\n\n oppgave 2")

class Complex {
  //real: number;
  //imag: number;

  constructor(real, img) {//real,: number, img: number) {
    this.real = real;
    this.imag = img;
  }
}

let v = [new Complex(2, 2), new Complex(1, 1)];

console.log('v elements as strings:', v.map(e => String(e.real) +" "+ String(e.imag)));
console.log('magnitude of v elements:', v.map(e => Math.sqrt(Math.pow(e.imag, 2) + Math.pow(e.real, 2))));
console.log('sum of v:', v.reduce((sum, cur) => sum.real + cur.real), v.reduce((sum, cur) => sum.imag + cur.imag));

// oppgave 3
console.log("\n\n oppgave 3")

let students = [{ name: 'Ola', grade: 'A' }, { name: 'Kari', grade: 'C' }, { name: 'Knut', grade: 'C' }];

function sum(v)
{
  return v.reduce((sum, cur) => sum + cur);
}

function countGrade(list, grade)
{
  v = list.map(e => (e.grade == grade)? 1 : 0)
  return sum(v)
}

console.log("elements as strings:", students.map(e => String(e.name) + " got " + e.grade)); //[ 'Ola got A', 'Kari got C', 'Knut got C' ]
console.log("How many got C:", countGrade(students, "C")); //2
console.log("Percentage of C grades:", countGrade(students, "C")/students.length) // 0.6666666666666666
console.log("Did anyone get A:", (countGrade(students, "A") > 0)? 'Yes' : 'No'); //Yes
console.log("Did anyone get F:", (countGrade(students, "F") > 0)? 'Yes' : 'No'); //No