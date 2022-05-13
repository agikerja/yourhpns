package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class BuketByName {
    @SerializedName("harga_bahanbaku")
    private String hargaBahanbaku;

    public BuketByName(String hargaBahanbaku) {
        this.hargaBahanbaku = hargaBahanbaku;
    }

    public String getHargaBahanbaku() {
        return hargaBahanbaku;
    }

    public void setHargaBahanbaku(String hargaBahanbaku) {
        this.hargaBahanbaku = hargaBahanbaku;
    }
}
