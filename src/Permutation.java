public class Permutation
{
	public static String chiffrer(int cle, String chaine)
	{
		String msg = "";
		for (int i = 0; i < chaine.length(); i++)
		{
			int ascii = ((int) chaine.charAt(i));
			msg += ((char) (ascii+cle));
		}
		return msg;
	}
	
	public static String dechiffrer(int cle, String chaine)
	{
		String msg = "";
		for (int i = 0; i < chaine.length(); i++)
		{
			int ascii = ((int) chaine.charAt(i));
			msg += ((char) (ascii-cle));
		}
		return msg;
	}
}
