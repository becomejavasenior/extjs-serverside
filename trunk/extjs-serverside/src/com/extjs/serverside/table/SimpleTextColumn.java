package com.extjs.serverside.table;

public class SimpleTextColumn implements TableColumn {

	String dataIndex;

	String headerName;

	String width;

	boolean sortable = true;

	boolean resizable = true;

	ColumnRenderer renderer;

	public SimpleTextColumn(String headerName) {
		super();
		this.headerName = headerName;
	}
	
	
	

	public SimpleTextColumn(String headerName, String width, ColumnRenderer renderer) {
		super();
		this.headerName = headerName;
		this.width = width;
		this.renderer = renderer;
	}




	public SimpleTextColumn(String headerName, String width) {
		super();
		this.headerName = headerName;
		this.width = width;
	}




	public ColumnRenderer getRenderer() {
		return renderer;
	}

	public void setRenderer(ColumnRenderer renderer) {
		this.renderer = renderer;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getWidth() {
		return width;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public boolean isResizable() {
		return resizable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setDataIndex(String dataIndex) {
		this.dataIndex = dataIndex;
	}

	public String getDataIndex() {
		return dataIndex;
	}

	public String getDefinition() {
		String s = "header: \"" + headerName + "\"";
		if (width != null) {
			s += ",width : '" + width + "'";
		}
		s += ",sortable : " + sortable;
		s += ",resizable : " + resizable;
		if (renderer!=null) {
			s += ", renderer : "+renderer.getName()+' ';
		}
		return s;
	}

}
