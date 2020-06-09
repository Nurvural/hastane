/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HastaDao;
import entity.Hasta;
import entity.Hasta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author sabiha
 */
@Named
@SessionScoped
public class HastaController implements Serializable {
    private List<Hasta> hastaList;
    private HastaDao hastaDao;
    
    private Hasta hasta;
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
        if (str.length() < 3) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if (str.length() > 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }
    public void updateForm(Hasta hasta){
        this.hasta=hasta;
        
    }
    public void clearForm(){
        this.hasta=new Hasta();
    }
    public void deleteConfirm(Hasta hasta){
        this.hasta=hasta;

    }
    public void update(){
        this.getHastaDao().update(this.hasta);
        this.clearForm();

    }
    public void delete(){
        this.getHastaDao().remove(hasta);
          this.clearForm();

    }
    
    
    public void create(){
        this.getHastaDao().insert(this.hasta);
        this.clearForm();
    }
    
/*
    public HastaController() {
        
        this.hastaList=new ArrayList<>();
        this.hastaDao=new HastaDao();
    }
    
    
    */

    public List<Hasta> getHastaList() {
        this.hastaList=this.getHastaDao().read(page,pageSize);
        return hastaList;
    }

    public void setHastaList(List<Hasta> hastaList) {
        this.hastaList = hastaList;
    }

    public HastaDao getHastaDao() {
        if(this.hastaDao==null)
            this.hastaDao=new HastaDao();
        return hastaDao;
    }

    public void setHastaDao(HastaDao hastaDao) {
        this.hastaDao = hastaDao;
    }

    public Hasta getHasta() {
        if(this.hasta==null)
            this.hasta=new Hasta();
        return hasta;
    }

    public void setHasta(Hasta hasta) {
        this.hasta = hasta;
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
        this.pagecount = (int) Math.ceil(this.getHastaDao().count() / (double) pageSize);

        return pagecount;
    } 
    
}
