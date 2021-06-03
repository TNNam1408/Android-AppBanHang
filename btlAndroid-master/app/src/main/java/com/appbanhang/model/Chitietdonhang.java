package com.appbanhang.model;

public class Chitietdonhang {
    public String idctdh;
    public String madonhangctdh;
    public String masanphamctdh;
    public String tensanphamctdh;
    public String giasanphamctdh;
    public String soluongsanphamctdh;

    public Chitietdonhang(String idctdh, String madonhangctdh, String masanphamctdh, String tensanphamctdh, String giasanphamctdh, String soluongsanphamctdh) {
        this.idctdh = idctdh;
        this.madonhangctdh = madonhangctdh;
        this.masanphamctdh = masanphamctdh;
        this.tensanphamctdh = tensanphamctdh;
        this.giasanphamctdh = giasanphamctdh;
        this.soluongsanphamctdh = soluongsanphamctdh;
    }

    public String getIdctdh() {
        return idctdh;
    }

    public void setIdctdh(String idctdh) {
        this.idctdh = idctdh;
    }

    public String getMadonhangctdh() {
        return madonhangctdh;
    }

    public void setMadonhangctdh(String madonhangctdh) {
        this.madonhangctdh = madonhangctdh;
    }

    public String getMasanphamctdh() {
        return masanphamctdh;
    }

    public void setMasanphamctdh(String masanphamctdh) {
        this.masanphamctdh = masanphamctdh;
    }

    public String getTensanphamctdh() {
        return tensanphamctdh;
    }

    public void setTensanphamctdh(String tensanphamctdh) {
        this.tensanphamctdh = tensanphamctdh;
    }

    public String getGiasanphamctdh() {
        return giasanphamctdh;
    }

    public void setGiasanphamctdh(String giasanphamctdh) {
        this.giasanphamctdh = giasanphamctdh;
    }

    public String getSoluongsanphamctdh() {
        return soluongsanphamctdh;
    }

    public void setSoluongsanphamctdh(String soluongsanphamctdh) {
        this.soluongsanphamctdh = soluongsanphamctdh;
    }
}
