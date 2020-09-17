package com.csnotify.k5.csnotify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.csnotify.k5.csnotify.BackgroundActivities.BackgroundLogin;

public class LoginTab1Fragment extends Fragment {
    private String studentID,pwd;
    private EditText studentid,password;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_login_tab1_fragment,container,false);

        // Loging in
        Button loginbutton = (Button) view.findViewById(R.id.LoginButtonID);
        handleClick(loginbutton);

        return view;
    }
    public void handleClick(Button login){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentid = (EditText) view.findViewById(R.id.EnterIDFieldID);
                password = (EditText) view.findViewById(R.id.EnterPasswordFieldID);
                studentID = studentid.getText().toString();
                pwd = password.getText().toString();
                String method = "login";

                BackgroundLogin loginBack = new BackgroundLogin(view.getContext());

                loginBack.execute(method,studentID,pwd);
            }
        });
    }

    public View getView(){
        return view;
    }
}
