package com.android.location_alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;  //android location alarm emergency service database

import java.util.ArrayList;
import java.util.List;

public class EmergencySDatabaseHelper extends SQLiteOpenHelper {
    public static final String E_SERVICE = "EMERGENCY_SERVICE";
    public static final String E_ID = "ID";
    public static final String E_NAME = "NAME";
    public static final String E_PHONE_NUMBER = "PHONE_NUMBER";
    public EmergencySDatabaseHelper(@Nullable Context context) {
        super(context, "e_contact.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + E_SERVICE + "(" + E_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + E_NAME + " TEXT," + E_PHONE_NUMBER + " INTEGER )";
        db.execSQL(createTableStatement);




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public boolean addOne(EmergencyContact emergencyContact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(E_NAME, emergencyContact.getName());
        cv.put(E_PHONE_NUMBER, emergencyContact.getPhonenumber());
        long insert = db.insert(E_SERVICE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    public boolean deleateOne(EmergencyContact emergencyContact){
        SQLiteDatabase db = this.getWritableDatabase();
        String quearyString = " DELETE FROM " + E_SERVICE + " WHERE " + E_ID + " = " + emergencyContact.getId();
        Cursor cursor = db.rawQuery(quearyString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }
    public List<EmergencyContact> getEveryone() {
        List<EmergencyContact> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + E_SERVICE;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()){
            do {
                int E_id = cursor.getInt(0);
                String E_name = cursor.getString(1);
                String E_phonenumber = cursor.getString(2);
                EmergencyContact newperson = new EmergencyContact(E_id,E_name,E_phonenumber);
                returnList.add(newperson);
            }while(cursor.moveToNext());
        }
        else{
            //empty list
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public Cursor viewData(){ //added latest
         SQLiteDatabase sqLitedatabase = this.getReadableDatabase();
         Cursor cursor = sqLitedatabase.rawQuery("select * from "+E_SERVICE,null);
         return cursor;

    }










}
