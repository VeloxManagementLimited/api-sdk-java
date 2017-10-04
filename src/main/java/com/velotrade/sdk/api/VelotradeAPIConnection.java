package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.velotrade.sdk.entity.ParameterStringBuilder;
import com.velotrade.sdk.entity.RequestMethod;
import com.velotrade.sdk.entity.TokenData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class VelotradeAPIConnection {

    private String baseUrl;
    private String userName;
    private String password;
    private String token;
    private String entityId;

    public static final String USER_LOGIN = "/user/login/";

    public VelotradeAPIConnection(String baseUrl, String userName, String password) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
        try {
            this.token = getAuthToken();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    private String getAuthToken() throws Exception {

        //create Request
        URL url = new URL(baseUrl+USER_LOGIN);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        //add header
        connection.setRequestProperty("Content-type", "application/json");
        connection.setRequestProperty("Authorization", "Velox_"+userName+":"+password);

        //send request
        int status = connection.getResponseCode();

        //get response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        //convert to object
        Gson gson = new Gson();
        TokenData tokenData = gson.fromJson(String.valueOf(content), TokenData.class);

        //get userID
        this.entityId = tokenData.getId();

        // return token
        return tokenData.getExtraData().getAuth();
    }

    public String getEntityId() {
        return entityId;
    }

    public String query(String method, String request, Map<String, String> params, Map<String, String> contentTypes) throws IOException {

        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        int status = 0;
        String inputLine = null;
        BufferedReader in = null;
        StringBuffer content = null;

        switch (method){
            case RequestMethod.GET:
                //create request
                url = new URL(baseUrl+request);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                //add header
                if(contentTypes != null){
                    for (Map.Entry<String, String> entry: params.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                connection.setRequestProperty("Authorization", this.token);

                //send request
                status = connection.getResponseCode();

                //get response
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null){
                    content.append(inputLine);
                }

                //get result and close connection
                result = String.valueOf(content);
                in.close();
                connection.disconnect();

                break;
            case RequestMethod.POST:
                //create request
                url = new URL(baseUrl+request);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(RequestMethod.POST);

                //add header
                if(contentTypes != null){
                    for (Map.Entry<String, String> entry: params.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                connection.setRequestProperty("Authorization", this.token);

                //add params to request
                connection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(params));
                out.flush();
                out.close();

                //send request
                status = connection.getResponseCode();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null){
                    content.append(inputLine);
                }

                //get result and close connection
                result = String.valueOf(content);
                in.close();
                connection.disconnect();
        }

        return result;

    }

}
