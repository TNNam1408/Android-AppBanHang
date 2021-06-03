package com.appbanhang.model;

public class DonHang {
    public String iddh;
    public String tenkhach;
    public  String sdt;
    public String email;

    public DonHang(String iddh, String tenkhach, String sdt, String email) {
        this.iddh = iddh;
        this.tenkhach = tenkhach;
        this.sdt = sdt;
        this.email = email;
    }

    public String getIddh() {
        return iddh;
    }

    public void setIddh(String iddh) {
        this.iddh = iddh;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
