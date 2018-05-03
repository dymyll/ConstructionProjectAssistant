package com.android.constructionprojectassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class SecondActivity extends AppCompatActivity {


    private static final String EXTRA_CRIME_ID = "test";

//    public static Intent newIntent(Context packageContext, UUID id) {
//        Intent intent = new Intent(packageContext, SecondActivity.class);
//        intent.putExtra(EXTRA_CRIME_ID, id);
//        return intent;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
