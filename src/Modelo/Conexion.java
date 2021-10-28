package Modelo;

import java.sql.*;
import com.mysql.jdbc.jdbc2.optional.*;
/**
 *
 * @author John
 */
public class Conexion {
    
    Connection conexion;
    ResultSet resul;
    Statement stm;
    
    String url="jdbc:mysql://localhost/wichitroco_2";
    String user="root";
    String pass= "";

  public Conexion () throws Exception{

    MysqlDataSource datos = new MysqlDataSource();

    datos.setServerName("localhost");
    datos.setUser("root");
    datos.setPassword("");
    datos.setDatabaseName("wichitroco_2");

    conexion = datos.getConnection();

  }

  public Connection Conectar(){
    try{
      
        Class.forName("com.mysql.jdbc.Driver");
        conexion=(Connection) DriverManager.getConnection(url, user, pass);
  
    }catch(Exception e){
  
    }
    return conexion;
  
  }
  
  
  public void dml(String instrucciones) throws SQLException{

     stm=conexion.createStatement();
     stm.execute(instrucciones);

  }

  public ResultSet GetConsultar (String sql) throws SQLException{

      stm=conexion.createStatement();
      resul=stm.executeQuery(sql);
      return resul;

  }

  public void Setcerrar() throws SQLException{

      conexion.close();

  }  
    
}
