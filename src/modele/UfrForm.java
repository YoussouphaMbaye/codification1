package modele;

import java.util.ArrayList;
import java.util.List;
import metier.Ufr;

public class UfrForm {
    private int idUfr;
    private String nomUfr;
    private Ufr ufr = new Ufr();
    private List<Ufr> liste = new ArrayList();

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

    public Ufr getUfr() {
        return ufr;
    }

    public void setUfr(Ufr ufr) {
        this.ufr = ufr;
    }

    public List<Ufr> getListe() {
        return liste;
    }

    public void setListe(List<Ufr> liste) {
        this.liste = liste;
    }

}
