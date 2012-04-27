package com.noi.utility.spring.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.noi.utility.hibernate.ImageValue;

public class HibernateImageValueDao extends HibernateDaoSupport 
	implements ImageValueDao {

	static Logger logger = Logger.getLogger(HibernateImageValueDao.class);
    
    public HibernateTemplate getHibernateTemplateOverride() {
        HibernateTemplate template = getHibernateTemplate();
        template.setFlushMode(HibernateTemplate.FLUSH_AUTO);
        return template;
    }

	@Override
	public ImageValue create() {
		return new ImageValue();
	}

	@Override
	public void delete(ImageValue entity) {
		 getHibernateTemplateOverride().delete(entity);		
	}

	@Override
	public Collection<ImageValue> findAll() {
		return getHibernateTemplateOverride().loadAll(ImageValue.class);
	}

	@Override
	public ImageValue findByPrimaryKey(Long id) {
		ImageValue m = (ImageValue)getHibernateTemplate().get(ImageValue.class, id);
		return m;	
	}

	@Override
	public void save(ImageValue entity) {
		 getHibernateTemplateOverride().save(entity);		
	}
    

}
