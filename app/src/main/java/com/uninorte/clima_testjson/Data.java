package com.uninorte.clima_testjson;

import java.io.Serializable;

/**
 * Created by Ricardo on 24/04/2015.
 */
public class Data implements Serializable{

    private String dia;
    private String min;
    private String max;
    private String noche;
    private String tarde;
    private String mañana;

    public Data(){

    }

    public Data(String dia, String min, String max, String noche, String tarde, String mañana) {
        this.dia = dia;
        this.min = min;
        this.max = max;
        this.noche = noche;
        this.tarde = tarde;
        this.mañana = mañana;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getNoche() {
        return noche;
    }

    public void setNoche(String noche) {
        this.noche = noche;
    }

    public String getTarde() {
        return tarde;
    }

    public void setTarde(String tarde) {
        this.tarde = tarde;
    }

    public String getMañana() {
        return mañana;
    }

    public void setMañana(String mañana) {
        this.mañana = mañana;
    }
}
