package org.iae.annecy.st1.etape1.model.controllerClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.iae.annecy.st1.etape1.model.client.Client;
import org.iae.annecy.st1.etape1.model.produit.Produit;
import org.iae.annecy.st1.tools.ConsoleHelper;

public class ControllerClient implements Serializable {
	private ArrayList<Client> clients = new ArrayList<Client>();

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
}