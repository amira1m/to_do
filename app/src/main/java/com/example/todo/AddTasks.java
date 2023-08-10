package com.example.todo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        EditText e1=(EditText) findViewById(R.id.title1);
        EditText e2=(EditText) findViewById(R.id.descriptin1);
        Button b2=(Button) findViewById(R.id.button);

        int User_ID = getIntent().getExtras().getInt("ID");

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoHelper DB=new ToDoHelper(AddTasks.this);
                boolean temp;
                temp = DB.addTask(e1.getText().toString(),e2.getText().toString(),User_ID);

                if(temp)
                    Toast.makeText(getApplicationContext(),"Task Added Successfully",Toast.LENGTH_SHORT);

                else
                    Toast.makeText(getApplicationContext(),"Task Failed To Be Added!",Toast.LENGTH_SHORT);

                Intent i = new Intent(AddTasks.this,MainActivity2.class);
                i.putExtra("ID",User_ID);
                startActivity(i);
            }
        });
    }
}