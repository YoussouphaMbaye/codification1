
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Occupe;

/**
 *
 * @author youssou
 */
public class OccupeForm {
    private int idPavion;
    private int idChambre;
    private int idEtudiant;
    private int annee; 
    private Occupe occupe=new Occupe();
    private List<Occupe>listeOccupe=new ArrayList<Occupe>();

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

    public Occupe getOccupe() {
        return occupe;
    }

    public void setoccupe(Occupe occupe) {
        this.occupe = occupe;
    }

    public List<Occupe> getListeOccupe() {
        return listeOccupe;
    }

    public void setListeOccupe(List<Occupe> listeOccupe) {
        this.listeOccupe = listeOccupe;
    }
    
}
