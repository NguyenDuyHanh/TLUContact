package com.example.tlucontact.model;

import java.io.Serializable;

public class CBGV implements Serializable {
    private String idCB, tenCB, sdtCB, dcCB, emailCB, donViCB;

    // Constructor mặc định (bắt buộc cho Firebase)
    public CBGV() {
    }

    public CBGV(String idCB, String tenCB, String sdtCB, String dcCB, String emailCB, String donViCB) {
        this.idCB = idCB;
        this.tenCB = tenCB;
        this.sdtCB = sdtCB;
        this.dcCB = dcCB;
        this.emailCB = emailCB;
        this.donViCB = donViCB;
    }

    public String getIdCB() {
        return idCB;
    }

    public void setIdCB(String idCB) {
        this.idCB = idCB;
    }

    public String getTenCB() {
        return tenCB;
    }

    public void setTenCB(String tenCB) {
        this.tenCB = tenCB;
    }

    public String getDcCB() {
        return dcCB;
    }

    public void setDcCB(String dcCB) {
        this.dcCB = dcCB;
    }

    public String getSdtCB() {
        return sdtCB;
    }

    public void setSdtCB(String sdtCB) {
        this.sdtCB = sdtCB;
    }

    public String getEmailCB() {
        return emailCB;
    }

    public void setEmailCB(String emailCB) {
        this.emailCB = emailCB;
    }

    public String getDonViCB() {
        return donViCB;
    }

    public void setDonViCB(String donViCB) {
        this.donViCB = donViCB;
    }
}
