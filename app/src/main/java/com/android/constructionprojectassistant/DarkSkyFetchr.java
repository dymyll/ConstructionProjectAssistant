package com.android.constructionprojectassistant;

import android.support.v4.util.Pair;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by troyr on 5/6/2018.
 */

public class DarkSkyFetchr {

    private static final String TAG = "DarkSkyFetchr";

    private static final String API_KEY = "d2439039bc2c32250968441a5872be5c";

    private String mLat;

    private String mLong;

    public DarkSkyFetchr(Pair<Double, Double> latLong)

    {
        mLat = latLong.first.toString();

        mLong = latLong.second.toString();
    }

    public byte[] getUrlBytes(String urlSpec) throws IOException {

        URL url = new URL(urlSpec);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            InputStream in = connection.getInputStream();


            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

                throw new IOException(connection.getResponseMessage() +

                        ": with " +

                        urlSpec);

            }


            int bytesRead = 0;

            byte[] buffer = new byte[1024];

            while ((bytesRead = in.read(buffer)) > 0) {

                out.write(buffer, 0, bytesRead);

            }

            out.close();

            return out.toByteArray();

        } finally {

            connection.disconnect();

        }
    }


    public String getUrlString(String urlSpec) throws IOException {

        return new String(getUrlBytes(urlSpec));

    }

//fetchItems method

    public List<DarkSkyItem> fetchItems() {

        List<DarkSkyItem> items = new ArrayList<>();

    }
}



