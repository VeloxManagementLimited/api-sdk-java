package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.velotrade.sdk.jsonobject.ParameterStringBuilder;
import com.velotrade.sdk.entity.RequestMethod;
import com.velotrade.sdk.jsonobject.TokenData;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public abstract class VelotradeConnection {

    protected String baseUrl;
    protected String userName;
    protected String password;
    protected String token;
    protected String entityId;

    protected String loginRequest = null;

    public VelotradeConnection(String baseUrl, String userName, String password, String loginRequest) throws Exception {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
        this.loginRequest = loginRequest;
        this.token = getAuthToken();

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    abstract protected String getAuthToken() throws IOException;

    public String getEntityId() {
        return entityId;
    }

    public String query(String method, String request, Map<String, String> params, Map<String, String> contentTypes) throws IOException {

        String result = null;
        int status = 0;
        String inputLine = null;
        BufferedReader in = null;
        StringBuffer content = null;
        URL url = new URL(baseUrl+request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        switch (method){
            case RequestMethod.GET:
                //create request
                connection.setRequestMethod("GET");

                //add header
                if(contentTypes != null){
                    for (Map.Entry<String, String> entry: contentTypes.entrySet()) {
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
                connection.setRequestMethod("POST");

                //add header
                if(contentTypes != null){
                    for (Map.Entry<String, String> entry: contentTypes.entrySet()) {
                        connection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                connection.setRequestProperty("Authorization", this.token);

                //add params to request
                connection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(params));
                out.flush();


                //send request
                status = connection.getResponseCode();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null){
                    content.append(inputLine);
                }

                //get result and close connection
                result = String.valueOf(content);
                out.close();
                in.close();
                connection.disconnect();
        }

        return result;

    }

}
