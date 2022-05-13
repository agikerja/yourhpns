package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class DetailBahanBakuModel {
    @SerializedName("detailbahanbaku")
    private Boolean detailbahanbaku;
    @SerializedName("status")
    private String status;

    public DetailBahanBakuModel(Boolean detailbahanbaku, String status) {
        this.detailbahanbaku = detailbahanbaku;
        this.status = status;
    }

    public Boolean getDetailbahanbaku() {
        return detailbahanbaku;
    }

    public void setDetailbahanbaku(Boolean detailbahanbaku) {
        this.detailbahanbaku = detailbahanbaku;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
