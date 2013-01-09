import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Vigenere {

	public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };

	// Cibles utilisées pour déterminer la longueur de la clé
	public static double alpha = 0.019;
	public static double french_indice_target = 0.073;

	private static class Decal {
		public int i;
		public int j;
		public int d;

		public Decal(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}

	public static void main(String[] args) {
		// String cyphered =
		// cypher("JADOREECOUTERLARADIOTOUTELAJOURNEE","MUSIQUE");
		// decipher(cyphered,"MUSIQUE");

		// Exemple du cours : L'analyseSimple ne donne que 3 sur 5 des lettres
		// de la clés.
		//String message = "CHREEVOAHMAERATBIAXXWTNXBEEOPHBSBQMQEQERBWRVXUOAKXAOSXXWEAHBWQIMMQMNKGRFVGXWTRZXWIAXLXFPSKAUTEMNDCMGTSXMXBTUIADNGMGPSRELXNJELXVRVPRTULHDNQWTWDTYGBPHXTFALJHASVBFXNGLLCHRZBWELEKMSJIKNBHWRJGNMGJSGLXFEYPHAGNRBIEQJTAMRVLCRREMNDGLXRRIMGNSNRWCHRQHAEYEVTAQEBBIPEEWEVKAKOEWADREMXMTBHHCHRTKDNVRICHRCLQOHPWQAIIWXNRMGWOIIFKEE";

		// L'analyseSimple fonctionne avec ce message français
		 String message ="WJZTGYQWAFVCQZKAZKSROIFSXFTMWXCVUYIWXVISOZJYKARWTFMDXNMAIWDWYIASVEFLWTXNUSHJISBXVDZYBINLSFEHANGOVMOSNDXUTNQSWYOIBZUHNUSYMZEYNJIWTFEODJZNJLHFLGQHAAEMBTBVFJNNJFSKPZFITJXKSXNZYTGOYJOYSJZXTQOGVFXIMJNIXGIAOIFQXLWGTJCNQZKOFJFJDVUYVKZJHNVGGYMWNLQTXODJNJRGHWOXMQVEYJOSDLGNGAAGIQKDFUTOLDWXCZDJGPWWGJCGAAKAXMBIOXQXFKYKEZSMQXBOYWBYCZSTGBQWBYOORNGEXKSSDKMWLKWLWWCJGXEWKGFROKQZLYNWBYSAUVNAIMBOEMASWAQSUWKIPJVKQGFJSGKFXJRABJBVXTZEJTWJXYQXWASGANXVFNHJXKSRSBDJVMZWGXOHUQTPNFSXNDRKBYNDSXKKDTGKSUSWNZOJLNZVSXKKBJEHFLWTXNCZBAHGFHRZDFBASLZJCGAAKAXVISZJAYXFJFSAOPJUTOIAFJNPYFEZJUSYDZEHBASUSQYDZIXITAAFSNXTKOVMCSCZFWHQAWSSZMQXXJHWRJCXDNLPFDZNCVFNHJXJVTWWAJWNNIIJCYQXKAXABJCMQYBJFKDMKGFJLZJKUMOGQSBPJKRJCOMSZWXAHJCYQXFKQQPIKOQXWAUDCRLYQXMQSYGYKOQXWARSBLKIAXXAYVSXDDFFGEFLSXNZLNKYTFSNVZEYIAWEWXKGMQTJLMSQKKXZLWIJCNDZPJYKZJQMOMAWWWSKZFFDXQXKSUCSXVUXLWNLQJDOQUTNIGBSKWXJBJKAFRSOQIXITFCSMGQJMKSWBFLPEFBPJLCSVVFYXJISWYKPJUTOXSUJCYMSZAWWICOOUQLARWHYKDFJGBZJSZBZFQHJWAONDXQVNESWGYZVEIXXTFUTEOYJFAUGIWNZEFEHJEOSNNENEUFNONDYASVPTMXTEMELKWSVSFPAXZXJHWRFEYUYXQWKOZHXAZKOIWZNNZZGKKHCQTWWUJGHJKGZSQMNXJYSGXSYGRXJYIINFZZFBASLGZBOAZMLTMFXOYQWBZJJOZHWQQEAXUCQYMQXWQUJCKONEJNNVMCNAPUQXJXGWYWJZTGYQWXJXZEFNNFAGYBJBQXZNJSJDVUYNJAWFNDVNQXOFNOSDWUJGMZAZHKNEFMLFJTTSNEJLAHZOSDDXQHJXSZJCZEXTUJJHWYKNWNOVMSROIFNEFTAUSKDFFNCJFWJNPSJHHTYIJVJQNEZZEWSOMMQHCNKHJKQQHLKSEOWDZMZLWUGWSDZPFVEJJGTXVULNEQDSFSHMSMAJKCSMCMQNIJSIJDNASYHFUCSNVONWASAHWSLGJVAYSWYEITTFIJLFTCAAWMWQSQFCNGWXWQSGUOXFFEWIMFJDZMQTBZKWGSGUYXWZKCSKGAIXQWSILYPFINJRABJBVXVNAQUCSAPQNEHJUZFCNMNMOFFGMONUYXNUSFRSGQXLECUSSDNQXIKHWGVEZXFLYNWBHOXARIPJSIOYPDIAQN";

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
		while (i < message.length()) {
			for (int j = 0; j < key.length() && i < message.length(); j++) {
				cyphered.append(table[message.charAt(i) - 65][key.charAt(j) - 65]);
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
		while (i < message.length()) {
			for (int j = 0; j < key.length() && i < message.length(); j++) {
				// cyphered.append(table[message.charAt(i)-65][key.charAt(j)-65]);
				boolean found = false;
				char k = 0;
				while (!found) {
					found = table[k][key.charAt(j) - 65] == message.charAt(i);
					k++;
				}
				if (found) {
					char c = (char) (k + 64);
					cyphered.append(c);
					// System.out.println(cyphered);
				}
				i++;
			}
		}

		System.out.println("deciphered by Vigenere : " + cyphered);
		return cyphered.toString();
	}

	public static String decipher(final String message) {
		String deciphered = "";
		char[][] table = createTable(alphabet);

		// On trouve l'indice de coïndcidence.
		int keySize = guessKeySize(message);
		System.out.println("Taille de clé supposée: " + keySize);

		// Analyse_fréquentielle
		String key = simpleAnalyse(message, keySize);
		System.out.println("Clée: " + key);
		deciphered = decipher(message, key);

		List<Decal> listDecal = new ArrayList<Decal>();

		for (int i = 1; i <= 5; i++) {
			for (int j = i + 1; j <= 5; j++) {
				double max = 0.0;
				double indice;
				Decal equation = null;
				for (int d = 0; d < 26; d++) {
					indice = incidence(message, keySize, i, keySize, j, d);
					if (indice > max) {
						max = indice;
						equation = new Decal(i, j, d);
					}
				}
				if (isValidIndice(max)) {
					System.out.println("Good indice found: " + max);
					listDecal.add(equation);
				}
			}
		}

		for (Decal d : listDecal) {
			System.out.println("i: " + d.i + " j: " + d.j + " d:" + d.d);
		}

		String extracted_message = extract(message, keySize, 1);
		String message_all = extracted_message;
		String message_dec1 = decal(extracted_message, 10);
		String message_dec2 = decal(extracted_message, 21);
		String message_dec3 = decal(extracted_message, 4);
		String message_dec4 = decal(extracted_message, 17);

		message_all += message_dec1;
		message_all += message_dec2;
		message_all += message_dec3;
		message_all += message_dec4;

		maxFreq(message_all, keySize, 1, 0);

		return deciphered;
	}

	public static boolean isValidIndice(double indice) {
		return indice < 0.09 && indice > 0.060;
	}

	public static String decal(final String message, int d) {
		char[] result = new char[message.length()];

		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);
			c = (char) (c + d);
			if (c > 90) {
				c = (char) (c % (90));
				c += 65;
			}
			// System.out.println("char: " + c);
			// System.out.println("int: " + (int)c);
			result[i] = c;
		}
		return new String(result);
	}

	public static String simpleAnalyse(String message_ori, int keySize) {
		String key = "";

		for (int i = 1; i <= keySize; i++) {
			String message = extract(message_ori, keySize, i);
			System.out.println("message, i=" + i + "\n" + message);
			Map<String, Double> count = Texte.count(message);
			String letter = "";
			double max = 0.0;
			for (Map.Entry<String, Double> entry : count.entrySet()) {
				if (entry.getValue() > max) {
					max = entry.getValue();
					letter = entry.getKey();
				}
			}
			System.out.println("maxFreq: " + max / message.length() + " , i="
					+ i + " " + letter);
			char letter_char = letter.toCharArray()[0];
			char key_char;
			if (letter_char >= 69) {
				key_char = (char) (letter_char - 4);
			} else {
				key_char = (char) (letter_char + 22);
			}
			int decal = letter.toCharArray()[0] - 69; // E -> ascii -> 69
			char decal_char = (char) (decal + 65);
			System.out.println("maxFreq, decal:" + decal + " decal_char: "
					+ decal_char);
			key += String.valueOf(key_char);
		}

		return key;
	}

	public static String maxFreq(String message_ori, int keySize, int i,
			int decal) {
		String key = "";

		String message = message_ori;
		// String message_decal = decal(message_ori,decal);
		// String message = extract(message_decal, keySize, i);
		System.out.println("message, i=" + i + "\n" + message);
		Map<String, Double> count = Texte.count(message);
		double max = 0.0;
		for (Map.Entry<String, Double> entry : count.entrySet()) {
			if (entry.getValue() > max) {
				max = entry.getValue();
				key = entry.getKey();
			}
		}
		char letter_char = key.toCharArray()[0];
		char key_char;
		if (letter_char >= 69) {
			key_char = (char) (letter_char - 4);
		} else {
			key_char = (char) (letter_char + 22);
		}
		System.out.println("maxFreq: " + max / message.length() + " , i=" + i
				+ " " + key);
		int diff = key.toCharArray()[0] - 69; // E -> ascii -> 69
		// char decal_char = (char) (decal + 65);
		System.out.println("maxFreq, diff:" + diff + " key_char: " + key_char);

		return key;
	}

	public static int guessKeySize(String message) {
		int i = 0;
		boolean found = false;
		while (!found) {
			i++;
			List<Double> indices = new ArrayList<Double>();
			// On calcule l'ensemble des indices de coincidences jusqu'à i.
			for (int j = 0; j <= i; j++) {
				indices.add(incidence(message, i, j));
			}
			// Si tous les indices sont proches de la valeur que l'on cherche à
			// attendre (ici 0.73 pour le français)
			// alors on a trouvé la longueur de la clé qui est i.
			found = true;
			for (Double indice : indices) {
				if (!(indice < french_indice_target + alpha && indice > french_indice_target
						- alpha)) {
					found = false;
					System.out.println("indice pas satisfaisant : " + indice);
				}
			}
			if (found == false)
				System.out
						.println("Pas d'indices de coincidence satisfaisant pour i = "
								+ i + " , on incrémente i.");
			if (i > 10)
				found = true;
		}
		return i;
	}

	public static String extract(String message, int i, int j) {
		StringBuffer extract = new StringBuffer();
		for (int k = 0; k < message.length(); k++) {
			if ((k + 1) % i == j % i) {
				extract.append(message.charAt(k));
			}
		}
		return extract.toString();
	}

	public static double incidence(String message_ori, int i, int j) {
		String message = extract(message_ori, i, j);
		Map<String, Double> count = Texte.count(message);

		double indice_co = 0.0;
		for (int k = 0; k < alphabet.length; k++) {
			double f = count.get(String.valueOf(alphabet[k]));
			f *= (f - 1);
			int message_length = message.length();
			indice_co += (f / (message_length * (message_length - 1)));
		}
		// System.out.println("i: " + i + " j: " + j + " indice: " + indice_co);
		return indice_co;
	}

	public static double incidence(String message_ori, int i, int j, int k,
			int l, int decal) {
		String message_1 = extract(message_ori, i, j);
		String message_decaled = decal(message_ori, decal);
		String message_2 = extract(message_decaled, k, l);

		Map<String, Double> count_1 = Texte.count(message_1);
		Map<String, Double> count_2 = Texte.count(message_2);

		double indice_co = 0.0;
		for (int r = 0; r < alphabet.length; r++) {
			double f_1 = count_1.get(String.valueOf(alphabet[r]));
			double f_2 = count_2.get(String.valueOf(alphabet[r]));
			double f = f_1 * f_2;
			int message_length_1 = message_1.length();
			int message_length_2 = message_2.length();
			indice_co += (f / (message_length_1 * message_length_2));
		}
		// System.out.println("i: " + i + " j: " + j + " k: " + k + " l: " + l +
		// " decal: " + decal + " indice: " + indice_co);
		return indice_co;
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
