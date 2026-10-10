package swaglabs_exceldata;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class LoginMultipleCredentialsData {

	// reading dta from excel
	public Object[][] getLoginData(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/swag_labs_testdata/swag_labs_testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);

		int rowCount = sh.getLastRowNum();
		System.out.println(rowCount);

		int cellCount = sh.getRow(0).getPhysicalNumberOfCells();

		Object[][] obj = new Object[rowCount][cellCount];
		for (int row = 1; row <= rowCount; row++) {
			for (int col = 0; col < cellCount; col++) {
				if (sh.getRow(row).getCell(col) != null) {
					obj[row - 1][col] = sh.getRow(row).getCell(col).getStringCellValue();
				} else {
					obj[row - 1][col] = "";
				}
			}
		}
		wb.close();
		fis.close();
		return obj;
	}

	@DataProvider(name = "validLogin")
	public Object[][] validLogin() throws EncryptedDocumentException, IOException {
		return getLoginData("validLoginData");
	}

	@DataProvider(name = "invalidLogin")
	public Object[][] invalidLogin() throws EncryptedDocumentException, IOException {
		return getLoginData("invalidLoginData");
	}

}
