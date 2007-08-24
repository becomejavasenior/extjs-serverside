package com.extjs.serverside.table;

import com.extjs.serverside.form.Component;

public class PagingToolbar extends Component {

	String dataSourceName;
	HeaderFooterPanel panel;

	public PagingToolbar(HeaderFooterPanel panel,String fieldName) {
		super("Ext.PagingToolbar", "TEMP_PAGING_TOOLBAR");
		this.panel = panel;
	}


	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	
	@Override
	public String getFieldName() {
		return panel.getVariableName() + "_paging";
	}

	@Override
	public String renderConstructorParameters() {
		return panel.getVariableName() + "," + dataSourceName + ",";
	}
	
	public void setPageSize(int ps) {
		setValue("pageSize", String.valueOf(ps));
	}

	public void setDisplayInfo(boolean flag) {
		setValue("displayInfo", Boolean.valueOf(flag));
	}

	/**
	 * Display message, for example: 'total {2} results found. Current shows {0} - {1}'
	 * @param msg
	 */
	public void setDisplayMessage(String msg) {
		setValue("displayMsg", msg);
	}
	
	
	/**
	 * Display message when the table is empty, for example: 'no result to display'
	 * @param msg
	 */
	public void setEmptyMessage(String msg) {
		setValue("emptyMsg", msg);
	}
	

}
