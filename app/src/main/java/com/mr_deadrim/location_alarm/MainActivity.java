package com.mr_deadrim.location_alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LocationListener {

    String Latitude;
    String Longitude;
    String Altitude;
    String LOCATION_LINK;
    TextView GpsTracker;
    LocationManager locationManager;
    NavigationView navigationView;
    String Name, ConnectPassword, HostPassword, Email, Connect, Host;
    EditText name, connect_password, host_password;
    TextView host_name, bar_host_0;
    Button connect, bar_connect, bar_host, host;
    DatabaseReference DataBase;
    ImageView cancel, map;
    ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences gps_name = getSharedPreferences("gps_name", MODE_PRIVATE);
        Name = gps_name.getString("gps_name", "");
        SharedPreferences gps_connect_password = getSharedPreferences("gps_connect_password", MODE_PRIVATE);
        ConnectPassword = gps_connect_password.getString("gps_connect_password", "");
        SharedPreferences gps_host_password = getSharedPreferences("gps_host_password", MODE_PRIVATE);
        HostPassword = gps_host_password.getString("gps_host_password", "");
        SharedPreferences email = getSharedPreferences("email", MODE_PRIVATE);
        Email = email.getString("email", "");
        SharedPreferences connect1 = getSharedPreferences("Connect", MODE_PRIVATE);
        Connect = connect1.getString("Connect", "");
        SharedPreferences host2 = getSharedPreferences("Host", MODE_PRIVATE);
        Host = host2.getString("Host", "");
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        GpsTracker = header.findViewById(R.id.textView12);
        map = header.findViewById(R.id.imageView5);
        map.setVisibility(View.INVISIBLE);

        GpsTracker.setText(Html.fromHtml("STATUS : "+"<font color=\"#EE0000\">"+"NOT CONNECTED"+"</font>"
                + "<br>" + "<br>" + "user:" + Name
                + "<br>" + "<br>" + "password:" + ConnectPassword
                + "<br>" + "<br>" + "Latitude:" + "-"
                + "<br>" + "<br>" + "Longitude:" + "-"
                + "<br>" + "<br>" + "Altitude:" + "-"
                + "<br>"));
        if (Connect.equals("CONNECT")) {
            map.setVisibility(View.VISIBLE);
            Connected();
        }
        if (Host.equals("HOST")) {
            getLocation();
        }
        /*
        if(Connect.equals("CONNECT") && Host.equals("HOST")){
            GpsTracker.setText(Html.fromHtml("STATUS : "+"<font color=\"#00CA4E\">"+"CONNECTED,HOSTING"+"</font>"
                    + "<br>" + "<br>" + "user:" + Name
                    + "<br>" + "<br>" + "password:" + ConnectPassword
                    + "<br>" + "<br>" + "Latitude:" + "-"
                    + "<br>" + "<br>" + "Longitude:" + "-"
                    + "<br>" + "<br>" + "Altitude:" + "-"
                    + "<br>"));
        }
         */
        statusCheck();
    }
    @SuppressLint("MissingPermission")//input location to firebase
    private void getLocation() {
        if(Host.equals("HOST")) {
            try {
                locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, (android.location.LocationListener) MainActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (Host.equals("HOSTED")) {
            if (locationManager != null) {
                locationManager.removeUpdates(this);
            }
            hosted();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(MainActivity.this, "firebase running", Toast.LENGTH_SHORT).show();
        Map<String, Object> GPS = new HashMap<>();
        GPS.put("Altitude", location.getAltitude());
        GPS.put("Latitude", location.getLatitude());
        GPS.put("Longitude", location.getLongitude());
        FirebaseDatabase.getInstance()
                .getReference("Android Location Alarm")
                .child(Email)
                .child("GPS")
                .setValue(GPS)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "update", Toast.LENGTH_SHORT).show();
                        hosted();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage() + "error", Toast.LENGTH_SHORT).show();
            }
        });
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
    @SuppressLint("WrongConstant")
    public void ShowSlide(View v) {
        DrawerLayout a = (DrawerLayout) findViewById(R.id.drawer_layout);
        a.openDrawer(Gravity.END);
    }
    public void Settings(View v) {
        startActivity(new Intent(this, Settings.class));
    }
    public void MapView(View v) {
        LOCATION_LINK = "maps.google.com/?q=" + Latitude + "," + Longitude;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://" + LOCATION_LINK));
        startActivity(i);
    }
    public void Gps(View v) {
        GpsBroadcast();
        Toast.makeText(MainActivity.this, "gpt under updating", Toast.LENGTH_SHORT).show();
    }
    public void LocationInput(View v) {
        startActivity(new Intent(this, LocationInput.class));
    }
    public void Exit(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit ?")
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item:
                Toast.makeText(MainActivity.this, "Info", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Info.class));
                finish();
                return true;
            case R.id.item1:
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                return true;
            case R.id.item2:
                Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Settings.class));
                finish();
                return true;
            case R.id.item3:
                Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            NoMessageGps();
        }
    }
    private void NoMessageGps() {
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Exit ?")
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
    private void GpsBroadcast() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        View dialog_layout = getLayoutInflater().inflate(R.layout.activity_gps_input, null);
        progress_bar = dialog_layout.findViewById(R.id.progressBar);
        progress_bar.setVisibility(View.INVISIBLE);
        name = (EditText) dialog_layout.findViewById(R.id.Input_Name);
        connect_password = (EditText) dialog_layout.findViewById(R.id.Input_Connect_Password);
        host_password = (EditText) dialog_layout.findViewById(R.id.Input_Host_Password);
        host_password.setVisibility(View.INVISIBLE);
        host_name = (TextView) dialog_layout.findViewById(R.id.textView15);
        connect = (Button) dialog_layout.findViewById(R.id.button8);
        bar_connect = (Button) dialog_layout.findViewById(R.id.button18);
        host = (Button) dialog_layout.findViewById(R.id.button6);
        bar_host = (Button) dialog_layout.findViewById(R.id.button17);
        bar_host_0 = (TextView) dialog_layout.findViewById(R.id.textView9);
        cancel = (ImageView) dialog_layout.findViewById(R.id.button7);
        host_name.setText(Email);
        bar_host.setBackgroundResource(R.drawable.button_black);
        bar_host_0.setBackgroundResource(android.R.color.black);
        bar_connect.setBackgroundResource(R.drawable.null_b);
        bar_host.getBackground().setAlpha(90);
        bar_host_0.getBackground().setAlpha(90);
        host_name.setVisibility(View.INVISIBLE);
        name.setText(Name);
        connect_password.setText(ConnectPassword);
        host_password.setText(HostPassword);
        if (Connect.equals("CONNECT")) {
            connect.setText("CONNECTED");
            bar_connect.setTextColor(Color.parseColor("#00CA4E"));
        }
        bar_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar_connect.setBackgroundResource(R.drawable.null_b);
                bar_host.setBackgroundResource(R.drawable.button_black);
                bar_host.getBackground().setAlpha(90);
                connect.setVisibility(View.VISIBLE);
                host.setVisibility(View.INVISIBLE);
                name.setVisibility(View.VISIBLE);
                connect_password.setVisibility(View.VISIBLE);
                host_password.setVisibility(View.INVISIBLE);
                host_name.setVisibility(View.INVISIBLE);
            }
        });
        bar_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar_host.setBackgroundResource(R.drawable.null_b);
                bar_connect.setBackgroundResource(R.drawable.button_black);
                bar_connect.getBackground().setAlpha(90);
                host.setVisibility(View.VISIBLE);
                connect.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);
                connect_password.setVisibility(View.INVISIBLE);
                host_password.setVisibility(View.VISIBLE);
                host_name.setVisibility(View.VISIBLE);
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                ConnectPassword = connect_password.getText().toString();
                Connect = connect.getText().toString();
                Toast.makeText(MainActivity.this, Connect, Toast.LENGTH_SHORT).show();
                SharedPreferences connect1 = getSharedPreferences("Connect", MODE_PRIVATE);
                SharedPreferences.Editor enable_connect = connect1.edit();
                enable_connect.putString("Connect", Connect);
                enable_connect.apply();
                SharedPreferences gps_name = getSharedPreferences("gps_name", MODE_PRIVATE);
                SharedPreferences.Editor enable_name = gps_name.edit();
                enable_name.putString("gps_name", Name);
                enable_name.apply();
                SharedPreferences gps_connect_password = getSharedPreferences("gps_connect_password", MODE_PRIVATE);
                SharedPreferences.Editor enable_password1 = gps_connect_password.edit();
                enable_password1.putString("gps_connect_password", ConnectPassword);
                enable_password1.apply();
                SharedPreferences gps_host_password = getSharedPreferences("gps_host_password", MODE_PRIVATE);
                SharedPreferences.Editor enable_password2 = gps_host_password.edit();
                enable_password2.putString("gps_host_password", HostPassword);
                enable_password2.apply();
                DataBase = FirebaseDatabase.getInstance().getReference("Android Location Alarm");
                DataBase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            String password = dataSnapshot.child(Name).child("PASSWORD").child("Gps").getValue().toString();
                            Toast.makeText(MainActivity.this, "password :" + password, Toast.LENGTH_SHORT).show();
                            if (ConnectPassword.equals(password)) {
                                Toast.makeText(MainActivity.this, "connected successfully", Toast.LENGTH_SHORT).show();
                                if (Connect.equals("CONNECT") || Connect.equals("CONNECTED")) {
                                    Connected();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "can't connect", Toast.LENGTH_SHORT).show();
                                Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "can't connect", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Incorrect username", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "database error...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                HostPassword = host_password.getText().toString();
                Host = host.getText().toString();
                SharedPreferences host2 = getSharedPreferences("Host", MODE_PRIVATE);
                SharedPreferences.Editor enable_host = host2.edit();
                enable_host.putString("Host", Host);
                enable_host.apply();
                FirebaseDatabase.getInstance().getReference("Android Location Alarm").child(Email).child("PASSWORD").child("Gps").setValue(HostPassword);
                SharedPreferences gps_name = getSharedPreferences("gps_name", MODE_PRIVATE);
                SharedPreferences.Editor enable_name = gps_name.edit();
                enable_name.putString("gps_name", Name);
                enable_name.apply();
                SharedPreferences gps_connect_password = getSharedPreferences("gps_connect_password", MODE_PRIVATE);
                SharedPreferences.Editor enable_password1 = gps_connect_password.edit();
                enable_password1.putString("gps_connect_password", ConnectPassword);
                enable_password1.apply();
                SharedPreferences gps_host_password = getSharedPreferences("gps_host_password", MODE_PRIVATE);
                SharedPreferences.Editor enable_password2 = gps_host_password.edit();
                enable_password2.putString("gps_host_password", HostPassword);
                enable_password2.apply();
                Toast.makeText(MainActivity.this, Host, Toast.LENGTH_SHORT).show();
                if (Host.equals("HOST") || Host.equals("HOSTED")) {
                    progress_bar.setVisibility(View.VISIBLE);
                    getLocation();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                ConnectPassword = connect_password.getText().toString();
                HostPassword = host_password.getText().toString();
                SharedPreferences gps_name = getSharedPreferences("gps_name", MODE_PRIVATE);
                SharedPreferences.Editor enable_name = gps_name.edit();
                enable_name.putString("gps_name", Name);
                enable_name.apply();
                SharedPreferences gps_connect_password = getSharedPreferences("gps_connect_password", MODE_PRIVATE);
                SharedPreferences.Editor enable_password1 = gps_connect_password.edit();
                enable_password1.putString("gps_connect_password", ConnectPassword);
                enable_password1.apply();
                SharedPreferences gps_host_password = getSharedPreferences("gps_host_password", MODE_PRIVATE);
                SharedPreferences.Editor enable_password2 = gps_host_password.edit();
                enable_password2.putString("gps_host_password", HostPassword);
                enable_password2.apply();
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            }
        });
        alertDialog.setView(dialog_layout);
        alertDialog.show();
    }
    public void Connected() {
        DataBase = FirebaseDatabase.getInstance().getReference("Android Location Alarm").child(Name);
        DataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (Connect.equals("CONNECT")) {
                    try {
                        Altitude = dataSnapshot.child("GPS").child("Altitude").getValue().toString();
                        Latitude = dataSnapshot.child("GPS").child("Latitude").getValue().toString();
                        Longitude = dataSnapshot.child("GPS").child("Longitude").getValue().toString();
                        GpsTracker.setText(Html.fromHtml("STATUS : "+"<font color=\"#00CA4E\">"+"CONNECTED"+"</font>"
                                + "<br>" + "<br>" + "user:" + Name
                                + "<br>" + "<br>" + "password:" + ConnectPassword
                                + "<br>" + "<br>" + "Latitude:" + Latitude
                                + "<br>" + "<br>" + "Longitude:" + Longitude
                                + "<br>" + "<br>" + "Altitude:" + Altitude
                                + "<br>"));
                    }catch(Exception e){
                        Toast.makeText(MainActivity.this, "error1", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        connect.setText("CONNECTED");
                        map.setVisibility(View.VISIBLE);
                        bar_connect.setTextColor(Color.parseColor("#00CA4E"));
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "has been connected1", Toast.LENGTH_SHORT).show();
                    }
                }
                if (Connect.equals("CONNECTED") && !Host.equals("HOST")) {
                    try {
                        GpsTracker.setText(Html.fromHtml("STATUS : "+"<font color=\"#EE0000\">"+"NOT CONNECTED"+"</font>"
                                + "<br>" + "<br>" + "user:" + Name
                                + "<br>" + "<br>" + "password:" + ConnectPassword
                                + "<br>" + "<br>" + "Latitude:" + "-"
                                + "<br>" + "<br>" + "Longitude:" + "-"
                                + "<br>" + "<br>" + "Altitude:" + "-"
                                + "<br>"));
                    } catch (Exception v) {
                        Toast.makeText(MainActivity.this, "error2", Toast.LENGTH_SHORT).show();
                    }
                    try {
                        connect.setText("CONNECT");
                        map.setVisibility(View.INVISIBLE);
                        bar_connect.setTextColor(Color.parseColor("#ffffff"));
                    }catch(Exception e){
                        Toast.makeText(MainActivity.this, "has been connected2", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "database error...", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void hosted() {
        if (Host.equals("HOST")) {
            host.setText("HOSTED");
            progress_bar.setVisibility(View.INVISIBLE);
            bar_host.setTextColor(Color.parseColor("#00CA4E"));
            GpsTracker.setText(Html.fromHtml("STATUS : "+"<font color=\"#00CA4E\">"+"HOST-RUNNING"+"</font>"
                    + "<br>" + "<br>" + "user:" + Email
                    + "<br>" + "<br>" + "password:" + HostPassword
                    + "<br>"));
        }
        if (Host.equals("HOSTED") && !Connect.equals("CONNECT")) {
            host.setText("HOST");
            progress_bar.setVisibility(View.INVISIBLE);
            bar_host.setTextColor(Color.parseColor("#ffffff"));
            GpsTracker.setText(Html.fromHtml("STATUS : "+"<font color=\"#EE0000\">"+"HOST-STOPPED"+"</font>"
                    + "<br>" + "<br>" + "user:" + Email
                    + "<br>" + "<br>" + "password:" + HostPassword
                    + "<br>"));
        }
    }
    public void Copy(View v) {
        GpsTracker = findViewById(R.id.textView12);
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(GpsTracker.getText());
        Toast.makeText(MainActivity.this, "copied", Toast.LENGTH_SHORT).show();
    }
}