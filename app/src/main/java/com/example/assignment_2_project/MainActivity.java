package com.example.assignment_2_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStartGame = findViewById(R.id.button_StartGame);
        buttonStartGame.setOnClickListener(view -> setContentView(R.layout.activity_game));

        Button buttonAboutGame = findViewById(R.id.button_AboutGame);
        buttonAboutGame.setOnClickListener(view -> setContentView(R.layout.activity_about));


    }
}