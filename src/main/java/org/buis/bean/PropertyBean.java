package org.buis.bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.buis.web.ResourceBundleMap;

@ManagedBean(name = "propertyBean")
@RequestScoped
public class PropertyBean
		implements Serializable
{

	private String bundle;
	private String property;
	private String content;

	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
		if ( property != null && bundle != null )
		{
			save();
		}
	}

	public String getBundle()
	{
		ResourceBundleMap map = (ResourceBundleMap) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().
				get("pgmsgs");
		if ( map != null )
		{
			return map.getBasename();
		}
		return bundle;
	}

	public void setBundle(String bundle)
	{
		this.bundle = bundle;
		if ( property != null && content != null )
		{
			save();
		}
	}

	public String getProperty()
	{
		return this.property;
	}

	public void setProperty(String property)
	{
		this.property = property;
		if ( bundle != null && content != null )
		{
			save();
		}
	}

	public void save()
	{

		System.out.println("we get to save our content");

		FacesContext faces = FacesContext.getCurrentInstance();
		ResourceBundleMap currentBundle = new ResourceBundleMap(bundle, faces.getViewRoot().getLocale());
		currentBundle.put(property.replace("-", "."), content);
		currentBundle.save();
	}

	//Helper method
	public String convertPropertyToId(String property)
	{
		return property.replace(".", "-");
	}
}
