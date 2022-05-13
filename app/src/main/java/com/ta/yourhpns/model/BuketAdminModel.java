package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuketAdminModel {

    @SerializedName("buketAdmin")
    private Boolean buketAdmin;
    @SerializedName("status")
    private String status;

    public BuketAdminModel(Boolean buketAdmin, String status) {
        this.buketAdmin = buketAdmin;
        this.status = status;
    }

    public Boolean getBuketAdmin() { return buketAdmin; }

    public void setBuketAdmin(Boolean buketAdmin) { this.buketAdmin = buketAdmin; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
