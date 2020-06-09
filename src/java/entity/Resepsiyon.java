package entity;

import java.util.Objects;

public class Resepsiyon {

    private Long idresepsiyon;
    private String resepsiyon_name;
    private int resepsiyon_number;

    public Resepsiyon() {
    }

    public Resepsiyon(Long idresepsiyon, String resepsiyon_name, int resepsiyon_number) {
        this.idresepsiyon = idresepsiyon;
        this.resepsiyon_name = resepsiyon_name;
        this.resepsiyon_number = resepsiyon_number;
    }

    public Long getIdresepsiyon() {
        return idresepsiyon;
    }

    public void setIdresepsiyon(Long idresepsiyon) {
        this.idresepsiyon = idresepsiyon;
    }

    public String getResepsiyon_name() {
        return resepsiyon_name;
    }

    public void setResepsiyon_name(String resepsiyon_name) {
        this.resepsiyon_name = resepsiyon_name;
    }

    public int getResepsiyon_number() {
        return resepsiyon_number;
    }

    public void setResepsiyon_number(int resepsiyon_number) {
        this.resepsiyon_number = resepsiyon_number;
    }

    @Override
    public String toString() {
        return resepsiyon_name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idresepsiyon);
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
        final Resepsiyon other = (Resepsiyon) obj;
        if (!Objects.equals(this.idresepsiyon, other.idresepsiyon)) {
            return false;
        }
        return true;
    }

}
