package com.noi.utility.spring.dao;

import java.util.Collection;

import com.noi.utility.hibernate.ImageValue;


public interface ImageValueDao {
	public ImageValue findByPrimaryKey(Long id);	
	
	public Collection<ImageValue> findAll();
		
	public ImageValue create();
		
	public void delete(ImageValue entity);
	
	public void save(ImageValue entity);
}
