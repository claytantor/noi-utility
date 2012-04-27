package com.noi.utility.text;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatUtils {
	
	public static final int FORMAT_CURRENCY = 1;
	public static final int FORMAT_INTEGER = 2;
	public static final int FORMAT_PERCENT = 3;
	
	public static String formatFloat(Float f, int style)
	{
		String num = new String("0");
		
		if(f != null)
			num = new String(f.toString());
		else return "";
		
		NumberFormat nf = null;
		switch(style)
		{
			case FORMAT_INTEGER:
			{
				nf = NumberFormat.getIntegerInstance(Locale.US);
				num = nf.format(Double.parseDouble(f.toString()));
				break;
			}
			case FORMAT_PERCENT:
			{
				nf = NumberFormat.getPercentInstance(Locale.US);
				num = nf.format(Double.parseDouble(f.toString()));
				break;
			}
			default:
			{
				nf = NumberFormat.getCurrencyInstance(Locale.US);
				num = nf.format(Double.parseDouble(f.toString()));
			}
		}
		
		return num;
	}

	public static String formatInteger(Integer i)
	{
		if(i != null)
		{
			NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);
			return nf.format(Double.parseDouble(i.toString()));
		}
		else
			return "";
	}	
	
	public static String formatCurrency(Float f)
	{
		return formatFloat(f, FORMAT_CURRENCY);
		
	}
	
	public static String formatInteger(Float f)
	{				
		return formatFloat(f, FORMAT_INTEGER);
	}
	
	
	public static String formatPercent(Float f)
	{				
		return formatFloat(f, FORMAT_PERCENT);
	}

	
	
}
