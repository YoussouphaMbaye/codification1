package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestEmployer {
    public int modifierEmployer(Employer emp){
        Connection con = ConnexionBD.getCon();
        int i=0;
        try {
            PreparedStatement st = con.prepareStatement("update employer set nom=?,prenom=?,profile=?,email=?,telephone=? where matricule=?");
            st.setString(1, emp.getNom());
            st.setString(2, emp.getPrenom());
            st.setString(3, emp.getProfile());
            st.setString(4, emp.getEmail());
            st.setInt(5, emp.getTelephone());
            st.setString(6, emp.getMatricule());
             i=st.executeUpdate();
        }catch(SQLException e){
         e.printStackTrace();
        }
        return i;
    }
    public int supprimerEmployer(String matricule){
        Connection con = ConnexionBD.getCon();
        int i=0;
        try {
            PreparedStatement st = con.prepareStatement("delete from employer where matricule=?");
            st.setString(1, matricule);
             i=st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return i;
    }
    public int mettreDansListe(List<String> liste){
        List<Integer>liste2=new ArrayList<Integer>();
        for(String chaine: liste){
            liste2.add(Integer.parseInt(chaine.substring(1, chaine.length())));
        }
        return Collections.max(liste2);
    }
    public int bloquer(String matricule){
        Connection con = ConnexionBD.getCon();
        int i=0;
        try {
            PreparedStatement st = con.prepareStatement("update employer set disponiblite=? where matricule=?");
            st.setBoolean(1, false);
            st.setString(2, matricule);
             i=st.executeUpdate();
        }catch(SQLException e){
            
        }
        return i;
    }
     public int debloquer(String matricule){
        Connection con = ConnexionBD.getCon();
        int i=0;
        try {
            PreparedStatement st = con.prepareStatement("update employer set disponiblite=? where matricule=?");
            st.setBoolean(1, true);
            st.setString(2, matricule);
             i=st.executeUpdate();
        }catch(SQLException e){
            
        }
        return i;
    }
    public List<Employer> listerEmployer() {
        Connection con = ConnexionBD.getCon();
        List<Employer> list = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select * from employer");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Employer em = new Employer( res.getString("matricule"),res.getString("nom"), res.getString("prenom"), res.getString("pseudo"), res.getString("mot_de_passe"),res.getBoolean("disponiblite"), res.getString("profile"),res.getString("email"),res.getInt("telephone"));
                list.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    public List<String> listeMatricule() {
        Connection con = ConnexionBD.getCon();
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select matricule from employer");
            ResultSet res = st.executeQuery();
            while (res.next()) {
               list.add(res.getString("matricule"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Employer> listerComptable() {
        Connection con = ConnexionBD.getCon();
        List<Employer> list = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select * from employer where profile=?");
            st.setString(1, "Comptable");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Employer em = new Employer( res.getString("matricule"),res.getString("nom"), res.getString("prenom"), res.getString("pseudo"), res.getString("mot_de_passe"),res.getBoolean("disponiblite"), res.getString("profile"),res.getString("email"),res.getInt("telephone"));
                list.add(em);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public Employer rechercheEmployer(String mat) {
        Connection con = ConnexionBD.getCon();
        Employer em=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from employer where matricule=?");
            st.setString(1, mat);
            ResultSet res=st.executeQuery();
            if(res.next()) {
                em=new Employer( res.getString("matricule"),res.getString("nom"), res.getString("prenom"), res.getString("pseudo"), res.getString("mot_de_passe"), res.getBoolean("disponiblite"), res.getString("profile"),res.getString("email"),res.getInt("telephone"));
            }
            if(em==null)throw new RuntimeException("ancun employer de matricule"+mat);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }
    public int ajouterEmployer(Employer em){
        Connection con = ConnexionBD.getCon();
         int a=0;
        try {
            PreparedStatement st = con.prepareStatement("insert into employer (nom,prenom,pseudo,mot_de_passe,disponiblite,matricule,profile,email,telephone) values (?,?,?,?,?,?,?,?,?)");
             st.setString(1, em.getNom());
             st.setString(2, em.getPrenom());
             st.setString(3, em.getPseudo());
             st.setString(4, em.getMot_de_passe());
             st.setBoolean(5, true);
             st.setString(6, em.getMatricule());
             st.setString(7, em.getProfile());
             st.setString(8, em.getEmail());
             st.setInt(9, em.getTelephone());
             a=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    public int modifierMotDePasse(String matricule,String motPasse){
        Connection con = ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement st = con.prepareStatement("update employer set mot_de_passe=? where matricule=?");
            st.setString(1,motPasse);
            st.setString(2, matricule);
            a=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    public Employer rechercheEmployerParCompte(String login,String password) {
        Connection con = ConnexionBD.getCon();
        List<Etudiant> list = new ArrayList<>();
        Employer em=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from employer where pseudo=? and mot_de_passe=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            if (res.next()) {
                em = new Employer( res.getString("matricule"),res.getString("nom"), res.getString("prenom"), res.getString("pseudo"), res.getString("mot_de_passe"),res.getBoolean("disponiblite"), res.getString("profile"),res.getString("email"),res.getInt("telephone"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return em;
    }
    
}
