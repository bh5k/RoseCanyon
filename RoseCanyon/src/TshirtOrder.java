import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class TshirtOrder {

	public static void main(String[] args) {
		try {
			InputStream inp = new FileInputStream("./zoo.xlsx");
		    
		    Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheet("Sheet3");
		    int i = 1;
		    Row rowInput = sheet.getRow(0);
		    
		    
		    Map<String, Integer> names = new HashMap<String, Integer>();
		    
		    while(rowInput != null) {
		    	Cell cellFirst = rowInput.getCell(1); 
		    	
		    	Integer count = names.get(cellFirst.getStringCellValue());
		    	
		    	System.out.println(cellFirst.getStringCellValue());
		    	
		    	rowInput = sheet.getRow(++i);
		    	
		    }
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
