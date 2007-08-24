var MainTabs = {
    init : function(){
        // basic tabs 1, built from existing content
        var tabs = new Ext.TabPanel('tabDiv');
        tabs.addTab('tab1', "Tab 1");
        tabs.addTab('tab2', "Tab 2");
        tabs.activate('tab1');
    }
}
Ext.EventManager.onDocumentReady(MainTabs.init, MainTabs, true);    