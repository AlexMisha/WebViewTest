package com.example.webviewtest;

import android.os.AsyncTask;
import android.support.annotation.CallSuper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class WebViewer extends AsyncTask<Void, Void, Void> {
    private String[] htmlCode = new String[1];
    private String urlString;

    Void setUrlString(String Url){
        urlString = Url;
        return null;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URLConnection connection = (new URL(urlString)).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder html = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                html.append(line);
            }
            in.close();
            htmlCode[0] = html.toString();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    String getHtmlCode() {
        return htmlCode[0];
    }
}