package com.velotrade.sdk.api;

import com.velotrade.sdk.entity.DebtorContact;
import retrofit2.Retrofit;

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

    public DebtorContact getDebtorContact(int id) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .build();

        IVelotradePublicAPI service = retrofit.create(IVelotradePublicAPI.class);

        return service.getDebtorContact(id).execute().body();
    }
}
