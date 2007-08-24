/**
 * 
 */
package com.extjs.serverside.form;

/**
 * @author zsombor
 *
 */
public class DateField extends Field {
	final static String FIELD_CLASS = "Ext.form.DateField";

	/**
	 * @param fieldName
	 */
	public DateField(String fieldName) {
		super(FIELD_CLASS, fieldName);
	}

	public DateField(String fieldName, String label) {
		super(FIELD_CLASS, fieldName, label);
	}

	public void setAllowBlank(boolean flag) {
		setValue("allowBlank", Boolean.valueOf(flag));
	}
	
	/**
	 * the date format, in a 'PHP' style format string. Eg: Y-d-m
	 * @param s
	 */
	public void setFormat(String s) {
		setValue("format", s);
	}
	
}
