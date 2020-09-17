package com.csnotify.k5.csnotify.BackgroundActivities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.csnotify.k5.csnotify.HomeActivity;
import com.csnotify.k5.csnotify.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadingActivity extends AppCompatActivity {

    private String studentID;
    public LoadingActivity(){
        super();
    }

    public LoadingActivity(String refresh) {
        if (refresh == "refresh"){
            FetchNotification fetchNotification = new FetchNotification();
            fetchNotification.execute();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

            FetchNotification fetchNotification = new FetchNotification();
            fetchNotification.execute();
            finish();

    }
private class FetchNotification extends AsyncTask<Void,Void,String>{

    private String notification_data;

    FetchNotification( ) {
        super();
    }
        /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */

    @Override
    protected String doInBackground(Void... params) {
        try {
            String json_url = "http://192.168.43.56:90/aau/mobileapp/getjson.php";
            URL url = new URL(json_url);
            HttpURLConnection httpURLConn= (HttpURLConnection) url.openConnection();
            httpURLConn.setRequestMethod("POST");
            InputStream inputStream = httpURLConn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String raw_data;
            while ((raw_data = bufferedReader.readLine()) != null ){
                stringBuilder.append(raw_data).append("\n");
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConn.disconnect();
            this.notification_data = stringBuilder.toString().trim();
            return stringBuilder.toString().trim();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Unable to fetch data!";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String result) {
        // Set explicit intent to send the data to the parser - here
        if (result!=null){
            this.notification_data = result;
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("json_data",getNotificationData());
            startActivity(intent);
//                TextView viewJson = (TextView) view.findViewById(R.id.textView);
//                viewJson.setText(result);
//                Intent intent = new Intent(ctx.getApplicationContext(), HomeActivity.class);
//                ctx.startActivity(intent);
        }
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public String getNotificationData(){
        return notification_data;
    }
 }
}