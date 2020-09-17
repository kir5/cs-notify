package com.csnotify.k5.csnotify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationHeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        ImageView dddimageView = (ImageView) findViewById(R.id.UserImagePlaceID);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user_icon);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        dddimageView.setImageDrawable(roundedBitmapDrawable);




    }
}
