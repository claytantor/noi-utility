package com.noi.utility.hibernate;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;


/**
 * Factory methods to convert a DataAccessCriteria object into a Hibernate
 * Criteria Object.
 * 
 */
public class HibernateCriteraFactory {
    static Logger logger = Logger.getLogger(HibernateCriteraFactory.class);

    public static Criteria makeHibernateCriteria(
            DataAccessCriteria dataAccessCriteria, Session session) {
        logger.debug("makeHibernateCriteria");
        Criteria crit = session.createCriteria(dataAccessCriteria
                .getEntityClass());
        for (Iterator iter = dataAccessCriteria.getExpressionList().iterator(); iter
                .hasNext();) {
            Object criteriaObject = iter.next();
            if (criteriaObject instanceof DataAccessExpression) {
                DataAccessExpression expr = (DataAccessExpression) criteriaObject;
                HibernateCriteraFactory.makeCriterionForExpression(expr, crit);
            }
        }
        return crit;
    }

    public static void makeCriterionForExpression(DataAccessExpression expr,
            Criteria crit) {
        switch (expr.getExpressionType()) {
        case DataAccessExpression.EXPRESSION_EQ: {
            crit.add(Expression
                    .eq(expr.getPropertyName(), expr.getFieldValue()));
            break;
        }
        case DataAccessExpression.EXPRESSION_GE: {
            crit.add(Expression
                    .ge(expr.getPropertyName(), expr.getFieldValue()));
            break;
        }
        case DataAccessExpression.EXPRESSION_GT: {
            crit.add(Expression
                    .gt(expr.getPropertyName(), expr.getFieldValue()));
            break;
        }
        case DataAccessExpression.EXPRESSION_LE: {
            crit.add(Expression
                    .le(expr.getPropertyName(), expr.getFieldValue()));
            break;
        }
        case DataAccessExpression.EXPRESSION_LT: {
            crit.add(Expression
                    .lt(expr.getPropertyName(), expr.getFieldValue()));
            break;
        }
        case DataAccessExpression.EXPRESSION_LIKE: {
            crit.add(Expression.like(expr.getPropertyName(), expr
                    .getFieldValue()));
            break;
        }
        default: {
            throw new RuntimeException(
                    "could not find expression for type:"
                            + expr.getExpressionType());
        }
        }
    }
}
