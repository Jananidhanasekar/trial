package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	//XSSF is predefined class in appache poi  --- latest xsls
	//Hssf
	
	static XSSFWorkbook wb;
	static XSSFSheet sh1;

	
//	to import the file 
	
	public ExcelUtility(String excelpath)
	{
		try
		{
			File source = new File(excelpath);
			// to import the file in java FIS
			FileInputStream inputfile = new FileInputStream(source);
			//get into wrkbook 
			wb=new XSSFWorkbook(inputfile);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	// to retrive the data in excel , data will be always --- string format
	
	public String getCellData(String sheetname,int rownum , int colnum )
	{
		
		// first find the  sheet then identify the row and column to get the celldata
		sh1 = wb.getSheet(sheetname);
		
		String data = sh1.getRow(rownum).getCell(colnum).getStringCellValue();
		return data;
		
	}
	
	// to write the input to cell
	public void setCellData(String sheetname,int rownum , int colnum , String status )
	{
		//first identify the sheet,then rownum and column  and  write the input to the cell 
		sh1 = wb.getSheet(sheetname);
		
		
		
		
		//if we are not interacting with cell , cell doesnot exist ,First check the cell is active or not 
		
		
		Row r1 = sh1.getRow(rownum);
		Cell c1 = r1.getCell(colnum);
		 // check the cell is active or not 
		
		if(c1==null)
		{
			
			//create a cell and set the status
			c1=r1.createCell(colnum);
			c1.setCellValue(status);
		}
		else
		{
			c1.setCellValue(status);
		}
	}


	
	
	
	
	// to get the rowcount , identify the sheetname
	
	public int getNumberofrows(String sheetname)
	{
		
	sh1=wb.getSheet(sheetname);
	
	// getlastrowNum always return the index value , index starts from 0 ie we are adding +1 (excel starts from 1)
	int totalrows = sh1.getLastRowNum()+1;
	System.out.println("Total colums are"+totalrows);
	return totalrows;
	
	
}
	
	//get the column count , identify the sheetname and row
	
	
	public int getNumberofcols(String sheetname)
	{
		
	sh1=wb.getSheet(sheetname);
	int totalcols = sh1.getRow(0).getLastCellNum();
	
	// getlastcellNum always start count from 1 , so no need to add 1
	System.out.println("Total colums are"+totalcols);
	return totalcols;
	
	
}
	
	//After writing the file , export the file to its location 
	
	public static void writeAndSaveExcel(String excelpath )
	{
	//excel path tells the destination path 
		
		try
		{
			File destination = new File(excelpath);
			FileOutputStream outputfile = new FileOutputStream(destination);
			//all info are stored in wrkbook
			wb.write(outputfile);
			outputfile.flush();
			outputfile.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}

