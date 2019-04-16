
package metier;

import java.sql.Date;


public class PayerCaution {
  private int idPavion;
  private int idChambre;
  private int idEtudiant; 
  private int annee;
  private Date dateCaution;
  private int montant;
  private String matriculeCompt;

    public PayerCaution(int idPavion, int idChambre, int idEtudiant, int annee, int montant,String matriculeCompt) {
        this.idPavion = idPavion;
        this.idChambre = idChambre;
        this.idEtudiant = idEtudiant;
        this.annee = annee;
        java.util.Date today = new java.util.Date();
        this.dateCaution = new java.sql.Date(today.getTime());
        this.montant = montant;
        this.matriculeCompt=matriculeCompt;
    }
  public PayerCaution(){
   java.util.Date today = new java.util.Date();
   this.dateCaution = new java.sql.Date(today.getTime());   
  }

    public int getIdPavion() {
        return idPavion;
    }

    public String getMatriculeCompt() {
        return matriculeCompt;
    }

    public void setMatriculeCompt(String matriculeCompt) {
        this.matriculeCompt = matriculeCompt;
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

    public Date getDateCaution() {
        return dateCaution;
    }

    public void setDateCaution(Date dateCaution) {
        this.dateCaution = dateCaution;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
  
}
