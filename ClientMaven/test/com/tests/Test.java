package com.tests;

import com.blo.Ville;
import com.dto.connexionAPI;

public class Test {

	public static void main(String[] args) {
		
		/** 
		 * Test pour get() sans code_Commune_INSEE
		 */
		//connexionAPI.get();
		
		/** 
		 * Test pour get() avec code_Commune_INSEE
		 */
		//connexionAPI.get("01001");

		/** 
		 * Test pour post()
		 */
		/*Ville v = new Ville();
		v.setCodeCommuneINSEE("99999");
		v.setCodePostal("49360");
		v.setNomCommune("Maulévrier");
		v.setLibelleAcheminement("Maulévrier");
		//v.setLigne5("test");
		v.setLatitude("14.1");
		v.setLongitude("145.1");
		connexionAPI.post(v);*/

		/** 
		 * Test pour put()
		 */
		/*String codeCommuneINSEE = "99999";
		Ville v = new Ville();
		v.setCodeCommuneINSEE("99998");
		v.setCodePostal("test");
		v.setNomCommune("test");
		v.setLibelleAcheminement("test");
		v.setLigne5("test");
		v.setLatitude("test");
		v.setLongitude("test");
		connexionAPI.put(codeCommuneINSEE,v);*/

		/** 
		 * Test pour delete()
		 */
		String codeCommuneINSEE = "99998";
		connexionAPI.delete(codeCommuneINSEE);
	}
}
