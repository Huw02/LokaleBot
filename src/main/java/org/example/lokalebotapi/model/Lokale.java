package org.example.lokalebotapi.model;

public class Lokale {

    String tid;
    String fag;
    String underviser;
    String rum;


    public Lokale(String tid, String fag, String underviser, String rum) {
        this.tid = tid;
        this.fag = fag;
        this.underviser = underviser;
        this.rum = rum;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getFag() {
        return fag;
    }

    public void setFag(String fag) {
        this.fag = fag;
    }

    public String getUnderviser() {
        return underviser;
    }

    public void setUnderviser(String underviser) {
        this.underviser = underviser;
    }

    public String getRum() {
        return rum;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }
}
