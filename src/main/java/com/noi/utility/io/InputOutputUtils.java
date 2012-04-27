package com.noi.utility.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

public class InputOutputUtils {
	public static BufferedReader getModifiedBufferedReader(InputStream is)
			throws IOException {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return new BufferedReader(new StringReader(buffer.toString()));
	}

	public static String getInputStringFromStream(InputStream is,
			String encoding) throws UnsupportedEncodingException {
		InputStreamReader isr = new InputStreamReader(is, encoding);

		StringBuffer buffer = new StringBuffer();

		try {
			Reader in = new BufferedReader(isr);
			int ch;
			while ((ch = in.read()) > -1) {
				buffer.append((char) ch);
			}
			in.close();
			is.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static byte[] getBytesFromStream(InputStream is) 
	throws Exception {
		
		InputStreamReader isr = new InputStreamReader(is);
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

	                baos.write(ba, 0, ba.length);
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
	
	public static void writeFileFromBytes(File f, byte[] buffer) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(f);
		fos.write( buffer );
		fos.flush();
		fos.close();
		

	}
	
}