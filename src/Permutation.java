import java.util.ArrayList;
import java.util.List;

public class Permutation
{
	public final static boolean printWorkSheet = true;
    
    /** Chiffrement par permutation : on prend un texte, le normalise (retrait d'espace/caractères non gérés, mise en majuscule de toutes les lettres) 
     *                                puis on le découpe en ligne de la longueur de la clé, qui forment ensuite un rectangle, chaque ligne étant superposé l'une
     *                                sur l'autre.
     *                                on créer en suite le message chiffré en lisant le rectangle par colonne, dans l'ordre indiqué par la clé.
     *                                La clé étant ici un mot, on en extrait un ordre en suivant l'ordre alphabétique des lettres du mots.
     *                                */
	public static String chiffrer(String cle, String chaine)
	{
        List<String> workSheet = new ArrayList<String>();
        StringBuffer ciphered = new StringBuffer();
        StringBuffer messageBuffer = new StringBuffer(chaine.replaceAll("\\s+", "").trim());
        System.out.println("Message en clair sans espace: " + messageBuffer);
        int keyLenght = cle.length();
        List<Integer> keyInt = new ArrayList<Integer>();
        
        StringBuffer key = new StringBuffer(cle);
        
        for(int j = 0; j < key.length(); j++) {
        	// index de la première lettre de key 
        	Integer index = 1;
        	char c  = key.charAt(j);
        	for(int i = 0; i < key.length(); i++) {
        		char c_next = key.charAt(i);
        		if(c > c_next) {
        			index++;
        		}
        	}
        	while(keyInt.contains(index)) {
        		index++;
        	}
        	keyInt.add(index);
        }
        
        System.out.println("Clé numérique: " + keyInt.toString());
        
        while(messageBuffer.length() > 0) {
        	String tmp;
        	try{
        		tmp = messageBuffer.substring(0, keyLenght);
        	}
        	catch(Exception e) {
        		tmp = messageBuffer.toString();
        		messageBuffer.setLength(0);
        	}
        	messageBuffer.delete(0, keyLenght);
        	if(printWorkSheet) {
        		System.out.println("workSheet: " + tmp);
        	}
        	workSheet.add(tmp.toString());
        }
        
        for(int i = 1; i < keyInt.size()+1; i++) {
        	int index = keyInt.indexOf(i);
        	StringBuffer column = new StringBuffer();
        	for(String line : workSheet) {
        		if(line.length() > index)
        			column.append(line.charAt(index));
        	}
        	ciphered.append(column);
        }
        
        return ciphered.toString();
	}
	
	public static String dechiffrer(String cle, String chaine)
	{
		return "bar";
	}
}
