package com.example.maptest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import javax.xml.transform.Result;

public class ResultPath{

    @SerializedName("route")
    @Expose
    private Route route;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}