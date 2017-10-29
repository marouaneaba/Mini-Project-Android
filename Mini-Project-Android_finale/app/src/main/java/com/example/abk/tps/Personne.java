package com.example.abk.tps;

/**
 * Created by Marouane Abakarim on 27/09/2017.
 */

public class Personne {

    protected static int id = 0;
    private String Name;
    private String Prenom;
    private String Tel;
    private String Email;
    private String Address;
    private String Commentaire;


    public Personne()
    {
        this.Name = "";
        this.Prenom = "";
        this.Tel = "";
        this.Email = "";
        this.Address = "";
        this.Commentaire = "";
    }


    public Personne(String name,String prenom,String tel,String email,String address,String commentaire)
    {
        this.Name = name;
        this.Prenom = prenom;
        this.Tel = tel;
        this.Email = email;
        this.Address = address;
        this.Commentaire = commentaire;
    }

    /* transformer notre objet en String */
    public String toString(){
        return "Name : "+this.Name+" ,Prénom : "+this.Prenom+
                " ,Tel : "+this.Tel+" ,Email : "+this.Email+
                " ,Address : "+this.Address+" ,Commentaire : "+this.Commentaire;
    }

    public String getName() {
        return Name;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Personne.id = id;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getTel() {
        return Tel;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCommentaire(String commentaire) {
        Commentaire = commentaire;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }


}
