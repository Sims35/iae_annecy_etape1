package org.iae.annecy.st1.etape1.model.client;

import org.iae.annecy.st1.tools.ConsoleHelper;

public class Client {
	private String nom;
	private String prenom;
	private int numeroClient;
	private int codePromotionnel;

	public Client(String nom, String prenom, int numeroClient, int codePromotionnel) { // Constructeur.
		this.nom = nom;
		this.prenom = prenom;
		this.numeroClient = numeroClient;
		this.codePromotionnel = codePromotionnel;
	}

	public String getnom() {
		return nom;
	}

	public void setnom(String nom) {
		this.nom = nom;
	}

	public String getprenom() {
		return prenom;
	}

	public void setprenom(String prenom) {
		this.prenom = prenom;
	}

	public int getnumeroClient() {
		return numeroClient;
	}

	public void setnumeroClient(int numeroClient) {
		this.numeroClient = numeroClient;
	}

	public int getcodePromotionnel() {
		return codePromotionnel;
	}

	public void setcodePromotionnel(int codePromotionnel) {
		this.codePromotionnel = codePromotionnel;
	}

	public String afficherClient() { // Méthode affichage.
		String t = "";
		t = "\nLe client " + this.getprenom() + " " + this.getnom() + " à le code client suivant : "
				+ this.getnumeroClient() + ".\nCode promotionnel (FACULTATIF !) : " + this.getcodePromotionnel() + ".";
		return t;
	}

}