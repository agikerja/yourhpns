package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListBarangModel {
    @SerializedName("listBarang")
    private List<ListBarang> listBarang = null;
    @SerializedName("status")
    private String status;

    public ListBarangModel(List<ListBarang> listBarang, String status) {
        this.listBarang = listBarang;
        this.status = status;
    }

    public List<ListBarang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<ListBarang> listBarang) {
        this.listBarang = listBarang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
