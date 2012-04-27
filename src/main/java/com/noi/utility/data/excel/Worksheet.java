package com.noi.utility.data.excel;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Worksheet {
	
	private static final Log logger = LogFactory.getLog(Worksheet.class);
	
	private String name;
	private GridModel grid;
	
	public RowModel getRowByFieldValue(int index, String value)
	{
		List<RowModel> rows = grid.getRows();
		
		//ignore the first row
		int rowindex = 0;
		for (RowModel row : rows) {			
			if(rowindex > 0)
			{	
				Cell cell = row.getFieldByIndex(index);
				if(cell.getData() != null && cell.getData().equals(value))
				{
					return row;
				}

			}
			
			rowindex++;
		}
		return null;
	}
	
	public Worksheet() {
		super();
		this.grid = new GridModel();
	}
	
	public GridModel getGrid() {
		return grid;
	}
	
	public void setGrid(GridModel grid) {
		this.grid = grid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addRow(RowModel row)
	{
		this.grid.addRow(row);
	}

}
