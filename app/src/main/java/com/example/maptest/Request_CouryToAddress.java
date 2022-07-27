package com.example.maptest;

import com.google.gson.annotations.SerializedName;

public class Request_CouryToAddress {
    @SerializedName("userSeq")
    private int userSeq;

    @SerializedName("packageName")
    private String packageName;

    public Request_CouryToAddress(){}

    public Request_CouryToAddress(int userSeq, String packageName) {
        this.userSeq = userSeq;
        this.packageName = packageName;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
