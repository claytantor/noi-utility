package com.noi.utility.data.excel;

public class Cell {
	private String data;
	private String type; 

	
	
	public String getData() {
		if(isNullData())
			return null;
		else if(!data.equals("*"))
			return data;
		else
			return "";
	}
	
	public boolean isNullData()
	{
		if(this.data == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[Cell");
		buf.append(" data: "+this.data);
		buf.append(" type: "+this.type);
		buf.append("]\n");
		return buf.toString();
	}


}
