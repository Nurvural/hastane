/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Tahlil;
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
public class TahlilDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();
    HastaDao hastaDao = new HastaDao();

    public List<Tahlil> read(int page,int pageSize) {

        List<Tahlil> tahlilList = new ArrayList<>();
        int start = (page-1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from tahlil order by idtahlil asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from tahlil");
            while (rs.next()) {
                //System.out.println(rs.getString("tahlil_name"));
                Tahlil tmp = new Tahlil(rs.getLong("idtahlil"), rs.getString("tahlil_name"), rs.getString("tahlil_surname"),rs.getString("tahlil_turu"));
                tahlilList.add(tmp);

                rs.getLong("idhasta");//veritabanından çekilecek sutunun
                tmp.setHasta(this.getHastaDao().find(rs.getLong("idhasta")));

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return tahlilList;
    }

    public void insert(Tahlil tahlil) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into tahlil (tahlil_name,tahlil_surname,tahlil_turu,idhasta) values (?,?,?,?)");
            pst.setString(1, tahlil.getTahlil_name());
            pst.setString(2, tahlil.getTahlil_surname());
            pst.setString(3, tahlil.getTahlil_turu());
            pst.setLong(4, tahlil.getHasta().getIdhasta());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into tahlil (tahlil_name,tahlil_surname,turu,salary,idhasta) values ('" + tahlil.getTahlil_name() + "','" + tahlil.getTahlil_surname() + "','" + tahlil.getTuru() + "'," + tahlil.getSalary() + "," + selectedHasta + ")");
        } catch (SQLException ex) {
            Logger.getLogger(TahlilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idtahlil) as tahlil_count from tahlil");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("tahlil_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void remove(Tahlil tahlil) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from tahlil where idtahlil=?");
            pst.setLong(1, tahlil.getIdtahlil());
            pst.executeUpdate();
            // Statement st = connection.createStatement();
            // st.executeUpdate("delete from tahlil where idtahlil=" + tahlil.getIdtahlil());
        } catch (SQLException ex) {
            Logger.getLogger(TahlilDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Tahlil tahlil) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update tahlil set tahlil_name=?,tahlil_surname=?,turu=?,idhasta=? where idtahlil=?");
            pst.setString(1, tahlil.getTahlil_name());
            pst.setString(2, tahlil.getTahlil_surname());
            pst.setString(3, tahlil.getTahlil_turu());
            pst.setLong(4, tahlil.getHasta().getIdhasta());
            pst.setLong(5, tahlil.getIdtahlil());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into tahlil (tahlil_name,tahlil_surname,turu,salary,idhasta) values ('" + tahlil.getTahlil_name() + "','" + tahlil.getTahlil_surname() + "','" + tahlil.getTuru() + "'," + tahlil.getSalary() + "," + selectedHasta + ")");
        } catch (SQLException ex) {
            Logger.getLogger(TahlilDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public HastaDao getHastaDao() {
        if (this.hastaDao == null) {
            this.hastaDao = new HastaDao();
        }
        return hastaDao;
    }

}
