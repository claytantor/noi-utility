package com.noi.utility.data.excel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author cgraham
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RowModel implements Comparable, Serializable {
	private Map fieldIndexMap;
	private Map fieldNameMap;
	private Map fieldNameIndexMap;
	private Map fieldIndexNameMap;
	private int columnIndex;
	
	
	public RowModel()
	{
		this.fieldIndexMap = new LinkedHashMap();		
		this.fieldNameMap = new LinkedHashMap();	
		this.fieldNameIndexMap = new LinkedHashMap();	
		this.fieldIndexNameMap = new LinkedHashMap();	
		this.columnIndex = 0;
		
		
	}
	
	public void addField(int index, String fieldName, Object value)
	{
		
		if(value == null)
			value = new String();
		fieldIndexMap.put(new Integer(index), value);
		fieldNameMap.put(fieldName, value);
		fieldIndexNameMap.put(new Integer(index), fieldName);
		fieldNameIndexMap.put(fieldName,new Integer(index));
		
	}
	
	public void addCell(Cell value)
	{
		
		if(value == null)
			value = new Cell();
		
		Integer col = new Integer(columnIndex);
		fieldIndexMap.put(col, value);
		fieldNameMap.put(col.toString(), value);
		fieldIndexNameMap.put(col, col.toString());
		fieldNameIndexMap.put(col.toString(),col);
		columnIndex++;
		
	}
	
	public List<Cell> getCells()
	{
		List<Cell> cells = new LinkedList<Cell>();
		Collection values = fieldIndexMap.values();
		for (Object object : values) {
			cells.add((Cell)object);
		}
		return cells;
	}
	
	
	public Cell getFieldByIndex(int index)
	{
		if(index <= fieldIndexMap.size())
			return (Cell)fieldIndexMap.get(new Integer(index));
		else
			return null;
	}
	
	public Object getFieldByName(String fieldName)
	{
		return fieldNameMap.get(fieldName);
	}
	
	public void setFieldByName(String fieldName, Object o)
	{
		fieldNameMap.put(fieldName, o);
		Integer i = (Integer)fieldNameIndexMap.get(fieldName);
		fieldIndexMap.put(i, o);
	}
	
	public void setFieldByIndex(int index, Object o)
	{
		fieldIndexMap.put(new Integer(index), o);
		String s = (String)fieldIndexNameMap.get(new Integer(index));
		fieldNameMap.put(s, o);
	}
	
	public Map getIndexedFields()
	{
		return this.fieldIndexMap;
	}
	
	public Map getFieldNameMap()
	{
		return this.fieldNameMap;
	}	
	

	public String[] getFieldNames() {
		Map fields = this.getFieldNameMap();
		
		String[] keys = null;
		int index = 0;
		if(fields.keySet().size()>0)
		{
			Iterator ikeys = fields.keySet().iterator();
			keys = new String[fields.keySet().size()];
		
			while(ikeys.hasNext())
			{
				String name = (String)ikeys.next();
				keys[index] = name;
				index++;
			}
		}

		return keys;
	}	
	
	public int getFieldCount()
	{
		return this.fieldIndexMap.size();
	}
		

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		Object o1 = this.getFieldByName(GridModel.BUSINESS_OBJECT_ID);
		Integer i1 = new Integer(o1.toString());
		RowModel row2 = (RowModel)arg0;
		Object o2 = row2.getFieldByName(GridModel.BUSINESS_OBJECT_ID);
		Integer i2 = new Integer(o2.toString());
		return i1.compareTo(i2);
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		try{
			String[] fieldnames = getFieldNames(); 
			buf.append("ROW field name count:"+fieldnames.length+" grid columns:"+getFieldCount()+"\n");
			for(int i=1;i<=getFieldCount();i++)
			{
				Object o = getFieldByName(fieldnames[i-1]);
				if(o != null)
					buf.append("\tcol name:"+fieldnames[i-1]+" value:"+o.toString()+" class:"+o.getClass().getName()+"\n");		
			}		
		} 
		catch (Exception e)
		{
			buf.append(super.toString());
		}
		return buf.toString();
		
	}

	/**
	 * @return Returns the fieldIndexMap.
	 */
	public Map getFieldIndexMap() {
		return fieldIndexMap;
	}
	/**
	 * @param fieldIndexMap The fieldIndexMap to set.
	 */
	public void setFieldIndexMap(Map fieldIndexMap) {
		this.fieldIndexMap = fieldIndexMap;
	}
	/**
	 * @return Returns the fieldIndexNameMap.
	 */
	public Map getFieldIndexNameMap() {
		return fieldIndexNameMap;
	}
	/**
	 * @param fieldIndexNameMap The fieldIndexNameMap to set.
	 */
	public void setFieldIndexNameMap(Map fieldIndexNameMap) {
		this.fieldIndexNameMap = fieldIndexNameMap;
	}
	/**
	 * @return Returns the fieldNameIndexMap.
	 */
	public Map getFieldNameIndexMap() {
		return fieldNameIndexMap;
	}
	/**
	 * @param fieldNameIndexMap The fieldNameIndexMap to set.
	 */
	public void setFieldNameIndexMap(Map fieldNameIndexMap) {
		this.fieldNameIndexMap = fieldNameIndexMap;
	}
	/**
	 * @param fieldNameMap The fieldNameMap to set.
	 */
	public void setFieldNameMap(Map fieldNameMap) {
		this.fieldNameMap = fieldNameMap;
	}
}
