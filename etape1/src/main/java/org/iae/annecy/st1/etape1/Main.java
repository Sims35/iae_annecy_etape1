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

import org.iae.annecy.st1.common.mvc.DataView;
import org.iae.annecy.st1.common.mvc.StringView;
import org.iae.annecy.st1.etape1.controller.CatalogueController;
import org.iae.annecy.st1.etape1.controller.MainController;
import org.iae.annecy.st1.etape1.model.UserModel;
import org.iae.annecy.st1.etape1.model.catalogue.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.etape1.view.UserTextFrenchView;
import org.iae.annecy.st1.tools.ConsoleHelper;

/**
 * Classe permetant de tester le MVC.
 * 
 * @author Djer1013
 */
public class Main {

	/**
	 * Controller permettant le traitement des actions d'exemple.
	 */
	//private static MainController mainController;

	//static Main.mainController = new MainController();
	

	public static void main(final String[] args) {
		/*initUserModel();

		final DataView userData = mainController.get("user:display");
		final StringView userView = new UserTextFrenchView();

		ConsoleHelper.display(userView.build(userData));*/
		
		Scanner s = new Scanner(System.in);
		
		final Catalogue catalogue1 = new Catalogue();				// Etape de désérialisation ==> Chargement en Input.
		
		CatalogueController Cat1 = new CatalogueController (catalogue1);
		
		
		int choixattribut=0;
		int choixmenu = 0;
		
		final Produit Produit1 = new Produit ("997", "léger et jaune", "Réputé par sa marque et la qualité de son papier", 9.99, "Cahier");
		final Produit Produit2 = new Produit ("783", "lourd et bleu", "Efficace pour supprimer vos plus grosses ratures", 3.99, "Gomme");
	
		catalogue1.ajouterProduit(Produit1);
		catalogue1.ajouterProduit(Produit2);
		
		
		System.out.println("-------------------------------------------- Menu Catalogue --------------------------------------------\n");
		System.out.println("1/ Afficher   -   2/ Modifier   -   3/ Recherche produit par reference   -   4/ Ajouter   -   5/ Quitter");
		choixmenu=s.nextInt();
		while(choixmenu < 5){
			switch(choixmenu){
				case 1:
					System.out.println(Cat1.get());

					System.out.println("-------------------Menu Catalogue-------------------\n");
					System.out.println("1: Afficher   -   2: Modifier   -   3: Recherche produit par reference   -   4:Ajouter   -   5:Quitter");
					choixmenu=s.nextInt();
					break;
				case 2:
					System.out.println("Veuillez entrer la réference du produit que vous souhaitez rechercher :");
					String ref=s.next();
					Produit p=catalogue1.chercheProduit(ref);
					if(p == null){
						System.out.println("Le produit demandé n'existe pas.");
					}else{
						int prod=catalogue1.chercheProduitid(ref);
						
						System.out.println("1: Nom	-	2: Description	-	3: Prix");
						System.out.println("Veuillez sélectionner le numero de l'attribut que vous-voulez modifier :");
						choixattribut = s.nextInt();
						catalogue1.modifierattribut(prod-1, choixattribut);
					}

					System.out.println("Menu Catalogue");
					System.out.println("1: Afficher	  -   2: Modifier   -  3: Rechercher produit par sa référence  -  4:Ajouter  -  5:Quitter\n");
					choixmenu=s.nextInt();
					break;		
				case 3:
					System.out.println("Veuillez entrer la réference du produit à rechercher :\n");
					String re=s.next();
					Produit pr=catalogue1.chercheProduit(re);
					if(pr == null){
						System.out.println("Le produit demandé n'existe pas.\n");
					}else{
						System.out.println("Produit trouvé :\n");
						pr.AfficherProduit();
					}
					
					System.out.println("-------------------Menu Catalogue-------------------\n");
					System.out.println("1: Afficher   -   2: Modifier   -   3: Recherche produit par reference   -   4:Ajouter   -   5:Quitter\n");
					choixmenu=s.nextInt();
					break;
				case 4:
					
					System.out.println("Veuillez renseigner le nom du produit :\n");
					String n = s.next();
					System.out.println("Veuillez renseigner la référence du produit :\n");
					String r = s.next();
					System.out.println("Veuillez entrer la description courte du produit :\n");
					String m = s.nextLine();
					System.out.println("Veuillez entrer la description longue du produit :\n");
					String l = s.nextLine();
					System.out.println("Veuillez renseigner le prix :\n");
					double prix = s.nextInt();
					
					Produit nouveaup = new Produit(r,m,l,prix,n);
					catalogue1.ajouterProduit(nouveaup);
				
					System.out.println("-------------------Menu Catalogue-------------------\n");
					System.out.println("1: Afficher   -   2: Modifier   -   3: Recherche produit par reference   -   4:Ajouter   -   5:Quitter\n");
					choixmenu=s.nextInt();
					break;
					
			}
			
			
		}
		
					
	}
}

		

			
	/*private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
	
	}*/
	