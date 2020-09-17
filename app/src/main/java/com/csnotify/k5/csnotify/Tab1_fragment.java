package com.csnotify.k5.csnotify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by k5 on 5/18/17.
 */

public class Tab1_fragment extends Fragment {

    private ListView listView;
    private CustomListViewAdapter customListViewAdapter;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.tab1_fragment,container,false);

       final String[] Course_names = new String[]{
               "Advanced Database Systems",
               "Advanced Programming",
               "Elective 2 Windows Programming",
               "Fundamentals of Software Engineering",
               "Internet Programming",
               "Entrpreneurship & Small Buisness Management",
               "Formal Language and Automata Theory",
               "Introduction to Artificial Intelligence",
               "Network and System Administration",
               "Object Oriented Software Engineering",
               "Wireless Communication and Wireless Computing"
       };

       final String[] Last_Chat_Sender_names = new String[]{
               "Natnael Tibebu",
               "Tewdros Messay",
               "Yirga Teklay",
               "Rabra Hierpa",
               "Kirubel Solomon",
               "Melaku Habtu",
               "Nathan Damtew",
               "Teacher Leykun",
               "Wagari Garoma",
               "Dr. Ayelew",
               "Teacher Biruk T."
       };

       final String[] DateTime = new String[]{
               "Mar 23 02:38 AM",
               "Jan 10 12:24 PM",
               "Feb 17 04:45 PM",
               "Mar 29 03:02 PM",
               "Mar 12 08:10 AM",
               "Sun 10:44 AM",
               "Fri 01:33 PM",
               "Thr 11:55 PM",
               "Fri 04:01 PM",
               "Wed 07:19 AM",
               "Wed 11:59 AM"
       };

       ArrayList<HashMap<String,String>> listRowInfo = new ArrayList<>();

       for (int i=0; i<11; i++) {
           HashMap<String,String> data = new HashMap<>();
           data.put("CName",Course_names[i]);
           data.put("Lsname",Last_Chat_Sender_names[i]);
           data.put("Date",DateTime[i]);

           listRowInfo.add(data);
       }


       listView = (ListView) view.findViewById(R.id.myListViewID);

       //setup adapter


       customListViewAdapter = new CustomListViewAdapter(getContext(), listRowInfo);
       listView.setAdapter(customListViewAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Intent changeChatroomIntent = new Intent(getContext(), ChatRooms.class);
               startActivity(changeChatroomIntent);

           }
       });



       return view;
   }
}
