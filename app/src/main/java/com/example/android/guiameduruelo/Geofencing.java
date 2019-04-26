package com.example.android.guiameduruelo;

import android.app.PendingIntent;
import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;


public class Geofencing {

    public static final String TAG = Geofencing.class.getSimpleName();
    private static final float GEOFENCE_RADIUS = 4000;

    private PendingIntent mGeofencePendingIntent;
    private GoogleApiClient mGoogleApiClient;
    private Context mContext;

    public Geofencing(Context context, GoogleApiClient client){
        mContext = context;
        mGoogleApiClient = client;
        }

    public void createGeofence(){
    Geofence geofence = new Geofence.Builder()
            .setRequestId("Duruelo de la Sierra")
            .setCircularRegion(41.955181, -2.931564, GEOFENCE_RADIUS)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_EXIT)
            .build();
    }

}
