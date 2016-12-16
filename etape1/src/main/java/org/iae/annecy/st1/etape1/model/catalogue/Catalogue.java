package org.iae.annecy.st1.etape1.model.catalogue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class Catalogue implements Serializable {
	private ArrayList<Produit> produits = new ArrayList<Produit>();
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private FileInputStream filin;
	private FileOutputStream filout;

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
	
	public int menu(){
		Scanner s = new Scanner(System.in);
		int choixmenu;
		ConsoleHelper.display(
				"------------------------------------------------------ Menu Catalogue (Responsable Produit)------------------------------------------------------\n");
		ConsoleHelper.display(
				"1/ Afficher   -   2/ Modifier   -   3/ Recherche produit par reference   -   4/ Ajouter   -   5/ Quitter");
		choixmenu = s.nextInt();
		return choixmenu;
	}
	
	public void monmenu(CatalogueController cataloguecontroller1){
		Scanner s = new Scanner(System.in);
		int choixattribut = 0;
		int choixmenu = this.menu();
		while (choixmenu < 5) {
			switch (choixmenu) {
			case 1:
				ConsoleHelper.display(cataloguecontroller1.get());

				choixmenu = this.menu();
				break;
			case 2:
				ConsoleHelper.display("Veuillez entrer la réference du produit que vous souhaitez rechercher :");
				String ref = s.next();
				Produit p = this.chercheProduit(ref);
				if (p == null) {
					ConsoleHelper.display("Le produit demandé n'existe pas.");
				} else {
					int prod = this.chercheProduitid(ref);

					ConsoleHelper.display("1: Nom	-	2: Description	-	3: Prix");
					ConsoleHelper.display("Veuillez sélectionner le numero de l'attribut que vous-voulez modifier :");
					choixattribut = s.nextInt();
					this.modifierattribut(prod - 1, choixattribut);

					try {
						filout = new FileOutputStream("biblio.txt");
						oos = new ObjectOutputStream(filout);
						oos.writeObject(this);
						filout.close();
						oos.close();
					} catch (IOException e) {
						ConsoleHelper.display(e.getMessage());
					}
				}

				choixmenu = this.menu();
				break;
			case 3:
				ConsoleHelper.display("Veuillez entrer la réference du produit à rechercher :\n");
				String re = s.next();
				Produit pr = this.chercheProduit(re);
				if (pr == null) {
					ConsoleHelper.display("Le produit demandé n'existe pas.\n");
				} else {
					ConsoleHelper.display("Produit trouvé :\n");
					pr.afficherProduit();
				}

				choixmenu = this.menu();
				break;
			case 4:

				ConsoleHelper.display("Veuillez renseigner le nom du produit :\n");
				String n = s.next();
				ConsoleHelper.display("Veuillez renseigner la référence du produit :\n");
				String r = s.next();

				if (this.chercheProduit(r) == null) {
					ConsoleHelper.display("Veuillez entrer la description courte du produit :\n");
					String m = s.next();
					ConsoleHelper.display("Veuillez entrer la description longue du produit :\n"); // ATTENTION,
																									// ZAPPEE
					String l = s.next();
					ConsoleHelper.display("Veuillez renseigner le prix :\n");
					double prix = s.nextDouble();

					if (prix < 0) {
						ConsoleHelper
								.display("Attention, le prix est négatif. Veuillez renseigner un nouveau prix :\n");
					} else {
						Produit nouveaup = new Produit(r, m, l, prix, n);
						this.ajouterProduit(nouveaup);

						try {
							filout = new FileOutputStream("biblio.txt");
							oos = new ObjectOutputStream(filout);
							oos.writeObject(this);
							filout.close();
							oos.close();

						} catch (IOException e) {
							ConsoleHelper.display(e.getMessage());
						}
					}

				} else {
					ConsoleHelper.display("Produit existe deja :\n");

				}

				choixmenu = this.menu();
				break;

			}

		}

		
	}
}