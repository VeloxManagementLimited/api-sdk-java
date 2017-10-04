package com.velotrade.sdk;

import com.velotrade.sdk.api.VelotradeAPIConnection;
import com.velotrade.sdk.entity.Auction;
import com.velotrade.sdk.entity.RequestMethod;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VelotradePublicAPITest {

    String baseUrl = "https://devapi.velotrade.com";
    String $username = "robin.walser+sel1@me.com";
    String $password = "LBlN/DMcGA/NnI7WQot3qg==";

    @Test
    public void test() {
        VelotradeAPIConnection connection = new VelotradeAPIConnection(baseUrl, $username, $password);
        String result = null;
        try {
            result = connection.query( RequestMethod.GET, "/debtor/list?fields=name,debtor,debtor.legalName", null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }
}
