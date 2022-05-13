package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class BarangModel {
    @SerializedName("barang")
    private Boolean barang;
    @SerializedName("status")
    private String status;

    public BarangModel(Boolean barang, String status) {
        this.barang = barang;
        this.status = status;
    }

    public Boolean getBarang() {
        return barang;
    }

    public void setBarang(Boolean barang) {
        this.barang = barang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
