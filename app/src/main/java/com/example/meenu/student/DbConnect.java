package com.example.meenu.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by meenu on 20-Aug-16.
 */
public class DbConnect extends SQLiteOpenHelper {
    SQLiteDatabase db;
    DbConnect dc;
    Context context;

    String DATABASE_NAME = "Studentdb";
    String TABLE_NAME1="student";
    String ID="ID";
    String NAME="NAME";

    public DbConnect(Context context) {

        super(context,"Studentdb", null,1);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE IF NOT EXISTS STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME VARCHAR,EMAIL TEXT,MOBILE TEXT,COURSE TEXT)") ;
        db.execSQL("CREATE TABLE IF NOT EXISTS COURSE(COURSE VARCHAR,DURATION TEXT)") ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DbConnect open(){

        DbConnect dc = new DbConnect(context);

        db = dc.getWritableDatabase();
        return this;
    }




    public void create(String name, String email, String mobile,String course) {

        // TODO Auto-generated method stub
        ContentValues cv=new ContentValues();

        cv.put("NAME", name);
        cv.put("EMAIL", email);
        cv.put("MOBILE", mobile);
        cv.put("COURSE",course);
        db.insert("student", null, cv);
    }

    public void createcourse(String course,String duration) {
        // TODO Auto-generated method stub
        ContentValues cv1=new ContentValues();
        cv1.put("COURSE", course);
        cv1.put("DURATION", duration);

         db.insert("course",null,cv1);
    }
    public void close(){
        db.close();
    }

    public String getAllData(int row) {
        Cursor c= db.rawQuery("SELECT * FROM student",null);

        if(c.moveToPosition(row)) {
            String result = "";
            for(int columnIndex = 0; columnIndex<c.getColumnCount();columnIndex++) {
                result += "\t"+c.getColumnName(columnIndex)+":\t"+c.getString(columnIndex)+"\n\n ";
            }
            return result;
        } else {
            throw new IllegalArgumentException("Row " + row + " does not exist");
        }
    }
    public boolean delete(int id) {
try{
        int result=db.delete("student","ID"+"=?",new String[] {String.valueOf(id)});
   if(result>0){
       return true;
   }

    }catch(SQLException e){
    e.printStackTrace();
        }
        return false;
    }


        }




