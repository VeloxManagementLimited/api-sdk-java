package com.velotrade.sdk;

import com.velotrade.sdk.api.VelotradePublicAPI;
import com.velotrade.sdk.entity.Debtor;
import com.velotrade.sdk.entity.DebtorContact;
import org.junit.Test;

import static org.junit.Assert.*;

public class VelotradePublicAPITest {

    String baseUrl = "https://devapi.velotrade.com";

    String username = "robin.walser+sel1@me.com";
    String password = "LBlN/DMcGA/NnI7WQot3qg==";

    @Test
    public void testGetDebtorContactIdEqualsExpectedId(){
        VelotradePublicAPI api = new VelotradePublicAPI(baseUrl, username, password);

        DebtorContact expectedResult = new DebtorContact();
        expectedResult.setId("oa1a6a170-d3d4-428a-835f-35ab021d410c");
        expectedResult.setEmail("robin.walser+contact1@me.com");
        expectedResult.setName("Contact 1");
        expectedResult.setPhone("123123");

        Debtor debtor = new Debtor();
        debtor.setId("e285d834e-b8d5-40f4-9dfa-6380b3d0788e");
        debtor.setAddress("ASDF");
        debtor.setBr("BR123123");
        debtor.setCity("Zurich");
        debtor.setCountry("CH");
        debtor.setHumanId("D57");
        debtor.setLegalName("Cement AG");
        debtor.setTradingName("Cement AG");
        debtor.setWebsite("http://www.cement123456.com");
        debtor.setZipcode("ASDF");

        expectedResult.setDebtor(debtor);


        String id = "oa1a6a170-d3d4-428a-835f-35ab021d410c";
        DebtorContact result = null;
        try {
            result = api.getDebtorContact(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expectedResult.getId(), result.getId());
    }

    @Test
    public void testgetAuctionStatusReturnExpectedResult(){

        String expectedResult = "UNDER_REVIEW";

        VelotradePublicAPI api = new VelotradePublicAPI(baseUrl, username, password);
        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        String result = null;
        try {
            result = api.getAuctionStatus(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(expectedResult, result);
    }

    @Test
    public void testApproveAuctionShouldReturnTrue(){
        VelotradePublicAPI api = new VelotradePublicAPI(baseUrl, username, password);

        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        boolean result = false;
        try {
            result = api.approveAuction(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(result);
    }

    @Test
    public void testRejectAuctionShouldReturnTrue(){
        VelotradePublicAPI api = new VelotradePublicAPI(baseUrl, username, password);

        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        boolean result = false;
        try {
            result = api.rejectAuction(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(result);
    }
}
