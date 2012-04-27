package com.noi.utility.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;


public class Base64Utils {
	
	private static Base64Utils _instance;
	
	private Base64Utils()
	{
		
	}
	
	public static Base64Utils getInstance() 
	{
		if(_instance == null)
			_instance = new Base64Utils();
		return _instance;
			
	}
	
	public static String encodeString(String b)throws Exception
	{
		return new String(encode(b.getBytes()));
	}
	
	public static String decodeString(String b)throws Exception
	{
		return new String(decode(b.getBytes()));
	}
	
	
	 public static byte[] encode(byte[] b) throws Exception {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        OutputStream b64os = MimeUtility.encode(baos, "base64");
	        b64os.write(b);
	        b64os.close();
	        return baos.toByteArray();
	 }
	 
	 public static byte[] getEncodedBytesFromStream(InputStream is) 
		throws Exception {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        try {

	            int bytesRead = 0;
	            int chunkSize = 10000000;
	            byte[] chunk = new byte[chunkSize];
	            while ((bytesRead = is.read(chunk)) > 0) {
	                byte[] ba = new byte[bytesRead];

	                for(int i=0; i<bytesRead;i++)
	                {
	                    ba[i] = chunk[i];
	                }

	                String encStr = Base64.base64Encode(ba);
	                baos.write(encStr.getBytes(), 0, encStr.length());
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                baos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        if(baos != null)
	        	return baos.toByteArray();
	        else
	        	throw new Exception("cannot convert stream to bytes");
			

		}	 
	 
	
	 public static byte[] decode(byte[] b) throws IOException, MessagingException {
	        ByteArrayInputStream bais = new ByteArrayInputStream(b);
	        InputStream b64is = MimeUtility.decode(bais, "Base64");
	        byte[] tmp = new byte[b.length];
	        int n = b64is.read(tmp);
	        byte[] res = new byte[n];
	        System.arraycopy(tmp, 0, res, 0, n);
	        return res;
	     } 
	 
	    /** Write the object to a Base64 string. 
	     * @throws Exception */
	    public static String toString( Serializable o ) throws Exception {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream( baos );
	        oos.writeObject( o );
	        oos.close();
	        return new String( Base64Utils.encode( baos.toByteArray() ) );
	    }
	    
	    /** Read the object from Base64 string. */
	    public static Object fromString( String s ) throws IOException ,
	                                                        ClassNotFoundException {
	        try {
				byte [] data = Base64Utils.decode( s.getBytes() );
				ObjectInputStream ois = new ObjectInputStream( 
				                                new ByteArrayInputStream(  data ) );
				Object o  = ois.readObject();
				ois.close();
				return o;
			} catch (MessagingException e) {
				return null;
			}
	    }

}
