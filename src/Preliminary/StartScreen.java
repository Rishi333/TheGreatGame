package Preliminary;

import CoreConstants.Constants;
import CoreConstants.GlLibrary;
import Foundation.Window;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

import static CoreConstants.Constants.kbState;


/**
 * Created by rishi on 5/11/16.
 */
public class StartScreen {
    boolean alive= true;
    Window window;
    GL2 gl;
    public int selectedIndex=0;
    private int background;
    private long time= System.currentTimeMillis();
    private int options=2;

    public StartScreen(){

        window=new Window(Constants.windowWidth,Constants.windowHeight);
        gl=window.getGl();
        //setup=new SetupStart(window);
        background=GlLibrary.glTexImageTGAFile(gl,"Media/Background/Start.tga",new int[]{100,100});


        // The game loop
        while (!Constants.shouldExit) {
            long time= System.currentTimeMillis();

            //Gl Logic Stuff
            clearScreen();
            drawBackground();

            displayPrompt(200,500, selectedIndex==0, " Start Game");

            //displayPrompt(200,400, selectedIndex==2,"Settings");

            displayPrompt(200,300, selectedIndex==1,"Settings ");

            //to Add a prompt, make sure to increase options

            //displayPrompt(200,200, selectedIndex==6,"Gravity Strength :");

            // displayPrompt(Constants.windowWidth/2-100,100, selectedIndex==0,"Tile Size"setup.objects[4].get(setup.index[4]).toString());


            checkInput();
            presntToPlayer();

            if(display()){
                break;
            }
            if(!alive){
                break;
            }
            GlLibrary.checkLag(time, Constants.frameRate);
        }


    }
    public void checkInput() {
        if (System.currentTimeMillis() - time > 100) {

            if (kbState[KeyEvent.VK_W]) {
                if (selectedIndex == 0) {
                    selectedIndex = options - 1;
                } else {
                    selectedIndex--;
                }
                time = System.currentTimeMillis();
            } else if (kbState[KeyEvent.VK_S]) {
                if (selectedIndex == options - 1) {
                    selectedIndex = 0;
                } else {
                    selectedIndex++;
                }
                time = System.currentTimeMillis();

            } else if (kbState[KeyEvent.VK_B]) {
                if (selectedIndex == 0) {
                    alive = false;// Game Start
                    window.getWindow().setVisible(false);
                    window.getWindow().destroy();
                } else if (selectedIndex == 1) {
                    //setup.next(settingScreen.selectedIndex);
                    window.getWindow().setVisible(false);
                    window.getWindow().destroy();
                    new SettingScreen();

                }
                time = System.currentTimeMillis();
            }
        }
    }




    public void displayPrompt(int x, int y, boolean selected, String string){
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 30));
        textRenderer.beginRendering(Constants.windowWidth, Constants.windowHeight);
        if(selected) {
            textRenderer.setColor(Color.YELLOW);
        }else {// not selected
            textRenderer.setColor(Color.CYAN);
        }
        textRenderer.setSmoothing(true);

        Point pt = new Point(x, y);
        textRenderer.draw(string, (int) (pt.x), (int) (pt.y));
        textRenderer.endRendering();
    }
    public void clearScreen() {
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    }
    public void presntToPlayer() {
        // Present to the player.

        window.getWindow().swapBuffers();
    }
    public boolean display() {
        // Actually, this runs the entire OS message pump.
        window.getWindow().display();
        if (!window.getWindow().isVisible()) {
            return true;
        }
        return false;
    }
    public void drawBackground() {
        //optimization

        GlLibrary.glDraw(background,  0, 0, Constants.windowWidth, Constants.windowHeight, gl);

    }
}
