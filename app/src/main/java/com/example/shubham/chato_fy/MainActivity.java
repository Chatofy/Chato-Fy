package com.example.shubham.chato_fy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DbHelper helper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkLogin(View view){
        EditText rollNumberTextInput = (EditText)findViewById(R.id.rollNumberTextInput);
        String rollNumberTextInputStr = rollNumberTextInput.getText().toString();
        EditText passwordTextInput = (EditText)findViewById(R.id.passwordTextInput);
        String passwordTextInputStr = passwordTextInput.getText().toString();

        String pass = helper.searchPassword(rollNumberTextInputStr);

        if(passwordTextInputStr.equals(pass)){
            Toast success = Toast.makeText(this, "Login Successfull!", Toast.LENGTH_LONG);
            success.show();
            Intent i = new Intent(this, ContactListActivity.class);
            startActivity(i);
        }
        else{
            Toast error = Toast.makeText(this, "Invalid Login!", Toast.LENGTH_LONG);
            error.show();
        }
    }

    public void goToSignUpActivity(View view){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
    public void gotoForgotPasswordActivity(View view){
        Intent i = new Intent(this, ForgotPasswordActivity.class);
        startActivity(i);
    }
}
