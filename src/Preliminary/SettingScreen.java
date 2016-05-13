package Preliminary;


import CoreConstants.Constants;
import CoreConstants.GlLibrary;
import Foundation.Window;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

/**
 * Created by rishi on 5/9/16.
 */
public class SettingScreen {
    //used to set initial settings
    boolean alive= true;
    Window window;
    GL2 gl;
    public static Setup setup;
    public int selectedIndex=3;
    public SettingScreenInput settingScreenInput;
    private int background;

    public SettingScreen(){

        window=new Window(Constants.windowWidth,Constants.windowHeight);
        gl=window.getGl();
        setup=new Setup(window);
        settingScreenInput =  new SettingScreenInput(this, setup);
        background=GlLibrary.glTexImageTGAFile(gl,"Media/Background/Start.tga",new int[]{100,100});


        // The game loop
        while (!Constants.shouldExit) {
            long time= System.currentTimeMillis();

            //Gl Logic Stuff
            clearScreen();
            drawBackground();

            Point point=(Point)setup.objects[0].get(setup.index[0]);
            displayPrompt(200,500, selectedIndex==0,point.x+ " pixels by "+point.y+" pixels");
            displayPrompt(200,450, selectedIndex==1,"Two Players : "+setup.objects[1].get(setup.index[1]).toString());
            displayPrompt(200,400, selectedIndex==2,"Enemy Count "+setup.objects[2].get(setup.index[2]).toString());
            displayPrompt(200,350, selectedIndex==3,"Enemy Speed "+setup.objects[3].get(setup.index[3]).toString());
            displayPrompt(200,300, selectedIndex==4,"Tile Size "+setup.objects[4].get(setup.index[4]).toString());
            displayPrompt(200,250, selectedIndex==5,"Level "+setup.objects[5].get(setup.index[5]).toString());
            displayPrompt(200,200, selectedIndex==6,"Gravity Strength :"+setup.objects[6].get(setup.index[6]).toString());
            displayPrompt(200,150, selectedIndex==7,"Start");
           // displayPrompt(Constants.windowWidth/2-100,100, selectedIndex==0,"Tile Size"setup.objects[4].get(setup.index[4]).toString());


            settingScreenInput.checkInput();
            presntToPlayer();

            if(display()){
                break;
            }
            GlLibrary.checkLag(time, Constants.frameRate);
        }

        setup.setConstants();
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