
package metier;

public class Formation {
 int idUfr;
 int idDepartement;
 int idFormation;
 String nomFormation;
 String niveau;

    public Formation(int idUfr, int idDepartement, int idFormation, String nomFormation, String niveau) {
        this.idUfr = idUfr;
        this.idDepartement = idDepartement;
        this.idFormation = idFormation;
        this.nomFormation = nomFormation;
        this.niveau = niveau;
    }

    public Formation() {
        
    }

    public int getIdUfr() {
        return idUfr;
    }

    public void setIdUfr(int idUfr) {
        this.idUfr = idUfr;
    }

    public int getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }

    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
 
}
