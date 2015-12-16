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

public class POIExample {

	public static void main(String[] args) {
		try {
			InputStream inp = new FileInputStream("./nineonesix.xlsx");

			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			int i = 1;
			Row rowInput = sheet.getRow(1);

			FileOutputStream fileOut = null;

			Map<String, Integer> names = new HashMap<String, Integer>();

			while (rowInput != null) {
				Cell cellFirst = rowInput.getCell(1); // first name
				Cell cellLast = rowInput.getCell(2); // first name
				Cell cellDate = rowInput.getCell(3);

				Integer count = names.get(cellFirst.getStringCellValue()
						+ cellLast.getStringCellValue());

				
				String filename = "./orders/" + cellFirst + " " + cellLast
						+ " - rose-canyon.xls";

				if (count != null) {
					count++;
					filename = "./orders/" + cellFirst + " " + cellLast + " - "
							+ count + " - rose-canyon.xls";
				} else {
					count = 0;
				}
				
				if(cellFirst.getStringCellValue().equalsIgnoreCase("tami")) {
					throw new RuntimeException("WTF!!!");
				}

				names.put(
						cellFirst.getStringCellValue()
								+ cellLast.getStringCellValue(), count);

				fileOut = new FileOutputStream(filename);

				InputStream inpOut = new FileInputStream(
						"./Rose-Canyon-Order-Form.xlsx");
				Workbook wbOut = WorkbookFactory.create(inpOut);

				Sheet sheetOut = wbOut.getSheetAt(0);
				Row rowOut = sheetOut.getRow(0);
				Cell name = rowOut.getCell(4);
				name.setCellType(Cell.CELL_TYPE_STRING);
				name.setCellValue(cellFirst + " " + cellLast);

				if (cellDate.getStringCellValue().startsWith("9/23")) {
					Row rowDate = sheetOut.getRow(10);
					Cell cellTownDate = rowDate.getCell(2);
					cellTownDate.setCellType(Cell.CELL_TYPE_STRING);
					cellTownDate.setCellValue("X");
				} else if (cellDate.getStringCellValue().startsWith("9/22")) {
					Row rowDate = sheetOut.getRow(8);
					Cell cellTownDate = rowDate.getCell(2);
					cellTownDate.setCellType(Cell.CELL_TYPE_STRING);
					cellTownDate.setCellValue("X");
				} else if (cellDate.getStringCellValue().startsWith("9/2")) {
					Row rowDate = sheetOut.getRow(6);
					Cell cellTownDate = rowDate.getCell(2);
					cellTownDate.setCellType(Cell.CELL_TYPE_STRING);
					cellTownDate.setCellValue("X");
				} else if (cellDate.getStringCellValue().startsWith("9/1")) {
					Row rowDate = sheetOut.getRow(4);
					Cell cellTownDate = rowDate.getCell(2);
					cellTownDate.setCellType(Cell.CELL_TYPE_STRING);
					cellTownDate.setCellValue("X");
				}

				Cell cellLunchOrder = rowInput.getCell(4);

				System.out.println(cellFirst.getStringCellValue()
						+ cellLast.getStringCellValue());

				System.out.println("building a :" + cellLunchOrder.getStringCellValue());
				
				if (cellLunchOrder.getStringCellValue().startsWith("Sand")) {
					buildSandwich(rowInput, sheetOut);
					buildRemainingOrder(rowInput, sheetOut);
				} else if (cellLunchOrder.getStringCellValue().startsWith(
						"Salad")) {
					buildSalad(rowInput, sheetOut);
					buildRemainingOrder(rowInput, sheetOut);
				}

				if (!cellLunchOrder.getStringCellValue().startsWith("n/a")) {
					wbOut.write(fileOut);
					fileOut.close();
				}

				rowInput = sheet.getRow(++i);
			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	private static void buildSalad(Row rowInput, Sheet sheetOut) {

		Cell inputSaladSize = rowInput.getCell(10);

		if (inputSaladSize.getStringCellValue().startsWith("Large")) {
			checkBox(sheetOut, 14, 12);
		} else if (inputSaladSize.getStringCellValue().startsWith("Small")) {
			checkBox(sheetOut, 16, 12);
		}

		Cell inputDressing = rowInput.getCell(11);

		if (inputDressing.getStringCellValue().startsWith("Blue")) {
			checkBox(sheetOut, 19, 12);
		} else if (inputDressing.getStringCellValue().startsWith("Ranch")) {
			checkBox(sheetOut, 21, 12);
		} else if (inputDressing.getStringCellValue().startsWith("Thous")) {
			checkBox(sheetOut, 23, 12);
		} else if (inputDressing.getStringCellValue().startsWith("Rasp")) {
			checkBox(sheetOut, 25, 12);
		} else if (inputDressing.getStringCellValue().startsWith("Ital")) {
			checkBox(sheetOut, 27, 12);
		} else if (inputDressing.getStringCellValue().startsWith("Fren")) {
			checkBox(sheetOut, 29, 12);
		}

	}

	private static void buildRemainingOrder(Row rowInput, Sheet sheetOut) {
		Cell inputSideSalad = rowInput.getCell(12);

		if (inputSideSalad.getStringCellValue().startsWith("Pota")) {
			checkBox(sheetOut, 56, 2);
		} else if (inputSideSalad.getStringCellValue().startsWith("Maca")) {
			checkBox(sheetOut, 56, 7);
		}

		Cell inputChips = rowInput.getCell(13);

		if (inputChips.getStringCellValue().startsWith("Class")) {
			checkBox(sheetOut, 59, 2);
		} else if (inputChips.getStringCellValue().startsWith("BBQ")) {
			checkBox(sheetOut, 61, 2);
		} else if (inputChips.getStringCellValue().startsWith("Sour")) {
			checkBox(sheetOut, 63, 2);
		} else if (inputChips.getStringCellValue().startsWith("Pre")) {
			checkBox(sheetOut, 65, 2);
		} else if (inputChips.getStringCellValue().startsWith("Dor")) {
			checkBox(sheetOut, 59, 7);
		} else if (inputChips.getStringCellValue().startsWith("Chee")) {
			checkBox(sheetOut, 61, 7);
		} else if (inputChips.getStringCellValue().startsWith("TGI")) {
			checkBox(sheetOut, 63, 7);
		} else if (inputChips.getStringCellValue().startsWith("Sun")) {
			checkBox(sheetOut, 65, 7);
		}

		Cell inputCookie = rowInput.getCell(14);

		if (inputCookie.getStringCellValue().startsWith("Choc")) {
			checkBox(sheetOut, 68, 2);
		} else if (inputCookie.getStringCellValue().startsWith("Suga")) {
			checkBox(sheetOut, 68, 7);
		}

		Cell inputDrink = rowInput.getCell(15);

		if (inputDrink.getStringCellValue().startsWith("Coke")) {
			checkBox(sheetOut, 71, 2);
		} else if (inputDrink.getStringCellValue().startsWith("Diet Co")) {
			checkBox(sheetOut, 73, 2);
		} else if (inputDrink.getStringCellValue().startsWith("Spri")) {
			checkBox(sheetOut, 75, 2);
		} else if (inputDrink.getStringCellValue().startsWith("Dr")) {
			checkBox(sheetOut, 71, 7);
		} else if (inputDrink.getStringCellValue().startsWith("Diet Dr")) {
			checkBox(sheetOut, 73, 7);
		} else if (inputDrink.getStringCellValue().startsWith("Water")) {
			checkBox(sheetOut, 75, 7);
		}
	}

	private static void buildSandwich(Row rowInput, Sheet sheetOut) {

		Cell inputSandSize = rowInput.getCell(5);
		
		System.out.println("Type of sando: " + inputSandSize);

		if(inputSandSize == null) {
			checkBox(sheetOut, 14, 2);
		}
		else if (inputSandSize.getStringCellValue().startsWith("Whol")) {
			checkBox(sheetOut, 14, 2);
		} else {
			checkBox(sheetOut, 14, 7);
		}

		Cell inputBread = rowInput.getCell(6);
		
		if(inputBread == null) {
			checkBox(sheetOut, 21, 2); //they get wheat
		} else if (inputBread.getStringCellValue().startsWith("White")) {
			checkBox(sheetOut, 17, 2);
		} else if (inputBread.getStringCellValue().startsWith("Sour")) {
			checkBox(sheetOut, 19, 2);
		} else if (inputBread.getStringCellValue().startsWith("Wheat")) {
			checkBox(sheetOut, 21, 2);
		} else if (inputBread.getStringCellValue().startsWith("Honey")) {
			checkBox(sheetOut, 17, 7);
		} else if (inputBread.getStringCellValue().startsWith("9")) {
			checkBox(sheetOut, 19, 7);
		} else if (inputBread.getStringCellValue().startsWith("Marble")) {
			checkBox(sheetOut, 21, 7);
		}

		Cell inputMeat = rowInput.getCell(7);
		StringTokenizer tokeMeat = new StringTokenizer(
				inputMeat.getStringCellValue());

		while (tokeMeat.hasMoreTokens()) {
			String meat = tokeMeat.nextToken();
			if (meat.startsWith("Turkey")) {
				checkBox(sheetOut, 25, 2);
			} else if (meat.startsWith("Ham")) {
				checkBox(sheetOut, 27, 2);
			} else if (meat.startsWith("Roast")) {
				checkBox(sheetOut, 29, 2);
			} else if (meat.startsWith("Past")) {
				checkBox(sheetOut, 25, 7);
			} else if (meat.startsWith("Chick")) {
				checkBox(sheetOut, 27, 7);
			} else if (meat.startsWith("Tuna")) {
				checkBox(sheetOut, 29, 7);
			}
		}

		Cell inputCheese = rowInput.getCell(8);
		StringTokenizer tokeCheese = new StringTokenizer(
				inputCheese.getStringCellValue());

		while (tokeCheese.hasMoreTokens()) {
			String cheese = tokeCheese.nextToken();
			if (cheese.startsWith("Swiss")) {
				checkBox(sheetOut, 33, 2);
			} else if (cheese.startsWith("Amer")) {
				checkBox(sheetOut, 35, 2);
			} else if (cheese.startsWith("Provo")) {
				checkBox(sheetOut, 33, 7);
			} else if (cheese.startsWith("Muen")) {
				checkBox(sheetOut, 35, 7);
			}
		}

		Cell inputToppings = rowInput.getCell(9);
		if (inputToppings != null) {
			StringTokenizer tokeToppings = new StringTokenizer(
					inputToppings.getStringCellValue());

			while (tokeToppings.hasMoreTokens()) {
				String toppings = tokeToppings.nextToken();
				if (toppings.startsWith("Mayo")) {
					checkBox(sheetOut, 38, 2);
				} else if (toppings.startsWith("Mir")) {
					checkBox(sheetOut, 40, 2);
				} else if (toppings.startsWith("Must")) {
					checkBox(sheetOut, 42, 2);
				} else if (toppings.startsWith("Spic")) {
					checkBox(sheetOut, 44, 2);
				} else if (toppings.startsWith("Horse")) {
					checkBox(sheetOut, 46, 2);
				} else if (toppings.startsWith("Cran")) {
					checkBox(sheetOut, 48, 2);
				} else if (toppings.startsWith("Lett")) {
					checkBox(sheetOut, 50, 2);
				} else if (toppings.startsWith("Oni")) {
					checkBox(sheetOut, 52, 2);
				} else if (toppings.startsWith("Toma")) {
					checkBox(sheetOut, 38, 7);
				} else if (toppings.startsWith("Green")) {
					checkBox(sheetOut, 38, 7);
				} else if (toppings.startsWith("Toma")) {
					checkBox(sheetOut, 40, 7);
				} else if (toppings.startsWith("Cuc")) {
					checkBox(sheetOut, 42, 7);
				} else if (toppings.startsWith("Pick")) {
					checkBox(sheetOut, 44, 7);
				} else if (toppings.startsWith("Yell")) {
					checkBox(sheetOut, 46, 7);
				} else if (toppings.startsWith("Spro")) {
					checkBox(sheetOut, 48, 7);
				} else if (toppings.startsWith("Jal")) {
					checkBox(sheetOut, 50, 7);
				}
			}
		}

	}

	private static void checkBox(Sheet sheetOut, int rowVal, int cellVal) {
		Row row = sheetOut.getRow(rowVal);
		Cell cell = row.getCell(cellVal);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("X");
	}

}
