package com.example.assignment_2_project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Change status bar color
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.parseColor("#1984BF"));
        int statusBarColor = ContextCompat.getColor(this, R.color.gray);
        window.setStatusBarColor(statusBarColor);

    }
    public void WrapUp(View view) {
        // Close about activity and start the main activity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        this.finish();
    }
}
