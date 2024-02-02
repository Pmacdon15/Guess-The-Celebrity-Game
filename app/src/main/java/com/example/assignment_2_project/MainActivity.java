package com.example.assignment_2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonStartGame;
    private Button buttonAboutGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartGame = findViewById(R.id.button_StartGame);
        buttonStartGame.setOnClickListener(view -> {
            setContentView(R.layout.activity_game);
        });

        buttonAboutGame = findViewById(R.id.button_AboutGame);
        buttonAboutGame.setOnClickListener(view -> {
            setContentView(R.layout.activity_about);
        });


    }
}