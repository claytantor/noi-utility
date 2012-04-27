package com.noi.utility.data.excel;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;

import com.noi.utility.reflection.ReflectionUtils;

public class ExcelWorkbookModelFactory {
	static Logger logger = Logger.getLogger(ExcelWorkbookModelFactory.class);

	public static Workbook makeWorkbookFromXml(InputStream ios) throws Exception
	{
		Workbook book = null;
		
		try {
			  
			  Digester digester = new Digester();
	          digester.setValidating( false );

	          digester.addObjectCreate( "Workbook", Workbook.class );
	          digester.addObjectCreate( "Workbook/Worksheet", Worksheet.class );
	          digester.addCallMethod("Workbook/Worksheet", "setName", 1);
	          digester.addCallParam("Workbook/Worksheet", 0, "ss:Name");
	          digester.addSetNext("Workbook/Worksheet", "addWorksheet");
	          
	          digester.addObjectCreate( "Workbook/Worksheet/Table/Row", RowModel.class );
	          digester.addSetNext("Workbook/Worksheet/Table/Row", "addRow"); 
	          
	          digester.addObjectCreate( "Workbook/Worksheet/Table/Row/Cell", Cell.class );
	          digester.addBeanPropertySetter( "Workbook/Worksheet/Table/Row/Cell/Data", "data" );  
	          digester.addCallMethod("Workbook/Worksheet/Table/Row/Cell/Data", "setType", 1);
	          digester.addCallParam("Workbook/Worksheet/Table/Row/Cell/Data", 0, "ss:Type");	          	          
	          digester.addSetNext("Workbook/Worksheet/Table/Row/Cell", "addCell");
	                  	          	          
	          	          	          
	          
	          book  
	          	= (Workbook)digester.parse( ios );
	          
       
	          
	     } catch( Exception exc ) {
	        logger.debug("problem parsing",exc);
	        throw exc;
	     } 
		
		
	     return book;
	}
	
	public static void writeFieldsFromWorksheet(
			Worksheet sheet, 
			String[][] fieldmap, 
			int dataColumnIndex,
			Object bean)
	{
		//iterate throw the fieldmap
		for (int i = 0; i < fieldmap.length; i++) {
			String[] fieldMapRow = fieldmap[i];
//			for every field use reflection to set the value of the field
			
			//get the value row from the workbook
			RowModel row = sheet.getRowByFieldValue(0, fieldMapRow[1]);
			Cell value = row.getFieldByIndex(dataColumnIndex);
		
			try {
				//format it
				Class formatClass = Class.forName(fieldMapRow[2]);
				CellFormatter formatter = (CellFormatter)formatClass.newInstance();
				Object fomatValue = formatter.getField(value.getData());
				
				//get the set mthod for the bean property
				ReflectionUtils.setMethodFromPropertyNameAndObject(
						fieldMapRow[0], fomatValue.toString(), bean);
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
