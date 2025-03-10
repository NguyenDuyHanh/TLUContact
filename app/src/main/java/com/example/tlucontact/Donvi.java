package com.example.tlucontact;

public class Donvi {
    private String tenDV;
    private String sdtDV;
    private String diaChi;

    public Donvi(String tenDV, String sdtDV, String diaChi) {
        this.tenDV = tenDV;
        this.sdtDV = sdtDV;
        this.diaChi = diaChi;
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
