package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class BuketModel {
    @SerializedName("0")
    private BuketByName buketByName;

    public BuketModel(BuketByName buketByName) {
        this.buketByName = buketByName;
    }

    public BuketByName getBuketByName() {
        return buketByName;
    }

    public void setBuketByName(BuketByName buketByName) {
        this.buketByName = buketByName;
    }
}
