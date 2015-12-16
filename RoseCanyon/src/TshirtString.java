import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class TshirtString {

	
	public static void main(String args[]) throws Exception {
		Map<String, Integer> shirts = new HashMap<>();
		
		FileInputStream fstream = new FileInputStream("zoo.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		  //System.out.println (strLine);
		  
		  Integer count = shirts.get(strLine);
		  
		  if(count == null) {
			  count = new Integer(1);
		  }
		  else {
			  count ++;
		  }
		  shirts.put(strLine, count);
		}
		
		System.out.println(shirts.values());

		//Close the input stream
		br.close();
	}
	
}
