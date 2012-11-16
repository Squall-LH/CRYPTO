public class Crypto
{
	public static void main(String[] args)
	{
		String message = "RAYMOND QUENEAU EST UN AUTEUR FANTASTIQUE";
		
		//message
		System.out.println(message);
		
		String cle = "ECRITURE";
		
		//message chiffré
		String messageChiffre = Permutation.chiffrer(cle,message);
		System.out.println(messageChiffre);
		
		//message déchiffré
		String messageDechiffre = Permutation.dechiffrer(cle,messageChiffre);
		System.out.println(messageDechiffre);
	}
}
