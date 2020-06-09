package dao;

import entity.Oda;
import entity.Personel;
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

public class OdaDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();
    HemsireDao hemsireDao = new HemsireDao();

    public List<Oda> read(int page,int pageSize) {
        List<Oda> odaList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem


        try {
            // TODO code application logic here
            PreparedStatement pst = this.getConnection().prepareStatement("select * from oda order by idoda asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oda");
            while (rs.next()) {
                //System.out.println(rs.getString("oda_name"));
                Oda tmp = new Oda(rs.getLong("idoda"), rs.getInt("odano"), rs.getString("oda_tipi"));
                odaList.add(tmp);

                rs.getLong("idhemsire");//veritabanından çekilecek sutunun
                tmp.setHemsire(this.getHemsireDao().find(rs.getLong("idoda")));

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return odaList;
    }

    public int count() {//sayfalama işlemi için gerekli olan method
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idoda) as oda_count from oda");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("oda_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void insert(Oda oda) {
        try {

            PreparedStatement pst = this.getConnection().prepareStatement("insert into oda (odano, oda_tipi, idhemsire) values (?,?,?,?,?)");
            pst.setLong(1, oda.getOdano());
            pst.setString(2, oda.getOda_tipi());

            pst.setLong(3, oda.getHemsire().getIdhemsire());
            pst.executeUpdate();

            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into oda (oda_name,oda_surname,odano,randevu,idresepsiyon) values ('" + oda.getOda_name() + "','" + oda.getOda_surname() + "'," + oda.getOdano() + ",'" + oda.getRandevu() + "'," + selectedResepsiyon + ")");
        } catch (SQLException ex) {
            Logger.getLogger(OdaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Oda oda) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from oda where idoda=");
            pst.setLong(1, oda.getIdoda());
            pst.executeUpdate();

            //Statement st = connection.createStatement();
            //st.executeUpdate("delete from bolum where idbolum=" + bolum.getIdbolum());
        } catch (SQLException ex) {
            Logger.getLogger(BolumDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {

            Statement st = connection.createStatement();
            st.executeUpdate("delete from oda where idoda=" + oda.getIdoda());
        } catch (SQLException ex) {
            Logger.getLogger(OdaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }

    public void update(Oda oda) {
        /*
        try {

            Statement st = connection.createStatement();
            st.executeUpdate("update oda set oda_name='" + oda.getOda_name() + "',oda_surname='" + oda.getOda_surname() + "',odano='" + oda.getOdano() + "',randevu=" + oda.getRandevu() + " where idoda=" + oda.getIdoda());
        } catch (SQLException ex) {
            Logger.getLogger(OdaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update oda set odano=?,oda_tipi=? where idoda=?");
            pst.setLong(1, oda.getOdano());
            pst.setString(2, oda.getOda_tipi());
            pst.setLong(3, oda.getIdoda());
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

    public HemsireDao getHemsireDao() {
        if (this.hemsireDao == null) {
            this.hemsireDao = new HemsireDao();
        }
        return hemsireDao;
    }

}
