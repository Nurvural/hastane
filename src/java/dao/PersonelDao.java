
package dao;

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
public class PersonelDao {

    DBConnection connector = new DBConnection();
    Connection connection = connector.connect();

    public List<Personel> read(int page, int pageSize) {
        List<Personel> personelList = new ArrayList<>();
        int start = (page - 1) * pageSize;//sayfalama islemi icin gerekli olan matematiksel islem

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from personel order by idpersonel asc limit "+start+","+pageSize);
            ResultSet rs = pst.executeQuery();

            //Statement st = this.getConnection().createStatement();
            //ResultSet rs = st.executeQuery("select * from personel");
            while (rs.next()) {
                //System.out.println(rs.getString("doktor_name"));
                Personel tmp = new Personel(rs.getLong("idpersonel"), rs.getString("personel_name"), rs.getString("personel_surname"), rs.getString("personel_turu"), rs.getInt("personel_salary"), rs.getString("personel_tcno"));
                personelList.add(tmp);

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
        return personelList;
    }

    public int count() {//sayfalama işlemi için gerekli olan method
        int count = 0;

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select count(idpersonel) as personel_count from personel");
            // Statement st = this.getConnection().createStatement();
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("personel_count");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return count;
    }

    public void insert(Personel personel) {

        try {
            PreparedStatement pst = this.getConnection().prepareStatement("insert into personel (personel_name,personel_surname,personel_turu,personel_salary,personel_tcno) values (?,?,?,?,?)");
            pst.setString(1, personel.getPersonel_name());
            pst.setString(2, personel.getPersonel_surname());
            pst.setString(3, personel.getPersonel_turu());
            pst.setInt(4, personel.getPersonel_salary());
            pst.setString(5, personel.getPersonel_tcno());
            pst.executeUpdate();

            //Statement st = this.getConnection().createStatement();
            //st.executeUpdate("insert into personel (personel_name,personel_surname,personel_turu,personel_salary,personel_tcno) values ('" + personel.getPersonel_name() + "','" + personel.getPersonel_surname() + "','" + personel.getPersonel_turu() + "'," + personel.getPersonel_salary() + "," + personel.getPersonel_tcno() + ")");
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remove(Personel personel) {

        try {

            Statement st = this.getConnection().createStatement();
            st.executeUpdate("delete from personel where idpersonel=" + personel.getIdpersonel());
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Personel personel) {

        try {

            Statement st = this.getConnection().createStatement();
            st.executeUpdate("update personel set personel_name='" + personel.getPersonel_name() + "',personel_surname='" + personel.getPersonel_surname() + "',personel_turu='" + personel.getPersonel_turu() + "',personel_salary=" + personel.getPersonel_salary() + ",personel_tcno='" + personel.getPersonel_tcno() + "' where idpersonel=" + personel.getIdpersonel());
        } catch (SQLException ex) {
            Logger.getLogger(PersonelDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Personel> getBolumPersonel(Long idbolum) {//filmin id si buraya gönderilir.
        List<Personel> bolumPersonel = new ArrayList<>();

        try {
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from personel_bolum where idbolum=" + idbolum);
            while (rs.next()) {
                bolumPersonel.add(this.find(rs.getLong("idbolum")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bolumPersonel;

    }

    public Personel find(Long id) {
        Personel p = null;

        try {

            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from personel where idpersonel=" + id);

            rs.next();

            p = new Personel();
            p.setIdpersonel(rs.getLong("idpersonel"));
            p.setPersonel_name(rs.getString("personel_name"));
            p.setPersonel_surname(rs.getString("personel_surname"));
            p.setPersonel_turu(rs.getString("personel_turu"));
            p.setPersonel_salary(rs.getInt("personel_salary"));
            p.setPersonel_tcno(rs.getString("personel_tcno"));

        } catch (SQLException ex) {
            Logger.getLogger(PersonelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
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
