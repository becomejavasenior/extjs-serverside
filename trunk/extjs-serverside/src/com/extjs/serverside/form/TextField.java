/**
 * 
 */
package com.extjs.serverside.form;

/**
 * @author zsombor
 *
 */
public class TextField extends Field {

	final static String FIELD_CLASS = "Ext.form.TextField";

	/**
	 * @param fieldName
	 */
	public TextField(String fieldName) {
		super(FIELD_CLASS,fieldName);
	}
	
	
	public TextField(String fieldName,String label) {
		super(FIELD_CLASS,fieldName, label);
	}
	


}
