package com.example.android.guiameduruelo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class SectionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
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

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();

        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloHoteles.json?alt=media&token=37413321-cd23-4693-b9f3-002989330a1a"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void seeRestaurantsInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloRestaurantes.json?alt=media&token=864cb3d8-fd1b-4050-96db-d46e4c9cb170"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void seeServicesInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloServicios.json?alt=media&token=5877a046-4e16-4f22-a155-d8514dc59ede"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void seeTurismInfo(View v){
        Intent i = new Intent();
        i.setClass(this, CardList.class);
        startActivity(i);

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();
        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloTurismo.json?alt=media&token=8a29f264-5b35-4985-9e09-a5a743f63ee4"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
