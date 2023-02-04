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
import android.widget.TextView;
import android.widget.Toast;

public class ChildInput extends AppCompatActivity {

    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    Button sb,seb;

    TextView gp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_input);


        sb=findViewById(R.id.button18);
        seb=findViewById(R.id.button19);
        gp=findViewById(R.id.textView11);


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

            gp.setTextColor(Color.parseColor(TextInputcolor));

            if (ci.equals("1")) {

                sb.setBackgroundResource(R.drawable.black_b);
                seb.setBackgroundResource(R.drawable.black_b);


                sb.setTextColor(Color.parseColor(TextInputcolor));
                seb.setTextColor(Color.parseColor(TextInputcolor));



            }
            if (ci.equals("2")) {


                sb.setBackgroundResource(R.drawable.grey_b);
                seb.setBackgroundResource(R.drawable.grey_b);


                sb.setTextColor(Color.parseColor(TextInputcolor));
                seb.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("3")) {

                sb.setBackgroundResource(R.drawable.white_b);
                seb.setBackgroundResource(R.drawable.white_b);


                sb.setTextColor(Color.parseColor(TextInputcolor));
                seb.setTextColor(Color.parseColor(TextInputcolor));



            }
            if (ci.equals("4")) {

                sb.setBackgroundResource(R.drawable.red_b);
                seb.setBackgroundResource(R.drawable.red_b);


                sb.setTextColor(Color.parseColor(TextInputcolor));
                seb.setTextColor(Color.parseColor(TextInputcolor));



            }
        }else {
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
            }
        }



    public void childTrackerId(View v){
        startActivity(new Intent(getApplicationContext(), ChildTrackerId.class));
        finish();
    }

    public void childTrackerIdS(View v){
        startActivity(new Intent(getApplicationContext(), ChildTrackerIdS.class));
        finish();
    }
}