/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sabiha
 */
public class Tahlil {
   private Long idtahlil;
   private String tahlil_name;
   private String tahlil_surname;
   private String tahlil_turu;
   private Hasta hasta;

    public Tahlil() {
    }

    public Tahlil(Long idtahlil, String tahlil_name, String tahlil_surname, String tahlil_turu) {
        this.idtahlil = idtahlil;
        this.tahlil_name = tahlil_name;
        this.tahlil_surname = tahlil_surname;
        this.tahlil_turu = tahlil_turu;
    }
   
   
   

    public Long getIdtahlil() {
        return idtahlil;
    }

    public void setIdtahlil(Long idtahlil) {
        this.idtahlil = idtahlil;
    }

    public String getTahlil_name() {
        return tahlil_name;
    }

    public void setTahlil_name(String tahlil_name) {
        this.tahlil_name = tahlil_name;
    }

    public String getTahlil_surname() {
        return tahlil_surname;
    }

    public void setTahlil_surname(String tahlil_surname) {
        this.tahlil_surname = tahlil_surname;
    }

    public String getTahlil_turu() {
        return tahlil_turu;
    }

    public void setTahlil_turu(String tahlil_turu) {
        this.tahlil_turu = tahlil_turu;
    }

    @Override
    public String toString() {
        return "Tahlil{" + "idtahlil=" + idtahlil + ", tahlil_name=" + tahlil_name + ", tahlil_surname=" + tahlil_surname + ", tahlil_turu=" + tahlil_turu + ", hasta=" + hasta + '}';
    }

   

    

    public Hasta getHasta() {
        return hasta;
    }

    public void setHasta(Hasta hasta) {
        this.hasta = hasta;
    }
   
   
   
  
    
    
    
}
