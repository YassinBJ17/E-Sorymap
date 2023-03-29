package com.example.projet_gestion.entity;

public class Project {




    private String idprojet;
    private String nom_projet;
    private String codeetat;
    private String idutilisateur ;

    public Project() {
    }

    public int getIdutilisateur() {
        return Integer.parseInt(idutilisateur);
    }


    public String getIdprojet() {
        return idprojet;
    }

    public void setIdprojet(String idprojet) {
        this.idprojet = idprojet;
    }

    public String getNom_projet() {
        return nom_projet;
    }

    public void setNom_projet(String nom_projet) {
        this.nom_projet = nom_projet;
    }

    public String getCodeetat() {
        return codeetat;
    }

    public void setCodeetat(String codeetat) {
        this.codeetat = codeetat;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public Project(String idprojet, String nom_projet, String codeetat, String idutilisateur) {
        this.idprojet = idprojet;
        this.nom_projet = nom_projet;
        this.codeetat = codeetat;
        this.idutilisateur = idutilisateur;
    }
}



