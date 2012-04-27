package com.noi.utility.html;

import java.util.ArrayList;
import java.util.List;

import com.noi.utility.reflection.ReflectionUtils;

public class HtmlBody {
	private List<HtmlTable> tables;

	public HtmlBody() {
		super();
		tables = new ArrayList<HtmlTable>();
	}

	/**
	 * @return Returns the tables.
	 */
	public List<HtmlTable> getTables() {
		return tables;
	}

	/**
	 * @param tables The tables to set.
	 */
	public void setTables(List<HtmlTable> tables) {
		this.tables = tables;
	}
	
	public void addTable(HtmlTable table)
	{
		this.tables.add(table);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return ReflectionUtils.toBeanString(this);
	}
	

}
