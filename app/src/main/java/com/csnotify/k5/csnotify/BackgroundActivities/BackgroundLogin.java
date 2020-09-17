package com.csnotify.k5.csnotify.BackgroundActivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.csnotify.k5.csnotify.HomeActivity;

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
 * on 5/31/17
 */

public class BackgroundLogin extends AsyncTask<String,Void,String> {
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param methodName Method name to be executed on background.
     * @param studentId Id of the user
     * @param passkey password of the user
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */

    private Context ctx;
    private String studentID;
    public BackgroundLogin(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String login_url = "http://192.168.43.56:90/aau/mlogin.php";
        String method = params[0];
        if (method.equals("login")){
            studentID = params[1];
            String passkey = params[2];

            try {
                URL loginUrl = new URL(login_url);
                HttpURLConnection httpConn = (HttpURLConnection) loginUrl.openConnection();
                httpConn.setRequestMethod("POST");
                httpConn.setDoOutput(true);
                OutputStream outputStream = httpConn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("studentID","UTF-8")+"="+URLEncoder.encode(studentID,"UTF-8")+"&"+
                        URLEncoder.encode("passkey","UTF-8")+"="+ URLEncoder.encode(passkey,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpConn.getInputStream();
                if(inputStream.read()=='s'){
                    inputStream.close();
                    return "Login Successful";
                }

            } catch (MalformedURLException e) {
                Toast.makeText(ctx,"Connection Interrupted", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Incorrect Username or Password!";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        if (result!=null){
            if (result == "Login Successful"){
                Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ctx,LoadingActivity.class);
                ctx.startActivity(intent);
            }else{
                Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(ctx,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Toast.makeText(ctx, "Signing In ...",Toast.LENGTH_SHORT).show();
    }
}
