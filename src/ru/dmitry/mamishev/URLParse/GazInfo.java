/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmitry.mamishev.URLParse;

import java.util.Objects;

/**
 *
 * @author dmitriy.mamishev
 */
public class GazInfo {

    public GazInfo(String fio, String adress, String date, String lastSum, String numBill) {
        this.fio = fio;
        this.adress = adress;
        this.date = date;
        this.lastSum = lastSum;
        this.numBill = numBill;
    }
    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "GazInfo{" + "fio=" + fio + ", adress=" + adress + ", date=" + date + ", lastSum=" + lastSum + ", numBill=" + numBill + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.fio);
        hash = 61 * hash + Objects.hashCode(this.adress);
        hash = 61 * hash + Objects.hashCode(this.numBill);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GazInfo other = (GazInfo) obj;
        if (!Objects.equals(this.fio, other.fio)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.numBill, other.numBill)) {
            return false;
        }
        return true;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastSum() {
        return lastSum;
    }

    public void setLastSum(String lastSum) {
        this.lastSum = lastSum;
    }

    public String getNumBill() {
        return numBill;
    }

    public void setNumBill(String numBill) {
        this.numBill = numBill;
    }

    private String fio;
    private String adress;
    private String date;
    private String lastSum;
    private String numBill;
}
