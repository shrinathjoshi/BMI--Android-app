package com.example.hp.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Hp on 6/24/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Context context;

    public DatabaseHandler(Context context) {
        super(context,"Bmidb", null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table Bmi(id integer primary key AUTOINCREMENT,bmi real,created_at datetime default  current_timestamp)");
        Toast.makeText(context, "Table c", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

   }

    public void addEntry(double bmi)
    {
        ContentValues values=new ContentValues();
        values.put("bmi",bmi);
        long rid=db.insert("Bmi",null,values);

        if(rid<0)
        {
            Toast.makeText(context, "Insert Issue", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, ""+values, Toast.LENGTH_SHORT).show();
        }

    }

/*    public  String viewDetails()
    {
        StringBuffer sb=new StringBuffer();

        Cursor cursor=db.query("Bmi",null,null,null,null,null,null);

        cursor.moveToFirst();

        if(cursor!=null&&(cursor.getCount()>0))
        {
            do {

                int id =cursor.getInt(0);
                String bmi=cursor.getString(1);
                String dt=cursor.getString(2);

                sb.append("id : "+id+" BMI :-"+bmi+" Datimey:-"+dt);

            }while (cursor.moveToNext());
        }
        else
        {
            sb.append("no record to show");
        }

        return  sb.toString();
    }

*/

    public  Cursor getInfo()
    {
        Cursor cursor=db.query("Bmi",null,null,null,null,null,null);
        return  cursor;
    }


}
