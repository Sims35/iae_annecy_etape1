package org.iae.annecy.st1.etape1.model.controllerClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class ControllerClient implements Serializable {
	private ArrayList<Client> clients = new ArrayList<Client>();
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private FileInputStream filin;
	private FileOutputStream filout;

	public void ajouterClient(Client nclient) {
		this.getClients().add(nclient);
	}

	public String afficherListeClients() { // Affichage de la liste des clients.
		String text = "";
		int i = 0;
		for (Client nclient : this.getClients()) {
			text += "Nom & Prénom :" + nclient.getnom() + " " + nclient.getprenom() + ". / N° Client :"
					+ nclient.getnumeroClient();
		}
		return text;
	}

	public void clientchoisi(int i) {
		// TODO Auto-generated method stub

		ConsoleHelper.display(
				"- Le client : " + this.getClients().get(i).getprenom() + " " + this.getClients().get(i).getnom()
						+ " a comme numéro client :" + this.getClients().get(i).getnumeroClient() + ".");
	}

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public Client chercheClient(int numero) { // ID --> Utilisateur peut
												// chercher via N° ou Ref le
												// produit.
		Iterator<Client> it = this.getClients().iterator();
		int i = 0;
		while (it.hasNext()) {
			Client p = it.next();
			i++;
			if (numero == p.getnumeroClient()) {
				return p;
			}
		}
		return null;
	}

	public int chercheClientienteir(int numero) { // ID --> Utilisateur peut
													// chercher via N° ou Ref le
													// produit.
		Iterator<Client> it = this.getClients().iterator();
		int i = 0;
		while (it.hasNext()) {
			Client p = it.next();
			i++;
			if (numero == p.getnumeroClient()) {
				return i;
			}
		}
		return i - 1;
	}

	public String get() {
		String texte = "";
		for (Client client : this.getClients()) {
			texte += client.afficherClient();
		}
		return texte;
	}

	public void modifierattribut(int choixclientMenu, int choixattributMenu) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		int choixclient = 0;
		switch (choixattributMenu) {
		case 1:
			ConsoleHelper.display("Veuillez entrer le nom que vous souhaitez modifier : ");
			String n = s.next();
			this.getClients().get(choixclient).setnom(n);
			break;
		case 2:
			ConsoleHelper.display("Veuillez entrer le prénom que vous souhaitez modifier : ");
			String m = s.next();
			this.getClients().get(choixclient).setprenom(m);
			break;
		case 3:
			ConsoleHelper.display("Veuillez entrer un N° client : ");
			int d = s.nextInt();
			this.getClients().get(choixclient).setnumeroClient(d);
			break;
		case 4:
			ConsoleHelper.display("Veuillez entrer le numéro que vous souhaitez modifier : ");
			int p = s.nextInt();
			this.getClients().get(choixclient).setcodePromotionnel(p);
			;
			break;
		}

	}
	
	public int menu(){
		Scanner s = new Scanner(System.in);
		int choixmenuClient;
		ConsoleHelper.display(
				"------------------------------------------------- Menu Gestion Client(s) ---------------------------------------------------\n");
		ConsoleHelper.display(
				"1/ Afficher Client   -   2/ Modifier Client   -   3/ Recherche client par son N°   -   4/ Ajouter Client   -   5/ Quitter\n");
		choixmenuClient = s.nextInt();
		return choixmenuClient;
	}
	
	public void monmenuclient(){
		Scanner s = new Scanner(System.in);
		int choixattributClient=0;
		int choixmenuClient = this.menu();
		while (choixmenuClient < 5) {
			switch (choixmenuClient) {
			case 1:

				if (this.get().equals("")) {
					ConsoleHelper.display(
							"*** ATTENTION *** : Notre base de client(s) est actuellement vide. Pensez à ajouter un client... :\n");
				} else {
					ConsoleHelper.display(this.get());
				}

				choixmenuClient = this.menu();
				break;
			case 2:
				ConsoleHelper.display("Veuillez entrer le numéro du client que vous souhaitez rechercher :");
				int ref = s.nextInt();
				Client c = this.chercheClient(ref);
				if (c == null) {
					ConsoleHelper.display("Le client que vous souhaitez rechercher n'existe pas.");
				} else {
					int cli = this.chercheClientienteir(ref);

					ConsoleHelper.display("1: Nom	-	2: Prénom	-	3: Numéro Client");
					ConsoleHelper.display("Veuillez sélectionner le numero de l'attribut que vous-voulez modifier :");
					choixattributClient = s.nextInt();
					this.modifierattribut(cli - 1, choixattributClient);

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

				choixmenuClient = this.menu();
				break;
			case 3:
				ConsoleHelper.display("Veuillez entrer le numéro client à rechercher :\n");
				int re = s.nextInt();
				Client cl = this.chercheClient(re);
				if (cl == null) {
					ConsoleHelper.display("Le client que vous souhaitez afficher n'existe pas.\n");
				} else {
					ConsoleHelper.display("Client que vous souhaitez afficher :\n");
					ConsoleHelper.display(cl.afficherClient());
				}

				choixmenuClient = this.menu();
				break;
			case 4:

				ConsoleHelper.display("Veuillez renseigner le numéro du client :\n");
				int r = s.nextInt();

				if (this.chercheClient(r) == null) {

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
						this.ajouterClient(nouveauc);

						try {
							filout = new FileOutputStream("biblio2.txt");
							oos = new ObjectOutputStream(filout);
							oos.writeObject(this);
							filout.close();
							oos.close();

						} catch (IOException e) {
							ConsoleHelper.display(e.getMessage());
						}
					}

				} else {
					ConsoleHelper.display("Le client existe déjà... :\n");

				}

				choixmenuClient = this.menu();
				break;

			}

		}
		
	}

}