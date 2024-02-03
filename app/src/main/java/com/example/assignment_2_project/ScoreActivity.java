package com.example.assignment_2_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.color.utilities.Score;

public class ScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get the score from the intent
        Intent intentValue = getIntent();
        int correct = intentValue.getIntExtra("correct", 0);
        int incorrect = intentValue.getIntExtra("incorrect", 0);
        int score = correct - incorrect;

        // Set the score message
        String scoreMessage = getString(R.string.score_message, correct, incorrect, score);
        TextView textViewScore = findViewById(R.id.textView_Score);
        textViewScore.setText(scoreMessage);


        Button buttonMenu = findViewById(R.id.button_Menu);
        buttonMenu.setOnClickListener(view -> {
            Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
