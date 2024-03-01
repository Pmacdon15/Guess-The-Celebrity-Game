package com.example.assignment_2_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    private ImageView imageViewCelebrity;
    private Button buttonNext;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    private ArrayList<Button> buttons;
    private final int[] resourceFiles = {R.drawable.cosby, R.drawable.tyson, R.drawable.kidrock, R.drawable.rdj, R.drawable.charliesheen};
    private static int correctAnswer = 0; // Index of the correct answer
    private static int incorrectAnswer = 0; // Index of the incorrect answer
    // To use lambda expression, the variable must be final so created an array to store the value which can be changed
    private static final int[] round = {0}; // Index of the current round

    // 2D array of guesses for each round
    // First Name in each row is the correct answer
    private final String[][] guesses = {
            {"Bill Cosby", "Bill Nye", "Bill Gates", "Bill Clinton"},
            {"Mike Tyson", "Bill Murray", "Billie Eilish", "Billie Joe Armstrong"},
            {"Kid Rock", "Billie Holiday", "Billy Joel", "Billy Ray Cyrus"},
            {"Robert Downey Jr.", "Billy Idol", "Billy Bob Thornton", "Billy Crystal"},
            {"Charlie Sheen", "Billy Dee Williams", "Billy Zane", "Billy Corgan"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d("GameActivity", "Setting up buttons for round " + round[0]);

        setHeaderColor();
        setFields();
        createButtonArray();
        setButtonColor();
        setOnClicks();
        setNextButton();
        setNextButtonIfLastRound();
        setImageViewCelebrity();

        // Restore the state if available
        if (savedInstanceState == null) setupButtonsTxtNotOnRotate();

    }

    // testing
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the text of each button
        for (int i = 0; i < buttons.size(); i++) {
            outState.putString("buttonText" + i, buttons.get(i).getText().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state only if buttons ArrayList is initialized
        if (buttons != null) {
            // Restore the text of each button
            for (int i = 0; i < buttons.size(); i++) {
                String buttonText = savedInstanceState.getString("buttonText" + i);
                buttons.get(i).setText(buttonText);
            }
            setNextButtonIfLastRound();
            setImageViewCelebrity();
            setButtonColor();
        }
    }
    // testing

    // Change status bar color
    public void setHeaderColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#1984BF"));
    }

    public void setFields() {
        imageViewCelebrity = findViewById(R.id.imageView_Celebrity);
        buttonAnswer1 = findViewById(R.id.button_Answer1);
        buttonAnswer2 = findViewById(R.id.button_Answer2);
        buttonAnswer3 = findViewById(R.id.button_Answer3);
        buttonAnswer4 = findViewById(R.id.button_Answer4);
        buttonNext = findViewById(R.id.button_Next);
    }

    public void createButtonArray() {
        // Initialize the buttons ArrayList
        buttons = new ArrayList<>();
        buttons.add(buttonAnswer1);
        buttons.add(buttonAnswer2);
        buttons.add(buttonAnswer3);
        buttons.add(buttonAnswer4);
    }

    public void setButtonColor() {
        for (Button button : buttons) {
            Log.d("GameActivity", "Setting up button " + button.getText().toString());
            if (button.getText().toString().equals("Correct!"))
                button.setBackgroundColor(getResources().getColor(R.color.green, null));
            else if (button.getText().toString().equals("Incorrect"))
                button.setBackgroundColor(getResources().getColor(R.color.red, null));
            else
                button.setBackgroundColor(getResources().getColor(R.color.blue, null));
        }
    }

    public void setOnClicks() {
        // Set up click listeners for each button
        for (Button button : buttons) {
            button.setOnClickListener(view -> {
                if (button.getText().toString().equals(guesses[round[0]][0])) {
                    // This is the correct answer
                    button.setBackgroundColor(getResources().getColor(R.color.green, null));
                    button.setText(R.string.correct);
                    correctAnswer++;
                    Log.d("correctAnswer", "Correct answer: " + correctAnswer);
                } else {
                    // This is not the correct answer
                    button.setBackgroundColor(getResources().getColor(R.color.red, null));
                    button.setText(R.string.incorrect);
                    incorrectAnswer++;
                    Log.d("incorrectAnswer", "Incorrect answer: " + incorrectAnswer);
                    // Turn off on click listener for the incorrect button
                    button.setOnClickListener(null);
                }
            });
        }
    }

    public void setImageViewCelebrity() {
        // Set the image of the celebrity
        imageViewCelebrity.setImageResource(resourceFiles[round[0]]);
    }

    private void setupButtonsTxtNotOnRotate() {
        // Shuffling the buttons
        Collections.shuffle(buttons);
        // Loop through the buttons and set the text
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(guesses[round[0]][i]);
        }
    }

    public void setNextButton() {
        // Set up the next button
        buttonNext.setOnClickListener(view -> {
            // Increment the round
            round[0]++;
            Log.d("GameActivity", "Setting up buttons for round " + round[0]);
            // Set up the buttons for the next round
            setupButtonsTxtNotOnRotate();
            setNextButtonIfLastRound();
            setImageViewCelebrity();
            setButtonColor();
        });
    }

    public void setNextButtonIfLastRound() {
        // Change the text of the next button to "Finish" if it's the last round
        if (round[0] == 4) {
            buttonNext.setText(R.string.finish);
            buttonNext.setOnClickListener(view -> {
                Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                intent.putExtra("correct", correctAnswer);
                intent.putExtra("incorrect", incorrectAnswer);
                startActivity(intent);
            });
        }
    }

    public static void resetGame() {
        round[0] = 0;
        correctAnswer = 0;
        incorrectAnswer = 0;
    }

}
