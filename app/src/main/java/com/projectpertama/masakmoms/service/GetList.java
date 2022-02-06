package com.projectpertama.masakmoms.service;

import com.google.gson.annotations.SerializedName;
import com.projectpertama.masakmoms.home.DataMenu;

import java.util.List;

public class GetList {
    @SerializedName("method")
    private String method;
    @SerializedName("status")
    private String status;
    @SerializedName("results")
    private List<DataMenu> result;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataMenu> getResult() {
        return result;
    }

    public void setResult(List<DataMenu> result) {
        this.result = result;
    }
}
