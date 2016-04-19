package ManagersandCreators;

import CoreConstants.Constants;
import Foundation.Event;
import CoreConstants.EventCode;

import java.util.ArrayList;

/**
 * Created by rishi on 4/18/16.
 */
public class EventHandler implements Runnable {


    public ArrayList<Event> events= new ArrayList<Event>();
    public boolean active=true;


    public void addEvent(Event event){
        events.add(event);
    }

    public void handleEvent(Event event){
        if(event.eventCode==EventCode.AUDIO){
            Constants.audioManager.addEvent(event);
        }else if(event.eventCode==EventCode.MESSAGE){

        }

    }

    @Override
    public void run() {
        while(active){
            if(events.size()>0){
                handleEvent(events.get(0));
                events.remove(0);
            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
