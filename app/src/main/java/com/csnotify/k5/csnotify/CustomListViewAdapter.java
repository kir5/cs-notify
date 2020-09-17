package com.csnotify.k5.csnotify;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by k5 on 5/18/17.
 */

public class CustomListViewAdapter extends BaseAdapter {

    private Context mcontext;
    private ArrayList<HashMap<String,String>> Courses;
    private static LayoutInflater layoutInflater = null;

    public CustomListViewAdapter(Context context, ArrayList<HashMap<String, String>> listRowInfo){

        mcontext = context;
        Courses = listRowInfo;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return Courses.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(convertView == null){
            view = layoutInflater.inflate(R.layout.listrow,null);

            ImageView lastChatSenderImage = (ImageView) view.findViewById(R.id.last_Sender_ImageID);
            TextView courseName = (TextView) view.findViewById(R.id.CoursetitleID);
            TextView lastChatSenderName = (TextView) view.findViewById(R.id.last_chat_senderID);
            TextView lastChatMessage = (TextView) view.findViewById(R.id.Last_Chat_MessageID);
            TextView datePlace = (TextView) view.findViewById(R.id.Dateplace_ID);


            HashMap<String,String> mCourses = new HashMap<>();
            mCourses= Courses.get(position);

            lastChatSenderImage.setImageDrawable(mcontext.getResources().getDrawable(R.drawable.aau_logo));
            courseName.setText(mCourses.get("CName"));
            lastChatSenderName.setText(mCourses.get("Lsname"));
            lastChatMessage.setText(mcontext.getResources().getText(R.string.last_Chat_Message));
            datePlace.setText(mCourses.get("Date"));


            // Circular imageView

            Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.aau_logo);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(view.getResources(), bitmap);
            roundedBitmapDrawable.setCircular(true);
            lastChatSenderImage.setImageDrawable(roundedBitmapDrawable);

        }

        return view;
    }
}
