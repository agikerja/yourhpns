package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListBuketAdmin {
    @SerializedName("id_buket")
    private String idBuket;
    @SerializedName("nama_buket")
    private String namaBuket;
    @SerializedName("jenis_buket")
    private String jenisBuket;
    @SerializedName("harga_buket")
    private String hargaBuket;
    @SerializedName("kode_buket")
    private String kodeBuket;

    public ListBuketAdmin(String idBuket, String namaBuket, String jenisBuket, String hargaBuket, String kodeBuket) {
        this.idBuket = idBuket;
        this.namaBuket = namaBuket;
        this.jenisBuket = jenisBuket;
        this.hargaBuket = hargaBuket;
        this.kodeBuket = kodeBuket;
    }

    public String getIdBuket() {
        return idBuket;
    }

    public void setIdBuket(String idBuket) {
        this.idBuket = idBuket;
    }

    public String getNamaBuket() {
        return namaBuket;
    }

    public void setNamaBuket(String namaBuket) {
        this.namaBuket = namaBuket;
    }

    public String getJenisBuket() {
        return jenisBuket;
    }

    public void setJenisBuket(String jenisBuket) {
        this.jenisBuket = jenisBuket;
    }

    public String getHargaBuket() {
        return hargaBuket;
    }

    public void setHargaBuket(String hargaBuket) {
        this.hargaBuket = hargaBuket;
    }

    public String getKodeBuket() {
        return kodeBuket;
    }

    public void setKodeBuket(String kodeBuket) {
        this.kodeBuket = kodeBuket;
    }
}
