package com.example.maptest;

import com.google.gson.annotations.SerializedName;

public class Get_CouryToAddress {

    public Get_CouryToAddress(String CouryToAddress, String Package_Name, String COURY_ASSIGNMENT, int userSeq) {
       this.CouryToAddress = CouryToAddress;
       this.Package_Name = Package_Name;
       this.COURY_ASSIGNMENT = COURY_ASSIGNMENT;
       this.userSeq = userSeq;
    }
    @SerializedName("COURY_TO_ADDRESS")
    private String CouryToAddress;

    public String getCouryToAddress() {
        return CouryToAddress;
    }

    public void setCouryToAddress(String couryToAddress) {
        CouryToAddress = couryToAddress;
    }
    @SerializedName("Package_Name")
    private String Package_Name;

    public String getPackage_Name() {
        return Package_Name;
    }

    public void setPackage_Name(String package_Name) {
        Package_Name = package_Name;
    }
    @SerializedName("COURY_ASSIGNMENT")
    private String COURY_ASSIGNMENT;

    public String getCOURY_ASSIGNMENT() {
        return COURY_ASSIGNMENT;
    }

    public void setCOURY_ASSIGNMENT(String COURY_ASSIGNMENT) {
        this.COURY_ASSIGNMENT = COURY_ASSIGNMENT;
    }

    @SerializedName("userSeq")
    private int userSeq;

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

}
