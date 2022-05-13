package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class ListBahanBaku {
    @SerializedName("id_bahanbaku")
    private String idBahanBaku;
    @SerializedName("nama_bahanbaku")
    private String namaBahanBaku;
    @SerializedName("jenis_bahanbaku")
    private String jenisBahanBaku;
    @SerializedName("harga_bahanbaku")
    private String hargaBahanBaku;

    public ListBahanBaku(String idBahanBaku, String namaBahanBaku, String jenisBahanBaku, String hargaBahanBaku) {
        this.idBahanBaku = idBahanBaku;
        this.namaBahanBaku = namaBahanBaku;
        this.jenisBahanBaku = jenisBahanBaku;
        this.hargaBahanBaku = hargaBahanBaku;
    }

    public String getIdBahanBaku() {
        return idBahanBaku;
    }

    public void setIdBahanBaku(String idBahanBaku) {
        this.idBahanBaku = idBahanBaku;
    }

    public String getNamaBahanBaku() {
        return namaBahanBaku;
    }

    public void setNamaBahanBaku(String namaBahanBaku) {
        this.namaBahanBaku = namaBahanBaku;
    }

    public String getJenisBahanBaku() {
        return jenisBahanBaku;
    }

    public void setJenisBahanBaku(String jenisBahanBaku) {
        this.jenisBahanBaku = jenisBahanBaku;
    }

    public String getHargaBahanBaku() {
        return hargaBahanBaku;
    }

    public void setHargaBahanBaku(String hargaBahanBaku) {
        this.hargaBahanBaku = hargaBahanBaku;
    }
}
