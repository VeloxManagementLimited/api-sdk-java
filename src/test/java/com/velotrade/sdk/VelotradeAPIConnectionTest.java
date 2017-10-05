package com.velotrade.sdk;

import com.velotrade.sdk.api.VelotradeConnection;
import com.velotrade.sdk.api.VelotradePublicAPI;
import com.velotrade.sdk.api.VelotradePublicConnection;
import com.velotrade.sdk.entity.RequestMethod;
import org.junit.Test;

import java.io.IOException;

public class VelotradeAPIConnectionTest {

    String baseUrl = "https://devapi.velotrade.com";
    String $username = "robin.walser+sel1@me.com";
    String $password = "LBlN/DMcGA/NnI7WQot3qg==";

    @Test
    public void test() {
        VelotradeConnection connection = null;
        try {
            connection = new VelotradePublicConnection(baseUrl, $username, $password, VelotradePublicAPI.LOGIN_REQUEST);
            assert connection != null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
