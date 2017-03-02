package com.asso.integration1.hot.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by André on 02/03/2017.
 */

public class NetworkUtils {

    private static final String THINGS_URL = "https://boleias.000webhostapp.com/get.php";

    public static void getStatus(final int requestCode, final NetworkCallback callback) {
        httpGetBoolean(THINGS_URL, requestCode, callback);
    }

    // NETWORKING METHODS

    private static void httpGet(final String urlString, final int requestCode, final NetworkCallback callback) {
        new Thread() {
            @Override
            public void run() {
                String text = actualHttpGet(urlString);
                callback.onResult(requestCode, text);
            }
        }.start();
    }

    private static void httpGetBoolean(final String urlString, final int requestCode, final NetworkCallback callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String text = actualHttpGet(urlString);
                    callback.onResult(requestCode, Boolean.parseBoolean(text));
                } catch (Exception e) {
                    Log.e("HoT networking", "No boolean acquired, return 'false' by default");
                    callback.onResult(requestCode, false);
                }
            }
        }.start();
    }

    private static String actualHttpGet(String urlString) {
        String text = null;

        try {
            URL url = new URL(urlString);

            // Send POST data request
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // Get the server response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                text = reader.readLine();
            } catch(Exception ex) {
                ex.printStackTrace();
                Log.d("HoT", "Unable to read from server (1).", ex);
            }

            Log.d("HoT", "Response: " + text);
        } catch(Exception ex) {
            Log.d("HoT", "Unable to read from server (2).", ex);
        }

        return text;
    }
}
