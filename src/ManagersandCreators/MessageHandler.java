package ManagersandCreators;

import CoreConstants.Constants;
import Foundation.Event;

import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

/**
 * Created by rishi on 4/18/16.
 */
public class MessageHandler extends EventHandler {

    /* The Message Handler class will use int params in order to store time for the message
    * 1. store the creation millis
    * 2. store the  duration time
    * 3.  store x coordinate
    * 4. store y coordinate*/



    @Override
    public void handleEvent(Event event) {
        TextRenderer textRenderer = new TextRenderer(new Font("Verdana", Font.BOLD, 30));
        textRenderer.beginRendering(900, 700);
        textRenderer.setColor(Color.CYAN);
        textRenderer.setSmoothing(true);

        Point pt = new Point(540, 600);
        textRenderer.draw("Score "+ Constants.score, (int) (pt.x), (int) (pt.y));
        textRenderer.draw("Top Score "+Constants.topScore, (int) (pt.x), (int) (pt.y-30));

        textRenderer.endRendering();
    }
    public void displayMessages(){
        for(int i=0;i <events.size();i++){
            displayMessage(events.get(i));
        }
    }
    public void displayMessage(Event event){

    }
    @Override
    public void run() {
        while(active){
            if(events.size()>0){



            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
