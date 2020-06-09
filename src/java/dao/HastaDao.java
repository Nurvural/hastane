/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Hasta;
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

/**
 *
 * @author sabiha
 */
public class HastaDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();

    public List<Hasta> read(int page, int pageSize) {
        List<Hasta> hastaList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem
        try {
            // TODO code application logic here

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from hasta order by idhasta asc limit " + start + "," + pageSize);
            while (rs.next()) {
                //System.out.println(rs.getString("doktor_name"));
                Hasta tmp = new Hasta(rs.getLong("idhasta"), rs.getString("hasta_name"), rs.getString("hasta_surname"), rs.getString("address"), rs.getString("tcno"));
                hastaList.add(tmp);

//getHastaPersonel e film gönderilir
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return hastaList;
    }

    public Hasta find(Long id) {
        Hasta b = null;
        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from hasta where idhasta=" + id);
            rs.next();
            b = new Hasta();
            b.setIdhasta(rs.getLong("idhasta"));
            b.setHasta_name(rs.getString("hasta_name"));
            b.setHasta_surname(rs.getString("hasta_surname"));
            b.setAddress(rs.getString("address"));
            b.setTcno(rs.getString("tcno"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return b;

    }

    public int count() {
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idhasta) as hasta_count from hasta");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("hasta_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void insert(Hasta hasta) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into hasta (hasta_name,hasta_surname,address,tcno) values (?,?,?,?)");
            pst.setString(1, hasta.getHasta_name());
            pst.setString(2, hasta.getHasta_surname());
            pst.setString(3, hasta.getAddress());
            pst.setString(4, hasta.getTcno());

            pst.executeUpdate();

            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into hasta (hasta_name,number) values ('" + hasta.getHasta_name() + "','" + hasta.getNumber() + "'",Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(HastaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Hasta hasta) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from personel_hasta where idhasta=?");
            pst.setLong(1, hasta.getIdhasta());
            pst.executeUpdate();

            //Statement st = connection.createStatement();
            //st.executeUpdate("delete from hasta where idhasta=" + hasta.getIdhasta());
        } catch (SQLException ex) {
            Logger.getLogger(HastaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Hasta hasta) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update hasta set hasta_name=?,hasta_surname=?,address=?,tcno=? where idhasta=?");
            pst.setString(1, hasta.getHasta_name());
            pst.setString(2, hasta.getHasta_surname());
            pst.setString(3, hasta.getAddress());
            pst.setString(4, hasta.getTcno());

            pst.setLong(5, hasta.getIdhasta());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into hasta (hasta_name,number) values ('" + hasta.getHasta_name() + "','" + hasta.getNumber() + "'",Statement.RETURN_GENERATED_KEYS);

            


        } catch (SQLException ex) {
            Logger.getLogger(HastaDao.class.getName()).log(Level.SEVERE, null, ex);
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
