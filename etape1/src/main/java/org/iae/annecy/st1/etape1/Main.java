/**
 * 
 */

package org.iae.annecy.st1.etape1;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import java.util.Scanner;

import org.iae.annecy.st1.common.mvc.BasicDataParam;
import org.iae.annecy.st1.common.mvc.ConsoleInputView;
import org.iae.annecy.st1.common.mvc.DataParam;
import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.catalogue.Catalogue;
import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.etape1.model.controllerClient.ControllerClient;
import org.iae.annecy.st1.etape1.model.panier.Panier;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.model.person.PersonAddModel;
import org.iae.annecy.st1.etape1.model.person.PersonGetModel;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonAddFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonCreateFrenchView;
import org.iae.annecy.st1.etape1.view.person.PersonGetFrenchView;
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
		int choixmenuClient = 0;

		int choixattributPanier = 0;
		int choixmenuPanier = 0;

		ControllerClient cont1 = new ControllerClient();
		Panier panier = new Panier();

		final Produit Produit1 = new Produit("997", "léger et jaune",
				"Réputé par sa marque et la qualité de son papier", 9.99, "Cahier");
		final Produit Produit2 = new Produit("783", "lourd et bleu", "Efficace pour supprimer vos plus grosses ratures",
				3.99, "Gomme");

		catalogue1.ajouterProduit(Produit1);
		catalogue1.ajouterProduit(Produit2);

		// MENU GESTION DES CLIENTS ... AJOUTS....

		// public int menugeneral(){

		/*
		 * ConsoleHelper.display("choisiez votre degreé de responsabilité : ");
		 * ConsoleHelper.display("1 : En tant que : Responsable produit");
		 * ConsoleHelper.display("2 : En tant que : Responsable clientèle");
		 * ConsoleHelper.display("3 : En tant que : Client");
		 * ConsoleHelper.display("4 : En tant que : Vendeur dans le magasin");
		 * ConsoleHelper.display("ou 5 : Quitter"); // choixmenu =
		 * this.saisie.nextInt();
		 */

		// return choixmenu;

		ConsoleHelper.display(
				"------------------------------------------------------ Menu Catalogue (Responsable Produit)------------------------------------------------------\n");
		ConsoleHelper.display(
				"1/ Afficher   -   2/ Modifier   -   3/ Recherche produit par reference   -   4/ Ajouter   -   5/ Quitter");
		choixmenu = s.nextInt();
		while (choixmenu < 5) {
			switch (choixmenu) {
			case 1:
				ConsoleHelper.display(cataloguecontroller1.get());

				ConsoleHelper.display(
						"------------------------------------------------------ Menu Catalogue (Responsable clientèle)------------------------------------------------------\n");
				ConsoleHelper.display(
						"1: Afficher   -   2: Modifier   -   3: Recherche produit par reference   -   4:Ajouter   -   5:Quitter");
				choixmenu = s.nextInt();
				break;
			case 2:
				ConsoleHelper.display("Veuillez entrer la réference du produit que vous souhaitez rechercher :");
				String ref = s.next();
				Produit p = catalogue1.chercheProduit(ref);
				if (p == null) {
					ConsoleHelper.display("Le produit demandé n'existe pas.");
				} else {
					int prod = catalogue1.chercheProduitid(ref);

					ConsoleHelper.display("1: Nom	-	2: Description	-	3: Prix");
					ConsoleHelper.display("Veuillez sélectionner le numero de l'attribut que vous-voulez modifier :");
					choixattribut = s.nextInt();
					catalogue1.modifierattribut(prod - 1, choixattribut);

					try {
						filout = new FileOutputStream("biblio.txt");
						oos = new ObjectOutputStream(filout);
						oos.writeObject(catalogue1);
						filout.close();
						oos.close();
					} catch (IOException e) {
						ConsoleHelper.display(e.getMessage());
					}
				}

				ConsoleHelper.display(
						"------------------------------------------------------ Menu Catalogue ------------------------------------------------------\n");
				ConsoleHelper.display(
						"1/ Afficher	  -      2/ Modifier    -    3/ Rechercher produit par sa référence    -    4/ Ajouter    -    5/ Quitter\n");
				choixmenu = s.nextInt();
				break;
			case 3:
				ConsoleHelper.display("Veuillez entrer la réference du produit à rechercher :\n");
				String re = s.next();
				Produit pr = catalogue1.chercheProduit(re);
				if (pr == null) {
					ConsoleHelper.display("Le produit demandé n'existe pas.\n");
				} else {
					ConsoleHelper.display("Produit trouvé :\n");
					pr.afficherProduit();
				}

				ConsoleHelper.display(
						"------------------------------------------------------ Menu Catalogue ------------------------------------------------------\n");
				ConsoleHelper.display(
						"1: Afficher   -   2: Modifier   -   3: Recherche produit par reference   -   4: Ajouter   -   5: Quitter\n");
				choixmenu = s.nextInt();
				break;
			case 4:

				ConsoleHelper.display("Veuillez renseigner le nom du produit :\n");
				String n = s.next();
				ConsoleHelper.display("Veuillez renseigner la référence du produit :\n");
				String r = s.next();

				if (catalogue1.chercheProduit(r) == null) {
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
						catalogue1.ajouterProduit(nouveaup);

						try {
							filout = new FileOutputStream("biblio.txt");
							oos = new ObjectOutputStream(filout);
							oos.writeObject(catalogue1);
							filout.close();
							oos.close();

						} catch (IOException e) {
							ConsoleHelper.display(e.getMessage());
						}
					}

				} else {
					ConsoleHelper.display("Produit existe deja :\n");

				}

				ConsoleHelper.display(
						"------------------------------------------------------ Menu Catalogue ------------------------------------------------------\n");
				ConsoleHelper.display(
						"1: Afficher   -   2: Modifier   -   3: Recherche produit par reference   -   4:Ajouter   -   5:Quitter\n");
				choixmenu = s.nextInt();
				break;

			}

		}

		// MENU GESTION DES CLIENTS .... AJOUTS....

		ConsoleHelper.display(
				"------------------------------------------------- Menu Gestion Client(s) ---------------------------------------------------\n");
		ConsoleHelper.display(
				"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
		choixmenuClient = s.nextInt();
		while (choixmenuClient < 5) {
			switch (choixmenuClient) {
			case 1:

				if (cont1.get().equals("")) {
					ConsoleHelper.display(
							"*** ATTENTION *** : Notre base de client(s) est actuellement vide. Pensez à ajouter un client... :\n");
				} else {
					ConsoleHelper.display(cont1.get());
				}

				ConsoleHelper.display(
						"-------------------------------------------------- Menu Gestion Client(s) --------------------------------------------------\n");
				ConsoleHelper.display(
						"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
				choixmenuClient = s.nextInt();
				break;
			case 2:
				ConsoleHelper.display("Veuillez entrer le numéro du client que vous souhaitez rechercher :");
				int ref = s.nextInt();
				Client c = cont1.chercheClient(ref);
				if (c == null) {
					ConsoleHelper.display("Le client que vous souhaitez rechercher n'existe pas.");
				} else {
					int cli = cont1.chercheClientienteir(ref);

					ConsoleHelper.display("1: Nom	-	2: Prénom	-	3: Numéro Client");
					ConsoleHelper.display("Veuillez sélectionner le numero de l'attribut que vous-voulez modifier :");
					choixattributClient = s.nextInt();
					cont1.modifierattribut(cli - 1, choixattributClient);

					try {
						filout = new FileOutputStream("biblio.txt");
						oos = new ObjectOutputStream(filout);
						oos.writeObject(cont1);
						filout.close();
						oos.close();
					} catch (IOException e) {
						ConsoleHelper.display(e.getMessage());
					}
				}

				ConsoleHelper.display(
						"-------------------------------------------------- Menu Gestion Client(s) --------------------------------------------------\n");
				ConsoleHelper.display(
						"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
				choixmenuClient = s.nextInt();
				break;
			case 3:
				ConsoleHelper.display("Veuillez entrer le numéro client à rechercher :\n");
				int re = s.nextInt();
				Client cl = cont1.chercheClient(re);
				if (cl == null) {
					ConsoleHelper.display("Le client que vous souhaitez afficher n'existe pas.\n");
				} else {
					ConsoleHelper.display("Client que vous souhaitez afficher :\n");
					ConsoleHelper.display(cl.afficherClient());
				}

				ConsoleHelper.display(
						"--------------------------------------------------- Menu Gestion Client(s) --------------------------------------------------\n");
				ConsoleHelper.display(
						"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
				choixmenuClient = s.nextInt();
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

				ConsoleHelper.display(
						"--------------------------------------------------- Menu Gestion Client(s) --------------------------------------------------\n");
				ConsoleHelper.display(
						"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
				choixmenuClient = s.nextInt();
				break;

			}

		}

		// MENU dédié aux clients --> Gestion du panier
		ConsoleHelper.display(
				"------------------------------------------------- Menu dédié au Client ---------------------------------------------------\n");
		ConsoleHelper.display("Entre votre numero en tant que client : \n");
		int choixc = s.nextInt();
		Client clientconnecte = cont1.chercheClient(choixc);
		if (clientconnecte != null) {
			panier.setClient(clientconnecte);
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

					ConsoleHelper.display(
							"--------------------------------------------------- Menu Gestion Client(s) --------------------------------------------------\n");
					ConsoleHelper.display(
							"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
					choixmenuClient = s.nextInt();
					break;

				}

			}

		}

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
