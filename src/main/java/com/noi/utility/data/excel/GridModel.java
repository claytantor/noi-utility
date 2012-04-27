package com.noi.utility.data.excel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GridModel {

	//required by all grids
	public static final String SELECTED_ROW_FIELD_ID = "SELECTED_ROW";
	public static final String BUSINESS_OBJECT_ID = "BUSINESS_OBJECT_ID";
	public static final String RECORD_STATUS_FIELD_ID = "API_RECORD_STATUS";
	public static final String DISPLAY_STATUS_FIELD_ID = "API_DISPLAY_STATUS";
	//record status types
	public static final String ROW_STATUS_DELETED = "D";
	public static final String ROW_STATUS_INSERTED = "I";
	public static final String ROW_STATUS_QUERY = "Q";
	public static final String ROW_STATUS_NEW = "N";
	public static final String ROW_STATUS_UPDATED = "U";	
	
	
	private  static final int FIRST_LINE=0;
	
	private List<RowModel> rows;
	
	private int rowIndex;
	private RowModel currentRow;
	//private int totalRows = 0;
	private int fetchSize = 0;
	private boolean rowUpdated = false;

	public GridModel()
	{
		this.rows = new LinkedList();
		this.rowIndex = 1;
		this.currentRow = new RowModel();
		
	}
		
	public GridModel(List rows)
	{
		this();
		this.rows = rows;
		this.currentRow = (RowModel)rows.get(this.rowIndex-1);
	}
	
	
	public GridModel(String data)
	{
		this();
		this.rows = makeRowsFromData(data);
		this.currentRow = (RowModel)rows.get(this.rowIndex-1);
	}
	
	private List<RowModel> makeRowsFromData(String data)
	{
		List<RowModel> rows = new ArrayList<RowModel>();
		StringReader sreader = new StringReader(data);
		
		try {
			BufferedReader bufferedReader = 
		        new BufferedReader(sreader);
		      // Read input
		      String line;
		      String[] fieldNames = null;
		      int counter = 0;
		      
		      while ((line = bufferedReader.readLine()) != null) {
		    	  
		    	switch(counter)
		    	{
		    		case FIRST_LINE:
		    		{
		    			fieldNames = line.split("\\u007c");
		    			break;
		    		}
		    		default:
		    		{
		    			RowModel row = new RowModel();		    			
		    			String[] fieldValues = line.split("\\u007c");
		    			for (int i = 0; i < fieldValues.length; i++) {
		    				row.addField(i, fieldNames[i], fieldValues[i]);
						}		    			
		    			rows.add(row);
		    			
		    		}		    		
		    	}		    	
		    	counter++;
		      }			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;

	}

	
	public void addRow(RowModel row)
	{
		this.rows.add(row);
		this.currentRow = row;
		
	}
		
	/**
	 * @return Returns the rows.
	 */
	public List<RowModel> getRows() {
		
		return rows;
	}
	
	/**
	 * @param rows The rows to set.
	 */
	public void setRows(List rows) {
		
		
		this.rows = rows;
		this.rowIndex = 1;
		this.currentRow = (RowModel)rows.get(this.rowIndex-1);
		
	}
	
	
	public void removeRow(RowModel row)
	{
		this.rows.remove(row);
	}
	
	
	public int getRowNumber()
	{
		return this.rowIndex;
	}
	

	public boolean absolute(int arg0) {
		return false;
	}
	

	public void afterLast() {
		this.rowIndex = this.rows.size()+1;

	}

	
	public void beforeFirst() {
		
		this.rowIndex = 0;
	}

	
	public void cancelRowUpdates() {
		// TODO Not implemented
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#clearWarnings()
	 */
	public void clearWarnings() {
		// TODO Not implemented

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#close()
	 */
	public void close() {
		// TODO Not implemented

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#deleteRow()
	 */
	public void deleteRow() {
		this.rows.remove(this.currentRow);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#findColumn(java.lang.String)
	 */
	public int findColumn(String arg0) {
		// TODO Empty implementation
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#first()
	 */
	public boolean first() {
		this.rowIndex = 1;
		
		// TODO Empty implementation
		if (this.rows.size()>0)
		{
			this.currentRow = (RowModel)this.rows.get(0);
			return true;
		}
		else
			return false;		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#sort()
	 */
	public void sort() {
		// TODO Empty implementation
		//order the rows
		Collections.sort(this.rows);

	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getAsciiStream(int)
	 */
	public InputStream getAsciiStream(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getAsciiStream(java.lang.String)
	 */
	public InputStream getAsciiStream(String arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBigDecimal(int, int)
	 */
	public BigDecimal getBigDecimal(int arg0, int arg1) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBigDecimal(int)
	 */
	public BigDecimal getBigDecimal(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBigDecimal(java.lang.String, int)
	 */
	public BigDecimal getBigDecimal(String arg0, int arg1) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBigDecimal(java.lang.String)
	 */
	public BigDecimal getBigDecimal(String arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBinaryStream(int)
	 */
	public InputStream getBinaryStream(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBinaryStream(java.lang.String)
	 */
	public InputStream getBinaryStream(String arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBoolean(int)
	 */
	public boolean getBoolean(int arg0) {
		// TODO Empty implementation
		return false;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(String arg0) {
		// TODO Empty implementation
		return false;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getByte(int)
	 */
	public byte getByte(int arg0) {
		// TODO Empty implementation
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getByte(java.lang.String)
	 */
	public byte getByte(String arg0) {
		// TODO Empty implementation
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBytes(int)
	 */
	public byte[] getBytes(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getBytes(java.lang.String)
	 */
	public byte[] getBytes(String arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getCharacterStream(int)
	 */
	public Reader getCharacterStream(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getCharacterStream(java.lang.String)
	 */
	public Reader getCharacterStream(String arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getConcurrency()
	 */
	public int getConcurrency() {
		// TODO Empty implementation
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getDate(int, java.util.Calendar)
	 */
	public Date getDate(int arg0, Calendar arg1) {
		// TODO Empty implementation
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getDate(int)
	 */
	public Date getDate(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getDate(java.lang.String, java.util.Calendar)
	 */
	public Date getDate(String arg0, Calendar arg1) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getDate(java.lang.String)
	 */
	public Date getDate(String arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getDouble(int)
	 */
	public double getDouble(int arg0) {
		Double d = new Double(((Cell)this.currentRow.getFieldByIndex(arg0)).getData());
		return d.doubleValue();
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getDouble(java.lang.String)
	 */
	public double getDouble(String arg0) {
		Double d = (Double)this.currentRow.getFieldByName(arg0);
		return d.doubleValue();		
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getFetchDirection()
	 */
	public int getFetchDirection() {
		// TODO Empty implementation
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getFetchSize()
	 */
	public int getFetchSize() {
		// TODO Empty implementation
		int size = this.rows.size();
		if(this.fetchSize > 0)
			size = this.fetchSize;
		
		return size;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getFloat(int)
	 */
	public float getFloat(int arg0) {
		Float f = new Float(((Cell)this.currentRow.getFieldByIndex(arg0)).getData());
		return f.floatValue();
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getFloat(java.lang.String)
	 */
	public float getFloat(String arg0) {
		Float f = (Float)this.currentRow.getFieldByName(arg0);
		return f.floatValue();
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getInt(int)
	 */
	public int getInt(int arg0) {
		Integer i = new Integer(((Cell)this.currentRow.getFieldByIndex(arg0)).getData());
		return i.intValue();
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getInt(java.lang.String)
	 */
	public int getInt(String arg0) {
		Integer i = (Integer)this.currentRow.getFieldByName(arg0);
		return i.intValue();
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getList(int)
	 */
	public Collection getCollection(int arg0) {
		Collection c = (Collection)this.currentRow.getFieldByIndex(arg0);
		return c;
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getList(java.lang.String)
	 */
	public Collection getCollection(String arg0) {
		Object o = this.currentRow.getFieldByName(arg0);
		
		Collection c = (Collection)this.currentRow.getFieldByName(arg0);
		return c;
	}
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getLong(int)
	 */
	public long getLong(int arg0) {
		Long l = new Long(((Cell)this.currentRow.getFieldByIndex(arg0)).getData());
		return l.longValue();
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getLong(java.lang.String)
	 */
	public long getLong(String arg0) {
		//logger.debug("trying to get value for arg:"+arg0);
		Long l = (Long)this.currentRow.getFieldByName(arg0);
		return l.longValue();
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getObject(int, java.util.Map)
	 */
	public Object getObject(int arg0, Map arg1) {
		// TODO Empty implementation 
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getObject(int)
	 */
	public Object getObject(int arg0) {
		// TODO Empty implementation
		return this.currentRow.getFieldByIndex(arg0);
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getObject(java.lang.String, java.util.Map)
	 */
	public Object getObject(String arg0, Map arg1) {
		// TODO Empty implementation
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getObject(java.lang.String)
	 */
	public Object getObject(String arg0) {
		// TODO Empty implementation
		return this.currentRow.getFieldByName(arg0);
	}


	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getRow()
	 */
	public RowModel getRow(int arg0) {
		return (RowModel)rows.get(arg0-1);
	}	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getShort(int)
	 */
	public short getShort(int arg0) {
		// TODO Empty implementation
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getShort(java.lang.String)
	 */
	public short getShort(String arg0) {
		// TODO Empty implementation
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getString(int)
	 */
	public String getString(int arg0) {
		Object o = this.currentRow.getFieldByIndex(arg0);
		return o.toString();
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getString(java.lang.String)
	 */
	public String getString(String arg0) {
		Object o = this.currentRow.getFieldByName(arg0);
		return o.toString();
	}
			
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getURL(int)
	 */
	public URL getURL(int arg0) {
		// TODO Empty implementation
		return null;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getURL(java.lang.String)
	 */
	public URL getURL(String arg0) {
		// TODO Empty implementation
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#isFirst()
	 */
	public boolean isFirst() {

		if ((this.rowIndex == 1) && (this.rows.size()>0))
			return true;
		else
			return false;	
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#isLast()
	 */
	public boolean isLast() {
		if ((this.rowIndex == this.rows.size()) && (this.rows.size()>0))
			return true;
		else
			return false;	
	}

	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#last()
	 */
	public boolean last() {
		this.rowIndex = this.rows.size();
		this.currentRow = (RowModel)this.rows.get(this.rowIndex-1);		
		if ((this.rowIndex == this.rows.size()) && (this.rows.size()>0))
			return true;
		else
			return false;	
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#moveToCurrentRow()
	 */
	public void moveToCurrentRow() {
		// TODO Empty implementation

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#moveToInsertRow()
	 */
	public void moveToInsertRow() {
		// TODO Empty implementation

	}
	
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#setCurrentRow(int)
	 */
	public void setCurrentRow(int rownum) {
		// TODO Empty implementation
		if(rownum<=this.size())
		{
			this.currentRow = this.getRow(rownum);
			this.rowIndex = rownum;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#next()
	 */
	public boolean next() {
		this.rowIndex++;
		if(!isAfterLast())
		{
			this.currentRow = (RowModel)this.rows.get(this.rowIndex-1);
			this.rowUpdated = false;
			return true;
		}
		else
			return false;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#previous()
	 */
	public boolean previous() {
		if(!isFirst())
		{
			this.rowIndex--;
			this.currentRow = (RowModel)this.rows.get(this.rowIndex-1);	
			return true;
		}
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#refreshRow()
	 */
	public void refreshRow() {
		// TODO Empty implementation

	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#relative(int)
	 */
	public boolean relative(int arg0) {
		// TODO Empty implementation
		return false;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#rowDeleted()
	 */
	public boolean rowDeleted() {
		// TODO Empty implementation
		return false;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#rowInserted()
	 */
	public boolean rowInserted() {
		// TODO Empty implementation
		return false;
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#rowUpdated()
	 */
	public boolean rowUpdated() {
		// TODO Empty implementation
		return this.rowUpdated;
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#setFetchDirection(int)
	 */
	public void setFetchDirection(int arg0) {
		// TODO Empty implementation

	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#setFetchSize(int)
	 */
	public void setFetchSize(int arg0) {
		this.fetchSize = arg0;

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateBigDecimal(int, java.math.BigDecimal)
	 */
	public void updateBigDecimal(int arg0, BigDecimal arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateBigDecimal(java.lang.String, java.math.BigDecimal)
	 */
	public void updateBigDecimal(String arg0, BigDecimal arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateBoolean(int, boolean)
	 */
	public void updateBoolean(int arg0, boolean arg1) {
		this.updateObject(arg0,  new Boolean(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateBoolean(java.lang.String, boolean)
	 */
	public void updateBoolean(String arg0, boolean arg1) {
		this.updateObject(arg0,  new Boolean(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateByte(int, byte)
	 */
	public void updateByte(int arg0, byte arg1) {
		this.updateObject(arg0,  new Byte(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateByte(java.lang.String, byte)
	 */
	public void updateByte(String arg0, byte arg1) {
		this.updateObject(arg0,  new Byte(arg1));
		
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateBytes(int, byte[])
	 */
	public void updateBytes(int arg0, byte[] arg1) {
		// TODO Empty implementation

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateBytes(java.lang.String, byte[])
	 */
	public void updateBytes(String arg0, byte[] arg1) {
		// TODO Empty implementation
	}
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateDate(int, java.util.Date)
	 */
	public void updateDate(int arg0, Date arg1) {
		this.updateObject(arg0,  arg1);
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateDate(java.lang.String, java.util.Date)
	 */
	public void updateDate(String arg0, Date arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateDouble(int, double)
	 */
	public void updateDouble(int arg0, double arg1) {
		this.updateObject(arg0,  new Double(arg1));
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateDouble(java.lang.String, double)
	 */
	public void updateDouble(String arg0, double arg1) {
		this.updateObject(arg0,  new Double(arg1));
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateFloat(int, float)
	 */
	public void updateFloat(int arg0, float arg1) {
		this.updateObject(arg0,  new Float(arg1));
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateFloat(java.lang.String, float)
	 */
	public void updateFloat(String arg0, float arg1) {
		this.updateObject(arg0,  new Float(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateInt(int, int)
	 */
	public void updateInt(int arg0, int arg1) {
		this.updateObject(arg0,  new Integer(arg1));
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateInt(java.lang.String, int)
	 */
	public void updateInt(String arg0, int arg1) {
		this.updateObject(arg0,  new Integer(arg1));
		
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateList(int, java.util.List)
	 */
	public void updateList(int arg0, List arg1) {
		this.updateObject(arg0, arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateList(java.lang.String, java.util.List)
	 */
	public void updateList(String arg0, List arg1) {
		this.updateObject(arg0, arg1);
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateLong(int, long)
	 */
	public void updateLong(int arg0, long arg1) {
		this.updateObject(arg0, new Long(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateLong(java.lang.String, long)
	 */
	public void updateLong(String arg0, long arg1) {
		this.updateObject(arg0,  new Long(arg1));
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateNull(int)
	 */
	public void updateNull(int arg0) {
		this.updateObject(arg0, null);
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateNull(java.lang.String)
	 */
	public void updateNull(String arg0) {
		this.updateObject(arg0, null);
	}
 
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateObject(int, java.lang.Object)
	 */
	public void updateObject(int arg0, Object arg1) {
		this.currentRow.setFieldByIndex(arg0, arg1);
		this.rows.set(this.rowIndex-1, this.currentRow);
		this.rowUpdated = true;

	}
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateObject(java.lang.String, java.lang.Object)
	 */
	public void updateObject(String arg0, Object arg1) {
		this.currentRow.setFieldByName(arg0, arg1);
		this.rows.set(this.rowIndex-1, this.currentRow);
	}
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateRow()
	 */
	public void updateRow(RowModel row) {
		// TODO Empty implementation
		this.currentRow = row;
		this.rows.set(this.rowIndex, this.currentRow);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateShort(int, short)
	 */
	public void updateShort(int arg0, short arg1) {
		this.updateObject(arg0,  new Short(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateShort(java.lang.String, short)
	 */
	public void updateShort(String arg0, short arg1) {
		this.updateObject(arg0,  new Short(arg1));

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateString(int, java.lang.String)
	 */
	public void updateString(int arg0, String arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateString(java.lang.String, java.lang.String)
	 */
	public void updateString(String arg0, String arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateTimestamp(int, java.sql.Timestamp)
	 */
	public void updateTimestamp(int arg0, Timestamp arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#updateTimestamp(java.lang.String, java.sql.Timestamp)
	 */
	public void updateTimestamp(String arg0, Timestamp arg1) {
		this.updateObject(arg0,  arg1);

	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#wasNull()
	 */
	public boolean wasNull() {
		// TODO Empty implementation
		return false;
	}
	
	public int getFieldCount()
	{
		return this.currentRow.getFieldCount();
	}	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#isAfterLast()
	 */
	public boolean isAfterLast() {
		//mas-
		//logger.debug("===> Begin isAfterLast -mas-  if ( this.rowIndex > this.rows.size() )");
		//if ((this.rowIndex > this.rows.size()) && (this.rows.size()>0))
		if ( this.rowIndex > this.rows.size() )
			return true;
		else
			return false;	
	}
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#isBeforeFirst()
	 */
	public boolean isBeforeFirst() {
		// TODO Empty implementation
		if (this.rowIndex <1)
			return true;
		else
			return false;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#getFieldNames()
	 */
	public String[] getFieldNames() {

		return this.currentRow.getFieldNames();
	}

	public int size()
	{
		return this.rows.size();
	}
	
	public Date stringToDate(String dateValue) {
		Date parsed=null;
		if (dateValue!=null) {
			SimpleDateFormat format = 
	            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			try {

	            parsed = format.parse(dateValue);
	        }catch(Exception e)
			{
	        	return null;
			}
		}
		return parsed;
	}
	
	public String dateToString(Date dateValue) {
		String dateStr="";
		if (dateValue!=null) {
			dateStr =  new Date(dateValue.getTime()).toString();
		} 
		return dateStr;
	}
	
	//-----------------clone override--------------------------//
	public Object clone()
	{
		GridModel clone = new GridModel();
		Iterator irows = this.rows.iterator();
		while(irows.hasNext())
		{
			RowModel rowto = new RowModel();
			RowModel rowfrom = (RowModel)irows.next();
			String[] names = rowfrom.getFieldNames();
			for(int i=0; i<names.length;i++)
			{
				rowto.addField((i+1),names[i],rowfrom.getFieldByName(names[i]));
			}
			clone.addRow(rowto);
			
		}
		clone.first();
		clone.sort();
		return clone;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.noi.bl.base.GridModel#isEmpty()
	 */
	public boolean isEmpty() 
	{
		String firstStatus = this.getString(GridModel.RECORD_STATUS_FIELD_ID);
		if(firstStatus.equals(GridModel.ROW_STATUS_NEW) && (this.size() == 1))
			return true;
		else
			return false;
	}

}
