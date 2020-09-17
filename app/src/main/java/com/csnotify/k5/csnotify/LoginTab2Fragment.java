package com.csnotify.k5.csnotify;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoginTab2Fragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_tab2_fragment,container,false);

        Button startbutton = (Button) view.findViewById(R.id.StartHelpButton);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enterHelpPage = new Intent(getContext(), HelpActivity.class);
                startActivity(enterHelpPage);
            }
        });


        return view;

    }
}
