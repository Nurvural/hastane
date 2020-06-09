package controller;

import dao.PersonelDao;
import entity.Personel;
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
public class PersonelController implements Serializable {

    private List<Personel> personelList;
    private PersonelDao personelDao;

    private Personel personel;
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

    //karakter sınırlaması için kullanılır kullanıcılar en az 1 en fazla 15 karakterli personel bilgieri girebilirler
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = value.toString();
        if (str.length() < 1) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 1 hane giriniz"));
        }
        if (str.length() > 15) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 15 hane girilebilir"));
        }
    }

    public void updateForm(Personel personel) {
        this.personel = personel;
    }

    public void clearForm() {
        this.personel = new Personel();//ekleme silme veyahuuta güncelleme işlemi yapıldığı zaman text alanlarını boşlatmak için bu method çağırılır

    }

    public void update() {
        this.getPersonelDao().update(this.personel);
        this.personel = new Personel();

    }

    //silme işleminin yapılabilmesi için dao sınıfına yönelndirme yapılır 
    public void delete() {
        this.getPersonelDao().remove(this.personel);
        this.personel = new Personel();

    }

    //ekleme işleminin yapılabilmesi için dao sınıfında bir method oluşturulur ve bu method kullanılır
    public void create() {
        this.getPersonelDao().insert(this.personel);
        this.personel = new Personel();//ekleme işlemi yapıldıktan sonra formu boşaltmak için

    }

    public List<Personel> getPersonelList() {
        this.personelList = this.getPersonelDao().read(page, pageSize);
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public PersonelDao getPersonelDao() {
        if (this.personelDao == null) {
            this.personelDao = new PersonelDao();
        }
        return personelDao;
    }

    public void setPersonelDao(PersonelDao personelDao) {
        this.personelDao = personelDao;
    }

    public Personel getPersonel() {
        if (this.personel == null) {
            this.personel = new Personel();
        }
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
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
        this.pagecount = (int) Math.ceil(this.getPersonelDao().count() / (double) pageSize);

        return pagecount;
    }

}
