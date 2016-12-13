package org.iae.annecy.st1.etape1.model.catalogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class Catalogue implements Serializable {
	private ArrayList<Produit> produits = new ArrayList<Produit>();

	public ArrayList<Produit> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}

	public void ajouterProduit(Produit nproduit) {
		this.getProduits().add(nproduit);
	}

	public String afficherListeProduits() { // Affichage de la liste de
											// produits.
		String text = "";
		int i = 0;
		for (Produit nproduit : this.getProduits()) {
			text += "Numéro de référence :" + i + "\n- Nom du produit : " + nproduit.getnom() + " à pour référence : "
					+ nproduit.getreference() + ".\nIl a comme description : " + nproduit.getdescription()
					+ ".\nEnfin, il a un prix unitaire de " + nproduit.getprix() + " €.\n\n";
			i++;
		}
		return text;
	}

	public void produitchoisi(int i) {
		// TODO Auto-generated method stub

		ConsoleHelper.display("- Nom du produit : " + this.getProduits().get(i).getnom() + " à pour référence : "
				+ this.getProduits().get(i).getreference() + ".\n Il a comme description :"
				+ this.getProduits().get(i).getdescription() + "avec un prix unitaire de "
				+ this.getProduits().get(i).getprix() + " €.");
	}

	public void modifierattribut(int choixproduit, int choixattribut) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		switch (choixattribut) {
		case 1:
			ConsoleHelper.display("Veuillez entrer le nom que vous souhaitez modifier : ");
			String n = s.next();
			this.getProduits().get(choixproduit).setnom(n);
			break;
		case 2:
			ConsoleHelper.display("Veuillez entrer une nouvelle description : ");
			String d = s.nextLine(); // ==> On prend en considération toute la
										// ligne pour la description longue.
			this.getProduits().get(choixproduit).setdescription(d);
			break;
		case 3:
			ConsoleHelper.display("Veuillez entrer le prix que vous souhaitez modifier : ");
			double p = s.nextDouble();
			this.getProduits().get(choixproduit).setprix(p);
			break;
		}

		/*
		 * try { File Fichier = new File ("Desktop"); ObjectInputStream ois =
		 * new ObjectInputStream (new FileInputStream (Fichier)); C1 =
		 * (Catalogue) ois.readObject();
		 * 
		 * 
		 * }catch(FileNotFoundException e){ C1 = new Catalogue();
		 * C1.ajouterProduit(P1); C1.ajouterProduit(P2); }
		 */

	}

	public Produit chercheProduit(String ref) { // ID --> Utilisateur peut
												// chercher via N° ou Ref le
												// produit. Ici, emploi du
												// design pattern "Iterateur"
		Iterator<Produit> it = this.getProduits().iterator();
		while (it.hasNext()) {
			Produit p = it.next();
			if (ref.equals(p.getreference())) {
				return p;
			}
		}
		return null;
	}

	public int chercheProduitid(String ref) { // ID --> Utilisateur peut
												// chercher via N° ou Ref le
												// produit.
		Iterator<Produit> it = this.getProduits().iterator();
		int i = 0;
		while (it.hasNext()) {
			Produit p = it.next();
			i++;
			if (ref.equals(p.getreference())) {
				return i;
			}
		}
		return -1;
	}
}