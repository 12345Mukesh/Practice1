package GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	
	public String readdatafromexcel(String Sheetname, int row, int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\Mukesh\\SampleProject\\src\\test\\resources\\ExcelUtility.xlsx");
		
		Workbook wb= WorkbookFactory.create(fis);
	String data=wb.getSheet(Sheetname).getRow(row).getCell(cell).toString();
	return data;
			
	}
	
	public void writedataintoexcel(String Sheetname, int row, int cell, String data) throws EncryptedDocumentException, IOException
	{
FileInputStream fis= new FileInputStream("C:\\Users\\Mukesh\\SampleProject\\src\\test\\resources\\ExcelUtility.xlsx");
		
		Workbook wb= WorkbookFactory.create(fis);
		wb.getSheet(Sheetname).getRow(row).createCell(cell).setCellValue(data);
		
		
		FileOutputStream fos= new FileOutputStream("C:\\Users\\Mukesh\\SampleProject\\src\\test\\resources\\ExcelUtility.xlsx");
		wb.write(fos);
		
		
		
		
	}

}
