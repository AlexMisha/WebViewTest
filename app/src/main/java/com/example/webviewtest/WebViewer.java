package com.example.webviewtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebViewer {
    private static final String TAG = "WebViewer";

    public static String getHtml(final String url) throws IOException, InterruptedException {

        final String[] htmlCode = new String[1];
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URLConnection connection = (new URL(url)).openConnection();
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();

        return htmlCode[0];
    }
}
