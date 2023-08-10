package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ToDoHelper extends SQLiteOpenHelper {

    private static String databaseName = "ToDoDatabase";
    SQLiteDatabase ToDoDatabase;
    private Context context;


    public ToDoHelper(@Nullable Context context)
    {
        super(context,databaseName,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_users = "create table User (" +
                "User_ID integer primary key AUTOINCREMENT," +
                "UserName TEXT UNIQUE," +
                "Password TEXT NOT NULL," +
                "Gender TEXT," +
                "Country TEXT );";

        String table_tasks = "create table my_task (" +
                "_id integer primary key autoincrement , " +
                "title TEXT , " +
                "description Text, " +
                "User_ID integer,"+
                "FOREIGN KEY (User_ID) " +
                "   REFERENCES User(User_ID));";
        db.execSQL(table_users);
        db.execSQL(table_tasks);

        System.out.println("Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists User");
        db.execSQL("DROP TABLE IF EXISTS my_task" );
        onCreate(db);
    }

    public boolean AddUser(String name,String pass,String gender,String Country)
    {
        boolean User;

        User = MatchedUser(name);

        if (User == true)
        {
            ContentValues row = new ContentValues();
            row.put("UserName" , name);
            row.put("Password" , pass);
            row.put("Gender" , gender);
            row.put("Country" , Country);
            ToDoDatabase = getWritableDatabase();
            ToDoDatabase.insert("User",null,row);
            ToDoDatabase.close();
            return true;
        }

        else return false;
    }

    public String FetchUser(String name,String Pass)
    {
        ToDoDatabase = getReadableDatabase();

        String[] tempinfo = {name,Pass};

        Cursor MatchedUser = ToDoDatabase.rawQuery("Select User_ID from User where UserName like ? AND PassWord like ? ",
                tempinfo);

        if(MatchedUser.getCount() != 0)
        {
            MatchedUser.moveToFirst();
            ToDoDatabase.close();
            return MatchedUser.getString(0);
        }
        else
        {
            ToDoDatabase.close();
            return null;
        }
    }

    public boolean MatchedUser(String name)
    {
        ToDoDatabase = getReadableDatabase();

        String[] tempinfo = {name};

        Cursor MatchedUser = ToDoDatabase.rawQuery("Select User_ID from User where UserName like ?",
                tempinfo);

        if(MatchedUser.getCount() == 0)
        {
            ToDoDatabase.close();
            return true;
        }
        else
        {
            ToDoDatabase.close();
            return false;
        }
    }



    public Cursor getUserData(int UserID)
    {
        ToDoDatabase = getReadableDatabase();

        //String temp1 = String.valueOf(UserID);
        //String[] temp = {temp1};

        Cursor User_Details = ToDoDatabase.rawQuery("SELECT * FROM User WHERE User_ID = " + UserID, null);

        User_Details.moveToFirst();
        ToDoDatabase.close();

        System.out.println(User_Details.getString(0).toString());

        return User_Details;
    }

    boolean addTask(String title2, String description2 , int UserID) {
        ToDoDatabase = getWritableDatabase();

        Cursor c = ToDoDatabase.rawQuery("Insert into  my_task (title,description,User_ID) values (?,?,?)", new String[]{title2, description2, Integer.toString(UserID)});
        System.out.println("Task added");

        if (c.getCount() != 0)
            return true;
        else
            return false;

    }

    Cursor readData(int UserID)
    {

        ToDoDatabase = getReadableDatabase();

        Cursor cursor = null;

        if(ToDoDatabase != null){
            cursor = ToDoDatabase.rawQuery("Select * from my_task where User_ID like ?" , new String[]{Integer.toString( UserID)});
        }
        return cursor;
    }

    boolean updateData(String row_id, String title2, String description2, int UserID)
    {
        ToDoDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title2);
        cv.put("description", description2);

        String[] temp = {row_id,String.valueOf(UserID)};

        try {
            ToDoDatabase.update("my_task", cv, "_id = ? AND User_ID = ?", new String[]{row_id, String.valueOf(UserID)});
            return true;
        }
        catch(Exception e){
            return false;
        }



    }

    boolean deleteRow(String row_id)
    {
        ToDoDatabase = getWritableDatabase();

        String[] temp = {row_id};

        long result = ToDoDatabase.delete("my_task", "_id=?", new String[]{row_id});

        if(result < 0)
        {
            //false
            return false;

        }
        else
        {
            return true;
        }
    }

    boolean deleteAllData(int UserID)
    {
        ToDoDatabase = getWritableDatabase();

        long result = ToDoDatabase.delete("my_task", "User_ID=?", new String[]{String.valueOf(UserID)});

        if(result > 0)
            return true;
        else
            return false;
    }

}


