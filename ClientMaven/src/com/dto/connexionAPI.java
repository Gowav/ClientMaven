package com.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.blo.Ville;

public class connexionAPI {

	public static List<Ville> get() {
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject("http://127.0.0.1:8181/get", String.class);

		List<Ville> liste = new ArrayList<Ville>();
		String[] villes = result.split(";");
		
		for(String ville : villes) {
			String[] infos = ville.split(",|;");
			Ville newVille = new Ville();
			newVille.setCodeCommuneINSEE(infos[0]);
			newVille.setNomCommune(infos[1]);
			newVille.setCodePostal(infos[2]);
			newVille.setLibelleAcheminement(infos[3]);
			newVille.setLigne5(infos[4]);
			newVille.setLongitude(infos[5]);
			newVille.setLatitude(infos[6]);
			liste.add(newVille);
		}
		
		/*for(Ville v : liste) {
			System.out.println(v.getCodeCommuneINSEE()+","+v.getNomCommune()+","+v.getCodePostal()+","+v.getLibelleAcheminement()+","+v.getLigne5()+","+v.getLongitude()+","+v.getLatitude());
		}*/
		
		return liste;
	}
	
	public static Ville get(String codeCommuneINSEE) {
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject("http://127.0.0.1:8181/get?codeCommuneINSEE="+codeCommuneINSEE, String.class);

		String[] infos = result.split(",|;");
		Ville v = new Ville();
		v.setCodeCommuneINSEE(infos[0]);
		v.setNomCommune(infos[1]);
		v.setCodePostal(infos[2]);
		v.setLibelleAcheminement(infos[3]);
		v.setLigne5(infos[4]);
		v.setLongitude(infos[5]);
		v.setLatitude(infos[6]);
		
		//System.out.println(v.getCodeCommuneINSEE()+","+v.getNomCommune()+","+v.getCodePostal()+","+v.getLibelleAcheminement()+","+v.getLigne5()+","+v.getLongitude()+","+v.getLatitude());
		
		return v;
	}
	
	public static void post(Ville ville) {
		RestTemplate rest = new RestTemplate();
		rest.postForLocation("http://127.0.0.1:8181/post", ville, void.class);

	}
	
	public static void put(String codeCommuneINSEE, Ville ville) {
		RestTemplate rest = new RestTemplate();
		String url = "http://127.0.0.1:8181/put?codeCommuneINSEE="+codeCommuneINSEE;
			
		if(ville.getCodeCommuneINSEE()!=null)
			url +="&newCodeCommuneINSEE="+ville.getCodeCommuneINSEE();	
		if(ville.getCodePostal()!=null)
			url +="&codePostal="+ville.getCodePostal();
		if(ville.getNomCommune()!=null)
			url +="&nomCommune="+ville.getNomCommune();
		if(ville.getLibelleAcheminement()!=null)
			url +="&libelleAcheminement="+ville.getLibelleAcheminement();
		if(ville.getLigne5()!=null)
			url +="&ligne5="+ville.getLigne5();
		if(ville.getLongitude()!=null)
			url +="&longitude="+ville.getLongitude();
		if(ville.getLatitude()!=null)
			url +="&latitude="+ville.getLatitude();
		
		rest.put(url,null);
	}

	public static void delete(String codeCommuneINSEE) {
		RestTemplate rest = new RestTemplate();
		rest.delete("http://127.0.0.1:8181/delete?codeCommuneINSEE="+codeCommuneINSEE);
	}
}
