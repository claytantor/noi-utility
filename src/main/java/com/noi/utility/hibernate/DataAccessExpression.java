package com.noi.utility.hibernate;

public interface DataAccessExpression {
	public static final int EXPRESSION_EQ = 1;
	public static final int EXPRESSION_GE = 2;
	public static final int EXPRESSION_GT = 3;
	public static final int EXPRESSION_LE = 4;
	public static final int EXPRESSION_LT = 5;
	public static final int EXPRESSION_LIKE = 6;
	
	public void setExpression(String name, Object value, int expressionType);
		
	public String getDescription();
	
	public void setDescription(String description);

	public String getEntityRelationName();
	
	public void setEntityRelationName(String entityRelationName);
	
	public String getPropertyName();
	
	public void setPropertyName(String fieldName);
	
	public Object getFieldValue();

	public void setFieldValue(Object fieldValue);
	
	public boolean isRelation();
	
	public void setRelation(boolean isRelation);
	
	public int getExpressionType();
	
	public void setExpressionType(int type);
	
}
