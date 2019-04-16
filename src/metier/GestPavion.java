
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestPavion {
 public List<Pavion> listerPavion() {
        List<Pavion> lister = new ArrayList<Pavion>();
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from Pavion";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pavion pv = new Pavion();
                pv.setIdPavion(rs.getInt("id_pavion"));
                pv.setNomPavion(rs.getString("nom_pavion"));
                lister.add(pv);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPavion");
            ex.printStackTrace();
        }
        return lister;
    }
    public int ajouterPavion(Pavion pv){
        int a=0;
         Connection con = ConnexionBD.getCon();
        try {
            String rsql="insert into Pavion (nom_pavion) values(?)";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setString(1,pv.getNomPavion());
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur lancement requete insert Pavion");
        }
         return a;
    }
    public void modifierPavion(Pavion pv){
        Connection con = ConnexionBD.getCon();
        try {  
            String rsql="update pavion set nom_pavion=? where id_pavion=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setString(1,pv.getNomPavion());
            ps.setInt(2,pv.getIdPavion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete modier Pvion");
        }
    }
    public int supprimerPavion(int idpavion){
      Connection con = ConnexionBD.getCon(); 
      int a=0;
        try {
            String rsql="delete from pavion where id_pavion=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, idpavion);
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete delete pavion");
        }
        return a;
    }   
      public Pavion recherchePavion(String nomPavion) {
        Connection con = ConnexionBD.getCon();
        Pavion pv=null;
        String rsql = "select * from Pavion where nom_pavion=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setString(1,nomPavion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 pv = new Pavion();
                pv.setIdPavion(rs.getInt("id_pavion"));
                pv.setNomPavion(rs.getString("nom_pavion"));
            }
            if(pv==null)throw new RuntimeException("pavion introuvable");
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPavion");
        }
        return pv;
    }
}
