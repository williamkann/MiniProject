package com.example.miniproject;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MusicDownloaderTask extends AsyncTask<String, Integer, Object> {



    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected Object doInBackground(String... params) {

        String myURL = params[0];

        URL url = null;
        try {
            url = new URL(myURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Authorization","Bearer BQCvjE1Ip-g1dIOwzvkVArHLwYWsHrBvEWa8Y5hfwPZCTQoEXufLNVhnUUJVbPeicSaDGNVdK1tKh5IsbD58bwOmNKQLVqhjHS1W1wo11TbABIdq1rYIJvymSX4bPToNBxeXR4cLzxuQ0IpeYAXoaJr30TY7sbC885gtJ3CwJQkW7HJxiVs6Vwcs502T5kAawb1Z5gur_cUGOZvve44gwBE0cWjtppy1bSPd-ImU5rGAkL3nR7Jz73MgEby_B3a354Bqh9fj7Av67cfKEVtnEcZw-6bNWw");
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String s = readStream(in);
                Log.i("JFL", s);
            } finally {
                urlConnection.disconnect();
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute(){

    }

    protected void onPostExecute(){

    }

    protected void onProgressUpdate(){

    }

}
