package com.example.whack_a_mole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LoserScreen extends AppCompatActivity {
    /**
     * In this page the highscore and current score are retrieved. A toast will show a thanks for playing.
     * The scores will be added to the text view and display the scores
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loser_screen);

        Intent intent = getIntent();
        String highScore = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        String score = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Context context = getApplicationContext();
        CharSequence text = "Thanks for Playing!!!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();



        TextView textView = findViewById(R.id.textView2);
        TextView textView1 = findViewById(R.id.textView3);
        TextView gameOver = findViewById(R.id.textView);

        String endGame = "Game Over!!!";
        String message = "Your score was: " + score;
        String message2 = "High score is: " + highScore;
        textView.setText(message);
        textView1.setText(message2);
        gameOver.setText(endGame);

    }
}