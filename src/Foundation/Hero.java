package Foundation;


import Foundation.Character;

/**
 * Created by rishi on 3/9/16.
 */
public class Hero extends Character {


    //Foundation.Hero Power
    private int[] fireBall=new int[2];
    private int[][] firePos=new int[100][4];
    //Hero Key Set
    public int up;
    public int down;
    public int right;
    public int left;
    public int attack;
    public int attack2;



    public Hero(int xLocation, int yLocation, int frameCount, int up, int down, int right, int left, int attack,int attack2){
        super(xLocation,yLocation,frameCount);
        this.up=up;
        this.down=down;
        this.right=right;
        this.left=left;
        this.attack=attack;
        this.attack2=attack2;

    }
    public int[] getFireBall() {
        return fireBall;
    }


    public int[][] getFirePos() {
        return firePos;
    }







}
