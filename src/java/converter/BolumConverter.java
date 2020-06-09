
package converter;

import dao.BolumDao;
import entity.Bolum;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sabiha
 */
@FacesConverter(value="bolumConverter")
public class BolumConverter implements Converter{
    private BolumDao bolumDao;
//bolum sınıfı one sınfııdır bu sınıfa ait converter oluşturulması için sorguların gerçekleştiği dao sınıfının nesnesi kullanılır
    @Override//string gelen valuenin objecte cevrilmesi
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBolumDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Bolum b=(Bolum) arg2;
        return b.getIdbolum().toString();
    }

    public BolumDao getBolumDao() {
        if(this.bolumDao==null)
            this.bolumDao=new BolumDao();
        return bolumDao;
    }

    public void setBolumDao(BolumDao bolumDao) {
        this.bolumDao = bolumDao;
    }
    
    
    
}
