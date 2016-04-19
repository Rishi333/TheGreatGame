package Foundation;

/**
 * Created by rishi on 4/18/16.
 */
public class Event {

    public int eventCode;
    public int numberParams[];
    public String stringParams[];

    public Event(int eventCode, int[] numberParams, String[] stringParams){
        this.eventCode=eventCode;
        this.numberParams=numberParams;
        this.stringParams=stringParams;
    }
}
