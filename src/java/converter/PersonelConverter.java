
package converter;

import dao.PersonelDao;
import entity.Personel;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sabiha
 */
@FacesConverter(value="personelConverter")
public class PersonelConverter implements Converter{
private PersonelDao personelDao;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getPersonelDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Personel p=(Personel) arg2;
        return p.getIdpersonel().toString();
        
    }

    public PersonelDao getPersonelDao() {
        if(this.personelDao==null)
            this.personelDao=new PersonelDao();
        return personelDao;
    }

    public void setPersonelDao(PersonelDao personelDao) {
        this.personelDao = personelDao;
    }
    
    
    
}
