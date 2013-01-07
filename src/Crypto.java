import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Crypto
{
	public static void main(String[] args)
	{
		//alphabet
		Scanner in = new Scanner(System.in);
		System.out.println("Veuillez saisir un alphabet\n('_' pour ABCDEFGHIJKLMNOPQRSTUVWXYZ) :");
		String alphabet = in.next();
		alphabet = alphabet.equals("_") ? "ABCDEFGHIJKLMNOPQRSTUVWXYZ" : alphabet;
		//crypto
		in = new Scanner(System.in);
		System.out.println("Type de chiffrement ?\n1) Permutation\n2) Vigenère");
		String chiffrement = in.next();
		//message
		in = new Scanner(System.in);
		System.out.println("Fichier texte à chiffrer :");
		String fichier = in.next();
		
		Texte t = new Texte(fichier);
		
		if (chiffrement.equals("1"))
		{
			System.out.println("__Permutation__");
			String key = Permutation.generate(alphabet);
			System.out.println("key: " + key);
			String cyphered = Permutation.cypher(t.getTexte(), key, false);
			String cracked = Permutation.crack(cyphered, alphabet);
			System.out.println("cracked: "+cracked);	
		} else
		{
			System.out.println("__Vigenère__");
		}
		
	}
}
