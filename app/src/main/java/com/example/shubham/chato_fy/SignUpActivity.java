package com.example.shubham.chato_fy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void gotoMainActivity(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void checkRegister(View view) {

        EditText rollNumberField = (EditText)findViewById(R.id.rollNumberField);
        EditText passwordField = (EditText)findViewById(R.id.passwordField);
        EditText repeatPasswordField = (EditText)findViewById(R.id.repeatPasswordField);

        String rollNumberFieldStr = rollNumberField.getText().toString();
        String passwordFieldStr = passwordField.getText().toString();
        String repeatPasswordFieldStr = repeatPasswordField.getText().toString();

        if(!passwordFieldStr.equals(repeatPasswordFieldStr)){

            Toast passError = Toast.makeText(this, "Passwords Don't Match!", Toast.LENGTH_LONG);
            passError.show();
        }
    }
}
