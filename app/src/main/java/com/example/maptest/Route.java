package com.example.maptest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Route {
    @SerializedName("traoptimal")
    private ArrayList<Option> Option;

    public Route(ArrayList<Option> Option) {
        this.Option = Option;
    }

    public ArrayList<Option> getOption() {
        return Option;
    }

    public void setOption(ArrayList<Option> Option) {
        this.Option = Option;
    }
}
