import java.util.Map;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Collection;

public class Texte
{
    private String contenu = "";
    
    public Texte(String titreFichier)
    {
        try
        {
            InputStreamReader fichier = new InputStreamReader(new FileInputStream(titreFichier)); 
            int line = fichier.read();
            while (line != -1)
            {
                System.out.print((char) line);
                contenu += (char) line;
                line = fichier.read();
            }
            System.out.println("");
        } catch (Exception e)
        {
            contenu = "texte de remplacement (erreur lors de l'ouverture du fichier)";
        }
    }

   public Map<String,Double> frequences()
   {
       Map<String,Double> list = new HashMap<String,Double>();
       
       String[] min = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","u","v","w","x","y","z"};
       String[] maj = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","U","V","W","X","Y","Z"};
       String[] bigramme = {"le","Le","La","je","ab","cd","de"};
       String[] trigramme = {"les","Bob","oui","non","des"};
       for (int i = 0; i < min.length; i++)
       {
           list.put(min[i],0.0);
       }
       for (int i = 0; i < maj.length; i++)
       {
           list.put(maj[i],0.0);
       }
       for (int i = 0; i < bigramme.length; i++)
       {
           list.put(bigramme[i],0.0);
       }
       for (int i = 0; i < trigramme.length; i++)
       {
           list.put(trigramme[i],0.0);
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
           double frequence = (double) count/contenu.length();
           list.put(entry.getKey(),frequence);
       }

       return list;
   }
}
