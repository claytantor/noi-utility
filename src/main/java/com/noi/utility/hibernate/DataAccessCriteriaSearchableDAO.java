package com.noi.utility.hibernate;

import java.util.Collection;

public interface DataAccessCriteriaSearchableDAO {
	public DataAccessCriteria createCriteria();
	public Collection findByDataAccessCriteria(DataAccessCriteria criteria);
}
