package controller;

import dao.LoginDao;
import entity.User;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private User user;
    private LoginDao userdao;

    public String login() {
        //ilk olarak giriş işleminin gerçekleşmesi için bu kısmın gerçekleşmesi gerekmektedir
        //bu kısımda kullanıcı veyahutta admin adi ve buna göre şifresinin kontrolü gerçekleşmektedir.

        User tmp = getUserdao().validate(getUser().getUsername());
        if (tmp != null && tmp.getPassword().equalsIgnoreCase(getUser().getPassword())) {

            setUser(tmp);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user", this.user);
//eğer kullanıcının türü admin ise admin sayfasına 
            if (tmp != null && tmp.getUturu().equals("Admin")) {

                return "/secret/secret?faces-redirect=true";

            } else {//eğer değilse kullanıcı sayfasına yönlendirilecektir

                return "/Users/View?faces-redirect=true";
            }
        } else {
//eğerki bilgiler hatalı girilirse kullanıcıya hata mesajı gönderilecektir
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Yanlış veya eksik bilgi girdiniz "));
            setUser(null);

            return "login.xhtml";//son olarak login sayfasına yönlendir
        }

    }

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = value.toString();
        if (str.length() < 3) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En az 3 hane giriniz"));
        }
        if (str.length() > 8) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "summary", "En fazla 8 hane girilebilir"));
        }
    }

    public User getUser() {
        if (this.user == null) {//eğer user yok ise yeni bir user oluştur
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginDao getUserdao() {
        if (this.userdao == null) {//eğer usedao null ise yeni bir kullanıcı oluştur
            this.userdao = new LoginDao();
        }
        return userdao;
    }

    public void setUserdao(LoginDao userdao) {
        this.userdao = userdao;
    }

}
