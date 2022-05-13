package com.ta.yourhpns.model;

import com.google.gson.annotations.SerializedName;

public class ListBarang {
    @SerializedName("id_barang")
    private String idBarang;
    @SerializedName("nama_barang")
    private String namaBarang;
    @SerializedName("jenis_barang")
    private String jenisBarang;
    @SerializedName("harga_barang")
    private String hargaBarang;
    @SerializedName("stok_barang")
    private String stokBarang;

    public ListBarang(String idBarang, String namaBarang, String jenisBarang, String hargaBarang, String stokBarang) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.jenisBarang = jenisBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(String hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public String getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(String stokBarang) {
        this.stokBarang = stokBarang;
    }
}
