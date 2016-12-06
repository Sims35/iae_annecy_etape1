package org.iae.annecy.st1.etape1.model.produit;

public class Produit {
	private String Reference;
	private String Description;
	private double Prix;
	private String Nom;
	private String DescLongue;
	
	
		
	
	public String getDescLongue() {
		return DescLongue;
	}
	public void setDescLongue(String descLongue) {
		DescLongue = descLongue;
	}

	public Produit(String reference, String description, String DescriptionLongue, double prix,String nom) {	// Constructeur.
		Reference = reference;
		Description = description;
		Prix = prix;
		this.Nom = nom;
		this.DescLongue = DescriptionLongue;
	}
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}


	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}
	
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	public double getPrix() {
		return Prix;
	}
	public void setPrix(double prix) {
		Prix = prix;
	}
	
	public String AfficherProduit(){	// Méthode affichage.
		String t = "";
		t +="\nLe produit : "+ this.getNom() + "à une référence qui est : " + this.getReference() + ". " +
							"La Description du produit est : " + this.getDescription() + ". " 
							+ " Le prix du produit est de " + this.getPrix() + "€.\n";
		return t;
	}
	
}