package com.csnotify.k5.csnotify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csnotify.k5.csnotify.BackgroundActivities.ChangePassword;

public class ChangePasswordActivity extends AppCompatActivity {



    private String oldPassword, newPassword;
    private EditText oldPasswd,newPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button myButton = (Button) findViewById(R.id.PasswordChangeButtonID);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                oldPasswd = (EditText) findViewById(R.id.OldPasswordFiledID);
                newPasswd = (EditText) findViewById(R.id.NewPasswordFiledID);
                if ( !(oldPasswd.toString().isEmpty()) ){
                    oldPassword = oldPasswd.getText().toString();
                }else {
                    Toast.makeText(getApplicationContext(),"Please insert your old password",Toast.LENGTH_SHORT).show();
                }

                if (!(newPasswd.toString().isEmpty())){
                    newPassword = newPasswd.getText().toString();
                }else{
                    Toast.makeText(getApplicationContext(),"Please insert your new password",Toast.LENGTH_SHORT).show();
                }

                String method = "changepassword";
                new ChangePassword().execute(method,oldPassword,newPassword);

                finish();
            }
        });
    }
}
