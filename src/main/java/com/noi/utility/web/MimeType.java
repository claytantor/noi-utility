package com.noi.utility.web;

import java.io.Serializable;

public class MimeType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int MIME_TYPE_PDF = 1;
	public static final int MIME_TYPE_CSV = 2;
	public static final int MIME_TYPE_HTM = 3;
	public static final int MIME_TYPE_XLS = 4;
	public static final int MIME_TYPE_DOC = 5;
	public static final int MIME_TYPE_XML = 6;
	public static final int MIME_TYPE_TEXT = 7;
	
	private static String[][] types = {
		{ "1" , "application/pdf", ".pdf", "Adobe Acrobat Portable Document"},
		{ "6" , "text/xml", ".xml", "XML Machine Readable"},
		{ "7" , "text/plain", ".txt", "Common Text File"},
		
	};
	
	private String type;
	private String extension;
	private String description;
	private Integer id;
	
	
	public MimeType(int id) {
		super();
		for (int i = 0; i < types.length; i++) {
			String[] values = types[i];
			int type = Integer.parseInt(values[0]);
			if(id == type)
			{
				setValues(values);
				break;
			}
		}
	}
	
	private void setValues(String[] values)
	{
		this.id = Integer.parseInt(values[0]);
		this.type = values[1];
		this.extension = values[2];
		this.description = values[3];
	}

	public String getDescription() {
		return description;
	}
	
	public String getExtension() {
		return extension;
	}
	
	public String getType() {
		return type;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	
	
}
