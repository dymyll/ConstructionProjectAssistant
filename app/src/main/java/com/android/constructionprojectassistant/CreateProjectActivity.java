package com.android.constructionprojectassistant;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.ProtocolException;
import java.util.ArrayList;

/**
 * Created by Dymyll on 5/3/2018.
 */

public class CreateProjectActivity extends AppCompatActivity {

    private static final String TAG = "UploadActivity";

    //declare variables
    private ImageView image;
    private EditText imageName, ProjectName, ProjectLocation, ProjectDescription;
    private Button btnUpload, btnNext, btnBack;
    private ImageButton myUploadbtn;
    private ProgressDialog mProgressDialog;

    private final static int mWidth = 512;
    private final static int mLength = 512;

    private ArrayList<String> pathArray;
    private int array_position;

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    private StorageReference mStorageRef;
    private FirebaseAuth auth;

    public CreateProjectActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project_activity);

        image = (ImageView) findViewById(R.id.uploadImage);
        btnBack = (Button) findViewById(R.id.btnBackImage);
        btnNext = (Button) findViewById(R.id.btnNextImage);
        btnUpload = (Button) findViewById(R.id.btnUploadImage);
        ProjectName = (EditText) findViewById(R.id.projectname);
        ProjectLocation = (EditText) findViewById(R.id.projectlocation);
        ProjectDescription = (EditText) findViewById(R.id.projectdesc);
        myUploadbtn = (ImageButton) findViewById(R.id.takepic);


        //add = (onOptionsItemSelected(item) findViewById(R.id.btnUploadImage);


        pathArray = new ArrayList<>();
        mProgressDialog = new ProgressDialog(CreateProjectActivity.this);
        auth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        checkFilePermissions();
        checkCameraPermissions();

        addFilePaths();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array_position > 0) {
                    Log.d(TAG, "onClick: Back an Image.");
                    array_position = array_position - 1;
                    loadImageFromStorage();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (array_position < pathArray.size() - 1) {
                    Log.d(TAG, "onClick: Next Image.");
                    array_position = array_position + 1;
                    loadImageFromStorage();
                }
            }
        });

        //Takes pic


        myUploadbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }




//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//               startActivityForResult(intent, CAMERA_REQUEST_CODE);
//
//            }
//        });
//    }

        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            FirebaseUser user = auth.getCurrentUser();
            String userID = user.getUid();


            String projname = ProjectName.getText().toString();
            String projdesc = ProjectDescription.getText().toString();
            String projlocation = ProjectLocation.getText().toString();
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                myUploadbtn.setImageBitmap(photo);
            }
            if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

//                ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                        LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
            }
            if ( Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission( CreateProjectActivity.this, android.Manifest.permission.CAMERA ) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
                mProgressDialog.setMessage("Uploading Image...");
                Uri uri = data.getData();
                StorageReference filepath = mStorageRef.child("images/users/" + userID + "/" + projname + ".jpg");

                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(CreateProjectActivity.this, "Upload Successful...", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    //Upload pics and more
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Uploading Image.");
                mProgressDialog.setMessage("Uploading Image...");
                mProgressDialog.show();

                //get the signed in user
                FirebaseUser user = auth.getCurrentUser();
                String userID = user.getUid();

                String projname = ProjectName.getText().toString();
                String projdesc = ProjectDescription.getText().toString();
                String projlocation = ProjectLocation.getText().toString();

                if(!projname.equals("") && !projdesc.equals("") && !projlocation.equals("")){
                    Uri uri = Uri.fromFile(new File(pathArray.get(array_position)));
                    StorageReference storageReference = mStorageRef.child("images/users/" + userID + "/" + projname + ".jpg");
                    storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            toastMessage("Upload Success");
                            mProgressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            toastMessage("Upload Failed");
                            mProgressDialog.dismiss();
                        }
                    })
                    ;
                }
                else{
                    toastMessage("Not all fields are filled: ");
                }

            }
        });

    }

    /**
     * Add the file paths you want to use into the array
     */
    private void addFilePaths(){
        Log.d(TAG, "addFilePaths: Adding file paths.");
        String path = System.getenv("EXTERNAL_STORAGE");
        pathArray.add(path+"/Pictures/Portal/image1.jpg");
        pathArray.add(path+"/Pictures/Portal/image2.jpg");
        pathArray.add(path+"/Pictures/Portal/image3.jpg");
        pathArray.add(path+"/Pictures/Portal/image4.jpg");
        pathArray.add(path+"/Pictures/Portal/image5.jpg");
        pathArray.add(path+"/Pictures/Portal/image6.jpg");
        loadImageFromStorage();
    }

    private void loadImageFromStorage()
    {
        try{
            String path = pathArray.get(array_position);
            File f=new File(path, "");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            image.setImageBitmap(b);
        }catch (FileNotFoundException e){
            Log.e(TAG, "loadImageFromStorage: FileNotFoundException: " + e.getMessage() );
        }

    }
    public  void checkCameraPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int cameraPermission = CreateProjectActivity.this.checkSelfPermission("Manifest.permission.CAMERA");
            cameraPermission += CreateProjectActivity.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (cameraPermission != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1001); //Any number
            }
        }
    }

    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = CreateProjectActivity.this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += CreateProjectActivity.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");

            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }

        }else{
            Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addproj:
                Intent intent = new Intent(this, CreateProjectActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btnLogout:
                // another startActivity, this is for item with id "menu_item2"
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }




    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
