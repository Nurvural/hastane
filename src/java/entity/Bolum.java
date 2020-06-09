
package entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author sabiha
 */
public class Bolum {
    private Long idbolum;
    private String bolum_name;
    private int number;
    
    private List<Personel> bolumPersonel;//herr bolumunde birden çok kişi çalışabilir

    public Bolum() {
    }
    
    public Bolum(Long idbolum, String bolum_name, int number) {
        this.idbolum = idbolum;
        this.bolum_name = bolum_name;
        this.number = number;
    }
    
    

    public Long getIdbolum() {
        return idbolum;
    }

    public void setIdbolum(Long idbolum) {
        this.idbolum = idbolum;
    }

    public String getBolum_name() {
        return bolum_name;
    }

    public void setBolum_name(String bolum_name) {
        this.bolum_name = bolum_name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return  bolum_name ;
    }

    

    public List<Personel> getBolumPersonel() {
        return bolumPersonel;
    }

    public void setBolumPersonel(List<Personel> bolumPersonel) {
        this.bolumPersonel = bolumPersonel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.idbolum);
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
        final Bolum other = (Bolum) obj;
        if (!Objects.equals(this.idbolum, other.idbolum)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
