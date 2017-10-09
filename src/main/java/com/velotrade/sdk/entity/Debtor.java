package com.velotrade.sdk.entity;

import com.google.gson.annotations.SerializedName;

public class Debtor {

    private String id;
    private String address;
    private String br;
    private String city;
    private String country;
    private String humanId;
    private String legalName;
    private String tradingName;
    private String website;
    private String zipCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHumanId() {
        return humanId;
    }

    public void setHumanId(String humanId) {
        this.humanId = humanId;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getZipcode() {
        return zipCode;
    }

    public void setZipcode(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", br='" + br + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", humanId='" + humanId + '\'' +
                ", legalName='" + legalName + '\'' +
                ", tradingName='" + tradingName + '\'' +
                ", website='" + website + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
