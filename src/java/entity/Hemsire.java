
package entity;


public class Hemsire {
    
    private Long idhemsire;
    private String hemsire_name;
    private String hemsire_surname;
    private String hemsire_turu;

    public Hemsire() {
    }

    public Hemsire(Long idhemsire, String hemsire_name, String hemsire_surname, String hemsire_turu) {
        this.idhemsire = idhemsire;
        this.hemsire_name = hemsire_name;
        this.hemsire_surname = hemsire_surname;
        this.hemsire_turu = hemsire_turu;
    }

    public Long getIdhemsire() {
        return idhemsire;
    }

    public void setIdhemsire(Long idhemsire) {
        this.idhemsire = idhemsire;
    }

    public String getHemsire_name() {
        return hemsire_name;
    }

    public void setHemsire_name(String hemsire_name) {
        this.hemsire_name = hemsire_name;
    }

    public String getHemsire_surname() {
        return hemsire_surname;
    }

    public void setHemsire_surname(String hemsire_surname) {
        this.hemsire_surname = hemsire_surname;
    }

    public String getHemsire_turu() {
        return hemsire_turu;
    }

    public void setHemsire_turu(String hemsire_turu) {
        this.hemsire_turu = hemsire_turu;
    }

    
   
    
}
