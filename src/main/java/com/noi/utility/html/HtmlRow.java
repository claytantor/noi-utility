package com.noi.utility.html;

import java.util.ArrayList;
import java.util.List;

import com.noi.utility.reflection.ReflectionUtils;


public class HtmlRow {
	private List<HtmlColumn> columns;
	
	

	public HtmlRow() {
		super();
		this.columns = new ArrayList<HtmlColumn>();
	}

	/**
	 * @return Returns the columns.
	 */
	public List<HtmlColumn> getColumns() {
		return columns;
	}

	/**
	 * @param columns The columns to set.
	 */
	public void setColumns(List<HtmlColumn> columns) {
		this.columns = columns;
	}
	
	public void addColumn(HtmlColumn column)
	{
		this.columns.add(column);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return ReflectionUtils.toBeanString(this);
	}
		
	

}
