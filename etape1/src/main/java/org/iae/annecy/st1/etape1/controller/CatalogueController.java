package org.iae.annecy.st1.etape1.controller;

import java.util.ArrayList;

import org.iae.annecy.st1.etape1.model.catalogue.Catalogue;
import org.iae.annecy.st1.etape1.model.produit.Produit;

		public class CatalogueController {
			private Catalogue cat;
	
		public String get (){		// ==> Reviens au même qu'un S.O.P, sauf ca renvoit sur une variable de chaine de caractères.
			return this.cat.afficherListeProduits();
		}

		public CatalogueController (Catalogue c ){
		this.cat = c;
		}
		
		public Catalogue getCat() {
			return cat;
		}
		public void setCat(Catalogue cat) {
			this.cat = cat;
		}

		public void chercheProduit() {
			// TODO Auto-generated method stub
			
		}
		
}