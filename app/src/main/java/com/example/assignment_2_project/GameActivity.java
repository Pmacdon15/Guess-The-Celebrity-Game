package com.example.assignment_2_project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private ImageView imageViewCelebrity;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonAnswer4;
    private Button buttonNext;
    private Button[] buttons;
    private final String[] correctGuesses = {"Bill Cosby", "Mike Tyson", "Kid Rock", "Robert Downey Jr.","Charlie Sheen"};
    private final int[] resourceFiles = {R.drawable.cosby, R.drawable.tyson, R.drawable.kidrock, R.drawable.rdj, R.drawable.charliesheen};

    // 2D array of incorrect guesses for each round
    private final String[][] incorrectGuesses = {
            {"Bill Nye", "Bill Gates", "Bill Clinton"},
            {"Bill Murray", "Billie Eilish", "Billie Joe Armstrong"},
            {"Billie Holiday", "Billy Joel", "Billy Ray Cyrus"},
            {"Billy Idol", "Billy Bob Thornton", "Billy Crystal"},
            {"Billy Dee Williams", "Billy Zane", "Billy Corgan"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageViewCelebrity = findViewById(R.id.imageView_Celebrity);
        buttonAnswer1 = findViewById(R.id.button_Answer1);
        buttonAnswer2 = findViewById(R.id.button_Answer2);
        buttonAnswer3 = findViewById(R.id.button_Answer3);
        buttonAnswer4 = findViewById(R.id.button_Answer4);
        buttonNext = findViewById(R.id.button_Next);

        // Initialize the buttons array after setContentView()
        buttons = new Button[]{buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4};

        setupButtons(0);

        int[] round = {0}; // Initialize with an array containing a single element
        buttonNext.setOnClickListener(view -> {
            Log.d("GameActivity", "Next button clicked");
            round[0]++;
            setupButtons(round[0]);
        });
    }


    private void setupButtons(int round) {

        if (round == 4) {
            buttonNext.setText(R.string.finish);
        }

        imageViewCelebrity.setImageResource(resourceFiles[round]);
        buttonAnswer1.setText(correctGuesses[round]);
        buttonAnswer2.setText(incorrectGuesses[round][0]);
        buttonAnswer3.setText(incorrectGuesses[round][1]);
        buttonAnswer4.setText(incorrectGuesses[round][2]);

        buttonAnswer1.setBackgroundColor(getResources().getColor(R.color.blue, null));
        buttonAnswer2.setBackgroundColor(getResources().getColor(R.color.blue, null));
        buttonAnswer3.setBackgroundColor(getResources().getColor(R.color.blue, null));
        buttonAnswer4.setBackgroundColor(getResources().getColor(R.color.blue, null));

        // Set up click listeners for each button
        for (Button button : buttons) {
            button.setOnClickListener(view -> {
                if (button.getText().toString().equals(correctGuesses[round])) {
                    // This is the correct answer
                    button.setBackgroundColor(getResources().getColor(R.color.green, null));
                    button.setText(R.string.correct);
                } else {
                    // This is not the correct answer
                    button.setBackgroundColor(getResources().getColor(R.color.red, null));
                    button.setText(R.string.incorrect);
                }
            });
        }

    }

}
