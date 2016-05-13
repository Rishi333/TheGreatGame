package Processes;

import CoreConstants.Constants;
import Foundation.Force;
import Foundation.Maps;

/**
 * Created by rishi on 4/20/16.
 */
public class Gravity extends Force implements Runnable {


    private Character character;

    public Gravity(Character character){
        this.character=character;
        new Thread(this).start();

    }

    @Override
    public void run() {
        while(true){
            if(Maps.Location(character.getPosition()[0],character.getPosition()[1]+Constants.gravity+10)!=1){
                y=Constants.gravity;

            }else{// charachter is grounded
                y=0;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
