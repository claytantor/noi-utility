package com.noi.utility.spring.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noi.utility.hibernate.ImageValue;
import com.noi.utility.spring.dao.ImageValueDao;

public class ImageValueServiceImpl implements ImageValueService {
	
	static Logger logger = Logger.getLogger(ImageValueServiceImpl.class);
	
	private ImageValueDao imageValueDao;
	
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	
	/* (non-Javadoc)
	 * @see com.noi.utility.spring.service.ImageValueService#saveImageValue(com.noi.utility.hibernate.ImageValue)
	 */
	public void saveImageValue(ImageValue iv)
	{
		imageValueDao.save(iv);
	}	

	@Override
	public List<ImageValue> findAll() {
		return new ArrayList<ImageValue>(imageValueDao.findAll());
	}

	@Override
	public InputStream findImageStream(ImageValue iv) 
	{
				
		try {
			ByteArrayInputStream bais = 
				new ByteArrayInputStream(iv.getImageBytes());
			
			BufferedInputStream input = null;			
			
			// Open image.
            input = new BufferedInputStream(bais, DEFAULT_BUFFER_SIZE);
		} catch (Exception e) {
			logger.error("problem getting image", e);
		}
		return null;
	}





	@Override
	public void deleteImageValue(ImageValue iv) {
		imageValueDao.delete(iv);
		
	}





	public void setImageValueDao(ImageValueDao imageValueDao) {
		this.imageValueDao = imageValueDao;
	}

	@Override
	public ImageValue findImageById(Long id) {
		ImageValue iv = imageValueDao.findByPrimaryKey(id); 
		return iv;
	}
	

}
