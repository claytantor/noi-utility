package com.noi.utility.hibernate;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noi.utility.string.StringUtils;


public class HibernateDatabaseProperties extends Properties {
	
    public static final String DRIVER_CLASS = "hibernate.connection.driver_class";

    public static final String USER_NAME = "hibernate.connection.username";

    public static final String PASSWORD = "hibernate.connection.password";

    public static final String URL = "hibernate.connection.url";

    public static final String DIALECT = "hibernate.dialect";

    public static final String CREATION_MODE = "hibernate.hbm2ddl.auto";

    public static final String QUERY_SUBSTITUTIONS = "hibernate.query.substitutions";

    public static final String SHOW_SQL = "hibernate.show_sql";

    public static final String POOL_SIZE_MAX = "hibernate.cp30.max_size";

    public static final String POOL_SIZE_MIN = "hibernate.cp30.min_size";

    public static final String AQUIRE_INCREMENT = "hibernate.c3p0.acquire_increment";

    public static final String IDLE_TEST_PERIOD = "hibernate.c3p0.idle_test_period";

    public static final String MAX_STATEMENTS = "hibernate.c3p0.max_statements";

    public static final String TIMEOUT = "hibernate.c3p0.timeout";

    public static final String TEST_CONNECTIONS_ON_CHECKOUT = "hibernate.c3p0.validate";

    public static final String CONNECTION_PROVIDER = "hibernate.connection.provider_class";

    public static final String USE_QUERY_CACHE = "hibernate.cache.use_query_cache";

    public static final String CACHE_PROVIDER = "hibernate.cache.provider_class";
    
    public static final String SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    
    public static final String JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";
    
    public static final String CONNECTION_PROPERTIES  = "hibernate.connectionProperties";

	   /**
     *  
     */
    private static final long serialVersionUID = 4046819579746139232L;

    private static Log log = LogFactory.getLog(HibernateDatabaseProperties.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.clarussystems.core.configuration.DatabaseProperties#setDriverClass(java.lang.String)
     */
    public void setDriverClass(String driverClass) {
        log.debug("set DriverClass =" + driverClass);
        setProperty(HibernateDatabaseProperties.DRIVER_CLASS, driverClass);
    }
    
    public String getDriverClass()
    {
    	return getProperty(HibernateDatabaseProperties.DRIVER_CLASS);
    }


    public void setUsername(String userName) {
        log.debug("set Username =" + userName);
        setProperty(HibernateDatabaseProperties.USER_NAME, userName);

    }
    
    public String getUsername()
    {
    	return getProperty(HibernateDatabaseProperties.USER_NAME);
    }     


    public void setPassword(String password) {
        log.debug("set Password =" + password);
        setProperty(HibernateDatabaseProperties.PASSWORD, password);

    }
    
    public String getPassword()
    {
    	return getProperty(HibernateDatabaseProperties.PASSWORD);
    }


    public void setUrl(String url) {
        log.debug("set URL =" + url);
        setProperty(HibernateDatabaseProperties.URL, url);

    }
    
    public String getUrl()
    {
    	return getProperty(HibernateDatabaseProperties.URL);
    }     


    public void setDialect(String dialect) {
        log.debug("set dialect =" + dialect);
        setProperty(HibernateDatabaseProperties.DIALECT, dialect);

    }
    
    public String getDialect()
    {
    	return getProperty(HibernateDatabaseProperties.DIALECT);
    }    


    public void setCreationMode(String creationMode) {
    	if(StringUtils.isNotEmpty(creationMode) && !creationMode.equalsIgnoreCase("none"));
    	{
	        log.debug("set creationMode=" + creationMode);
	        setProperty(HibernateDatabaseProperties.CREATION_MODE, creationMode);
    	}

    }
    
    public String getCreationMode()
    {
    	return getProperty(HibernateDatabaseProperties.CREATION_MODE);
    }    


    public void setPoolSizeMax(int max) {
        log.debug("set poolSizeMax =" + max);
        setProperty(HibernateDatabaseProperties.POOL_SIZE_MAX, new Integer(max).toString());
    }


    public void setPoolSizeMin(int min) {
        log.debug("set poolSizeMin =" + min);
        setProperty(HibernateDatabaseProperties.POOL_SIZE_MIN, new Integer(min).toString());
    }


    public void setQuerySubstitutions(String querySubstitutions) {
        log.debug("set querySubstitutions =" + querySubstitutions);
        setProperty(HibernateDatabaseProperties.QUERY_SUBSTITUTIONS, querySubstitutions);
    }


    public void setShowSql(boolean showSql) {
        log.debug("set showSql =" + showSql);
        setProperty(HibernateDatabaseProperties.SHOW_SQL, new Boolean(showSql).toString());
    }


    public void setTimeout(int timeout) {
        log.debug("set timeout =" + timeout);
        setProperty(HibernateDatabaseProperties.TIMEOUT, new Integer(timeout).toString());
    }


    public void setIdleTestPeriod(int idleTestPeriod) {
        log.debug("set idleTestPeriod =" + idleTestPeriod);
        setProperty(HibernateDatabaseProperties.IDLE_TEST_PERIOD, new Integer(idleTestPeriod).toString());
    }


    public void setAquireIncrement(int increment) {
        log.debug("set aquireIncrement =" + increment);
        setProperty(HibernateDatabaseProperties.AQUIRE_INCREMENT, new Integer(increment).toString());
    }

    public void setMaxStatements(int maxStatements) {
        log.debug("set maxStatements =" + maxStatements);
        setProperty(HibernateDatabaseProperties.MAX_STATEMENTS, new Integer(maxStatements).toString());
    }

    public void setValidate(boolean validate) {
        log.debug("set validate =" + validate);
        setProperty(HibernateDatabaseProperties.TEST_CONNECTIONS_ON_CHECKOUT, new Boolean(validate)
                .toString());
    }

    public void setConnectionProvider(String connectionProvider) {
        log.debug("set connectionProvider =" + connectionProvider);
        setProperty(HibernateDatabaseProperties.CONNECTION_PROVIDER, connectionProvider);
    }


    public void setUseQueryCache(boolean useQueryCache) {
        log.debug("set useQueryCache =" + useQueryCache);
        setProperty(HibernateDatabaseProperties.USE_QUERY_CACHE, new Boolean(useQueryCache).toString());
    }

    public void setCacheProvider(String cacheProvider) {
        log.debug("set cacheProvider =" + cacheProvider);
        setProperty(HibernateDatabaseProperties.CACHE_PROVIDER, cacheProvider);
    }

    public void setUseSecondLevelCache(boolean useSecondLevelCache) {
        log.debug("set useSecondLevelCache =" + useSecondLevelCache);
        setProperty(HibernateDatabaseProperties.SECOND_LEVEL_CACHE, new Boolean(useSecondLevelCache).toString());
    }

    public void setJdbcBatchSize(int jdbcBatchSize) {
        log.debug("set jdbcBatchSize =" + jdbcBatchSize);
        setProperty(HibernateDatabaseProperties.JDBC_BATCH_SIZE, new Integer(jdbcBatchSize).toString());
    }

	public void setConnectionProperties(String connectionProperties) {
        log.debug("set connectionProperties =" + connectionProperties);
        setProperty(HibernateDatabaseProperties.CONNECTION_PROPERTIES, connectionProperties);
	}


	public Properties getProperties() {
		return (Properties)this;
	}

}
