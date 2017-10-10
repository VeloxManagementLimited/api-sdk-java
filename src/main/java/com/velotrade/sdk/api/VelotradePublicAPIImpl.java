package com.velotrade.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.*;
import com.velotrade.sdk.entity.Attachment;
import com.velotrade.sdk.entity.Auction;
import com.velotrade.sdk.entity.DebtorContact;
import com.velotrade.sdk.entity.RequestMethod;
import com.velotrade.sdk.jsonobject.AuctionStatus;
import com.velotrade.sdk.jsonobject.PaginationList;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VelotradePublicAPIImpl implements VelotradePublicAPI {

    private String baseUrl;
    private String userName;
    private String password;
    private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();

    public static final String LOGIN_REQUEST = "/user/login/";

    private VelotradeConnection  api;


    public VelotradePublicAPIImpl(String baseUrl, String userName, String password) throws Exception {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
        api = new VelotradePublicConnection(this.baseUrl, this.userName, this. password, VelotradePublicAPIImpl.LOGIN_REQUEST);
    }

    /**
     *
     * @param id of debtorContact
     * @return DebtorContact Object
     * @throws Exception
     */
    public DebtorContact getDebtorContact(String id) throws Exception {

        String request = "/debtor/"+id+"?fields=name,email,phone,debtor.address,debtor.br,debtor.city,debtor.country,debtor.humanId," +
                "debtor.legalName,debtor.tradingName,debtor.website,debtor.zipCode";
        String method = RequestMethod.GET;
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");
        String result = api.query(method, request, null, contentType, false);

        DebtorContact debtorContact = gson.fromJson(result, DebtorContact.class);

        return debtorContact;
    }

    /**
     *
     * @param id of AuctionStatus
     * @return AuctionStatus Object
     * @throws Exception
     */
    public String getAuctionStatus(String id) throws Exception {

        String request = "/auction/"+id+"?fields=status";
        String method = RequestMethod.GET;
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");
        String result = api.query(method, request, null, contentType, false);

        AuctionStatus auctionStatus = gson.fromJson(result, AuctionStatus.class);

        return auctionStatus.getStatus();
    }

    /**
     *
     * @param id of Auction
     * @return true if Auction is reject, if not return false
     * @throws Exception
     */
    public boolean rejectAuction(String id) throws Exception {

        String method = "POST";
        String request = "/"+id+"/reject";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("auctionId", id));
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json;charset=UTF-8");

        String result = null;
        try {
            result = api.query(method, request, params, contentType, false);
        } catch (IOException e) {
            throw new Exception("An error has occurred while approving the auction");
        }

        return result == null;
    }

    /**
     *
     * @param id of Auction
     * @return true if auction already approve, if not return false
     * @throws Exception
     */
    public boolean approveAuction(String id) throws Exception {

        String method = "POST";
        String request = "/"+id+"/approve";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("auctionId", id));

        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json;charset=UTF-8");

        String result = null;
        try {
            result = api.query(method, request, params, contentType, false);
        } catch (IOException e) {
            throw new Exception("An error has occurred while approving the auction");
        }

        return result == null;
    }

    /**
     *
     * @return list of DebtorContact Object
     * @throws Exception
     */
    public List<DebtorContact> getDebtorContacts() throws Exception {
        String request = "/debtor/list?fields=id,name,email.phone";
        String method = "GET";
        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");
        String result = api.query(method, request, null, contentType, false);

        PaginationList debtorContacts = gson.fromJson(result, PaginationList.class);

        return debtorContacts.getDebtorContacts();
    }

    /**
     *
     * @param filePath of file
     * @return Attachment Object
     * @throws Exception
     */
    public Attachment uploadAttachment(String filePath) throws Exception {

        String request = "/attachment/";
        String result = api.uploadFile(filePath, request);

        Attachment attachment = gson.fromJson(result, Attachment.class);

        attachment.setName(FilenameUtils.getName(filePath));
        return attachment;
    }


    /**
     *
     * @param auction Object
     * @return id of auction
     * @throws Exception
     */
    public String createAuction(Auction auction) throws Exception {
        String request = "/auction/";
        String method = "POST";
        List<NameValuePair> params = new ArrayList<>();

        String json = gson.toJson(auction);
        params.add(new BasicNameValuePair("json", json));

        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json;charset=UTF-8");
        String result = api.query(method,request,params,contentType, true);

        AuctionStatus auctionStatus = gson.fromJson(result, AuctionStatus.class);
        return auctionStatus.getId();
    }

    /**
     *
     * @param idAuciton
     * @return
     * @throws Exception
     */
    @Override
    public String getAuctionPhase(String idAuciton) throws Exception {
        String request = "/auction/"+idAuciton+"?fields=phase";
        String method = RequestMethod.GET;

        Map<String, String> contentType = new HashMap<>();
        contentType.put("Content-type", "application/json");

        String result = api.query(method, request, null, contentType, false);

        AuctionStatus auctionStatus = gson.fromJson(result, AuctionStatus.class);
        return auctionStatus.getPhase();
    }


    public void setApi(VelotradeConnection api) {
        this.api = api;
    }

    private class GsonUTCDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

        private final DateFormat dateFormat;

        public GsonUTCDateAdapter() {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        }

        @Override
        public synchronized JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(dateFormat.format(date));
        }

        @Override
        public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
            try {
                return dateFormat.parse(jsonElement.getAsString());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
