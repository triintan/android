package com.example.kuis_uts.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")

public class DataModel {
    @SerializedName("kode")
    private String mKode;
    @SerializedName("nama")
    private String mNama;
    @SerializedName("kategori")
    private String mKategori;

    public String getkode(){
        return mKode;
    }

    public void setKode(String kode) {
        mKode=kode;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama=nama;
    }

    public String getKategori() {
        return mKategori;
    }

    public void setKategori(String kategori) {
        mKode=kategori;
    }
}
