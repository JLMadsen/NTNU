import turtle # i need a turtle, so i import one
import math # i dont know how to do math, so i import it from someone who can

'''
Author jakob Lonnerod Madsen

Since my last assignment got feedback conserning comments i have tried to improve, let me know if they are too much.

My strategy for solving this is:
1. To go from the center to point 1, and get the coordinates.
2. Then go to point 2, which is point 1 times 2, then go to that point and get those coordinates.
3. Then draw a line from point 1 to point 2.

Depending on the amount of dots you choose the drawing will be more or less precise.
If you want to see another drawing you can change the factor in the variables section at the bottom.
'''

# Used to make main circle, need to know radius
def circle(trt, radius):

    trt.sety(-(radius)) # for circle senter to be (0,0)
    trt.pendown() # DRAW!
    trt.circle(radius) # makes the circle
    trt.penup() # DONT DRAW!
    trt.home() # go home

# used to calculate needed coords for the 2 points, thid function also calls all the other functions i need
def calc(trt, point, angle, radius, factor):

    trt.penup()
    p1ang = point*angle # current point is found by multiplying point by angle
    travel(trt, p1ang, radius) # travel to the point
    p1 = trt.pos() # get pos p1
    product = point*factor # point 2 is found by mulitplying point 1 with a factor
    p2ang = product*angle # angle is found by product times angle
    travel(trt, p2ang, radius)
    p2 = trt.pos() # get pos p2
    line(trt, p1, p2) # call line to draw a line

# used to travel, assume always from home, so the distance is always the same as radius
def travel(trt, angle, radius):

    trt.home() # always start at (0,0)
    trt.setheading(angle)
    trt.forward(radius) # same as radius, could use radius variable but i dont want to

# used to draw line
def line(trt, p1, p2):

    trt.color('red')
    trt.pendown() # DRAW!
    trt.goto(p1)
    trt.penup() # DONT DRAW!

def info(trt, radius, angle, dots):
    for i in range(0, (dots)): # for all dots
        travel(trt, i*angle, radius) # travel to edge
        trt.dot(6) # create dot
        trt.forward(20) # go 20 units out from circle
        trt.write(str(i), align ="center", font=("Arial", 8, "normal")) # write number

# used to start, input the amount of dots you want to use
def main(dots, radius, factor):

    trt = turtle.Turtle() # needed a name for the turtle, i chose trt, after Marcus trt, he was a cool dude
    vindu = turtle.Screen() # need a screen
    trt.penup() # Dont draw while setting up
    trt.speed(0) # set appropriate speed, fast speed because lots to draw
    angle = math.degrees(2 * math.pi/dots) # angle between points, in degrees
    circle(trt, radius) # draw circle
    #info(trt, radius, angle, dots)

    for i in range(0, dots): # loop for all the lines
        calc(trt, i, angle, radius, factor) # call calc function

    vindu.exitonclick() # click when you want to close window after drawing is done

# Declare variables here because i want to:
# Choose the amount of dots
dots = 50
# Choose the radius of the circle
radius = 200 
# choose number which you want to multiply with
factor = 2
# call main
main(dots, radius, factor) 