
package entity;

/**
 *
 * @author sabiha
 */
public class Doktor {
    private Long iddoktor;
    private String doktor_name;
    private String doktor_surname;
    private int salary;
    private String turu;
    private Long idbolum;
    
    private Bolum bolum;//bir bolumde birden çok doktor

    public Doktor() {
    }

    public Doktor(Long iddoktor, String doktor_name, String doktor_surname, int salary, String turu) {
        this.iddoktor = iddoktor;
        this.doktor_name = doktor_name;
        this.doktor_surname = doktor_surname;
        this.salary = salary;
        this.turu = turu;
    }

    public Long getIddoktor() {
        return iddoktor;
    }

    public void setIddoktor(Long iddoktor) {
        this.iddoktor = iddoktor;
    }

    public String getDoktor_name() {
        return doktor_name;
    }

    public void setDoktor_name(String doktor_name) {
        this.doktor_name = doktor_name;
    }

    public String getDoktor_surname() {
        return doktor_surname;
    }

    public void setDoktor_surname(String doktor_surname) {
        this.doktor_surname = doktor_surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
    }

  
    @Override
    public String toString() {
        return  doktor_name;
    }

    public Bolum getBolum() {
        return bolum;
    }

    public void setBolum(Bolum bolum) {
        this.bolum = bolum;
    }
    
    
    
}
