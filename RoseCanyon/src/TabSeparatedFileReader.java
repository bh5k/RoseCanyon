import java.io.BufferedReader;
import java.io.FileReader;

public class TabSeparatedFileReader {

	public static void main(String args[]) throws Exception {
		/**
		 * Source file to read data from.
		 */
		String dataFileName = "./orders.txt";

		/**
		 * Creating a buffered reader to read the file
		 */
		BufferedReader bReader = new BufferedReader(
				new FileReader(dataFileName));

		String line;

		/**
		 * Looping the read block until all lines in the file are read.
		 */
		while ((line = bReader.readLine()) != null) {

			/**
			 * Splitting the content of tabbed separated line
			 */
			String datavalue[] = line.split("\t");
			
			/**
			 * Printing the value read from file to the console
			 */
			System.out.println(datavalue[0] + "\t" + datavalue[1] 
					+ "\t" + datavalue[2] 
					+ "\t" + datavalue[3] 
					+ "\t" + datavalue[4] 
					+ "\t" + datavalue[5] 
					+ "\t" + datavalue[6] 
					+ "\t" + datavalue[7] 
					+ "\t" + datavalue[8] 
					+ "\t" + datavalue[9] 
					+ "\t" + datavalue[10] 
					+ "\t" + datavalue[11]
					+ "\t" + datavalue[12] 
					+ "\t" + datavalue[13] 
					+ "\t" + datavalue[14] 
					+ "\t" + datavalue[15]);
		}
		bReader.close();
	}
}