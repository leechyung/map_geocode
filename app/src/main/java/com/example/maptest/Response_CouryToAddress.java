package com.example.maptest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response_CouryToAddress {
    @SerializedName("recvAddress")
    @Expose
    private String recvAddress;

    @SerializedName("REPL_CD")
    @Expose
    private String REPL_CD;

    @SerializedName("REPL_MSG")
    @Expose
    private String REPL_MSG;

    public String getRecvAddress() {
        return recvAddress;
    }

    public void setRecvAddress(String recvAddress) {
        this.recvAddress = recvAddress;
    }

    public String getREPL_CD() {
        return REPL_CD;
    }

    public void setREPL_CD(String REPL_CD) {
        this.REPL_CD = REPL_CD;
    }

    public String getREPL_MSG() {
        return REPL_MSG;
    }

    public void setREPL_MSG(String REPL_MSG) {
        this.REPL_MSG = REPL_MSG;
    }
}
