package com.example.tlucontact.model;

import java.io.Serializable;

public class Donvi implements Serializable {
    private String idDV;
    private String tenDV;
    private String sdtDV;
    private String diaChi;

    // Constructor mặc định (cần thiết để Firebase đọc dữ liệu)
    public Donvi() {
    }

    public Donvi(String idDV, String tenDV, String sdtDV, String diaChi) {
        this.idDV = idDV;
        this.tenDV = tenDV;
        this.sdtDV = sdtDV;
        this.diaChi = diaChi;
    }

    public String getIdDV() {
        return idDV;
    }

    public void setIdDV(String idDV) {
        this.idDV = idDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getSdtDV() {
        return sdtDV;
    }

    public void setSdtDV(String sdtDV) {
        this.sdtDV = sdtDV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
