/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HastaDao;
import dao.TahlilDao;
import entity.Hasta;
import entity.Tahlil;
import entity.Tahlil;
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

/**
 *
 * @author sabiha
 */
@Named
@SessionScoped
public class TahlilController implements Serializable {
    private List<Tahlil> tahlilList;
    private TahlilDao tahlilDao;
    
    private Tahlil tahlil;
    private HastaDao hastaDao;
    private List<Hasta> hastaList;
    
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
        if (str.length() < 3) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if (str.length() > 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 6 hane girilebilir"));
        }
    }
    //private Long selectedHasta; converter a donusturudlugu için bu nesneye ihtiyac klmadı
    public void updateForm(Tahlil tahlil){
        this.tahlil=tahlil;
      //  this.selectedHasta=this.tahlil.getHasta().getIdhasta();
      
      
        
    }
    public void clearForm(){
        this.tahlil=new Tahlil();
        
    }
    public String deleteConfirm(Tahlil tahlil){
        this.tahlil=tahlil;
        return "confirm_delete";
    }
    public void update(){
        this.getTahlilDao().update(this.tahlil);
        this.clearForm();
       
    }
    public void delete(){
        this.getTahlilDao().remove(tahlil);
this.clearForm();
    }
    
    
    public void create(){
        this.getTahlilDao().insert(this.tahlil);
        
    }
    
/*
    public TahlilController() {
        
        this.tahlilList=new ArrayList<>();
        this.tahlilDao=new TahlilDao();
    }
    
    
    */

    public List<Tahlil> getTahlilList() {
        this.tahlilList=this.getTahlilDao().read(page,pageSize);
        return tahlilList;
    }

    public void setTahlilList(List<Tahlil> tahlilList) {
        this.tahlilList = tahlilList;
    }

    public TahlilDao getTahlilDao() {
        if(this.tahlilDao==null)
            this.tahlilDao=new TahlilDao();
        return tahlilDao;
    }

    public void setTahlilDao(TahlilDao tahlilDao) {
        this.tahlilDao = tahlilDao;
    }

    public Tahlil getTahlil() {
        if(this.tahlil==null)
            this.tahlil=new Tahlil();
        return tahlil;
    }

    public void setTahlil(Tahlil tahlil) {
        this.tahlil = tahlil;
    }

    public HastaDao getHastaDao() {
        if(this.hastaDao==null)
            this.hastaDao=new HastaDao();
        return hastaDao;
    }

    public void setHastaDao(HastaDao hastaDao) {
        this.hastaDao = hastaDao;
    }

    public List<Hasta> getHastaList() {
        this.hastaList=this.getHastaDao().read(page,pageSize);
        return hastaList;
    }

    public void setHastaList(List<Hasta> hastaList) {
        this.hastaList = hastaList;
    }
/*
    public Long getSelectedHasta() {
        return selectedHasta;
    }

    public void setSelectedHasta(Long selectedHasta) {
        this.selectedHasta = selectedHasta;
    }

   */
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
        this.pagecount = (int) Math.ceil(this.getTahlilDao().count() / (double) pageSize);

        return pagecount;
    }

    public void setPagecount(int pagecount) {

        this.pagecount = pagecount;
    }

  
    
    
    
    
}
