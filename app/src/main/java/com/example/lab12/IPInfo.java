package com.example.lab12;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

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

    public List<Rates> getRates(){return this.rates;}

    public void setTable(String table) {
        this.table = table;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @SerializedName("table")
    private String table;
    @SerializedName("currency")
    private String currency;
    @SerializedName("code")
    private String code;
    @SerializedName("rates")
    private List<Rates> rates ;

    class Rates{
        @SerializedName("mid")
        private double mid;

        public double getMid(){
            return mid;
        }

        public void setMid(double mid) {this.mid = mid;}
    }


}
