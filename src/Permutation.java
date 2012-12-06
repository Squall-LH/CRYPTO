import java.util.Random;

public class Permutation {
	public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		String key = generate(alphabet);
		System.out.println("key: " + key);
		String message = "FUuU";
		System.out.println("message: " + message);
		String cyphered = cypher(message, key, false);
		System.out.println("cyphered: " + cyphered);
		String clear = decipher(cyphered, key);
		System.out.println("clear: " + clear);
	}

	public static String generate(String alphabet) {
		char[] key = alphabet.toCharArray();
		Random r = new Random();

		for (int i = 0; i < alphabet.length(); i++) {
			char tmp = key[i];
			int rand = r.nextInt(alphabet.length());
			key[i] = key[rand];
			key[rand] = tmp;
		}

		System.out.println(key);
		return String.copyValueOf(key);
	}

	public static String cypher(String message, String key, boolean delete) {
		StringBuffer cyphered = new StringBuffer();
		
		for (int i = 0; i < message.length(); i++) {
			if (key.indexOf(message.charAt(i)) >= 0)
				cyphered.append(key.charAt(message.charAt(i) - 65));
			else if (!delete)
				cyphered.append('~');
		}

		return cyphered.toString();
	}

	public static String decipher(String message, String key) {
		StringBuffer cyphered = new StringBuffer();

		for (int i = 0; i < message.length(); i++) {
			int index = key.indexOf(message.charAt(i)) + 65;
			char toAdd = '~';
			if (message.charAt(i) != '~')
				toAdd = (char) index;
			cyphered.append(toAdd);
		}

		return cyphered.toString();
	}
/*
	public static String dechiffrer(int cle, String chaine) {
		String msg = "";
		for (int i = 0; i < chaine.length(); i++) {
			int ascii = ((int) chaine.charAt(i));
			msg += ((char) (ascii - cle));
		}
		return msg;
	}
	*/
}
