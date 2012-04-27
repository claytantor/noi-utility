package com.noi.utility.hibernate;

/**
 * this implementation is aggreated over a list of fields for a specific type
 * @author cgraham
 *
 */
public class BasicDataAccessExpression implements DataAccessExpression {

	private String propertyName;
	private Object fieldValue;

	private int expressionType;
	private boolean isRelation;
	private String entityRelationName;
	private String description;
	
	
	public void setExpression(String name, Object value, int expressionType)
	{
		this.propertyName = name;
		this.fieldValue = value;
		this.expressionType = expressionType;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntityRelationName() {
		return entityRelationName;
	}
	public void setEntityRelationName(String entityRelationName) {
		this.entityRelationName = entityRelationName;
	}
	
	public String getPropertyName() {
		return propertyName;
	}


	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	public int getExpressionType() {
		return expressionType;
	}


	public void setExpressionType(int expressionType) {
		this.expressionType = expressionType;
	}


	public Object getFieldValue() {
		return fieldValue; 
	}


	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}


	public boolean isRelation() {
		return isRelation;
	}
	public void setRelation(boolean isRelation) {
		this.isRelation = isRelation;
	}


}
