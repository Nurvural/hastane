/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BolumDao;
import dao.DoktorDao;
import entity.Bolum;
import entity.Doktor;
import entity.Doktor;
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
public class DoktorController implements Serializable {

    private List<Doktor> doktorList;
    private DoktorDao doktorDao;

    private Doktor doktor;
    private BolumDao bolumDao;
    private List<Bolum> bolumList;

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

    //private Long selectedBolum; converter a donusturudlugu için bu nesneye ihtiyac klmadı
    public void updateForm(Doktor doktor) {
        this.doktor = doktor;
        //  this.selectedBolum=this.doktor.getBolum().getIdbolum();

    }

    public void clearForm() {
        this.doktor = new Doktor();

    }


    public void update() {
        this.getDoktorDao().update(this.doktor);
        this.clearForm();

    }

    public void delete() {
        this.getDoktorDao().remove(doktor);
        this.clearForm();
    }

    public void create() {
        this.getDoktorDao().insert(this.doktor);

    }

    /*
    public DoktorController() {
        
        this.doktorList=new ArrayList<>();
        this.doktorDao=new DoktorDao();
    }
    
    
     */
    public List<Doktor> getDoktorList() {
        this.doktorList = this.getDoktorDao().read(page, pageSize);
        return doktorList;
    }

    public void setDoktorList(List<Doktor> doktorList) {
        this.doktorList = doktorList;
    }

    public DoktorDao getDoktorDao() {
        if (this.doktorDao == null) {
            this.doktorDao = new DoktorDao();
        }
        return doktorDao;
    }

    public void setDoktorDao(DoktorDao doktorDao) {
        this.doktorDao = doktorDao;
    }

    public Doktor getDoktor() {
        if (this.doktor == null) {
            this.doktor = new Doktor();
        }
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
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

    public List<Bolum> getBolumList() {
        this.bolumList = this.getBolumDao().read(page, pageSize);
        return bolumList;
    }

    public void setBolumList(List<Bolum> bolumList) {
        this.bolumList = bolumList;
    }

    /*
    public Long getSelectedBolum() {
        return selectedBolum;
    }

    public void setSelectedBolum(Long selectedBolum) {
        this.selectedBolum = selectedBolum;
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
        this.pagecount = (int) Math.ceil(this.getDoktorDao().count() / (double) pageSize);

        return pagecount;
    }

    public void setPagecount(int pagecount) {

        this.pagecount = pagecount;
    }

}
