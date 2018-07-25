package com.practice.uiu.tablayoutsregisterpage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.practice.uiu.tablayoutsregisterpage.model.UserModel;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "userinfo";
    private static final String TABLE_NAME = "user_table";
    private static final int DB_VERSION =1;

    private static final String COL_NAME = "NAME";
    private static final String COL_ID = "_ID";
    private static final String COL_PASS = "PASSWORD";
    private static final String COL_PHONE = "PHONE_NUMBER";

    private static final String SELECT_ALL = " ";
    private static String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+" ( "+COL_ID+" INTEGER, "+COL_NAME+" TEXT NOT NULL, "+COL_PASS+" TEXT NOT NULL, "+COL_PHONE+" TEXT NOT NULL, PRIMARY KEY("+COL_ID+") )";

    private Context context;
    private SQLiteDatabase mDatabase;

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DB_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +COL_NAME+" TEXT," +COL_PASS+" TEXT," +COL_PHONE+ " TEXT );" ;

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addUserInfo(UserModel userModel)
    {
        mDatabase=this.getWritableDatabase();//permission for writing in database
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME, userModel.getName());
        contentValues.put(COL_PASS, userModel.getPassword());
        contentValues.put(COL_PHONE, userModel.getPhone());

       // mDatabase.insert(TABLE_NAME,null,contentValues);
        long reselt=mDatabase.insert(TABLE_NAME,null,contentValues);
        if(reselt==-1)
        {
            mDatabase.close();
            return false;
        }
        else{
            mDatabase.close();
            return true;
        }

    }
    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userlist = new ArrayList<>();
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_NAME;
        // 2. get reference to writable DB
         mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery(query, null,null);

        // 3. go over each row, build book and add it to list
        UserModel tempuser = null;
        if (cursor.moveToFirst()) {
            do {
               String id=cursor.getString(cursor.getColumnIndex(COL_ID));
               String name=cursor.getString(cursor.getColumnIndex(COL_NAME));
               String pass= cursor.getString(cursor.getColumnIndex(COL_PASS));
               String phone = cursor.getString(cursor.getColumnIndex(COL_PHONE));

               tempuser=new UserModel(id,name,pass,phone);

                // Add user to users
                userlist.add(tempuser);
            } while (cursor.moveToNext());
        }

        cursor.close();
        mDatabase.close();
        Log.d("getAllinfo()", userlist.toString());
        return userlist;
    }
    public String getSinlgeEntry(String userName)
    {
        mDatabase = this.getReadableDatabase();
        Cursor cursor=mDatabase.query(TABLE_NAME, null, "NAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(COL_PASS));
        String id=cursor.getString(cursor.getColumnIndex(COL_ID));
        UserDetails.id=id;
        UserDetails.name=userName;
        UserDetails.pass=password;
        UserDetails.phone=cursor.getString(cursor.getColumnIndex(COL_PHONE));



        cursor.close();
        mDatabase.close();
        return password;
    }
}
