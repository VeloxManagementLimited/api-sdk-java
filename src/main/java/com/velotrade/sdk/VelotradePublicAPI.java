package com.velotrade.sdk;

import com.velotrade.sdk.entity.Auction;

import java.util.List;

public class VelotradePublicAPI {

    private String endpoint;
    private String username;
    private String password;

    public VelotradePublicAPI(String endpoint, String username, String password) {
        this.endpoint = endpoint;
        this.username = username;
        this.password = password;
    }

    public List<Auction> getListAuctions() {
        return null;
    }
}
