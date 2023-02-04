package com.android.location_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;


import android.location.Geocoder;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;


import android.telephony.SmsManager;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class View extends AppCompatActivity implements LocationListener {

    private final static int SEND_SMS_PERMISSION_REQ = 1;
    private String USER_TEXT="";
    private String USER_LOCATION="";
    private String LOCATION_LINK="";
    private String USER_TIME="";
    private String USER_SMS="";
    int limit=0;
    TextView textView_location, textView_coordinate,textView_time,viewt,head;
    LocationManager locationManager;
    Button redirect;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar;
    android.view.View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);






        head=findViewById(R.id.textView5);
        viewt=findViewById(R.id.view);


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

        textView_location = findViewById(R.id.text_location);
        textView_coordinate = findViewById(R.id.text_coordinate);
        redirect=findViewById(R.id.redirect);
        textView_time=findViewById(R.id.text_time);
        redirect.setEnabled(false);
        SharedPreferences sharedPreferences = getSharedPreferences("textKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value", "");
        USER_TEXT= value;
        getLocation();






        if(UserColorStatus.equals("1")) {
            getWindow().setStatusBarColor(Color.parseColor(StatusBar));
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(BarInputcolor)));
            getSupportActionBar().setTitle(Html.fromHtml("<font color=" + TextInputcolor + ">" + getString(R.string.app_name) + "</font>"));
            view.setBackgroundColor(Color.parseColor(BgInputcolor));



            viewt.setTextColor(Color.parseColor(TextInputcolor));
            textView_location.setTextColor(Color.parseColor(TextInputcolor));
            textView_coordinate.setTextColor(Color.parseColor(TextInputcolor));
            textView_time.setTextColor(Color.parseColor(TextInputcolor));
            head.setTextColor(Color.parseColor(TextInputcolor));




        }else
        {
            Toast.makeText(this, "default", Toast.LENGTH_SHORT).show();
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
            case SEND_SMS_PERMISSION_REQ:
                if(grantResults.length>0 &&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(View.this, "permission grand", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, View.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
        public void onLocationChanged (Location location){
        //error
        Log.d("pass", "on locationchange :pass");
            Toast.makeText(this, "running...", Toast.LENGTH_SHORT).show();
            try {
                Geocoder geocoder = new Geocoder(View.this, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String address = addresses.get(0).getAddressLine(0);
                textView_location.setText("Address:" + address);
                USER_LOCATION = "Address:" + address ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        redirect.setEnabled(true);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm " + "\n" + "dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        textView_time.setText(formattedDate);
        textView_coordinate.setText("Lat:" + location.getLatitude() + "\n" + "Long:" + location.getLongitude());
        LOCATION_LINK = "maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
        USER_TIME = "Time :" + formattedDate;
        redirect.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://"+LOCATION_LINK));
                startActivity(i);
            }
        });
        EmergencySDatabaseHelper emergencySDatabaseHelper = new EmergencySDatabaseHelper(this);
        TextView textView = findViewById(R.id.view);
        Cursor cursor = emergencySDatabaseHelper.viewData();
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            stringBuilder.append(cursor.getString(2) + "\n");
            String.valueOf(stringBuilder);
        }
        Toast.makeText(View.this, "checking", Toast.LENGTH_SHORT).show();
        textView.setText(stringBuilder);
        String speech = String.valueOf(stringBuilder);
        String[] result = speech.split("\\s");
        if (USER_TEXT == null || USER_TEXT.equals(" ") || USER_TEXT.equals(""))
        {
            USER_TEXT = "Emergency service requires".trim();
        } else {
            Toast.makeText(View.this, "sms text", Toast.LENGTH_SHORT).show();
        }
        Log.d("lim", String.valueOf(limit));
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            if (limit == 0 ) {
                for (int num = 0; num < result.length; num++) {
                    try {
                        if (result[num] == null) {
                            Toast.makeText(View.this, "no data", Toast.LENGTH_SHORT).show();
                            Log.d("errors", result[num]);

                        }
                        Toast.makeText(View.this, "send :" + result[num], Toast.LENGTH_SHORT).show();
                        Log.d("works", result[num]);

                            USER_SMS = USER_TIME + "\n" + USER_TEXT + "\n" + USER_LOCATION + "\n" + LOCATION_LINK;
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(result[num].trim(), null, USER_SMS, null, null);
                        limit = 1;
                    } catch (Exception err)
                    {
                        Toast.makeText(View.this, "GO BACK AND ADD DATA FIRST", Toast.LENGTH_SHORT).show();
                        limit = 1;
                    }
                    while (cursor.moveToNext()) {
                        stringBuilder.append(cursor.getString(2) + "\n");
                        String.valueOf(stringBuilder);
                    }
                }
          }
            else {
              Toast.makeText(View.this, "finish", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
            Toast.makeText(View.this, "Checking for permission", Toast.LENGTH_SHORT).show();
        }
        //end error
    }
    @Override
    public void onProviderDisabled(String provider) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}