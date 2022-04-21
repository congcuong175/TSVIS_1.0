package com.example.tvsid_10.Entity;

public class SinhVien {
    String Avatar;
    String HoTen;
    int ID;
    String KhoaHoc;
    String Lop;
    String MatKhau;
    String NganhHoc;
    String NgaySinh;
    boolean Status;

    public SinhVien(String avatar, String hoTen, int ID, String khoaHoc, String lop, String matKhau, String nganhHoc, String ngaySinh, boolean status) {
        Avatar = avatar;
        HoTen = hoTen;
        this.ID = ID;
        KhoaHoc = khoaHoc;
        Lop = lop;
        MatKhau = matKhau;
        NganhHoc = nganhHoc;
        NgaySinh = ngaySinh;
        Status = status;
    }

    public SinhVien() {
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKhoaHoc() {
        return KhoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        KhoaHoc = khoaHoc;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getNganhHoc() {
        return NganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        NganhHoc = nganhHoc;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
