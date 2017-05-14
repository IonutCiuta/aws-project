package com.aws.privatesector.PrivateSector.entities;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Document(collection = "election2016")
public class ElectionResult {
    private String id;

    @Field("JUDET")
    private String county;

    @Field("LOCALITATE")
    private String location;

    @Field("COD_SECTIE")
    private String sectionCode;

    private int PNL;

    private int PMP;

    private int PRM;

    private int PRU;

    private int PSD;

    private int UDMR;

    private int ALDE;

    private int USR;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public int getPNL() {
        return PNL;
    }

    public void setPNL(int PNL) {
        this.PNL = PNL;
    }

    public int getPMP() {
        return PMP;
    }

    public void setPMP(int PMP) {
        this.PMP = PMP;
    }

    public int getPRM() {
        return PRM;
    }

    public void setPRM(int PRM) {
        this.PRM = PRM;
    }

    public int getPRU() {
        return PRU;
    }

    public void setPRU(int PRU) {
        this.PRU = PRU;
    }

    public int getPSD() {
        return PSD;
    }

    public void setPSD(int PSD) {
        this.PSD = PSD;
    }

    public int getUDMR() {
        return UDMR;
    }

    public void setUDMR(int UDMR) {
        this.UDMR = UDMR;
    }

    public int getALDE() {
        return ALDE;
    }

    public void setALDE(int ALDE) {
        this.ALDE = ALDE;
    }

    public int getUSR() {
        return USR;
    }

    public void setUSR(int USR) {
        this.USR = USR;
    }

    @Override
    public String toString() {
        return  "ElectionResult\n" +
                "  county: " + county + "\n" +
                "  location: " + location + "\n" +
                "  sectionCode: " + sectionCode + "\n" +
                "  PNL: "     + PNL      + "\n" +
                "  PMP: "     + PMP      + "\n" +
                "  PRM: "     + PRM      + "\n" +
                "  PRU: "     + PRU      + "\n" +
                "  PSD: "     + PSD      + "\n" +
                "  UDMR: "    + UDMR     + "\n" +
                "  ALDE: "    + ALDE     + "\n" +
                "  USR: "     + USR      + "\n" +
                "===========\n";
    }
}
