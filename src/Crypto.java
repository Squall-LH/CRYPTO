import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Crypto {
	public static void main(String[] args) {
		// crypto
		Scanner in = new Scanner(System.in);
		System.out
				.println("Type de chiffrement ?\n1) Permutation\n2) Vigenère (fonction de base)\n3) Vigenere (Cryptanalyse)");
		String chiffrement = in.next();

		if (chiffrement.equals("1")) {
			System.out.println("__Permutation__");

			// alphabet
			System.out
					.println("Veuillez saisir un alphabet\n('_' pour ABCDEFGHIJKLMNOPQRSTUVWXYZ) :");
			String alphabet = in.next();
			alphabet = alphabet.equals("_") ? "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
					: alphabet;

			// message
			in = new Scanner(System.in);
			System.out.println("Fichier texte à chiffrer :");
			String fichier = in.next();

			Texte t = new Texte(fichier);

			String key = Permutation.generate(alphabet);
			System.out.println("key: " + key);
			String cyphered = Permutation.cypher(t.getTexte(), key, false);
			String cracked = Permutation.crack(cyphered, alphabet);
			System.out.println("cracked: " + cracked);
		} else if (chiffrement.equals("2")){
			System.out.println("__Vigenère__ : Fonction de base.");
			// message
			in = new Scanner(System.in);
			System.out.println("Fichier texte à chiffrer.\n Doit être composé de caractères entre A et Z, sans espaces. ('_' pour utiliser le texte par défaut, texte_vigenere.txt):");
			String fichier = in.next();
			if(fichier.equals("_")) fichier = "texte_vigenere.txt";
			Texte t = new Texte(fichier);

			String key = Vigenere.generate(10);
			String cyphered = Vigenere.cypher(t.getContent(), key);
			Vigenere.decipher(cyphered, key);
		} else if (chiffrement.equals("3")){
			System.out.println("__Vigenère__ : Cryptanalyse.");
			System.out.println("Fichier texte à déchiffré..\n Doit être composé de caractères entre A et Z, sans espaces. ('_' pour utiliser le texte par défaut, texte_vigenere_cyphered.txt):");
			String fichier = in.next();
			if(fichier.equals("_")) fichier = "texte_vigenere_cyphered.txt";
			Texte t = new Texte(fichier);
			
			System.out.println("Texte sur lequel la cryptanalyse va s'effectuer:\n" + t.getContent());
			Vigenere.decipher(t.getContent());
		}

	}
}
