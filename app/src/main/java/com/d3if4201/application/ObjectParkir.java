package com.d3if4201.application;

public class ObjectParkir {
    private int id;
    private String lokasi;
    private String koordinat;
    private byte[] gambar;

    public ObjectParkir(int id, String lokasi, byte[] gambar,String koordinat) {
        this.id = id;
        this.lokasi = lokasi;
        this.gambar = gambar;
        this.koordinat=koordinat;
    }

    public int getId() {
        return id;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
}
