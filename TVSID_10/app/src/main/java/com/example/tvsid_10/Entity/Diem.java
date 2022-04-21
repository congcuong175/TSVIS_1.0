package com.example.tvsid_10.Entity;

public class Diem {
    int ID;
    String TenHocPhan;
    int SoTinChi;
    int HeSo;
    float Diem;
    int Ky;
    String NamHoc;

    public Diem() {
    }

    public Diem(int ID, String tenHocPhan, int soTinChi, int heSo, float diem, int ky, String namHoc) {
        this.ID = ID;
        TenHocPhan = tenHocPhan;
        SoTinChi = soTinChi;
        HeSo = heSo;
        Diem = diem;
        Ky = ky;
        NamHoc = namHoc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenHocPhan() {
        return TenHocPhan;
    }

    public void setTenHocPhan(String tenHocPhan) {
        TenHocPhan = tenHocPhan;
    }

    public int getSoTinChi() {
        return SoTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        SoTinChi = soTinChi;
    }

    public int getHeSo() {
        return HeSo;
    }

    public void setHeSo(int heSo) {
        HeSo = heSo;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float diem) {
        Diem = diem;
    }

    public int getKy() {
        return Ky;
    }

    public void setKy(int ky) {
        Ky = ky;
    }

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String namHoc) {
        NamHoc = namHoc;
    }
}
