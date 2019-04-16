
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Ployer;


public class PloyerForm {
   private int idPavion; 
   private int idChambre;
   private int idEtudiant ;
   private int annee;
   private int moi;
   private Ployer ployer=new Ployer();
   List<Ployer>listePloyer=new ArrayList<Ployer>();

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

    public Ployer getPloyer() {
        return ployer;
    }

    public void setPloyer(Ployer ployer) {
        this.ployer= ployer;
    }

    public List<Ployer> getListePloyer() {
        return listePloyer;
    }

    public void setListePloyer(List<Ployer> listePloyer) {
        this.listePloyer = listePloyer;
    }
   
}
