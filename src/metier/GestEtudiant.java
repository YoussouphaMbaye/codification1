
package metier;

import java.io.IOException;
import java.io.InputStream;
import static java.lang.String.*;
import static java.lang.String.format;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import static org.apache.taglibs.standard.functions.Functions.substring;



public class GestEtudiant {
    @SuppressWarnings("rawtypes")
	public int insertionEtudiant(InputStream input) {
                 int a=0;
		// Déclaration des variables
		ArrayList<String> values = new ArrayList<String>();
		Connection connection;
		Statement statement;

		// Récupération des données depuis le fichier excel
		try {
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();

			while (rows.hasNext()) {

				values.clear();

				HSSFRow row = (HSSFRow) rows.next();

				Iterator cells = row.cellIterator();

				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();
					
					if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType())
						values.add(Integer.toString((int) cell.getNumericCellValue()));
					else if (HSSFCell.CELL_TYPE_STRING == cell.getCellType())
						values.add(cell.getStringCellValue());
				}

				// Insertion en BDD
                                Connection con = ConnexionBD.getCon();
                               
                    

				try {
                                    java.util.Date date = row.getCell(11).getDateCellValue();  
                                                  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String Date = sdf.format(date);
                                   SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");  // United States style of format.
                String dateNai=Date;
                System.out.println("la dateF"+dateNai);
                char lettre = '-';
                String motD="";
                for (int i = 0; i < dateNai.length(); i++) {
                    if (dateNai.charAt(i)==lettre) {
                     motD=motD+"/";   
                    }
                    else{
                    motD=motD+dateNai.charAt(i); 
                    }
                }
                String motDate=substring(motD,motD.length()-2,motD.length())+"/";
                motDate=motDate+substring(motD,motD.length()-5,motD.length()-2);
                motDate=motDate+substring(motD,0,motD.length()-6);
                System.out.println(motDate);
                 int bien=0;
                try {
                    java.util.Date myDate = format.parse(motDate);
                    java.sql.Date sqlDate = new java.sql.Date( myDate.getTime());
                    
                     System.out.println("voici la date"+sqlDate);
					statement = con.createStatement();
					String sql = String
							.format("INSERT INTO etudiant(nom,prenom,sexe,numEtudiant,lieu,email,ufr,departement,formation,niveau,telephone,disponiblite,pseudo,mot_de_passe,codifier,dateDeNaissance) VALUES ('%s', '%s','%s',%s,'%s','%s','%s','%s','%s',%s,%s,"+true+",'%s','UADB2017',"+true+",'"+sqlDate+"')",
									values.get(0), values.get(1),
									values.get(2),values.get(3),values.get(4),values.get(5),values.get(6),values.get(7),values.get(8),values.get(9),values.get(10),values.get(5));

					int count = statement.executeUpdate(sql);

					// Message de succès
					if (count > 0) {
						a=1;
					}

                   
                    bien=1;
                } catch (ParseException ex) {
                    
                    ex.printStackTrace();
                }
                            
				} catch (Exception e) {
                                        try{
                                            statement = con.createStatement();
                                            String sql2="update etudiant set niveau="+values.get(9)+",codifier="+true+" where numEtudiant="+values.get(3)+" and Id_etudiant in (select Id_etudiant from occupe)";
                                            int i=statement.executeUpdate(sql2);
                                            if(i>0){
                                             System.out.println("update reuussi");   
                                            }else{
                                                System.out.println("nom reuussi");  
                                            }
                                            e.printStackTrace();
                                        }catch(Exception ex){
                                            System.out.println("------");
                                            ex.printStackTrace();
                                        }
					
				}

			}
		} catch (IOException e) {
			throw new RuntimeException("Type de fichier non valide");
		}
             return a;
	}  

    public void bloquerCompteEtu(int id){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set disponiblite=? where Id_etudiant=?");
            st.setBoolean(1, false);
            st.setInt(2, id);
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
     public void codifierEtu(int id){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set codifier=? where Id_etudiant=?");
            st.setBoolean(1, true);
            st.setInt(2, id);
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
     public void deCodifierEtu(int id){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set codifier=? where Id_etudiant=?");
            st.setBoolean(1, false);
            st.setInt(2, id);
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
      public void toutDeCodifierEtu(){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set codifier=?");
            st.setBoolean(1, false);
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
    
    public void deBloquerCompteEtu(int id){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set disponiblite=? where Id_etudiant=?");
            st.setBoolean(1, true);
            st.setInt(2, id);
            st.executeUpdate();
        }catch(SQLException e){
            
        }
    }
public int deBloquerLesCodifierDeLanneeDernier(int annee){
    int a=0;
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set disponiblite=? where Id_etudiant in (select id_etudiant from occupe where annee=?) and codifier=?");
            st.setBoolean(1, true);
            st.setInt(2, annee);
            st.setBoolean(3, true);
            a=st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }
    public int supprimerEtudiant(Etudiant et){
        Connection con = ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement st = con.prepareStatement("delete from etudiant where Id_etudiant=?");
            st.setInt(1, et.getIdEtudiant());
            a=st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return a;
    }
    public List<Etudiant> listerEtudiant() {
        Connection con = ConnexionBD.getCon();
        List<Etudiant> list = new ArrayList<>();
        try {
            PreparedStatement st = con.prepareStatement("select * from Etudiant");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                Etudiant etu = new Etudiant(res.getInt("Id_etudiant"), res.getString("nom"), res.getString("prenom"),res.getString("pseudo"),res.getString("sexe"),res.getString("mot_de_passe"),res.getBoolean("disponiblite"),res.getBoolean("codifier"),res.getInt("numEtudiant"),res.getDate("dateDeNaissance"),res.getString("lieu"),res.getString("email"),res.getInt("niveau"),res.getString("formation"),res.getString("ufr"),res.getString("departement"));
                list.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public Etudiant rechercheEtudiant(int num) {
        Connection con = ConnexionBD.getCon();
        List<Etudiant> list = new ArrayList<>();
        Etudiant etu=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from etudiant where numEtudiant=?");
            st.setInt(1,num);
            ResultSet res = st.executeQuery();
            if (res.next()) {
                etu =new Etudiant(res.getInt("Id_etudiant"), res.getString("nom"), res.getString("prenom"),res.getString("pseudo"),res.getString("sexe"),res.getString("mot_de_passe"),res.getBoolean("disponiblite"),res.getBoolean("codifier"),res.getInt("numEtudiant"),res.getDate("dateDeNaissance"),res.getString("lieu"),res.getString("email"),res.getInt("niveau"),res.getString("formation"),res.getString("ufr"),res.getString("departement"));
                list.add(etu);
            }else
                throw new RuntimeException("L'etudinat de numero "+num+" est intourvable");
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etu;
    }
    public Etudiant rechercheEtudiantParCompte(String login,String password) {
        Connection con = ConnexionBD.getCon();
        List<Etudiant> list = new ArrayList<>();
        Etudiant etu=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from etudiant where pseudo=? and mot_de_passe=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                etu = new Etudiant(res.getInt("Id_etudiant"), res.getString("nom"), res.getString("prenom"), res.getString("pseudo"), res.getString("mot_de_passe"), res.getBoolean("disponiblite"), res.getInt("numEtudiant"),res.getString("sexe"),res.getBoolean("codifier"),res.getDate("dateDeNaissance"),res.getString("lieu"));
                list.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etu;
    }
    public List listeDesCodifiers() {
        Connection con = ConnexionBD.getCon();
        List<Etudiant> list = new ArrayList<>();
        Etudiant etu=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from etudiant where codifier="+true+" order by formation,niveau");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                etu = new Etudiant(res.getInt("Id_etudiant"), res.getString("nom"), res.getString("prenom"),res.getString("pseudo"),res.getString("sexe"),res.getString("mot_de_passe"),res.getBoolean("disponiblite"),res.getBoolean("codifier"),res.getInt("numEtudiant"),res.getDate("dateDeNaissance"),res.getString("lieu"),res.getString("email"),res.getInt("niveau"),res.getString("formation"),res.getString("ufr"),res.getString("departement"));
                list.add(etu);
            }
            if(etu==null)throw new RuntimeException("Aucun etudiant codifier n'a été trouver");
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public List listeDesCodifiersParForm(String formation,int niveau) {
        
        Connection con = ConnexionBD.getCon();
        List<Etudiant> list = new ArrayList<>();
        Etudiant etu=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from etudiant where codifier="+true+" and niveau=? and formation=?");
            st.setInt(1, niveau);
            st.setString(2,formation);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                etu = new Etudiant(res.getInt("Id_etudiant"), res.getString("nom"), res.getString("prenom"),res.getString("pseudo"),res.getString("sexe"),res.getString("mot_de_passe"),res.getBoolean("disponiblite"),res.getBoolean("codifier"),res.getInt("numEtudiant"),res.getDate("dateDeNaissance"),res.getString("lieu"),res.getString("email"),res.getInt("niveau"),res.getString("formation"),res.getString("ufr"),res.getString("departement"));
                list.add(etu);
            }
            if(etu==null)throw new RuntimeException("Aucun étudiant codifier n'a été trouver pour la formation "+formation+" de niveau "+niveau);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<Etudiant> rechercheEtuCodifier() {
        List<Etudiant> liste = new ArrayList<>();
        Connection con = ConnexionBD.getCon();
        Etudiant etu=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from etudiant where codifier=?");
            st.setBoolean(1, true);
            ResultSet res=st.executeQuery();
            if(res.next()) {
                etu = new Etudiant(res.getInt("Id_etudiant"), res.getString("nom"), res.getString("prenom"),res.getString("pseudo"),res.getString("sexe"),res.getString("mot_de_passe"),res.getBoolean("disponiblite"),res.getBoolean("codifier"),res.getInt("numEtudiant"),res.getDate("dateDeNaissance"),res.getString("lieu"),res.getString("email"),res.getInt("niveau"),res.getString("formation"),res.getString("ufr"),res.getString("departement"));
                liste.add(etu);
            }else{
                throw new RuntimeException("aucun etudiant codifier n'est trouver dans la base");
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return liste;
    }
   
     public Etudiant rechercheParPseudo(String pseudo) {
        Connection con = ConnexionBD.getCon();
        Etudiant etu=null;
        try {
            PreparedStatement st = con.prepareStatement("select * from etudiant where pseudo=?");
            st.setString(1, pseudo);
            ResultSet res=st.executeQuery();
            if (res.next()) {
                etu = new Etudiant(res.getInt("Id_etudiant"),res.getString("nom"), res.getString("prenom"), res.getString("pseudo"), res.getString("mot_de_passe"), res.getBoolean("disponiblite"), res.getInt("numEtudiant"),res.getString("sexe"),res.getBoolean("codifier"),res.getDate("dateDeNaissance"),res.getString("lieu"));
            }
            else{
                throw new RuntimeException("Etudiant introuvable");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etu;
    }
    public int insererEtudiant(Etudiant et){
        Connection con = ConnexionBD.getCon();
         int a=0;
        try {
            PreparedStatement st = con.prepareStatement("insert into etudiant (nom,prenom,pseudo,mot_de_passe,disponiblite,sexe,numEtudiant,codifier,dateDeNaissance,lieu) values (?,?,?,?,?,?,?,?,?,?)");
             st.setString(1, et.getNom());
             st.setString(2, et.getPrenom());
             st.setString(3, et.getPseudo());
             st.setString(4, et.getMot_de_passe());
             st.setBoolean(5, et.isDisponibilite());
             st.setString(6, et.getSexe());
             st.setInt(7, et.getNumeroEtudiant());
             st.setBoolean(8, et.isCodifier());
             st.setDate(9, et.getDateNai());
             st.setString(10, et.getLieuNai());
             a=st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }
public void toutBloquer(){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set disponiblite=?");
            st.setBoolean(1, false);
             
             int a=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void deBloquerCodifier(){
        Connection con = ConnexionBD.getCon();
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set disponiblite=? where codifier=?");
            st.setBoolean(1, true);
            st.setBoolean(2, true);
             int a=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public int modifierEtudiant(Etudiant et){
        Connection con = ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set nom=?,prenom=?,sexe=?,numEtudiant=?,pseudo=?,lieu=?,dateDenaissance=?,ufr=?,departement=?,formation=?,niveau=? where id_etudiant=?");
            st.setString(1, et.getNom());
            st.setString(2, et.getPrenom());
            st.setString(3, et.getSexe());
            st.setInt(4, et.getNumeroEtudiant());
            st.setString(5, et.getPseudo());
            st.setString(6, et.getLieuNai());
            st.setDate(7, et.getDateNai());
            st.setString(8, et.getUfr());
            st.setString(9, et.getDepartement());
            st.setString(10, et.getFormation());
            st.setInt(11, et.getNiveau());
            st.setInt(12, et.getIdEtudiant());
            a=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
      public int modifierMotDePasse(int idEtu,String motPasse){
        Connection con = ConnexionBD.getCon();
        int a=0;
        try {
            PreparedStatement st = con.prepareStatement("update etudiant set mot_de_passe=? where id_etudiant=?");
            st.setString(1,motPasse);
            st.setInt(2, idEtu);
            a=st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }



}
