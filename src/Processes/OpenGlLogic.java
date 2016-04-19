package Processes;

import CoreConstants.Constants;
import CoreConstants.GlLibrary;
import Foundation.*;
import ManagersandCreators.CharacterCreator;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

/**
 * Created by rishi on 2/18/16.
 */
public class OpenGlLogic {


    private int map[][]= new int[1000][1000];
    private boolean[] kbState;
    private int[] spriteTex=new int[12];
    protected int[] background=new int[10];

    protected static GL2 gl;
    private int[] spriteSize;
    private GLWindow window;
    protected int windowWidth;
    protected int windowHeight;
    protected Camera camera;
    private Maps maps= new Maps();
    protected int[][] currentLevel= Constants.currentlevel;
    protected int tileWidth;
    protected int tileHeight;

    public OpenGlLogic(Camera camera, final boolean[] kbState, CharacterCreator characterCreator, int[] spriteSize){
        this.windowWidth=Constants.windowWidth;
        this.windowHeight=Constants.windowHeight;
        Foundation.Window windowClass=new Foundation.Window(windowWidth,windowHeight,kbState);
        this.window=windowClass.getWindow();
        this.gl=windowClass.getGl();
        this.kbState=windowClass.getKbState();
        this.camera=camera;
        this.spriteTex=characterCreator.getHero(0).getSprite();
        this.kbState=kbState;
        this.spriteSize=spriteSize;

        this.tileWidth=windowWidth/32;
        this.tileHeight=windowHeight/32;


    }
    // Load a file into an OpenGL texture and return that texture.

    public GL2 getGl() {
        return gl;
    }


    public void loadSprites() {
        // Load the texture.
        // Load the texture.
        background[0]= GlLibrary.glTexImageTGAFile(gl,"Media/Background/GreenPasture.tga",spriteSize);
        background[1]= GlLibrary.glTexImageTGAFile(gl,"Media/Background/RedPasture.tga",spriteSize);
    }

    public boolean display() {
        // Actually, this runs the entire OS message pump.
        window.display();
        if (!window.isVisible()) {
            return true;
        }
        return false;
    }
    public void glDrawSprite(int x, int y, int w, int h, int frame,int[] sprite){
        //optimization

            if (x - camera.getX() < windowWidth && x - camera.getX() > 0 && y - camera.getY() < windowHeight && y > -10) {
                GlLibrary.glDraw(sprite[frame], x - camera.getX(), y - camera.getY(), w, h, gl);
            }


    }


    public void presntToPlayer() {
        // Present to the player.

        window.swapBuffers();
    }

    public void clearScreen() {
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    }


    public void drawBackground() {
        //optimization

        for (int i = 0; i < tileHeight + 2; i++) {
            for (int j = 0; j < tileWidth + 1; j++) {
                GlLibrary.glDraw(background[currentLevel[i + camera.getY() / 32][j + camera.getX() / 32]], j * 32 - camera.getX() % 32, i * 32 - camera.getY() % 32, 32, 32, gl);
            }
        }

    }
    public void DisplayScore(){
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 30));
        textRenderer.beginRendering(900, 700);
        textRenderer.setColor(Color.CYAN);
        textRenderer.setSmoothing(true);

        Point pt = new Point(640, 600);
        textRenderer.draw("Score "+Constants.score, (int) (pt.x), (int) (pt.y));
        textRenderer.draw("Top Score "+Constants.topScore, (int) (pt.x), (int) (pt.y-30));

        textRenderer.endRendering();
    }

    public void drawBackgroundTile() {
        int tempBackground = GlLibrary.glTexImageTGAFile(gl, "TileHighlight.tga", new int[2]);
        for(int i=0; i<windowWidth+10; i+=32){
            for (int j=0;j<windowHeight+10; j+=32) {

                GlLibrary.glDraw(tempBackground, i-camera.getX()%32, j-camera.getY()%32,32, 32,gl);
            }
        }
    }
}
