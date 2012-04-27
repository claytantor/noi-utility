package com.noi.utility.html;

import com.noi.utility.reflection.ReflectionUtils;

public class HtmlColumn {
	
	private String tagClass;
	
	private String cellBody;
	
	private String header;



	/**
	 * @return Returns the cellBody.
	 */
	public String getCellBody() {
		return cellBody;
	}

	/**
	 * @param cellBody The cellBody to set.
	 */
	public void setCellBody(String cellBody) {
		this.cellBody = cellBody;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return ReflectionUtils.toBeanString(this);
	}

	/**
	 * @return Returns the tagClass.
	 */
	public String getTagClass() {
		return tagClass;
	}

	/**
	 * @param tagClass The tagClass to set.
	 */
	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	/**
	 * @return Returns the header.
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header The header to set.
	 */
	public void setHeader(String header) {
		this.header = header;
	}


		

}
