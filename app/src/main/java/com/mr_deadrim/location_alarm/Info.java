package com.mr_deadrim.location_alarm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Info extends AppCompatActivity {

    DatabaseReference remove_db;
    String Email,Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);



        SharedPreferences email = getSharedPreferences("email", MODE_PRIVATE);
        Email = email.getString("email", "");


    }

    public void Remove(View v){
       //UNDER DEVELOPMENT


      // remove_db = FirebaseDatabase.getInstance().getReference("Android Location Alarm").child(Email);
       //remove_db.removeValue();



    }
}