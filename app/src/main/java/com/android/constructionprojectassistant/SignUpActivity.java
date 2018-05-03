package com.android.constructionprojectassistant;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/**
 * Created by Dymyll on 5/3/2018.
 */

public class SignUpActivity extends Activity {
    CustomerBaseHelper helper = new CustomerBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupfragment);

    }
}
