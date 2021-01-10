import turtle, math
def circle(trt, radius):
    trt.sety(-(radius))
    trt.pendown()
    trt.circle(radius)
    trt.penup()
def calc(trt, point, angle, radius, factor):
    travel(trt, point*angle, radius)
    p1 = trt.pos()
    travel(trt, point*factor*angle, radius)
    p2 = trt.pos()
    line(trt, p1, p2)
def travel(trt, angle, radius):
    trt.home()
    trt.setheading(angle)
    trt.forward(radius)
def line(trt, p1, p2):
    trt.color('red')
    trt.goto(p1)
    trt.pendown()
    trt.goto(p2)
    trt.penup()
def main(dots, radius, factor):
    trt = turtle.Turtle()
    vindu = turtle.Screen()
    trt.penup()
    trt.speed(5)
    angle = math.degrees(2 * math.pi/dots)
    circle(trt, radius)
    for i in range(0, dots):
        calc(trt, i, angle, radius, factor)
    vindu.exitonclick()
main(50, 200, 2) 