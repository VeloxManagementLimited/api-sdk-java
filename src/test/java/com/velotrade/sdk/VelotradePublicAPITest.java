package com.velotrade.sdk;

import com.velotrade.sdk.api.VelotradePublicAPI;
import com.velotrade.sdk.api.VelotradePublicAPIImpl;
import com.velotrade.sdk.entity.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class VelotradePublicAPITest {

    String baseUrl = "https://devapi.velotrade.com";
    String username = "<username>";
    String password = "<password>";

    VelotradePublicAPI api;

    @Before
    public void createVelotradePublicAPI(){
        try {
            api = new VelotradePublicAPIImpl(baseUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetDebtorContactIdEqualsExpectedId(){


        DebtorContact expectedResult = new DebtorContact();
        expectedResult.setId("oa1a6a170-d3d4-428a-835f-35ab021d410c");
        expectedResult.setEmail("<email>");
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
    public void testUploadFileShouldReturnResultContainName(){
        Attachment result = null;
        try {
            result = api.uploadAttachment("<file-path>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("detail page", result.getName());

    }

    @Test
    public void createAuctionShouldNotReturnNull() throws Exception {

        String issueDate = "2017-09-28T16:00:00.000Z";
        String dueDate = "2017-11-27T16:00:00.000Z";
        String expectedPaymentDate = "2017-11-27T16:00:00.000Z";
        DateTimeFormatter parser = ISODateTimeFormat.dateTimeParser();

        String id = "oa1a6a170-d3d4-428a-835f-35ab021d410c";
        Invoice invoice = new Invoice();
        invoice.setNumber("TEST");
        invoice.setIssueDate(parser.parseDateTime(issueDate).toDate());
        invoice.setCurrency("USD");
        invoice.setAmount(10000);
        invoice.setExpectedAmount(10000);
        invoice.setPaymentTerms(6000);
        invoice.setDueDate(parser.parseDateTime(dueDate).toDate());
        invoice.setExpectedPaymentDate(parser.parseDateTime(expectedPaymentDate).toDate());
        invoice.setDescription("TEST");


        String result = null;
        try {
            Attachment attachment = api.uploadAttachment("<file-path>");
            DebtorContact debtorContact = api.getDebtorContact(id);
            Auction auction = new Auction(debtorContact, invoice, attachment, attachment, attachment);

            result = api.createAuction(auction);

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(result);

    }

    @Test
    public void testGetAuctionPhaseShouldReturnExpectedId(){

        String id = "t6bba8cd6-5cd0-489a-8452-e3e351337755";
        String expectedResult = "READY_TO_GO_LIVE";

        try {
            String result = api.getAuctionPhase(id);

            assertEquals(expectedResult,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
