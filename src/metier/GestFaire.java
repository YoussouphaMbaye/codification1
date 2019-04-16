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

/**
 *
 * @author Side Sarr
 */
public class GestFaire {
    
    public List<Faire> listerFaire(){
        List<Faire> liste=new ArrayList<Faire>();
        Connection con=ConnexionBD.getCon();
        try{
            PreparedStatement pr=con.prepareStatement("select * from faire");
            ResultSet res=pr.executeQuery();
            Faire f=new Faire();
            while(res.next()){
                f.setIdDepartement(res.getInt("id_departement"));
                f.setIdEtudiant(res.getInt("Id_etudiant"));
                f.setIdFormation(res.getInt("id_formation"));
                f.setIdUfr(res.getInt("id_ufr"));
                f.setAnnee(res.getInt("annee"));
                liste.add(f);
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return liste;
    }
    public List<Faire> rechercheFaire(int idForm,int annee){
        List<Faire> liste=new ArrayList<Faire>();
        Faire f=null;
        Connection con=ConnexionBD.getCon();
        try {
            PreparedStatement pr=con.prepareStatement("select * from faire where id_Formation=? and annee=?");
            pr.setInt(1,idForm);
            pr.setInt(2,annee);
            ResultSet res= pr.executeQuery();
            while(res.next()){
                f=new Faire();
                f.setIdDepartement(res.getInt("id_departement"));
                f.setIdEtudiant(res.getInt("Id_etudiant"));
                f.setIdFormation(res.getInt("id_formation"));
                f.setIdUfr(res.getInt("id_ufr"));
                f.setAnnee(res.getInt("annee"));
                liste.add(f);
            }
           if(f==null) throw new RuntimeException("Auncun etudiant n'est inscrite pour cette formation a cette annee "+annee);
        } catch (SQLException ex) {
            Logger.getLogger(GestDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    public Faire rechercheFaireParEtudiant(int idetudiant,int annee){
        Faire f=null;
        Connection con=ConnexionBD.getCon();
        try {
            PreparedStatement pr=con.prepareStatement("select * from faire where Id_etudiant=? and annee=?");
            pr.setInt(1,idetudiant);
            pr.setInt(2,annee);
            ResultSet res= pr.executeQuery();
            if(res.next()){
                f=new Faire();
                f.setIdDepartement(res.getInt("id_departement"));
                f.setIdEtudiant(res.getInt("Id_etudiant"));
                f.setIdFormation(res.getInt("id_formation"));
                f.setIdUfr(res.getInt("id_ufr"));
                f.setAnnee(res.getInt("annee"));
                
            }
           if(f==null) throw new RuntimeException("Auncun etudiant n'est inscrite pour cette formation a cette annee");
        } catch (SQLException ex) {
            Logger.getLogger(GestDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    public int supprimerFaire(Faire f){
        Departement d=new Departement();
        Connection con=ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement pr=con.prepareStatement("delete from faire where Id_etudiant=? and id_formation=? and annee=?");
            pr.setInt(1, f.getIdEtudiant());
            pr.setInt(2, f.getIdFormation());
            pr.setInt(3, f.getAnnee());
            a= pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    public int ajouterFaire(Faire f){
        Connection con = ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement pr = con.prepareStatement("insert into faire (id_ufr,id_departement,Id_etudiant,id_formation,annee)values (?,?,?,?,?)");
            pr.setInt(1, f.getIdUfr());
            pr.setInt(2, f.getIdDepartement());
            pr.setInt(3, f.getIdEtudiant());
            pr.setInt(4, f.getIdFormation());
            pr.setInt(5, f.getAnnee());
            a=pr.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }
    public int modifierFaire(Faire d){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement pr = con.prepareStatement("update faire set id_ufr=?,id_departement=?,id_formation,Id_etudiant=? ,annee=where id_classe=?");
            pr.setInt(1, d.getIdUfr());
            pr.setInt(2, d.getIdDepartement());
            pr.setInt(3, d.getIdFormation());
            pr.executeUpdate();
        }catch(SQLException e){
           e.printStackTrace(); 
        }
        return 1;
    }
    
}
