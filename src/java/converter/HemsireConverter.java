
package converter;

import dao.HemsireDao;
import entity.Hemsire;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="hemsireConverter")
public class HemsireConverter implements Converter{
    HemsireDao hemsireDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getHemsireDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Hemsire r=(Hemsire) arg2;
        return r.getIdhemsire().toString();
    }

    public HemsireDao getHemsireDao() {
        if(this.hemsireDao==null)
            this.hemsireDao=new HemsireDao();
        return hemsireDao;
    }

    public void setHemsireDao(HemsireDao hemsireDao) {
        this.hemsireDao = hemsireDao;
    }
    
}
