
package metier;

import java.io.Serializable;


public class Ufr implements Serializable{
  int idUfr;
  String nomUfr;

    public Ufr(int idUfr, String nomUfr) {
        this.idUfr = idUfr;
        this.nomUfr = nomUfr;
    }

    public Ufr() {
       
    }

    public int getIdUfr() {
        return idUfr;
    }

    public void setIdUfr(int idUfr) {
        this.idUfr = idUfr;
    }

    public String getNomUfr() {
        return nomUfr;
    }

    public void setNomUfr(String nomUfr) {
        this.nomUfr = nomUfr;
    }
  
}
