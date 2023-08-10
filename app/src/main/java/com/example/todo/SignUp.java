package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final ToDoHelper ToDo = new ToDoHelper(this);

        Button SignUP_Page_Button = (Button)findViewById(R.id.But_Signup_Page);

        SignUP_Page_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ans;
                EditText UserName = (EditText)findViewById(R.id.Edit_Name_sign);
                EditText Password = (EditText)findViewById(R.id.Edit_Pass_Sign);

                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.Radio_Group);
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton)findViewById(selectedId);

                final Spinner CountryOptions = (Spinner)findViewById(R.id.Spinner_Sign);

                ans = ToDo.AddUser(String.valueOf(UserName.getText()),String.valueOf(Password.getText()),String.valueOf(radioButton.getText())
                        ,String.valueOf(CountryOptions.getSelectedItem()));

                if(ans == true)
                {
                    Toast.makeText(getApplicationContext(), "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this,MainActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(), "UserName Already Taken", Toast.LENGTH_SHORT).show();
            }
        });


    }
}