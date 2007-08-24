package com.extjs.serverside.table;

/**
 * This class encapsulates a javascript function which is used by extjs to render a column. 
 * The valid variables which can be used by the javascript are the followings: 
 *  value,cell,record,rowIndex,columnIndex,store 
 *  
 * @author zsombor
 *
 */
public class CustomRenderer extends ColumnRenderer {

	String javascript;
	
	/**
	 * Creates a javascript function which can be used as a column renderer.
	 * @param name the name of the renderer function
	 * @param javascript the javascript statement which should return a html fragment which should be rendered.
	 */
	public CustomRenderer(String name, String javascript) {
		super(name);
		this.javascript = javascript;
	}
	
	public String getJavascript() {
		return javascript;
	}
	

}
