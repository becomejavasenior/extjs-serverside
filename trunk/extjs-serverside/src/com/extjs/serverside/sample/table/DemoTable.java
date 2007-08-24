/**
 * 
 */
package com.extjs.serverside.sample.table;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.extjs.serverside.table.ColumnRenderer;
import com.extjs.serverside.table.CustomRenderer;
import com.extjs.serverside.table.DateColumn;
import com.extjs.serverside.table.HiddenColumn;
import com.extjs.serverside.table.SimpleTextColumn;
import com.extjs.serverside.table.Table;
import com.extjs.serverside.table.TableColumn;
import com.extjs.serverside.table.TableDataRequest;
import com.extjs.serverside.table.Utils;


/**
 * @author zsombor
 * 
 */
public class DemoTable extends Table {

	final TableColumn[] COLUMNS;

	public DemoTable() {
		COLUMNS = new TableColumn[5];
		COLUMNS[0] = new SimpleTextColumn("First name", "200", ALERT_RENDERER);
		COLUMNS[1] = new SimpleTextColumn("Last name", null, Utils.BOLD_COLUMN);
		COLUMNS[2] = new DateColumn("Birth day", null, Utils
				.getDateFormatRenderer("Y.m.d."));
		COLUMNS[3] = new SimpleTextColumn("Gender");
		COLUMNS[4] = new HiddenColumn("alert");
		
	}

	final String[] FIRSTNAMES = new String[] { "Kovács", "Szabó", "Horváth",
			"Kiss" };

	final String[] LASTNAMES = new String[] { "István", "Béla", "Gyula",
			"Péter", "Gábor" };

	final String[] GENDERS = new String[] { "férfi", "nő" };

	final static ColumnRenderer ALERT_RENDERER = new CustomRenderer(
			"alertRenderer", 
					 "if (record.data.alert=='true') { \n"
					+ "   return '<font color=\\'red\\'>'+value+'</font>'\n"
					+ " } else {\n" 
					+ "   return value;\n" 
					+ " }");

	@Override
	public ColumnRenderer[] getRenderers() {
		return new ColumnRenderer[] { Utils.BOLD_COLUMN, ALERT_RENDERER };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.extjs.serverside.table.Table#getColumns()
	 */
	@Override
	public TableColumn[] getColumns() {
		return COLUMNS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.extjs.serverside.table.Table#renderData(com.extjs.serverside.table.TableDataRequest)
	 */
	@Override
	public void renderData(TableDataRequest request) throws IOException {
		request.startResponse(100);
		Random rnd = new Random();
		long now = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			request.startRow();
			request.writeCell(FIRSTNAMES[rnd.nextInt(FIRSTNAMES.length)]);
			request.writeCell(LASTNAMES[rnd.nextInt(LASTNAMES.length)]);
			// request.writeCell("ize");
			request.writeCell(new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date(rnd.nextLong() % now)));
			request.writeCell(GENDERS[rnd.nextInt(GENDERS.length)]);
			request.writeCell(Boolean.valueOf(i % 5 == 0));
		}
		request.endResponse();
	}

}
