
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestFormation {
  public int ajouterFormation(Formation f){
        int a=0;
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("insert into Formation(id_ufr,id_departement,id_formation,nom_Formation,niveau)values (?,?,?,?,?)");
            st.setInt(1, f.getIdUfr());
            st.setInt(2, f.getIdDepartement());
            st.setInt(3, f.getIdFormation());
            st.setString(4, f.getNomFormation());
            st.setString(5, f.getNiveau());
             a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }  
  public int modifierFormation(Formation f){
        int a=0;
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update Formation set nom_Formation=?,niveau=?  where id_ufr=? and id_departement=? and id_formation=?");
            st.setInt(3, f.getIdUfr());
            st.setInt(4, f.getIdDepartement());
            st.setInt(5, f.getIdFormation());
            st.setString(1, f.getNomFormation());
            st.setString(2, f.getNiveau());
             a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }   
  public int supprimerFormation(Formation f){
        int a=0;
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("delete from formation where id_ufr=? and id_departement=? and id_formation=?");
            st.setInt(1, f.getIdUfr());
            st.setInt(2, f.getIdDepartement());
            st.setInt(3, f.getIdFormation());
            a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return a;
    }   
  public List<Formation> listerFormation(){
        List<Formation> list=new ArrayList<Formation>();
        Connection con = ConnexionBD.getCon();
         Formation f=null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from formation");
            ResultSet res=ps.executeQuery();
            while(res.next()){
                f=new Formation();
                f.setIdUfr(res.getInt("id_ufr"));
                f.setIdDepartement(res.getInt("id_departement"));
                f.setIdFormation(res.getInt("id_formation"));
                f.setNomFormation(res.getString("nom_formation"));
                f.setNiveau(res.getString("niveau"));
                list.add(f);
            }
            if(f==null){
                throw new RuntimeException("aucune formation n'a etait enregistre");
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
  public Formation rechercheFormation(String nomFormation,String niveau){
        Connection con = ConnexionBD.getCon();
         Formation f=null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from formation where nom_Formation=? and niveau=?");
            ps.setString(1, nomFormation);
            ps.setString(2, niveau);
            ResultSet res=ps.executeQuery();
            if(res.next()){
                f=new Formation();
                f.setIdUfr(res.getInt("id_ufr"));
                f.setIdDepartement(res.getInt("id_departement"));
                f.setIdFormation(res.getInt("id_formation"));
                f.setNomFormation(res.getString("nom_formation"));
                f.setNiveau(res.getString("niveau"));
            }
            if(f==null){
                throw new RuntimeException("aucune formation ayant pour nom "+nomFormation+"et pour niveau "+niveau);
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return f;
    }
  public int rechIdFormation(String nomFormation,String niveau){
        Connection con = ConnexionBD.getCon();
         int a=0;
        try {
            PreparedStatement ps = con.prepareStatement("select id_formation from formation where nom_Formation=? and niveau=?");
            ps.setString(1, nomFormation);
            ps.setString(2, niveau);
            ResultSet res=ps.executeQuery();
            if(res.next()){
                a=res.getInt("id_formation");
            }
            else
                throw new RuntimeException("Auncun n'étudiant pour la formation "+nomFormation+" de Niveau "+niveau+" n'a été trouvé" );
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return a;
    }
}
