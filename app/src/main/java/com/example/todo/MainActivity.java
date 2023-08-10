package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToDoHelper ToDo = new ToDoHelper(this);

        Button SignUp_Btn = (Button)findViewById(R.id.but_SignUp);

        SignUp_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_SignUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent_SignUp);
            }
        });

        Button Login_Btn = (Button)findViewById(R.id.but_Login);

        Login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText UserName = (EditText) findViewById(R.id.Edit_Name_Login);
                EditText Password = (EditText) findViewById(R.id.Edit_Pass_Login);
                try {
                    String User;

                    User = ToDo.FetchUser(String.valueOf(UserName.getText()), String.valueOf(Password.getText()));

                    System.out.println(User);

                    if (User == null) {
                        Toast.makeText(getApplicationContext(), "Invalid UserName or Password", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                    int User_ID = Integer.parseInt(User);


                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    i.putExtra("ID", User_ID);
                    startActivity(i);
                }
                catch(Exception exception){

                }
            }

        });

    };
}