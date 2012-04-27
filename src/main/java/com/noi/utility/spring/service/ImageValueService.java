package com.noi.utility.spring.service;

import java.io.InputStream;
import java.util.List;

import com.noi.utility.hibernate.ImageValue;

public interface ImageValueService {

	public void saveImageValue(ImageValue iv);
	public ImageValue findImageById(Long id);
	public List<ImageValue> findAll();
	public void deleteImageValue(ImageValue iv);
	public InputStream findImageStream(ImageValue iv);

}