package com.collo.youthapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    private RecyclerView hospitalRecyclerView;
    private HospitalAdapter hospitalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.theme));
        }

        hospitalRecyclerView = findViewById(R.id.hospitalRecyclerView);
        hospitalRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the selected sub-county from the intent
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String selectedSubcounty = sharedPreferences.getString("savedText", "");

        // Get the hospitals for the selected sub-county
        List<Hospital> hospitals = getHospitalsForSubcounty(selectedSubcounty);

        // Set up the HospitalAdapter
        hospitalAdapter = new HospitalAdapter(hospitals);
        hospitalRecyclerView.setAdapter(hospitalAdapter);
    }

    private List<Hospital> getHospitalsForSubcounty(String subcounty) {
        List<Hospital> hospitals = new ArrayList<>();

        // Replace with your logic to fetch hospitals based on the selected sub-county
        // Here, we are using hardcoded data for demonstration purposes

        if (subcounty.equals("Ainamoi")) {
            hospitals.add(new Hospital("Ainamoi Health Centre", "Location: Ainamoi.", "Ainamoi"));
            hospitals.add(new Hospital("Live With Hope Centre", "Location: Kipchebor.", "Ainamoi"));
            hospitals.add(new Hospital("Chepkemel Health Centre", "Location: Kaplelartet.", "Ainamoi"));
            hospitals.add(new Hospital("Konnect Youth Consortium", "Location: Along Kisumu Road.", "Ainamoi"));
        } else if (subcounty.equals("Bureti")) {
            hospitals.add(new Hospital("Hospital B1", "Location B1", "Bureti"));
            hospitals.add(new Hospital("Hospital B2", "Location B2", "Bureti"));
        } else if (subcounty.equals("Belgut")) {
            hospitals.add(new Hospital("Hospital C1", "Location C1", "Belgut"));
            hospitals.add(new Hospital("Hospital C2", "Location C2", "Belgut"));
            hospitals.add(new Hospital("Hospital C3", "Location C3", "Belgut"));
        }
        // Add more conditions for other sub-counties

        return hospitals;
    }
}
