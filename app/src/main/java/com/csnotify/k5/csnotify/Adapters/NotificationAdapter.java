package com.csnotify.k5.csnotify.Adapters;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csnotify.k5.csnotify.DataHolders.Notifications;
import com.csnotify.k5.csnotify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rz
 * on 6/4/17
 */

public class NotificationAdapter extends ArrayAdapter {

    List list = new ArrayList();

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public NotificationAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     */
    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        NotificationHolder notificationHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);

            notificationHolder = new NotificationHolder();
            notificationHolder.tx_date = (TextView) row.findViewById(R.id.tx_date);
            notificationHolder.tx_message = (TextView) row.findViewById(R.id.tx_message);
            notificationHolder.tx_tag = (TextView) row.findViewById(R.id.tx_tag);

            row.setTag(notificationHolder);

        }else{
            notificationHolder = (NotificationHolder) row.getTag();
        }

        Notifications notifications = (Notifications) this.getItem(position);
        assert notifications != null;
        notificationHolder.tx_date.setText(notifications.getDate());
        notificationHolder.tx_message.setText(notifications.getMessage());
        notificationHolder.tx_tag.setText(notifications.getTag());
        return row;
    }

    private static class NotificationHolder{
        TextView tx_date,tx_message,tx_tag;
    }
}
