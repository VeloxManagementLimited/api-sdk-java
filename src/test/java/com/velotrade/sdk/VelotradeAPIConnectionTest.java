package com.velotrade.sdk;

import com.velotrade.sdk.api.VelotradeConnection;
import com.velotrade.sdk.api.VelotradePublicAPIImpl;
import com.velotrade.sdk.api.VelotradePublicConnection;
import org.junit.Test;

public class VelotradeAPIConnectionTest {

    String baseUrl = "https://devapi.velotrade.com";
    String $username = "<username>";
    String $password = "<password>";

    @Test
    public void test() {
        VelotradeConnection connection = null;
        try {
            connection = new VelotradePublicConnection(baseUrl, $username, $password, VelotradePublicAPIImpl.LOGIN_REQUEST);
            assert connection != null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
