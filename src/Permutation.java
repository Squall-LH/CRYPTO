import java.util.Random;
import java.util.Map;
import java.lang.Math;
import java.util.HashMap;

public class Permutation {
	public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		String key = generate(alphabet);
		System.out.println("key: " + key);
		//String message = "I WOULD WALK FIVE HUNDRED MILES AND I WOULD WALK FIVE HUNDRED MORE";
		//String message = "IWOULDWALKFIVEHUNDREDMILESANDIWOULDWALKFIVEHUNDREDMORE";
      Texte t = new Texte("texte.txt");
      String message = t.getTexte();
		
		//System.out.println("message: " + message);
		String cyphered = cypher(message, key, false);
		//System.out.println("cyphered: " + cyphered);
		String clear = decipher(cyphered, key);
		//System.out.println("clear: " + clear);
		String correspondances = crack(cyphered);
		System.out.println("cracked: "+correspondances);
		//System.out.println(decipher(cyphered, correspondances));
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

	public static String crack(String message)
	{
		Integer[][] correspondances = new Integer[alphabet.length()][alphabet.length()];
		//lettres restantes	
		String restant = message;
		//frequences dans le MESSAGE
		Map<String,Double> freqMsg = Texte.frequences(message);
		//frequences dans le CORPUS
      Texte t = new Texte("texte.txt");
      Map<String,Double> freqCorpus = t.frequences();
		//tant qu'il reste des lettres
		while (restant.length() > 0)
		{
			double freqTmp = 0.0;
			String charTmp = "";
			if (freqMsg.get(restant.charAt(0)+"") != null)
			{
				for (Map.Entry<String,Double> entry : freqCorpus.entrySet())
				{
					if (entry.getKey().length() == 1)
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
					}
				}
			}
			
			if (charTmp.length() == 1)
			{
				if (correspondances[charTmp.charAt(0)-65][restant.charAt(0)-65] == null)
					correspondances[charTmp.charAt(0)-65][restant.charAt(0)-65] = 1;
				else
					correspondances[charTmp.charAt(0)-65][restant.charAt(0)-65]++;
			}
			restant = restant.substring(1);
		}
		
		String key = "";
		String max = "";
		int max_nb = 0;
		for (int i = 0; i < alphabet.length(); i++)
		{
			max = ".";
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
