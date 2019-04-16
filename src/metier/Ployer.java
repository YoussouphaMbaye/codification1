
package metier;

import java.io.Serializable;
import java.sql.Date;



public class Ployer implements Serializable{
   private int idPavion; 
   private int idChambre;
   private int idEtudiant ;
   private int annee;
   private int moi;
   private int prixLoyer;
   private Date datePloyer;
   private String matriculeCompt;
  
    public Ployer(int idPavion, int idChambre, int idEtudiant, int annee, int moi, int prixLoyer,String matriculeCompt) {
        this.idPavion = idPavion;
        this.idChambre = idChambre;
        this.idEtudiant = idEtudiant;
        this.annee = annee;
        this.moi = moi;
        this.prixLoyer = prixLoyer;
        this.matriculeCompt=matriculeCompt;
        java.util.Date today = new java.util.Date();
        this.datePloyer = new java.sql.Date(today.getTime());
    }
    public Ployer() {
        java.util.Date today = new java.util.Date();
        this.datePloyer = new java.sql.Date(today.getTime());
    }
public Date getDatePloyer() {
        return datePloyer;
    }

    public String getMatriculeCompt() {
        return matriculeCompt;
    }

    public void setMatriculeCompt(String matriculeCompt) {
        this.matriculeCompt = matriculeCompt;
    }

    public void setDatePloyer(Date datePloyer) {
        this.datePloyer = datePloyer;
    }
    public int getIdPavion() {
        return idPavion;
    }

    public void setIdPavion(int idPavion) {
        this.idPavion = idPavion;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMoi() {
        return moi;
    }

    public void setMoi(int moi) {
        this.moi = moi;
    }

    public int getPrixLoyer() {
        return prixLoyer;
    }

    public void setPrixLoyer(int prixLoyer) {
        this.prixLoyer = prixLoyer;
    }
   
}
