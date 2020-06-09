package dao;

import entity.User;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sabiha
 */
public class UserAdminDao  {
    private DBConnection connector;
    private Connection connection;
//okuma işlemi
    public List<User> read(int page, int pageSize, String searchTerm) {
        List<User> userList = new ArrayList<>();

        try {
            String query = "select * from user";

            if (searchTerm != null) {
                query += " where uname like ? ";
            }

            query += " order by id asc limit ? offset ?";
            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (searchTerm != null) {

                st.setString(1, "%" + searchTerm + "%");
                st.setInt(2, pageSize);
                st.setInt(3, (page - 1) * pageSize);
            } else {
                st.setInt(1, pageSize);
                st.setInt(2, (page - 1) * pageSize);
            }
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User tmp = new User();
                tmp.setId(rs.getLong("id"));
                tmp.setUsername(rs.getString("uname"));
                tmp.setPassword(rs.getString("password"));
                tmp.setUturu(rs.getString("userTuru"));
                tmp.setC(rs.getLong("c"));
                tmp.setR(rs.getLong("r"));
                tmp.setU(rs.getLong("u"));
                tmp.setD(rs.getLong("d"));

                userList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return userList;
    }
//silme işlemi
    public void remove(User user) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from user where id=?");
            pst.setLong(1, user.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//ekleme işlemi
    public void create(User user) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into user (uname,password,userTuru,c,r,u,d) values (?,?,?,?,?,?,?)");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getUturu());
            pst.setLong(4, user.getC());
            pst.setLong(5, user.getR());
            pst.setLong(6, user.getU());
            pst.setLong(7, user.getD());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
//güncelleme işlemi
    public void edit(User user) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update user set uname=?, password=? , userTuru=? , c=? , r=? , u=? , d=? where id=?");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getUturu());

            pst.setLong(4, user.getC());
            pst.setLong(5, user.getR());
            pst.setLong(6, user.getU());
            pst.setLong(7, user.getD());

            pst.setLong(8, user.getId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//sayfalama işlemi için gerekli olan count methodu
    public int count(String searchTerm) {
        int count = 0;

        try {

            String query = "select count(id) as uu from user";
            if (searchTerm != null) {
                query += " where uname like ? ";
            }
            PreparedStatement st = this.getConnection().prepareStatement(query);

            if (searchTerm != null) {
                st.setString(1, "%" + searchTerm + "%");
            }
            ResultSet rs = st.executeQuery();

            rs.next();
            count = rs.getInt("uu");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
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
