
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Chambre;
import metier.Pavion;


public class PavionForm {
    private String nomPavion;
    private int idPavion;
    private Pavion pav=new Pavion();
    private List<Pavion>listePav=new ArrayList<Pavion>();
    private Chambre chambre=new Chambre();
    private String error;

    public String getError() {
        return error;
    }

    public int getIdPavion() {
        return idPavion;
    }

    public void setIdPavion(int idPavion) {
        this.idPavion = idPavion;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }
    public String getNomPavion() {
        return nomPavion;
    }

    public void setNomPavion(String nomPavion) {
        this.nomPavion = nomPavion;
    }

    public Pavion getPav() {
        return pav;
    }

    public void setPav(Pavion pav) {
        this.pav = pav;
    }

    public List<Pavion> getListePav() {
        return listePav;
    }

    public void setListePav(List<Pavion> listePav) {
        this.listePav = listePav;
    }
    
}
