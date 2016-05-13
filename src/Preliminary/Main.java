package Preliminary;

import ManagersandCreators.Game;

/**
 * Created by rishi on 5/9/16.
 */
public class Main {

    public static void main(String[] args) {


        //Starting music and testing events
       // Constants.eventHandler.addEvent(new Event(EventCode.AUDIO,new int[]{500},new String[]{"file.mp3"}));
        //Constants.eventHandler.addEvent(new Event(EventCode.MESSAGE,null,new String[]{"file.mp3"}));

        new StartScreen();
        //new SettingScreen();


        new Game();
    }
}
