package com.noi.utility.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.impl.dv.util.Base64;


public class SimpleHttpClient {

    private static final Log logger = LogFactory.getLog(SimpleHttpClient.class);

    private String username;

    private String password;

    public SimpleHttpClient(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

       
    public SimpleHttpClient() {
		super();
	}

    @Deprecated
	public String getUrlContents(String urlString) {

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(!StringUtils.isEmpty(this.username))
            {
	            String pushAuth = Base64.encode(new String(username + ":"
	                    + password).getBytes());
	            conn.setRequestProperty("Authorization", "Basic " + pushAuth);
            }
            return IOUtils.toString(conn.getInputStream());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public int submitDocument(String urlString, String doc) {

        try {
            String postData = URLEncoder.encode("XML", "UTF-8") + "="
                    + URLEncoder.encode(doc, "UTF-8");

            logger.debug("Using URL [" + urlString + "]");

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestMethod("POST");

            String userAndPassEncoded = Base64.encode(new String(username + ":"
                    + password).getBytes());

            conn.setRequestProperty("Authorization", "Basic "
                    + userAndPassEncoded);

            Writer submitWriter = new OutputStreamWriter(conn.getOutputStream());
            submitWriter.write(postData);
            submitWriter.flush();

            return conn.getResponseCode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	public static ClientResponse post(String endpoint,
			Map<String, String> params) {
		ClientResponse response = new ClientResponse();
		response.setCode(500);
		HttpClient client = new HttpClient();
		//client.getParams().setParameter("http.useragent", "RateCred");

		BufferedReader br = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PostMethod method = new PostMethod(endpoint);
		for (Entry<String,String> entry : params.entrySet()) {
			method.addParameter(entry.getKey(), entry.getValue());
		}
		

		try {
			int returnCode = client.executeMethod(method);
			response.setCode(returnCode);
			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				logger.error("The Post method is not implemented by this URI");
				// still consume the response body
				response.setResponse(method.getResponseBody());

			} else {
				
				IOUtils.copy(
						method.getResponseBodyAsStream(), baos);				
				baos.flush();
				response.setResponse(baos.toByteArray());
				
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			method.releaseConnection();
		}
		return response;

	}
	
	public static ClientResponse get(String endpoint,
			Map<String, String> params, Map<String, String> headers) {
		ClientResponse response = new ClientResponse();
		response.setCode(500);
		HttpClient client = new HttpClient();

		BufferedReader br = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		GetMethod method = new GetMethod(endpoint);
		
		//headers
		if(headers != null && headers.size()>0)
		{
			for (Entry<String,String> entry : headers.entrySet()) {
				
				method.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		
		//params
		if(params != null && params.size()>0)
		{
			NameValuePair[] paramsNV = new NameValuePair[params.size()];
			int index=0;
			for (Entry<String,String> entry : params.entrySet()) {
				paramsNV[index] = new NameValuePair(entry.getKey(), entry.getValue());
				index++;
			}
			method.setQueryString(paramsNV);
		}
		
		try {
			
			logger.debug(method.getURI().toString());
			
			int returnCode = client.executeMethod(method);
			response.setCode(returnCode);
			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				logger.error("The GET method is not implemented by this URI");
				// still consume the response body
				response.setResponse(method.getResponseBody());

			} else {
				
				IOUtils.copy(
						method.getResponseBodyAsStream(), baos);				
				baos.flush();
				response.setResponse(baos.toByteArray());
				
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			method.releaseConnection();
		}
		return response;

	}


}
