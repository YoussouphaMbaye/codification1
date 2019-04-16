package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class GestEtudie {

    public List<Etudie> listerEtudie() {
        List<Etudie> listeEtudie = new ArrayList<Etudie>();
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from etudie";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etudie etu = new Etudie();
                etu.setIdEtudiant(rs.getInt("id_etudiant"));
                etu.setIdClasse(rs.getInt("id_classe"));
                etu.setAnnee(rs.getInt("annee"));
                listeEtudie.add(etu);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerEtudie ");
        }
        return listeEtudie;
    }
    public int ajouterEtudie(Etudie etu){
         Connection con = ConnexionBD.getCon();
         int a=0;
        try {
            String rsql="insert into Etudie values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1,etu.getIdEtudiant());
            ps.setInt(2,etu.getIdClasse());
            ps.setInt(3,etu.getAnnee());
            a=ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur lancement requete insert etudie");
        }
        return a; 
    }
    public void supprimerEtudie(int idEtu,int annee){
      Connection con = ConnexionBD.getCon(); 
        try {
            String rsql="delete from etudie where id_etudiant=? and annee=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, idEtu);
            ps.setInt(2, annee);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete delete etudie");
        }
    }
     public List<Etudie> listerEtudiantParClasse(int idclasse,int annee) {
        
        List<Etudie> listeEtudie = new ArrayList<Etudie>();
        Connection con = ConnexionBD.getCon();
        String rsql = "select * from etudie where id_classe=? and annee=?";
        try {
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setInt(1, idclasse);
            ps.setInt(2, annee);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etudie etudie=new Etudie(rs.getInt("id_Etudiant"), rs.getInt("id_classe"), rs.getInt("annee"));
                listeEtudie.add(etudie);
            }
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete listerEtudie ");
        }
        return listeEtudie;
    }
}
