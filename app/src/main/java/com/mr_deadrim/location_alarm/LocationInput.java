package com.mr_deadrim.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LocationInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);
    }
    public void maps(View v){
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }
}