import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.*;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class RobotDude extends GLCanvas implements GLEventListener {
    private static String TITLE = "Øving 15";
    private static final int CANVAS_WIDTH = 1500;  // width of the drawable
    private static final int CANVAS_HEIGHT = 800; // height of the drawable

    private GLU glu;  // for the GL Utility
    private GLUT glutenAllergi = new GLUT();

    private int rotAngle = 0;
    private int k = 0;

    private double xMove = 0;
    private double zMove = 0;
    private double yJump = 0;

    private double legAng = 0;
    private double armAng = 0;
    private double camAng = 0;

    private boolean idk = true;
    private boolean idk2 = true;
    private boolean idk3 = false;
    private boolean up2 = true;


    private double adjX = 0;
    private double adjY = 0;
    private double adjZ = 0;

    private RobotDude() {
        this.addGLEventListener(this);
        this.addKeyListener(new RotateKeyListener());
    }
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 1.0f, 0.0f); // set background (clear) color
        gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

        if (height == 0) height = 1;   // prevent divide by zero
        float aspect = (float)width / height;
        gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
        gl.glLoadIdentity();             // reset projection matrix
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); // reset
    }
    private void head(GL2 gl){

        gl.glTranslated(0,1.5,0);
        gl.glRotated(90.0, 0, 1,0);
        gl.glColor3f(1.0f, 0.4f, 1.0f);
        glutenAllergi.glutSolidTeapot(0.25);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glutenAllergi.glutWireTeapot(0.2505);
    }
    private void body(GL2 gl){

        gl.glColor3f(0.4f, 0.4f, 1.0f);
        gl.glTranslated(0,1,0);
        glutenAllergi.glutSolidCube(0.5f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glutenAllergi.glutWireCube(0.505f);
    }
    private void arms(GL2 gl){
        double size = 0.07125;
        double length = 0.5;


        gl.glTranslated(0,1.3,0);
        gl.glRotated(90, 1,0,0);

        gl.glPushMatrix();

        gl.glTranslated(-0.3,0,0);
        gl.glRotated(armAng, 1,0 ,0);

        gl.glColor3d(0.2, 0.2, 1);
        glutenAllergi.glutSolidSphere(size, 9, 5);
        gl.glColor3d(0,0,0);
        glutenAllergi.glutWireSphere(size+0.01, 9, 5);

        gl.glColor3f(0.4f, 1.4f, 1.0f);
        glutenAllergi.glutSolidCylinder(size, length, 8, 3);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glutenAllergi.glutWireCylinder(size+0.01, length, 8, 3);

        gl.glPopMatrix();
        gl.glPushMatrix();

        gl.glTranslated(0.3,0, 0);
        gl.glRotated(-armAng, 1,0 ,0);

        gl.glColor3d(0.2, 0.2, 1);
        glutenAllergi.glutSolidSphere(size, 9, 5);
        gl.glColor3d(0,0,0);
        glutenAllergi.glutWireSphere(size+0.01, 9, 5);

        gl.glColor3f(0.4f, 1.4f, 1.0f);
        glutenAllergi.glutSolidCylinder(size, length, 8, 3);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glutenAllergi.glutWireCylinder(size+0.01, length, 8, 3);

        gl.glPopMatrix();

    }
    private void legs(GL2 gl){
        double size = 0.125;
        double length = 0.7;

        gl.glTranslated(0,0.7,0);
        gl.glRotated(90, 1,0,0);

        gl.glPushMatrix();

        gl.glRotated(legAng, 1,0 ,0);
        gl.glTranslated(0.25,0,0);

        gl.glColor3d(0.2, 0.2, 1);
        glutenAllergi.glutSolidSphere(size, 9, 5);
        gl.glColor3d(0,0,0);
        glutenAllergi.glutWireSphere(size+0.01, 9, 5);

        gl.glColor3f(0.4f, 1.4f, 1.0f);
        glutenAllergi.glutSolidCylinder(size, length, 8, 3);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glutenAllergi.glutWireCylinder(size+0.01, length, 8, 3);

        gl.glPopMatrix();
        gl.glPushMatrix();

        gl.glRotated(-legAng, 1,0 ,0);
        gl.glTranslated(-0.25,0,0);

        gl.glColor3d(0.2, 0.2, 1);
        glutenAllergi.glutSolidSphere(size, 9, 5);
        gl.glColor3d(0,0,0);
        glutenAllergi.glutWireSphere(size+0.01, 9, 5);

        gl.glColor3f(0.4f, 1.4f, 1.0f);
        glutenAllergi.glutSolidCylinder(size, length, 8, 3);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        glutenAllergi.glutWireCylinder(size+0.01, length, 8, 3);

        gl.glPopMatrix();
    }
    private void robot(GL2 gl){
        gl.glPushMatrix();
        legs(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        arms(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        head(gl);
        gl.glPopMatrix();
        gl.glPushMatrix();
        body(gl);
        gl.glPopMatrix();

    }
    private void world(GL2 gl){
        gl.glPushMatrix();

        glutenAllergi.glutWireSphere(40,40,40);
        gl.glColor3d(0.2,0.7,0.2);
        float str = 12;
        gl.glTranslated(0,-(str/2),-(str/4));
        glutenAllergi.glutSolidCube(str);
        gl.glColor3d(0,0,0);
        glutenAllergi.glutWireCube((float)(str+0.01));

        gl.glTranslated(1, str/2, 0);
        stone(gl);
        gl.glTranslated(-3, 0, -2.5);
        stone(gl);

        gl.glPopMatrix();
    }
    private void stone(GL2 gl){
        gl.glPushMatrix();
        gl.glRotated(-90, 1, 0, 0);
        gl.glColor3d(0.5, 0.5, 0.5);
        glutenAllergi.glutSolidCone(0.5, 0.5, 10, 10);
        gl.glColor3d(0,0,0);
        glutenAllergi.glutWireCone(0.52, 0.52, 10, 10);
        gl.glPopMatrix();
    }
    private void setCamAng(int x){
        camAng += 4*x;
    }
    private void cameraPos(int x, int y, int z, int i){

        adjX += 0.2*x;
        adjY += 0.2*y;
        adjZ += 0.2*z;
        k += i;

        glu.gluLookAt((float)adjX, (float)adjY+5, (float)adjZ,  // eye
                0.0f, 0.0f, -5.0f+k,  // center
                0.0f, 2.0f, 0.0f); // up*/

        RobotDude.this.repaint();
    }
    private void movement(int x, double y){
        double radians = Math.toRadians(rotAngle);
        System.out.println(radians +" radianer");

        xMove += (Math.cos(radians)*x)/15;
        zMove += (Math.sin(radians)*x)/15;

        boundaries();
        legMovement(y);
    }
    private void legMovement(double y){
        if (idk){
            legAng += 5*y;
            if (legAng >= 45) idk = false;
        } else {
            legAng -= 5*y;
            if (legAng <= -45) idk = true;
        }
        armMovement(y);
    }
    private void armMovement(double y){
        if (idk2){
            armAng += 5*y;
            if (armAng >= 45) idk2 = false;
        } else {
            armAng -= 5*y;
            if (armAng <= -45) idk2 = true;
        }
    }
    private void boundaries(){
        if(xMove >= 2.5) xMove = 2.5;
        if(xMove <= -8.5) xMove = -8.5;
        if(zMove >= 5) zMove = 5;
        if(zMove <= -5) zMove = -5;

        if(yJump < 0){
            yJump = 0;
        }
    }
    private boolean getOnGround(){
        boolean onGround;
        if(yJump == 0){
            onGround = true;
        }else{
            onGround = false;
        }
        return onGround;
    }
    private void jump(GL2 gl){
        boolean temp = true;
        boolean up = true;
        if(getOnGround()){
            while(temp){
                legAng = 45;
                armAng = 45;
                if(up){
                    yJump += 0.02;
                    if(yJump >= 0.8) up = false;
                    System.out.println(yJump);
                    RobotDude.this.repaint();

                    //glutenAllergi.glutPostRedisplay();

                    try { Thread.sleep(50);
                    } catch (InterruptedException ignored){}
                }else {
                    yJump -= 0.02;
                    if(yJump <= 0) temp = false;
                    System.out.println(yJump);
                    RobotDude.this.repaint();
                    try { Thread.sleep(50);
                    } catch (InterruptedException ignored){}
                }
            }
            legAng = 0;
            armAng = 0;
            idk = false;
        }
    }
    public void jump2(){
        if(up2){
            yJump += 0.2;
            if(yJump >= 2) up2 = false;
        }else {
            yJump -= 0.2;
            if(yJump <= 0) up2 = true;
        }
    }
    public void reset(){
        xMove = 0;
        zMove = 0;
        yJump = 0;
    }
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

       // glutenAllergi.glutIdleFunc();

        cameraPos(0,0,0,0);

        gl.glTranslated(0,1, -2.0);
        gl.glRotated(camAng, 0,1,0);
        gl.glTranslated(0,-2,0);
        world(gl);

        //glutenAllergi.glutIdleFunc();

        gl.glTranslated(zMove,yJump, xMove);
        gl.glRotated(rotAngle, 0,1,0);

        robot(gl);
        if(idk3)jump(gl);
    }
    public void dispose(GLAutoDrawable drawable) { }
    public static void main(String[] args) {
        GLCanvas canvas = new RobotDude();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);
    }
    private class RotateKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getExtendedKeyCode());
            char c = e.getKeyChar();
            System.out.println(c +" trykk");
            if(rotAngle == 360 || rotAngle == -360) rotAngle = 0;

            switch(c){
                case 'a':
                    rotAngle += 4;
                    legMovement(0.5);
                    System.out.println(rotAngle +" grader");
                    break;
                case 'd':
                    rotAngle -= 4;
                    legMovement(0.5);
                    System.out.println(rotAngle +" grader");
                    break;
                case 'w':
                    movement(-1, 1);
                    System.out.println(xMove +", "+ yJump +", "+ zMove +" pos");
                    break;
                case 's':
                    movement(1, 1);
                    System.out.println(xMove +", "+ yJump +", "+ zMove +" pos");
                    break;
                case 'q':
                    cameraPos(-1, 0, 1,0);
                    //setCamAng(-1);
                    break;
                case 'e':
                    cameraPos(1,0,-1,0);
                    //setCamAng(1);
                    break;
                case 'f':
                    cameraPos(0,0,0,1);
                    break;
                case ' ':
                    //idk3 = true;
                    jump2();
                    System.out.println(yJump);
                    try{ Thread.sleep(10);}
                    catch (InterruptedException ignored) {}
                    break;
                case 't':
                    yJump = 0;
                    break;
                case 'r':
                    reset();
                    break;
                default:
                    break;
            }
            RobotDude.this.repaint();
        }
    }
}
