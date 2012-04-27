package com.noi.utility.text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * simple template engine
 * 
 * String template = ResourceUtils.loadResToString(
				R.raw.subscription_momentlist_item, 
				ctx);			
		try {
			Jtpl templateEngine = new Jtpl(template);
			templateEngine.assign("TITLE", m.getName());
			templateEngine.assign("DESCR", m.getDescription());
			templateEngine.parse("moment");
			return templateEngine.out();
		} catch (IOException e) {
			Log.e(TAG,"problem rendering moment");
		}
		return "";
*/

public class Template 
{
	private HashMap blocks = new HashMap();
	private HashMap parsedBlocks = new HashMap();
	private HashMap subBlocks = new HashMap();
	private HashMap vars = new HashMap();
	
	private String blockName;
	

	/**
	 * 
	 * dont friggin hard code the way to get the template in!
	 * 
	 * @param tempateText
	 * @throws IOException
	 */
	public Template(String tempateText) throws IOException
	{
		makeTree(tempateText);
	}
	
	/**
	* Assign a template variable.
	* For variables that are used in blocks, the variable value
	* must be set before <code>parse</code> is called.
	* @param varName the name of the variable to be set.
	* @param varData the new value of the variable.
	*/
	public void assign(String varName, String varData)
	{
		vars.put(varName, varData);
	}
	
	/**
	* Generates the HTML page and return it into a String.
	*/
	public String out()
	{
		return(parsedBlocks.get(blockName).toString());
	}
	
	/**
	* Parse a template block.
	* If the block contains variables, these variables must be set
	* before the block is added.
	* If the block contains subblocks, the subblocks
	* must be parsed before this block.
	* @param blockName the name of the block to be parsed.
	*/
	public void parse(String mblockName)
	{
		this.blockName = mblockName;
		
		String copy = "";
		try {
			copy = blocks.get(blockName).toString();
		} catch (NullPointerException e) {
			
		}
		Pattern pattern = Pattern.compile("\\{([\\w\\.]+)\\}");
		Matcher matcher = pattern.matcher(copy);
		pattern = Pattern.compile("_BLOCK_\\.(.+)");
		for (Matcher matcher2; matcher.find();)
		{
			String match = matcher.group(1);
			matcher2 = pattern.matcher(match);
			if (matcher2.find())
			{
				if (parsedBlocks.containsKey(matcher2.group(1)))
				{
					copy = copy.replaceFirst("\\{"+match+"\\}", parsedBlocks.get(matcher2.group(1)).toString());
				}
				else
				{
					copy = copy.replaceFirst("\\{"+match+"\\}", "");
				}
			}
			else
			{
				if (vars.containsKey(match))
				{
					copy = copy.replaceFirst("\\{"+match+"\\}", vars.get(match).toString());
				}
				else
				{
					copy = copy.replaceFirst("\\{"+match+"\\}", "");
				}
			}
		}
		if (parsedBlocks.containsKey(blockName))
		{
			parsedBlocks.put(blockName, parsedBlocks.get(blockName) + copy);
		}
		else
		{
			parsedBlocks.put(blockName, copy);
		}
		if (subBlocks.containsKey(blockName))
		{
			parsedBlocks.put(subBlocks.get(blockName), "");
		}
	}
	
	private String readFile(String fileName) throws IOException
	{
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		StringBuffer content = new StringBuffer();
		for (int c; (c = fr.read()) != -1; content.append((char)c));
		fr.close();
		return content.toString();
	}
	
	private void makeTree(String fileText)
	{
		Pattern pattern = Pattern.compile("<!--\\s*(BEGIN|END)\\s*:\\s*(\\w+)\\s*-->(.*?)(?=(?:<!--\\s*(?:BEGIN|END)\\s*:\\s*\\w+\\s*-->)|(?:\\s*$))", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(fileText);
		ArrayList blockNames = new ArrayList();
		String parentName = "";
		while (matcher.find())
		{
			if (matcher.group(1).toUpperCase().equals("BEGIN"))
			{
				parentName = implode(blockNames);
				blockNames.add(matcher.group(2));
				String currentBlockName = implode(blockNames);
				if (blocks.containsKey(currentBlockName))
				{
					blocks.put(currentBlockName, blocks.get(currentBlockName) + matcher.group(3));
				}
				else
				{
					blocks.put(currentBlockName, matcher.group(3));
				}
				if (blocks.containsKey(parentName))
				{
					blocks.put(parentName, blocks.get(parentName) + "{_BLOCK_." + currentBlockName + "}");
				}
				else
				{
					blocks.put(parentName, "{_BLOCK_." + currentBlockName + "}");
				}
				subBlocks.put(parentName, currentBlockName);
				subBlocks.put(currentBlockName, "");
			}
			else if (matcher.group(1).toUpperCase().equals("END"))
			{
				blockNames.remove(blockNames.size()-1);
				parentName = implode(blockNames);
				if (blocks.containsKey(parentName))
				{
					blocks.put(parentName, blocks.get(parentName) + matcher.group(3));
				}
				else
				{
					blocks.put(parentName, matcher.group(3));
				}
			}
		}
	}
	
	private String implode(ArrayList al)
	{
		String ret = "";
		for (int i = 0; al.size() > i; i++)
		{
			if (i != 0)
			{
				ret += ".";
			}
			ret += al.get(i);
		}
		return (ret);
	}
}