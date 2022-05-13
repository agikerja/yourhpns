package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDetailBahanbakuModel {
    @SerializedName("listDetailBahanbaku")
    private List<ListDetailBahanBaku> listDetailBahanbakus;
    @SerializedName("status")
    private String status;

    public ListDetailBahanbakuModel(List<ListDetailBahanBaku> listDetailBahanbakus, String status) {
        this.listDetailBahanbakus = listDetailBahanbakus;
        this.status = status;
    }

    public List<ListDetailBahanBaku> getListDetailBahanbakus() {
        return listDetailBahanbakus;
    }

    public void setListDetailBahanbakus(List<ListDetailBahanBaku> listDetailBahanbakus) {
        this.listDetailBahanbakus = listDetailBahanbakus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
