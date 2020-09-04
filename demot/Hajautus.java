/**
 * 
 */
package demot;

/**
 * Sijoitetaan lukuja hajautuksen avulla taulukkoon.
 * 
 * Sovitaan, että tallennettavat luvut ovat positiivisia 
 * kokonaislukuja ja 0 tarkoittaa että indeksi on VAPAA ja
 * -1 tarkoittaa että indeksistä on POISTETTU alkio
 * @author Siiri
 * @version 12 Apr 2020
 */
public class Hajautus {
			
	/**
	 * Yksinkertainen hajautusfunktio, jossa käytetään avointa
	 * osoitteenmuodostusta ja lineaarista etsintää h(k,i)
	 * @param t hajautustaulukko
	 * @param k sijoitettava kokonaisluku/avain
	 * @param i parametri joka saa peräkkäisiä kokonaislukuarvoja (0,1,2...)
	 * @return palauttaa hajautustaulukon indeksin, johon k sijoitetaan
	 */
	public static int hajauta(int[] t, int k, int i) {
		int indeksi = (k % t.length + i) % t.length;
		return indeksi;
	}

	/**
	 * Lisätään kokonaisluku/avain hajautustaulukkoon ensimmäiseen sellaiseen
	 * indeksiin, joka on vapaa (arvo 0) tai josta on poistettu alkio (arvo -1)
	 * @param t hajautustaulukko
	 * @param k sijoitettava kokonaisluku/avain
	 */
	public static void lisaa(int[] t, int k) {
	    int indeksi = hajauta(t, k, 0);
	    if (t[indeksi] == 0 || t[indeksi] == -1) t[indeksi] = k;
	    else {
	        int i=0;
	        while (t[indeksi] != 0 && t[indeksi] != -1 && indeksi
	                < t.length) {
	            indeksi = hajauta(t, k, i);
	            i++;
	        }
	        if (indeksi < t.length) t[indeksi] = k;
	    }
	}

	/**
	 * Etsitään hajautustaulukosta kokonaislukua/avainta, kunnes vastaan tulee
	 * ensimmäinen vapaa indeksi tai taulukko on käyty kokonaan läpi
	 * @param t hajautustaulukko
	 * @param k etsittävä kokonaisluku/avain
	 * @return totuusarvo true jos k löytyy taulukosta, false jos ei löydy
	 */
	public static boolean etsi(int[] t, int k) {
		int indeksi = hajauta(t, k, 0);
	    int i = 0;
	    while (t[indeksi] != 0 && indeksi < t.length) {
	            indeksi = hajauta(t, k, i);
	            if (t[indeksi] == k) return true;
	            i++;
	    }
	    return false;
	}

	/**
	 * Kokonaisluvun/avaimen poisto hajautustaulukosta, sijoitetaan
	 * poistetun kohdalle arvoksi -1
	 * @param t hajautustaulukko
	 * @param k poistettava kokonaisluku/avain
	 */
	public static void poista(int[] t, int k) {
	    int indeksi = hajauta(t, k, 0);
	    int i = 0;
	    while (t[indeksi] != 0 && indeksi < t.length) {
	            indeksi = hajauta(t, k, i);
	            if (t[indeksi] == k) {
	                t[indeksi] = -1;
	            }
	            i++;
	    }
	}

	/**
	 * Testiohjelma
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {

		int m = 10;		// taulukon koko, asetetaan nyt esim koko 10
		int[] t = new int[m]; // hajautustaulukko, oletuksena kaikissa
							  // indekseissä on aluksi arvo 0
		
		lisaa(t, 21);   // lisätään nyt esim luvut:
		lisaa(t, 55);   // 21, 55, 31, 49, 52, 72, 26 ja 19
		lisaa(t, 31);
		lisaa(t, 49);
		lisaa(t, 52);
		lisaa(t, 72);
		lisaa(t, 26);
		lisaa(t, 19);
		
		for (int i=0; i<t.length; i++) { // testitulostus
			System.out.println(t[i]);
		}
		
		System.out.println();
		System.out.println(etsi(t, 31)); // kokeillaan etsiä lukua 31 (true)
		System.out.println(etsi(t, 11)); // ja lukua 11 (false)
		System.out.println();
		
		poista(t, 49);   // poistetaan nyt esim luvut: 49, 26, 11, 21
		poista(t, 26);   // -> muiden poistot onnistuu, mutta 11 ei löydy
		poista(t, 11);	 // taulukosta, joten sen osalta ei tapahdu mitään
		poista(t, 21);
		
		for (int i=0; i<t.length; i++) { // testitulostus
			System.out.println(t[i]);
		}
		
		lisaa(t, 26);    // katsotaan vielä, että lisäys onnistuu kohtaan,
		lisaa(t, 36);    // joista on poistettu (arvona -1)
		
		System.out.println();
		for (int i=0; i<t.length; i++) { // testitulostus
			System.out.println(t[i]);
		}
		
	}

}
