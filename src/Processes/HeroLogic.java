package Processes;

import Foundation.TimeController;
import Foundation.Camera;
import CoreConstants.Constants;
import Foundation.Hero;
import Foundation.Maps;
import com.jogamp.newt.event.KeyEvent;

/**
 * Created by rishi on 2/18/16.
 */
public class HeroLogic implements Runnable {

    protected final int[] HeroPos;
    private TimeController timeController;
    //static variable
    private boolean[] kbState;
    protected int inputSpeed;
    protected Camera camera;
    protected int windowWidth;
    protected int windowHeight;
    private int inputRight=0;
    private int inputLeft=0;
    private int counter=0;
    private Hero hero;
    private long currentMillis=0;



    protected boolean Exit=false;


    public HeroLogic(Camera camera, boolean[] kbState, Hero hero, TimeController timeController){
        this.camera=camera;
        this.inputSpeed=Constants.inputSpeed;
        this.HeroPos= hero.getPosition();
        this.kbState=kbState;
        this.windowWidth=Constants.windowWidth;
        this.windowHeight= Constants.windowHeight;
        this.timeController=timeController;
        this.hero=hero;

    }
    public boolean CheckExit(){
        if (kbState[KeyEvent.VK_ESCAPE]) {
            return true;
        }
        return false;
    }
    public void doHeroLogic( int[] spritePos){




        doWeaponLogic();

        // ManagersandCreators.Game logic.
        if (kbState[hero.left]&&spritePos[0]>5+camera.getX()) {
            //
            if(Maps.Location((spritePos[0]-5-20),spritePos[1])!=1) {
                spritePos[0] -= 5;
                inputLeft++;
            }
        }

        if (kbState[hero.right]&&spritePos[0]<windowWidth-40+camera.getX()) {
            if(Maps.Location((spritePos[0]+5),spritePos[1])!=1) {
                spritePos[0] += 5;
                inputRight++;
            }
        }

        if (kbState[hero.up]&&spritePos[1]>5+camera.getY()) {
            if(Maps.Location((spritePos[0]),(spritePos[1]))!=1) {
                spritePos[1] -= 5;
            }
        }

        if (kbState[hero.down]&&spritePos[1]<windowHeight-40+camera.getY()) {
            // 12 is a modifier for the hero
            if(Maps.Location((spritePos[0]),(spritePos[1]+5+12))!=1) {
                spritePos[1] += 5;
            }

        }

        //Used for animation
        if(inputLeft>inputSpeed/10) {
            timeController.setStateLeft();
            timeController.setCurrentFrame((timeController.getCurrentFrame() + 1) % 6);
            inputLeft = 0;
        }else if(inputRight>inputSpeed/10){
            timeController.setStateRight();
            timeController.setCurrentFrame((timeController.getCurrentFrame()+1)%6);
            inputRight=0;

        }else {
            counter++;
            if (counter>inputSpeed/2){
                timeController.setCurrentFrame(0);
                counter=0;
            }

        }

    }
    public void setExit(boolean exit) {
        Exit = exit;
    }

    @Override
    public void run() {
        while(!Exit) {
            doHeroLogic(HeroPos);
            doCameraLogic();
            try {
                Thread.sleep(1000/inputSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doCameraLogic() {

        //Need to fix to allow not magic constants-> Use variable for pixels
        int speed=5;

        if (HeroPos[0] - 300 < camera.getX() && camera.getX() > 0) {
            camera.setX(camera.getX() - speed);
        } else if (HeroPos[0] - 340 > camera.getX() && camera.getX() <= Constants.TileSize * Constants.levelSize - (windowWidth + 70)) {
            camera.setX(camera.getX() + speed);
        }
        if (HeroPos[1] - 220 < camera.getY() && camera.getY() > 0) {
            camera.setY(camera.getY() - speed);
        } else if (HeroPos[1] - 260 > camera.getY() && camera.getY() <= Constants.TileSize * Constants.levelSize - (windowHeight + 75)) {
            camera.setY(camera.getY() + speed);
        }


    }
    public void doWeaponLogic(){
        if (kbState[hero.attack]&& System.currentTimeMillis()-currentMillis>300) {
            currentMillis= System.currentTimeMillis();
            int possibleFire=-1;
            for(int i=0;i<hero.getFirePos().length;i++){
                if(hero.getFirePos()[i][2]==0){
                    possibleFire=i;
                    hero.getFirePos()[i][2]=1;
                    hero.getFirePos()[i][3]=(timeController.getLeftRightModifier()>1)? -1:1;// setting direction
                    i=hero.getFirePos().length;//break out of loop you found a possible fire ball
                }

            }
            if(possibleFire!=-1){// create the fireball at the hero location
                hero.getFirePos()[possibleFire][0]=hero.getPosition()[0];
                hero.getFirePos()[possibleFire][1]=hero.getPosition()[1];
            }
        }
        if (kbState[hero.attack2]) {
            currentMillis= System.currentTimeMillis();
            int possibleFire=-1;
            for(int i=0;i<hero.getFirePos().length;i++){
                if(hero.getFirePos()[i][2]==0){
                    possibleFire=i;
                    hero.getFirePos()[i][2]=1;
                    hero.getFirePos()[i][3]=(timeController.getLeftRightModifier()>1)? -1:1;// setting direction
                    i=hero.getFirePos().length;//break out of loop you found a possible fire ball
                }

            }
            if(possibleFire!=-1){// create the fireball at the hero location
                hero.getFirePos()[possibleFire][0]=hero.getPosition()[0];
                hero.getFirePos()[possibleFire][1]=hero.getPosition()[1];
            }
        }
        //Add to fireball timer and move fireball
        for(int i=0;i<hero.getFirePos().length;i++) {
            if (hero.getFirePos()[i][2] >= 1) {

                hero.getFirePos()[i][2]++;
                hero.getFirePos()[i][0]+=8*hero.getFirePos()[i][3];

            }
            if(hero.getFirePos()[i][2]>inputSpeed){// Disable command
                hero.getFirePos()[i][2]=0;

            }
        }
    }

}
