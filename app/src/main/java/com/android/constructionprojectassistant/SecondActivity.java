package com.android.constructionprojectassistant;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.database.DataSnapshot;
//tes

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    public  static final String EXTRA_CRIME_ID = "testing";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    private EditText editTextTitle;
    private EditText editTextDescription;

    //private FirebaseFirestore db = FirebaseFirestore.getInstance();

//

    private EditText firstname;
    //     private EditText Last
//     private EditText Username;
//     private EditText Password;
//     private EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final EditText fname = findViewById(R.id.edit_text_firstname);
        final EditText lname = findViewById(R.id.edit_text_lastname);
        final EditText username = findViewById(R.id.edit_text_userid);
        final EditText password = findViewById(R.id.edit_text_password);
        final EditText email = findViewById(R.id.edit_text_email);



        Button button = findViewById(R.id.SubmitButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view)

            {

                final Context context = getApplicationContext();
                //Sets message to the input from above
                final String userf = fname.getText().toString();
                final String userl = lname.getText().toString();
                final String id = username.getText().toString();
                final String pass = password.getText().toString();
                final String ema = email.getText().toString();
                //Write to database
                DatabaseReference firebaseRef = database.getReference("message_post");
                firebaseRef.setValue("Profile Created");
                firebaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String valueFromFirebase = dataSnapshot.getValue(String.class);
                        Toast toast = Toast.makeText(context, "Profile Created" , Toast.LENGTH_LONG);
                        //Toast.makeText(SecondActivity.this, "Hello World", + message, Toast.LENGTH_SHORT).show();
                        toast.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        });
    }
}


//
//        editTextTitle = findViewById(R.id.edit_text_title);
//        editTextDescription = findViewById(R.id.edit_text_description);
//    }
//    public void saveNote(View v) {
//        String title = editTextTitle.getText().toString();
//        String description = editTextDescription.getText().toString();
//
//        Map<String, Object> note = new HashMap<>();
//        note.put(KEY_TITLE, title);
//        note.put(KEY_DESCRIPTION, description);
//
//        db.document("Notebook/My First Note");
//        db.collection("Notebook").document("My First Note").set(note)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess (Void aVoid){
//
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e){
//                        Toast.makeText(SecondActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, e.toString());
//            }
//        });
//            };            });
//        };








