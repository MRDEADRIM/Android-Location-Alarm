package com.android.location_alarm;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.geofire.GeoFire;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class GMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private static final String TAG = "GMapsActivity";
    private GoogleMap mMap;
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;
    private String GEOFENCE_ID = "SOME_GEOFENCE_ID",time_text;
    private int FINE_LOCATION_ACCESS_REQUEST_CODE = 10001;
    private int BACKGROUND_LOCATION_ACCESS_REQUEST_CODE = 10002;
    private int GEOFENCE_NUM = 5;
    private float GEOFENCE_RADIUS = 200;
    private DatabaseReference myLocationRef;
    private GeoFire geoFire;
    private int num = 1, gnum = 1;
    private String email, notify = "0";
    String time = "10", snooz = "0";
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    TextView TIME_TEXT;
    //PlaceAutocompleteFragment placeAutoComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_maps);
        TIME_TEXT=findViewById(R.id.time_t);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geofencingClient = LocationServices.getGeofencingClient(this);
        geofenceHelper = new GeofenceHelper(this);
        Intent i = getIntent();
        String User_Radius = i.getStringExtra("user_geo_input");
        try {
            int user_radius = Integer.parseInt(User_Radius);
            Log.d("User_Radius", User_Radius);
            GEOFENCE_RADIUS = user_radius;
        } catch (Exception e) {
            {
                Toast.makeText(GMapsActivity.this, "check the  Radius size", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LocationInputG.class));
                finish();
            }
        }
        SharedPreferences sharedPreferencesg = getSharedPreferences("Gnumber", MODE_PRIVATE);
        String Gnumber = sharedPreferencesg.getString("Gnumber", "");
        GEOFENCE_NUM = Integer.parseInt(Gnumber);
        SharedPreferences sharedPreferences = getSharedPreferences("eKey", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        email = email.replaceAll("@gmail.com", "");
        Log.d("textt", email);


        try {
            SharedPreferences sharedPreference = getSharedPreferences("notify", MODE_PRIVATE);
            notify = sharedPreference.getString("notify", "");


            if (notify.equals("1")) {
                //Alert();
            }
        } catch (Exception e) {

        }











        SharedPreferences SharedPreferencestim = getSharedPreferences("time_text", MODE_PRIVATE);
        time_text= SharedPreferencestim.getString("time_text", "");
        TIME_TEXT.setText(time_text);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng map = new LatLng(10, 90);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(map, 0));
        enableUserLocation();
        mMap.setOnMapLongClickListener(this);

    }

    private void enableUserLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == FINE_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "allow location permission", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == BACKGROUND_LOCATION_ACCESS_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "You can add geofences...", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Background location is  required to run location alarm", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (Build.VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "got background permission", Toast.LENGTH_SHORT).show();
                handleMapLongClick(latLng);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, BACKGROUND_LOCATION_ACCESS_REQUEST_CODE);
                Toast.makeText(this, "Background permission not available", Toast.LENGTH_SHORT).show();
            }
        } else {
            handleMapLongClick(latLng);
        }
    }

    public void clear(View v){
        gnum=1;
        mMap.clear();
    }





    private void handleMapLongClick(LatLng latLng) {
        if (gnum == GEOFENCE_NUM + 1) {
            mMap.clear();
            gnum = 1;
            num = 1;

        } else {
            addMarker(latLng);

            addCircle(latLng, GEOFENCE_RADIUS);
            addGeofence(latLng, GEOFENCE_RADIUS);
            FirebaseDatabase.getInstance()
                    .getReference(email)
                    .child("location")
                    .child(String.valueOf(num))
                    .setValue(latLng)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(GMapsActivity.this, "update", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(GMapsActivity.this, e.getMessage() + "error", Toast.LENGTH_SHORT).show();
                }
            });
            num++;
            gnum++;
            settingGeoFire();
        }
    }

    private void settingGeoFire() {
        myLocationRef = FirebaseDatabase.getInstance().getReference(email);
        geoFire = new GeoFire(myLocationRef);
    }

    private void addGeofence(LatLng latLng, float radius) {
        Geofence geofence = geofenceHelper.getGeofence(GEOFENCE_ID, latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();
        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Geofence Added...");
                        Toast.makeText(GMapsActivity.this, "hi", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String errorMessage = geofenceHelper.getErrorString(e);
                        Log.d(TAG, "onFailure: " + errorMessage);
                    }
                });

    }

    private void addMarker(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        mMap.addMarker(markerOptions);



    }

    private void addCircle(LatLng latLng, float radius) {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.argb(255, 255, 0, 0));
        circleOptions.fillColor(Color.argb(64, 255, 0, 0));
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);







    }


    public void searchLocation(View view) {


        Log.d("staaa", "p");
        EditText locationSearch = (EditText) findViewById(R.id.searchText);
        String location = locationSearch.getText().toString().trim();
        List<Address> addressList = null;


        if (location.equals("")) {
            Toast.makeText(this, "Enter the place ", Toast.LENGTH_SHORT).show();
        } else {

            if (location != null || !location.equals("")) {

                location = location.toLowerCase();


                Geocoder geocoder = new Geocoder(this);
                try {
                    Log.d("staaa", "p2");
                    addressList = geocoder.getFromLocationName(location, 1);

                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    Log.d("latLng", String.valueOf(latLng));
                    // mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                    // mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    if (location.equals("india") || location.equals("usa") || location.equals("china") || location.equals("russia") || location.equals("japan") || location.equals("south korean") || location.equals("afghanistan") || location.equals("south africa") || location.equals("australia")) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4));
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    } else {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    }
                    Toast.makeText(getApplicationContext(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(this, "invalid place please check again", Toast.LENGTH_SHORT).show();
                }


                Log.d("staaa", "p4");

            }

        }

        /*
          +++++++++________________Gradile Script______________________+++++++++++++++

        compile 'com.google.android.gms:play-services-places:11.0.2'

          ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        PlaceAutocompleteFragment placeAutoComplete;

        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.searchText);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place selected: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


         */
    }












    public void Alert()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit ??")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Toast.makeText(GMapsActivity.this, "exit successfully", Toast.LENGTH_SHORT).show();

                        String notify="0";
                        SharedPreferences sharedPre = getSharedPreferences("notify", MODE_PRIVATE);
                        SharedPreferences.Editor enable = sharedPre.edit();
                        enable.putString("notify", notify);
                        enable.apply();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Toast.makeText(GMapsActivity.this, "continue", Toast.LENGTH_SHORT).show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
