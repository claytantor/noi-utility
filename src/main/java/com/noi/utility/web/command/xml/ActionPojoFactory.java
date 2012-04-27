package com.noi.utility.web.command.xml;

import java.util.Map;

public interface ActionPojoFactory {
	public Object makePojoFromNoun(Noun n,Map pojoFactoryMap);
}
