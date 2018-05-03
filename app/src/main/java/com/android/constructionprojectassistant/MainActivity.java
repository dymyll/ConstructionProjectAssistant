package com.android.constructionprojectassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

        private EditText UserName;
        private EditText Password;
        private TextView Info;
        private TextView SignUp;
        private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_second);
//        UserName = (EditText) findViewById(R.id.etName);
//        Password = (EditText)findViewById(R.id.etPassword);
//        Info = (TextView) findViewById(R.id.tvInfo);
//        SignUp = (TextView)findViewById(R.id.etSignUp);
//        Login = (Button)findViewById(R.id.btnLogin);

    }

//    private  void validate(String userName, String userPassword){
//        if((userName == "Admin") && (userPassword == "1234")){
//            Intent intent = new Intent (MainActivity.this, SecondActivity.class);
//        }
//    }
}
