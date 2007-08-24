package com.extjs.serverside.table;

public class Utils {

	public final static ColumnRenderer BOLD_COLUMN = new CustomRenderer("bold",
			"return '<b>'+value+'</b>';");

	public static ColumnRenderer getDateFormatRenderer(String format) {
		return new ColumnRenderer("Ext.util.Format.dateRenderer('"+format+"')");
	}
	
	
	public static String getDataIndex(TableColumn[] tc, int cellPosition) {
		String tagName = tc[cellPosition].getDataIndex();
		if (tagName == null) {
			tagName = "cell" + cellPosition;
		}
		return tagName;
	}

	public static String getInitializer(TableColumn tc, int cellPosition) {
		String definition = tc.getDefinition();
		if (definition==null) {
			// it's a hidden column, no need to initalize the column model.
			return "";
		}
		String dataIndex = tc.getDataIndex();
		if (dataIndex == null) {
			dataIndex = "cell" + cellPosition;
		}
		return (cellPosition > 0 ? ",{" : "{") + definition
				+ ", dataIndex:\"" + dataIndex + "\"}";
	}
	
}
