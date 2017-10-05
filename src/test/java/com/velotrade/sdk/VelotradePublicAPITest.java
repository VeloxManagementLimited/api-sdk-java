package com.velotrade.sdk;

import com.velotrade.sdk.api.VelotradePublicAPI;
import com.velotrade.sdk.entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VelotradePublicAPITest {

    String baseUrl = "https://devapi.velotrade.com";
    String username = "robin.walser+sel1@me.com";
    String password = "LBlN/DMcGA/NnI7WQot3qg==";

    VelotradePublicAPI api;

    @Before
    public void createVelotradePublicAPI(){
        api = new VelotradePublicAPI(baseUrl, username, password);
    }

    @Test
    public void testGetDebtorContactIdEqualsExpectedId(){


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
            assertEquals(expectedResult.getId(), result.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testgetAuctionStatusReturnExpectedResult(){

        String expectedResult = "UNDER_REVIEW";
        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        String result = null;
        try {
            result = api.getAuctionStatus(id);
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testApproveAuctionShouldReturnTrue(){
        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        boolean result = false;
        try {
            result = api.approveAuction(id);
        } catch (Exception e) {
            assertEquals("An error has occurred while approving the auction", e.getMessage());
        }

    }

    @Test
    public void testRejectAuctionShouldReturnTrue(){
        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        boolean result = false;
        try {
            result = api.rejectAuction(id);
        } catch (Exception e) {
            assertEquals("An error has occurred while approving the auction", e.getMessage());
        }

    }

    @Test
    public void testGetDebtorContacts(){
        try {
            List<DebtorContact> result  = api.getDebtorContacts();
            assertEquals(2, result.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUploadFileShouldReturnNull(){
        try {
            Attachment result = api.uploadAttachment("/home/hieu/icon.png");
            assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void createAuctionShouldNotReturnNull(){
        String id = "oa1a6a170-d3d4-428a-835f-35ab021d410c";
        Invoice invoice = new Invoice();
        invoice.setNumber("TEST");
        invoice.setIssueDate("2017-09-28T16:00:00.000Z");
        invoice.setCurrency("USD");
        invoice.setAmount(10000);
        invoice.setExpectedAmount(10000);
        invoice.setPaymentTerms(6000);
        invoice.setDueDate("2017-11-27T16:00:00.000Z");
        invoice.setExpectedPaymentDate("2017-11-27T16:00:00.000Z");
        invoice.setDescription("TEST");


        try {
            Attachment attachment = api.uploadAttachment("/home/hieu/icon.png");
            DebtorContact debtorContact = api.getDebtorContact(id);
            Auction auction = new Auction(debtorContact, invoice, attachment, attachment, attachment);

            String result = api.createAuction(auction);
            assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
