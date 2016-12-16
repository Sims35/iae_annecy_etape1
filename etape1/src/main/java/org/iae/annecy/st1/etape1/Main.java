/**
 * 
 */

package org.iae.annecy.st1.etape1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.catalogue.Catalogue;
import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.etape1.model.controllerClient.ControllerClient;
import org.iae.annecy.st1.etape1.model.panier.Panier;
import org.iae.annecy.st1.etape1.model.person.PersonAddModel;
import org.iae.annecy.st1.etape1.model.person.PersonGetModel;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

/**
 * Classe permettant de tester le MVC.
 * 
 * @author Djer1013
 */
public class Main {

	/**
	 * Controller permettant le traitement des actions d'exemple.
	 */
	private static MainController mainController;
	static {
		Main.mainController = new MainController();
	}

	public static void main(final String[] args) {
		initUserModel();
		initCustomerModel();

		final Scanner scan = new Scanner(System.in, "UTF-8");

		/*
		 * final DataView userData = mainController.get("user:display"); final
		 * StringView userView = new UserTextFrenchView();
		 * 
		 * ConsoleHelper.display(userView.build(userData));
		 */

		Scanner s = new Scanner(System.in);

		Catalogue catalogue1 = new Catalogue(); // Etape de
		// désérialisation ==>
		// Chargement en Input.
		ObjectOutputStream oos;
		ObjectInputStream ois;
		FileInputStream filin;
		FileOutputStream filout;

		try {
			filin = new FileInputStream("biblio.txt");
			ois = new ObjectInputStream(filin);
			catalogue1 = (Catalogue) ois.readObject();
			filin.close();
			ois.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			ConsoleHelper.display(e.getMessage());
		}

		CatalogueController cataloguecontroller1 = new CatalogueController(catalogue1);

		int choixattribut = 0;
		int choixmenu = 0;

		int choixattributClient = 0;
		

		
		int choixmenuPanier = 0;

		ControllerClient cont1 = new ControllerClient();
		Panier panier = new Panier();

		final Produit Produit1 = new Produit("997", "léger et jaune",
				"Réputé par sa marque et la qualité de son papier", 9.99, "Cahier");
		final Produit Produit2 = new Produit("783", "lourd et bleu", "Efficace pour supprimer vos plus grosses ratures",
				3.99, "Gomme");

		catalogue1.ajouterProduit(Produit1);
		catalogue1.ajouterProduit(Produit2);

		choixmenu = menugeneral();

		do{
			switch(choixmenu){
			case 1:
				//Gestion de notre catalogue 
				catalogue1.monmenu(cataloguecontroller1);
				choixmenu = menugeneral();
				break;
			case 2:
				// MENU GESTION DES CLIENTS .... AJOUTS....
				cont1.monmenuclient();
				choixmenu = menugeneral();
				break;
			case 3:
				// MENU dédié aux clients --> Gestion du panier
				ConsoleHelper.display(
						"------------------------------------------------- Menu dédié au Client ---------------------------------------------------\n");
				ConsoleHelper.display("Entre votre numero en tant que client : \n");
				int choixc1 = s.nextInt();
				Client clientconnecte1 = cont1.chercheClient(choixc1);
				if (clientconnecte1 != null) {
					panier.setClient(clientconnecte1);
					ConsoleHelper.display(
							"------------------------------------------------- Menu dédié au Client ---------------------------------------------------\n");
					ConsoleHelper.display(
							"1/ Ajouter produit au panier   -   2/ Afficher votre panier   -   3/ Afficher le prix total de votre panier   -   4/ Quitter\n");
					choixmenuPanier = s.nextInt();

					while (choixmenuPanier < 4) {
						switch (choixmenuPanier) {
						case 1:
							ConsoleHelper.display("Veuillez renseigner la référence du produit :\n");
							String refe = s.next();
							Produit ppanier = catalogue1.chercheProduit(refe);
							if (ppanier == null) {
								ConsoleHelper.display("Le produit n'existe pas !");
							} else {
								panier.ajouterProduit(ppanier);
								ConsoleHelper.display("Le produit est ajouté !");
							}
							ConsoleHelper.display(
									"------------------------------------------------- Menu dédié au Client ---------------------------------------------------\n");
							ConsoleHelper.display(
									"1/ Ajouter produit au panier   -   2/ Afficher votre panier   -   3/ Afficher le prix total de votre panier   -   4/ Quitter\n");
							choixmenuPanier = s.nextInt();
							break;

						case 2:
							ConsoleHelper.display(panier.afficherListeProduits());
							ConsoleHelper.display(
									"------------------------------------------------- Menu dédié au Client ---------------------------------------------------\n");
							ConsoleHelper.display(
									"1/ Ajouter produit au panier   -   2/ Afficher votre panier   -   3/ Afficher le prix total de votre panier   -   4/ Quitter\n");
							choixmenuPanier = s.nextInt();
							break;
						case 3:
							panier.afficherPrixProduits();
							ConsoleHelper.display(
									"------------------------------------------------- Menu dédié au Client ---------------------------------------------------\n");
							ConsoleHelper.display(
									"1/ Ajouter produit au panier   -   2/ Afficher votre panier   -   3/ Afficher le prix total de votre panier   -   4/ Quitter\n");
							choixmenuPanier = s.nextInt();
							break;
						case 4:

							ConsoleHelper.display("Veuillez renseigner le numéro du client :\n");
							int r = s.nextInt();

							if (cont1.chercheClient(r) == null) {

								ConsoleHelper.display("Veuillez entrer le nom du client :\n");
								String y = s.next();
								ConsoleHelper.display("Veuillez entrer le prenom du client :\n"); // ATTENTION,
								// ZAPPEE
								String l = s.next();
								ConsoleHelper.display("Veuillez renseigner le code promotionnel du client :\n");
								int numero = s.nextInt();

								if (numero < 0) {
									ConsoleHelper.display(
											"Attention, le numéro client est négatif. Veuillez renseigner un nouveau numéro :\n");
								} else {
									Client nouveauc = new Client(y, l, r, numero); // Client(String
									// nom,
									// String
									// prenom,
									// int
									// numeroClient,
									// int
									// codePromotionnel
									cont1.ajouterClient(nouveauc);

									try {
										filout = new FileOutputStream("biblio2.txt");
										oos = new ObjectOutputStream(filout);
										oos.writeObject(cont1);
										filout.close();
										oos.close();

									} catch (IOException e) {
										ConsoleHelper.display(e.getMessage());
									}
								}

							} else {
								ConsoleHelper.display("Le client existe déjà... :\n");

							}


						}
					}
				}
						choixmenu = menugeneral();
						break;
					default :
						ConsoleHelper.display("Saisie erronée ");
						choixmenu = menugeneral();
						break;

					}
				}while(choixmenu!=5);

			}

			public static int menugeneral(){
				int choix;
				Scanner saisie= new Scanner(System.in);
				ConsoleHelper.display("Choisissez votre degré de responsabilité : ");
				ConsoleHelper.display("1 : Je suis : Responsable produit");
				ConsoleHelper.display("2 : Je suis : Responsable clientèle");
				ConsoleHelper.display("3 : Je suis : Client");
				ConsoleHelper.display("4 : Je suis : Vendeur dans le magasin");
				ConsoleHelper.display("5 : Quitter"); 

				choix = saisie.nextInt();
				return choix;
			}







			private static void initUserModel() {
				final UserModel userModel = new UserModel();
				userModel.register(mainController);

			}

			private static void initCustomerModel() {
				final PersonGetModel customerGetModel = new PersonGetModel();
				customerGetModel.register(mainController);

				final PersonAddModel customerAddModel = new PersonAddModel();
				customerAddModel.register(mainController);
			}

		}
