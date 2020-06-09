package controller;

import dao.RegisterDao;
import entity.User;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@javax.enterprise.context.SessionScoped

public class RegisterController implements Serializable {

    private String uname;
    private String password;
    private String userTuru;
    private User user;
    private RegisterDao registerDao;

//kullanıcı oluşturabilmek için gerekli olan create fonksiyonun controller kısmı
    public String create() {
        //bu kısımda registerdaoda insert methodu kullanılır
        this.getRegisterDao().insert(this.uname, this.password, this.userTuru);
        this.uname = null;
        this.password = null;

        return "login";
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

    public RegisterController() {

        this.uname = "";
        this.password = "";

    }

    public RegisterController(String uname, String password, String userTuru) {

        this.uname = uname;
        this.password = password;
        this.userTuru = userTuru;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserTuru() {
        return userTuru;
    }

    public void setUserTuru(String userTuru) {
        this.userTuru = userTuru;
    }

    public RegisterDao getRegisterDao() {
        if (this.registerDao == null) {
            this.registerDao = new RegisterDao();
        }
        return registerDao;
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
