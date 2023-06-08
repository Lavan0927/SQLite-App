package com.example.sqllitedbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student_table (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT, marks INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS student_table");
        onCreate(db);
    }

    public boolean insertToDb(String name, String surname, int marks){
        SQLiteDatabase db=this.getReadableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("surname",surname);
        contentValues.put("marks",marks);
        long result=db.insert("student_table",null,contentValues);
        //second parameter for selecting column. In this case not required
        boolean isDataEntered=false;
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("Select * from student_table",null);
        return result;
    }

    public Cursor getIds(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("Select id from student_table",null);
        return result;
    }

    public Cursor getDataFromID(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("Select * from student_table where id=?",new String[]{id});
        return result;
    }

    public boolean updateData(String id,String name, String surname, int marks){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name",name);
        contentValues.put("surname",surname);
        contentValues.put("marks",marks);
        db.update("student_table",contentValues,"id=?",new String[]{id});
        return true;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("student_table", "id=?", new String[]{id});
        return true;
    }
}
