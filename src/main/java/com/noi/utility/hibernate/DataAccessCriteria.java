package com.noi.utility.hibernate;

import java.util.List;

/**
 * Interface for the DataAccessCriteria object
 * 
 */
public interface DataAccessCriteria {
    /**
     * abstract method for creating a DataAccessExpression
     * 
     * @return <code>DataAccessExpression</code>
     */
    abstract DataAccessExpression createExpression();

    /**
     * abstract method for adding an DataAccessExpression to the criteria.
     * 
     * @param <code>DataAccessExpression</code>
     */
    abstract void addExpression(DataAccessExpression expr);

    /**
     * abstract method for retrieving the list of Expressions.
     * 
     * @return List<DataAccessExpression>
     */
    abstract List<DataAccessExpression> getExpressionList();

    /**
     * method gets the entityName
     * 
     * @return String entity Name
     */
    String getEntityName();

    /**
     * method gets the EntityClass
     * 
     * @return Class -- entityClass
     */
    Class getEntityClass();

    /**
     * sets the entityClass
     * 
     * @param entityClass
     */
    void setEntityClass(Class entityClass);
}
