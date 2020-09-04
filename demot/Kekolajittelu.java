package demot;

import java.util.Random;

/**
 * @author Siiri
 * @version 30 Mar 2020
 */
public class Kekolajittelu {
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		
		Random random = new Random();
		// arvotaan taulukon koko väliltä [10,20]:
		int taulukonKoko = random.nextInt(11) + 10;
		int[] keko = new int[taulukonKoko];
		// 0-indeksiin tieto taulukon koosta:
		keko[0] = taulukonKoko -1;
		//täytetään satunnaisilla luvuilla väliltä [0,100]:
		for (int i=1; i<taulukonKoko; i++) {
			keko[i] = random.nextInt(101);
		}
		
		System.out.println("\nTaulukon alkuperäinen muoto:");
		
		for(int i=0; i<taulukonKoko; i++) {
			System.out.println(" " + keko[i]);
		}
		
		//lajitellaan taulukko kekolajittelulla
		kekolajittelu(keko, (taulukonKoko -1));
		
		System.out.println("\nKeko suuruusjärjestyksessä:");
		
		for(int i=0; i<taulukonKoko; i++) {
			System.out.println(" " + keko[i]);
		}

	}
	
	/**
	 * Järjestää keon suurimmasta alkaen pienenevään järjestykseen.
	 * Ensimmäinen alkio ilmaisee kekoalkioiden lukumäärän
	 * @param a Lajiteltava taulukko
	 * @param n Järjestettävien alkioiden määrä
	 */
	public static void kekolajittelu(int[] a, int n){
		a[0] = n;
		teeKeko(a);		
		for(int i = n; i > 1; i--){
			int apu = a[1];
			a[1] = a[i];
			a[i] = apu;
			a[0]--;
			korjaaKeko(a, 1);
		}
		a[0] = n;		//asetetaan 0-indeksiin takaisin lukumäärä
	}

	public static int[] teeKeko(int[] a) {
		for(int i = a[0]/2; i >= 1; i--) korjaaKeko(a, i);
		return a;
	}
	
	public static void korjaaKeko(int[] a, int i) {
		if (2*i > a[0]) return;
		int j = 2*i;
		int alkio = a[i];
		while (j <= a[0]) {
			if ((j < a[0]) && (a[j] > a[j+1])) j = j+1;
			if (alkio <= a[j]) break;
			a[j/2] = a[j];
			j = 2*j;
		}
		a[j/2] = alkio;		
	}
}
