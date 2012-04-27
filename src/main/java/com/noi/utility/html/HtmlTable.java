package com.noi.utility.html;

import java.util.ArrayList;
import java.util.List;

import com.noi.utility.reflection.ReflectionUtils;

public class HtmlTable {
	private List<HtmlRow> rows;
	

	public HtmlTable() {
		super();
		rows = new ArrayList<HtmlRow>();
	}

	/**
	 * @return Returns the rows.
	 */
	public List<HtmlRow> getRows() {
		return rows;
	}

	/**
	 * @param rows The rows to set.
	 */
	public void setRows(List<HtmlRow> rows) {
		this.rows = rows;
	}
	
	public void addRow(HtmlRow row)
	{
		this.rows.add(row);
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return ReflectionUtils.toBeanString(this);
	}
	

}
