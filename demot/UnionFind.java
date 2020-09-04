/**
 * 
 */
package demot;

/**
 * @author Siiri
 * @version 18 Apr 2020
 */
public class UnionFind {

	/**
	 * Operaatio, joka yhdistää annetut alipuut yhteen siten, että
	 * vähemmän alkioita sisältävä alipuu liitetään enemmän alkioita 
	 * sisältävän puun juurisolmuun.
	 * @param alkiojoukko alkiojoukko, jossa yhdistettävät alipuut
	 * sijaitsevat
	 * @param a yhdistettävän alipuun juurisolmu
	 * @param b toisen yhdistettävän alipuun juurisolmu
	 */
	public static void union(int[] alkiojoukko, int a, int b) {
	  int k;
	  k = alkiojoukko[a] + alkiojoukko[b];
	  if (alkiojoukko[a] <= alkiojoukko[b]) {
	    alkiojoukko[a] = k;
	    alkiojoukko[b] = a;
	  }
	  else {
	    alkiojoukko[b] = k;
	    alkiojoukko[a] = b;
	  }
	}
	
	/**
	 * Find-operaatio palauttaa viitteen pyydetyn indeksin kohdalla
	 * sijaitsevaan merkkijonoon.
	 * Tiivistetään alipuuta siten, että kaikki hakupolun alkiot
	 * viittaavat suoraan alipuun juurisolmuun.
	 * @param alkiojoukko alkiojoukko, jossa etsittävä juurisolmu
	 * sijaitsee
	 * @param x indeksi, jonka kohdalla olevan solmun juurisolmua etsitään
	 * @return viite merkkijonoon, joka sijaitsee juurisolmussa
	 */
	public static int find(int[] alkiojoukko, int x) {
	  int j, k;  
	  j = x;
	  while (alkiojoukko[j] > 0) { // Juurisolmun etsintä
	    j = alkiojoukko[j];
	  }
	 
	  while (alkiojoukko[x] > 0) { // Hakupolun tiivistys
	    k = x;
	    x = alkiojoukko[x];
	    alkiojoukko[k] = j;
	  }
	  return j;
	}
	
	/**
	 * Testitulostusmetodi, joka tulostaa taulukon arvot muodossa
	 * [alkio + " " + arvo]. Tyhjää 0-indeksiä ei tulosteta.
	 * @param taulukko jonka arvot halutaan tulostaa
	 */
	public static void testiTulostus(int[] taulukko) {
		for (int i=1; i<taulukko.length; i++) {
			System.out.println(i + " " + taulukko[i]);
		}
		System.out.println();
	}
	
	/**
	 * Testiohjelma:
	 * Luodaan merkkijonotaulukko, tässä esim 5 varsinaista alkiota.
	 * Huom: 0-indeksiin luodaan tyhjä merkkijono, jotta saadaan varsinaiset 
	 * alkiot alkamaan indeksistä 1.
	 * Viitteet-taulukossa on merkittynä kunkin indeksin solmun juurisolmu,
	 * paitsi jos alkio on itse juurisolmu, on taulukkoon merkitty juurisolmun
	 * alipuun solmujen määrän vastaluku.
	 * Asetetaan aluksi jokaiselle varsinaiselle alkiolle viitteeksi -1, 
	 * koska kaikki ovat alkutilanteessa juurisolmuja.
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
					 
		String[] alkiojoukko = 
		 // tyhjä, alkio 1, alkio 2, alkio 3,  alkio 4, alkio 5	
			{"",   "kiuru", "lokki", "rastas", "sorsa", "varis"};
		
		// taulukko, jossa alkiojoukkoa vastaavat viitteet,
		// 0-indeksi ei käytössä.
		int[] viitetaulukko = {0,-1,-1,-1,-1,-1};
		testiTulostus(viitetaulukko);
		
		// yhdistetään alipuita, testitulostukset:
		union(viitetaulukko, 1, 4);			
		testiTulostus(viitetaulukko);
		
		union(viitetaulukko, 2, 3);	
		testiTulostus(viitetaulukko);
		
		union(viitetaulukko, 1, 2);	
		testiTulostus(viitetaulukko);
		
		// find-operaatiot, tulostetaan palautetut alkiot:
		String etsitty = alkiojoukko[find(viitetaulukko, 2)];
		System.out.println(etsitty + "\n");	
		
		etsitty = alkiojoukko[find(viitetaulukko, 3)];
		System.out.println(etsitty + "\n");
		
		etsitty = alkiojoukko[find(viitetaulukko, 5)];
		System.out.println(etsitty + "\n");
		
		// tulostetaan lopuksi taulukko find-tiivistysten jälkeen:
		testiTulostus(viitetaulukko);		
	}
}
