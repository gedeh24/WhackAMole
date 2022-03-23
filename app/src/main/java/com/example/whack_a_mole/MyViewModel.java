package com.example.whack_a_mole;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    /**
     * The current time for every iteration before moles appear again
     */
    private MutableLiveData<Long> currentTime;
    /**
     * Score
     */
    private MutableLiveData<Long> scoreSheet;
    /**
     * Long value that holds time
     */
    private Long time;
    /**
     * Long value that holds ths score
     */
    private Long score;

    /**
     * Initializes all of the variables
     */
    public MyViewModel(){

        if(currentTime == null){
            currentTime = new MutableLiveData<>();
            scoreSheet = new MutableLiveData<>();
            time = (long) 5030;
            score = (long) 0;
            scoreSheet.setValue(score);
            currentTime.setValue((long) 0);

        }
    }

    /**
     *
     * @return the amount of time before moles respawn. Subtracts 150ms from time
     */
    public Long getTime(){
        time = time -150;
        return time;
    }

    /**
     * Will return the score sheet
     * @returns the score sheet
     */
    public MutableLiveData<Long> getScoreSheet(){
        return scoreSheet;
    }

}
