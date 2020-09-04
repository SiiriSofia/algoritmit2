/**
 * 
 */
package demot;

import java.util.Random;

/**
 * Algoritmi Piin likiarvon laskemiseksi menetelmällä, jossa
 * lasketaan, kuinka suuri osuus generoitavista satunnaisista
 * pisteistä sijaitsee neliön sisään piirretyn ympyrän sisä-
 * puolella.
 * @author Siiri
 * @version 4 May 2020
 */
public class Pii {
	/**
	 * Teoreettinen 1.0 x 1.0 -kokoinen neliö sijaitsee koordinaatistossa
	 * siten, että toinen sivu on x-akselilla pisteiden (0,0)(1,0) välillä
	 * ja toinen sivu y-akselilla pisteiden (0,0)(0,1) välillä. Neliön sisällä 
	 * kulkee origokeskeisen ympyrän kaari siten, että ympyrän säde on 1.
	 * Koko ympyrän kaaresta siis neljännes kulkee neliön alueella. Lasketaan
	 * kuinka moni generoitavista pisteistä osuu ympyrän kaaren 
	 * alapuoliselle alueelle koordinaatistossa.
	 * @param pisteidenMaara Generoitavien satunnaispisteiden määrä
	 * @return piin likiarvo laskettuna kaavalla 4*k/n, jossa k on ympyrän 
	 * kaaren alapuolella sijaitsevien pisteiden määrä ja n kaikkien pisteiden 
	 * määrä
	 */
	public static double laskePiinLikiarvo(int pisteidenMaara) {
		int k = 0;
		int n = pisteidenMaara;
		for(int i=0; i<n; i++) {
			Random random = new Random();
			double x = random.nextDouble();
			double y = random.nextDouble();
			// Ympyrän kaaren kaava: x*x+y*y=r*r
			// Jos pisteen y-arvo on ympyrän kaaren alapuolella niin k++
			if (y < Math.sqrt(1.0-(x*x))) {  
				k++;
			}
		}
		double likiarvo = (double) 4*k/n;
		return likiarvo;
	}
	
	/**
	 * Lasketun Piin likiarvon virhe verrattuna Javan kirjastosta löytyvään
	 * piin likiarvoon.
	 * @param likiarvo Laskettu piin likiarvo, jota verrataan
	 * @return virheen itseisarvo
	 */
	public static double laskeVirhe(double likiarvo) {
		return Math.abs(likiarvo - Math.PI);
	}
	
	/**
	 * Testiohjelma metodien toiminnalle
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		
		//Annetaan metodille generoitavien pisteiden lukumäärä
		//Tulostetaan saatu piin likiarvo ja virhe verrattuna Javan
		//kirjastosta löytyvään likiarvoon:
		
		double likiarvo = laskePiinLikiarvo(1000);
		double virhe = laskeVirhe(likiarvo);
		System.out.println("Piin likiarvo: " + likiarvo + ", Virhe: " + virhe);
		
		likiarvo = laskePiinLikiarvo(10000);
		virhe = laskeVirhe(likiarvo);
		System.out.println("Piin likiarvo: " + likiarvo + ", Virhe: " + virhe);
		
		likiarvo = laskePiinLikiarvo(100000);
		virhe = laskeVirhe(likiarvo);
		System.out.println("Piin likiarvo: " + likiarvo + ", Virhe: " + virhe);
		
		likiarvo = laskePiinLikiarvo(1000000);
		virhe = laskeVirhe(likiarvo);
		System.out.println("Piin likiarvo: " + likiarvo + ", Virhe: " + virhe);
		
		likiarvo = laskePiinLikiarvo(10000000);
		virhe = laskeVirhe(likiarvo);
		System.out.println("Piin likiarvo: " + likiarvo + ", Virhe: " + virhe);	
	}
}
