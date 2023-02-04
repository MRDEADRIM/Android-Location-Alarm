package com.android.location_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final static int SEND_SMS_PERMISSION_REQ=1;
    String BarInputcolor="", TextInputcolor="",BgInputcolor="",UserColorStatus,StatusBar,ci;
    View view;
    Button La,Cl,Gc,Is,Gt,Es,Ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        La=findViewById(R.id.button3);
        Cl=findViewById(R.id.button);
        Gc=findViewById(R.id.button7);
        Is=findViewById(R.id.button9);
        Gt=findViewById(R.id.button2);
        Es=findViewById(R.id.button5);
        Ex=findViewById(R.id.button10);
       // android:inputType="number"
       // android:maxLength="3"

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
            if (ci.equals("1")) {
                La.setBackgroundResource(R.drawable.black_b);
                Cl.setBackgroundResource(R.drawable.black_b);
                Gc.setBackgroundResource(R.drawable.black_b);
                Is.setBackgroundResource(R.drawable.black_b);
                Gt.setBackgroundResource(R.drawable.black_b);
                Es.setBackgroundResource(R.drawable.black_b);
                Ex.setBackgroundResource(R.drawable.black_b);
                La.setTextColor(Color.parseColor(TextInputcolor));
                Cl.setTextColor(Color.parseColor(TextInputcolor));
                Gc.setTextColor(Color.parseColor(TextInputcolor));
                Is.setTextColor(Color.parseColor(TextInputcolor));
                Gt.setTextColor(Color.parseColor(TextInputcolor));
                Es.setTextColor(Color.parseColor(TextInputcolor));
                Ex.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("2")) {
                La.setBackgroundResource(R.drawable.grey_b);
                Cl.setBackgroundResource(R.drawable.grey_b);
                Gc.setBackgroundResource(R.drawable.grey_b);
                Is.setBackgroundResource(R.drawable.grey_b);
                Gt.setBackgroundResource(R.drawable.grey_b);
                Es.setBackgroundResource(R.drawable.grey_b);
                Ex.setBackgroundResource(R.drawable.grey_b);
                La.setTextColor(Color.parseColor(TextInputcolor));
                Cl.setTextColor(Color.parseColor(TextInputcolor));
                Gc.setTextColor(Color.parseColor(TextInputcolor));
                Is.setTextColor(Color.parseColor(TextInputcolor));
                Gt.setTextColor(Color.parseColor(TextInputcolor));
                Es.setTextColor(Color.parseColor(TextInputcolor));
                Ex.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("3")) {
                La.setBackgroundResource(R.drawable.white_b);
                Cl.setBackgroundResource(R.drawable.white_b);
                Gc.setBackgroundResource(R.drawable.white_b);
                Is.setBackgroundResource(R.drawable.white_b);
                Gt.setBackgroundResource(R.drawable.white_b);
                Es.setBackgroundResource(R.drawable.white_b);
                Ex.setBackgroundResource(R.drawable.white_b);
                La.setTextColor(Color.parseColor(TextInputcolor));
                Cl.setTextColor(Color.parseColor(TextInputcolor));
                Gc.setTextColor(Color.parseColor(TextInputcolor));
                Is.setTextColor(Color.parseColor(TextInputcolor));
                Gt.setTextColor(Color.parseColor(TextInputcolor));
                Es.setTextColor(Color.parseColor(TextInputcolor));
                Ex.setTextColor(Color.parseColor(TextInputcolor));
            }
            if (ci.equals("4")) {
                La.setBackgroundResource(R.drawable.red_b);
                Cl.setBackgroundResource(R.drawable.red_b);
                Gc.setBackgroundResource(R.drawable.red_b);
                Is.setBackgroundResource(R.drawable.red_b);
                Gt.setBackgroundResource(R.drawable.red_b);
                Es.setBackgroundResource(R.drawable.red_b);
                Ex.setBackgroundResource(R.drawable.red_b);
                La.setTextColor(Color.parseColor(TextInputcolor));
                Cl.setTextColor(Color.parseColor(TextInputcolor));
                Gc.setTextColor(Color.parseColor(TextInputcolor));
                Is.setTextColor(Color.parseColor(TextInputcolor));
                Gt.setTextColor(Color.parseColor(TextInputcolor));
                Es.setTextColor(Color.parseColor(TextInputcolor));
                Ex.setTextColor(Color.parseColor(TextInputcolor));
            }
        }else
        {
            Toast.makeText(MainActivity.this, "default", Toast.LENGTH_SHORT).show();
        }
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            Toast.makeText(MainActivity.this, "Permission grand..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ);
        }
        statusCheck();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){

            case R.id.item:
                Toast.makeText(MainActivity.this, "Info", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Info.class));
                finish();
                return true;
            case R.id.item1:
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
                return true;
            case R.id.item2:
                Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                finish();
                return true;
            case R.id.item3:
                Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Turn on GPS ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "no permission turn on the permission", Toast.LENGTH_SHORT).show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    public void LocationInputC(View v){
        startActivity(new Intent(getApplicationContext(), LocationInputC.class));
    }
    public void LocationInputG(View v){
        startActivity(new Intent(getApplicationContext(), LocationInputG.class));
    }
    public void EmergencyService(View v) {
        startActivity(new Intent(getApplicationContext(), EmergencyService.class));
    }
    public void SmsInput(View v) {
        startActivity(new Intent(getApplicationContext(), SmsInput.class));
    }
    public void ChatInput(View v) {
       startActivity(new Intent(getApplicationContext(), ChatInput.class));
    }
    public void GpsManager(View v) {
        startActivity(new Intent(getApplicationContext(), ChildInput.class));
    }
    public void Exit(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit ??")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Toast.makeText(MainActivity.this, "exit successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "continue", Toast.LENGTH_SHORT).show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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
                    Toast.makeText(MainActivity.this, "Permission grand..", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){


            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Exit ??")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            Toast.makeText(MainActivity.this, "exit successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                            Toast.makeText(MainActivity.this, "continue", Toast.LENGTH_SHORT).show();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}