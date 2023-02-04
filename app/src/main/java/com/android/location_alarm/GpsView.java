package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GpsView extends AppCompatActivity {

    private DatabaseReference myDatabase;
    String  Latitude;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    String Longitude;
    String Altitude;
    String LOCATION_LINK;
    String Gpskey;
    View view;
    TextView latitude,longitude,altitude,glt,la,lo,al;
    Button vom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_view);

        latitude=findViewById(R.id.latitude);
        longitude=findViewById(R.id.longitude);
        altitude=findViewById(R.id.altitude);
        glt=findViewById(R.id.textView15);


        la=findViewById(R.id.textView19);
        lo=findViewById(R.id.textView16);
        al=findViewById(R.id.textView17);
        view=(View)findViewById(R.id.bg);
        vom=findViewById(R.id.button23);



        SharedPreferences SharedPreferences0 = getSharedPreferences("bcolor", MODE_PRIVATE);
        BarInputcolor = SharedPreferences0.getString("bcolor", "");

        SharedPreferences SharedPreferences1 = getSharedPreferences("tcolor", MODE_PRIVATE);
        TextInputcolor= SharedPreferences1.getString("tcolor", "");

        SharedPreferences SharedPreferences2 = getSharedPreferences("bgcolor", MODE_PRIVATE);
        BgInputcolor= SharedPreferences2.getString("bgcolor", "");

        SharedPreferences SharedPreferences3 = getSharedPreferences("Scolor", MODE_PRIVATE);
        StatusBar= SharedPreferences3.getString("Scolor", "");

        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");

        if(UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));





            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");

            latitude.setTextColor(Color.parseColor(TextInputcolor));
            longitude.setTextColor(Color.parseColor(TextInputcolor));
            altitude.setTextColor(Color.parseColor(TextInputcolor));
            glt.setTextColor(Color.parseColor(TextInputcolor));

            la.setTextColor(Color.parseColor(TextInputcolor));
            lo.setTextColor(Color.parseColor(TextInputcolor));
            al.setTextColor(Color.parseColor(TextInputcolor));



            if (ci.equals("1")) {

                vom.setBackgroundResource(R.drawable.black_b);
                vom.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("2")) {

                vom.setBackgroundResource(R.drawable.grey_b);
                vom.setTextColor(Color.parseColor(TextInputcolor));




            }
            if (ci.equals("3")) {

                vom.setBackgroundResource(R.drawable.white_b);
                vom.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("4")) {

                vom.setBackgroundResource(R.drawable.red_b);
                vom.setTextColor(Color.parseColor(TextInputcolor));
            }







        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }




        SharedPreferences sharedPreferences1 = getSharedPreferences("GpsidKey", MODE_PRIVATE);
        Gpskey = sharedPreferences1.getString("Gpsid", "");
        Gpskey=Gpskey.replaceAll("@gmail.com","");
        myDatabase = FirebaseDatabase.getInstance().getReference(Gpskey);
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                   Altitude = dataSnapshot.child("GPS").child("Altitude").getValue().toString();
                   Latitude = dataSnapshot.child("GPS").child("Latitude").getValue().toString();
                   Longitude= dataSnapshot.child("GPS").child("Longitude").getValue().toString();
                   altitude.setText(Altitude);
                   latitude.setText(Latitude);
                   longitude.setText(Longitude);
                   Toast.makeText(GpsView.this, Latitude, Toast.LENGTH_SHORT).show();
                   Toast.makeText(GpsView.this, Longitude, Toast.LENGTH_SHORT).show();
                   LOCATION_LINK="maps.google.com/?q="+Latitude+","+Longitude;
               } catch (Exception v) {
                    Toast.makeText(GpsView.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(GpsView.this, "database error...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void map(View v){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://"+LOCATION_LINK));
        startActivity(i);
    }
}