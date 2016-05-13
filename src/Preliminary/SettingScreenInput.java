package Preliminary;

import com.jogamp.newt.event.KeyEvent;

import static CoreConstants.Constants.kbState;

/**
 * Created by rishi on 5/9/16.
 */
public class SettingScreenInput {

    private SettingScreen settingScreen;
    private Setup setup;

    public long time= System.currentTimeMillis();

    public SettingScreenInput(SettingScreen settingScreen, Setup setup){
        this.settingScreen = settingScreen;
        this.setup=setup;



    }
    public void checkInput(){
       if(System.currentTimeMillis()-time>100) {
           if (kbState[KeyEvent.VK_D] && setup.last != settingScreen.selectedIndex) {
               setup.next(settingScreen.selectedIndex);
               time = System.currentTimeMillis();
           } else if (kbState[KeyEvent.VK_A] && setup.last != settingScreen.selectedIndex) {
               setup.previous(settingScreen.selectedIndex);
               time = System.currentTimeMillis();
           } else if (kbState[KeyEvent.VK_W]) {
               if (settingScreen.selectedIndex == 0) {
                   settingScreen.selectedIndex = setup.objects.length;
               } else {
                   settingScreen.selectedIndex--;
               }
               time = System.currentTimeMillis();
           } else if (kbState[KeyEvent.VK_S]) {
               if (settingScreen.selectedIndex == setup.objects.length) {
                   settingScreen.selectedIndex = 0;
               } else {
                   settingScreen.selectedIndex++;
               }
               time = System.currentTimeMillis();

           } else if (kbState[KeyEvent.VK_B]) {
               if (settingScreen.selectedIndex == setup.last) {
                   setup.setConstants();
               } else {
                   //setup.next(settingScreen.selectedIndex);
               }
               time = System.currentTimeMillis();
           }
       }



    }
}
