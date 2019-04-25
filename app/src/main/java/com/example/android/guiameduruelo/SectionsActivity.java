package com.example.android.guiameduruelo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class SectionsActivity extends AppCompatActivity implements DownloadAsyncTask.DownloadAmenitiesInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void seeHotelsInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        //downloadData();

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        DownloadAsyncTask.delegate = this;
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloHoteles.json?alt=media&token=08dac43d-e8b6-450c-a7db-eeb1cdf41c55"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void seeRestaurantsInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        //downloadData();
        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        DownloadAsyncTask.delegate = this;
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloRestaurantes.json?alt=media&token=c16991cf-d2e4-4ccd-ad96-910bcc38176c"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void seeServicesInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        //downloadData();
        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        DownloadAsyncTask.delegate = this;
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloServicios.json?alt=media&token=4378366d-1c30-4f78-8e11-c9ec2258a83a"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void seeTurismInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        //downloadData();
        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        DownloadAsyncTask.delegate = this;
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloTurismo.json?alt=media&token=5254c0d0-eefb-429d-a280-9c7cb4e1b156"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAmenitiesDownloaded(String amenitiesData) {
        Log.d("DATOS", amenitiesData);
    }

}
