
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GestPCaution {
  public int ajouterPCaution(PayerCaution pc){
      int a=0;
      Connection con = ConnexionBD.getCon();
        try {
            String rsql="insert into payerCaution values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,pc.getIdPavion());
            ps.setInt(2,pc.getIdChambre());
            ps.setInt(3,pc.getIdEtudiant());
            ps.setInt(4,pc.getAnnee());
            ps.setDate(5, pc.getDateCaution());
            ps.setInt(6, pc.getMontant());
            ps.setString(7, pc.getMatriculeCompt());
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return a;
  } 
  public int supprimerPCaution(PayerCaution pc){
      int a=0;
      Connection con = ConnexionBD.getCon();
        try {
            String rsql="delete from payerCaution where id_pavion=? and num_chambre=? and Id_etudiant=? and annee=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,pc.getIdPavion());
            ps.setInt(2,pc.getIdChambre());
            ps.setInt(3,pc.getIdEtudiant());
            ps.setInt(4, pc.getAnnee());
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return a;
  }
  public int modifierPCaution(PayerCaution pc){
      int a=0;
      Connection con = ConnexionBD.getCon();
        try {
            String rsql="update  payerCaution set montant_caution=?,matriculeCompt=? where id_pavion=? and num_chambre=? and Id_etudiant=? and annee=? ";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,pc.getMontant());
            ps.setString(2, pc.getMatriculeCompt());
            ps.setInt(3,pc.getIdPavion());
            ps.setInt(4,pc.getIdChambre());
            ps.setInt(5,pc.getIdEtudiant());
            ps.setInt(6, pc.getAnnee());
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      return a;
  }
  public List<PayerCaution> listerPCaution() {
        List<PayerCaution> lister = new ArrayList<PayerCaution>();
        PayerCaution pc=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from payerCaution";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pc=new PayerCaution();
                pc.setIdPavion(rs.getInt("id_pavion"));
                pc.setIdEtudiant(rs.getInt("Id_etudiant"));
                pc.setIdChambre(rs.getInt("num_chambre"));
                pc.setAnnee(rs.getInt("annee"));
                pc.setDateCaution(rs.getDate("date_Caution"));
                pc.setMontant(rs.getInt("montant_caution"));
                lister.add(pc);
            }
            if(pc==null)throw new RuntimeException("aucun payement de caution n'a etait enregistre");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
  public List<PayerCaution> listerPCautionDuCompt(String matricule) {
        List<PayerCaution> lister = new ArrayList<PayerCaution>();
        PayerCaution pc=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from payerCaution where matriculeCompt=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setString(1, matricule);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pc=new PayerCaution();
                pc.setIdPavion(rs.getInt("id_pavion"));
                pc.setIdEtudiant(rs.getInt("Id_etudiant"));
                pc.setIdChambre(rs.getInt("num_chambre"));
                pc.setAnnee(rs.getInt("annee"));
                pc.setDateCaution(rs.getDate("date_Caution"));
                pc.setMontant(rs.getInt("montant_caution"));
                lister.add(pc);
            }
            if(pc==null)throw new RuntimeException("aucun payement de caution n'a etait enregistre");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
   public List<PayerCaution> PCautionNonValider(int annee) {
        List<PayerCaution> lister = new ArrayList<PayerCaution>();
        PayerCaution pc=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from payerCaution where Id_etudiant not in (select Id_etudiant from occupe where annee=?)";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,annee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pc=new PayerCaution();
                pc.setIdPavion(rs.getInt("id_pavion"));
                pc.setIdEtudiant(rs.getInt("Id_etudiant"));
                pc.setIdChambre(rs.getInt("num_chambre"));
                pc.setAnnee(rs.getInt("annee"));
                pc.setDateCaution(rs.getDate("date_Caution"));
                pc.setMontant(rs.getInt("montant_caution"));
                lister.add(pc);
            }
            if(pc==null)throw new RuntimeException("aucun paiement de caution non validé a été trouvé");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
  public List<PayerCaution> rechercheParAnnee(int annee) {
        List<PayerCaution> lister = new ArrayList<PayerCaution>();
        PayerCaution pc=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from payerCaution where annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, annee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pc=new PayerCaution();
                pc.setIdPavion(rs.getInt("id_pavion"));
                pc.setIdEtudiant(rs.getInt("Id_etudiant"));
                pc.setIdChambre(rs.getInt("num_chambre"));
                pc.setAnnee(rs.getInt("annee"));
                pc.setDateCaution(rs.getDate("date_Caution"));
                pc.setMontant(rs.getInt("montant_caution"));
                lister.add(pc);
            }
            if(pc==null)throw new RuntimeException("aucun payement de caution n'a etait enregistre pour l'année "+annee);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
  public List<PayerCaution> rechercheParAnneeMatri(int annee,String matricule) {
        List<PayerCaution> lister = new ArrayList<PayerCaution>();
        PayerCaution pc=null;
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from payerCaution where annee=? and matriculeCompt=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, annee);
            ps.setString(2, matricule);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pc=new PayerCaution();
                pc.setIdPavion(rs.getInt("id_pavion"));
                pc.setIdEtudiant(rs.getInt("Id_etudiant"));
                pc.setIdChambre(rs.getInt("num_chambre"));
                pc.setAnnee(rs.getInt("annee"));
                pc.setDateCaution(rs.getDate("date_Caution"));
                pc.setMontant(rs.getInt("montant_caution"));
                lister.add(pc);
            }
            if(pc==null)throw new RuntimeException("aucun payement de caution n'a etait enregistre par ce comptable pour l'année "+annee);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lister;
    }
  public int sommeTCaution(int annee) {
      int som=0;
        Connection con = ConnexionBD.getCon();
        String rsql = "select sum(montant_caution) as montant from payerCaution where annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, annee);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               som=rs.getInt("montant");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return som;
    }
}

