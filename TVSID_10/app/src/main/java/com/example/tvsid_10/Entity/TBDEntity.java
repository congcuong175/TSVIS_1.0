package com.example.tvsid_10.Entity;

public class TBDEntity {
    String name;
    float diem;

    public TBDEntity(String name, float diem) {
        this.name = name;
        this.diem = diem;
    }

    public TBDEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
