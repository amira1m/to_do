package com.example.todo;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ToDoHelper ToDo = new ToDoHelper(this);

        int User_ID = getIntent().getExtras().getInt("ID");

        Cursor getUserData = ToDo.getUserData(User_ID);


        if (getUserData != null) {
            ((TextView) findViewById(R.id.Text_ID)).setText("ID:  " + getUserData.getString(0));
            ((TextView) findViewById(R.id.Text_Name)).setText("Name:  " + getUserData.getString(1));
            ((TextView) findViewById(R.id.Text_Pass)).setText("Password:  " + getUserData.getString(2));
            ((TextView) findViewById(R.id.Text_Gender)).setText("Gender:  " + getUserData.getString(3));
            ((TextView) findViewById(R.id.Text_Country)).setText("Country:  " + getUserData.getString(4));
        }
    }
}