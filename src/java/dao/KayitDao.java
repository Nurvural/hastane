package dao;

import entity.Kayit;
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

public class KayitDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();
    ResepsiyonDao resepsiyonDao = new ResepsiyonDao();

    public List<Kayit> read(int page, int pageSize) {
        List<Kayit> kayitList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem

        try {
            // TODO code application logic here
            PreparedStatement pst = this.getConnection().prepareStatement("select * from kayit order by idkayit asc limit " + start + "," + pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from kayit");
            while (rs.next()) {
                //System.out.println(rs.getString("kayit_name"));
                Kayit tmp = new Kayit(rs.getLong("idkayit"), rs.getString("kayit_name"), rs.getString("kayit_surname"), rs.getInt("kayitno"), rs.getString("randevu"));
                kayitList.add(tmp);

                rs.getLong("idresepsiyon");//veritabanından çekilecek sutunun
                tmp.setResepsiyon(this.getResepsiyonDao().find(rs.getLong("idresepsiyon")));

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return kayitList;
    }

    public void insert(Kayit kayit) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into kayit (kayit_name,kayit_surname,kayitno,randevu,idresepsiyon) values (?,?,?,?,?)");
            pst.setString(1, kayit.getKayit_name());
            pst.setString(2, kayit.getKayit_surname());
            pst.setInt(3, kayit.getKayitno());
            pst.setString(4, kayit.getRandevu());
            pst.setLong(5, kayit.getResepsiyon().getIdresepsiyon());
            pst.executeUpdate();

            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into kayit (kayit_name,kayit_surname,kayitno,randevu,idresepsiyon) values ('" + kayit.getKayit_name() + "','" + kayit.getKayit_surname() + "'," + kayit.getKayitno() + ",'" + kayit.getRandevu() + "'," + selectedResepsiyon + ")");
        } catch (SQLException ex) {
            Logger.getLogger(KayitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idkayit) as kayit_count from kayit");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("kayit_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void remove(Kayit kayit) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from kayit where idkayit=?");
            pst.setLong(1, kayit.getIdkayit());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KayitDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Kayit kayit) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update kayit set kayit_name=?,kayit_surname=?,kayitno=?,randevu=?,idresepsiyon=? where idkayit=?");
            pst.setString(1, kayit.getKayit_name());
            pst.setString(2, kayit.getKayit_surname());
            pst.setInt(3, kayit.getKayitno());
            pst.setString(4, kayit.getRandevu());
            pst.setLong(5, kayit.getResepsiyon().getIdresepsiyon());
            pst.setLong(6, kayit.getIdkayit());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(KayitDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public ResepsiyonDao getResepsiyonDao() {
        if (this.resepsiyonDao == null) {
            this.resepsiyonDao = new ResepsiyonDao();
        }
        return resepsiyonDao;
    }

}
