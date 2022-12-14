package com.team.serveur_app.model.plat;

import com.team.serveur_app.model.categorie.Categorie;

import java.util.Arrays;
import java.util.Objects;

public class Plat {
    protected int id;
    protected String nom;
    protected float price;
    protected String description;
    protected byte[] img;
    protected Categorie categorie;

    public Plat(int id, String nom, float price, String description, byte[] img, Categorie categorie) {
        this.id = id;
        this.nom = nom;
        this.price = price;
        this.description = description;
        this.img = img;
        this.categorie = categorie;
    }
    public Plat( String nom, float price, String description, byte[] img, Categorie categorie) {
        this.nom = nom;
        this.price = price;
        this.description = description;
        this.img = img;
        this.categorie = categorie;
    }

    public Plat() {}

    public Plat(String nom, float price, Categorie categorie) {
        this.nom = nom;
        this.price = price;
        this.categorie = categorie;
    }

    public Plat(Plat p){
        this.id = p.id;
        this.nom = p.nom;
        this.categorie = p.categorie;
        this.price = p.price;
        this.img = p.img;
        this.description = p.description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", img=" + img.length +
                ", categorie=" + categorie.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        Plat plat = (Plat) o;
        return id == plat.id;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nom, price, description);
        result = 31 * result;
        return result;
    }
}

