package com.extjs.serverside.form;

public class Field extends Component {

	public Field(String javascriptClass, String fieldName) {
		super(javascriptClass, fieldName);
		setRequestParamName(fieldName);
	}

	public Field(String javascriptClass, String fieldName, String label) {
		super(javascriptClass, fieldName);
		setRequestParamName(fieldName);
		setLabel(label);
	}

	

	public void setRequestParamName(String value) {
		setValue("name", value);
	}

	public void setLabel(String value) {
		setValue("fieldLabel", value);
	}

	public void setWidth(int width) {
		setValue("width", Integer.valueOf(width));
	}

	@Override
	public String renderAddToContainer(RenderableContainer form) {
		return form.getFieldName() + ".add(" + fieldName + ");\n";
	}
}
