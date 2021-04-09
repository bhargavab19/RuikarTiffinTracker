package com.koaladev.ruikartiffintracker.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.koaladev.ruikartiffintracker.Model.Record;
import com.koaladev.ruikartiffintracker.Params.DBParams;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, DBParams.DB_NAME, null, DBParams.DB_VERSION);
    }

    //Method implementations for Adding, Updating, Reading andDeleting Records
    public long addRecord(Record record){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        //Specify each value to insert
        values.put(DBParams.KEY_DATE,record.getDate());
        values.put(DBParams.KEY_LUNCH,record.getLunch());
        values.put(DBParams.KEY_DINNER,record.getDinner());
        values.put(DBParams.KEY_QUANTITY,record.getQuantity());
        values.put(DBParams.KEY_RATE,record.getRate());

        //Insert values into DB
        long flag=db.insert(DBParams.TABLE_NAME,null,values);

        db.close();

        return flag;
    }

    public ArrayList<Record> getAllRecords(){
        ArrayList<Record> records=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();

        //Query Definition
        String select="SELECT * FROM "+DBParams.TABLE_NAME+" ORDER BY "+DBParams.KEY_DATE;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor!=null) {
            cursor.moveToFirst();
            do {
                Record record = new Record();
                record.setId(cursor.getInt(0));
                record.setDate(cursor.getString(1));
                record.setLunch(cursor.getString(2));
                record.setDinner(cursor.getString(3));
                record.setQuantity(cursor.getInt(4));
                record.setRate(cursor.getInt(5));
                records.add(record);
            } while (cursor.moveToNext());
        }
        return records;
    }

    public int updateRecord(Record record){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(DBParams.KEY_LUNCH, record.getLunch());
        values.put(DBParams.KEY_DINNER, record.getDinner());
        values.put(DBParams.KEY_QUANTITY, record.getQuantity());
        values.put(DBParams.KEY_RATE, record.getRate());

        return db.update(DBParams.TABLE_NAME,values,DBParams.KEY_ID+"=?",new String[]{String.valueOf(record.getId())});
    }

    public ArrayList<Record> getSpecificRecord(String date){
        ArrayList<Record> records=new ArrayList<>();

        SQLiteDatabase db=this.getReadableDatabase();

        String searchRecord="SELECT * FROM "+DBParams.TABLE_NAME+" WHERE "+DBParams.KEY_DATE+"=?";
        Cursor cursor=db.rawQuery(searchRecord,new String[]{date});

        if(cursor!=null) {
            cursor.moveToFirst();
            Record record = new Record();
            record.setId(cursor.getInt(0));
            record.setDate(cursor.getString(1));
            record.setLunch(cursor.getString(2));
            record.setDinner(cursor.getString(3));
            record.setQuantity(cursor.getInt(4));
            record.setRate(cursor.getInt(5));
            records.add(record);
        }
        return records;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+DBParams.TABLE_NAME+"("
                +DBParams.KEY_ID+" INTEGER PRIMARY KEY, "
                +DBParams.KEY_DATE+" TEXT,"
                +DBParams.KEY_LUNCH+" TEXT,"
                +DBParams.KEY_DINNER+" TEXT,"
                +DBParams.KEY_QUANTITY+" INTEGER,"
                +DBParams.KEY_RATE+" INTEGER)";

        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
