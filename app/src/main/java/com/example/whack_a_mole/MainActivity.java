package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;

import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * The main activity for the app
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Key used for intent when new page is loading
     */
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    /**
     * Key used for intent when new page is loading
     */
    public static final String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
    /**
     * ArrayList that keeps track of how many moles are current present. Present meaning they are visible
     */
    private ArrayList<Integer> moles = new ArrayList<>();
    /**
     * View model instance
     */
    private MyViewModel model;
    /**
     * Variable that is the 1st hole
     */
    private ImageButton hole1;
    /**
     * Variable that is the 2nd hole
     */
    private ImageButton hole2;
    /**
     * Variable that is the 3rd hole
     */
    private ImageButton hole3;
    /**
     * Variable that is the 4th hole
     */
    private ImageButton hole4;
    /**
     * Variable that is the 5th hole
     */
    private ImageButton hole5;
    /**
     * Instance of a mole attribute
     */
    private MoleAttribute mole_attribute1;
    /**
     * Instance of a mole attribute
     */
    private MoleAttribute mole_attribute2;
    /**
     * Instance of a mole attribute
     */
    private MoleAttribute mole_attribute3;
    /**
     * Instance of a mole attribute
     */
    private MoleAttribute mole_attribute4;
    /**
     * Instance of a mole attribute
     */
    private MoleAttribute mole_attribute5;
    /**
     * Long value that has score
     */
    private long scored;
    /**
     * Long value of the highest score
     */
    private long highestScore;
    /**
     * Lives that the user has
     */
    private long int_lives;
    /**
     * A mole
     */
    private ImageView mole1;
    /**
     * A mole
     */
    private ImageView mole2;
    /**
     * A mole
     */
    private ImageView mole3;
    /**
     * A mole
     */
    private ImageView mole4;
    /**
     * A mole
     */
    private ImageView mole5;
    /**
     * Start button to begin app
     */
    private Button start;
    /**
     * Handler that continues to loop
     */
    Handler handler = new Handler(Looper.getMainLooper());
    /**
     * Handler that continues to loop
     */
    Handler handyman = new Handler(Looper.getMainLooper());


    /**
     * In this runnable it continues to update the score. If the game isDone... ie if is done = true,
     * then it will open a new page showing the user score and the high score. If it is false it will post the new score
     */
    Runnable run = new Runnable() {
        @Override
        public void run() {
            if(isDone() == false) {
                model.getScoreSheet().setValue(scored);
                handyman.postDelayed(run,  100);
           }
           else{
               newPage();
               handyman.removeCallbacks(run);
            }


        }
    };

    /**
     * In this runnable if isDone is false and the arrayList is not empty, the lives will be updated. Any mole left in
     * the arrayList will be a deduction life point. Then the array list is cleared. The moles are initially set to disappear
     * It will then go through logic and post the moles that are supposed to be present every x amount of seconds. When
     * the lives are up, all callbacks are removed.
     */

    Runnable runnable = new Runnable(){

        @Override
        public void run() {
           // model.increaseSpeed();
            if(isDone() == false) {
                if (!moles.isEmpty()) {

                        int_lives = int_lives - (moles.size());
                        moles.removeAll(moles);



                }

                moles.removeAll(moles);
                disappear(mole_attribute1);
                disappear(mole_attribute2);
                disappear(mole_attribute3);
                disappear(mole_attribute4);
                disappear(mole_attribute5);
                mole_attribute1.moleGo();
                mole_attribute2.moleGo();
                mole_attribute3.moleGo();
                mole_attribute4.moleGo();
                mole_attribute5.moleGo();

                mole_attribute1.setMole(1);
                if (mole_attribute1.getDirection() == true) {
                    moles.add(mole_attribute1.getMole());
                }
                mole_attribute2.setMole(2);

                if (mole_attribute2.getDirection() == true) {
                    moles.add(mole_attribute2.getMole());
                }

                mole_attribute3.setMole(3);

                if (mole_attribute3.getDirection() == true) {
                    moles.add(mole_attribute3.getMole());
                }

                mole_attribute4.setMole(4);

                if (mole_attribute4.getDirection() == true) {
                    moles.add(mole_attribute4.getMole());
                }

                mole_attribute5.setMole(5);

                if (mole_attribute5.getDirection() == true) {
                    moles.add(mole_attribute5.getMole());
                }


                go(mole_attribute1);
                go(mole_attribute2);
                go(mole_attribute3);
                go(mole_attribute4);


                go(mole_attribute5);

                handler.postDelayed(runnable, model.getTime());
            }
            else{

                handler.removeCallbacks(runnable);
            }

        }
    };

    /**
     * Create method that loads up the app initially. Shared preference is used to save the score
     * of the high score
     * @param savedInstanceState the last instance
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        long highScore = sharedPref.getLong(getString(R.string.HighScore), 0);

        scored = 0;
        highestScore=highScore;
        moles.clear();
        int_lives =  11;


        model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);

        TextView highscore_box = findViewById(R.id.highScore);
        String message = "The high score is: " + highestScore;
        highscore_box.setText(message);
        TextView score = findViewById(R.id.Score);
        TextView lives = findViewById(R.id.Lives);
        hole1 = findViewById(R.id.hole_1);
        hole2 = findViewById(R.id.hole2);
        hole3 = findViewById(R.id.hole3);
        hole4 = findViewById(R.id.hole4);
        hole5 = findViewById(R.id.hole5);
        start = findViewById(R.id.start);
        mole1 = findViewById(R.id.imageView);
        mole2 = findViewById(R.id.imageView2);
        mole3 = findViewById(R.id.imageView3);
        mole4 = findViewById(R.id.imageView4);
        mole5 = findViewById(R.id.imageView5);

        mole1.setVisibility(View.INVISIBLE);
        mole2.setVisibility(View.INVISIBLE);
        mole3.setVisibility(View.INVISIBLE);
        mole4.setVisibility(View.INVISIBLE);
        mole5.setVisibility(View.INVISIBLE);
        mole1.invalidate();

        mole_attribute1 = new MoleAttribute(mole1);
        mole_attribute2 = new MoleAttribute(mole2);
        mole_attribute3 = new MoleAttribute(mole3);
        mole_attribute4 = new MoleAttribute(mole4);
        mole_attribute5 = new MoleAttribute(mole5);


        /**
         * This observer updates the score and the lives accordingly
         */
       final Observer<Long> nameObserver = new Observer<Long>() {
           @Override
           public void onChanged(Long scoreView) {
               String final_score = Long.toString(scored);
               String final_lives = Long.toString(int_lives);
               score.setText(final_score);
               lives.setText(final_lives);
           }
       };

       model.getScoreSheet().observe(this, nameObserver);

        /**
         * start button is pressed it will start the handler and disappears after appearing
         */
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                start.invalidate();
               handler.postAtTime(runnable, 5000);

            }
        });

        /**
         * if a hole is pressed with a mole present the score is updated. The mole disappears
         * and attributes are rmeoved so if clicked again more points will not be added.
         */

        hole1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mole_attribute1.getDirection() == true){
                    scored = scored +1;
                    disappear(mole_attribute1);
                    mole_attribute1.clear();
                    remove();
                    handler.postAtTime(run,10);
                }


            }
        });

        /**
         * if a hole is pressed with a mole present the score is updated. The mole disappears
         * and attributes are rmeoved so if clicked again more points will not be added.
         */
        hole2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mole_attribute2.getDirection() == true){
                    scored = scored +1;
                    disappear(mole_attribute2);
                    mole_attribute2.clear();
                    remove();
                    handler.postAtTime(run,10);
                }

            }
        });

        /**
         * if a hole is pressed with a mole present the score is updated. The mole disappears
         * and attributes are rmeoved so if clicked again more points will not be added.
         */
        hole3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mole_attribute3.getDirection() == true){
                    scored++;
                    disappear(mole_attribute3);
                    mole_attribute3.clear();
                    remove();
                    handler.postAtTime(run,10);
                }

            }
        });

        /**
         * if a hole is pressed with a mole present the score is updated. The mole disappears
         * and attributes are rmeoved so if clicked again more points will not be added.
         */
        hole4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mole_attribute4.getDirection() == true){
                    scored++;
                    disappear(mole_attribute4);
                    mole_attribute4.clear();
                    remove();
                    handler.postAtTime(run,10);
                }

            }
        });

        /**
         * if a hole is pressed with a mole present the score is updated. The mole disappears
         * and attributes are rmeoved so if clicked again more points will not be added.
         */

        hole5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mole_attribute5.getDirection() == true){
                    scored++;
                    disappear(mole_attribute5);
                    mole_attribute5.clear();
                    remove();
                    handler.postAtTime(run,10);
                }
            }
        });


    }


    /**
     * Method that removes the mole from the arrayList
     */
    private void remove(){
        moles.remove(0);
    }

    /**
     * Will make the mole disappear
     * @param mole a mole that has an image will be disappeared
     */
    private void disappear(MoleAttribute mole){
        mole.image.setVisibility(View.INVISIBLE);
        mole.image.invalidate();
    }

    /**
     * If the mole is allowed to go then it will become visible at its given hole
     * @param mole  is a given mole with certain attributes
     */
    private void go(MoleAttribute mole){
        //if mole is not going this round then disappear

        if(mole.getDirection() == true){
           mole.image.setVisibility(View.VISIBLE);
           mole.image.invalidate();

        }
      }

    /**
     * Checks to see if there are lives present
     * @return false if there are no more lives. True if there is
     */
    private boolean isDone(){
        if(int_lives<=0){
            return true;
        }
        else{
            return false;
        }


      }

    /**
     * Loads up new page with intent. If the current score is higher than old score this will be updated.
     * In the shared preference, the editor is used to put in the new HighScore and save it. Lastly scores
     * will be put into intent as strings for the next page
     */
     private void newPage(){
        Intent intent = new Intent(this, LoserScreen.class);

        if((long)scored> (long)highestScore){
            highestScore = scored;

        }

         SharedPreferences sharedPref;

         {
             sharedPref = this.getPreferences(Context.MODE_PRIVATE);
             SharedPreferences.Editor editor = sharedPref.edit();
             editor.putLong(getString(R.string.HighScore), highestScore);
             editor.apply();
         }

        String temp = ""+scored;
        String ret = ""+highestScore;

        intent.putExtra(EXTRA_MESSAGE,temp);
        intent.putExtra(EXTRA_MESSAGE1,ret);
        startActivity(intent);
     }











}