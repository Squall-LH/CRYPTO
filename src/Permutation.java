public class Permutation
{
	//non opérationnel
	public static String chiffrer(String cle, String chaine)
	{
		int nbRows = chaine.length()/cle.length();
		String tab[] = new String[cle.length()];
		for (int i = 0; i < chaine.length(); i++)
		{
			tab[i%8] += chaine.charAt(i);
		}
		//ordre à déterminer selon la clé
		String chiffre = tab[1]+tab[0]+tab[7]+tab[3]+tab[2]+tab[6]+tab[4]+tab[5];
		return(chiffre);
	}
	
	public static String dechiffrer(String cle, String chaine)
	{
		return "bar";
	}
}
