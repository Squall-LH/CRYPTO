import java.util.Random;

public class Permutation {
	public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };

	public static void main(String[] args) {
		String key = generate(alphabet);
		System.out.println("key: " + key);
		String message = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println("message: " + message);
		String cyphered = chiffrer(message, key);
		System.out.println("cyphered: " + cyphered);
		String clear = decipher(cyphered,key);
		System.out.println("clear: " + clear);
	}

	public static String generate(char[] alphabet) {
		char[] key = alphabet;
		Random r = new Random();

		for (int i = 0; i < alphabet.length; i++) {
			char tmp = key[i];
			int rand = r.nextInt(alphabet.length);
			key[i] = key[rand];
			key[rand] = tmp;
		}

		System.out.println(key);
		return String.copyValueOf(key);
	}

	public static String chiffrer(String message, String key) {
		StringBuffer cyphered = new StringBuffer();

		for (int i = 0; i < message.length(); i++) {
			cyphered.append(key.charAt(message.charAt(i) - 65));
		}

		return cyphered.toString();
	}

	public static String decipher(String message, String key) {
		StringBuffer cyphered = new StringBuffer();

		for (int i = 0; i < message.length(); i++) {
			int index = key.indexOf(message.charAt(i))+65;
			char toAdd = (char)index;
			cyphered.append(toAdd);
		}

		return cyphered.toString();
	}

	public static String dechiffrer(int cle, String chaine) {
		String msg = "";
		for (int i = 0; i < chaine.length(); i++) {
			int ascii = ((int) chaine.charAt(i));
			msg += ((char) (ascii - cle));
		}
		return msg;
	}
}
