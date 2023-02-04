package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class EmergencyService extends AppCompatActivity {

    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    android.view.View view;
    Button eb,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_service);

         eb=findViewById(R.id.Emergency_btn);
         add=findViewById(R.id.button6);


        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");

        Log.d("UserColorStatus",UserColorStatus);



        view=(android.view.View)findViewById(R.id.bg);


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






            if (ci.equals("1")) {
                eb.setBackgroundResource(R.drawable.black_b);
                add.setBackgroundResource(R.drawable.black_b);
                eb.setTextColor(Color.parseColor(TextInputcolor));
                add.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("2")) {



                eb.setBackgroundResource(R.drawable.grey_b);
                add.setBackgroundResource(R.drawable.grey_b);
                eb.setTextColor(Color.parseColor(TextInputcolor));
                add.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("3")) {


                eb.setBackgroundResource(R.drawable.white_b);
                add.setBackgroundResource(R.drawable.white_b);
                eb.setTextColor(Color.parseColor(TextInputcolor));
                add.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("4")) {

                eb.setBackgroundResource(R.drawable.red_b);
                add.setBackgroundResource(R.drawable.red_b);
                eb.setTextColor(Color.parseColor(TextInputcolor));
                add.setTextColor(Color.parseColor(TextInputcolor));
            }


        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
    }
    public void E_information(android.view.View v) {
        startActivity(new Intent(getApplicationContext(), EmergencyS.class));
        finish();
    }
    public void view(android.view.View v) {
        Button Emergency_btn=findViewById(R.id.Emergency_btn);
        //Emergency_btn.setEnabled(false);
        Intent intent = new Intent(getApplicationContext(), View.class);
        startActivity(intent);
       // finish();
    }
}