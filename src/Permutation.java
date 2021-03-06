import java.util.Random;
import java.util.Map;
import java.lang.Math;
import java.util.HashMap;

public class Permutation {
	public static String generate(String alphabet) {
		char[] key = alphabet.toCharArray();
		Random r = new Random();

		for (int i = 0; i < alphabet.length(); i++) {
			char tmp = key[i];
			int rand = r.nextInt(alphabet.length());
			key[i] = key[rand];
			key[rand] = tmp;
		}

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

	public static String crack(String message, String alphabet)
	{
		Integer[][] correspondances = new Integer[alphabet.length()][alphabet.length()];
		//lettres restantes à traiter dans le message	
		String restant = message;
		//frequences des lettres dans le message
		Map<String,Double> freqMsg = Texte.frequences(message);
		//frequences des lettres dans le corpus
      Texte t = new Texte("corpus.txt");
      Map<String,Double> freqCorpus = t.frequences();
		//tant qu'il reste des lettres à analyser dans le message
		System.out.println("Déchiffrement sans clé");
		while (restant.length() > 0)
		{
			double freqTmp = 0.0;
			String charTmp = "";

			for (Map.Entry<String,Double> entry : freqCorpus.entrySet())
			{
				//un caractère
				if (entry.getKey().length() == 1 && freqMsg.get(restant.charAt(0)+"") != null)
				{
					if (Double.compare(freqTmp,0) == 0)
					{
						charTmp = entry.getKey();
						freqTmp = entry.getValue();
					} else if (Double.compare(Math.abs(freqMsg.get(restant.charAt(0)+"") - entry.getValue()),Math.abs(freqMsg.get(restant.charAt(0)+"") - freqTmp)) < 0)
					{
						freqTmp = entry.getValue();
						charTmp = entry.getKey();
					}
				//deux caractères
				} else if (entry.getKey().length() == 2 && restant.length() > 1 && freqMsg.get(""+restant.charAt(0)+restant.charAt(1)) != null)
				{
					if (Double.compare(freqTmp,0) == 0)
					{
						charTmp = entry.getKey();
						freqTmp = entry.getValue();
					} else if (Double.compare(Math.abs(freqMsg.get(""+restant.charAt(0)+restant.charAt(1)) - entry.getValue()),Math.abs(freqMsg.get(""+restant.charAt(0)+restant.charAt(1)) - freqTmp)) < 0)
					{
						freqTmp = entry.getValue();
						charTmp = entry.getKey();
					}
				//trois caractères
				} else if (entry.getKey().length() == 3 && restant.length() > 2 && freqMsg.get(""+restant.charAt(0)+restant.charAt(1)+restant.charAt(2)) != null)
				{
					if (Double.compare(freqTmp,0) == 0)
					{
						charTmp = entry.getKey();
						freqTmp = entry.getValue();
					} else if (Double.compare(Math.abs(freqMsg.get(""+restant.charAt(0)+restant.charAt(1)+restant.charAt(2)) - entry.getValue()),Math.abs(freqMsg.get(""+restant.charAt(0)+restant.charAt(1)+restant.charAt(2)) - freqTmp)) < 0)
					{
						freqTmp = entry.getValue();
						charTmp = entry.getKey();
					}
				}
			}
			
			if (charTmp.length() > 0)
			{
				for (int k = 0; k < charTmp.length(); k++)
				{
					int weight = charTmp.length()*charTmp.length();
					if (correspondances[charTmp.charAt(k)-65][restant.charAt(k)-65] == null)
						correspondances[charTmp.charAt(k)-65][restant.charAt(k)-65] = weight;
					else
						correspondances[charTmp.charAt(k)-65][restant.charAt(k)-65] += weight;
				}
			}
			
			restant = restant.substring(1);
		}
		
		String key = "";
		String max = "";
		int max_nb = 0;
		
		for (int i = 0; i < alphabet.length(); i++)
		{
			max = "";
			max_nb = 0;
			for (int j = 0; j < alphabet.length(); j++)
			{
				if (correspondances[i][j] != null && correspondances[i][j] > max_nb)
				{
					max = alphabet.charAt(j)+"";
					max_nb = correspondances[i][j];
				}
			}
			key += max;
		}
		
		return key;
	}
	
	public static String replace(String message, String from, String to)
	{
		String new_string = "";
		int index = message.indexOf(from);
		for (int i = 0; i < message.length(); i++)
		{
			new_string += (i == index) ? to : message.charAt(i)+"";
		}
		return new_string;
	}
}
