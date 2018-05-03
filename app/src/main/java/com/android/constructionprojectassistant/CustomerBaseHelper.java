package com.android.constructionprojectassistant;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import com.android.constructionprojectassistant.CustomerDbSchema.CustomerTable;

import static com.android.constructionprojectassistant.CustomerDbSchema.CustomerTable.*;
//import static com.android.constructionprojectassistant.CustomerDbSchema.CustomerTable.Cols.DATEOFBIRTH;

/**
 * Created by Dymyll on 4/1/2018.
 *
 */

public class CustomerBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDatabase;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "customerBase.db";

    public CustomerBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        this.mDatabase =db;

        db.execSQL("create table " + NAME + "(" +
        " _id integer primary key autoincrement, " +
        Cols.UUID + ", " +
        Cols.FIRSTNAME + ", " +
        Cols.LASTNAME + ", " +

                Cols.USERNAME + ", " +
                Cols.EMAILADDRESS + ", " +
                //DATEOFBIRTH + ", " +
                Cols.PASSWORD +

        ")"

        );

        Log.d("Table created","created");

    }

    //adds customer to database
    public  void addCustomer(Customer customerData)
    {
        ContentValues values = new ContentValues();
        values.put(Cols.FIRSTNAME,customerData.getFirstName());
        values.put(Cols.LASTNAME,customerData.getLastName());
        values.put(Cols.USERNAME,customerData.getUserName());
        values.put(Cols.EMAILADDRESS,customerData.getEmailAddress());
       // values.put(DATEOFBIRTH, customerData.getDateofBirth().toString());
        values.put(Cols.PASSWORD,customerData.getPassword());

        try{
            mDatabase.insert(CustomerTable.NAME, null ,values);
            Log.d("ADD SUCCESSFUL", values.toString());
        } catch (Exception e){
            Log.d("ADD FAILURE", e.toString());
        }
        //db.close();
    }

    //Checks if user exist in database
    public boolean userExists(String UserName,String Password){
        //db = this.getReadableDatabase();
        String fetchuser = "Select UserName,Password from " +CustomerTable.NAME;
        //Searches through database with cursor
        Cursor cursor = mDatabase.rawQuery(fetchuser, null);
        //creates two search variables a and b
        String a,b = "not found";
        Log.d("received UserName", UserName);
        Log.d("Cursor count", String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()){
            Log.d("Select " , " clause");
            do{
                a= cursor.getString(0);
                Log.d("a " , a);
                //if a finds username
                if (a.equals(UserName)){
                    Log.d("UserName  If loop" , a);
                    //and b finds password, then user exist
                    b = cursor.getString(1);
                    Log.d("b " , b);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        if (b.equals(Password)) {
            Log.d("Returning "," true");
            return true;
        }
        else return false;
    }

    //finds the username based on email and First Name in same column

    public String getUserName(String email){
        //db = this.getReadableDatabase();
        String fetchuser = "Select "+Cols.EMAILADDRESS+", "+Cols.FIRSTNAME+" from " +CustomerTable.NAME;
        Cursor cursor = mDatabase.rawQuery(fetchuser, null);
        String a,b = "not found";
        Log.d("received Email", email);
        Log.d("Cursor count", String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()){
            Log.d("Select " , " clause");
            do{
                a= cursor.getString(0);
                Log.d("a " , a);
                if (a.equals(email)){
                    Log.d("email  If loop" , a);
                    b = cursor.getString(1);
                    Log.d("b " , b);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropquery= "DROP TABLE IF EXISTS "+CustomerTable.NAME;
        db.execSQL(dropquery);
        this.onCreate(db);
    }
}
