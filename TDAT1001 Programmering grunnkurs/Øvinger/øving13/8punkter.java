import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.GL2GL3.GL_POINT;

public class Testing extends GLCanvas implements GLEventListener {
    private static String TITLE = "Øving 13";
    private static final int CANVAS_WIDTH = 1500;  // width of the drawable
    private static final int CANVAS_HEIGHT = 800; // height of the drawable
    static final float points[] = {
            0.0f, 2.0f, 0.0f,
            1.5f, 1.5f, 0.0f,
            2.0f, 0.0f, 0.0f,
            1.5f, -1.5f, 0.0f,
            0.0f, -2.0f, 0.0f,
            -1.5f, -1.5f, 0.0f,
            -2.0f, 0.0f, 0.0f,
            -1.5f, 1.5f, 0.0f, };

    private GLU glu;  // for the GL Utility
    public Testing() {
        this.addGLEventListener(this);
    }
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
        glu = new GLU();                         // get GL Utilities
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
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
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix

        int i = 0;
//___________________________________________________________________________ Tegn her :)
        gl.glTranslatef(-20.0f, 4.0f, -30.0f); // into the screen
        gl.glBegin(GL_POINTS);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL_LINES);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL_LINE_STRIP);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL_LINE_LOOP);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL_TRIANGLES);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL_TRIANGLE_STRIP);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL_TRIANGLE_FAN);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(-30.0f, -5.0f, 0.0f);
        gl.glBegin(GL_QUADS);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
        gl.glTranslatef(5.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_POLYGON);
        for(i = 0; i < points.length/3; i++) {
            int arrayIndex = i * 3;
            gl.glVertex3fv(points, arrayIndex);
        }
        gl.glEnd();
    }

    public void dispose(GLAutoDrawable drawable) { }
    public static void main(String[] args) {
        GLCanvas canvas = new Testing();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);
    }
}
