package com.extjs.serverside.table;

import java.io.IOException;

public abstract class Table {

	public abstract TableColumn[] getColumns();
	
	public abstract void renderData(TableDataRequest request) throws IOException;
	
	public ColumnRenderer[] getRenderers() {
		return new ColumnRenderer[0];
	}
}
