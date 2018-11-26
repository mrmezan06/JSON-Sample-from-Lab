package com.mezan.json_application;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HTTPHandler {

    private static final String TAG=HTTPHandler.class.getSimpleName();
    public HTTPHandler(){

    }
    public String makeServiceCall(String reqURL) throws IOException {
        String response=null;
        try{
            URL url=new URL(reqURL);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in=new BufferedInputStream(connection.getInputStream());
            response=convertStreamtoString(in);
        }catch (MalformedURLException e){
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        }catch (ProtocolException e){
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        }catch (IOException e){
            Log.e(TAG, "IOException: " + e.getMessage());
        }catch (Exception e){
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamtoString(InputStream in) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuilder sb=new StringBuilder();
        String line;
        try {
            while ((line=reader.readLine()) != null){
                sb.append(line).append("\n");
            }
        }catch (IOException e){
                e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
