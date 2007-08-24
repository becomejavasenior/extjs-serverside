package com.extjs.serverside.form;

import java.util.ArrayList;
import java.util.List;

public class Form extends Component {

	List<Renderable> fieldList = new ArrayList<Renderable>();
	
	List<Renderable> initScript = new ArrayList<Renderable>();

	public Form(String fieldName) {
		super("Ext.form.Form", fieldName);

		setLabelWidth(100);
		setLabelAlign("right");
		setButtonAlign("right");
	}

	/**
	 * 'left' or 'right'
	 * 
	 * @param string
	 */
	public void setLabelAlign(String string) {
		setValue("labelAlign", string);
	}

	public void setLabelWidth(int width) {
		setValue("labelWidth", width);
	}

	public void setButtonAlign(String string) {
		setValue("buttonAlign", string);
	}

	public void addField(Field field) {
		fieldList.add(field);
	}

	public void addFieldSet(String fieldSetName, Field... fields) {
		FieldSet fset = new FieldSet(fieldSetName);
		for (Field f : fields) {
			fset.add(f);
		}
		fieldList.add(fset);
	}
	
	public void addInitScript(final String script) {
		initScript.add(new Renderable() {
			public String renderAddToForm(Form form) {
				return null;
			}
			public String renderComponent() {
				return script;
			}
		});
	}
	

	@Override
	public String renderComponent() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ext.onReady(function(){\n");
		
		for (Renderable r : fieldList) {
			builder.append(r.renderComponent());
		}
		builder.append(renderJavascriptConstructor());

		for (Renderable r : fieldList) {
			builder.append(r.renderAddToForm(this)).append('\n');
		}
		
		
		
		for (Renderable r : initScript) {
			builder.append(r.renderComponent()).append('\n');
		}
		builder.append("})\n");
		return builder.toString();
	}

	class FieldSet implements Renderable {

		String label;

		List<Field> fields = new ArrayList<Field>();

		public FieldSet(String label) {
			this.label = label;
		}

		public FieldSet add(Field field) {
			fields.add(field);
			return this;
		}

		public List<Field> getFields() {
			return fields;
		}

		public String renderComponent() {
			StringBuilder s = new StringBuilder();
			for (Field field : fields) {
				s.append(field.renderComponent());
			}
			s.append('\n');
			return s.toString();
		}

		public String renderAddToForm(Form form) {
			String s = form.fieldName + ".fieldset(" + "{legend:'" + label + "'},\n";
			
			for (int i=0;i<fields.size(); i++) { 
				Field field = fields.get(i);
				if (i>0) {
					s += ",\n";
				}
				s += field.fieldName;
			}
			s += "\n);";
			return s;
		}

	}

}
