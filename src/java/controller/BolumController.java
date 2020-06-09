package controller;

import dao.BolumDao;
import dao.DoktorDao;
import entity.Bolum;
import entity.Personel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class BolumController implements Serializable {

    private List<Bolum> bolumList;
    private BolumDao bolumDao;

    private Bolum bolum;

    private Long selectedBolum;

    // private List<Long> selectedPersonel; converter a donusturuldugu için bu nesneye ihityac kalmadı
    @Inject
    private PersonelController personelController;
    
    
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
        if(this.page==1)
            this.page=this.getPagecount();
        else
        this.page--;
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

    public void updateForm(Bolum bolum) {
        this.bolum = bolum;
    }

    public void clearForm() {
        this.bolum = new Bolum();
    }

  

    public void update() {
        this.getBolumDao().update(this.bolum);
        this.clearForm();

    }

    public void delete() {
        this.getBolumDao().remove(bolum);
        this.clearForm();
    }

    public void create() {
        this.getBolumDao().insert(this.bolum);
        this.clearForm();
    }

    /*
    public BolumController() {
        
        this.bolumList=new ArrayList<>();
        this.bolumDao=new BolumDao();
    }
    
    
     */
    public List<Bolum> getBolumList() {
        this.bolumList = this.getBolumDao().read(page,pageSize);
        return bolumList;
    }

    public void setBolumList(List<Bolum> bolumList) {
        this.bolumList = bolumList;
    }

    public BolumDao getBolumDao() {
        if (this.bolumDao == null) {
            this.bolumDao = new BolumDao();
        }
        return bolumDao;
    }

    public void setBolumDao(BolumDao bolumDao) {
        this.bolumDao = bolumDao;
    }

    public Bolum getBolum() {
        if (this.bolum == null) {
            this.bolum = new Bolum();
        }
        return bolum;
    }

    public void setBolum(Bolum bolum) {
        this.bolum = bolum;
    }

    public Long getSelectedBolum() {
        return selectedBolum;
    }

    public void setSelectedBolum(Long selectedBolum) {
        this.selectedBolum = selectedBolum;
    }

    public PersonelController getPersonelController() {
        return personelController;
    }

    public void setPersonelController(PersonelController personelController) {
        this.personelController = personelController;
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

    public int getPagecount() {//kaç sayfa olduğunu bulacak olan kısım
        this.pagecount = (int) Math.ceil(this.getBolumDao().count() / (double) pageSize);
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

}
