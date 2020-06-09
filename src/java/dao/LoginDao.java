
package dao;

import entity.User;
import util.DBConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sabiha
 */
public class LoginDao  implements Serializable {
    private DBConnection connector;
    private Connection connection;

    

    public User validate(String user) {
        //bu kısım doğrulama kısmıdır.yani aşağıda verilen try-catch kısmında veritabanındaki user tablosunun varlığı sorgulanır
        Connection con;

        PreparedStatement ps;

        User user2 = null;

        try {
            con = this.getConnection();
            ps = con.prepareStatement("select * from user  where  uname='" + user + "'");

            ResultSet rs = ps.executeQuery();
            rs.next();
            user2 = new User();
//ve içindeki kolanların varlığı
            user2.setUsername(rs.getString("uname"));
            user2.setPassword(rs.getString("password"));
            user2.setUturu(rs.getString("userTuru"));
            user2.setC(rs.getLong("c"));
            user2.setR(rs.getLong("r"));
            user2.setU(rs.getLong("u"));
            user2.setD(rs.getLong("d"));

        } catch (SQLException ex) {
            return null;

        }
        return user2;
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
