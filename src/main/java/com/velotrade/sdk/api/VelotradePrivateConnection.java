package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.velotrade.sdk.jsonobject.TokenData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class VelotradePrivateConnection extends VelotradeConnection {

    public VelotradePrivateConnection(String baseUrl, String userName, String password, String loginRequest) throws Exception {
        super(baseUrl, userName, password, loginRequest);
    }

    @Override
    protected String getAuthToken() throws IOException {
        //create Request
        URL url = new URL(baseUrl+VelotradePublicAPI.LOGIN_REQUEST);
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
}
