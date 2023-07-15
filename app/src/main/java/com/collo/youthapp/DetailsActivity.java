package com.collo.youthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.theme));
        }

        String selectedSubcounty = getIntent().getStringExtra("selectedSubcounty");
        TextView subcountyTextView = findViewById(R.id.subcount);
        subcountyTextView.setText(selectedSubcounty);


    }
    public void goMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goContracept(View view) {
        // Get the text from the TextView
        TextView textView = findViewById(R.id.subcount);
        String text = textView.getText().toString();
        // Save the text to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedText", text);
        editor.apply();
        Intent intent = new Intent(this, ContraceptActivity.class);
        startActivity(intent);
    }


    public void goServices(View view) {
        // Get the text from the TextView
        TextView textView = findViewById(R.id.subcount);
        String text = textView.getText().toString();
        // Save the text to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("savedText", text);
        editor.apply();
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }
    public void goInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

}