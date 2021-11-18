package com.example.lab12;

import com.google.gson.annotations.SerializedName;

public class IPInfo {

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

//    public String[] getRates() {
//        return rates;
//    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCode(String code) {
        this.code = code;
    }

//    public void setRates(String[] rates) {
//        this.rates = rates;
//    }

    @SerializedName("table")
    private String table;
    @SerializedName("currency")
    private String currency;
    @SerializedName("code")
    private String code;
//    @SerializedName("rates")
//    private String rates [];


}
