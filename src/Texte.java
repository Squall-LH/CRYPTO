import java.util.Map;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Collection;

public class Texte
{
    private String contenu = "";
    public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
		'V', 'W', 'X', 'Y', 'Z' };
    
    public Texte(String titreFichier)
    { 
        try
        {
            InputStreamReader fichier = new InputStreamReader(new FileInputStream(titreFichier)); 
            int line = fichier.read();
            while (line != -1)
            {
                //System.out.print((char) line);
                contenu += (char) line;
                line = fichier.read();
            }
            System.out.println("");
        } catch (Exception e)
        {
        	System.out.println("ERROR: File not found...");
            contenu = "texte de remplacement (erreur lors de l'ouverture du fichier)";
        }
    }
    
    public Texte() {
    	
    }

    public void setContenu(String c) {
    	contenu = c;
    }
    
    public String getContent() {
    	return contenu;
    }
    
   public Map<String,Double> frequences()
   {
       Map<String,Double> freq = new HashMap<String,Double>();
      
       for(char a : alphabet) {
    	   double count = 0.0;
    	   for(int i = 0; i < contenu.length(); i++) {
    		   if(contenu.charAt(i) == a) {
    			   count++;
    		   }
    	   }
    	   freq.put(String.valueOf(a), count);
    	   //System.out.println("key: " + String.valueOf(a) + " freq: " + count);
       }
       
       return freq;
   }
   
   public int length() {
	   return contenu.length();
   }
   
   public char charAt(int i) {
	   return contenu.charAt(i);
   }
}
