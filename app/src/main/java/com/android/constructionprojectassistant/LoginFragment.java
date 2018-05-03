package com.android.constructionprojectassistant;

import android.content.Intent;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Dymyll on 5/3/2018.
 */

public class LoginFragment extends Fragment {

    private TextView Info;
    private TextView SignUp;
    private Button Login;
    private int counter = 10;
    private Customer id;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.login_fragment, container, false);
//
//
//        UserName = (EditText) view.findViewById(R.id.etName);
//        Password = (EditText) view.findViewById(R.id.etPassword);
//        Info = (TextView) view.findViewById(R.id.tvInfo);
//        SignUp = (TextView) view.findViewById(R.id.etSignUp);
//        SignUp.setPaintFlags(SignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//        Login = (Button) view.findViewById(R.id.btnLogin);
//
//
//        public class LoginModuleFragment extends Fragment {
//            public static View loginview;
//            private static Button loginbtn, registerbtn;
//            private static FragmentManager loginfragmentManager;
//            private static EditText emailid,pass,fullname;
//
//
//            public LoginModuleFragment() {
//            }

            public static View loginview;
            private static Button btnLogin, registerbtn;
            private  static TextView signUP;
            private static FragmentManager loginfragmentManager;
            private static EditText UserName,Password,fullname;

//Creates the view for Login Fragment
    @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                loginview = inflater.inflate(R.layout.login_fragment, container, false);
                loginfragmentManager = getActivity().getSupportFragmentManager();

                //When user clicks login button, this will check to see if fields match database to access
        btnLogin = (Button) loginview.findViewById (R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Login ", " clicked");

                        UserName = (EditText) loginview.findViewById(R.id.etUserName);
                        Password = (EditText) loginview.findViewById(R.id.etPassword);
                        Log.d("UserName ", UserName.getText().toString());
                        Log.d("Password ", Password.getText().toString());

                        LoginActivity loginctivity = new LoginActivity();

                        if(loginctivity.helper.userExists(UserName.getText().toString(),Password.getText().toString())){

                            String username = loginctivity.helper.getUserName(UserName.getText().toString());
                            Log.d("Retrieved username", username);
                            //Opens up new page for existing users
                            Intent i = new Intent(getActivity(),CustomerPageActivity.class);
                            i.putExtra("password", username);
                            startActivity(i);

                        } else{
                            Toast.makeText(getActivity(), "User Name and/or Password don't match.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });



                 signUP = (TextView) loginview.findViewById (R.id.etSignUp);
                signUP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, new SignUpFragment(), "SignUpFragmentTag");
                        ft.commit();
                    }
                });

                return loginview;
            }






























            //Info.setText("Number of attempts remaining: 5");

//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                validate(UserName.getText().toString(), Password.getText().toString());
//            }
//
//        });
//        return  view;
//    }
//
//
//    private void validate(String userName, String userPassword) {
//        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
//            Intent intent = new Intent(getActivity(), SecondActivity.class);
//            startActivity(intent);
//        } else {
//            counter--;
//
//            Info.setText("Number of attempts remaining: " + String.valueOf(counter));
//
//            if (counter == 0) {
//                Login.setEnabled(false);
//            }
//        }
//
//
//    }

}


