package com.noi.utility.data.excel;

import java.util.ArrayList;
import java.util.List;

public class Workbook {
	//private Map<String, Worksheet> worksheets;
	private List<Worksheet> worksheets;
	
	public Workbook() {
		super();
		//this.worksheets = new HashMap<String, Worksheet>();
		this.worksheets = new ArrayList<Worksheet>();
		
	}
	
	public void addWorksheet(Worksheet sheet)
	{
		this.worksheets.add(sheet);
	}
	
	public Worksheet getWorksheetByName(String name)
	{
		//return this.worksheets.get(name);
		for (Worksheet sheet: this.worksheets) {
			if(sheet.getName().equals(name))
				return sheet;
			
		}
		return null;
	}
	

}
