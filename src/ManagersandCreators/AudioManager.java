package ManagersandCreators;

import Foundation.Audio;
import Foundation.Event;

/**
 * Created by rishi on 4/18/16.
 */
public class AudioManager extends EventHandler {

    @Override
    public void handleEvent(Event event) {
        new Thread(new Audio(event.stringParams[0],event.numberParams[0])).start();
    }
}
