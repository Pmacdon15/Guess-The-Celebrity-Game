package com.example.assignment_2_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.parseColor("#1984BF"));
        int statusBarColor = ContextCompat.getColor(this, R.color.gray);
        window.setStatusBarColor(statusBarColor);



        Button buttonStartGame = findViewById(R.id.button_StartGame);
        buttonStartGame.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            // app works better if we don't finish the main activity until the game is over
            finish();
            startActivity(intent);
        });

        Button buttonAboutGame = findViewById(R.id.button_AboutGame);
        buttonAboutGame.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//            startActivity(intent);

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialoguewindow);
            TextView textViewInfo = dialog.findViewById(R.id.textViewInfo);
            dialog.show();
        });
    }
}