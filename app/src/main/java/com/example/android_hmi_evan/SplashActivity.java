package com.example.android_hmi_evan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    public final static String TAG = "SplashActivity";
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hides ActionBar
        this.getSupportActionBar().hide();

        // Hides system UI
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Layout file to use
        setContentView(R.layout.activity_splash);

        // Progress bar drawable
        Drawable drawable = getResources().getDrawable(R.drawable.circularprogressbar);

        // Progress bar in layout
        progressBar = findViewById(R.id.circularProgressbar);
        progressBar.setProgress(0);
        progressBar.setSecondaryProgress(100);
        progressBar.setMax(100);
        progressBar.setProgressDrawable(drawable);

        // Thread for loading bar and moving to next Activity
        Thread progressThread = new Thread(() -> {
            makeProgress();
            startApp();
            finish();
        });

        // Actually start the thread
        progressThread.start();
    }

    // Makes the ProgressBar fill up
    private void makeProgress() {
        for (int progress = 0; progress <= 100; progress++) {
            try {
                Thread.sleep(30);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Move to next Activity
    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }
}