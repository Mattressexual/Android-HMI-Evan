package com.example.android_hmi_evan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;

public class StartScreenActivity extends AppCompatActivity {

    Button
            standbyButton,
            settingsButton,
            alarmLimitsButton,
            logButton,
            alarmSilenceButton,
            o2Button;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hides ActionBar
        this.getSupportActionBar().hide();

        // Hides system UI
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_start_screen);

        standbyButton = findViewById(R.id.standby_button);
        settingsButton = findViewById(R.id.settings_button);
        alarmLimitsButton = findViewById(R.id.alarm_limits_button);
        logButton = findViewById(R.id.log_button);

        alarmSilenceButton = findViewById(R.id.alarm_silence_button);
        o2Button = findViewById(R.id.oxygen_100_button);
        o2Button.setText(Html.fromHtml("100% O<sub>2</sub>"));


        OnStandbyFragment onStandbyFragment = new OnStandbyFragment();
        Bundle onStandbyBundle = new Bundle();
        String onStandbyTag = "OnStandby";

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(
                        R.id.fragment_container_view,
                        OnStandbyFragment.class,
                        onStandbyBundle,
                        onStandbyTag)
                .addToBackStack(onStandbyTag)
                .commit();
    }
}