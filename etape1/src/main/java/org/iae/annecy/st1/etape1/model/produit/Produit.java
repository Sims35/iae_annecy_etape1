package org.iae.annecy.st1.etape1.model.produit;

import java.io.Serializable;

public class Produit implements Serializable {
	private String reference;
	private String description;
	private double prix;
	private String nom;
	private String descLongue;

	public String getdescLongue() {
		return descLongue;
	}

	public void setdescLongue(String descLongue) {
		this.descLongue = descLongue;
	}

	public Produit(String reference, String description, String descriptionLongue, double prix, String nom) { // Constructeur.
		this.reference = reference;
		this.description = description;
		this.prix = prix;
		this.nom = nom;
		this.descLongue = descriptionLongue;
	}

	public String getnom() {
		return nom;
	}

	public void setnom(String nom) {
		this.nom = nom;
	}

	public String getreference() {
		return reference;
	}

	public void setreference(String reference) {
		this.reference = reference;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public double getprix() {
		return prix;
	}

	public void setprix(double prix) {
		this.prix = prix;
	}

	public String afficherProduit() { // Méthode affichage.
		String t = "";
		t += "\nLe produit : " + this.getnom() + "à une référence qui est : " + this.getreference() + ". "
				+ "La description du produit est : " + this.getdescription() + ". " + " Le prix du produit est de "
				+ this.getprix() + "€.\n";
		return t;
	}

}