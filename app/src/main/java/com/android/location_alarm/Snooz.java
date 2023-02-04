package com.android.location_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class Snooz extends Activity {

    String time="10",Inp_i;
    int i=1;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    String snoozoff="0",isnoozn,snooz,path;
    int snoozn=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snooz);
        SharedPreferences sharedPreferences1 = getSharedPreferences("Audio1", MODE_PRIVATE);
        String Audio1 = sharedPreferences1.getString("Audio1", "");
        Audio1 = Audio1.replaceAll("document/primary:", "");
        if (Audio1.contains("/")) {
            Toast.makeText(Snooz.this, "got audiio file", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Snooz.this, "error no audiio file", Toast.LENGTH_SHORT).show();
        }
        path = Audio1;
        MediaPlayer mPlayer = new MediaPlayer();
        try {

            mPlayer.setDataSource("/mnt/sdcard" + path);
            mPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPlayer.start();
        SharedPreferences sharedPreferencesn = getSharedPreferences("snoozn", MODE_PRIVATE);
        isnoozn = sharedPreferencesn.getString("snoozn", "");
        try {
            snoozn = Integer.parseInt(isnoozn);
        }
        catch(Exception e){
            Toast.makeText(this, "dadasds", Toast.LENGTH_SHORT).show();
        }
        if(snoozn<=3) {
            snoozn = snoozn + 1;
            SharedPreferences sharedPrefz = getSharedPreferences("snoozn", MODE_PRIVATE);
            SharedPreferences.Editor enable = sharedPrefz.edit();
            enable.putString("snoozn", String.valueOf(snoozn));
            enable.apply();
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ALERT")
                .setMessage("Message:" + "message")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Stop", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        mPlayer.reset();
                        snooz="0";
                        SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                        SharedPreferences.Editor enable = sharedPrefz.edit();
                        enable.putString("snooz", snooz);
                        enable.apply();
                        finish();
                    }
                })
        .setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int id) {
                dialog.cancel();
                mPlayer.reset();
                finish();
            }
        });
                final AlertDialog alert = builder.create();
                alert.show();
            }









}