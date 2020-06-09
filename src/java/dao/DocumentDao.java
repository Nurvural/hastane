
package dao;

import entity.Document;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao {
    
     private DBConnection connector;
    private Connection connection;
    
    public List<Document> findAll(){
        List<Document> documentList= new ArrayList<>();
        
        try{
            PreparedStatement pst=this.getConnection().prepareStatement("select * from document");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Document d= new Document();
                d.setId(rs.getInt("id"));
                d.setFilePath(rs.getString("path"));
                d.setFileName(rs.getString("name"));
                d.setFileType(rs.getString("type"));
                documentList.add(d);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return documentList;
    }
    
     public void insert (Document d) 
    {
        String query = "insert into document (name , path , type) values (?,?,?) " ;
                
        try { 
            PreparedStatement pst =   this.getConnection().prepareStatement(query) ;
            pst.setString(1 , d.getFileName());
            pst.setString(2 , d.getFilePath());
            pst.setString(3 , d.getFileType());
            
            pst.executeUpdate() ;
      
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
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
