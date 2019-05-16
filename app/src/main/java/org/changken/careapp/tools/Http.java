package org.changken.careapp.tools;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * @deprecated
 * */
public class Http {
    private String apiKey;

    public Http(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public StringBuffer doGet(String urlAddress){
        return getWebContent(urlAddress, "GET");
    }

    public StringBuffer doDelete(String urlAddress){
        return getWebContent(urlAddress, "DELETE");
    }

    public StringBuffer doPost(String urlAddress, String paramsData){
        return sendWebRequest(urlAddress, paramsData, "POST");
    }

    public StringBuffer doPatch(String urlAddress, String paramsData){
        return sendWebRequest(urlAddress, paramsData, "PATCH");
    }

    public StringBuffer doPut(String urlAddress, String paramsData){
        return sendWebRequest(urlAddress, paramsData, "PUT");
    }

    private StringBuffer getWebContent(String urlAddress, String method){
        //changeable String
        StringBuffer data = new StringBuffer();

        try {
            //get url object
            URL url = new URL(urlAddress);
            //get HttpsURLConnection
            HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
            //Set Header Variable
            uc.setRequestMethod(method);
            uc.setRequestProperty("Authorization", "Bearer " + apiKey);
            //Response Code
            switch (uc.getResponseCode()) {
                case 200:
                case 201:
                    //Open InputStream implicit uc.connect();
                    //Use some reader to get http content
                    InputStream inputStream = uc.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    //Get Http Response Content
                    String str = "";
                    while ((str = bufferedReader.readLine()) != null) {
                        data.append(str);
                        //Log.i("MainActivity", "進入迴圈");
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
            }
            //close http connect
            uc.disconnect();
        } catch (IOException e) {
            Log.e("MainActivity", e.getMessage());
        }

        return data;
    }

    private StringBuffer sendWebRequest(String urlAddress, String paramsData, String method){
        StringBuffer responseData = new StringBuffer();
        try{
            URL url = new URL(urlAddress);
            HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
            uc.setRequestMethod(method);
            uc.setRequestProperty("Authorization", "Bearer " + apiKey);
            uc.setRequestProperty("encoding", "UTF-8");
            uc.setRequestProperty("Content-Type", "application/json");
            uc.setRequestProperty("Accept", "application/json");
            uc.setDoOutput(true);
            uc.setDoInput(true);

            //send data
            OutputStream outputStream = uc.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(paramsData);
            bufferedWriter.flush();

            //get response
            InputStream inputStream = uc.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //get response data
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                responseData.append(line);
            }

            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();

            outputStream.close();
            outputStreamWriter.close();
            bufferedWriter.close();

            uc.disconnect();
        }catch (IOException e){
            Log.e("MainActivity", e.getMessage());
        }

        return responseData;
    }
}
