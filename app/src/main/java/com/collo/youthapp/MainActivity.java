package com.collo.youthapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner countySpinner;
    private Spinner subcountySpinner;

    private Map<String, List<String>> subcountyMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.theme));
        }

        // Retrieve references to the Spinners
        countySpinner = findViewById(R.id.county_spinner);
        subcountySpinner = findViewById(R.id.subcounty_spinner);

        // After setting up the adapter for countySpinner
        countySpinner.setBackgroundResource(R.drawable.custom_spinner_background);

        // After setting up the adapter for subcountySpinner
        subcountySpinner.setBackgroundResource(R.drawable.custom_spinner_background);


        // Populate the county Spinner with sample data (replace with your own data)
        List<String> countyList = new ArrayList<>();
        countyList.add("Select County");
        countyList.add("Kericho");
        countyList.add("Bomet");
        countyList.add("Nakuru");

        CustomSpinnerAdapter countyAdapter = new CustomSpinnerAdapter(this, countyList);
        countyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        countySpinner.setAdapter(countyAdapter);

        // Populate the sub-county Spinner with default data
        List<String> defaultSubcountyList = new ArrayList<>();
        defaultSubcountyList.add("Select Sub-County");

        CustomSpinnerAdapter subcountyAdapter = new CustomSpinnerAdapter(this, defaultSubcountyList);
        subcountyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        subcountySpinner.setAdapter(subcountyAdapter);

        // Create the sub-county data map
        subcountyMap = new HashMap<>();
        subcountyMap.put("Kericho", getKerichoSubcounties());
        subcountyMap.put("Bomet", getBometSubcounties());
        subcountyMap.put("Nakuru", getNakuruSubcounties());

        // Handle county selection changes
        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCounty = (String) parent.getItemAtPosition(position);

                // Retrieve the corresponding sub-counties for the selected county
                List<String> subcountyData = subcountyMap.get(selectedCounty);

                if (subcountyData != null) {
                    // Populate the sub-county Spinner with the relevant data
                    CustomSpinnerAdapter subcountyAdapter = new CustomSpinnerAdapter(MainActivity.this, subcountyData);
                    subcountyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    subcountySpinner.setAdapter(subcountyAdapter);
                    subcountySpinner.setSelection(0); // Set default subcounty as the first item
                } else {
                    // Handle if no sub-county data is available for the selected county
                    List<String> defaultSubcountyList = new ArrayList<>();
                    defaultSubcountyList.add("No Sub-Counties Available");
                    CustomSpinnerAdapter subcountyAdapter = new CustomSpinnerAdapter(MainActivity.this, defaultSubcountyList);
                    subcountyAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    subcountySpinner.setAdapter(subcountyAdapter);
                    subcountySpinner.setSelection(0); // Set default subcounty as the first item
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when nothing is selected
            }
        });

        // Handle sub-county selection changes
        subcountySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSubcounty = (String) parent.getItemAtPosition(position);
                // Save the selected sub-county to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedSubcounty", selectedSubcounty);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when nothing is selected
            }
        });

    }

    // Sample sub-county data for Kericho
    private List<String> getKerichoSubcounties() {
        List<String> subcountyList = new ArrayList<>();
        subcountyList.add("Ainamoi");
        subcountyList.add("Bureti");
        subcountyList.add("Belgut");
        subcountyList.add("Kericho Town");
        subcountyList.add("Kipkelion East");
        subcountyList.add("Kipkelion West");
        subcountyList.add("Sigowet/Soin");
        return subcountyList;
    }

    // Sample sub-county data for Bomet
    private List<String> getBometSubcounties() {
        List<String> subcountyList = new ArrayList<>();
        subcountyList.add("Bomet Central");
        subcountyList.add("Bomet East");
        subcountyList.add("Chepalungu");
        return subcountyList;
    }

    // Sample sub-county data for Nakuru
    private List<String> getNakuruSubcounties() {
        List<String> subcountyList = new ArrayList<>();
        subcountyList.add("Nakuru Town East");
        subcountyList.add("Nakuru Town West");
        subcountyList.add("Njoro");
        return subcountyList;
    }



    private static class CustomSpinnerAdapter extends ArrayAdapter<String> {
        private final LayoutInflater inflater;

        public CustomSpinnerAdapter(Context context, List<String> items) {
            super(context, R.layout.spinner_item, items);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.spinner_item, parent, false);
                holder = new ViewHolder();
                holder.textView = convertView.findViewById(R.id.spinner_item_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textView.setText(getItem(position));

            return convertView;
        }

        private static class ViewHolder {
            TextView textView;
        }
    }

    public void goDetails(View view) {
        String selectedSubcounty = subcountySpinner.getSelectedItem().toString();

        // Check if the selected sub-county contains "No Sub-County"
        if (selectedSubcounty.equals("No Sub-Counties Available")) {
            // Show an error message to select a county first
            Toast.makeText(this, "Please select a County first", Toast.LENGTH_LONG).show();
            return; // Return without starting the next activity
        }

        // Save the selected sub-county to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedSubcounty", selectedSubcounty);
        editor.apply();

        // Start the next activity and pass the selected sub-county
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("selectedSubcounty", selectedSubcounty);
        startActivity(intent);
    }


}

