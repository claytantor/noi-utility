package com.noi.utility.hibernate;

import java.sql.Blob;

import org.hibernate.Hibernate;

public class ImageValue {
	// id for entity
	private Long id;
	private String contentType;
	private String filename;
	private String type;

	private Blob imageBlob;
	private byte[] imageBytes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageBytes() throws Exception {
		return imageBlob.getBytes(1, (int) imageBlob.length());
	}

	public byte[] getImageBytesBuffered() throws Exception {
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes) {
		this.imageBlob = Hibernate.createBlob((byte[]) imageBytes);
		this.imageBytes = imageBytes;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Blob getImageBlob() {
		return imageBlob;
	}

	public void setImageBlob(Blob blob) {
		imageBlob = blob;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
