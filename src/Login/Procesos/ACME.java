/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Login.Procesos;

import Login.datos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ACME {
    public static Boolean InsertUsuario(Usuario u1) throws SQLException, ClassNotFoundException {
        try {            
            Connection cnx=getCnx();
            String query = "INSERT INTO usuario(NomUsuario,PassUsuario) VALUES(?,?)";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, u1.getUsername());
            ps.setString(2, u1.getPass());
            ps.executeUpdate();

            return true;
            
        }catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
            return false;
        }
    }
    
 public static Boolean ActualizarScore1(int score,String u1) throws SQLException, ClassNotFoundException {
        try {            
            Connection cnx=getCnx();
            String query = "UPDATE usuario SET Score1=? WHERE NomUsuario=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2,u1);
            ps.executeUpdate();

            return true;
            
        }catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
            return false;
        }
    }
 
 public static Boolean ActualizarScore2(int score,String u1) throws SQLException, ClassNotFoundException {
        try {            
            Connection cnx=getCnx();
            String query = "UPDATE usuario SET Score2=? WHERE NomUsuario=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2,u1);
            ps.executeUpdate();

            return true;
            
        }catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
            return false;
        }
    }
 
  public static Boolean ActualizarScore3(int score,String u1) throws SQLException, ClassNotFoundException {
        try {            
            Connection cnx=getCnx();
            String query = "UPDATE usuario SET Score3=? WHERE NomUsuario=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2,u1);
            ps.executeUpdate();

            return true;
            
        }catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
            return false;
        }
    }
  
   public static Boolean ActualizarScore4(int score,String u1) throws SQLException, ClassNotFoundException {
        try {            
            Connection cnx=getCnx();
            String query = "UPDATE usuario SET Score=4? WHERE NomUsuario=?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2,u1);
            ps.executeUpdate();

            return true;
            
        }catch (SQLException ex) {
            System.out.println("Error:" + ex.getMessage());
            return false;
        }
    }
    
 public static Connection getCnx() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/ucadash";
            String driver = "com.mysql.jdbc.Driver";
            String user="root";
            String pass="root";
            Class.forName(driver);
            Connection cnx=DriverManager.getConnection(url, user, pass);
            return cnx;
        }catch(ClassNotFoundException ex){
            ex.getMessage();
            return null;
        }
    }

 public static boolean buscarUsuario(String user){
     try{
         Connection cnx=getCnx();
         boolean flag=true;
         String query="SELECT NomUsuario FROM usuario WHERE NomUsuario=?";
         PreparedStatement ps=cnx.prepareStatement(query);
         ps.setString(1, user);
         ResultSet rs=ps.executeQuery();
         flag=rs.next()?true:false;
         return flag;
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
         return false;
     }
 }
 
 
 
 public static boolean isLoginOk(String user, String pass){
     try{
         Connection cnx=getCnx();
         boolean flag=false;
         String query="SELECT PassUsuario FROM usuario WHERE NomUsuario=?";
         PreparedStatement ps=cnx.prepareStatement(query);
         ps.setString(1, user);
         ResultSet rs=ps.executeQuery();
         if(rs.next()){ 
             flag=rs.getString("PassUsuario").equals(pass)?true:false;
         }
         
         return flag;
     }catch(SQLException ex){
         System.out.println(ex.getMessage());
         return false;
     }
 }
 
 
 public static Vector getUsuarios() throws ClassNotFoundException,SQLException{
      Vector row;
      Vector reg = new Vector();

        Connection cnx=getCnx();
        Statement s = cnx.createStatement();
        String query = "SELECT * FROM usuario";

        ResultSet rs = s.executeQuery(query);

        while (rs.next()) {
            row = new Vector();
            row.add(rs.getString("NomUsuario"));
            row.add(rs.getString("score"));
            reg.add(row);

        }
        return reg;
 }

 
 public static int verificarScore1(String u1) throws SQLException{
    int score;
     Connection cnx=getCnx();
    String query="SELECT Score1 FROM usuario WHERE NomUsuario=?";
    PreparedStatement ps=cnx.prepareStatement(query);
    ps.setString(1, u1);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        score = rs.getInt("Score1");
        return score;
    }else
        return 0;
 }
 
 public static int verificarScore2(String u1) throws SQLException{
    int score;
     Connection cnx=getCnx();
    String query="SELECT Score2 FROM usuario WHERE NomUsuario=?";
    PreparedStatement ps=cnx.prepareStatement(query);
    ps.setString(1, u1);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        score = rs.getInt("Score2");
        return score;
    }else
        return 0;
 }
 
 public static int verificarScore3(String u1) throws SQLException{
    int score;
     Connection cnx=getCnx();
    String query="SELECT Score3 FROM usuario WHERE NomUsuario=?";
    PreparedStatement ps=cnx.prepareStatement(query);
    ps.setString(1, u1);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        score = rs.getInt("Score3");
        return score;
    }else
        return 0;
 }
 
 public static int verificarScore4(String u1) throws SQLException{
    int score;
     Connection cnx=getCnx();
    String query="SELECT Score4 FROM usuario WHERE NomUsuario=?";
    PreparedStatement ps=cnx.prepareStatement(query);
    ps.setString(1, u1);
    ResultSet rs = ps.executeQuery();
    if(rs.next()){
        score = rs.getInt("Score4");
        return score;
    }else
        return 0;
 }
 
}
