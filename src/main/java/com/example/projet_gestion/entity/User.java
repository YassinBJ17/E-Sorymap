package com.example.projet_gestion.entity;

public class User {
    private String idutilisateur ;
    private String mot_de_passe;
    private String nom;
    private String prenom;
    private String email;
    public User() {
    }

    public int getIdutilisateur() {
        return Integer.parseInt(idutilisateur);
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setIdutilisateur(String idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String idutilisateur, String mot_de_passe, String nom , String prenom, String email) {
        this.idutilisateur = idutilisateur;
        this.mot_de_passe = mot_de_passe;
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
    }


}


