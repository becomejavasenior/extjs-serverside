package com.extjs.serverside.table;

public class HiddenColumn implements TableColumn {

	String name;
	
	
	
	public HiddenColumn(String name) {
		super();
		this.name = name;
	}

	public String getDataIndex() {
		return name;
	}

	public String getDefinition() {
		return null;
	}

}
