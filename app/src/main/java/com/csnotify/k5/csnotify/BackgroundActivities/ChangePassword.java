package com.csnotify.k5.csnotify.BackgroundActivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.csnotify.k5.csnotify.HomeActivity;
import com.csnotify.k5.csnotify.Tab2_fragment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rz
 * on 6/5/17
 */

public class ChangePassword extends AsyncTask<String,Void,String>{


    private Context ctx;

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
    protected String doInBackground(String... params) {
        String changePwdURL = "http://10.4.18.141:90/aau/mobileapp/changePassword.php";
        String method = params[0];

        if (method.equals("changepassword")){
            String oldPassword = params[1];
            String newPassword = params[2];

            try {
                URL loginUrl = new URL(changePwdURL);
                HttpURLConnection httpConn = (HttpURLConnection) loginUrl.openConnection();
                httpConn.setRequestMethod("POST");
                httpConn.setDoOutput(true);

                OutputStream outputStream = httpConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("oldPassword","UTF-8")+"="+URLEncoder.encode(oldPassword,"UTF-8")+"&"+
                        URLEncoder.encode("newPassword","UTF-8")+"="+ URLEncoder.encode(newPassword,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpConn.getInputStream();

                if(inputStream.read()=='s'){
                    inputStream.close();
                    return "Password Changed Successfully";
                }else {
                    inputStream.close();
                }

            } catch (MalformedURLException e) {
                Toast.makeText(ctx,"Connection Interrupted", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Old Password Mismatch";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if(result!=null){
            if (result == "Password Changed Successfully"){
                Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ctx,HomeActivity.class);
                ctx.startActivity(intent);
            }else{
                Log.d("Error","Result is still null");
            }
        }else{
            Toast.makeText(ctx,"Error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Toast.makeText(ctx, "Syncing ...",Toast.LENGTH_SHORT).show();
    }
}
