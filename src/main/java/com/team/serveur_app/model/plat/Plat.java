package com.team.serveur_app.model.plat;

import com.team.serveur_app.model.categorie.Category;

public class Plat {
    protected int id;
    protected String nom;
    protected float price;
    protected String description;
    protected byte[] img;
    protected Category category;

    public Plat(int id, String nom, float price, String description, byte[] img, Category category) {
        this.id = id;
        this.nom = nom;
        this.price = price;
        this.description = description;
        this.img = img;
        this.category = category;
    }
    public Plat( String nom, float price, String description, byte[] img, Category category) {
        this.nom = nom;
        this.price = price;
        this.description = description;
        this.img = img;
        this.category = category;
    }

    public Plat() {}

    public Plat(String nom, float price, Category category) {
        this.nom = nom;
        this.price = price;
        this.category = category;
    }

    public Plat(Plat p){
        this.id = p.id;
        this.nom = p.nom;
        this.category = p.category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategorie(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", img=" + img.length +
                ", categorie=" + category.toString() +
                '}';
    }
}
