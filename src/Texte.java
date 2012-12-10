import java.util.Map;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class Texte
{
    private String contenu = "";
//    private double freqMin = 0.005;
    private static double freqMin = 0.0;
    
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
            contenu = "TEXTE DE REMPLACEMENT (ERREUR LORS DE L'OUVERTURE DU FICHIER)";
        }
        contenu = contenu.toUpperCase();
    }

   public Map<String,Double> frequences()
   {
   	System.out.println("Génération des fréquences du corpus");
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
   
   public static Map<String,Double> frequences(String contenu)
   {
   	System.out.println("Génération des fréquences du message");
		Map<String,Double> list = new HashMap<String,Double>();
       
       for (int i = 0; i < contenu.length(); i++)
       {
           String c = contenu.charAt(i)+"";
           if (c.matches("[A-Z]"))
               list.put(c,0.0);
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
}
