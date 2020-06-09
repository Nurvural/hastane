package controller;

import dao.ResepsiyonDao;
import entity.Resepsiyon;
import entity.Resepsiyon;
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
public class ResepsiyonController implements Serializable {

    private List<Resepsiyon> resepsiyonList;
    private ResepsiyonDao resepsiyonDao;

    private Resepsiyon resepsiyon;
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
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 1 hane giriniz"));
        }
        if (str.length() > 15) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 15 hane girilebilir"));
        }
    }

    public void updateForm(Resepsiyon resepsiyon) {
        this.resepsiyon = resepsiyon;
    }

    public void clearForm() {
        this.resepsiyon = new Resepsiyon();
    }

    public void update() {
        this.getResepsiyonDao().update(this.resepsiyon);
        this.clearForm();
    }

    public void delete() {
        this.getResepsiyonDao().remove(this.resepsiyon);
        this.clearForm();
    }

    public void create() {
        this.getResepsiyonDao().insert(this.resepsiyon);
        this.clearForm();
    }

    /*
    public ResepsiyonController() {
        
        this.resepsiyonList=new ArrayList<>();
        this.resepsiyonDao=new ResepsiyonDao();
    }
    
    
     */
    public List<Resepsiyon> getResepsiyonList() {
        this.resepsiyonList = this.getResepsiyonDao().read(page, pageSize);
        return resepsiyonList;
    }

    public void setResepsiyonList(List<Resepsiyon> resepsiyonList) {
        this.resepsiyonList = resepsiyonList;
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

    public Resepsiyon getResepsiyon() {
        if (this.resepsiyon == null) {
            this.resepsiyon = new Resepsiyon();
        }
        return resepsiyon;
    }

    public void setResepsiyon(Resepsiyon resepsiyon) {
        this.resepsiyon = resepsiyon;
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
        this.pagecount = (int) Math.ceil(this.getResepsiyonDao().count() / (double) pageSize);

        return pagecount;
    }

}
