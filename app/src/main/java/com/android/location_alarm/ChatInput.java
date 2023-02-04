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

public class ChatInput extends AppCompatActivity {

    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    Button sab,cr;
    TextView ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_input);



        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");

        Log.d("UserColorStatus",UserColorStatus);

        view=(View)findViewById(R.id.bg);
        sab=findViewById(R.id.button22);
        cr=findViewById(R.id.button21);
        ch=findViewById(R.id.textView18);

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

            ch.setTextColor(Color.parseColor(TextInputcolor));




















            if (ci.equals("1")) {

                sab.setBackgroundResource(R.drawable.black_b);
                cr.setBackgroundResource(R.drawable.black_b);

                sab.setTextColor(Color.parseColor(TextInputcolor));
                cr.setTextColor(Color.parseColor(TextInputcolor));




            }
            if (ci.equals("2")) {

                sab.setBackgroundResource(R.drawable.grey_b);
                cr.setBackgroundResource(R.drawable.grey_b);

                sab.setTextColor(Color.parseColor(TextInputcolor));
                cr.setTextColor(Color.parseColor(TextInputcolor));







            }
            if (ci.equals("3")) {

                sab.setBackgroundResource(R.drawable.white_b);
                cr.setBackgroundResource(R.drawable.white_b);

                sab.setTextColor(Color.parseColor(TextInputcolor));
                cr.setTextColor(Color.parseColor(TextInputcolor));







            }
            if (ci.equals("4")) {


                sab.setBackgroundResource(R.drawable.red_b);
                cr.setBackgroundResource(R.drawable.red_b);

                sab.setTextColor(Color.parseColor(TextInputcolor));
                cr.setTextColor(Color.parseColor(TextInputcolor));

            }




        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }
    }
    public void sessionsearch(View v)
    {
        startActivity(new Intent(getApplicationContext(), SessionSearch.class));
    }

    public void sessioncreate(View v)
    {
        startActivity(new Intent(getApplicationContext(), SessionCreate.class));
    }

}