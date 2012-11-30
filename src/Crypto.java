import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Crypto
{
	public static void main(String[] args)
	{
        //Scanner in = new Scanner(System.in);
        //System.out.println("Veuillez saisir un alphabet :");
        //String alphabet = in.next();

        String fichier = "texte.txt";
        
        Texte t = new Texte(fichier);
        Map<String,Double> list = t.frequences();
       
       for (Map.Entry<String,Double> entry : list.entrySet())
       {
           System.out.println(entry.getKey()+" :    "+entry.getValue());
       }
	}
}
