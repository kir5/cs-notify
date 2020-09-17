package com.csnotify.k5.csnotify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.csnotify.k5.csnotify.Adapters.NotificationAdapter;
import com.csnotify.k5.csnotify.BackgroundActivities.LoadingActivity;
import com.csnotify.k5.csnotify.DataHolders.Notifications;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by k5
 * on 5/18/17
 */

public class Tab2_fragment extends Fragment {


    String JSON_data;
    String date,message,tag;
    JSONObject JSON_object;
    JSONArray JSON_array;
    NotificationAdapter notificationAdapter;
    ListView listView;
    private View view;


    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        notificationAdapter = new NotificationAdapter(getActivity().getApplicationContext(),R.layout.row_layout);
        listView = (ListView) view.findViewById(R.id.list_view);
        assert listView != null;
        listView.setAdapter(notificationAdapter);
        JSON_data = getActivity().getIntent().getExtras().getString("json_data");
        parseJson(JSON_data);

    }

    private void parseJson(String json_data) {
        if (json_data!=null){

            try {

                int count = 0;

                JSON_object = new JSONObject(json_data);
                JSON_array = JSON_object.getJSONArray("server_response");

                while (count < JSON_array.length()){

                    JSONObject raw_data_Object = JSON_array.getJSONObject(count);

                /*
                * date - Add each date item to the string date
                * message - add each message item to the string message
                * tag - add each tag item to the string tag */

                    date = raw_data_Object.getString("date");
                    message = raw_data_Object.getString("message");
                    tag = raw_data_Object.getString("tag");

                /*@class Notifications
                *
                * Creates an object for each date, message and tag
                * item parsed from the JSON Array and adds
                * theme to the notification adapter*/

                    Notifications notification_data = new Notifications(date,message,tag);
                    notificationAdapter.add(notification_data);
                    count++;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getActivity().getApplicationContext(),"Unable to get Json",Toast.LENGTH_LONG).show();
        }
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2_fragment,container,false);
        return view;
    }


}





