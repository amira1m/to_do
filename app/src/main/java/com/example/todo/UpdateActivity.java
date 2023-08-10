package com.example.todo;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText edit_title,edit_description;
    Button button_Update,button_delete;
    String id,title,description;
    int User_ID ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edit_title=(EditText) findViewById(R.id.title2);
        edit_description=(EditText) findViewById(R.id.descriptin2);
        button_Update=(Button) findViewById(R.id.button1);
        button_delete=(Button) findViewById(R.id.button2);
        User_ID = getIntent().getExtras().getInt("ID");



        GetAndSetData();
        ActionBar ab=getSupportActionBar();
        if(ab!=null){
            ab.setTitle(title);
        }
        button_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean temp;

                ToDoHelper DB=new ToDoHelper(UpdateActivity.this);

                title = edit_title.getText().toString();

                description = edit_description.getText().toString();

                temp = DB.updateData(id,title,description,User_ID);

                if(temp)
                {
                    Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT);
                    Intent i = new Intent(UpdateActivity.this,MainActivity2.class);
                    i.putExtra("ID",User_ID);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT);

            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Warning_delete();
            }
        });



    }

    void GetAndSetData()
    {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("description")) {
            //Getting Data from Intent

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            //Setting Intent Data
            edit_title.setText(title);
            edit_description.setText(description);


        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void Warning_delete()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ToDoHelper DB = new ToDoHelper(UpdateActivity.this);

                boolean temp;

                temp = DB.deleteRow(id);

                if(temp)
                {
                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, MainActivity2.class);
                    intent.putExtra("ID", User_ID);
                    finish();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Failed To Delete!", Toast.LENGTH_SHORT).show();

                }


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }
}

