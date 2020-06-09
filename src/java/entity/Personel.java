
package entity;

import java.util.Objects;

/**
 *
 * @author sabiha
 */
public class Personel {
    private Long idpersonel;
    private String personel_name;
    private String personel_surname;
    private String personel_turu;
    private int personel_salary;
    private String personel_tcno;

    public Personel() {
    }

    public Personel(Long idpersonel, String personel_name, String personel_surname, String personel_turu, int personel_salary, String personel_tcno) {
        this.idpersonel = idpersonel;
        this.personel_name = personel_name;
        this.personel_surname = personel_surname;
        this.personel_turu = personel_turu;
        this.personel_salary = personel_salary;
        this.personel_tcno = personel_tcno;
    }

    public Long getIdpersonel() {
        return idpersonel;
    }

    public void setIdpersonel(Long idpersonel) {
        this.idpersonel = idpersonel;
    }

    

    public String getPersonel_name() {
        return personel_name;
    }

    public void setPersonel_name(String personel_name) {
        this.personel_name = personel_name;
    }

    public String getPersonel_surname() {
        return personel_surname;
    }

    public void setPersonel_surname(String personel_surname) {
        this.personel_surname = personel_surname;
    }

    public String getPersonel_turu() {
        return personel_turu;
    }

    public void setPersonel_turu(String personel_turu) {
        this.personel_turu = personel_turu;
    }

    public int getPersonel_salary() {
        return personel_salary;
    }

    public void setPersonel_salary(int personel_salary) {
        this.personel_salary = personel_salary;
    }

    public String getPersonel_tcno() {
        return personel_tcno;
    }

    public void setPersonel_tcno(String personel_tcno) {
        this.personel_tcno = personel_tcno;
    }

    @Override
    public String toString() {
        return  personel_name ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idpersonel);
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
        final Personel other = (Personel) obj;
        if (!Objects.equals(this.idpersonel, other.idpersonel)) {
            return false;
        }
        return true;
    }

    
    
    

    
    
}
