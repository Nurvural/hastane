package entity;

public class Kayit {

    private Long idkayit;
    private String kayit_name;
    private String kayit_surname;
    private int kayitno;
    private String randevu;

    private Resepsiyon resepsiyon;

    public Kayit() {
    }

    public Kayit(Long idkayit, String kayit_name, String kayit_surname, int kayitno, String randevu) {
        this.idkayit = idkayit;
        this.kayit_name = kayit_name;
        this.kayit_surname = kayit_surname;
        this.kayitno = kayitno;
        this.randevu = randevu;
    }

    public Long getIdkayit() {
        return idkayit;
    }

    public void setIdkayit(Long idkayit) {
        this.idkayit = idkayit;
    }

    public String getKayit_name() {
        return kayit_name;
    }

    public void setKayit_name(String kayit_name) {
        this.kayit_name = kayit_name;
    }

    public String getKayit_surname() {
        return kayit_surname;
    }

    public void setKayit_surname(String kayit_surname) {
        this.kayit_surname = kayit_surname;
    }

    public int getKayitno() {
        return kayitno;
    }

    public void setKayitno(int kayitno) {
        this.kayitno = kayitno;
    }

    public String getRandevu() {
        return randevu;
    }

    public void setRandevu(String randevu) {
        this.randevu = randevu;
    }

    @Override
    public String toString() {
        return kayit_name;
    }

    public Resepsiyon getResepsiyon() {
        return resepsiyon;
    }

    public void setResepsiyon(Resepsiyon resepsiyon) {
        this.resepsiyon = resepsiyon;
    }

}
