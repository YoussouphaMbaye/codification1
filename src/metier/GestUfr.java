
package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GestUfr {
  public int ajoutUfr(Ufr u){
      int a=0;
      Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("insert into ufr(nom_ufr)values (?)");
            st.setString(1, u.getNomUfr());
            a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
      return a;
  }  
   public int SuprimerUfr(int idUfr){
      int a=0;
      Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("delete from ufr where id_ufr=?");
            st.setInt(1, idUfr);
            a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
      return a;
  }  
  public int modifierUfr(String nomUfr,int idUfr){
      int a=0;
      Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update ufr set nom_ufr=? where id_ufr=?");
            st.setString(1,nomUfr);
            st.setInt(2, idUfr);
            a = st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
      return a;
  }  
   public List<Ufr> listerUfr(){
        List<Ufr> list=new ArrayList<Ufr>();
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("select * from ufr");
            ResultSet res=st.executeQuery();
            while(res.next()){
                Ufr u=new Ufr();
                u.setIdUfr(res.getInt("id_ufr"));
                u.setNomUfr(res.getString("nom_ufr"));
                list.add(u);
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
   public Ufr rechercheUfr(String nomUfr){
        Ufr u=null;
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement ps = con.prepareStatement("select * from ufr where nom_ufr=?");
            ps.setString(1, nomUfr);
            ResultSet res=ps.executeQuery();
            while(res.next()){
                u=new Ufr();
                u.setIdUfr(res.getInt("id_ufr"));
                u.setNomUfr(res.getString("nom_ufr"));
            }
        }catch(SQLException e){
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, e);
        }
        return u;
    }
}


 