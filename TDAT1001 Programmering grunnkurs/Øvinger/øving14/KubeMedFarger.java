import java.awt.*;
import javax.swing.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.GL2.GL_POLYGON;


import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class KubeMedFarger extends GLCanvas implements GLEventListener {
    private static String TITLE = "Øving 14";
    private static final int CANVAS_WIDTH = 1500;  // width of the drawable
    private static final int CANVAS_HEIGHT = 800; // height of the drawable

    private static double cornerPos[][] = {{1,1,1}, // punkt 1
            {-1,1,1}, // punkt 2
            {-1,-1,1}, // punkt 3
            {1,-1,1}, // punkt 4
            {1,-1,-1}, // punkt 5
            {-1,-1,-1}, // punkt 6
            {-1,1,-1}, // punkt 7
            {1,1,-1}, // punkt 8
    };

    private static float color[][] = {{0.2f,0.2f,1.0f}, // RGB
                                      {0.2f,1.0f,0.2f},
                                      {1.0f,0.0f,0.2f},
                                      {1.0f,1.0f,0.2f},
                                      {1.0f,0.5f,0.5f},
                                      {0.2f,1.0f,1.0f}
    };
    private int colorCounter = 0;
    private int cameraPos = 1;
    private GLU glu;  // for the GL Utility
    private GLUT glutenAllergi = new GLUT();
    private int rotAngle;
    private boolean up = true;

    public KubeMedFarger() {
        this.addGLEventListener(this);
        this.addKeyListener(new RotateKeyListener());
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
    public void drawSide(GL2 gl, int a, int b, int c, int d){
        gl.glColor3fv(color[colorCounter], 0);
        gl.glBegin(GL_POLYGON);
        gl.glVertex3dv(cornerPos[(a-1)],0);
        gl.glVertex3dv(cornerPos[(b-1)],0);
        gl.glVertex3dv(cornerPos[(c-1)],0);
        gl.glVertex3dv(cornerPos[(d-1)],0);
        gl.glEnd();
        colorCounter++;
    }
    public void drawCube(GL2 gl){
        drawSide(gl, 1, 2, 3, 4);
        drawSide(gl, 1, 2, 7, 8);
        drawSide(gl, 1, 4, 5, 8);
        drawSide(gl, 3, 4, 5, 6);
        drawSide(gl, 2, 3, 6, 7);
        drawSide(gl, 5, 6, 7, 8);
    }
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix
        /*glu.gluLookAt(rotAngle+0.0f, rotAngle+1.0f, rotAngle+0.0f,  // eye
                0.0f, 0.0f, -5.0f,  // center
                0.0f, 1.0f, 0.0f); // up*/

        //gl.glViewport(0, 0, 800+rotAngle, 800);



        switch (cameraPos) {
            case 1:
                glu.gluLookAt(0.0f, 1.0f, 0.0f,  // eye
                        0.0f, 0.0f, -5.0f,  // center
                        0.0f, 1.0f, 0.0f); // up
                gl.glViewport(0, 0, 800, 800);
                break;
            case 2:
                glu.gluLookAt(0.0f, 3.0f, 0.0f,  // eye
                        0.0f, 0.0f, -5.0f,  // center
                        0.0f, 1.0f, 0.0f); // up
                gl.glViewport(100, 100, 800, 800);
                break;
            case 3:
                glu.gluLookAt(0.0f, 5.0f, 0.0f,  // eye
                        0.0f, 0.0f, -5.0f,  // center
                        0.0f, 1.0f, 0.0f); // up
                gl.glViewport(50, 50, 800, 800);
                break;
            default:

                break;
        }

        gl.glTranslatef(0.0f, 0.0f, -7.0f); // into the screen
        gl.glRotated(rotAngle, 1, 1, 1);
        colorCounter = 0;
        drawCube(gl);
    }
    public void dispose(GLAutoDrawable drawable) { }
    public static void main(String[] args) {
        GLCanvas canvas = new KubeMedFarger();
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
            //accept any key
            rotAngle += 4;
            if(rotAngle == 360){
                rotAngle = 0;
                cameraPos++;
                if(cameraPos == 4) cameraPos = 1;
            }
            System.out.println(rotAngle);

           /* if(up){
                rotAngle += 1;
                if(rotAngle == 10) up = false;
            }else {
                rotAngle -=1;
                if(rotAngle == 0) up = true;
            }//*/

            KubeMedFarger.this.repaint();
        }
    }
}
