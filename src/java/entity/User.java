package entity;

import java.io.Serializable;


public class User implements Serializable{

    private Long id ;
    private String username;
    private String password;
    private String uturu;

    private Long c ;
    private Long r ;
    private Long u ;
    private Long d ;

    public User(Long id, String username, String password, String uturu, Long c, Long r, Long u, Long d) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.uturu = uturu;
        this.c = c;
        this.r = r;
        this.u = u;
        this.d = d;
    }

    public Long getC() {
        return c;
    }

    public void setC(Long c) {
        this.c = c;
    }

    public Long getR() {
        return r;
    }

    public void setR(Long r) {
        this.r = r;
    }

    public Long getU() {
        return u;
    }

    public void setU(Long u) {
        this.u = u;
    }

    public Long getD() {
        return d;
    }

    public void setD(Long d) {
        this.d = d;
    }
    
    

    public User() {
        
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUturu() {
       
        return uturu;
    }

    public void setUturu(String uturu) {
        this.uturu = uturu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", uturu=" + uturu + '}';
    }

   
}
