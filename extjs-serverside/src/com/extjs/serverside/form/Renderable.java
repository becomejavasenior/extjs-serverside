package com.extjs.serverside.form;

/**
 * Base interface for renderable components.
 * 
 * @author zsombor
 *
 */
public interface Renderable {

	/**
	 * This method should return the javascript fragment which creates the object.
	 * @return
	 */
	public String renderComponent();
	
	
	/**
	 * This method should return the javascript fragment which adds the component to the container.
	 * @param form
	 * @return
	 */
	public String renderAddToContainer(RenderableContainer form);
	
}
