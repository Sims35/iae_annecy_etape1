package org.iae.annecy.st1.etape1.model.panier;

import java.util.ArrayList;

import org.iae.annecy.st1.etape1.model.catalogue.Catalogue;
import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class Panier {
	private Client client;
	private double prixTotal;
	private ArrayList<Produit> produits = new ArrayList<Produit>();

	public void ajouterProduit(Produit nproduit) {
		this.getProduits().add(nproduit);
	}

	public String afficherListeProduits() { // Affichage de la liste des
											// produits contenus dans le panier.
		String text = "";
		int i = 0;
		for (Produit nproduit : this.getProduits()) {
			text += nproduit.afficherProduit();
		}
		return text;
	}

	public void afficherPrixProduits() { // Affichage de la liste des produits
											// contenus dans le panier.
		double total = 0;

		for (Produit nproduit : this.getProduits()) {
			ConsoleHelper.display(nproduit.afficherProduit());

			total = nproduit.getprix() + total;
		}

		ConsoleHelper.display("Le prix total est : " + total + " €.\n");
	}

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public void monmenupanier(Catalogue catalogue1) {
		// TODO Auto-generated method stub
		
	}

	

}
