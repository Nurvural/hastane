package converter;

import dao.ResepsiyonDao;
import entity.Resepsiyon;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "resepsiyonConverter")
public class ResepsiyonConverter implements Converter {

    ResepsiyonDao resepsiyonDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getResepsiyonDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Resepsiyon r = (Resepsiyon) arg2;
        return r.getIdresepsiyon().toString();
    }

    public ResepsiyonDao getResepsiyonDao() {
        if (this.resepsiyonDao == null) {
            this.resepsiyonDao = new ResepsiyonDao();
        }
        return resepsiyonDao;
    }

    public void setResepsiyonDao(ResepsiyonDao resepsiyonDao) {
        this.resepsiyonDao = resepsiyonDao;
    }

}
