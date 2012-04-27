/*
 * URLWriter.java
 *
 * Created on August 27, 2001, 9:51 PM
 */

package com.noi.utility.data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author  clay
 * @version 
 */
public class URLWriter {

    /** Creates new URLWriter */
    public URLWriter(String url, String file) throws Exception {
        
        URL tUrl = new URL(url);
        BufferedReader in = new BufferedReader(
			        new InputStreamReader(
			        tUrl.openStream()));
        //file outputFile = new File(file);
        DataOutputStream out = new DataOutputStream(
                           new FileOutputStream(file));
        

        String inputLine;

        while ((inputLine = in.readLine()) != null)
	        //mp_PageString = mp_PageString + inputLine + "\n";
            out.writeBytes(inputLine);


        in.close();
        out.close();
        
    }

}
