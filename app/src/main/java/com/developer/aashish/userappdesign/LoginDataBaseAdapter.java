package com.developer.aashish.userappdesign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

/**
 * Created by Aashish on 10/8/2017.
 */

public class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 3;

    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID integer primary key autoincrement,"+ "PASSWORD  text,"+"REPASSWORD text,"+ "SECURITYHINT text) ";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String password,String repassword,String securityhint)
    {

        db.execSQL("delete from "+ "LOGIN");


        ContentValues newValues = new ContentValues();
        newValues.put("PASSWORD", password);
        newValues.put("REPASSWORD",repassword);
        newValues.put("SECURITYHINT",securityhint);

        db.insert("LOGIN", null, newValues);
    }

    public int deleteEntry(String password)
    {
        String where="PASSWORD=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{password}) ;
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String password)
    {
        Cursor cursor=db.query("LOGIN", null, " PASSWORD=?", new String[]{password}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String repassword= cursor.getString(cursor.getColumnIndex("REPASSWORD"));
        cursor.close();
        return repassword;
    }



    public String getSinlgeEntr(String securityhint)
    {
        Cursor cursor=db.query("LOGIN", null, " SECURITYHINT=?", new String[]{securityhint}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String repassword= cursor.getString(cursor.getColumnIndex("SECURITYHINT"));
        cursor.close();
        return repassword;
    }








    public String getAllTags(String a) {


        Cursor c = db.rawQuery("SELECT * FROM " + "LOGIN" + " where SECURITYHINT = '" +a + "'" , null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("PASSWORD"));
            } while (c.moveToNext());
        }
        return str;
    }


    public void  updateEntry(String password,String repassword,String a)
    {

        ContentValues updatedValues = new ContentValues();
    //  updatedValues.put("SECURITYHINT", a);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("REPASSWORD",repassword);
        updatedValues.put("PASSWORD",repassword);

        String where="PASSWORD = ?";
        db.update("LOGIN",updatedValues, where, new String[]{a});
    }








//
//    public boolean UpdateData(String Id,String Name,String Surname,int Marks)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put(Col1,Id);
//        contentValues.put(Col2,Name);
//        contentValues.put(Col3,Surname);
//        contentValues.put(Col4,Marks);
//        db.update(Table_Name,contentValues,"Id=?",new String[]{Id});
//        return true;

//    }

























    public HashMap<String, String> getAnimalInfo(String id) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM LOGIN where SECURITYHINT='"+id+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                wordList.put("PASSWORD", cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return wordList;
    }
}