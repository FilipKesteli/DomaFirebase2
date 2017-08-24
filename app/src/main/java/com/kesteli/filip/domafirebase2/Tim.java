package com.kesteli.filip.domafirebase2;

/**
 * Created by Valemate on 24.8.2017..
 */

public class Tim {

    private String imeTima;
    private String korisnici;
    private String net;
    private String vjera;
    private String ulog;
    private String realno;
    private String tkoUlog;
    private String motivacija;
    private String podrucja;
    private String sansaUlog;
    private String brojClanova;

    public Tim() {
    }

    public Tim(String imeTima, String korisnici, String net, String vjera, String ulog, String realno, String tkoUlog, String motivacija, String podrucja, String sansaUlog, String brojClanova) {
        this.imeTima = imeTima;
        this.korisnici = korisnici;
        this.net = net;
        this.vjera = vjera;
        this.ulog = ulog;
        this.realno = realno;
        this.tkoUlog = tkoUlog;
        this.motivacija = motivacija;
        this.podrucja = podrucja;
        this.sansaUlog = sansaUlog;
        this.brojClanova = brojClanova;
    }

    public String getImeTima() {
        return imeTima;
    }

    public void setImeTima(String imeTima) {
        this.imeTima = imeTima;
    }

    public String getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(String korisnici) {
        this.korisnici = korisnici;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getVjera() {
        return vjera;
    }

    public void setVjera(String vjera) {
        this.vjera = vjera;
    }

    public String getUlog() {
        return ulog;
    }

    public void setUlog(String ulog) {
        this.ulog = ulog;
    }

    public String getRealno() {
        return realno;
    }

    public void setRealno(String realno) {
        this.realno = realno;
    }

    public String getTkoUlog() {
        return tkoUlog;
    }

    public void setTkoUlog(String tkoUlog) {
        this.tkoUlog = tkoUlog;
    }

    public String getMotivacija() {
        return motivacija;
    }

    public void setMotivacija(String motivacija) {
        this.motivacija = motivacija;
    }

    public String getPodrucja() {
        return podrucja;
    }

    public void setPodrucja(String podrucja) {
        this.podrucja = podrucja;
    }

    public String getSansaUlog() {
        return sansaUlog;
    }

    public void setSansaUlog(String sansaUlog) {
        this.sansaUlog = sansaUlog;
    }

    public String getBrojClanova() {
        return brojClanova;
    }

    public void setBrojClanova(String brojClanova) {
        this.brojClanova = brojClanova;
    }
}