package com.android.location_alarm;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    String rb, enable3, enable2, enable1, path, CheckBox1, CheckBox2, CheckBox3, usert1, usert2, alert,snooz="",enable;

    private static final String TAG = "GeofenceBroadcastReceiv";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("rbKey", MODE_PRIVATE);
        rb = sharedPreferences.getString("rb", "");
        Log.d("rdddd", rb);
        SharedPreferences sharedPreferencesg = context.getSharedPreferences("gmape", MODE_PRIVATE);
        String gmape = sharedPreferencesg.getString("gmape", "");
        SharedPreferences sharedPreferencec1 = context.getSharedPreferences("CheckBox1", MODE_PRIVATE);
        CheckBox1 = sharedPreferencec1.getString("CheckBox1", "");
        SharedPreferences sharedPreferencec2 = context.getSharedPreferences("CheckBox2", MODE_PRIVATE);
        CheckBox2 = sharedPreferencec2.getString("CheckBox2", "");
        SharedPreferences sharedPreferencec3 = context.getSharedPreferences("CheckBox3", MODE_PRIVATE);
        CheckBox3 = sharedPreferencec3.getString("CheckBox3", "");
        SharedPreferences sharedPreferencesa = context.getSharedPreferences("Settext1", MODE_PRIVATE);
        String Settext1 = sharedPreferencesa.getString("Settext1", "");
        SharedPreferences sharedPreferencesb = context.getSharedPreferences("Settext2", MODE_PRIVATE);
        String Settext2 = sharedPreferencesb.getString("Settext2", "");
        SharedPreferences sharedPreferencesc = context.getSharedPreferences("Settext3", MODE_PRIVATE);
        String Settext3 = sharedPreferencesc.getString("Settext3", "");
        SharedPreferences sharedPreferencese1 = context.getSharedPreferences("sKey1", MODE_PRIVATE);
        enable1 = sharedPreferencese1.getString("enable1", "");
        SharedPreferences sharedPreferencese2 = context.getSharedPreferences("sKey2", MODE_PRIVATE);
        enable2 = sharedPreferencese2.getString("enable2", "");
        SharedPreferences sharedPreferencese3 = context.getSharedPreferences("tKey3", MODE_PRIVATE);
        enable3 = sharedPreferencese3.getString("tenable3", "");
        SharedPreferences SharedPreferencest1 = context.getSharedPreferences("time1", MODE_PRIVATE);
        usert1 = SharedPreferencest1.getString("time1", "");
        SharedPreferences SharedPreferencest2 = context.getSharedPreferences("time2", MODE_PRIVATE);
        usert2 = SharedPreferencest2.getString("time2", "");
        SharedPreferences sharedPreferencesp = context.getSharedPreferences("phKey", MODE_PRIVATE);
        String Phoneno = sharedPreferencesp.getString("Phonenoc", "");
        SharedPreferences sharedPreferences1 = context.getSharedPreferences("Audio1", MODE_PRIVATE);
        String Audio1 = sharedPreferences1.getString("Audio1", "");
        SharedPreferences sharedPreference = context.getSharedPreferences("snooz", MODE_PRIVATE);
        snooz = sharedPreference.getString("snooz", "");
        Audio1 = Audio1.replaceAll("document/primary:", "");
        if (Audio1.contains("/")) {
            Toast.makeText(context, "got audiio file", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error no audiio file", Toast.LENGTH_SHORT).show();
        }
        path = Audio1;
        Log.d("enable2", Audio1);

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Geofence added", Toast.LENGTH_SHORT).show();
        NotificationHelper notificationHelper = new NotificationHelper(context);
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }
        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        for (Geofence geofence : geofenceList) {
            Log.d(TAG, "onReceive: " + geofence.getRequestId());
        }
//        Location location = geofencingEvent.getTriggeringLocation();
        int transitionType = geofencingEvent.getGeofenceTransition();
      //  if (enable3.equals("0") || gmape.equals("1")) {
        if (enable3.equals("1") && gmape.equals("0")) {
            Toast.makeText(context, "time", Toast.LENGTH_SHORT).show();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm ");
            String formattedDate = df.format(c.getTime());
            if (!usert1.equals("") || !usert2.equals("")) {
                String pattern = "HH:mm";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                String time = usert1;
                String endtime = usert2;
                Date tim = null;
                try {
                    tim = sdf.parse(formattedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date1 = null;
                try {
                    date1 = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = null;
                try {
                    date2 = sdf.parse(endtime);
                } catch (ParseException e) {
                    e.printStackTrace();



                }





















                if (date1.before(tim) && date2.after(tim) && date1.before(date2)) {
                    // Toast.makeText(this, "time is inside", Toast.LENGTH_SHORT).show();
                    switch (transitionType) {
                        case Geofence.GEOFENCE_TRANSITION_ENTER:
                            Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();





                            SharedPreferences sharedPreference1 = context.getSharedPreferences("swkey1", MODE_PRIVATE);
                            enable= sharedPreference1.getString("swkey1", "");
                            if(enable.equals("") ||enable.equals("1")){


                                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_ENTER", Settext1, GMapsActivity.class);
                                if (CheckBox1.equals("1") && enable1.equals("1")) {
                                    SmsManager smsManager1 = SmsManager.getDefault();
                                    smsManager1.sendTextMessage(Phoneno, null, Settext1, null, null);
                                }
                                if (rb.equals("1") && enable2.equals("1")) {
                                    Toast.makeText(context, "sound 1", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(context.getApplicationContext(), Alert.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                } else {
                                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                    } else {
                                        vibrator.vibrate(100);
                                    }
                                    Intent i = new Intent(context.getApplicationContext(), Alert.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                }
                                alert = "1";
                                SharedPreferences sharedPre1 = context.getSharedPreferences("alert", MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sharedPre1.edit();
                                editor1.putString("alert", alert);
                                editor1.apply();


                            }










                            break;
                        case Geofence.GEOFENCE_TRANSITION_DWELL:
                            Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();

                            SharedPreferences sharedPreference2 = context.getSharedPreferences("swkey2", MODE_PRIVATE);
                            enable= sharedPreference2.getString("swkey2", "");
                            if(enable.equals("") || enable.equals("1")){

                                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL", Settext2, GMapsActivity.class);
                                if (CheckBox1.equals("2") && enable1.equals("1")) {
                                    SmsManager smsManager1 = SmsManager.getDefault();
                                    smsManager1.sendTextMessage(Phoneno, null, Settext1, null, null);
                                }
                                if (rb.equals("2") && enable2.equals("1")) {
                                    Toast.makeText(context, "sound 2", Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(context.getApplicationContext(), Alert.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                } else {
                                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                    } else {
                                        vibrator.vibrate(100);
                                    }

                                    Intent i = new Intent(context.getApplicationContext(), Alert.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                }
                                alert = "2";
                                SharedPreferences sharedPre2 = context.getSharedPreferences("alert", MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sharedPre2.edit();
                                editor2.putString("alert", alert);
                                editor2.apply();


                            }





                            break;
                        case Geofence.GEOFENCE_TRANSITION_EXIT:
                            Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();



                            SharedPreferences sharedPreference3 = context.getSharedPreferences("swkey3", MODE_PRIVATE);
                            enable= sharedPreference3.getString("swkey3", "");
                            if(enable.equals("") || enable.equals("1")){

                                notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT", Settext3, GMapsActivity.class);
                                if (CheckBox1.equals("3") && enable1.equals("1")) {
                                    SmsManager smsManager1 = SmsManager.getDefault();
                                    smsManager1.sendTextMessage(Phoneno, null, Settext1, null, null);
                                }
                                if (rb.equals("3") && enable2.equals("1")) {
                                    Toast.makeText(context, "sound 3", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(context.getApplicationContext(), Alert.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                } else {
                                    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                        vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                                    } else {
                                        vibrator.vibrate(100);
                                    }
                                    Intent i = new Intent(context.getApplicationContext(), Alert.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                }
                                alert = "3";
                                SharedPreferences sharedPre3 = context.getSharedPreferences("alert", MODE_PRIVATE);
                                SharedPreferences.Editor editor3 = sharedPre3.edit();
                                editor3.putString("alert", alert);
                                editor3.apply();

                            }





                            break;
                    }
                }else {
                    Toast.makeText(context, "time is outside", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(context, "works ", Toast.LENGTH_SHORT).show();
            switch (transitionType) {
                case Geofence.GEOFENCE_TRANSITION_ENTER:
                    Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();






























                    SharedPreferences sharedPreference1 = context.getSharedPreferences("swkey1", MODE_PRIVATE);
                    enable= sharedPreference1.getString("swkey1", "");
                    if(enable.equals("") ||enable.equals("1")){


                        notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_ENTER", Settext1, CMapsActivity.class);





                        if (CheckBox1.equals("1") && enable1.equals("1")) {
                            SmsManager smsManager1 = SmsManager.getDefault();
                            smsManager1.sendTextMessage(Phoneno, null, Settext1, null, null);
                        }
                        if (rb.equals("1") && enable2.equals("1")) {
                            Toast.makeText(context, "sound 1", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(context.getApplicationContext(), Alert.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        } else {
                            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }
                            Intent i = new Intent(context.getApplicationContext(), Alert.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                        alert="1";
                        SharedPreferences sharedPre1 = context.getSharedPreferences("alert", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPre1.edit();
                        editor1.putString("alert", alert);
                        editor1.apply();
                    }
                    break;
                case Geofence.GEOFENCE_TRANSITION_DWELL:
                    Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreference2 = context.getSharedPreferences("swkey2", MODE_PRIVATE);
                    enable= sharedPreference2.getString("swkey2", "");
                    if(enable.equals("") || enable.equals("1")){
                        notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL", Settext2, CMapsActivity.class);
                        if (CheckBox1.equals("2") && enable1.equals("1")) {
                            SmsManager smsManager1 = SmsManager.getDefault();
                            smsManager1.sendTextMessage(Phoneno, null, Settext1, null, null);
                        }
                        if (rb.equals("2") && enable2.equals("1")) {
                            Toast.makeText(context, "sound 2", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(context.getApplicationContext(), Alert.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        } else {
                            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }
                            Intent i = new Intent(context.getApplicationContext(), Alert.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                        alert="2";
                        SharedPreferences sharedPre2 = context.getSharedPreferences("alert", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = sharedPre2.edit();
                        editor2.putString("alert", alert);
                        editor2.apply();
                    }
                    break;
                case Geofence.GEOFENCE_TRANSITION_EXIT:
                    Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreference3 = context.getSharedPreferences("swkey3", MODE_PRIVATE);
                    enable= sharedPreference3.getString("swkey3", "");
                    if(enable.equals("") || enable.equals("1")){
                        notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_EXIT", Settext3, CMapsActivity.class);
                        if (CheckBox1.equals("3") && enable1.equals("1")) {
                            SmsManager smsManager1 = SmsManager.getDefault();
                            smsManager1.sendTextMessage(Phoneno, null, Settext1, null, null);
                        }
                        if (rb.equals("3") && enable2.equals("1")) {
                            Toast.makeText(context, "sound 3", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(context.getApplicationContext(), Alert.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        } else {
                            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(100);
                            }
                            Intent i = new Intent(context.getApplicationContext(), Alert.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                        alert="3";
                        SharedPreferences sharedPre3 = context.getSharedPreferences("alert", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = sharedPre3.edit();
                        editor3.putString("alert", alert);
                        editor3.apply();
                    }





                    break;
            }
            Toast.makeText(context, "time off", Toast.LENGTH_SHORT).show();
        }
    }
}