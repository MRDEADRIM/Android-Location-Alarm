package com.android.location_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class LocationInputC extends AppCompatActivity {

    private String enable1 = "0",enable2="0",enable3="0",gpsconfig="0";
    private String User_Radius;
    private RadioGroup radioGroup;
    final static int RQS_OPEN_AUDIO_MP3 = 1;
    EditText audio1,audio2,audio3,settext1,settext2,settext3,phoneno,meter;
    CheckBox checkBox1,checkBox2,checkBox3;
    String CheckBox1,CheckBox2,CheckBox3,ci;
    TextView rs,sf,ea,gt;
    Button mapb,gmap;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar;
    View view;
    private final static int SEND_STORAGE_PERMISSION_REQ =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input_c);
        rs=findViewById(R.id.textView3);
        sf=findViewById(R.id.textView7);
        ea=findViewById(R.id.textView8);
        mapb=findViewById(R.id.button4);
        gt=findViewById(R.id.textView35);
        gmap=findViewById(R.id.gps);
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
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Switch sw1 = (Switch) findViewById(R.id.switch1);
        Switch sw2 = (Switch) findViewById(R.id.switch2);
        Switch sw3 = (Switch) findViewById(R.id.switch3);
        phoneno = (EditText) findViewById(R.id.phno);
        audio1 = (EditText) findViewById(R.id.audio1);
        audio2 = (EditText) findViewById(R.id.audio2);
        audio3 = (EditText) findViewById(R.id.audio3);
        checkBox1= (CheckBox) findViewById(R.id.checkBox1);
        checkBox2= (CheckBox) findViewById(R.id.checkBox2);
        checkBox3= (CheckBox) findViewById(R.id.checkBox3);
        settext1= (EditText) findViewById(R.id.settext1);
        settext2= (EditText) findViewById(R.id.settext2);
        settext3= (EditText) findViewById(R.id.settext3);
        meter=(EditText) findViewById(R.id.meter);
        Button audiod1 = (Button) findViewById(R.id.audiod1);
        Button audiod2 = (Button) findViewById(R.id.audiod2);
        Button audiod3 = (Button) findViewById(R.id.audiod3);
        Button gps = (Button) findViewById(R.id.gps);
        SharedPreferences sharedPreferences1 = getSharedPreferences("Audio1", MODE_PRIVATE);
        String Audio1 = sharedPreferences1.getString("Audio1", "");
        audio1.setText(Audio1);
        SharedPreferences sharedPreferences2 = getSharedPreferences("Audio2", MODE_PRIVATE);
        String Audio2 = sharedPreferences2.getString("Audio2", "");
        audio2.setText(Audio2);
        SharedPreferences sharedPreferences3 = getSharedPreferences("Audio3", MODE_PRIVATE);
        String Audio3 = sharedPreferences3.getString("Audio3", "");
        audio3.setText(Audio3);
        SharedPreferences sharedPreferencesa = getSharedPreferences("Settext1", MODE_PRIVATE);
        String  Settext1= sharedPreferencesa.getString("Settext1", "");
        settext1.setText(Settext1);
        Log.d("process", String.valueOf(settext1));
        SharedPreferences sharedPreferencesb = getSharedPreferences("Settext2", MODE_PRIVATE);
        String Settext2 = sharedPreferencesb.getString("Settext2", "");
        settext2.setText(Settext2);
        SharedPreferences sharedPreferencesc = getSharedPreferences("Settext3", MODE_PRIVATE);
        String Settext3 = sharedPreferencesc.getString("Settext3", "");
        settext3.setText(Settext3);
        SharedPreferences sharedPreferencesp = getSharedPreferences("phKey", MODE_PRIVATE);
        String Phoneno = sharedPreferencesp.getString("Phonenoc", "");
        phoneno.setText(Phoneno);
        if(Settext1.equals("") || Settext2.equals(" "))
        {
            settext1.setText("enter");
        }
        if(Settext2.equals("") || Settext2.equals(" "))
        {
            settext2.setText("dewell");
        }
        if(Settext3.equals("") || Settext3.equals(" "))
        {
            settext3.setText("exit");
        }
        phoneno.setVisibility(View.INVISIBLE);
        audio1.setVisibility(View.INVISIBLE);
        audio2.setVisibility(View.INVISIBLE);
        audio3.setVisibility(View.INVISIBLE);
        audiod1.setVisibility(View.INVISIBLE);
        audiod2.setVisibility(View.INVISIBLE);
        audiod3.setVisibility(View.INVISIBLE);
        gps.setVisibility(View.INVISIBLE);
        checkBox1.setVisibility(View.INVISIBLE);
        checkBox2.setVisibility(View.INVISIBLE);
        checkBox3.setVisibility(View.INVISIBLE);
        radioGroup.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPreferencec1 = getSharedPreferences("CheckBox1", MODE_PRIVATE);
        CheckBox1 = sharedPreferencec1.getString("CheckBox1", "");
        if(CheckBox1.equals("1")) {
            checkBox1.setChecked(true);
        }
        SharedPreferences sharedPreferencec2 = getSharedPreferences("CheckBox2", MODE_PRIVATE);
        CheckBox2= sharedPreferencec2.getString("CheckBox2", "");
        if(CheckBox2.equals("2")) {
            checkBox2.setChecked(true);
        }
        SharedPreferences sharedPreferencec3 = getSharedPreferences("CheckBox3", MODE_PRIVATE);
        CheckBox3= sharedPreferencec3.getString("CheckBox3", "");
        if(CheckBox3.equals("3")) {
            checkBox3.setChecked(true);
        }
        SharedPreferences sharedPreferencese1 = getSharedPreferences("sKey1", MODE_PRIVATE);
        enable1 = sharedPreferencese1.getString("enable1", "");
        if(enable1.equals("1")){
            sw1.setChecked(true);
            phoneno.setVisibility(View.VISIBLE);
            checkBox1.setVisibility(View.VISIBLE);
            checkBox2.setVisibility(View.VISIBLE);
            checkBox3.setVisibility(View.VISIBLE);
        }
        SharedPreferences sharedPreferencese2 = getSharedPreferences("sKey2", MODE_PRIVATE);
        enable2 = sharedPreferencese2.getString("enable2", "");
        if(enable2.equals("1")){
            sw2.setChecked(true);
            audio1.setEnabled(false);
            audio2.setEnabled(false);
            audio3.setEnabled(false);
            audio1.setVisibility(View.VISIBLE);
            audio2.setVisibility(View.VISIBLE);
            audio3.setVisibility(View.VISIBLE);
            audiod1.setEnabled(false);
            audiod2.setEnabled(false);
            audiod3.setEnabled(false);
            audiod1.setVisibility(View.VISIBLE);
            audiod2.setVisibility(View.VISIBLE);
            audiod3.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
           // enable2="1";
        }
        SharedPreferences sharedPreferencese3 = getSharedPreferences("sKey3", MODE_PRIVATE);
        enable3 = sharedPreferencese3.getString("enable3", "");
        if(enable3.equals("1")) {
            sw3.setChecked(true);
            gps.setVisibility(View.VISIBLE);
        }
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    phoneno.setVisibility(View.VISIBLE);
                    checkBox1.setVisibility(View.VISIBLE);
                    checkBox2.setVisibility(View.VISIBLE);
                    checkBox3.setVisibility(View.VISIBLE);
                    enable1 = "1";
                    Toast.makeText(LocationInputC.this, "Switch on"+enable1, Toast.LENGTH_SHORT).show();
                } else {
                    phoneno.setVisibility(View.INVISIBLE);
                    checkBox1.setVisibility(View.INVISIBLE);
                    checkBox2.setVisibility(View.INVISIBLE);
                    checkBox3.setVisibility(View.INVISIBLE);
                    enable1 = "0";
                    Toast.makeText(LocationInputC.this, "Switch off"+enable1, Toast.LENGTH_SHORT).show();
                }
                SharedPreferences sharedPref2 = getSharedPreferences("sKey1", MODE_PRIVATE);
                SharedPreferences.Editor enable = sharedPref2.edit();
                enable.putString("enable1", enable1);
                enable.apply();
            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    audio1.setEnabled(false);
                    audio2.setEnabled(false);
                    audio3.setEnabled(false);
                    audio1.setVisibility(View.VISIBLE);
                    audio2.setVisibility(View.VISIBLE);
                    audio3.setVisibility(View.VISIBLE);
                    audiod1.setEnabled(false);
                    audiod2.setEnabled(false);
                    audiod3.setEnabled(false);
                    audiod1.setVisibility(View.VISIBLE);
                    audiod2.setVisibility(View.VISIBLE);
                    audiod3.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    enable2="1";
                } else {
                    audio1.setVisibility(View.INVISIBLE);
                    audio2.setVisibility(View.INVISIBLE);
                    audio3.setVisibility(View.INVISIBLE);
                    audiod1.setVisibility(View.INVISIBLE);
                    audiod2.setVisibility(View.INVISIBLE);
                    audiod3.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    enable2="0";
                }
                SharedPreferences sharedPref2 = getSharedPreferences("sKey2", MODE_PRIVATE);
                SharedPreferences.Editor enable = sharedPref2.edit();
                enable.putString("enable2", enable2);
                enable.apply();
            }
        });
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    gps.setVisibility(View.VISIBLE);
                        enable3="1";
                } else {
                    gps.setVisibility(View.INVISIBLE);
                    enable3="0";
                    }
                SharedPreferences sharedPref2 = getSharedPreferences("sKey3", MODE_PRIVATE);
                SharedPreferences.Editor enable = sharedPref2.edit();
                enable.putString("enable3", enable3);
                enable.apply();
                 }
            });
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (rb!=null) {
                    if(rb.getText().equals("1")){
                        audio1.setEnabled(true);
                        audiod1.setEnabled(true);
                        Toast.makeText(LocationInputC.this, rb.getText(), Toast.LENGTH_SHORT).show();
                        audio2.setEnabled(false);
                        audiod2.setEnabled(false);
                        audio3.setEnabled(false);
                        audiod3.setEnabled(false);
                        audiod1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View arg0) {
                                if(checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
                                {
                                    Toast.makeText(LocationInputC.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setType("audio/*");
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Open Audio (mp3) file"), RQS_OPEN_AUDIO_MP3);
                                }
                                else
                                {
                                    ActivityCompat.requestPermissions(LocationInputC.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SEND_STORAGE_PERMISSION_REQ);
                                }
                            }
                            });
                    }
                    if(rb.getText().equals("2")){
                        audio2.setEnabled(true);
                        audiod2.setEnabled(true);
                        Toast.makeText(LocationInputC.this, rb.getText(), Toast.LENGTH_SHORT).show();
                        audio1.setEnabled(false);
                        audiod1.setEnabled(false);
                        audio3.setEnabled(false);
                        audiod3.setEnabled(false);
                        audiod2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View arg0) {
                                if(checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
                                {
                                    Toast.makeText(LocationInputC.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setType("audio/*");
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Open Audio (mp3) file"), RQS_OPEN_AUDIO_MP3);
                                }
                                else
                                {
                                    ActivityCompat.requestPermissions(LocationInputC.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SEND_STORAGE_PERMISSION_REQ);
                                }
                            }
                        });
                    }
                    if(rb.getText().equals("3")){
                        audio3.setEnabled(true);
                        audiod3.setEnabled(true);
                        audio1.setEnabled(false);
                        audiod1.setEnabled(false);
                        audio2.setEnabled(false);
                        audiod2.setEnabled(false);
                        audiod3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View arg0) {
                                if(checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
                                {
                                    Toast.makeText(LocationInputC.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setType("audio/*");
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(Intent.createChooser(intent, "Open Audio (mp3) file"), RQS_OPEN_AUDIO_MP3);
                                }
                                else
                                {
                                    ActivityCompat.requestPermissions(LocationInputC.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SEND_STORAGE_PERMISSION_REQ);
                                }
                            }
                        });
                    }
                    Log.d("hi", String.valueOf(rb.getText()));
                    SharedPreferences sharedPref = getSharedPreferences("rbKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("rb", String.valueOf(rb.getText()));
                    editor.apply();
                }
            }
        });
        if(UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));
            SharedPreferences SharedPreferencesb = getSharedPreferences("ci", MODE_PRIVATE);
            ci = SharedPreferencesb.getString("ci", "");
            rs.setTextColor(Color.parseColor(TextInputcolor));
            sf.setTextColor(Color.parseColor(TextInputcolor));
            gt.setTextColor(Color.parseColor(TextInputcolor));
            ea.setTextColor(Color.parseColor(TextInputcolor));
            if (ci.equals("1")) {
                audio1.setBackgroundResource(R.drawable.black_b);
                audio2.setBackgroundResource(R.drawable.black_b);
                audio3.setBackgroundResource(R.drawable.black_b);
                settext1.setBackgroundResource(R.drawable.black_b);
                settext2.setBackgroundResource(R.drawable.black_b);
                settext3.setBackgroundResource(R.drawable.black_b);
                phoneno.setBackgroundResource(R.drawable.black_b);
                meter.setBackgroundResource(R.drawable.black_b);
                mapb.setBackgroundResource(R.drawable.black_b);
                gmap.setBackgroundResource(R.drawable.black_b);
                audio1.setTextColor(Color.parseColor(TextInputcolor));
                audio2.setTextColor(Color.parseColor(TextInputcolor));
                audio3.setTextColor(Color.parseColor(TextInputcolor));
                settext1.setTextColor(Color.parseColor(TextInputcolor));
                settext2.setTextColor(Color.parseColor(TextInputcolor));
                settext3.setTextColor(Color.parseColor(TextInputcolor));
                phoneno.setTextColor(Color.parseColor(TextInputcolor));
                meter.setTextColor(Color.parseColor(TextInputcolor));
                mapb.setTextColor(Color.parseColor(TextInputcolor));
                gmap.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("2")) {
                audio1.setBackgroundResource(R.drawable.grey_b);
                audio2.setBackgroundResource(R.drawable.grey_b);
                audio3.setBackgroundResource(R.drawable.grey_b);
                settext1.setBackgroundResource(R.drawable.grey_b);
                settext2.setBackgroundResource(R.drawable.grey_b);
                settext3.setBackgroundResource(R.drawable.grey_b);
                phoneno.setBackgroundResource(R.drawable.grey_b);
                meter.setBackgroundResource(R.drawable.grey_b);
                mapb.setBackgroundResource(R.drawable.grey_b);
                gmap.setBackgroundResource(R.drawable.grey_b);
                audio1.setTextColor(Color.parseColor(TextInputcolor));
                audio2.setTextColor(Color.parseColor(TextInputcolor));
                audio3.setTextColor(Color.parseColor(TextInputcolor));
                settext1.setTextColor(Color.parseColor(TextInputcolor));
                settext2.setTextColor(Color.parseColor(TextInputcolor));
                settext3.setTextColor(Color.parseColor(TextInputcolor));
                phoneno.setTextColor(Color.parseColor(TextInputcolor));
                meter.setTextColor(Color.parseColor(TextInputcolor));
                mapb.setTextColor(Color.parseColor(TextInputcolor));
                gmap.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("3")) {
                audio1.setBackgroundResource(R.drawable.white_b);
                audio2.setBackgroundResource(R.drawable.white_b);
                audio3.setBackgroundResource(R.drawable.white_b);
                settext1.setBackgroundResource(R.drawable.white_b);
                settext2.setBackgroundResource(R.drawable.white_b);
                settext3.setBackgroundResource(R.drawable.white_b);
                phoneno.setBackgroundResource(R.drawable.white_b);
                meter.setBackgroundResource(R.drawable.white_b);
                mapb.setBackgroundResource(R.drawable.white_b);
                gmap.setBackgroundResource(R.drawable.white_b);
                audio1.setTextColor(Color.parseColor(TextInputcolor));
                audio2.setTextColor(Color.parseColor(TextInputcolor));
                audio3.setTextColor(Color.parseColor(TextInputcolor));
                settext1.setTextColor(Color.parseColor(TextInputcolor));
                settext2.setTextColor(Color.parseColor(TextInputcolor));
                settext3.setTextColor(Color.parseColor(TextInputcolor));
                phoneno.setTextColor(Color.parseColor(TextInputcolor));
                meter.setTextColor(Color.parseColor(TextInputcolor));
                mapb.setTextColor(Color.parseColor(TextInputcolor));
                gmap.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("4")) {
                audio1.setBackgroundResource(R.drawable.red_b);
                audio2.setBackgroundResource(R.drawable.red_b);
                audio3.setBackgroundResource(R.drawable.red_b);
                settext1.setBackgroundResource(R.drawable.red_b);
                settext2.setBackgroundResource(R.drawable.red_b);
                settext3.setBackgroundResource(R.drawable.red_b);
                phoneno.setBackgroundResource(R.drawable.red_b);
                meter.setBackgroundResource(R.drawable.red_b);
                mapb.setBackgroundResource(R.drawable.red_b);
                gmap.setBackgroundResource(R.drawable.red_b);
                audio1.setTextColor(Color.parseColor(TextInputcolor));
                audio2.setTextColor(Color.parseColor(TextInputcolor));
                audio3.setTextColor(Color.parseColor(TextInputcolor));
                settext1.setTextColor(Color.parseColor(TextInputcolor));
                settext2.setTextColor(Color.parseColor(TextInputcolor));
                settext3.setTextColor(Color.parseColor(TextInputcolor));
                phoneno.setTextColor(Color.parseColor(TextInputcolor));
                meter.setTextColor(Color.parseColor(TextInputcolor));
                mapb.setTextColor(Color.parseColor(TextInputcolor));
                gmap.setTextColor(Color.parseColor(TextInputcolor));

            }
        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
        }













    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == RQS_OPEN_AUDIO_MP3) {
                Uri audioFileUri = data.getData();
                SharedPreferences sharedPreferences = getSharedPreferences("rbKey", MODE_PRIVATE);
                String rb = sharedPreferences.getString("rb", "");
                if (rb.equals("1")) {
                    audio1.setText(audioFileUri.getPath());
                    String Audio1 = audio1.getText().toString().trim();
                    if (Audio1.contains("document/audio")) {
                        Toast.makeText(LocationInputC.this, "can't access this path use enternal storage ( /sdcard/ )", Toast.LENGTH_SHORT).show();
                        audio1.setText("");
                    } else {
                        SharedPreferences sharedPref = getSharedPreferences("Audio1", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("Audio1", Audio1);
                        editor.apply();
                        Log.d("locations", Audio1);
                    }
                }
                if (rb.equals("2")) {
                    audio2.setText(audioFileUri.getPath());
                    String Audio2 = audio2.getText().toString().trim();
                    if (Audio2.contains("document/audio")) {
                        Toast.makeText(LocationInputC.this, "can't access this path use enternal storage ( /sdcard/ )", Toast.LENGTH_SHORT).show();
                        audio2.setText("");
                    } else {
                        SharedPreferences sharedPref = getSharedPreferences("Audio2", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("Audio2", Audio2);
                        editor.apply();
                        Log.d("locations", Audio2);
                    }
                }
                if (rb.equals("3")) {
                    audio3.setText(audioFileUri.getPath());
                    String Audio3 = audio3.getText().toString().trim();

                    if (Audio3.contains("document/audio")) {
                        Toast.makeText(LocationInputC.this, "can't access this path use enternal storage ( /sdcard/ )", Toast.LENGTH_SHORT).show();
                        audio3.setText("");
                    } else {
                        SharedPreferences sharedPref = getSharedPreferences("Audio3", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("Audio3", Audio3);
                        editor.apply();
                        Log.d("locations", Audio3);
                    }
                }
            }
        }
    }
    public void childTracker(View v) {
        startActivity(new Intent(getApplicationContext(), ChildInput.class));
        finish();
    }
    public void MapsActivity(View v) {



















        String gmape="1";
        SharedPreferences sharedPrefg = getSharedPreferences("gmape", MODE_PRIVATE);
        SharedPreferences.Editor editorg = sharedPrefg.edit();
        editorg.putString("gmape", gmape);
        editorg.apply();




















        String Settext1 = settext1.getText().toString().trim();
        SharedPreferences sharedPref1 = getSharedPreferences("Settext1", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPref1.edit();
        editor1.putString("Settext1",Settext1);
        editor1.apply();
        String Settext2 = settext2.getText().toString().trim();
        SharedPreferences sharedPref2 = getSharedPreferences("Settext2", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putString("Settext2",Settext2);
        editor2.apply();
        String Settext3 = settext3.getText().toString().trim();
        SharedPreferences sharedPref3 = getSharedPreferences("Settext3", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPref3.edit();
        editor3.putString("Settext3",Settext3);
        editor3.apply();
        String Phoneno = phoneno.getText().toString().trim();
        SharedPreferences sharedPref4 = getSharedPreferences("phKey", MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPref4.edit();
        editor4.putString("Phonenoc", Phoneno);
        Log.d("Phonenoc",Phoneno);
        editor4.apply();
        if(checkBox1.isChecked()){
            Toast.makeText(LocationInputC.this, "1", Toast.LENGTH_SHORT).show();
            CheckBox1="1";
            String CheckBox1 = checkBox1.getText().toString().trim();
            SharedPreferences sharedPref = getSharedPreferences("CheckBox1", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("CheckBox1",CheckBox1);
            editor.apply();
        }
        if(checkBox2.isChecked()) {
            Toast.makeText(LocationInputC.this, "2", Toast.LENGTH_SHORT).show();
            CheckBox2="2";
            String CheckBox2 = checkBox2.getText().toString().trim();
            SharedPreferences sharedPref = getSharedPreferences("CheckBox2", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("CheckBox2",CheckBox2);
            editor.apply();
        }
        if(checkBox3.isChecked()){
            Toast.makeText(LocationInputC.this, "3", Toast.LENGTH_SHORT).show();
            CheckBox3="3";
            String CheckBox3 = checkBox3.getText().toString().trim();
            SharedPreferences sharedPref = getSharedPreferences("CheckBox3", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("CheckBox3",CheckBox3);
            editor.apply();
        }
        User_Radius = ((EditText) findViewById(R.id.meter)).getText().toString();
        Log.d("User_Radius",User_Radius);

        SharedPreferences sharedPreferencese3 = getSharedPreferences("sKey3", MODE_PRIVATE);
        enable3 = sharedPreferencese3.getString("enable3", "");


        SharedPreferences sharedPreferencese = getSharedPreferences("Gpsconfig", MODE_PRIVATE);
        gpsconfig = sharedPreferencese.getString("Gconfig", "");

        if(enable3.equals(""))
        {
            enable3="0";
        }

        Toast.makeText(LocationInputC.this, enable3, Toast.LENGTH_SHORT).show();

        Log.d("enable3",enable3);


        if(enable3.equals("0")){
            Intent i = new Intent(this, CMapsActivity.class);
            i.putExtra("user_geo_input", User_Radius);
            startActivity(i);
            finish();
            Toast.makeText(LocationInputC.this, "map", Toast.LENGTH_SHORT).show();

        }
        else {
            if (enable3.equals("1") && gpsconfig.equals("1")) {
                Toast.makeText(LocationInputC.this, "tracker map", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, CMapsActivity.class);
                i.putExtra("user_geo_input", User_Radius);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(LocationInputC.this, "Set up the gps function or turn off gps tracker", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ChildInput.class));
            }
        }
    }
    private boolean checkPermission(String sendSms) {
        int checkpermission= ContextCompat.checkSelfPermission(this,sendSms);
        return checkpermission == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case SEND_STORAGE_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(LocationInputC.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setType("audio/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Open Audio (mp3) file"), RQS_OPEN_AUDIO_MP3);
                }
                break;
        }
    }
}