<%@page import="com.extjs.serverside.table.*" contentType="application/x-javascript"%>
<%
    String tableName = (String) request.getAttribute("tableName");
	Table table = (Table) request.getAttribute("table");
	String basePath = (String) request.getAttribute("basePath");
	TableColumn[] tc = table.getColumns();
	ColumnRenderer[] cr = table.getRenderers();
%>

Ext.onReady(function(){

var <%= tableName %>_dataStore = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({url: '<%= basePath %>/data.xml'}),
        reader: new Ext.data.XmlReader({
               record: 'row',
           }, [
<% for (int i=0;i<tc.length;i++) {  %>
          <% if (i>0) { %>,<% } %>'<%= Utils.getDataIndex(tc,i) %>'
<% } %>          
           ])
    });
    
var <%= tableName %>_colModel = new Ext.grid.ColumnModel([
<% for (int i=0;i<tc.length;i++) {  %>
		<%= Utils.getInitializer(tc[i],i) %>
<% } %>
	]);
//	<%= tableName %>_colModel.defaultSortable = true;
<%

	for (int i=0;i<cr.length;i++) {
		if (cr[i] instanceof CustomRenderer) {
%>	          function <%= cr[i].getName() %>(value,cell,record,rowIndex,columnIndex,store) {
                <%=  ((CustomRenderer)cr[i]).getJavascript() %>
		}
<%			}	
	
		} %>				

var <%= tableName %>_grid = new Ext.grid.Grid('<%= tableName %>', {
        ds: <%= tableName %>_dataStore,
        cm: <%= tableName %>_colModel
    });
    
<% if (table instanceof PagedTable) { 
		PagedTable pagedTable = (PagedTable) table;
		HeaderFooterPanel header = pagedTable.getHeaderPanel();
		HeaderFooterPanel footer = pagedTable.getFooterPanel();
%>	
		<%= header!=null ? header.renderAddToContainer(table) : "" %>
		<%= footer!=null ? footer.renderAddToContainer(table) : "" %>

<% } %>    
    
<%= tableName %>_grid.render();

<%= tableName %>_dataStore.load();	
});   