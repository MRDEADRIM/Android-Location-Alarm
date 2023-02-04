package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    //theam   xml = Theme.MaterialComponents.DayNight.DarkActionBar
    //replace xml = Theme.AppCompat.Light.NoActionBar
    Button save,black,grey,white,red,normal;
    EditText TextInput,BarInput,bgcolor,StatusBarInput;
    View view;
    String ci, UserColorStatus ;
    TextView sb,bc,tc,bac, dc1,e,d,ex,ed,dc2,se;
    String StatusBar,BarInputcolor,TextInputcolor,BgInputcolor, enable,CheckBox1,CheckBox2,CheckBox3;;
    CheckBox checkBox1,checkBox2,checkBox3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Switch sw1 = (Switch) findViewById(R.id.switch4);
        Switch sw2 = (Switch) findViewById(R.id.switch5);
        Switch sw3 = (Switch) findViewById(R.id.switch6);






        sb=findViewById(R.id.textView25);
        bc=findViewById(R.id.textView24);
        tc=findViewById(R.id.textView22);
        bac=findViewById(R.id.textView23);
        dc1 =findViewById(R.id.textView21);

        dc2=findViewById(R.id.textView34);
        ed=findViewById(R.id.textView32);
        e=findViewById(R.id.textView27);
        d=findViewById(R.id.textView28);
        ex=findViewById(R.id.textView31);
        se=findViewById(R.id.textView33);

        save =findViewById(R.id.save);
        StatusBarInput = findViewById(R.id.StatusBar);
        BarInput = findViewById(R.id.Barcolor);
        TextInput = findViewById(R.id.TextColor);
        view=(View)findViewById(R.id.bg);
        bgcolor  = findViewById(R.id.BackgroundColor);
        black=findViewById(R.id.black);
        grey=findViewById(R.id.grey);
        white=findViewById(R.id.white);
        red=findViewById(R.id.red);
        normal=findViewById(R.id.normal);



        SharedPreferences SharedPreferencesc = getSharedPreferences("usc", MODE_PRIVATE);
        UserColorStatus = SharedPreferencesc.getString("usc", "");
        SharedPreferences SharedPreferences0 = getSharedPreferences("bcolor", MODE_PRIVATE);
        BarInputcolor = SharedPreferences0.getString("bcolor", "");
        SharedPreferences SharedPreferences1 = getSharedPreferences("tcolor", MODE_PRIVATE);
        TextInputcolor= SharedPreferences1.getString("tcolor", "");
        SharedPreferences SharedPreferences2 = getSharedPreferences("bgcolor", MODE_PRIVATE);
        BgInputcolor= SharedPreferences2.getString("bgcolor", "");
        SharedPreferences SharedPreferences3 = getSharedPreferences("Scolor", MODE_PRIVATE);
        StatusBar= SharedPreferences3.getString("Scolor", "");

        if(UserColorStatus.equals("1")) {

            StatusBarInput.setText(StatusBar);
            BarInput.setText(BarInputcolor);
            TextInput.setText(TextInputcolor);
            bgcolor.setText(BgInputcolor);






            black.setTextColor(Color.parseColor(TextInputcolor));
            grey.setTextColor(Color.parseColor(TextInputcolor));
            white.setTextColor(Color.parseColor(TextInputcolor));
            red.setTextColor(Color.parseColor(TextInputcolor));








            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));

            sb.setTextColor(Color.parseColor(TextInputcolor));
            bc.setTextColor(Color.parseColor(TextInputcolor));
            tc.setTextColor(Color.parseColor(TextInputcolor));
            bac.setTextColor(Color.parseColor(TextInputcolor));
            dc1.setTextColor(Color.parseColor(TextInputcolor));


            dc2.setTextColor(Color.parseColor(TextInputcolor));
            ed.setTextColor(Color.parseColor(TextInputcolor));
            e.setTextColor(Color.parseColor(TextInputcolor));
            d.setTextColor(Color.parseColor(TextInputcolor));
            ex.setTextColor(Color.parseColor(TextInputcolor));
            se.setTextColor(Color.parseColor(TextInputcolor));


            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");





            if (ci.equals("1")) {


                black.setBackgroundResource(R.drawable.black_b);
                grey.setBackgroundResource(R.drawable.null_b);
                white.setBackgroundResource(R.drawable.null_b);
                red.setBackgroundResource(R.drawable.null_b);

                TextInput.setBackgroundResource(R.drawable.black_b);
                BarInput.setBackgroundResource(R.drawable.black_b);
                bgcolor.setBackgroundResource(R.drawable.black_b);
                StatusBarInput.setBackgroundResource(R.drawable.black_b);
                save.setBackgroundResource(R.drawable.black_b);
            }
            if (ci.equals("2")) {

                black.setBackgroundResource(R.drawable.null_b);
                grey.setBackgroundResource(R.drawable.grey_b);
                white.setBackgroundResource(R.drawable.null_b);
                red.setBackgroundResource(R.drawable.null_b);


                TextInput.setBackgroundResource(R.drawable.grey_b);
                BarInput.setBackgroundResource(R.drawable.grey_b);
                bgcolor.setBackgroundResource(R.drawable.grey_b);
                StatusBarInput.setBackgroundResource(R.drawable.grey_b);
                save.setBackgroundResource(R.drawable.grey_b);
            }
            if (ci.equals("3")) {

                black.setBackgroundResource(R.drawable.null_b);
                grey.setBackgroundResource(R.drawable.null_b);
                white.setBackgroundResource(R.drawable.white_b);
                red.setBackgroundResource(R.drawable.null_b);

                TextInput.setBackgroundResource(R.drawable.white_b);
                BarInput.setBackgroundResource(R.drawable.white_b);
                bgcolor.setBackgroundResource(R.drawable.white_b);
                StatusBarInput.setBackgroundResource(R.drawable.white_b);
                save.setBackgroundResource(R.drawable.white_b);
            }
            if (ci.equals("4")) {

                black.setBackgroundResource(R.drawable.null_b);
                grey.setBackgroundResource(R.drawable.null_b);
                white.setBackgroundResource(R.drawable.null_b);
                red.setBackgroundResource(R.drawable.red_b);




                TextInput.setBackgroundResource(R.drawable.red_b);
                BarInput.setBackgroundResource(R.drawable.red_b);
                bgcolor.setBackgroundResource(R.drawable.red_b);
                StatusBarInput.setBackgroundResource(R.drawable.red_b);
                save.setBackgroundResource(R.drawable.red_b);
            }
        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences sharedPreference1 = getSharedPreferences("swkey1", MODE_PRIVATE);
        enable= sharedPreference1.getString("swkey1", "");
        if(enable.equals("") ||enable.equals("1")){
            sw1.setChecked(true);
        }
        else {
            sw1.setChecked(false);
        }
        SharedPreferences sharedPreference2 = getSharedPreferences("swkey2", MODE_PRIVATE);
        enable= sharedPreference2.getString("swkey2", "");
        if(enable.equals("") || enable.equals("1")){
            sw2.setChecked(true);
        }else {
            sw2.setChecked(false);
        }
        SharedPreferences sharedPreference3 = getSharedPreferences("swkey3", MODE_PRIVATE);
        enable= sharedPreference3.getString("swkey3", "");
        if(enable.equals("") || enable.equals("1")){
            sw3.setChecked(true);
        }else{
            sw3.setChecked(false);
        }
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enable = "1";
                    Toast.makeText(SettingsActivity.this, "Switch on 1", Toast.LENGTH_SHORT).show();
                } else {

                    enable = "0";
                    Toast.makeText(SettingsActivity.this, "Switch off 1", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences sharedPref1 = getSharedPreferences("swkey1", MODE_PRIVATE);
                SharedPreferences.Editor enable = sharedPref1.edit();
                enable.putString("swkey1", SettingsActivity.this.enable);
                enable.apply();
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enable = "1";
                    Toast.makeText(SettingsActivity.this, "Switch on 2", Toast.LENGTH_SHORT).show();
                } else {

                    enable = "0";
                    Toast.makeText(SettingsActivity.this, "Switch off 2", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences sharedPref2 = getSharedPreferences("swkey2", MODE_PRIVATE);
                SharedPreferences.Editor enable = sharedPref2.edit();
                enable.putString("swkey2", SettingsActivity.this.enable);
                enable.apply();
            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enable = "1";
                    Toast.makeText(SettingsActivity.this, "Switch on 3", Toast.LENGTH_SHORT).show();
                } else {

                    enable = "0";
                    Toast.makeText(SettingsActivity.this, "Switch off 3", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences sharedPref3 = getSharedPreferences("swkey3", MODE_PRIVATE);
                SharedPreferences.Editor enable = sharedPref3.edit();
                enable.putString("swkey3", SettingsActivity.this.enable);
                enable.apply();
            }
        });

















    }
    public void BlackB(View v){
        black.setBackgroundResource(R.drawable.black_b);
        grey.setBackgroundResource(R.drawable.null_b);
        white.setBackgroundResource(R.drawable.null_b);
        red.setBackgroundResource(R.drawable.null_b);
        ci = "1";
        SharedPreferences sharedPref = getSharedPreferences("ci", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ci", ci);
        editor.apply();
    }
   public void GreyB(View v) {
       black.setBackgroundResource(R.drawable.null_b);
       grey.setBackgroundResource(R.drawable.grey_b);
       white.setBackgroundResource(R.drawable.null_b);
       red.setBackgroundResource(R.drawable.null_b);

       ci = "2";
       SharedPreferences sharedPref = getSharedPreferences("ci", MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPref.edit();
       editor.putString("ci", ci);
       editor.apply();
   }
    public void WhiteB(View v){
        black.setBackgroundResource(R.drawable.null_b);
        grey.setBackgroundResource(R.drawable.null_b);
        white.setBackgroundResource(R.drawable.white_b);
        red.setBackgroundResource(R.drawable.null_b);
        ci = "3";
        SharedPreferences sharedPref = getSharedPreferences("ci", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ci", ci);
        editor.apply();
    }
    public void RedB(View v){
        black.setBackgroundResource(R.drawable.null_b);
        grey.setBackgroundResource(R.drawable.null_b);
        white.setBackgroundResource(R.drawable.null_b);
        red.setBackgroundResource(R.drawable.red_b);
        ci = "4";
        SharedPreferences sharedPref = getSharedPreferences("ci", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ci", ci);
        editor.apply();
    }


    public void normal(View v){

        normal.setBackgroundResource(R.drawable.button);
        black.setBackgroundResource(R.drawable.null_b);
        grey.setBackgroundResource(R.drawable.null_b);
        white.setBackgroundResource(R.drawable.null_b);
        red.setBackgroundResource(R.drawable.null_b);
        ci = "0";
        SharedPreferences sharedPref = getSharedPreferences("ci", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ci", ci);
        editor.apply();
    }
    public void DefaultSettings(View v){

        UserColorStatus="0";
        SharedPreferences sharedPref = getSharedPreferences("usc", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("usc", UserColorStatus);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }








    public void Apply(View v) {
        if(BarInput.getText().toString().trim().contains("#") && TextInput.getText().toString().trim().contains("#") && bgcolor.getText().toString().trim().contains("#") &&
                StatusBarInput.getText().toString().trim().contains("#") ){





            if (bgcolor.getText().toString().trim().equals(TextInput.getText().toString().trim())) {
                Toast.makeText(this, "cannot apply text color and bg color same", Toast.LENGTH_SHORT).show();


            } else {

                if (bgcolor.getText().toString().trim().equals("#FFFFFF")) {

                    Toast.makeText(this, "cannot apply this color apply different color", Toast.LENGTH_SHORT).show();


                }

                else {

                    UserColorStatus = "1";
                    SharedPreferences sharedPref = getSharedPreferences("usc", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("usc", UserColorStatus);
                    editor.apply();


                    BarInputcolor = BarInput.getText().toString().trim();

                    SharedPreferences sharedPref0 = getSharedPreferences("bcolor", MODE_PRIVATE);
                    SharedPreferences.Editor editor0 = sharedPref0.edit();
                    editor0.putString("bcolor", BarInputcolor);
                    editor0.apply();


                    TextInputcolor = TextInput.getText().toString().trim();
                    SharedPreferences sharedPref1 = getSharedPreferences("tcolor", MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPref1.edit();
                    editor1.putString("tcolor", TextInputcolor);
                    editor1.apply();


                    BgInputcolor = bgcolor.getText().toString().trim();


                    SharedPreferences sharedPref2 = getSharedPreferences("bgcolor", MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPref2.edit();
                    editor2.putString("bgcolor", BgInputcolor);
                    editor2.apply();

                    StatusBar = StatusBarInput.getText().toString().trim();

                    SharedPreferences sharedPref3 = getSharedPreferences("Scolor", MODE_PRIVATE);
                    SharedPreferences.Editor editor3 = sharedPref3.edit();
                    editor3.putString("Scolor", StatusBar);
                    editor3.apply();
                }


            }




        }else {
            Toast.makeText(this, "include # before the hex", Toast.LENGTH_SHORT).show();
        }




    }


    public void Save(View v){

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

    }




    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}