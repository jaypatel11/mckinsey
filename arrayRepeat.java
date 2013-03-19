/*Given an array of integers, write some code to find all the integers that appear more than once in the array, 
sorted by which appears most often to least often (once) */

import java.util.*;

/**
 *
 * @author Jay
 */
public class arrayRepeat
{

    /**
     * @param args the command line arguments
     */
    public static void arrayCountandSort(int arr[])
    {
        if( arr.length == 0 )
        {
            System.out.println("Array empty");							//Empty array
            return;
        }
        
        Map<Integer,Integer> map = new HashMap();						// Hash Map to store frequencies of integers(unsorted)
        int old_count = 0;
        
        for (int i = 0; i < arr.length; i++)
        {
            if( !map.containsKey(arr[i]) )
            {
                map.put(arr[i],1);										// First entry of the given integere. Frequency is 1
            }
        
			else
            {
                old_count = map.get(arr[i]);
                map.put(arr[i], old_count+1);							// Entry present. Update the old frequency and increment by 1
                        
            }
                    
        }
        
        Map<Integer,Integer> sorted_map = sort_map(map);				// Function that sorts entries by their frequency
        
        Iterator iterator = sorted_map.keySet().iterator();  
    
		while ( iterator.hasNext() ) 
        {  
            String key = iterator.next().toString();  					//Iterate through the sorted entries and print by most to least occurring
            System.out.println(key);  
        }  
   
    }
    
    public static Map<Integer,Integer> sort_map(Map map)
    {
        List list = new LinkedList(map.entrySet());
        
		Collections.sort(list, new Comparator(){
            public int compare(Object o1, Object o2) {
                        return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());			// Sort entries by frequency
        }});
        
        Map sortedMap = new LinkedHashMap();							// Preserve order of insertion
      
        for (Iterator it = list.iterator(); it.hasNext();) 
        {
            Map.Entry entry = (Map.Entry) it.next();
            sortedMap.put(entry.getKey(), entry.getValue());			// Insert entries sorted by frequency to the LinkedHashMap
        }
		
		return sortedMap;
            
    }
	
    public static void main(String[] args)
	{
        // TODO code application logic here
        int a[]= {8,1,1,8,8,3,3,3,3,2,1,7,7,7,8,8,9};
        arrayCountandSort(a);
              
    }
}
