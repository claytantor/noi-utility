package com.noi.utility.data;

import java.util.ArrayList;
import java.util.List;

/**
 * this class makes it easy to scrape a certain number 
 * of values trailing after a keyword
 * @author clay
 *
 */
public class FilteredTrailingList {

	List<String> data;

	public FilteredTrailingList() {
		super();
		data = new ArrayList<String>();
	}

	public void add(String value)
	{
		this.data.add(value);
	}
	
	public List<String> getFilteredData(String key, int trailing)
	{
		List<String> filtered = new ArrayList<String>();
		int count = 0;
		boolean toggle = false;
		for (String field: this.data) {
			if(field.equals(key))
			{
				toggle = true;
			}
			if(toggle)
			{
				filtered.add(field);
				count++;
			}				
			if(count == trailing)
				break;
		}
		return filtered;
	}
}
