
package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Chambre;


public class ChambreForm {
   private int idChambre; 
   private int idPavion;
   private Chambre chambre=new Chambre();
   private List<Chambre>listeChambre=new ArrayList<Chambre>();

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public int getIdPavion() {
        return idPavion;
    }

    public void setIdPavion(int idPavion) {
        this.idPavion = idPavion;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public List<Chambre> getListeChambre() {
        return listeChambre;
    }

    public void setListeChambre(List<Chambre> listeChambre) {
        this.listeChambre = listeChambre;
    }
   
}
