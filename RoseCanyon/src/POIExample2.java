import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class POIExample2 {

	public static void main(String[] args) throws Exception {
		InputStream inp = new FileInputStream("./nineonesix.xlsx");

		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);

		for (int j = 0; j < sheet.getLastRowNum() + 1; j++) {
			Row row = sheet.getRow(j);

			Cell cell = row.getCell(1);
			System.out.println(cell.getStringCellValue());

		}
	}
}
