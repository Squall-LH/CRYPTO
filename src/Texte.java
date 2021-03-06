import java.util.Map;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class Texte
{
    private String contenu = "";
//    private double freqMin = 0.005;
    private static double freqMin = 0.0;
    
    public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
'V', 'W', 'X', 'Y', 'Z' };
    
    public Texte(String titreFichier)
    {
    	
		try
		{
			System.out.println("Lecture du fichier "+titreFichier);
			InputStreamReader fichier = new InputStreamReader(new FileInputStream(titreFichier)); 
			int line = fichier.read();
			while (line != -1)
			{
				contenu += (char) line;
				line = fichier.read();
			}
		} catch (Exception e)
		{
			System.out.println("Erreur lors de l'ouverture du fichier");
			contenu = "TEXTE DE REMPLACEMENT (ERREUR LORS DE L'OUVERTURE DU FICHIER)";
		}
		contenu = contenu.toUpperCase();
    }

   public Map<String,Double> frequences()
   {
		return Texte.frequences(contenu);
   }
   
   public static Map<String,Double> frequences(String contenu)
   {
   	System.out.println("Génération des fréquences");
       Map<String,Double> list = new HashMap<String,Double>();
       
       String c0 = "";
       String c1 = "";
       String c2 = "";
       String tmpBigramme = "";
       String tmpTrigramme = "";
       for (int i = 0; i < contenu.length(); i++)
       {
           c0 = c1;
           c1 = c2;
           c2 = contenu.charAt(i)+"";
           tmpBigramme = c1+c2;
           tmpTrigramme = c0+c1+c2;
           
           if (c2.matches("[A-Z]"))
               list.put(c2,0.0);

           if (tmpBigramme.matches("[A-Z]+") && tmpBigramme.length() == 2)
               list.put(tmpBigramme,0.0);
           if (tmpTrigramme.matches("[A-Z]+") && tmpTrigramme.length() == 3)
               list.put(tmpTrigramme,0.0);
       }
       
       boolean search;
       int debut;

       int count;
       for (Map.Entry<String,Double> entry : list.entrySet())
       {
           search = true;
           debut = 0;
           count = 0;
           while(search)
           {
               int index = contenu.indexOf(entry.getKey(),debut);
              if (index >= 0)
              {
                  debut = index+1; 
                  count++;
              } else
                  search = false;
           }
           double frequence = (double) count/((contenu.length()-1)/entry.getKey().length());
           list.put(entry.getKey(),frequence);
       }

       Map<String,Double> finalList = new HashMap<String,Double>();
       
       for (Map.Entry<String,Double> entry : list.entrySet())
       {
           if (entry.getValue() >= freqMin)
               finalList.put(entry.getKey(),entry.getValue());
       }

       return finalList;
   }
   
   public String getTexte()
   {
   	return contenu;
   }
   
   public void setContenu(String c) {
   	contenu = c;
   }
   
   public String getContent() {
   	return contenu;
   }
   
  public static Map<String,Double> count(String contenu)
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
