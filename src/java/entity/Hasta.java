/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author sabiha
 */
public class Hasta {
     private Long idhasta;
    private String hasta_name;
    private String hasta_surname;
    private String address;
    private String tcno;

    public Hasta() {
    }

    public Hasta(Long idhasta, String hasta_name, String hasta_surname, String address, String tcno) {
        this.idhasta = idhasta;
        this.hasta_name = hasta_name;
        this.hasta_surname = hasta_surname;
        this.address = address;
        this.tcno = tcno;
    }
    
    
    
    

    public Long getIdhasta() {
        return idhasta;
    }

    public void setIdhasta(Long idhasta) {
        this.idhasta = idhasta;
    }

    public String getHasta_name() {
        return hasta_name;
    }

    public void setHasta_name(String hasta_name) {
        this.hasta_name = hasta_name;
    }

    public String getHasta_surname() {
        return hasta_surname;
    }

    public void setHasta_surname(String hasta_surname) {
        this.hasta_surname = hasta_surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
    }

    @Override
    public String toString() {
        return "Hasta{" + "idhasta=" + idhasta + ", hasta_name=" + hasta_name + ", hasta_surname=" + hasta_surname + ", address=" + address + ", tcno=" + tcno + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.idhasta);
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
        final Hasta other = (Hasta) obj;
        if (!Objects.equals(this.idhasta, other.idhasta)) {
            return false;
        }
        return true;
    }
    
    

}
