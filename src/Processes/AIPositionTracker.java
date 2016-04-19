package Processes;

import Foundation.Hero;
import ManagersandCreators.CharacterCreator;
import CoreConstants.Constants;
import Foundation.Maps;

/**
 * Created by rishi on 3/20/16.
 */
public class AIPositionTracker implements Runnable {

    private int[] heroPos=new int[2];
    private int[][] enemies;
    private CharacterCreator characterCreator;
    private boolean alive=true;
    private Hero hero;


    public AIPositionTracker(CharacterCreator characterCreator, Hero hero){
        this.hero=hero;
        this.characterCreator=characterCreator;
        this.enemies=new int[characterCreator.getEnemies().length][2];

    }




    @Override
    public void run() {

        while(alive) {
            if (Maps.level1[hero.getPosition()[1]/ Constants.TileSize][hero.getPosition()[0]/Constants.TileSize] != 1) {
                heroPos[0] = hero.getPosition()[0];
                heroPos[1] = hero.getPosition()[1];
            }

            for (int i = 0; i < enemies.length; i++) {
                if(characterCreator.getEnemies()[i].getPosition()[1]>0&&characterCreator.getEnemies()[i].getPosition()[0]>0) {
                    enemies[i][0] = characterCreator.getEnemies()[i].getPosition()[0];
                    enemies[i][1] = characterCreator.getEnemies()[i].getPosition()[1];
                }
            }


            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getHeroPos() {
        return heroPos;
    }

    public int[][] getEnemies() {
        return enemies;
    }
    public void disable(){
        alive=false;
    }
}
