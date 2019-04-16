package metier;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


 
public class ConnexionBD {
 public static Connection con;   
 static{
     try {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost/codification","root","");
     } catch (ClassNotFoundException ex) {
         System.out.println("erreur de chargement de driver");
     }catch(SQLException e){
         System.out.println("erreure de connexion");
     }    
 }
    public static Connection getCon() {
        return con;
    }
 
}
