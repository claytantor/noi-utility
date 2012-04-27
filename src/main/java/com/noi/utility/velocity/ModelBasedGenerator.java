package com.noi.utility.velocity;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;


public class ModelBasedGenerator extends VelocityBaseGenerator  {
	
	private Map model;

	

	public String makeDisplayString() {
		logger.debug("makeDisplayString");
		StringBuffer buf = new StringBuffer();
		try{			
			buf =  super.generateFromModelAndTemplate(makeContext(), super.getTemplate());
		}
		catch(Exception e)
		{
			logger.debug("makeDisplayString error",e);
		}
		return buf.toString();
	}
	
	public void writeOutput(Writer out) throws IOException
	{
		super.setOut(out);
		super.getOut().write(this.makeDisplayString());
		super.getOut().flush();
		super.getOut().close();
	}	
		
	private File doCreate(String path) {

        // Create a File object
        File file = new File(path);

        boolean success = false;

        try {
            // Create file on disk (if it doesn't exist)
            success = file.createNewFile();
        } catch (IOException e) {
           logger.debug("cannot create file",e);
        }

        if (success) {
            logger.debug("File did not exist and was created.\n");
        } else {
        	logger.debug("File already exists.\n");
        }
        
        return file;

    }
	
	

	public VelocityContext makeContext()
	{		
		VelocityContext vcontext = super.getVcontext();
		//iterate over model and add it
		Collection<String> keys = model.keySet();
		for (String key : keys) {
			vcontext.put(key, model.get(key));
		}
		
		return vcontext;
	}


	public Map getModel() {
		return model;
	}


	public void setModel(Map model) {
		
		//add tools to model
		org.apache.velocity.tools.generic.NumberTool numberTool = new NumberTool();
		model.put("numberTool", numberTool);
		model.put("dateTool", new DateTool());
				
		this.model = model;
	}


}
