
package dao;

import entity.Resepsiyon;
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


public class ResepsiyonDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();

    public List<Resepsiyon> read(int page,int pageSize) {
        List<Resepsiyon> resepsiyonList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem

        try {
            // TODO code application logic here
            PreparedStatement pst = this.getConnection().prepareStatement("select * from resepsiyon order by idresepsiyon asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = this.getConnection().createStatement();
            // ResultSet rs = st.executeQuery("select * from resepsiyon");
            while (rs.next()) {
                //System.out.println(rs.getString("doktor_name"));
                Resepsiyon tmp = new Resepsiyon(rs.getLong("idresepsiyon"), rs.getString("resepsiyon_name"), rs.getInt("resepsiyon_number"));
                resepsiyonList.add(tmp);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return resepsiyonList;
    }

    public int count() {//sayfalama işlemi için gerekli olan method
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idresepsiyon) as resepsiyon_count from resepsiyon");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("resepsiyon_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public Resepsiyon find(Long id) {
        Resepsiyon b = null;
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from resepsiyon where idresepsiyon=?");
            pst.setLong(1, id);

            //Statement st = this.getConnection().createStatement();
            //ResultSet rs = st.executeQuery("select * from resepsiyon where idresepsiyon=" + id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            b = new Resepsiyon();
            b.setIdresepsiyon(rs.getLong("idresepsiyon"));
            b.setResepsiyon_name(rs.getString("resepsiyon_name"));
            b.setResepsiyon_number(rs.getInt("resepsiyon_number"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return b;

    }

    public void insert(Resepsiyon resepsiyon) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into resepsiyon (resepsiyon_name,resepsiyon_number) values (?,?)");
            pst.setString(1, resepsiyon.getResepsiyon_name());
            pst.setInt(2, resepsiyon.getResepsiyon_number());
            pst.executeUpdate();
            // Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into resepsiyon (resepsiyon_name,resepsiyon_number) values ('" + resepsiyon.getResepsiyon_name() + "','" + resepsiyon.getResepsiyon_number() + "'");
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public void remove(Resepsiyon resepsiyon) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from resepsiyon where idresepsiyon=?");
            pst.setLong(1, resepsiyon.getIdresepsiyon());
            pst.executeUpdate();
            // Statement st = connection.createStatement();
            // st.executeUpdate("delete from doktor where iddoktor=" + doktor.getIddoktor());
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  public void update(Resepsiyon resepsiyon) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update resepsiyon set resepsiyon_name=?,resepsiyon_number=? where idresepsiyon=?");
            pst.setString(1,resepsiyon.getResepsiyon_name());
            pst.setInt(2, resepsiyon.getResepsiyon_number()); 
            pst.setLong(3, resepsiyon.getIdresepsiyon());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into doktor (doktor_name,doktor_surname,turu,salary,idbolum) values ('" + doktor.getDoktor_name() + "','" + doktor.getDoktor_surname() + "','" + doktor.getTuru() + "'," + doktor.getSalary() + "," + selectedBolum + ")");
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonDao.class.getName()).log(Level.SEVERE, null, ex);
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
