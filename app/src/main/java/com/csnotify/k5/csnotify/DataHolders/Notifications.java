package com.csnotify.k5.csnotify.DataHolders;

/**
 * Created by rz
 * on 5/17/17
 * Holds each notification data
 * that comes form the server.
 */

public class Notifications {

    private String date,message,tag;

    /**Constructor
     *
     * @param date - for holding the date value
     * @param message - for holding the actual notification message
     * @param  tag  - for holding the tag of the notification message
     * */

    public Notifications(String date,String message,String tag){
        this.setDate(date);
        this.setMessage(message);
        this.setTag(tag);

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
