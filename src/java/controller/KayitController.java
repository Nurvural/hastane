package controller;

import dao.ResepsiyonDao;
import dao.KayitDao;
import entity.Resepsiyon;
import entity.Kayit;
import entity.Kayit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@SessionScoped
public class KayitController implements Serializable {

    private List<Kayit> kayitList;
    private KayitDao kayitDao;

    private Kayit kayit;
    private ResepsiyonDao resepsiyonDao;
    private List<Resepsiyon> resepsiyonList;
    // private Long selectedResepsiyon;
    private int page = 1;
    private int pageSize = 10;
    private int pagecount;

    public void next() {
        //sonraki sayfaya geçecek method
        if (this.page == this.getPagecount()) {
            this.page = 1;
        } else {
            this.page++;

        }
    }

    public void previous() {
        //onceki sayfaya gececek method
        if (this.page == 1) {
            this.page = this.getPagecount();
        } else {
            this.page--;
        }
    }

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = value.toString();
        if (str.length() < 1) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 1 hane giriniz"));
        }
        if (str.length() > 15) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 15 hane girilebilir"));
        }
    }

    public void updateForm(Kayit kayit) {
        this.kayit = kayit;
    }

    public void clearForm() {
        this.kayit = new Kayit();
    }

    public void update() {
        this.getKayitDao().update(this.kayit);
        this.clearForm();

    }

    public void delete() {
        this.getKayitDao().remove(this.kayit);
        this.clearForm();
    }

    public void create() {
        this.getKayitDao().insert(this.kayit);
        this.clearForm();
    }

    public List<Kayit> getKayitList() {
        this.kayitList = this.getKayitDao().read(page, pageSize);
        return kayitList;
    }

    public void setKayitList(List<Kayit> kayitList) {
        this.kayitList = kayitList;
    }

    public KayitDao getKayitDao() {
        if (this.kayitDao == null) {
            this.kayitDao = new KayitDao();
        }
        return kayitDao;
    }

    public void setKayitDao(KayitDao kayitDao) {
        this.kayitDao = kayitDao;
    }

    public Kayit getKayit() {
        if (this.kayit == null) {
            this.kayit = new Kayit();
        }
        return kayit;
    }

    public void setKayit(Kayit kayit) {
        this.kayit = kayit;
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

    public List<Resepsiyon> getResepsiyonList() {
        this.resepsiyonList = this.getResepsiyonDao().read(page, pageSize);
        return resepsiyonList;
    }

    public void setResepsiyonList(List<Resepsiyon> resepsiyonList) {
        this.resepsiyonList = resepsiyonList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPagecount() {
        this.pagecount = (int) Math.ceil(this.getKayitDao().count() / (double) pageSize);

        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

}
