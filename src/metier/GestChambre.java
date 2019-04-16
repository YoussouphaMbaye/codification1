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


public class GestChambre {
    public int ajouterChambre(Chambre c){
        int a=0;
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("insert into chambre(id_pavion,num_chambre,reserver)values (?,?,?)");
            st.setInt(1, c.getId_pavillon());
            st.setInt(2, c.getId_chambre());
            st.setBoolean(3, c.isReserver());
             a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }
    public List<Chambre> listerChambre(){
        List<Chambre> list=new ArrayList<Chambre>();
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("select * from chambre");
            ResultSet res=st.executeQuery();
            while(res.next()){
                Chambre c=new Chambre(res.getInt("id_pavion"), res.getInt("num_chambre"),res.getBoolean("reserver"));
                list.add(c);
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
     public List<Chambre> chambreDuPavion(int idPavion){
        List<Chambre> list=new ArrayList<Chambre>();
        Connection con = ConnexionBD.getCon();
         Chambre c=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from chambre where id_pavion=?");
            st.setInt(1,idPavion);
            ResultSet res=st.executeQuery();
            while(res.next()){
                c=new Chambre(res.getInt("id_pavion"), res.getInt("num_chambre"),res.getBoolean("reserver"));
                list.add(c);
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
         if(c==null){
                throw new RuntimeException("ce pavion existe pas ou n'a auncun chambre");
            }
        return list;
    }
      public List<Chambre> chambreNomOccupe(int idPavion){
        List<Chambre> list=new ArrayList<Chambre>();
        Connection con = ConnexionBD.getCon();
         Chambre c=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from chambre where id_pavion=? and num_chambre not in (select num_chambre from occupe where id_pavion=? )");
            st.setInt(1,idPavion);
            st.setInt(2,idPavion);
            ResultSet res=st.executeQuery();
            while(res.next()){
                c=new Chambre(res.getInt("id_pavion"), res.getInt("num_chambre"),res.getBoolean("reserver"));
                list.add(c);
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
         if(c==null){
                throw new RuntimeException("ce pavion existe pas ou n'a auncun chambre");
            }
        return list;
    }
     public void reserver(Boolean re,int idchambre,int pavion){
        Connection con = ConnexionBD.getCon();
        try {  
            String rsql="update chambre set reserver=? where num_chambre=? and id_pavion=?";
            PreparedStatement ps = con.prepareStatement(rsql);
            ps.setBoolean(1,re);
            ps.setInt(2, idchambre);
            ps.setInt(3, pavion);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erreur de lancement de requete resevation etudie");
        }
    }
     public List<Chambre> chambreAChoisir(int idPavion,int annee){
        List<Chambre> list=new ArrayList<Chambre>();
        Connection con = ConnexionBD.getCon();
         Chambre c=null;
         String rsql="select * from chambre c where id_pavion=? and reserver=? and ((select count(num_chambre) from occupe where num_chambre=c.num_chambre and id_pavion=? and annee=?) <2 and (select count(num_chambre) from choisir where num_chambre=c.num_chambre and id_pavion=?)<2) or (c.num_chambre not in(select num_chambre from occupe where id_pavion=? and annee=?) and c.num_chambre not in(select num_chambre from choisir where id_pavion=?)) and reserver=? and id_pavion=?";
        try {
            PreparedStatement st = con.prepareStatement(rsql);
            st.setInt(1, idPavion);
            st.setBoolean(2,false);
            st.setInt(3, idPavion);
            st.setInt(4, annee);
            st.setInt(5, idPavion);
            st.setInt(6, idPavion);
            st.setInt(7, annee);
            st.setInt(8, idPavion);
            st.setBoolean(9,false);
            st.setInt(10, idPavion);
            ResultSet res=st.executeQuery();
            while(res.next()){
                c=new Chambre(res.getInt("id_pavion"), res.getInt("num_chambre"),res.getBoolean("reserver"));
                list.add(c);
            }
            if(c==null){
                throw new RuntimeException("chamdre déja choisie, réserver ou occupé");
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
         
        return list;
    }
        public Chambre rechercheChambre(Chambre ch){
        Connection con = ConnexionBD.getCon();
        Chambre c=null;
        try {
            String rsql="select * from chambre where id_pavion=? and num_chambre=?";
            PreparedStatement st = con.prepareStatement(rsql);
            st.setInt(1,ch.getId_pavillon());
            st.setInt(2,ch.getId_chambre());
            ResultSet res=st.executeQuery();
            if(res.next()){
                c=new Chambre(res.getInt("id_pavion"), res.getInt("num_chambre"),res.getBoolean("reserver"));
            }
            if(c==null){
                throw new RuntimeException("Cette chambre n'existe pas!");
            }
        }
        catch (SQLException ex) {
            System.out.println("erreur de lancement de requete resevation etudie");
        }
        return c;
    } 
public int nombreDeChambreDuPavion(int idPav){
        Connection con = ConnexionBD.getCon();
        Chambre c=null;
        int a=0;
        try {
            String rsql="select count(num_chambre) as nb from chambre where id_pavion=?";
            PreparedStatement st = con.prepareStatement(rsql);
            st.setInt(1,idPav);
            ResultSet res=st.executeQuery();
            if(res.next()){
                a=res.getInt("nb");
            }
            
        }
        catch (SQLException ex) {
            System.out.println("erreur de lancement de requete nombre de chambre du pavillon");
        }
        return a;
    } 

public int supprimerLesChambresDuPavion(int idPav){
        Connection con = ConnexionBD.getCon();
        Chambre c=null;
        int a=0;
        try {
            String rsql="delete from chambre where id_pavion=?";
            PreparedStatement st = con.prepareStatement(rsql);
            st.setInt(1,idPav);
            a=st.executeUpdate();
        }
        catch (SQLException ex) {
           ex.printStackTrace();
        }
        return a;
    } 

    }
    

