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




}


