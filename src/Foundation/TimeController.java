package Foundation;

/**
 * Created by rishi on 2/27/16.
 */
public class TimeController {



    private int currentFrame=0;



    private int LeftRightModifier=0;

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setStateRight() {
        setLeftRightModifier(0);
    }

    public void setStateLeft() {
        setLeftRightModifier(6);
    }
    public int getLeftRightModifier() {
        return LeftRightModifier;
    }

    public void setLeftRightModifier(int leftRightModifier) {
        LeftRightModifier = leftRightModifier;
    }
}
