package dao;

import util.DBConnection;
import filter.DBCon;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterDao implements Serializable{

    private DBConnection connector;
    private Connection connection;

   public   void insert(String uname, String password, String userTuru) {
         
        try {
            //yeni bir kullanıcı veyahut admin eklemek için gerekli olan sorgu statement ile yapılmıştır
             Statement sts = this.getConnection().createStatement();
            String sql = "INSERT INTO user(uname,password,userTuru,c,r,u,d) VALUES('" + uname + "','" + password + "','" + userTuru + "','" +0+ "','" + 0 + "','" + 0 + "','" + 0 + "')";
            sts.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }
        return connector;
    }
    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }

}
