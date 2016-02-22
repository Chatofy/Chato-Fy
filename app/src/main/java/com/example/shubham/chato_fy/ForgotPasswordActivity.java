package com.example.shubham.chato_fy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    DbHelper helper = new DbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void gotoMainActivity(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void changePassword(View view){
        EditText forgotRollnumberField = (EditText)findViewById(R.id.forgotRollnumberField);
        EditText forgotNewPasswordField = (EditText)findViewById(R.id.forgotNewPasswordField);
        EditText forgotConfirmPasswordField = (EditText)findViewById(R.id.forgotConfirmPasswordField);

        String forgotRollnumberFieldStr = forgotRollnumberField.getText().toString();
        String forgotNewPasswordFieldStr = forgotNewPasswordField.getText().toString();
        String forgotConfirmPasswordFieldStr = forgotConfirmPasswordField.getText().toString();

        boolean flag = helper.searchRoll(forgotConfirmPasswordFieldStr);

        if(!forgotNewPasswordFieldStr.equals(forgotConfirmPasswordFieldStr)){
            //PopUp Message
            Toast passError = Toast.makeText(this, "Passwords Don't Match!", Toast.LENGTH_LONG);
            passError.show();
        }
        else if(!flag){
            Toast passError = Toast.makeText(this, "User Not Found!", Toast.LENGTH_LONG);
            passError.show();
        }
        else{
            Contact c = new Contact();
            //c.setRollNumber(rollNumberFieldStr);
            //c.setPassword(passwordFieldStr);
            helper.updateDetails(c,forgotRollnumberFieldStr,forgotNewPasswordFieldStr);

            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            Toast cool = Toast.makeText(this,"Password Updated! Please Login To Continue",Toast.LENGTH_LONG);
            cool.show();
        }
    }
}
