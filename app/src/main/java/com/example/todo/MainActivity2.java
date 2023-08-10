package com.example.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<String> id,title,description;
    ToDoHelper db1;
    Adapterr AD1;
    int User_ID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        User_ID = getIntent().getExtras().getInt("ID");

        RecyclerView r1=(RecyclerView) findViewById(R.id.recyclerView);

        FloatingActionButton add_btn=(FloatingActionButton) findViewById(R.id.add_button);

        Button btn_grid = (Button) findViewById(R.id.grid_btn);
        btn_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Y = new Intent(MainActivity2.this,grid_view.class);
                Y.putExtra("ID", User_ID);
                startActivity(Y);
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity2.this,AddTasks.class);
                intent.putExtra("ID", User_ID);
                startActivity(intent);
            }
        });


        db1 =new ToDoHelper(MainActivity2.this);
        id=new ArrayList<>();
        title=new ArrayList<>();
        description=new ArrayList<>();
        GetAllData();
        AD1=new Adapterr(MainActivity2.this,MainActivity2.this,id,title,description,User_ID);
        r1.setAdapter(AD1);
        r1.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void GetAllData(){

        Cursor cursor= db1.readData(User_ID);

        if(cursor.getCount()!=0){
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                description.add(cursor.getString(2));
            }
        }
        else{
            Toast.makeText(this,"NO DATA",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.Delelte_All){
            Warning_delete();
        }
        return super.onOptionsItemSelected(item);
    }

    void Warning_delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All " );
        builder.setMessage("Are you sure you want to delete all? " );
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                boolean temp;

                ToDoHelper DB = new ToDoHelper(MainActivity2.this);
                temp = DB.deleteAllData(User_ID);

                if(temp)
                {
                    //Refresh Activity
                    Toast.makeText(getApplicationContext(),"Delete Successful",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
                    intent.putExtra("ID", User_ID);
                    startActivity(intent);

                    finish();
                }
                else Toast.makeText(getApplicationContext(),"Delete Unsuccessful",Toast.LENGTH_SHORT).show();
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