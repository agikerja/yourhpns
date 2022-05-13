package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListBahanBakuModel {
    @SerializedName("listBahanBaku")
    private List<ListBahanBaku> listBahanBaku;
    @SerializedName("status")
    private String status;

    public ListBahanBakuModel(List<ListBahanBaku> listBahanBaku, String status) {
        this.listBahanBaku = listBahanBaku;
        this.status = status;
    }

    public List<ListBahanBaku> getListBahanBaku() {
        return listBahanBaku;
    }

    public void setListBahanBaku(List<ListBahanBaku> listBahanBaku) {
        this.listBahanBaku = listBahanBaku;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
