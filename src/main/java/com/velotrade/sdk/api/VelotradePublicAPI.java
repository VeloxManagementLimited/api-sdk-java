package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.velotrade.sdk.entity.*;
import com.velotrade.sdk.jsonobject.AuctionStatus;

import java.util.HashMap;
import java.util.Map;

public class VelotradePublicAPI {

    private String baseUrl;
    private String userName;
    private String password;

    public static final String LOGIN_REQUEST = "/user/login/";

    public VelotradePublicAPI(String baseUrl, String userName, String password) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
    }

    public DebtorContact getDebtorContact(String id) throws Exception {
        VelotradePublicConnection api = new VelotradePublicConnection(this.baseUrl, this.userName, this. password, VelotradePublicAPI.LOGIN_REQUEST);
        String request = "/debtor/"+id+"?fields=name,email,phone,debtor.address,debtor.br,debtor.city,debtor.country,debtor.humanId,debtor.legalName,debtor.tradingName,debtor.website,debtor.zipCode";
        String method = RequestMethod.GET;
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");

        String result = api.query(method, request, null, contentType);

        Gson gson = new Gson();
        DebtorContact debtorContact = gson.fromJson(result, DebtorContact.class);

        return debtorContact;
    }

    public String getAuctionStatus(String id) throws Exception {
        VelotradePublicConnection api = new VelotradePublicConnection(this.baseUrl, this.userName, this. password, VelotradePublicAPI.LOGIN_REQUEST);

        String request = "/auction/"+id+"?fields=status";
        String method = RequestMethod.GET;
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");

        String result = api.query(method, request, null, contentType);

        Gson gson = new Gson();
        AuctionStatus auctionStatus = gson.fromJson(result, AuctionStatus.class);
        return auctionStatus.getStatus();
    }

    public boolean rejectAuction(String id) throws Exception {
        VelotradePublicConnection api = new VelotradePublicConnection(this.baseUrl, this.userName, this. password, VelotradePublicAPI.LOGIN_REQUEST);

        String method = RequestMethod.POST;
        String request = "/"+id+"/reject";
        Map<String, String> params = new HashMap<>();
        params.put("auctionId", id);

        Map<String, String> contentType = new HashMap<>();
        contentType.put("content-type", "application/json;charset=UTF-8");

        String result = api.query(method, request, params, contentType);

        return result == null;
    }

    public boolean approveAuction(String id) throws Exception {
        VelotradePublicConnection api = new VelotradePublicConnection(this.baseUrl, this.userName, this. password, VelotradePublicAPI.LOGIN_REQUEST);

        String method = RequestMethod.POST;
        String request = "/"+id+"/approve";
        Map<String, String> params = new HashMap<>();
        params.put("auctionId", id);

        Map<String, String> contentType = new HashMap<>();
        contentType.put("content-type", "application/json;charset=UTF-8");

        String result = api.query(method, request, params, contentType);

        return result == null;
    }


}
