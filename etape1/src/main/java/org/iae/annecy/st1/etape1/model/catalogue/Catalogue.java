package org.iae.annecy.st1.etape1.model.catalogue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.model.produit.Produit;

public class Catalogue {
	private ArrayList<Produit> produits = new ArrayList<Produit>();

	
	public ArrayList<Produit> getProduits() {
		return produits;
	}
	public void setProduits(ArrayList<Produit> produits) {
		this.produits = produits;
	}
	
	
	public void ajouterProduit(Produit nproduit){
		this.getProduits().add(nproduit);
	}
	
	
	public String afficherListeProduits(){			// Affichage de la liste de produits.
		String text = "";
		int i=0;
		for (Produit Nproduit: this.getProduits()){
			text += "Numéro de référence :"+i+"\n- Nom du produit : " + Nproduit.getNom() + " à pour référence : "+ Nproduit.getReference() 
			+ ".\nIl a comme description : " + Nproduit.getDescription() + ".\nEnfin, il a un prix unitaire de " 
			+ Nproduit.getPrix() + " €.\n\n";
			i++;	
			}
		return text;
		}
	
	public void produitchoisi(int i) {
		// TODO Auto-generated method stub
		
		System.out.println( "- Nom du produit : " + this.getProduits().get(i).getNom() + " à pour référence : " 
							+ this.getProduits().get(i).getReference() + ".\n Il a comme description :" 
							+ this.getProduits().get(i).getDescription()
							+ "avec un prix unitaire de " + this.getProduits().get(i).getPrix() + " €.");
	}
	
	public void modifierattribut(int choixproduit, int choixattribut) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		switch (choixattribut) {
		case 1:
			System.out.println("Veuillez entrer le nom que vous souhaitez modifier : ");
			String n=s.next();
			this.getProduits().get(choixproduit).setNom(n);
			break;
		case 2:
			System.out.println("Veuillez entrer une nouvelle description : ");
			String d=s.nextLine();		// ==> On prend en considération toute la ligne pour la description longue.
			this.getProduits().get(choixproduit).setDescription(d);
			break;
		case 3:
			System.out.println("Veuillez entrer le prix que vous souhaitez modifier : ");
			double p=s.nextDouble();
			this.getProduits().get(choixproduit).setPrix(p);
			break;
		}	
	
		
		
		/*try {
			File Fichier = new File ("Desktop");
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream (Fichier));
			C1 = (Catalogue) ois.readObject();
			
			
		}catch(FileNotFoundException e){
			C1 = new Catalogue();
			C1.ajouterProduit(P1);
			C1.ajouterProduit(P2);
		}*/
		
		
		
	}
	
	public Produit chercheProduit(String ref){		// ID --> Utilisateur peut chercher via N° ou Ref le produit. Ici, emploi du design pattern "Iterateur"
		Iterator<Produit> it = this.getProduits().iterator();
		while(it.hasNext()){
			Produit p=it.next();
			if(ref.equals(p.getReference())){
				return p;
			}
		}
		return null;
	}
	
	public int chercheProduitid(String ref){		// ID --> Utilisateur peut chercher via N° ou Ref le produit.
		Iterator<Produit> it = this.getProduits().iterator();
		int i=0;
		while(it.hasNext()){
			Produit p=it.next();
			i++;
			if(ref.equals(p.getReference())){
				return i;
			}
		}
		return -1;
	}
}