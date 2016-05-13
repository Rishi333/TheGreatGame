package Processes;


import Foundation.Hero;
import ManagersandCreators.CharacterCreator;
import CoreConstants.Constants;
import Foundation.Maps;

import java.util.Random;

/**
 * Created by rishi on 3/8/16.
 */
public class Physics implements Runnable {

    protected int[] heroPos;
    protected Character[] enemies;
    //private final ArrayList<ArrayList<Integer>> firePos;
    protected boolean alive=true;
    protected int[][] fireBall;
    protected int[][] map=Maps.level1;
    protected AIPositionTracker aiPositionTracker;
    protected Random rand= new Random(2);


    public Physics(CharacterCreator characterCreator, AIPositionTracker aitracker, Hero hero){

        this.heroPos=hero.getPosition();
        this.enemies=characterCreator.getEnemies();
        //this.firePos=firePos;
        this.fireBall=hero.getFirePos();
        this.aiPositionTracker=aitracker;

    }

    public void disable(){
        alive=false;
    }
    public int square(int square){
        return square*square;
    }


    @Override
    public void run() {
        while(alive){

            //Enemy Damage logic
            for(int i=0;i<enemies.length;i++){
                //Correct Radius
                if(square(heroPos[0]-enemies[i].getPosition()[0])+square(heroPos[1]-enemies[i].getPosition()[1])<square(24+2)){
                    System.out.println("Enemy Hit You "+ i);
                    heroPos[0]=100;
                    heroPos[1]=100;
                    if(Constants.score>Constants.topScore) {
                        Constants.topScore = Constants.score;
                    }
                    Constants.score=0;

                }
                int size=fireBall.length;
                // Check Enemy hit by Fireball
                for(int j=0;j<size;j++){
                    if(square(fireBall[j][0]-enemies[i].getPosition()[0])+square(fireBall[j][1]-enemies[i].getPosition()[1])<square(24+16)){
                        System.out.println("You Hit Enemy "+ i);
                        int x=0;
                        int y=0;
                        do {
                            x = rand.nextInt(Constants.TileSize*Constants.currentlevel.length);
                            y = rand.nextInt(Constants.TileSize* Constants.currentlevel.length);
                        }while (Maps.Location(x,y)==1);
                        //if(){
                            enemies[i].getPosition()[0]=x;
                            enemies[i].getPosition()[1]=y;
                            Constants.score++;
                        //}else{
                         //   i--;// redo the random generation if bad spot
                        //}
                    }

                }


            }
            //Check Background for each enemy and player
            for(int i=0; i<enemies.length;i++){
                if(enemies[i].getPosition()[0]>0&&enemies[i].getPosition()[1]>0) {
                    if (map[enemies[i].getPosition()[0] / Constants.TileSize][enemies[i].getPosition()[1] / Constants.TileSize] == 1) {
                        //System.out.println("Enemy collision detected " + i);
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

    public void setMap(int[][] map) {
        this.map = map;
    }

}
