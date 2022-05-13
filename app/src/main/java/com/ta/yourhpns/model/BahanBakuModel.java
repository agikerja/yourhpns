package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class BahanBakuModel {

    @SerializedName("bahanbaku")
    private Boolean bahanbaku;
    @SerializedName("status")
    private String status;

    public BahanBakuModel(Boolean bahanbaku, String status) {
        this.bahanbaku = bahanbaku;
        this.status = status;
    }

    public Boolean getBahanbaku() {
        return bahanbaku;
    }

    public void setBahanbaku(Boolean bahanbaku) {
        this.bahanbaku = bahanbaku;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
