package TwoPlayer;

import Processes.Character;
import Foundation.Hero;
import ManagersandCreators.CharacterCreator;
import Processes.EnemyLogic;
import Processes.OpenGlLogic;

/**
 * Created by rishi on 4/16/16.
 */
public class EnemyLogicTwoPlayer extends EnemyLogic {
    public EnemyLogicTwoPlayer(CharacterCreator characterCreator, OpenGlLogic openGlLogic, Hero hero) {
        super(characterCreator, openGlLogic, hero);
    }

    @Override
    public void drawEnemies(){
        for (int i = 0; i < enemies.length; i++) {
            openGlLogic.glDrawSprite(enemies[i].getPosition()[0], enemies[i].getPosition()[1], 48, 48, 0, enemies[i].getSprite());
        }for (int i = 0; i < enemies.length; i++) {
            openGlLogic.glDrawSprite(enemies[i].getPosition()[0], enemies[i].getPosition()[1], 48, 48, 0, enemies[i].getSprite());
        }
    }

    @Override
    protected void setHero(Character enemy){
        if(square(characterCreator.getHero(0).getPosition()[0]-enemy.getPosition()[0]) +(characterCreator.getHero(0).getPosition()[1]-enemy.getPosition()[1])<
                square(characterCreator.getHero(1).getPosition()[0]-enemy.getPosition()[0]) +(characterCreator.getHero(1).getPosition()[1]-enemy.getPosition()[1])){
            hero=characterCreator.getHero(0);
        }else {//hero 2 is closer
            hero=characterCreator.getHero(1);
        }


    }
    public int square(int square){
        return square*square;
    }


}
