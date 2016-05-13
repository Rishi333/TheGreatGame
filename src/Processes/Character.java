package Processes;

import CoreConstants.Constants;
import Foundation.Force;
import Foundation.Hero;
import Processes.Gravity;
import Processes.OpenGlLogic;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by rishi on 3/9/16.
 */
public class Character implements Runnable {

    // Position of the sprite.
    private int[] position;

    // Texture for the sprite.
    private int[] sprite;

    private boolean stuck=false;

    private boolean grounded=false;
    private int forceX;
    private int forceY;
    private LinkedList<Force> forces= new LinkedList<>();


    public Character(int xLocation, int yLocation, int FrameSize){

        position=new int[] {xLocation,yLocation};
        sprite=new int[FrameSize];
        if(Constants.gravityState) {
            this.addForce(new Gravity(this));
            new Thread(this).start();
        }

    }
    private void compileForces(){
        forceX=0;
        forceY=0;
        for(Force force: forces){
            forceX+=force.getX();
            forceY+=force.getY();
        }
    }
    public void addForce(Force force){
        forces.add(force);
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] getSprite() {
        return sprite;
    }

    public void setSprite(int[] sprite) {
        this.sprite = sprite;
    }


    public void setSprite(int i) {
        this.sprite[0]=i;
    }

    public void drawBullets(OpenGlLogic openGlLogic, Hero hero) {
        int length= hero.getFirePos().length;
        int[][] bullets=hero.getFirePos();
        int[] sprite=hero.getFireBall();
        for(int i=0; i<length; i++){
            if(bullets[i][2]!=0) {// checking valid fireballs
                if(bullets[i][3]==1){// checking direction for correct texture
                    openGlLogic.glDrawSprite(bullets[i][0], bullets[i][1], 48, 48, 0, sprite);
                }else{
                    openGlLogic.glDrawSprite(bullets[i][0], bullets[i][1], 48, 48, 1, sprite);
                }
            }
        }
    }
    public boolean isGrounded() {
        return grounded;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }


    public boolean getStuck() {
        return stuck;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }

    @Override
    public void run() {
        while(true){
            compileForces();
            position[0]+=forceX;
            position[1]+=forceY;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
