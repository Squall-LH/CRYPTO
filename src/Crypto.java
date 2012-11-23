public class Crypto
{
	public static void main(String[] args)
	{
		String message = "RAYMOND QUENEAU EST UN AUTEUR FANTASTIQUE";
		
		//message
		System.out.println("Message clair: " + message);
		
		String cle = "ECRITURE";
		System.out.println("Clé: " + cle);
		//message chiffré
		String messageChiffre = Transposition.chiffrer(cle,message);
		System.out.println("Message chiffré:\n" + messageChiffre);
		
		//message déchiffré
		//String messageDechiffre = Permutation.dechiffrer(cle,messageChiffre);
		//System.out.println(messageDechiffre);
		
		String permut = Permutation.chiffrer(2,message);
		System.out.println(permut+" -> "+Permutation.dechiffrer(2,permut));
	}
}
