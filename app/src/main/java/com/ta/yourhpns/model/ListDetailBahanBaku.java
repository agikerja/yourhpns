package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class ListDetailBahanBaku {
    @SerializedName("id_detailbahanbaku")
    private String idDetailBahanBaku;
    @SerializedName("nama_detailbahanbaku")
    private String namaDetailBahanBaku;
    @SerializedName("jumlah_detailbahanbaku")
    private String jumlahDetailBahanBaku;
    @SerializedName("harga_detailbahanbaku")
    private String hargaDetailBahanBaku;
    @SerializedName("kode_buket")
    private String kodeBuket;

    public String getIdDetailBahanBaku() {
        return idDetailBahanBaku;
    }

    public void setIdDetailBahanBaku(String idDetailBahanBaku) {
        this.idDetailBahanBaku = idDetailBahanBaku;
    }

    public String getNamaDetailBahanBaku() {
        return namaDetailBahanBaku;
    }

    public void setNamaDetailBahanBaku(String namaDetailBahanBaku) {
        this.namaDetailBahanBaku = namaDetailBahanBaku;
    }

    public String getJumlahDetailBahanBaku() {
        return jumlahDetailBahanBaku;
    }

    public void setJumlahDetailBahanBaku(String jumlahDetailBahanBaku) {
        this.jumlahDetailBahanBaku = jumlahDetailBahanBaku;
    }

    public String getHargaDetailBahanBaku() {
        return hargaDetailBahanBaku;
    }

    public void setHargaDetailBahanBaku(String hargaDetailBahanBaku) {
        this.hargaDetailBahanBaku = hargaDetailBahanBaku;
    }

    public String getKodeBuket() {
        return kodeBuket;
    }

    public void setKodeBuket(String kodeBuket) {
        this.kodeBuket = kodeBuket;
    }

    public ListDetailBahanBaku(String idDetailBahanBaku, String namaDetailBahanBaku, String jumlahDetailBahanBaku, String hargaDetailBahanBaku, String kodeBuket) {
        this.idDetailBahanBaku = idDetailBahanBaku;
        this.namaDetailBahanBaku = namaDetailBahanBaku;
        this.jumlahDetailBahanBaku = jumlahDetailBahanBaku;
        this.hargaDetailBahanBaku = hargaDetailBahanBaku;
        this.kodeBuket = kodeBuket;


    }
}
