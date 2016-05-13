package Processes;

import Foundation.Maps;

import java.util.Random;

/**
 * Created by rishi on 3/20/16.
 */
public class AIPositionTracker implements Runnable {

    private int[] characterPos =new int[2];
    private Character character;
    private boolean alive=true;


    public AIPositionTracker(Character character){
        this.character=character;
        new Thread(this).start();

    }




    @Override
    public void run() {

        while(alive) {
            if (Maps.Location(character.getPosition()[0],character.getPosition()[1]) != 1) {
                characterPos[0] = character.getPosition()[0];
                characterPos[1] = character.getPosition()[1];
            }

                if (Maps.Location(character.getPosition()[0],character.getPosition()[1]) == 1) {
                    System.out.println("Hero collision detected "+ new Random().nextInt());
                    character.getPosition()[0] = getCharacterPos()[0];
                    character.getPosition()[1] = getCharacterPos()[1];
                }




            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getCharacterPos() {
        return characterPos;
    }

}
