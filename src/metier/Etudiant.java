/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Side Sarr
 */
public class Etudiant implements Serializable{
    private int idEtudiant;
    private String nom;
    private String prenom;
    private String pseudo;
    private String sexe;
    private String mot_de_passe;
    private boolean disponibilite;
    private boolean codifier;
    private int numeroEtudiant;
    private Date dateNai;
    private String lieuNai;
    private String email;
    private int niveau;
    private String ufr;
    private String departement;
    private String formation;
    

    public Etudiant(String nom, String prenom, String pseudo, String mot_de_passe, boolean disponibilite, int numeroEtudiant,String sexe,boolean codifier,String ladate,String lieuNai) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
        this.disponibilite = disponibilite;
        this.numeroEtudiant = numeroEtudiant;
        this.codifier=codifier;
        this.sexe=sexe;
        java.util.Date today = new java.util.Date(ladate);
        this.dateNai = new java.sql.Date(today.getTime());
        this.lieuNai=lieuNai;
    }
    public Etudiant(int idEtudiant,String nom, String prenom, String pseudo, String mot_de_passe, boolean disponibilite, int numeroEtudiant,String sexe,boolean codifier,Date dateNai,String lieuNai) {
        this.idEtudiant=idEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
        this.disponibilite = disponibilite;
        this.numeroEtudiant = numeroEtudiant;
        this.codifier=codifier;
        this.sexe=sexe;
        this.lieuNai=lieuNai;
        this.dateNai=dateNai;
    }

    public Etudiant(int idEtudiant, String nom, String prenom, String pseudo, String sexe, String mot_de_passe, boolean disponibilite, boolean codifier, int numeroEtudiant, Date dateNai, String lieuNai, String email, int niveau,String formation, String ufr, String departement) {
        this.idEtudiant = idEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.sexe = sexe;
        this.mot_de_passe = mot_de_passe;
        this.disponibilite = disponibilite;
        this.codifier = codifier;
        this.numeroEtudiant = numeroEtudiant;
        this.dateNai = dateNai;
        this.lieuNai = lieuNai;
        this.email = email;
        this.niveau = niveau;
        this.formation=formation;
        this.ufr = ufr;
        this.departement = departement;
    }
    public int getNiveau() {
        return niveau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUfr() {
        return ufr;
    }

    public void setUfr(String ufr) {
        this.ufr = ufr;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
 public Etudiant() {
       
    }

    public Date getDateNai() {
        return dateNai;
    }

    public void setDateNai(Date dateNai) {
        this.dateNai = dateNai;
    }

    public String getLieuNai() {
        return lieuNai;
    }

    public void setLieuNai(String lieuNai) {
        this.lieuNai = lieuNai;
    }
 
    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
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

    public int getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(int numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public boolean isCodifier() {
        return codifier;
    }

    public void setCodifier(boolean codifier) {
        this.codifier = codifier;
    }
    
    
    
}
