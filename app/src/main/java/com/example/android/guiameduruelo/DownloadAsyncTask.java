package com.example.android.guiameduruelo;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class DownloadAsyncTask extends AsyncTask<URL, Void, String> {

    public static DownloadAmenitiesInterface delegate;

    public interface DownloadAmenitiesInterface{
        void onAmenitiesDownloaded(String amenitiesData);
    }

    @Override
    protected String doInBackground(URL... urls) {
        String amenitiesData = "";

        try {
            amenitiesData = downloadData(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amenitiesData;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        delegate.onAmenitiesDownloaded(s);
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
