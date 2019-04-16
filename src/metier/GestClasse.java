/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestClasse {
    public void supprimerClasse(int id){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("delete from classe where id_classe=?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
    
    public List<Classe> listerClasse() {
        Connection con = ConnexionBD.getCon();
        List<Classe> list = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select * from classe");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Classe c=new Classe(res.getInt("id_classe"), res.getString("formation"),res.getString("departement") , res.getString("UFR"), res.getString("niveau"));
               list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    public Classe rechercheClasse(int id){
        Connection con = ConnexionBD.getCon();
        Classe c = new Classe();
        try {
            PreparedStatement st = con.prepareStatement("select * from classe where id_classe=?");
            st.setInt(1, id);
            ResultSet res= st.executeQuery();
            while(res.next()){
            c.setDepartement(res.getString("departement"));
            c.setFormation(res.getString("formation"));
            c.setId_classe(res.getInt("id_classe"));
            c.setNiveau(res.getString("niveau"));
            c.setUfr(res.getString("UFR"));
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return c;
    }
    public void ajouterClasse(Classe c){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("insert into classe (formation,departement,UFR,niveau)values (?,?,?,?)");
            st.setString(1, c.getFormation());
            st.setString(2, c.getDepartement());
            st.setString(3, c.getUfr());
            st.setString(4, c.getNiveau());
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
     public void modifierClasse(Classe c){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update classe set formation=?,departement=?,UFR=?,niveau=? where id_classe=?");
            st.setString(1, c.getFormation());
            st.setString(2, c.getDepartement());
            st.setString(3, c.getUfr());
            st.setString(4, c.getNiveau());
            st.setInt(5, c.getId_classe());
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
    

      

    
}
