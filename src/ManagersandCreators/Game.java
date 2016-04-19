package ManagersandCreators;

import CoreConstants.*;
import Foundation.*;
import Processes.*;
import TwoPlayer.EnemyLogicTwoPlayer;
import TwoPlayer.HeroLogicTwoPlayer;
import TwoPlayer.OpenGlLogicTwoPlayer;

import java.io.IOException;

/**
 * Created by jared on 2/9/16.
 */

public class Game {




    public static void main(String[] args) throws IOException {



        CharacterCreator characterCreator= new CharacterCreator();
        AIPositionTracker aitracker=new AIPositionTracker(characterCreator, characterCreator.getHero(0));
        AIPositionTracker aitracker2= null;
        TimeController timeController=new TimeController();
        OpenGlLogic openGlLogic=null;
        HeroLogic heroLogic=null;
        EnemyLogic enemyLogic=null;
        TimeController timeController1=null;
        if(!Constants.twoPlayer) {
            openGlLogic=new OpenGlLogic(Constants.camera[0], Constants.kbState, characterCreator, Constants.spriteSize);
            heroLogic=new HeroLogic(Constants.camera[0], Constants.kbState, characterCreator.getHero(0),timeController);
            enemyLogic=new EnemyLogic(characterCreator, openGlLogic, characterCreator.getHero(0));


        }else{
            aitracker2=new AIPositionTracker(characterCreator, characterCreator.getHero(1));
            openGlLogic=new OpenGlLogicTwoPlayer(Constants.camera[0], Constants.kbState, characterCreator,Constants.spriteSize);
            heroLogic=new HeroLogicTwoPlayer(Constants.camera[0], Constants.kbState, characterCreator.getHero(0),timeController);
            Constants.camera[1]=new Camera(0,0);
            timeController1= new TimeController();
            HeroLogic hero2Logic= new HeroLogicTwoPlayer(Constants.camera[1],Constants.kbState,characterCreator.getHero(1),timeController1);
            Thread heroLogic2= new Thread(hero2Logic);
            heroLogic2.start();
            Physics physPlayerTwo=new Physics(characterCreator,aitracker2,characterCreator.getHero(1));
            Thread physicsPlayer2= new Thread(physPlayerTwo);
            physicsPlayer2.start();
            enemyLogic=new EnemyLogicTwoPlayer(characterCreator, openGlLogic, characterCreator.getHero(0));
            Thread aithread2= new Thread(aitracker2);
            aithread2.start();
            //add enemies logic
            // check gl logic
        }
        characterCreator.setGl(openGlLogic.getGl());
        characterCreator.loadSprites();


        Physics physics= new Physics(characterCreator, aitracker,characterCreator.getHero(0));



        openGlLogic.loadSprites();

        //Starting Foundation.Camera and Foundation.Hero Logic Thread
        Thread logic=new Thread(heroLogic);
        logic.start();

        //Starting AiTracker Position thread
        Thread positionTracker= new Thread(aitracker);
        positionTracker.start();

        //Event Handler
        Thread eventHandler= new Thread(Constants.eventHandler);
        eventHandler.start();

        //Audio Manger
        Thread audioManager= new Thread(Constants.audioManager);
        audioManager.start();

        //Starting Enemy Thread
        Thread enemies= new Thread(enemyLogic);
        enemies.start();

        //Starting Processes.Physics Thread
        Thread phys= new Thread(physics);
        phys.start();

        //Starting music and testing events
        Constants.eventHandler.addEvent(new Event(EventCode.AUDIO,new int[]{500},new String[]{"file.mp3"}));
        Constants.eventHandler.addEvent(new Event(EventCode.MESSAGE,null,new String[]{"file.mp3"}));

        // The game loop
        while (!Constants.shouldExit) {
            long time= System.currentTimeMillis();
            System.arraycopy(Constants.kbState, 0, Constants.kbPrevState, 0, Constants.kbState.length);


            //Gl Logic Stuff
            openGlLogic.clearScreen();
            openGlLogic.drawBackground();

            //DrawHero 1
            Hero hero=characterCreator.getHero(0);
            openGlLogic.glDrawSprite(hero.getPosition()[0], hero.getPosition()[1], 48, 48,timeController.getCurrentFrame()+timeController.getLeftRightModifier(),hero.getSprite());
            hero.drawBullets(openGlLogic, hero);

            //DrawHero 2
            if(Constants.twoPlayer) {
                Hero hero2 = characterCreator.getHero(1);
                openGlLogic.glDrawSprite(hero2.getPosition()[0], hero2.getPosition()[1], 48, 48, timeController1.getCurrentFrame() + timeController1.getLeftRightModifier(), hero.getSprite());
                hero2.drawBullets(openGlLogic,hero2);
            }
            //Draw Enemies
            enemyLogic.drawEnemies();
            //Draw bullets


            openGlLogic.DisplayScore();

            openGlLogic.presntToPlayer();




            if(heroLogic.CheckExit()||openGlLogic.display()){
                break;
            }

            GlLibrary.checkLag(time, Constants.frameRate);


        }
        heroLogic.setExit(true);
        enemyLogic.disableEnemies();
        physics.disable();

        System.exit(0);
    }





}
