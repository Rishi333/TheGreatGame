package Processes;

import Foundation.Character;
import Foundation.Hero;
import ManagersandCreators.CharacterCreator;
import CoreConstants.Constants;
import Foundation.Maps;

import java.lang.*;
import java.util.Random;

/**
 * Created by rishi on 3/8/16.
 */
public class EnemyLogic implements Runnable {


    protected OpenGlLogic openGlLogic;
    protected Hero hero;
    protected boolean alive=true;
    protected Character[] enemies;
    protected int[][] enemyStuckCounter;
    protected CharacterCreator characterCreator;

    public EnemyLogic(CharacterCreator characterCreator, OpenGlLogic openGlLogic, Hero hero){
        this.openGlLogic=openGlLogic;
        this.enemies=characterCreator.getEnemies();
        this.hero=hero;
        enemyStuckCounter=new int[characterCreator.getEnemies().length][2];
        this.characterCreator=characterCreator;



    }
    public void drawEnemies(){
        for (int i = 0; i < enemies.length; i++) {
            openGlLogic.glDrawSprite(enemies[i].getPosition()[0], enemies[i].getPosition()[1], 48, 48, 0, enemies[i].getSprite());
        }

    }
    public void disableEnemies(){
        alive=false;
    }

    @Override
    public void run() {
        Random rand= new Random(System.currentTimeMillis());
        int movement=5;
        int x=0;
        while(alive) {

            if(x<10){
                movement=5;
            }else if(x<20){
                movement=-5;
            }else {
                x=0;
            }
            x++;
            for (int i = 0; i < enemies.length; i++) {//For every enemy do this

                //Random Logic
                //enemies[i].getPosition()[0]+=rand.nextInt()%5;
                //enemies[i].getPosition()[1]+=rand.nextInt()%5;
                int enemySpeed=2;
                setHero(enemies[i]);


                    int xPos = (hero.getPosition()[0] - enemies[i].getPosition()[0]) % enemySpeed+ rand.nextInt() % 2;
                    int yPos = (hero.getPosition()[1] - enemies[i].getPosition()[1]) % enemySpeed + rand.nextInt() % 2;
                    if (Maps.Location(xPos + enemies[i].getPosition()[0], enemies[i].getPosition()[1]) != 1) {
                        enemies[i].getPosition()[0] += xPos;
                        enemyStuckCounter[i][0]=0;
                    }else{
                        enemyStuckCounter[i][0]++;
                        if(enemyStuckCounter[i][0]>Constants.frameRate*5){
                            enemies[i].getPosition()[0] += xPos;
                        }
                    }
                    if (Maps.Location(enemies[i].getPosition()[0], yPos + enemies[i].getPosition()[1]) != 1) {
                        enemies[i].getPosition()[1] += yPos;
                        enemyStuckCounter[i][0]=0;
                    }else{
                        enemyStuckCounter[i][1]++;
                        if(enemyStuckCounter[i][1]> Constants.frameRate*5){
                            enemies[i].getPosition()[1] += yPos;
                        }
                    }





            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void setHero(Character enemy) {
        hero=characterCreator.getHero(0);
    }
}
