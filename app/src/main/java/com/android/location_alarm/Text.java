package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.Telephony.Mms.Part.TEXT;

public class Text extends AppCompatActivity {

    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    TextView et,text;
    Button sav;
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);


        et=findViewById(R.id.textView6);
        sav=findViewById(R.id.esave);
        editText = findViewById(R.id.Etext);
        text=findViewById(R.id.E_text);

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

            et.setTextColor(Color.parseColor(TextInputcolor));

            text.setTextColor(Color.parseColor(TextInputcolor));



            if (ci.equals("1")) {

                sav.setBackgroundResource(R.drawable.black_b);
                editText.setBackgroundResource(R.drawable.black_b);

                editText.setTextColor(Color.parseColor(TextInputcolor));
                sav.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("2")) {

                sav.setBackgroundResource(R.drawable.grey_b);
                editText.setBackgroundResource(R.drawable.grey_b);

                editText.setTextColor(Color.parseColor(TextInputcolor));
                sav.setTextColor(Color.parseColor(TextInputcolor));




            }
            if (ci.equals("3")) {

                sav.setBackgroundResource(R.drawable.white_b);
                editText.setBackgroundResource(R.drawable.white_b);

                editText.setTextColor(Color.parseColor(TextInputcolor));
                sav.setTextColor(Color.parseColor(TextInputcolor));


            }
            if (ci.equals("4")) {

                sav.setBackgroundResource(R.drawable.red_b);
                editText.setBackgroundResource(R.drawable.red_b);

                editText.setTextColor(Color.parseColor(TextInputcolor));
                sav.setTextColor(Color.parseColor(TextInputcolor));
            }



        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }





        EditText editText = findViewById(R.id.Etext);


        SharedPreferences sharedPreferences = getSharedPreferences("textKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        text.setText("Emergency Message :"+value);
        sav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString().trim();
                SharedPreferences sharedPref = getSharedPreferences("textKey", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("value", value);
                editor.apply();
                Toast.makeText(Text.this,"SAVE SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}