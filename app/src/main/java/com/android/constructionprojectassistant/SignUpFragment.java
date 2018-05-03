package com.android.constructionprojectassistant;


import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.Calendar;

import static java.lang.String.*;


/**
 * Created by Dymyll on 5/3/2018.
 */

public class SignUpFragment extends Fragment {
    private static View signupview;
    private static FragmentManager registerfragmentManager;


    /**
     * Sign-up form fields
     */

    private static EditText firstname;
    private static EditText lastname;
    private static EditText username;
    private static EditText email;
    //private static EditText dateofbirth;
    private static EditText password;
    private static EditText confirmPassword;

    private static Button registerbutton;
    //Date dateofbirth = new Date();
    Calendar dob = Calendar.getInstance();



    public SignUpFragment() {


    }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {


            signupview = inflater.inflate(R.layout.signupfragment, container, false);
            registerfragmentManager = getActivity().getSupportFragmentManager();
            Log.d("fullName", firstname.getText().toString());
            Log.d("lastName", lastname.getText().toString());
            Log.d("userName", username.getText().toString());
            registerbutton = (Button) signupview.findViewById(R.id.submit_btn);
            registerbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("HEREEEE", " ++++");
                    boolean areFieldsValid = validatefields();
                    if (areFieldsValid) {
                        Toast.makeText(getActivity(), "Successfully registered!!!",
                                Toast.LENGTH_LONG).show();

                        Customer customerData = new Customer();
                        customerData.setFirstName(firstname.getText().toString());
                        customerData.setLastName(lastname.getText().toString());
                        customerData.setUserName(username.getText().toString());
                        //customerData.setDateOfBirth(dateofbirth.getText().toString());

                        customerData.setEmailAddress(email.getText().toString());
                        customerData.setPassword(password.getText().toString());

                        Log.d("userdata.getFirstname", customerData.getFirstName());
                        Log.d("userdata.getLastnme", customerData.getLastName());
                        Log.d("userdata.getUserName", customerData.getUserName());
                        //Log.d("userdata.DateOfBirth", valueOf(customerData.getDateofBirth()));
                        Log.d("userdata.getEmail", customerData.getEmailAddress());
                        Log.d("userdata.getPassword", customerData.getPassword());

                        SignUpActivity signupactivity = new SignUpActivity();
                        signupactivity.helper.addCustomer(customerData);

                        registerfragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container, new LoginFragment(),
                                        "LoginModuleFragment").commit();
                    }

                }
            });
            return signupview;
        }

    public void getUserEnteredDetails(){

        firstname = (EditText) signupview.findViewById(R.id.firstname);
        lastname = (EditText) signupview.findViewById(R.id.lastname);
        email = (EditText) signupview.findViewById(R.id.emailaddress);

        username = (EditText) signupview.findViewById(R.id.username);
        //dateofbirth = (EditText) signupview.findViewById(R.id.dateofbirth);
        password = (EditText) signupview.findViewById(R.id.password);
        confirmPassword = (EditText) signupview.findViewById(R.id.confirmpassword);

    }

    // Check Validation Method
    private boolean validatefields() {

        // Get all edittext texts
        String getFirstName = firstname.getText().toString();
        String getLastName = lastname.getText().toString();
        String getUserName = username.getText().toString();
        String getEmailAddress = email.getText().toString();
        //String getDateOfBirth = dateofbirth.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
//        Pattern p = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b\"");
//        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFirstName.equals("") || getFirstName.length() == 0
                || getLastName.equals("") || getLastName.length() == 0
                || getUserName.equals("") || getUserName.length() == 0
                || getEmailAddress.equals("") || getEmailAddress.length() == 0
               // || getDateOfBirth.equals("") || getDateOfBirth.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)


        {
            Toast.makeText(getActivity(), "All fields are required.",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        // Check if email id valid or not
//        else if (!m.find()) {
//            Toast.makeText(getActivity(), "Email ID is invalid.",
//                    Toast.LENGTH_LONG).show();
//            return false;
//        }
        // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword)) {
            Toast.makeText(getActivity(), "Passwords don't match!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        // Else do signup or do your stuff
        else
            return true;

    }
}
