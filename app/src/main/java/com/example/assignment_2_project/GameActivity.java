package com.example.assignment_2_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private ImageView imageViewCelebrity;
    private Button buttonNext, buttonBack;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    private ArrayList<Button> buttons;
    private final int[] resourceFiles = {R.drawable.cosby, R.drawable.tyson, R.drawable.kidrock, R.drawable.rdj, R.drawable.charliesheen};
    private int correctAnswer = 0; // Index of the correct answer
    private int incorrectAnswer = 0; // Index of the incorrect answer
    // To use lambda expression, the variable must be final so created an array to store the value which can be changed
    private final int[] round = {0}; // Index of the current round
    private final String[][] ButtonTextSaved = new String[5][4];

    // 2D array of guesses for each round
    private final String[][] guesses = {
            {"Bill Cosby", "Bill Nye", "Bill Gates", "Bill Clinton"},
            {"Mike Tyson", "Bill Murray", "Billie Eilish", "Billie Joe Armstrong"},
            {"Kid Rock", "Billie Holiday", "Billy Joel", "Billy Ray Cyrus"},
            {"Robert Downey Jr.", "Billy Idol", "Billy Bob Thornton", "Billy Crystal"},
            {"Charlie Sheen", "Billy Dee Williams", "Billy Zane", "Billy Corgan"}
    };

    private static final String[] correctGuesses = {
            "Bill Cosby",
            "Mike Tyson",
            "Kid Rock",
            "Robert Downey Jr.",
            "Charlie Sheen"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d("GameActivity", "Setting up buttons for round " + round[0]);

        setHeaderColor();
        setFields();
        createButtonArray();

        // Restore the state if available if not call these in the on restore instance state
        if (savedInstanceState == null) {
            setupButtonsTxtNotOnRotate();
            commonSetUp();
            setUpNextButton();
            setUpBackButton();
        }

    }

    // testing
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the text of each button
        for (int i = 0; i < buttons.size(); i++) {
            outState.putString("buttonText" + i, buttons.get(i).getText().toString());
        }
        // Save correct and incorrect answers
        outState.putInt("correctAnswer", correctAnswer);
        outState.putInt("incorrectAnswer", incorrectAnswer);
        // Save the round
        outState.putInt("round", round[0]);
        // Save the state ButtonTextSaved
        for (int i = 0; i < ButtonTextSaved.length; i++) {
            outState.putStringArray("ButtonTextSaved" + i, ButtonTextSaved[i]);
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
            // Restore correct and incorrect answers
            correctAnswer = savedInstanceState.getInt("correctAnswer");
            incorrectAnswer = savedInstanceState.getInt("incorrectAnswer");
            // Restore the round
            round[0] = savedInstanceState.getInt("round");
            // Restore the state ButtonTextSaved
            for (int i = 0; i < ButtonTextSaved.length; i++) {
                ButtonTextSaved[i] = savedInstanceState.getStringArray("ButtonTextSaved" + i);
            }

            setUpNextButton();
            setUpBackButton();
            commonSetUp();

        }
    }

    // Change status bar color
    public void setHeaderColor() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int statusBarColor = ContextCompat.getColor(this, R.color.gray);
        window.setStatusBarColor(statusBarColor);
    }

    public void setFields() {
        imageViewCelebrity = findViewById(R.id.imageView_Celebrity);
        buttonAnswer1 = findViewById(R.id.button_Answer1);
        buttonAnswer2 = findViewById(R.id.button_Answer2);
        buttonAnswer3 = findViewById(R.id.button_Answer3);
        buttonAnswer4 = findViewById(R.id.button_Answer4);
        buttonNext = findViewById(R.id.button_Next);
        buttonBack = findViewById(R.id.button_Back);
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
            if (button.getText().toString().equals("Correct!")) {
                button.setBackgroundColor(getResources().getColor(R.color.green, null));
                // Turn off on click listener for the correct button
                button.setOnClickListener(null);
            } else if (button.getText().toString().equals("Incorrect")) {
                button.setBackgroundColor(getResources().getColor(R.color.red, null));
                // Turn off on click listener for the correct button
                button.setOnClickListener(null);
            } else
                button.setBackgroundColor(getResources().getColor(R.color.darkGray, null));
        }
    }

    public void setOnClicks() {
        // Set up click listeners for each button
        for (Button button : buttons) {
            button.setOnClickListener(view -> {
                if (button.getText().toString().equals(correctGuesses[round[0]])) {
                    // This is the correct answer
                    button.setBackgroundColor(getResources().getColor(R.color.green, null));
                    button.setText(R.string.correct);
                    correctAnswer++;
                    Log.d("correctAnswer", "Correct answer: " + correctAnswer);
                    // Turn off on click listener for the correct button
                    Toast.makeText(GameActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    button.setOnClickListener(null);
                } else {
                    // This is not the correct answer
                    button.setBackgroundColor(getResources().getColor(R.color.red, null));
                    button.setText(R.string.incorrect);
                    incorrectAnswer++;
                    Log.d("incorrectAnswer", "Incorrect answer: " + incorrectAnswer);
                    Toast.makeText(GameActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
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
        // Shuffle the names within each row
        for (String[] row : guesses) {
            List<String> names = Arrays.asList(row);
            Collections.shuffle(names);
            //row = names.toArray(new String[0]);
        }
        // Loop through the buttons and set the text
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(guesses[round[0]][i]);
        }
        //saveButtonText();
    }

    public void setUpNextButton() {
        buttonNext.setOnClickListener(view -> {
            saveButtonText();
            if (round[0] == 4) {
                Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                intent.putExtra("correct", correctAnswer);
                intent.putExtra("incorrect", incorrectAnswer);
                startActivity(intent);
                finish();
                return;
            }
            round[0]++;
            Log.d("GameActivity", "Setting up buttons for round " + round[0]);
            if (ButtonTextSaved[round[0]][0] == null) {
                setupButtonsTxtNotOnRotate();
            } else {
                restoreButtonText();
            }

            if (round[0] == 4)
                buttonNext.setText(R.string.finish);
            // Set the text of the back button
            if (round[0] > 0)
                buttonBack.setText(R.string.back);
            commonSetUp();
        });
    }

    public void setUpBackButton() {
        buttonBack.setOnClickListener(view -> {
            saveButtonText();
            if (round[0] == 0) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            } else {
                round[0]--;
                Log.d("GameActivity", "Setting up buttons for round " + round[0]);
                if (ButtonTextSaved[round[0]][0] == null) {
                    setupButtonsTxtNotOnRotate();
                } else {
                    restoreButtonText();
                }
                commonSetUp();
                // Set the text of the next button
                if (round[0] < 4)
                    buttonNext.setText(R.string.next);
                // Set the text of the back button
                if (round[0] == 0)
                    buttonBack.setText(R.string.backToMenu);
            }
        });
    }

    private void commonSetUp() {
        setImageViewCelebrity();
        setOnClicks();
        setButtonColor();
    }

    // Save the state of the game
    public void saveButtonText() {
        for (int i = 0; i < buttons.size(); i++) {
            ButtonTextSaved[round[0]][i] = buttons.get(i).getText().toString();
        }
    }

    // Restore the state of the game
    public void restoreButtonText() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(ButtonTextSaved[round[0]][i]);
        }
    }

}
