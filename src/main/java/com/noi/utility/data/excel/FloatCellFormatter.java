package com.noi.utility.data.excel;

import com.noi.utility.regex.RegularExpressionUtils;

public class FloatCellFormatter implements CellFormatter {

	public String getFieldClassName() {
		return "java.lang.Float";
	}

	public Object getField(String value) {
		
		String cleaned = RegularExpressionUtils.makeFilteredString("[0-9.]", value);
		return new Float(cleaned);
	}

}
