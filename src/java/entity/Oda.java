
package entity;


public class Oda {
    
    private Long idoda;
    private int odano;
    private String oda_tipi;
    
    private Hemsire hemsire;

    public Oda() {
    }

    public Oda(Long idoda, int odano, String oda_tipi) {
        this.idoda = idoda;
        this.odano = odano;
        this.oda_tipi = oda_tipi;
       
    }

    public Long getIdoda() {
        return idoda;
    }

    public void setIdoda(Long idoda) {
        this.idoda = idoda;
    }

    public int getOdano() {
        return odano;
    }

    public void setOdano(int odano) {
        this.odano = odano;
    }

    public String getOda_tipi() {
        return oda_tipi;
    }

    public void setOda_tipi(String oda_tipi) {
        this.oda_tipi = oda_tipi;
    }

    public Hemsire getHemsire() {
        return hemsire;
    }

    public void setHemsire(Hemsire hemsire) {
        this.hemsire = hemsire;
    }
    
    
    
}
