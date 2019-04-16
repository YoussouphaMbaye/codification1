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
public class GestDepartement {
    
    public List<Departement> listerDepartement(){
        List<Departement> liste=new ArrayList<Departement>();
        Connection con=ConnexionBD.getCon();
        try{
            PreparedStatement pr=con.prepareStatement("select * from departement");
            ResultSet res=pr.executeQuery();
            Departement d=null;
            while(res.next()){
                d=new Departement();
                d.setIdDepartement(res.getInt("id_departement"));
                d.setIdUfr(res.getInt("id_ufr"));
                d.setNomDepartement(res.getString("nom_departement"));
                liste.add(d);
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return liste;
    }
    public Departement rechercheDept(int id_dept,int id_ufr){
        Departement d=null;
        Connection con=ConnexionBD.getCon();
        try {
            PreparedStatement pr=con.prepareStatement("select * from departement where id_departement=? and id_ufr=?");
            pr.setInt(1, id_dept);
            pr.setInt(2, id_ufr);
            ResultSet res= pr.executeQuery();
            if(res.next()){
                d=new Departement();
                d.setIdDepartement(res.getInt("id_departement"));
                d.setIdUfr(res.getInt("id_ufr"));
                d.setNomDepartement(res.getString("nom_departement"));
            }
            if(d==null){
                throw new RuntimeException("Departement Introuvable");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public int supprimer(int id_dept,int idUfr){
        Departement d=new Departement();
        Connection con=ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement pr=con.prepareStatement("delete from departement where id_departement=? and id_ufr=?");
            pr.setInt(1, id_dept);
             pr.setInt(2, idUfr);
            a= pr.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GestDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    public int ajouterDepartement(Departement d){
        Connection con = ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement pr = con.prepareStatement("insert into departement (id_ufr,id_departement,nom_departement)values (?,?,?)");
            pr.setInt(1, d.getIdUfr());
            pr.setInt(2, d.getIdDepartement());
            pr.setString(3, d.getNomDepartement());
            a=pr.executeUpdate();
        }catch(SQLException e){
         e.printStackTrace();
        }
        return a;
    }
    public int modifierDepartement(Departement d){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement pr = con.prepareStatement("update departement set nom_departement=? where id_ufr=? and id_departement=?");
            pr.setString(1, d.getNomDepartement());
            pr.setInt(2, d.getIdUfr());
            pr.setInt(3, d.getIdDepartement());
            pr.executeUpdate();
        }catch(SQLException e){
            
        }
        return 1;
    }
    
}
