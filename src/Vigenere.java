public class Vigenere {
	
	public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		'W', 'X', 'Y', 'Z' };
	
	public static void main(String[] args) {
		//String cyphered = cypher("JADOREECOUTERLARADIOTOUTELAJOURNEE","MUSIQUE");
		//decipher(cyphered,"MUSIQUE");
		String message = "CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBWRVXUOAKXAOSXXWEAHBWQIMMQMNKGRFVGXWTRZXWIAXLXFPSKAUTEMNDCMGTSXMXBTUIADNGMGPSRELXNJELXVRVPRTULHDNQWTWDTYGBPHXTFALJHASVBFXNGLLCHRZBWELEKMSJIKNBHWRJGNMGJSGLXFEYPHAGNRBIEQJTAMRVLCRREMNDGLXRRIMGNSNRWCHRQHAEYEVTAQEBBIPEEWEVKAKOEWADREMXMTBHHCHRTKDNVRICHRCLQOHPWQAIIWXNRMGWOIIFKEE";
		decipher(message);
	}

	private static char[][] createTable(char[] alphabet) {
		char[][] table = new char[alphabet.length][alphabet.length];
		int a;
		for (int i = 0; i < alphabet.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				a = i + j;
				if (a >= alphabet.length) {
					a = a - alphabet.length;
				}
				table[i][j] = alphabet[a];
			}
		}
		return table;
	}

	private static void printTable(char[][] table) {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print(table[i][j]);
			}
			System.out.println();
		}
	}
	
	public static String cypher(String message, String key) {
		StringBuffer cyphered = new StringBuffer();
		char[][] table = createTable(alphabet);
		int i = 0; 
		while(i < message.length()) {
			for(int j = 0; j < key.length() && i < message.length(); j++) {
				cyphered.append(table[message.charAt(i)-65][key.charAt(j)-65]);
				i++;
			}
		}
		
		System.out.println("cyphered by Vigenere : " + cyphered);
		return cyphered.toString();
	}
	
	public static String decipher(String message, String key) {
		StringBuffer cyphered = new StringBuffer();
		char[][] table = createTable(alphabet);
		int i = 0; 
		while(i < message.length()) {
			for(int j = 0; j < key.length() && i < message.length(); j++) {
				//cyphered.append(table[message.charAt(i)-65][key.charAt(j)-65]);
				boolean found = false;
				char k = 0;
				while(!found) {
					found = table[k][key.charAt(j)-65] == message.charAt(i);
					k++;
				}
				if(found) {
					char c = (char) (k + 64);
					cyphered.append(c);
					System.out.println(cyphered);
				}
				i++;
			}
		}
		
		System.out.println("deciphered by Vigenere : " + cyphered);
		return cyphered.toString();
	}
	
	public static String decipher(String message) {
		StringBuffer cyphered = new StringBuffer();
		char[][] table = createTable(alphabet);
		
		// On commence par extraire les mots du message chiffré
		int i = 5, j = 5;
		
		StringBuffer extract = new StringBuffer();
		for(int k = 0; k < message.length(); k++) {
			if((k+1) % i == j % i) {
				extract.append(message.charAt(k));
			}
		}
		
		System.out.println("extract : " + extract);
		
		//System.out.println("deciphered by Vigenere : " + cyphered);
		return cyphered.toString();
	}

	public static String generate(int size) {
		char[] key = new char[size];
		Random r = new Random();

		for (int i = 0; i < size; i++) {
			int rand = r.nextInt(alphabet.length);
			key[i] = alphabet[rand];
		}

		System.out.println(key);
		return String.copyValueOf(key);
	}

}
