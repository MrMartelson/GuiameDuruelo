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

        DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask();

        try {
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloHoteles.json?alt=media&token=c7ae73b4-4b1c-41d0-9ab1-e17d217066ef"));
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
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloRestaurantes.json?alt=media&token=e32860d5-9a70-4ba1-ac37-e2874c4aed78"));
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
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloServicios.json?alt=media&token=af2d6ab7-0466-49c7-befa-8c6068b213e0"));
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
            downloadAsyncTask.execute(new URL("https://firebasestorage.googleapis.com/v0/b/zeta-feat-236616.appspot.com/o/GuiaDurueloTurismo.json?alt=media&token=0588b7bb-efa4-485a-8347-3e6fe6703dd6"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
