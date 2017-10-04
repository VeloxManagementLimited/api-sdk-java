package com.velotrade.sdk.api;

import com.velotrade.sdk.entity.DebtorContact;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IVelotradePublicAPI {

    @Headers("Content-type: application/json")
    @GET("/debtor/$id?fields=name,email,phone,debtor.address,debtor.br," +
            "debtor.city,debtor.country,debtor.humanId,debtor.legalName,debtor.tradingName," +
            "debtor.website,debtor.zipCode")
    Call<DebtorContact> getDebtorContact(@Path("id") int id);

}
