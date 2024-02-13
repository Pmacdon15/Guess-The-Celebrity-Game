package com.example.assignment_2_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Change status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#1984BF"));

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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
