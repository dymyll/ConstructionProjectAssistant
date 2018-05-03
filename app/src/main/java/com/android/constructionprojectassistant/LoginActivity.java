package com.android.constructionprojectassistant;

/**
 * Created by Dymyll on 5/3/2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.EditText;

public class LoginActivity extends FragmentActivity {
    CustomerBaseHelper helper = new CustomerBaseHelper(this);
    private static EditText UserName,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        LoginFragment loginfragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, loginfragment).commit();

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        //Setting the variables to those entered into the fragment
        UserName = (EditText) LoginFragment.loginview.findViewById(R.id.etUserName);
        outState.putString("User Name", UserName.getText().toString());
        Password = (EditText) LoginFragment.loginview.findViewById(R.id.etPassword);
        outState.putString("Password", Password.getText().toString());
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.

            Log.d("HEREEEEEEEEE","+++++++++");
            Log.d("UserName+++++++++",UserName.getText().toString());
            Log.d("Password+++++++++",Password.getText().toString());
            UserName.setText(savedInstanceState.getString("emailid"));
            Password.setText(savedInstanceState.getString("password"));
            Log.d("Received emailid+++++++",savedInstanceState.getString("emailid"));

        }
    }
}
