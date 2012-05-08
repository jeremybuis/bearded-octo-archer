package org.cmpa.web;

import com.sun.faces.facelets.tag.TagHandlerImpl;
import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import org.cmpa.web.ResourceBundleMap;

public final class LoadBundleHandler
		extends TagHandlerImpl
{

	private final TagAttribute basename;
	private final TagAttribute var;

	/**
	 * @param config
	 */
	public LoadBundleHandler(TagConfig config)
	{
		super(config);
		this.basename = this.getRequiredAttribute("basename");
		this.var = this.getRequiredAttribute("var");
	}

	/**
	 * See taglib documentation.
	 *
	 * @see com.sun.faces.facelets.FaceletHandler#apply(com.sun.faces.facelets.FaceletContext,
	 * javax.faces.component.UIComponent)
	 */
	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException
	{
		FacesContext faces = ctx.getFacesContext();
		ResourceBundleMap bundleMap = new ResourceBundleMap(this.basename.getValue(ctx), faces.getViewRoot().getLocale());
		ExternalContext ec = faces.getExternalContext();
		Map<String,Object> requestMap = ec.getRequestMap();
		requestMap.put(this.var.getValue(ctx), bundleMap);
	}
}