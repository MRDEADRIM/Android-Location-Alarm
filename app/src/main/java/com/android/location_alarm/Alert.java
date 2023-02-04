package com.android.location_alarm;

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
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Alert extends Activity {
    String path, enable2, alert, message,time="10";
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    String snooz="0",snoozoff="0",snoozn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        SharedPreferences sharedPreferencese2 = getSharedPreferences("sKey2", MODE_PRIVATE);
        enable2 = sharedPreferencese2.getString("enable2", "");
        Log.d("enable2rrr",enable2);

        if (enable2.equals("")) {
            enable2 = "0";
        }

        SharedPreferences sharedPreferences = getSharedPreferences("alert", Context.MODE_PRIVATE);
        alert = sharedPreferences.getString("alert", "");
        Log.d("alerte", alert);


        if (alert.equals("1")) {
            SharedPreferences sharedPreferencesa = getSharedPreferences("Settext1", MODE_PRIVATE);
            message = sharedPreferencesa.getString("Settext1", "");

            if (enable2.equals("0")) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("ALERT")
                        .setMessage("Message:" + message)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                snooz="0";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                finish();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            } else {
                SharedPreferences sharedPreferences1 = getSharedPreferences("Audio1", MODE_PRIVATE);
                String Audio1 = sharedPreferences1.getString("Audio1", "");
                Audio1 = Audio1.replaceAll("document/primary:", "");
                if (Audio1.contains("/")) {
                    Toast.makeText(Alert.this, "got audiio file", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Alert.this, "error no audiio file", Toast.LENGTH_SHORT).show();
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
                final AlertDialog.Builder abuilder = new AlertDialog.Builder(Alert.this);
                abuilder.setTitle("ALERT")
                        .setMessage("message:" + message)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_lock_idle_alarm)
                        .setPositiveButton("snooze", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                mPlayer.reset();
                                snooz="1";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                Snooz();
                                snoozn="0";
                                SharedPreferences sharedPrefzn = getSharedPreferences("snoozn", MODE_PRIVATE);
                                SharedPreferences.Editor enable2 = sharedPrefzn.edit();
                                enable2.putString("snoozn", snoozn);
                                enable2.apply();
                            }
                        })
                        .setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                mPlayer.reset();
                                snooz="0";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                Snooz();
                                Log.d("statusw","work:selected2");
                            }
                        });
                final AlertDialog alertDialog = abuilder.create();
                alertDialog.show();
            }
        }
        if (alert.equals("2")) {
            SharedPreferences sharedPreferencesb = getSharedPreferences("Settext2", MODE_PRIVATE);
            message = sharedPreferencesb.getString("Settext2", "");
            Log.d("vsvsdfa",message);
            if (enable2.equals("0")) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("ALERT")
                        .setMessage("Message:" + message)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                snooz="0";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                finish();
                            }
                        });

                final AlertDialog alert = builder.create();
                alert.show();
            } else {
                SharedPreferences sharedPreferences2 = getSharedPreferences("Audio2", MODE_PRIVATE);
                String Audio2 = sharedPreferences2.getString("Audio2", "");
                Audio2 = Audio2.replaceAll("document/primary:", "");
                if (Audio2.contains("/")) {
                    Toast.makeText(Alert.this, "got audiio file", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Alert.this, "error no audiio file", Toast.LENGTH_SHORT).show();
                }
                path = Audio2;
                MediaPlayer mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource("/mnt/sdcard" + path);
                    mPlayer.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPlayer.start();
                final AlertDialog.Builder abuilder = new AlertDialog.Builder(Alert.this);
                abuilder.setTitle("ALERT")
                        .setMessage("message:" + message)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_lock_idle_alarm)
                        .setPositiveButton("snooze", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                mPlayer.reset();
                                snooz="1";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                Snooz();
                                snoozn="0";
                                SharedPreferences sharedPrefzn = getSharedPreferences("snoozn", MODE_PRIVATE);
                                SharedPreferences.Editor enable2 = sharedPrefzn.edit();
                                enable2.putString("snoozn", snoozn);
                                enable2.apply();
                            }
                        })
                        .setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                mPlayer.reset();
                                snooz="0";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                Snooz();
                                Log.d("statusw","work:selected2");
                            }
                        });
                final AlertDialog alertDialog = abuilder.create();
                alertDialog.show();
            }
        }
        if (alert.equals("3")) {
            SharedPreferences sharedPreferencesc = getSharedPreferences("Settext3", MODE_PRIVATE);
            message = sharedPreferencesc.getString("Settext3", "");
            Log.d("vsvsdfa",message);
            if (enable2.equals("0")) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("ALERT")
                        .setMessage("Message:" + message)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                snooz="0";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                finish();
                            }
                        });
                final AlertDialog alert = builder.create();
                alert.show();
            } else {
                SharedPreferences sharedPreferences3 = getSharedPreferences("Audio3", MODE_PRIVATE);
                String Audio3 = sharedPreferences3.getString("Audio3", "");
                Audio3 = Audio3.replaceAll("document/primary:", "");
                if (Audio3.contains("/")) {
                    Toast.makeText(Alert.this, "got audiio file", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Alert.this, "error no audiio file", Toast.LENGTH_SHORT).show();
                }
                path = Audio3;
                MediaPlayer mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource("/mnt/sdcard" + path);
                    mPlayer.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPlayer.start();
                final AlertDialog.Builder abuilder = new AlertDialog.Builder(Alert.this);
                abuilder.setTitle("ALERT")
                        .setMessage("message:" + message)
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_lock_idle_alarm)
                        .setPositiveButton("snooze", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                mPlayer.reset();
                                snooz="1";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                Snooz();
                                snoozn="0";
                                SharedPreferences sharedPrefzn = getSharedPreferences("snoozn", MODE_PRIVATE);
                                SharedPreferences.Editor enable2 = sharedPrefzn.edit();
                                enable2.putString("snoozn", snoozn);
                                enable2.apply();
                            }
                        })
                        .setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                                mPlayer.reset();
                                snooz="0";
                                SharedPreferences sharedPrefz = getSharedPreferences("snooz", MODE_PRIVATE);
                                SharedPreferences.Editor enable = sharedPrefz.edit();
                                enable.putString("snooz", snooz);
                                enable.apply();
                                Snooz();
                                Log.d("statusw","work:selected2");
                            }
                        });
                final AlertDialog alertDialog = abuilder.create();
                alertDialog.show();
            }
        }
    }
    private void Snooz() {
        Log.d("statusw",snooz);
        if(snooz.equals("1")) {
            if (!time.equals("")) {
                int i = Integer.parseInt(time);
                intent = new Intent(this, SnoozBroadcastReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(
                        this.getApplicationContext(), 280192, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + (i * 1000), 10000, pendingIntent);
                Toast.makeText(this, "Alarm will set in " + i + " seconds", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Please Provide time ", Toast.LENGTH_SHORT).show();
            }
        }else{
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Alarm Disabled !!",Toast.LENGTH_LONG).show();
            }
        }
        finish();
    }
}