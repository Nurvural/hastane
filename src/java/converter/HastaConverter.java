/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.HastaDao;
import entity.Hasta;
import entity.Resepsiyon;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sabiha
 */
@FacesConverter(value = "hastaConverter")
public class HastaConverter implements Converter {

    private HastaDao hastaDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getHastaDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Hasta h= (Hasta) arg2;
        return h.getIdhasta().toString();
    }

    public HastaDao getHastaDao() {
        if (this.hastaDao == null) {
            this.hastaDao = new HastaDao();
        }
        return hastaDao;
    }

    public void setHastaDao(HastaDao hastaDao) {
        this.hastaDao = hastaDao;
    }

}
