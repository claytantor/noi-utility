package com.noi.utility.hibernate;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.HibernateException;
import org.hibernate.type.ImmutableType;

public class StringStreamingClobType extends ImmutableType {
	
	
	@Override
	public Object fromStringValue(String arg0) throws HibernateException {		
		return null;
	}

	@Override
	public String toString(Object arg0) throws HibernateException {
		return null;
	}

	public Class getReturnedClass() {
		return null;
	}

	public Object get(ResultSet rs, String name) throws HibernateException, SQLException
    {
        Reader reader = rs.getCharacterStream(name);
        if (reader == null)
        {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try
        {
            char[] charbuf = new char[4096];
            for (int i = reader.read(charbuf); i > 0; i = reader.read(charbuf))
            {
                sb.append(charbuf, 0, i);
            }
        }
        catch (IOException e)
        {
            throw new SQLException( e.getMessage() );
        }
        return sb.toString();
    }
	
	public Class returnedClass() {
	    return String.class;
	}
	
	public void set(PreparedStatement st, Object value, int index) 
	    throws SQLException {
	    StringReader r = new StringReader( (String)value );
	    st.setCharacterStream( index, r, ((String)value).length() );
	}
	
	public int sqlType() {
	    return Types.CLOB;
	}
	    
	public String getName() { return "string"; }
	
	public boolean hasNiceEquals() {
	    return false;
	}
	
	public boolean equals(Object x, Object y) {
	    return ObjectUtils.equals(x, y);
	}
	
	public String toXML(Object value) {
	    return (String) value;
	}
}
