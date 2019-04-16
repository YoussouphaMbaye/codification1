
package metier;

import java.io.Serializable;


public class Pavion implements Serializable{
 private int idPavion;
 private String nomPavion;
public Pavion() {
    }
    public Pavion(int idPavion, String nomPavion) {
        this.idPavion = idPavion;
        this.nomPavion = nomPavion;
    }
     public Pavion(String nomPavion) {
        this.nomPavion = nomPavion;
    }

    public int getIdPavion() {
        return idPavion;
    }

    public void setIdPavion(int idPavion) {
        this.idPavion = idPavion;
    }

    public String getNomPavion() {
        return nomPavion;
    }

    public void setNomPavion(String nomPavion) {
        this.nomPavion = nomPavion;
    }
 
}
