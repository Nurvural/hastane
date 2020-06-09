
package controller;

import dao.HemsireDao;
import dao.OdaDao;
import entity.Hemsire;
import entity.Oda;
import entity.Oda;
import entity.Personel;
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
public class OdaController implements Serializable {
    private List<Oda> odaList;
    private OdaDao odaDao;
    
    private Oda oda;
    private HemsireDao hemsireDao;
    private List<Hemsire> hemsireList;
   // private Long selectedHemsire;
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
    public void updateForm(Oda oda){
        this.oda=oda;
      
    }
    public void clearForm(){
        this.oda=new Oda();
       
    }
    public String deleteConfirm(Oda oda){
        this.oda=oda;
        return "confirm_delete";
    }
    public void update(){
        this.getOdaDao().update(this.oda);
        this.clearForm();
       
    }
    public void delete(){
        this.getOdaDao().remove(this.oda);
        this.clearForm();
    }
    
    public void create(){
        this.getOdaDao().insert(this.oda);
        this.clearForm();
    }
    
/*
    public OdaController() {
        
        this.odaList=new ArrayList<>();
        this.odaDao=new OdaDao();
    }
    
    
    */

    public List<Oda> getOdaList() {
        this.odaList=this.getOdaDao().read(page,pageSize);
        return odaList;
    }

    public void setOdaList(List<Oda> odaList) {
        this.odaList = odaList;
    }

    public OdaDao getOdaDao() {
        if(this.odaDao==null)
            this.odaDao=new OdaDao();
        return odaDao;
    }

    public void setOdaDao(OdaDao odaDao) {
        this.odaDao = odaDao;
    }

    public Oda getOda() {
        if(this.oda==null)
            this.oda=new Oda();
        return oda;
    }

    public void setOda(Oda oda) {
        this.oda = oda;
    }

     public HemsireDao getHemsireDao() {
        if(this.hemsireDao==null)
            this.hemsireDao=new HemsireDao();
        return hemsireDao;
    }

    public void setHemsireDao(HemsireDao hemsireDao) {
        this.hemsireDao = hemsireDao;
    }

    public List<Hemsire> getHemsireList() {
        this.hemsireList=this.getHemsireDao().read(page, pageSize);
        return hemsireList;
    }

    public void setHemsireList(List<Hemsire> hemsireList) {
        this.hemsireList = hemsireList;
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
        this.pagecount = (int) Math.ceil(this.getOdaDao().count() / (double) pageSize);

        return pagecount;
    } 
   
    
    
    
    
    
}
