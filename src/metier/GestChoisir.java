/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestChoisir {
    public List<Choisir> listerChoix(){
         Connection con = ConnexionBD.getCon();
         List<Choisir> list=new ArrayList<Choisir>();
        try {
            PreparedStatement st = con.prepareStatement("select * from choisir");
            ResultSet res=st.executeQuery();
            while(res.next()){
                Choisir c=new Choisir(res.getInt("id_pavion"), res.getInt("num_chambre"), res.getInt("id_etudiant"),res.getDate("dateChoix"));
                list.add(c);
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
     public Choisir rechercheChoixParEtudiant(int idEtudiant){
         Connection con = ConnexionBD.getCon();
          Choisir c=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from choisir where id_etudiant=?");
            st.setInt(1, idEtudiant);
            ResultSet res=st.executeQuery();
            if(res.next()){
                 c=new Choisir(res.getInt("id_pavion"), res.getInt("num_chambre"), res.getInt("id_etudiant"));
                
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return c;
    }
    public int effectuerChoix(Choisir c){
        int a=0;
        Connection con = ConnexionBD.getCon();
         try {
            PreparedStatement st = con.prepareStatement("insert into choisir (id_pavion,num_chambre,id_etudiant,dateChoix) value (?,?,?,?)");
            st.setInt(1, c.getId_pavillon());
            st.setInt(2, c.getId_chambre());
            st.setInt(3, c.getId_etudiant());
            st.setDate(4, c.getDate());
           a=st.executeUpdate();
         }catch(SQLException e){
             Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);

         }
         return a;
    }
    public int supprimerChoix(Choisir c){
        
        Connection con = ConnexionBD.getCon();
        int a=0;
         try {
            PreparedStatement st = con.prepareStatement("delete from choisir where id_pavion=? and num_chambre=? and id_etudiant=?");
            st.setInt(1, c.getId_pavillon());
            st.setInt(2, c.getId_chambre());
            st.setInt(3, c.getId_etudiant());
            a=st.executeUpdate();
         }catch(SQLException e){
             Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);

         }
         return a;
    }
    public int toutSupprimerChoix(){
        
        Connection con = ConnexionBD.getCon();
        int a=0;
         try {
            PreparedStatement st = con.prepareStatement("delete from choisir");
            a=st.executeUpdate();
         }catch(SQLException e){
             Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);

         }
         return a;
    }
    }
    

