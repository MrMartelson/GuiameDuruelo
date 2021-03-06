package com.example.android.guiameduruelo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class DownloadAsyncTask extends AsyncTask<URL, Void, ArrayList<Amenities>> {

    public static DownloadAmenitiesInterface delegate;


    public interface DownloadAmenitiesInterface{
        void onAmenitiesDownloaded(ArrayList<Amenities> placesList);
    }

    @Override
    protected ArrayList<Amenities> doInBackground(URL... urls) {
        String amenitiesData;
        ArrayList<Amenities> placesList = null;
        try {
            amenitiesData = downloadData(urls[0]);
            placesList = parseDataFromJson(amenitiesData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return placesList;
    }

    @Override
    protected void onPostExecute(ArrayList<Amenities> placesList) {
        super.onPostExecute(placesList);

        delegate.onAmenitiesDownloaded(placesList);
    }

    private ArrayList<Amenities> parseDataFromJson(String amenitiesData){
        ArrayList<Amenities> placesList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(amenitiesData);
            JSONArray amenitiesJsonArray = jsonObject.getJSONArray("array");

            for(int i = 0; i < amenitiesJsonArray.length(); i++){
                JSONObject amenitiesJsonObject = amenitiesJsonArray.getJSONObject(i);
                JSONObject latlngJsonObject = amenitiesJsonArray.getJSONObject(i);
                String title = amenitiesJsonObject.getString("title");
                String info = amenitiesJsonObject.getString("info");
                String image = amenitiesJsonObject.getString("image");


                String lat = latlngJsonObject.getString("lat");
                String lng = latlngJsonObject.getString("lng");

                placesList.add(new Amenities(title, info, lat, lng, image));

                Log.d("DATOS", title + ": " + info + "\n " + "latlong: " + lat + " " + lng);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return placesList;
    }

    private String downloadData(URL url) throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* miliseconds */);
            urlConnection.setConnectTimeout(15000 /* miliseconds*/);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        }catch (IOException e){
          //  Toast.makeText(this, R.string.download_error, Toast.LENGTH_SHORT).show();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
