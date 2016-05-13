package TwoPlayer;

import Foundation.Camera;
import ManagersandCreators.CharacterCreator;
import CoreConstants.Constants;
import CoreConstants.GlLibrary;
import Processes.OpenGlLogic;

/**
 * Created by rishi on 4/15/16.
 */
public class OpenGlLogicTwoPlayer extends OpenGlLogic {

    protected CharacterCreator characterCreator;

    public OpenGlLogicTwoPlayer(Camera camera, final boolean[] kbState, CharacterCreator characterCreator, int[] spriteSize){
        super( camera, kbState, characterCreator, spriteSize);
        this.characterCreator=characterCreator;
    }
    @Override
    public void glDrawSprite(int x, int y, int w, int h, int frame,int[] sprite){
        //optimization

            if (x - camera.getX() < windowWidth/2 && x - camera.getX() > 0 && y - camera.getY() < windowHeight && y > -10) {
                GlLibrary.glDraw(sprite[frame], x - camera.getX(), y - camera.getY(), w, h, gl);
            }
            Camera camera= Constants.camera[1];
         //   x= characterCreator.getHero(1).getPosition()[0];
         //   y= characterCreator.getHero(1).getPosition()[1];

            if (x - camera.getX() < windowWidth/2 && x - camera.getX() > 0 && y - camera.getY() < windowHeight && y > -10) {
                GlLibrary.glDraw(sprite[frame], x - camera.getX()+windowWidth/2, y - camera.getY(), w, h, gl);
            }//+(Constants.windowWidth/2 + 1)



    }
    @Override
    public void drawBackground(){


        for (int i = 0; i < tileHeight + 2; i++) {
            for (int j = 0; j < tileWidth/2 + 2; j++) {
                GlLibrary.glDraw(background[currentLevel[i + camera.getY() / Constants.TileSize][j + camera.getX() / Constants.TileSize]], j * 32 - camera.getX() % 32, i * 32 - camera.getY() % 32, 32, 32, gl);
            }
        }
        Camera camera=Constants.camera[1];
        for (int i = 0; i < tileHeight + 2; i++) {
            for (int j = 0; j < tileWidth/2 + 2; j++) {
                GlLibrary.glDraw(background[currentLevel[i + camera.getY() / 32][j + camera.getX() / 32]], (j+tileWidth/2 + 1) * 32 - camera.getX() % 32, i * 32 - camera.getY() % 32, 32, 32, gl);
            }
        }

    }
}
