package metier;
public class Employer {
     private String nom;
    private String prenom;
    private String pseudo;
    private String mot_de_passe;
    private boolean disponibilite;
    private String profile;
    private String matricule;
    private String email;
    private int telephone;

    public Employer(String matricule,String nom, String prenom, String pseudo, String mot_de_passe, boolean disponibilite, String profile,String email,int telephone) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
        this.disponibilite = disponibilite;
        this.profile = profile;
        this.email=email;
        this.telephone=telephone;
    }
    public Employer() {
       
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
    
   
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    
    
}
