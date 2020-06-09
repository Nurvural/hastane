package dao;

import entity.Hemsire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

public class HemsireDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();

    public List<Hemsire> read(int page,int pageSize) {
        List<Hemsire> hemsireList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem

        try {
            // TODO code application logic here
            PreparedStatement pst = this.getConnection().prepareStatement("select * from hemsire order by idhemsire asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = this.getConnection().createStatement();
            // ResultSet rs = st.executeQuery("select * from hemsire");
            while (rs.next()) {
                //System.out.println(rs.getString("doktor_name"));
                Hemsire tmp = new Hemsire(rs.getLong("idhemsire"), rs.getString("hemsire_name"), rs.getString("hemsire_surname"), rs.getString("hemsire_turu"));
                hemsireList.add(tmp);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return hemsireList;
    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idhemsire) as hemsire_count from hemsire");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("hemsire_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public Hemsire find(Long id) {
        Hemsire b = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from hemsire where idhemsire=?");
            pst.setLong(1, id);

            //Statement st = this.getConnection().createStatement();
            //ResultSet rs = st.executeQuery("select * from hemsire where idhemsire=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            b = new Hemsire();
            b.setIdhemsire(rs.getLong("idhemsire"));
            b.setHemsire_name(rs.getString("hemsire_name"));
            b.setHemsire_surname(rs.getString("hemsire_surname"));
            b.setHemsire_turu(rs.getString("hemsire_turu"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return b;

    }

    public void insert(Hemsire hemsire) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into hemsire (hemsire_name,hemsire_surname, hemsire_turu) values (?,?, ?)");
            pst.setString(1, hemsire.getHemsire_name());
            pst.setString(2, hemsire.getHemsire_surname());
            pst.setString(3, hemsire.getHemsire_turu());
            pst.executeUpdate();
            // Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into hemsire (hemsire_name,hemsire_number) values ('" + hemsire.getHemsire_name() + "','" + hemsire.getHemsire_number() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(HemsireDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remove(Hemsire hemsire) {

        try {

            Statement st = this.getConnection().createStatement();
            st.executeUpdate("delete from hemsire where idhemsire=" + hemsire.getIdhemsire());
        } catch (SQLException ex) {
            Logger.getLogger(HemsireDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Hemsire hemsire) {

        try {

            Statement st = connection.createStatement();
            st.executeUpdate("update hemsire set hemsire_name='" + hemsire.getHemsire_name() + "', hemsire_surname = '" + hemsire.getHemsire_surname() + "', hemsire_turu = '" + hemsire.getHemsire_turu() + "' where idhemsire="+hemsire.getIdhemsire());
        } catch (SQLException ex) {
            Logger.getLogger(HemsireDao.class.getName()).log(Level.SEVERE, null, ex);
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
