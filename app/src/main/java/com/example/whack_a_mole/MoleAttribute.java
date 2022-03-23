package com.example.whack_a_mole;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that shows attributes of a Mole
 */
public class MoleAttribute {
    /**
     * Everymole has an image
     */
  public ImageView image;
    /**
     * Every mole should know whether it is allowed to go or not
     */
  private boolean should_go;
    /**
     * Every mole has its own number
     */
  private  int mole_number;

    /**
     * Initializes every mole attribute
     * @param image_1 image of the mole
     */
    public MoleAttribute(ImageView image_1){
      this.image = image_1;
      should_go = false;
        mole_number = -9999;
    }
//can mole go or not

    /**
     * Randomization is used to determine if the mole can go or not
     */
    public void moleGo(){
        Random random = new Random();
        should_go = random.nextBoolean();
    }
    //make simpler to just go or disappear.. and move to create

    /**
     * Sets the hole the mole is assigned to if it is allowed to move
     * @param num
     */
    public void setMole(int num){
        if(getDirection() == true) {
            Random rand = new Random();
            mole_number = num;
        }
    }

    /**
     * Gets the mole number
     * @return returns the mole number
     */
    public int getMole(){
        return mole_number; //now you can access mole number //on create you set and get and add to array tbh then worry about go
    }

    /**
     * boolean value is returned if the mole can go or not
     * @return true or false
     */
    public boolean getDirection(){
        return should_go;
    }

    /**
     * Method that sets the should_go to false, so when a user clicks on a hole that a mole was assigned to they will not
     * gain any points.
     */
    public void clear(){
        should_go =false;
    }




}
