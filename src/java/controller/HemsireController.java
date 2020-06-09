package controller;

import dao.HemsireDao;
import entity.Hemsire;
import entity.Hemsire;
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
public class HemsireController implements Serializable {

    private List<Hemsire> hemsireList;
    private HemsireDao hemsireDao;

    private Hemsire hemsire;
    private int page = 1;//kaçıncı sayfadan başlaması gerektiği
    private int pageSize = 10;//bir sayfada kaç nesne olması gerektiği
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
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if (str.length() > 15) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }

    public void updateForm(Hemsire hemsire) {
        this.hemsire = hemsire;

    }

    public void clearForm() {
        this.hemsire = new Hemsire();

    }

    public String deleteConfirm(Hemsire hemsire) {
        this.hemsire = hemsire;
        return "confirm_delete";
    }

    public String update() {
        this.getHemsireDao().update(this.hemsire);
        return "index";
    }

    public void delete() {
        this.getHemsireDao().remove(hemsire);

    }

    public void create() {
        this.getHemsireDao().insert(this.hemsire);

    }

    /*
    public HemsireController() {
        
        this.hemsireList=new ArrayList<>();
        this.hemsireDao=new HemsireDao();
    }
    
    
     */
    public List<Hemsire> getHemsireList() {
        this.hemsireList = this.getHemsireDao().read(page, pageSize);
        return hemsireList;
    }

    public void setHemsireList(List<Hemsire> hemsireList) {
        this.hemsireList = hemsireList;
    }

    public HemsireDao getHemsireDao() {
        if (this.hemsireDao == null) {
            this.hemsireDao = new HemsireDao();
        }
        return hemsireDao;
    }

    public void setHemsireDao(HemsireDao hemsireDao) {
        this.hemsireDao = hemsireDao;
    }

    public Hemsire getHemsire() {
        if (this.hemsire == null) {
            this.hemsire = new Hemsire();
        }
        return hemsire;
    }

    public void setHemsire(Hemsire hemsire) {
        this.hemsire = hemsire;
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
        this.pagecount = (int) Math.ceil(this.getHemsireDao().count() / (double) pageSize);

        return pagecount;
    }
}
