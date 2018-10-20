package com.example.admin.project.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.project.model.User;



public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION =1;
    private  static final String DATABASE_NAME= "UserData.db";
    private static final String TABLE_USER="user";
    private static  final String COLUMN_USER_ID="user_id";
    private static  final String  COLUMN_USER_NAME="user_name";
    private static  final String  COLUMN_USER_EMAIL="user_email";
    private static  final String  COLUMN_USER_PASSWORD="user_password";

    public DatabaseHelper(Context context)
    {
         super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT NOT NULL," + COLUMN_USER_EMAIL + " TEXT NOT NULL,"
                + COLUMN_USER_PASSWORD + " TEXT NOT NULL" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db,int i,int i1)
    {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS" + TABLE_USER;
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
    // import user from model Activity
    public void addUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USER_NAME,user.getName());
        values.put(COLUMN_USER_EMAIL,user.getEmail());
        values.put(COLUMN_USER_PASSWORD,user.getPassword());

        db.insert(TABLE_USER,null,values);
        db.close();
    }

    public boolean CheckUser(String email )
    {
        String[] columns={ COLUMN_USER_ID };
        SQLiteDatabase db=this.getWritableDatabase();
        String selection= COLUMN_USER_EMAIL + " =?";
        String[] selectionArgs={email};
        Cursor cursor= db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorcount= cursor.getCount();
        cursor.close();
        db.close();

        return cursorcount > 0;
    }

    public boolean CheckUser(String email,String password)
    {
        String[] columns={ COLUMN_USER_ID };
        SQLiteDatabase db=this.getWritableDatabase();
        String selection=COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs={email,password};

        Cursor cursor=db.query(TABLE_USER,columns,selection,selectionArgs,null,null,null);
        int cursorcount=cursor.getCount();
        cursor.close();
        db.close();

        return cursorcount > 0;
    }

}
