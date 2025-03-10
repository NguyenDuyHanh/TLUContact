package com.example.tlucontact;

import java.io.Serializable;

public class CBGV implements Serializable {
    private String tenCB, sdtCB, dcCB, emailCB, donViCB;

    public CBGV(String tenCB, String sdtCB, String dcCB, String emailCB, String donViCB) {
        this.tenCB = tenCB;
        this.sdtCB = sdtCB;
        this.dcCB = dcCB;
        this.emailCB = emailCB;
        this.donViCB = donViCB;
    }

    public String getTenCB() {
        return tenCB;
    }

    public void setTenCB(String tenCB) {
        this.tenCB = tenCB;
    }

    public String getSdtCB() {
        return sdtCB;
    }

    public void setSdtCB(String sdtCB) {
        this.sdtCB = sdtCB;
    }

    public String getDcCB() {
        return dcCB;
    }

    public void setDcCB(String dcCB) {
        this.dcCB = dcCB;
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
