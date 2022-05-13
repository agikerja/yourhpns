package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListBuketAdminModel {
    @SerializedName("listBuketAdmin")
    private List<ListBuketAdmin> listBuketAdmin;
    @SerializedName("status")
    private String status;

    public ListBuketAdminModel(List<ListBuketAdmin> listBuketAdmin, String status) {
        this.listBuketAdmin = listBuketAdmin;
        this.status = status;
    }

    public List<ListBuketAdmin> getListBuketAdmin() {
        return listBuketAdmin;
    }

    public void setListBuketAdmin(List<ListBuketAdmin> listBuketAdmin) {
        this.listBuketAdmin = listBuketAdmin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
