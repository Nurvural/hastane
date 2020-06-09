
package dao;

import entity.Doktor;
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

/**
 *
 * @author sabiha
 */
public class DoktorDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();
    BolumDao bolumDao = new BolumDao();

    public List<Doktor> read(int page,int pageSize) {

        List<Doktor> doktorList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from doktor order by iddoktor asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from doktor");
            while (rs.next()) {
                //System.out.println(rs.getString("doktor_name"));
                Doktor tmp = new Doktor(rs.getLong("iddoktor"), rs.getString("doktor_name"), rs.getString("doktor_surname"), rs.getInt("salary"), rs.getString("turu"));
                doktorList.add(tmp);

                rs.getLong("idbolum");//veritabanından çekilecek sutunun
                tmp.setBolum(this.getBolumDao().find(rs.getLong("idbolum")));

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return doktorList;
    }

    public void insert(Doktor doktor) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into doktor (doktor_name,doktor_surname,turu,salary,idbolum) values (?,?,?,?,?)");
            pst.setString(1, doktor.getDoktor_name());
            pst.setString(2, doktor.getDoktor_surname());
            pst.setString(3, doktor.getTuru());
            pst.setInt(4, doktor.getSalary());
            pst.setLong(5, doktor.getBolum().getIdbolum());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into doktor (doktor_name,doktor_surname,turu,salary,idbolum) values ('" + doktor.getDoktor_name() + "','" + doktor.getDoktor_surname() + "','" + doktor.getTuru() + "'," + doktor.getSalary() + "," + selectedBolum + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DoktorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(iddoktor) as doktor_count from doktor");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("doktor_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void remove(Doktor doktor) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from doktor where iddoktor=?");
            pst.setLong(1, doktor.getIddoktor());
            pst.executeUpdate();
            // Statement st = connection.createStatement();
            // st.executeUpdate("delete from doktor where iddoktor=" + doktor.getIddoktor());
        } catch (SQLException ex) {
            Logger.getLogger(DoktorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Doktor doktor) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update doktor set doktor_name=?,doktor_surname=?,turu=?,salary=?,idbolum=? where iddoktor=?");
            pst.setString(1, doktor.getDoktor_name());
            pst.setString(2, doktor.getDoktor_surname());
            pst.setString(3, doktor.getTuru());
            pst.setInt(4, doktor.getSalary());
            pst.setLong(5, doktor.getBolum().getIdbolum());
            pst.setLong(6, doktor.getIddoktor());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into doktor (doktor_name,doktor_surname,turu,salary,idbolum) values ('" + doktor.getDoktor_name() + "','" + doktor.getDoktor_surname() + "','" + doktor.getTuru() + "'," + doktor.getSalary() + "," + selectedBolum + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DoktorDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public BolumDao getBolumDao() {
        if (this.bolumDao == null) {
            this.bolumDao = new BolumDao();
        }
        return bolumDao;
    }

}
