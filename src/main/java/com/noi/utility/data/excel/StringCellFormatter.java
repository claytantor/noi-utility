package com.noi.utility.data.excel;

public class StringCellFormatter implements CellFormatter {

	public String getFieldClassName() {
		return "java.lang.String";
	}

	public Object getField(String value) {
		return value;
	}

}
