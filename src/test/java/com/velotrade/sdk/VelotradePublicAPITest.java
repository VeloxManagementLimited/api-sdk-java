package com.velotrade.sdk;

import com.velotrade.sdk.entity.Auction;
import org.junit.Test;

import java.util.List;

public class VelotradePublicAPITest {

    VelotradePublicAPI api;

    @Test
    public void test() {
        String endpoint = "";
        String username = "";
        String password = "";

        api = new VelotradePublicAPI(endpoint, username, password);
        List<Auction> auctionsList = api.getListAuctions();
    }
}
