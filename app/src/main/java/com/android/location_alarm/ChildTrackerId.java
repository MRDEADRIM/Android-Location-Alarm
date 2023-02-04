package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChildTrackerId extends AppCompatActivity {

    TextView text,g;
    EditText keyinput;
    Button sa;
    private DatabaseReference myDatabase;
    String email,key,enable3,gpsconfig="0";

    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_tracker_id);

        g=findViewById(R.id.textView9);
        text=findViewById(R.id.gpsid);
        keyinput =findViewById(R.id.key);
        sa=findViewById(R.id.button16);



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

            text.setTextColor(Color.parseColor(TextInputcolor));
            g.setTextColor(Color.parseColor(TextInputcolor));





            if (ci.equals("1")) {

                keyinput.setBackgroundResource(R.drawable.black_b);
                sa.setBackgroundResource(R.drawable.black_b);


                keyinput.setTextColor(Color.parseColor(TextInputcolor));
                sa.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("2")) {

                keyinput.setBackgroundResource(R.drawable.grey_b);
                sa.setBackgroundResource(R.drawable.grey_b);


                keyinput.setTextColor(Color.parseColor(TextInputcolor));
                sa.setTextColor(Color.parseColor(TextInputcolor));



            }
            if (ci.equals("3")) {

                keyinput.setBackgroundResource(R.drawable.white_b);
                sa.setBackgroundResource(R.drawable.white_b);

                keyinput.setTextColor(Color.parseColor(TextInputcolor));
                sa.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("4")) {

                keyinput.setBackgroundResource(R.drawable.red_b);
                sa.setBackgroundResource(R.drawable.red_b);


                keyinput.setTextColor(Color.parseColor(TextInputcolor));
                sa.setTextColor(Color.parseColor(TextInputcolor));
            }


        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }










        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        email = email.replaceAll("@gmail.com", "");

        text.setText("id:"+email);
        myDatabase = FirebaseDatabase.getInstance().getReference(email);
    }
    public void gpssave(View v){
        key=keyinput.getText().toString().trim();
        myDatabase.child("keys").child("Gkey").setValue(key);
        Toast.makeText(ChildTrackerId.this, "Successfully gps on", Toast.LENGTH_SHORT).show();
        enable3="1";
        SharedPreferences sharedPref2 = getSharedPreferences("sKey3", MODE_PRIVATE);
        SharedPreferences.Editor enable = sharedPref2.edit();
        enable.putString("enable3", enable3);
        enable.apply();

        gpsconfig="1";
        SharedPreferences sharedPref = getSharedPreferences("Gpsconfig", MODE_PRIVATE);
        SharedPreferences.Editor config = sharedPref.edit();
        config.putString("Gconfig", gpsconfig);
        config.apply();








            startActivity(new Intent(getApplicationContext(), LocationInputC.class));
            finish();


    }


/*


    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){






            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

 */

}