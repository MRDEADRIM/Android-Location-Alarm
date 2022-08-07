package com.mr_deadrim.location_alarm;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mr_deadrim.location_alarm.databinding.ActivityMapsBinding;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final int FINE_LOCATION_ACCESS_REQUEST_CODE = 10001;
    private final int BACKGROUND_LOCATION_ACCESS_REQUEST_CODE = 10002;
    private final int GEOFENCE_RADIUS = 200;
    private final int GEOFENCE_NUM = 5;
    private int gnum = 0,num=GEOFENCE_NUM;
    private final String GEOFENCE_ID = "SOME_GEOFENCE_ID";
    private GeofencingClient geofencingClient;
    private GeofenceHelper geofenceHelper;
    String TAG = "MapsActivity";
    ImageView CloseBtn;
    TextView SIZE,NUMBER,TRACKER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geofencingClient = LocationServices.getGeofencingClient(this);
        geofenceHelper = new GeofenceHelper(this);
        CloseBtn=findViewById(R.id.close);
        SIZE=findViewById(R.id.size);
        NUMBER=findViewById(R.id.number);
        TRACKER=findViewById(R.id.tracker);
        SIZE.setText("SIZE:" + 32);
        TRACKER.setText("GPS TRACKER:" + "OFF");
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
        // Add a marker in Sydney and move the camera
        LatLng map = new LatLng(20.5937, 78.9629);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(map, 3));
        enableUserLocation();
        mMap.setOnMapLongClickListener(this);
    }
    private void enableUserLocation() {
        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, FINE_LOCATION_ACCESS_REQUEST_CODE);
        }
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    private void handleMapLongClick(LatLng latLng) {
        if (num == 0) {
            num = GEOFENCE_NUM;
            mMap.clear();
            NUMBER.setText("NUMBER:" + num);
            CloseBtn.setVisibility(View.INVISIBLE);
        } else {
            CloseBtn.setVisibility(View.VISIBLE);
            addMarker(latLng);
            addCircle(latLng, GEOFENCE_RADIUS);
            addGeofence(latLng, GEOFENCE_RADIUS);
            num--;
            NUMBER.setText("NUMBER:" + num);
            if(num == 0){
                NUMBER.setText("NUMBER:" + "click to reset");
            }
            Log.d("monitor", String.valueOf(num));
        }
    }
    @SuppressLint("MissingPermission")
    private void addGeofence(LatLng latLng, float radius) {
        Geofence geofence = geofenceHelper.getGeofence(GEOFENCE_ID, latLng, radius, Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_DWELL | Geofence.GEOFENCE_TRANSITION_EXIT);
        GeofencingRequest geofencingRequest = geofenceHelper.getGeofencingRequest(geofence);
        PendingIntent pendingIntent = geofenceHelper.getPendingIntent();
        geofencingClient.addGeofences(geofencingRequest, pendingIntent)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: Geofence Added...");
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
    public void searchLocation(View v) {
        EditText locationSearch = findViewById(R.id.searchText);
        String location = locationSearch.getText().toString().trim();
        List<Address> addressList = null;
        location = location.toLowerCase();
        Geocoder geocoder = new Geocoder(this);
        try {
            addressList = geocoder.getFromLocationName(location, 1);
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            if (location.equals("india")) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 4));
            } else {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
        } catch (Exception e) {
            Toast.makeText(this, "invalid place please check again", Toast.LENGTH_SHORT).show();
        }
    }
    public void clear(View v) {
        num = GEOFENCE_NUM;
        mMap.clear();
        NUMBER.setText("NUMBER:" + num);
        CloseBtn.setVisibility(View.INVISIBLE);
    }
    public void size(View v) {
        Toast.makeText(this, "size", Toast.LENGTH_SHORT).show();
        EditText size = new EditText(v.getContext());
        androidx.appcompat.app.AlertDialog.Builder SizeDialog = new AlertDialog.Builder(v.getContext());
        SizeDialog.setTitle("Geofence Size:");
        SizeDialog.setMessage("Enter the radius :");
        SizeDialog.setView(size);
        SizeDialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String Gsize = size.getText().toString();
                Toast.makeText(MapsActivity.this, Gsize, Toast.LENGTH_SHORT).show();
            }
        });
        SizeDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        SizeDialog.create().show();
    }
    public void number(View v){
        EditText number = new EditText(v.getContext());
        androidx.appcompat.app.AlertDialog.Builder NumberDialog = new AlertDialog.Builder(v.getContext());
        NumberDialog.setTitle("Modify Geofence number:");
        NumberDialog.setMessage("Enter the number os geofence :");
        NumberDialog.setView(number);
        NumberDialog.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String Gnumber = number.getText().toString();
                Toast.makeText(MapsActivity.this, Gnumber, Toast.LENGTH_SHORT).show();
            }
        });
        NumberDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        NumberDialog.create().show();
        Toast.makeText(this, "number", Toast.LENGTH_SHORT).show();
    }
    public void tracker(View v){
        TRACKER.setTextColor(Color.parseColor("#008000"));
        TRACKER.setText("GPS TRACKER:" + "ON");
        Toast.makeText(this, "tracker", Toast.LENGTH_SHORT).show();
    }
}