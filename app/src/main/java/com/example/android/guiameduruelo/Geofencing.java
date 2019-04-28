package com.example.android.guiameduruelo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;

import static com.google.android.gms.location.GeofencingRequest.INITIAL_TRIGGER_DWELL;


public class Geofencing implements ResultCallback {

    private static final String TAG = Geofencing.class.getSimpleName();
    private static final float GEOFENCE_RADIUS = 4000;
    private Geofence geofence;

    private PendingIntent mGeofencePendingIntent;
    private GoogleApiClient mGoogleApiClient;
    private Context mContext;

    public Geofencing(Context context, GoogleApiClient client){
        mContext = context;
        mGoogleApiClient = client;
        }

    public void registerGeofence(){
        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()){
            return;
            }
        try {
            LocationServices.GeofencingApi.addGeofences(
                    mGoogleApiClient, getGeofencingRequest(),
                    getGeofencePendingIntent()).setResultCallback(this);
        }catch (SecurityException securityException) {
                Log.e(TAG, securityException.getMessage());
        }
    }

    public void unRegisterGeofence(){
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()){
            return;
        }
        try {
            LocationServices.GeofencingApi.removeGeofences(mGoogleApiClient,
                    getGeofencePendingIntent()).setResultCallback(this);
        } catch (SecurityException securityException){
            Log.e(TAG, securityException.getMessage());
        }
    }

    public void createGeofence(){
    geofence = new Geofence.Builder()
            .setRequestId("Duruelo de la Sierra")
            .setCircularRegion(41.955181, -2.931564, GEOFENCE_RADIUS)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build();
    }



    public GeofencingRequest getGeofencingRequest(){
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER | INITIAL_TRIGGER_DWELL);
        builder.addGeofence(geofence);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        if (mGeofencePendingIntent != null){
            return mGeofencePendingIntent;
        }
        Intent intent = new Intent(mContext, GeofenceBroadcastReceiver.class);
        mGeofencePendingIntent = PendingIntent.getBroadcast(mContext, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return mGeofencePendingIntent;
    }

    @Override
    public void onResult(@NonNull Result result) {
        Log.e(TAG, String.format("Error adding/removing geofence : %s",
                result.getStatus().toString()));
    }

}
