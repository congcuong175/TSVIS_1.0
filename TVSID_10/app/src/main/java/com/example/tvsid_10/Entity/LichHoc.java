package com.example.tvsid_10.Entity;

public class LichHoc {
    String Ngay;
    String Thu;
    String Tiet;
    String ThoiGian;
    String TenHocPhan;
    String Phong;
    String GiaoVien;
    String NamHoc;

    public LichHoc() {
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getThu() {
        return Thu;
    }

    public void setThu(String thu) {
        Thu = thu;
    }

    public String getTiet() {
        return Tiet;
    }

    public void setTiet(String tiet) {
        Tiet = tiet;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String thoiGian) {
        ThoiGian = thoiGian;
    }

    public String getTenHocPhan() {
        return TenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        TenHocPhan = tenHocPhan;
    }

    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public String getGiaoVien() {
        return GiaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        GiaoVien = giaoVien;
    }

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String namHoc) {
        NamHoc = namHoc;
    }

    public LichHoc(String ngay, String thu, String tiet, String thoiGian, String tenHocPhan, String phong, String giaoVien, String namHoc) {
        Ngay = ngay;
        Thu = thu;
        Tiet = tiet;
        ThoiGian = thoiGian;
        TenHocPhan = tenHocPhan;
        Phong = phong;
        GiaoVien = giaoVien;
        NamHoc = namHoc;
    }
}
