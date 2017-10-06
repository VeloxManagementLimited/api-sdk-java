package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.velotrade.sdk.jsonobject.TokenData;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;


public class VelotradePublicConnection extends VelotradeConnection {

    public VelotradePublicConnection(String baseUrl, String userName, String password, String loginRequest) throws Exception {
        super(baseUrl, userName, password, loginRequest);
    }

    @Override
    protected String getAuthToken() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(baseUrl + VelotradePublicAPIImpl.LOGIN_REQUEST);
        httpGet.addHeader("Content-type", "application/json");
        httpGet.addHeader("Authorization", "Velox_"+userName+":"+password);
        ResponseHandler<String> handler = new BasicResponseHandler();
        CloseableHttpResponse response = client.execute(httpGet);
        String result = handler.handleResponse(response);
        Gson gson = new Gson();
        TokenData tokenData = gson.fromJson(result, TokenData.class);
        this.entityId = tokenData.getId();
        return tokenData.getExtraData().getAuth();
    }

    private boolean isTheSameAccount(String baseUrl, String userName, String password){
        if(baseUrl.equals(this.baseUrl) && userName.equals(this.userName) && password.equals(this.password)){
            return true;
        }
        return false;
    }
}
