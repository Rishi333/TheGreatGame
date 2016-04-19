package ManagersandCreators;

import Foundation.Hero;
import Foundation.Character;
import CoreConstants.Constants;
import CoreConstants.GlLibrary;
import Foundation.Maps;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.opengl.GL2;


import java.util.Random;

/**
 * Created by rishi on 3/9/16.
 */
public class CharacterCreator {

    private  Hero[] hero=new Hero[2];

    private Character[] enemies= new Character[Constants.enemyCount];


    private GL2 gl;


    public CharacterCreator(){

        hero[0]=new Hero(200, 220,12, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_B,KeyEvent.VK_G);
        this.gl=gl;

        if(Constants.twoPlayer){
            hero[1]=new Hero(200,240,12, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_SLASH,KeyEvent.VK_PERIOD);
        }

        Random rand= new Random(61);
        for(int i=0; i<enemies.length; i++){
            int x=rand.nextInt(Constants.TileSize*Constants.currentlevel.length);
            int y=rand.nextInt(Constants.TileSize*Constants.currentlevel.length);
            if(Maps.Location(x,y)!=1){
                enemies[i]=new Character(x,y,1);
            }else{
                i--;// redo the random generation if bad spot
            }

        }

    }
    public void setGl(GL2 gl) {
        this.gl = gl;
    }

    public void loadSprites(){
        int[] spriteSize= new int[]{48,48};

        for (int i=0;i<enemies.length;i++){
            enemies[i].setSprite(GlLibrary.glTexImageTGAFile(gl, "Media/Mega-Man-transparent.tga", spriteSize)); ;
        }

        int[] spriteTex=hero[0].getSprite();
        spriteSize=new int[]{48,48};
        for(int i=0;i<12;i++) {
            spriteTex[i] = GlLibrary.glTexImageTGAFile(gl, "Media/Mario/Mario000"+i+".tga", spriteSize);
        }
        int[] fireballs=hero[0].getFireBall();
        for(int i=0;i<hero[0].getFireBall().length;i++){
            fireballs[i]=GlLibrary.glTexImageTGAFile(gl, "Media/Mario/FireBall"+i+".tga", spriteSize);
        }
        // setting textures for second hero
        if(Constants.twoPlayer){
            int[] spriteTex2=hero[1].getSprite();
            spriteSize=new int[]{48,48};
            for(int i=0;i<12;i++) {
                spriteTex2[i] = GlLibrary.glTexImageTGAFile(gl, "Media/Mario2/Mario000"+i+".tga", spriteSize);
            }
            int[] fireballs2=hero[1].getFireBall();
            for(int i=0;i<hero[1].getFireBall().length;i++){
                fireballs2[i]=GlLibrary.glTexImageTGAFile(gl, "Media/Mario2/FireBall"+i+".tga", spriteSize);
            }
        }


    }
    public Hero getHero(int i) {
        return hero[i];
    }

    public void setHero(Hero hero, int i) {
        this.hero[i] = hero;
    }

    public Character[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Character[] enemies) {
        this.enemies = enemies;
    }





}
