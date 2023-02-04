package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.util.Log.d;

public class ChildTrackerIdS extends AppCompatActivity {

    EditText gpsids,gpskey;
    Button sb;
    TextView gs;
    String ci;
    private DatabaseReference myDatabase;
    int i=0;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_tracker_id_s);

        gpsids = findViewById(R.id.gpsids);
        gpskey = findViewById(R.id.gpskey);

        sb =findViewById(R.id.button17);
        gs=findViewById(R.id.textView10);




        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");

        Log.d("UserColorStatus",UserColorStatus);



        view=(View)findViewById(R.id.bg);


        SharedPreferences SharedPreferences0 = getSharedPreferences("bcolor", MODE_PRIVATE);
        BarInputcolor = SharedPreferences0.getString("bcolor", "");

        SharedPreferences SharedPreferences1 = getSharedPreferences("tcolor", MODE_PRIVATE);
        TextInputcolor= SharedPreferences1.getString("tcolor", "");

        SharedPreferences SharedPreferences2 = getSharedPreferences("bgcolor", MODE_PRIVATE);
        BgInputcolor= SharedPreferences2.getString("bgcolor", "");

        SharedPreferences SharedPreferences3 = getSharedPreferences("Scolor", MODE_PRIVATE);
        StatusBar= SharedPreferences3.getString("Scolor", "");
        if(UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));


            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");

            gs.setTextColor(Color.parseColor(TextInputcolor));






            if (ci.equals("1")) {

                gpsids.setBackgroundResource(R.drawable.black_b);
                gpskey.setBackgroundResource(R.drawable.black_b);
                sb.setBackgroundResource(R.drawable.black_b);



                gpsids.setTextColor(Color.parseColor(TextInputcolor));
                gpskey.setTextColor(Color.parseColor(TextInputcolor));
                sb.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("2")) {



                gpsids.setBackgroundResource(R.drawable.grey_b);
                gpskey.setBackgroundResource(R.drawable.grey_b);
                sb.setBackgroundResource(R.drawable.grey_b);


                gpsids.setTextColor(Color.parseColor(TextInputcolor));
                gpskey.setTextColor(Color.parseColor(TextInputcolor));
                sb.setTextColor(Color.parseColor(TextInputcolor));



            }
            if (ci.equals("3")) {

                gpsids.setBackgroundResource(R.drawable.white_b);
                gpskey.setBackgroundResource(R.drawable.white_b);
                sb.setBackgroundResource(R.drawable.white_b);

                gpsids.setTextColor(Color.parseColor(TextInputcolor));
                gpskey.setTextColor(Color.parseColor(TextInputcolor));
                sb.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("4")) {

                gpsids.setBackgroundResource(R.drawable.red_b);
                gpskey.setBackgroundResource(R.drawable.red_b);
                sb.setBackgroundResource(R.drawable.red_b);


                gpsids.setTextColor(Color.parseColor(TextInputcolor));
                gpskey.setTextColor(Color.parseColor(TextInputcolor));
                sb.setTextColor(Color.parseColor(TextInputcolor));
            }


        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }









    }
    public void gpssearch(View v){

        String Gpsid = gpsids.getText().toString().trim();
        String Gpskey = gpskey.getText().toString().trim();
        SharedPreferences sharedPref = getSharedPreferences("GpsidKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gpsid = Gpsid.replaceAll("@gmail.com", "");
        editor.putString("Gpsid",Gpsid);
        editor.apply();
        myDatabase = FirebaseDatabase.getInstance().getReference(Gpsid);











        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(i==0) {


                    try {
                        String password1 = dataSnapshot.child("keys").child("Gkey").getValue().toString();
                        if (Gpskey.equals(password1)) {

                            Toast.makeText(ChildTrackerIdS.this, "Login sucessfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), GpsView.class));
                            i=1;

                        } else {
                            Toast.makeText(ChildTrackerIdS.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {


                        Toast.makeText(ChildTrackerIdS.this, "Incorrect username", Toast.LENGTH_SHORT).show();


                    }
                }

            }









            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChildTrackerIdS.this, "database error...", Toast.LENGTH_SHORT).show();
            }
        });



    }
}