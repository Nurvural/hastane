
package dao;

import entity.Bolum;
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
public class BolumDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();
    private PersonelDao personelDao;

    public List<Bolum> read(int page, int pageSize) {
        List<Bolum> bolumList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem
        try {
          

            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from bolum order by idbolum asc limit " + start + "," + pageSize);
            while (rs.next()) {
                //System.out.println(rs.getString("doktor_name"));
                Bolum tmp = new Bolum(rs.getLong("idbolum"), rs.getString("bolum_name"), rs.getInt("number"));
                bolumList.add(tmp);

                tmp.setBolumPersonel(this.getPersonelDao().getBolumPersonel(tmp.getIdbolum()));//bu bolumle baglantılı personelin listesinin alınması lazım
//getBolumPersonel e film gönderilir
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return bolumList;
    }

    public Bolum find(Long id) {
        Bolum b = null;
        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from bolum where idbolum=" + id);
            rs.next();
            b = new Bolum();
            b.setIdbolum(rs.getLong("idbolum"));
            b.setBolum_name(rs.getString("bolum_name"));
            b.setNumber(rs.getInt("number"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return b;

    }

    public int count() {//sayfalama işlemi için gerekli olan methoddur
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idbolum) as bolum_count from bolum");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("bolum_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void insert(Bolum bolum) {//ekleme işlemi için sorguların yapılacağı methodur
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into bolum (bolum_name,number) values (?,?)",Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, bolum.getBolum_name());
            pst.setInt(2, bolum.getNumber());
            pst.executeUpdate();

            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into bolum (bolum_name,number) values ('" + bolum.getBolum_name() + "','" + bolum.getNumber() + "'",Statement.RETURN_GENERATED_KEYS);
            Long idbolum = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                idbolum = gk.getLong(1);
            }

            for (Personel p : bolum.getBolumPersonel()) {
                pst = this.getConnection().prepareStatement("insert into personel_bolum (idpersonel,idbolum) values (?,?)");
                pst.setLong(1, p.getIdpersonel());
                pst.setLong(2, idbolum);
                // Statement st2 = this.getConnection().createStatement();
                //st2.executeUpdate("insert into personel_bolum (idpersonel,idbolum) values (" + l + "," + idbolum + ")");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BolumDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remove(Bolum bolum) {
//silme işlemi için sorgularn yapılacağı methoddur
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("delete from personel_bolum where iddoktor=?");
            pst.setLong(1, bolum.getIdbolum());
            pst.executeUpdate();

            //Statement st = connection.createStatement();
            //st.executeUpdate("delete from bolum where idbolum=" + bolum.getIdbolum());
        } catch (SQLException ex) {
            Logger.getLogger(BolumDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Bolum bolum) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("update bolum set bolum_name=?,number=? where idbolum=?");
            pst.setString(1, bolum.getBolum_name());
            pst.setInt(2, bolum.getNumber());
            pst.setLong(3, bolum.getIdbolum());
            pst.executeUpdate();
            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into bolum (bolum_name,number) values ('" + bolum.getBolum_name() + "','" + bolum.getNumber() + "'",Statement.RETURN_GENERATED_KEYS);

            Long idbolum = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                idbolum = gk.getLong(1);
            }

            pst = this.getConnection().prepareStatement("delete from personel_bolum idbolum=?");
            pst.setLong(1, bolum.getIdbolum());
            pst.executeUpdate();

            for (Personel p : bolum.getBolumPersonel()) {
                pst = this.getConnection().prepareStatement("insert into personel_bolum (idpersonel,idbolum) values (?,?)");
                pst.setLong(1, p.getIdpersonel());
                pst.setLong(2, idbolum);
                // Statement st2 = this.getConnection().createStatement();
                //st2.executeUpdate("insert into personel_bolum (idpersonel,idbolum) values (" + l + "," + idbolum + ")");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BolumDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public PersonelDao getPersonelDao() {
        if (this.personelDao == null) {
            this.personelDao = new PersonelDao();
        }
        return personelDao;
    }

}
