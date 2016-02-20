package com.example.shubham.chato_fy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
