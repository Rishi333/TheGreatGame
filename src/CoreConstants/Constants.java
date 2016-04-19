package CoreConstants;

import Foundation.Camera;
import Foundation.Maps;
import ManagersandCreators.AudioManager;
import ManagersandCreators.EventHandler;

/**
 * Created by rishi on 4/15/16.
 */
public class Constants {

    // Set this to true to force the game to exit.
    public static boolean shouldExit;

    // The previous frame's keyboard state.
    public static boolean kbPrevState[] = new boolean[256];
    // The nt frame's keyboard state.ZZZZZZ
    public static boolean kbState[] = new boolean[256];

    //framerate
    public static int frameRate=20;
    public static int inputSpeed=32;

    public static int windowWidth=1200;
    public static int windowHeight=600;

    public static int TileSize=32;
    public static int levelSize=40;
    public static int[][] currentlevel= Maps.level2;

    public static int enemyCount=60;

    public static int score=0;
    public static int topScore=200;

    public static boolean twoPlayer=false;// automatically set to false cause of cool java


    public static Camera[] camera= new Camera[]{new Camera(0,0),null};

    // Size of the sprite.
    public static int[] spriteSize = new int[2];

    //ManagersandCreators
    public static EventHandler eventHandler=new EventHandler();
    public static AudioManager audioManager=new AudioManager();


}
