package com.example.assignment_2_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button buttonMenu = findViewById(R.id.button_Menu);
        buttonMenu.setOnClickListener(view -> {
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
