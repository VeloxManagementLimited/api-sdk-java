package com.velotrade.sdk.api;

import com.velotrade.sdk.entity.DebtorContact;

import java.io.IOException;

public class VelotradePublicAPI {

    private String baseUrl;
    private String userName;
    private String password;

    public VelotradePublicAPI(String baseUrl, String userName, String password) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
    }

}
