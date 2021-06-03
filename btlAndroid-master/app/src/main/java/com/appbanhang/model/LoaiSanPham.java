package com.appbanhang.model;

import java.io.Serializable;

public class LoaiSanPham implements Serializable {
    public int id;
    public String tenloaisanpham;
    public  String hinhanhloaisanpham;

    public LoaiSanPham(int id, String tenloaisanpham, String hinhanhloaisanpham) {
        this.id = id;
        this.tenloaisanpham = tenloaisanpham;
        this.hinhanhloaisanpham = hinhanhloaisanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }

    public String getHinhanhloaisanpham() {
        return hinhanhloaisanpham;
    }

    public void setHinhanhloaisanpham(String hinhanhloaisanpham) {
        this.hinhanhloaisanpham = hinhanhloaisanpham;
    }
}
