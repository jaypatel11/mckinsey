/*Create an app that will be executed in terminal or from the command line. 
The app should accept a hashtag as an argument and should only print out unique urls found in the 100 
most recent tweets that matched the hashtag. */
import java.net.*;
import java.io.*;
import java.util.*;
import net.sf.json.*;

/**
 *
 * @author Jay
 */

 
public class urlTweet
{
	/**
     * @param args the command line arguments
     */
  public static void main(String[] args) throws Exception 
  {
      if ( args.length == 0)
      {
        System.out.println("Please enter A HASHTAG");														// No input
        return;
      }
      
      if ( args.length > 1 )
      {
          System.out.println("Enter only one hashtag");														// More than one input
          return;
      }
      
      String input = args[0];
      input = input.replace("#","");																		//Remove hashtag if present in the input
      URL tweet_url = new URL("http://search.twitter.com/search.json?q="+input+"&rpp=100&result_type=recent");
      
      BufferedReader br = new BufferedReader(new InputStreamReader(tweet_url.openStream()));			
      String out = "";
      String jsonString = "";
      
	  while( (out = br.readLine()) != null )
      {
          jsonString = out;																					//Gets the jsonstring to be processed
      }
      
	  br.close();
      //System.out.println(jsonString);
      JSONObject tweet = new JSONObject();
      JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonString);
      JSONArray results = json.getJSONArray("results");														//Extracts all the results in the form of an array
	  
     // System.out.println(results);
	  int index = 0;                                                      
      String process = "";
      String http = "";
      String final_process = "";
      String final_http = "";
      
      Map <String,String> map= new HashMap ();

      for(int i=0; i < results.size();i++) 
      {  
            tweet = results.getJSONObject(i);  
             
            String text_result = results.getJSONObject(i).getString("text");
            //System.out.println(text_result);
           
            int start = text_result.indexOf("http");
            int end = text_result.indexOf(" ",start);
            
            if( end == -1)
            {
                end = text_result.length();
            }
            
           
            if (! (start == -1))
            {
                http = text_result.substring(start,end);								//Get link of format http(s)://t.co/X where X is alphanumeric character. 
				index = http.lastIndexOf("/");
                process = http.substring(index+1);										
                //System.out.println(process);
                if( !map.containsKey(process) )
                {
                    map.put(process, http);													//Store only unique URLS. Make X as the key
                }
                    
                
            }
            
      }
       
       Iterator it = map.entrySet().iterator();
       
       while (it.hasNext()) 															//Iterate the hashmap
       {
           final_process=""; 
           Map.Entry entry = (Map.Entry)it.next();
           process = (String)entry.getKey();
           http = (String)entry.getValue();		
            //System.out.println(http);
            
            for (int j = 0 ; j < process.length(); j++)									// The key i.e. X may contain non alphanumeric charcters. Trim them
            {
                if( Character.isLetterOrDigit(process.charAt(j)) )
                {
                    final_process += process.charAt(j);
                }
                else
                    break;
                
             }
                final_http = http.substring(0,index+1)+ final_process;
                
                System.out.println(final_http);											// Print the trimmed http.
                
        }     
       
 
  }     
}