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
	private static MainController mainController;

	static {
		Main.mainController = new MainController();
	}

	/**
	 * Lance l'application.
	 * 
	 * @param args
	 *            command line parameters
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(final String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		/*initUserModel();

		final DataView userData = mainController.get("user:display");
		final StringView userView = new UserTextFrenchView();

		ConsoleHelper.display(userView.build(userData));*/
		
		Scanner s = new Scanner(System.in);
		
		Catalogue C1 = null;				// Etape de désérialisation ==> Chargement en Input.
		try {
			File fichier = new File ("Desktop");
			ObjectInputStream ois = new ObjectInputStream (new FileInputStream("fichier"));
			
			C1 = (Catalogue) ois.readObject();
			
		}
		catch (FileNotFoundException E) {
			C1 = new Catalogue();
		}
		
		
		
		
		
		CatalogueController Cat1 = new CatalogueController (C1);
		
		
		int choixproduit=0;
		int choixattribut=0;
		
		
		Produit P1 = new Produit ("997", "léger et jaune", 9.99, "Cahier");
		Produit P2 = new Produit ("783", "lourd et bleu", 3.99, "Gomme");
	
		
		System.out.println("Listing de tous les produits du catalogue :\n");
		
		C1.ajouterProduit(P1);
		C1.ajouterProduit(P2);
		
		//C1.afficherListeProduits();
		
		System.out.println(Cat1.get());
		
		// AffichageMenu
		String chProd = null;
		int choixMenu = 0;
		
		do {
			affichageMenu();
			choixMenu = s.nextInt();
			while (choixMenu != 1 && choixMenu != 2 && choixMenu !=3){
				System.out.println("Une erreur est apparue ! Veuillez rentrer 1= Modification de produit "
						+ "			/ 2= Afficher les produits / 3= Ajouter un produit");
			}
			
			if(choixMenu == 1){
			
				System.out.println("Veuillez indiquer la référence du produit à modifier :" );
					CatalogueController cat = new CatalogueController (C1);
					System.out.println(cat.get());
					chProd = s.next();
			}
			
			
			System.out.println("Quel attribut souhaitez-vous changer ? " + "1- Description" 
								+ "2- Description longue" + "3- Prix" + "4- Nom");
			
			int Sims = s.nextInt();
				while (Sims !=1 && Sims !=2 && Sims !=3 && Sims !=4){
					System.out.println("Une erreur est apparue ! Veuillez rentrer 1- Description" 
								+ "2- Description longue" + "3- Prix" + "4- Nom");
					Sims = s.next();
				}
		
		// MENU PRINCIPAL REGROUPANT : l'affichage, la modification, la recherche et l'ajout
		
		/* System.out.println("Menu principal:");
		
		int menu = 0;
		
		do
		{
				System.out.println(" (1) --> Afficher tous les produits."); 
				System.out.println(" (2) --> Modifier un produit.");
				System.out.println(" (3) --> Rechercher un produit.");
				System.out.println(" (4) --> Ajouter un produit.");
			
				
				System.out.println("Veuillez sélectionner le numéro adéquat à votre recherche :");
				menu = s.nextInt();
				
				switch (menu)
				{
				

				case 1 : 
					System.out.println(Cat1.get()); 	//Afficher tous les produits.
					break;
				case 2 :
					C1.modifierattribut(choixproduit,choixattribut);		// Modifier un produit
					break;
				case 3 :
					System.out.println("SVP, entrer la nouvelle reference : ");
					String r=s.next();
					C1.chercheProduit(r);			    // Chercher un produit dans le catalogue.
					break;
				case 4 :
					System.out.println("SVP, entrer la nouvelle reference : ");
					r=s.next();
					
					System.out.println("Entrer le nouveau prix : ");
					double p=s.nextDouble();
					
					System.out.println("Entrer la nouvelle description : ");
					String d=s.next();
					
					System.out.println("Entrer la nouveau nom : ");
					String n=s.next();
					
					C1.ajouterProduit(new Produit(r,d,p,n));									// ajouter un produit
					break;
				default :
						System.out.println("Ce que vous avez sélectionné n'est pas valide");
						
		}
		}while (menu != 4);		
		*/
		
		
		
		System.out.println("\nAjouter un produit au catalogue 1: OUI / 2: NON\n");
		int t=s.nextInt();
		
		if(t==1){
			System.out.println("SVP, entrer la nouvelle reference : ");
			String r=s.next();
			
			System.out.println("Entrer le nouveau prix : ");
			double p=s.nextDouble();
			
			System.out.println("Entrer la nouvelle description : ");
			String d=s.next();
			
			System.out.println("Entrer la nouveau nom : ");
			String n=s.next();
			
			C1.ajouterProduit(new Produit(r,d,p,n));
			
			File fichier = new File ("Desktop");			// 1ere étape pour sérializer = Sauvegarde en Output.
			ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream (fichier));
			oos.writeObject(C1);
		}
		
		
		
		
		System.out.println("\nVeuillez taper la référence du produit que vous cherchez :");
		
		String r=s.nextLine();
		Produit p= C1.chercheProduit(r);			// CETTE ETAPE EST ZAPPEE.. ??
		
		
		
		System.out.println("\nQuel attribut voulez-vous modifier ?");
		
		System.out.println("1 : Nom"); //> antislash T == pour le menu sur une ligne
		System.out.println("2 : Description");
		System.out.println("3 : Prix");
		
		choixattribut = s.nextInt();
		
		C1.modifierattribut(choixproduit,choixattribut);
		
		//C1.afficherListeProduits();
		System.out.println(Cat1.get());
		
	}

	
	private static void initUserModel() {
		final UserModel userModel = new UserModel();
		userModel.register(mainController);
	}
	
	public static void affichageMenu(){
		System.out.println("----------Menu---------"
					+ "Modifier un produit" + "Afficher liste des produits" 
					+ "Afficher un produit");
		
	}
	
	
	
}