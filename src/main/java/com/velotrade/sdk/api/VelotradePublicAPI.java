package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.velotrade.sdk.entity.DebtorContact;
import com.velotrade.sdk.entity.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class VelotradePublicAPI {

    private String baseUrl;
    private String userName;
    private String password;

    public VelotradePublicAPI(String baseUrl, String userName, String password) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
    }

    public DebtorContact getDebtorContact(String id) throws Exception {
        VelotradeAPIConnection api = new VelotradeAPIConnection(this.baseUrl, this.userName, this. password);
        String request = "/debtor/"+id+"?fields=name,email,phone,debtor.address,debtor.br,debtor.city,debtor.country,debtor.humanId,debtor.legalName,debtor.tradingName,debtor.website,debtor.zipCode";
        String method = RequestMethod.GET;
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");

        String result = null;

        result = api.query(method, request, null, contentType);

        Gson gson = new Gson();
        DebtorContact debtorContact = gson.fromJson(result, DebtorContact.class);

        return debtorContact;
    }

}
