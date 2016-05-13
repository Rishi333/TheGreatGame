package Preliminary;

import CoreConstants.Constants;
import Foundation.*;
import Foundation.Window;
import com.jogamp.nativewindow.util.*;

import java.awt.*;
import java.awt.Point;
import java.util.*;

/**
 * Created by rishi on 5/9/16.
 */
public class Setup {
    private Window window;

    //Array of Iterators that store the Constants
    //will rotate the settings



    public LinkedList<Object>[]  objects= new LinkedList[7];
    public int[] index=new int[8];// same size as Linkedlist
    public int last=objects.length;


    public Setup(Window window){

        this.window=window;
        // Setting up system

        //Setting up window size
        objects[0]=new LinkedList<Object>();
        objects[0].add(new Point(1200,600));
        objects[0].add(new Point(900,700));

        //two player
        objects[1]= new LinkedList<>();
        objects[1].add(true);
        objects[1].add(false);

        //Setting up EnemyCount
        objects[2]= new LinkedList<>();
        objects[2].add(32);
        objects[2].add(16);
        objects[2].add(64);

        // Enemy Speed
        objects[3]= new LinkedList<>();
        objects[3].add(10);
        objects[3].add(5);
        objects[3].add(15);

        //Tile Size
        objects[4]= new LinkedList<>();
        objects[4].add(32);
        objects[4].add(16);
        objects[4].add(64);

        //Level
        objects[5]= new LinkedList<>();
        objects[5].add(1);
        objects[5].add(2);
        //objects[3].add();

        //gravity
        objects[6]= new LinkedList<>();
        objects[6].add(0);
        objects[6].add(10);
        objects[6].add(20);
        objects[6].add(30);
        objects[6].add(40);

    }
    //Type is ex. gravity, level
    public void next(int elementsIndex ){
        if(objects[elementsIndex].size()>index[elementsIndex]+1){
            index[elementsIndex]++;

        }else{// doesn't have next
            index[elementsIndex]=0;


        }

    }
    public void previous(int elementIndex ){
        if(index[elementIndex]>0){
            index[elementIndex]--;
        }else{// doesn't have next
            index[elementIndex]=objects[elementIndex].size()-1;


        }

    }
    public void setConstants(){

        Point point=(Point)objects[0].get(index[0]);
        Constants.windowWidth=point.x;
        Constants.windowHeight=point.y;

        Constants.twoPlayer=(boolean)objects[1].get(index[1]);

        Constants.enemyCount=(int)objects[2].get(index[2]);

        Constants.enemySpeed=(int)objects[3].get(index[3]);

        //Constants.TileSize=(int)objects[4].get(index[4]);// tile size

        int level=(int)objects[5].get(index[5]);// level
        if(level==1) {
            Constants.currentlevel =Maps.level1;
        }else if(level==2){
            Constants.currentlevel =Maps.level2;
        }


        Constants.gravity=(int)objects[6].get(index[6]);//gravity
        if(index[6]>0){
            Constants.gravityState=true;
        }else{
            Constants.gravityState=false;
        }






        window.getWindow().setVisible(false);
        window.getWindow().destroy();

    }


}
