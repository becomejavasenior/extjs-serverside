package com.extjs.serverside.table;

public class DateColumn extends SimpleTextColumn {

	String dateFormat = "Y:d:m";
	
	public DateColumn(String headerName) {
		super(headerName);
	}
	
	
	
	
	public DateColumn(String headerName, String width, ColumnRenderer renderer) {
		super(headerName, width, renderer);
	}




	public DateColumn(String headerName, String width) {
		super(headerName, width);
	}




	/**
	 * The date format in some stupid javascript notation, eg 'Y-d-m'
	 * @param dateFormat
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}
	
	
	@Override
	public String getDefinition() {
		return super.getDefinition() +",type: 'date', dateFormat: '"+dateFormat+"'";
	}

}
