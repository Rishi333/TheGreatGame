package Foundation;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;

/**
 * Created by rishi on 3/2/16.
 */
public class Window {
    private GLWindow window;
    private GLProfile gl2Profile;
    boolean[] kbState;
    GL2 gl;

    public Window( int windowWidth, int windowHeight,final boolean[] kbState){
        this.kbState=kbState;

        try {
            // Make sure we have a recent version of OpenGL
            gl2Profile = GLProfile.get(GLProfile.GL2);
        }
        catch (GLException ex) {
            System.out.println("OpenGL max supported version is too low.");
            System.exit(1);
            return;
        }

        // Create the window and OpenGL context.
        window = GLWindow.create(new GLCapabilities(gl2Profile));
        window.setSize(windowWidth, windowHeight);
        window.setTitle("Java Framework");
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.addKeyListener(new KeyListener() {


            @Override
            public void keyPressed(KeyEvent keyEvent) {
                kbState[keyEvent.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                kbState[keyEvent.getKeyCode()] = false;
            }
        });

        // Setup OpenGL state.
        window.getContext().makeCurrent();
        gl = window.getGL().getGL2();
        gl.glViewport(0, 0, windowWidth, windowHeight);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glOrtho(0, windowWidth, windowHeight, 0, 0, 100);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
    }

    public GLWindow getWindow() {
        return window;
    }

    public GLProfile getGl2Profile() {
        return gl2Profile;
    }

    public boolean[] getKbState() {
        return kbState;
    }

    public GL2 getGl() {
        return gl;
    }
}
