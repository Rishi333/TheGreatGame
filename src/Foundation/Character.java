package Foundation;

import Processes.OpenGlLogic;

/**
 * Created by rishi on 3/9/16.
 */
public class Character {

    // Position of the sprite.
    private int[] position;

    // Texture for the sprite.
    private int[] sprite;

    private boolean stuck=false;





    public Character(int xLocation, int yLocation, int FrameSize){

        position=new int[] {xLocation,yLocation};
        sprite=new int[FrameSize];


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

    public boolean getStuck() {
        return stuck;
    }

    public void setStuck(boolean stuck) {
        this.stuck = stuck;
    }
}
