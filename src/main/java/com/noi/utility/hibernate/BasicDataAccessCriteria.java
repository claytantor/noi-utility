package com.noi.utility.hibernate;

import java.util.ArrayList;
import java.util.List;


/**
 * this implementation is aggreated over a list of fields for a specific type
 * 
 * @author cgraham
 * 
 */
public class BasicDataAccessCriteria implements DataAccessCriteria {

    private Class entityClass;

    private List<DataAccessExpression> expressionList = new ArrayList<DataAccessExpression>();;

    public BasicDataAccessCriteria() {
    }

    public BasicDataAccessCriteria(Class entityClass) {
        this.entityClass = entityClass;
    }

    public DataAccessExpression createExpression() {
        return new BasicDataAccessExpression();
    }

    public void addExpression(DataAccessExpression expr) {
        this.expressionList.add(expr);
    }

    public List<DataAccessExpression> getExpressionList() {
        return this.expressionList;
    }

    public String getEntityName() {
        return entityClass.getName();
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }


}
