package com.collo.youthapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 seconds

    private TextView connectionStatusText;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        connectionStatusText = findViewById(R.id.connection_status_text);
        refreshButton = findViewById(R.id.refresh_button);

        // Check internet connectivity
        if (ConnectivityHelper.isNetworkAvailable(this)) {
            // Internet connection available, proceed to MainActivity
            openMainActivityWithDelay();
        } else {
            // No internet connection
            showNoConnectionUI();
        }

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check internet connectivity on refresh button click
                if (ConnectivityHelper.isNetworkAvailable(SplashScreenActivity.this)) {
                    // Internet connection available, proceed to MainActivity
                    openMainActivityWithDelay();
                } else {
                    // No internet connection, show UI
                    //Toast.makeText(SplashScreenActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    showNoConnectionUI();
                }
            }
        });
    }

    private void openMainActivityWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }

    private void refreshAfterConnection() {
        if (ConnectivityHelper.isNetworkAvailable(this)) {
            openMainActivityWithDelay();
        } else {
            showNoConnectionUI();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshAfterConnection();
    }


    private void showNoConnectionUI() {
        connectionStatusText.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.VISIBLE);
    }
}
