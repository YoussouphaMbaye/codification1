
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GestOccupe {
    
    public List<Occupe> listerOccupe() {
        List<Occupe> lister = new ArrayList<Occupe>();
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from occupe";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Occupe oc = new Occupe();
                oc.setIdPavion(rs.getInt("id_pavion"));
                oc.setIdEtudiant(rs.getInt("Id_etudiant"));
                oc.setIdChambre(rs.getInt("num_chambre"));
                oc.setAnnee(rs.getInt("annee"));
                oc.setDatePloyer(rs.getDate("date_occupation"));
                lister.add(oc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
    public List<Occupe> occupeDuPavion(int idPavion,int annee) {
        List<Occupe> lister = new ArrayList<Occupe>();
        Connection con = ConnexionBD.getCon();
         Occupe oc=null;
        String rsql = "select * from occupe where id_pavion=? and annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,idPavion);
             ps.setInt(2,annee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                oc = new Occupe();
                oc.setIdPavion(rs.getInt("id_pavion"));
                oc.setIdEtudiant(rs.getInt("Id_etudiant"));
                oc.setIdChambre(rs.getInt("num_chambre"));
                oc.setAnnee(rs.getInt("annee"));
                oc.setDatePloyer(rs.getDate("date_occupation"));
                lister.add(oc);
            }
            if(oc==null){
                throw new RuntimeException("Aucune codification n'est enregistrer dans cette pavillon pour l'année "+annee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
    public List<Occupe> occupeDuChambre(int idPavion,int annee,int id_chambre) {
        List<Occupe> lister = new ArrayList<Occupe>();
        Connection con = ConnexionBD.getCon();
         Occupe oc=null;
        String rsql = "select * from occupe where id_pavion=? and annee=? and num_chambre=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,idPavion);
            ps.setInt(2,annee);
            ps.setInt(3,id_chambre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                oc = new Occupe();
                oc.setIdPavion(rs.getInt("id_pavion"));
                oc.setIdEtudiant(rs.getInt("id_etudiant"));
                oc.setIdChambre(rs.getInt("num_chambre"));
                oc.setAnnee(rs.getInt("annee"));
                oc.setDatePloyer(rs.getDate("date_occupation"));
                lister.add(oc);
            }
            if(oc==null){
                throw new RuntimeException("chambre non occupe ou inexistante pour l'année "+annee);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerOccupe");
        }
        return lister;
    }
    public int ajouterOccupe(Occupe oc){
        int a=0;
         Connection con = ConnexionBD.getCon();
        try {
            String rsql="insert into Occupe values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,oc.getIdPavion());
            ps.setInt(2,oc.getIdChambre());
            ps.setInt(3,oc.getIdEtudiant());
            ps.setInt(4,oc.getAnnee());
            ps.setDate(5, oc.getDateOccupe());
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur lancement requete insert Occupe");
            ex.printStackTrace();
        }
      return a;   
    }
    
    public void supprimerOccupe(Occupe oc){//on recupere et on odifier les attribut avec set
      Connection con = ConnexionBD.getCon(); 
        try {
            String rsql="delete from occupe where id_etudiant=? and id_pavion=? and num_chambre=? and annee=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, oc.getIdEtudiant());
            ps.setInt(2, oc.getIdPavion());
            ps.setInt(3, oc.getIdChambre());
            ps.setInt(4, oc.getAnnee());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete delete etudie");
        }
    }
}
