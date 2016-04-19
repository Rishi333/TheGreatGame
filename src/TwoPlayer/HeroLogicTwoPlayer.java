package TwoPlayer;

import Foundation.Camera;
import CoreConstants.Constants;
import Foundation.Hero;
import Foundation.TimeController;
import Processes.HeroLogic;

/**
 * Created by rishi on 4/16/16.
 */
public class HeroLogicTwoPlayer extends HeroLogic {
    public HeroLogicTwoPlayer(Camera camera, boolean[] kbState, Hero hero, TimeController timeController) {
        super(camera, kbState, hero, timeController);
    }
    @Override
    protected void doCameraLogic() {

        //Need to fix to allow not magic constants-> Use variable for pixels
        int speed=5;

        if (HeroPos[0] - 300 < camera.getX() && camera.getX() >= 0) {
            camera.setX(camera.getX() - speed);
        } else if (HeroPos[0] - 340 > camera.getX() && camera.getX() <= Constants.TileSize * Constants.levelSize - (windowWidth/2 + 20)) {
            camera.setX(camera.getX() + speed);
        }
        if (HeroPos[1] - 220 < camera.getY() && camera.getY() >= 0) {
            camera.setY(camera.getY() - speed);
        } else if (HeroPos[1] - 260 > camera.getY() && camera.getY() <= 32 * 40 - (windowHeight + 20)) {
            camera.setY(camera.getY() + speed);
        }


    }

}
