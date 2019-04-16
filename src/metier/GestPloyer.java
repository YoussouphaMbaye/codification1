
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GestPloyer {
  
    public List<Ployer> listerPloyer() {
        List<Ployer> lister = new ArrayList<>();
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from Ployer";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ployer pl = new Ployer();
                pl.setIdPavion(rs.getInt("id_pavion"));
                pl.setIdEtudiant(rs.getInt("id_etudiant"));
                pl.setIdChambre(rs.getInt("num_chambre"));
                pl.setAnnee(rs.getInt("annee"));
                pl.setMoi(rs.getInt("moi"));
                pl.setPrixLoyer(rs.getInt("prix_loyer"));
                pl.setDatePloyer(rs.getDate("date_ployer"));
                lister.add(pl);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPloyer");
        }
        return lister;
    }
    public int ajouterPloyer(Ployer pl){
        int a=0;
         Connection con = ConnexionBD.getCon();
        try {
            String rsql="insert into Ployer values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,pl.getIdPavion());
            ps.setInt(2,pl.getIdChambre());
            ps.setInt(3,pl.getIdEtudiant());
            ps.setInt(4,pl.getAnnee());
            ps.setInt(5,pl.getMoi());
            ps.setInt(6,pl.getPrixLoyer());
            ps.setDate(7, pl.getDatePloyer());
            ps.setString(8, pl.getMatriculeCompt());
           a= ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erreur lancement requete insert Ployer");
            e.printStackTrace();
        }
      return a;   
    }
    public void modifierPloyer(Ployer pl){//ne modifie que le prix du loyer
        Connection con = ConnexionBD.getCon();
        try {  
            String rsql="update ployer set prix_loyer=?,matriculeEmp=? where moi=? and annee=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,pl.getPrixLoyer());
            ps.setString(2, pl.getMatriculeCompt());
            ps.setInt(3, pl.getMoi());
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete modier Prix loyer");
        }
    }
    public void supprimerPloyer(Ployer pl){//on recupere et on modifier les attribut avec set
      Connection con = ConnexionBD.getCon(); 
        try {
            String rsql="delete from ployer where id_etudiant=? and id_pavion=? and num_chambre=? and annee=? and moi=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, pl.getIdEtudiant());
            ps.setInt(2, pl.getIdPavion());
            ps.setInt(3, pl.getIdChambre());
            ps.setInt(4, pl.getAnnee());
            ps.setInt(5, pl.getMoi());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete delete ployer");
        }
    } 
    public List<Ployer> recherchcherPloyerParMoi(int annee,int moi) {
        List<Ployer> lister = new ArrayList<>();
        Ployer pl=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from Ployer where annee=? and moi=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, annee);
            ps.setInt(2, moi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 pl = new Ployer();
                pl.setIdPavion(rs.getInt("id_pavion"));
                pl.setIdEtudiant(rs.getInt("id_etudiant"));
                pl.setIdChambre(rs.getInt("num_chambre"));
                pl.setAnnee(rs.getInt("annee"));
                pl.setMoi(rs.getInt("moi"));
                pl.setPrixLoyer(rs.getInt("prix_loyer"));
                pl.setDatePloyer(rs.getDate("date_ployer"));
                lister.add(pl);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPloyer");
        }
        if(pl==null) throw new RuntimeException("aucun payement pour le moi: "+moi+"de l'annee:"+annee);
        return lister;
    }
    public List<Ployer> recherchcherPloyerParEtudiant(int idEtudiant,int annee) {
        List<Ployer> lister = new ArrayList<>();
        Ployer pl=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from Ployer where id_etudiant=? and annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, idEtudiant);
            ps.setInt(2, annee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pl = new Ployer();
                pl.setIdPavion(rs.getInt("id_pavion"));
                pl.setIdEtudiant(rs.getInt("id_etudiant"));
                pl.setIdChambre(rs.getInt("num_chambre"));
                pl.setAnnee(rs.getInt("annee"));
                pl.setMoi(rs.getInt("moi"));
                pl.setPrixLoyer(rs.getInt("prix_loyer"));
                pl.setDatePloyer(rs.getDate("date_ployer"));
                lister.add(pl);
            }
             if(pl==null) throw new RuntimeException("Aucun payement pour cet étudiant pour l'annee:"+annee);
       
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPloyer");
        }
        return lister;
    }
    public List<Ployer> recherchcherPloyerParMatriculeCompt(String matricule,int annee) {
        List<Ployer> lister = new ArrayList<>();
        Ployer pl=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from Ployer where matriculeCompt=? and annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setString(1, matricule);
            ps.setInt(2, annee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pl = new Ployer();
                pl.setIdPavion(rs.getInt("id_pavion"));
                pl.setIdEtudiant(rs.getInt("id_etudiant"));
                pl.setIdChambre(rs.getInt("num_chambre"));
                pl.setAnnee(rs.getInt("annee"));
                pl.setMoi(rs.getInt("moi"));
                pl.setPrixLoyer(rs.getInt("prix_loyer"));
                pl.setDatePloyer(rs.getDate("date_ployer"));
                lister.add(pl);
            }
             if(pl==null) throw new RuntimeException("Aucun payement de loyer n'a était enregistré par cet comptable pour l'annee:"+annee);
       
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPloyer");
        }
        return lister;
    }
    public int leMoiAPayer(int annee ,int idEtudiant) {
        int i=0;
        Connection con = ConnexionBD.getCon();
        String rsql = "select moi from Ployer where moi>=all (select moi from Ployer where annee=? and id_etudiant=?) and annee=? and id_etudiant=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, annee);
            ps.setInt(2, idEtudiant);
             ps.setInt(3, annee);
             ps.setInt(4, idEtudiant);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                i=rs.getInt("moi");
            }
            else throw new RuntimeException("aucun loyer n'a etait payer par cet etudiant pour annee"+annee);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }
     public List<Ployer> payerAvant(int idEtudiant,int annee,int leMoi) {
        List<Ployer> lister = new ArrayList<>();
        Ployer pl=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from Ployer where moi > leMoi and id_etudiant=? and annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, idEtudiant);
            ps.setInt(2, annee);
            ps.setInt(3, leMoi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pl = new Ployer();
                pl.setIdPavion(rs.getInt("id_pavion"));
                pl.setIdEtudiant(rs.getInt("id_etudiant"));
                pl.setIdChambre(rs.getInt("num_chambre"));
                pl.setAnnee(rs.getInt("annee"));
                pl.setMoi(rs.getInt("moi"));
                pl.setPrixLoyer(rs.getInt("prix_loyer"));
                pl.setDatePloyer(rs.getDate("date_ployer"));
                lister.add(pl);
            }
             if(pl==null) throw new RuntimeException("aucun payement pour ce etudiant pour l'annee:"+annee);
       
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerPloyer");
        }
        return lister;
    }
     public int moiPayer(int idEtudiant,int annee) {
      int i=0;
        Connection con = ConnexionBD.getCon();
        String rsql = "select max(moi) from Ployer where id_etudiant=? and annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, idEtudiant);
            ps.setInt(2, annee);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                i=rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("erreure count ployer");
            ex.printStackTrace();
        }
        return i;
    }
}
