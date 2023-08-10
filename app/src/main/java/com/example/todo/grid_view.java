package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class grid_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int User_ID = getIntent().getExtras().getInt("ID");

        setContentView(R.layout.activity_grid_view);
        String[] myArr = getResources().getStringArray(R.array.grid);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myArr);
        GridView myGrid = (GridView) findViewById(R.id.grid);

        myGrid.setAdapter(adap);

        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView t1 = (TextView) view;
                String value = t1.getText().toString();

                switch (value){
                    case "Your Tasks":
                        Intent to_tasks =new Intent(grid_view.this,MainActivity2.class);
                        to_tasks.putExtra("ID", User_ID);
                        startActivity(to_tasks);
                        break;
                    case "Add Task":
                        Intent add_tasks =new Intent(grid_view.this,AddTasks.class);
                        add_tasks.putExtra("ID", User_ID);
                        startActivity(add_tasks);
                        break;
                    case "Your Profile":
                        Intent Your_Profile =new Intent(grid_view.this,Profile.class);
                        Your_Profile.putExtra("ID", User_ID);
                        startActivity(Your_Profile);
                        break;
                    case "About Us":
                        Intent About_Us =new Intent(grid_view.this,AboutUs.class);
                        startActivity(About_Us);
                        break;
                    case "Logout":
                        Intent Logout =new Intent(grid_view.this,MainActivity.class);
                        startActivity(Logout);
                        break;

                }

            }
        });
    }
}