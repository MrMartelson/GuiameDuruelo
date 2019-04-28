package com.example.android.guiameduruelo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "MAP_ACTIVITY";

    private static final int ACCESS_FINE_LOCATION_PERMISSION_REQUEST_CODE = 0;
    private GoogleMap mMap;
    private double DurueloLat = 41.955181;
    private double DurueloLong = -2.931564;
    private GoogleApiClient mClient;
    private GoogleApiClient googleApiClient;
    private Geofencing mGeofencing;
    private Location userLocation;
    private ArrayList<Amenities> markersList;
    Marker durueloMarker;
    Marker amMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Create the map and the toolbar*/
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = new SupportMapFragment();
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.map_fragment_container, mapFragment).commit();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*Set the geofence*/
        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .enableAutoManage(this, this)
                .build();

        mGeofencing = new Geofencing(this, mClient);
        mGeofencing.registerGeofence();

        /*Check the user location*/
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker in Duruelo, Spain.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng duruelo = new LatLng(DurueloLat, DurueloLong);
        durueloMarker = mMap.addMarker(new MarkerOptions().position(duruelo).title("Duruelo de la Sierra"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(duruelo));
        mMap.setMinZoomPreference(8.0f);

        //Set the map over Duruelo when is created
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(DurueloLat, DurueloLong), 15.5f));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(DurueloLat, DurueloLong), 15.5f));

        mGeofencing = new Geofencing(this, mClient);
        mGeofencing.registerGeofence();


        if (getIntent() != null) {
            markersList = getIntent().getParcelableArrayListExtra("key");
            if (markersList != null) {

                if (durueloMarker != null) {
                    durueloMarker.remove();
                }

                for (Amenities markers : markersList) {
                    double latDouble = Double.valueOf(markers.getLat());
                    double lngDouble = Double.valueOf(markers.getLng());
                    String title = markers.getPlace();
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latDouble, lngDouble))
                            .title(title));
                }
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                } else {
                    final String[] permisions = new String[]{ACCESS_FINE_LOCATION};
                    requestPermissions(permisions, ACCESS_FINE_LOCATION_PERMISSION_REQUEST_CODE);
                }
            } else {
                mMap.setMyLocationEnabled(true);
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.icon_set_markers){
            startActivity(new Intent(getApplicationContext(), SectionsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {

        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onStop(){
        googleApiClient.disconnect();
        super.onStop();
    }

    private void upgradeMap(){

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                userLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            }else{
                final String[] permisions = new String[]{ACCESS_FINE_LOCATION};
                requestPermissions(permisions, ACCESS_FINE_LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            userLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);}

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == ACCESS_FINE_LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                googleApiClient.reconnect();
            }
            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.permission_title));
                builder.setMessage(getString(R.string.permission_message));
                builder.setPositiveButton(getString(R.string.acept), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String[] permisions = new String[]{ACCESS_FINE_LOCATION};
                        requestPermissions(permisions, ACCESS_FINE_LOCATION_PERMISSION_REQUEST_CODE);
                    }
                });
                builder.show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static Intent makeNotificationIntent(Context context, String msg) {
        Intent intent = new Intent( context, MapActivity.class );
        intent.putExtra(String.valueOf(R.string.geofence_entry_msg), msg );
        return intent;
    }

}
