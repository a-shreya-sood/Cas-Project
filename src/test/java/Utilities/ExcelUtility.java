package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelUtility {	
	static FileInputStream file;
	static XSSFWorkbook book;
	static  XSSFSheet sheet ;
	static XSSFRow row;
	static FileOutputStream fileOutput;
	public static void write(String sheetName,int rownum,int colnum,String data)throws IOException 
	{
		
		//Opening the excel file
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\testData\\writeData.xlsx");
		XSSFWorkbook book=new XSSFWorkbook(file); 

		//Creating the sheet if it does not exist
		if(book.getSheetIndex(sheetName)==-1) {
			book.createSheet(sheetName);              
		}
		XSSFSheet sheet=book.getSheet(sheetName);
		if(sheet.getRow(rownum)==null)
		{
			sheet.createRow(rownum);
		}
		XSSFRow row =sheet.getRow(rownum);   
		
		
	  // Creating cell
		XSSFCell cell=row.createCell(colnum); 
		//Setting the data in the column
		cell.setCellValue(data); 
		
		//writing into excel
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\testData\\writeData.xlsx");
		book.write(fo);
		
		//closing
		book.close();
		file.close();
		fo.flush();
		fo.close();

	}  
}