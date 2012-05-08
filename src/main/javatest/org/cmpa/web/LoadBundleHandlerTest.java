package org.cmpa.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.*;
import org.easymock.EasyMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(
{
	Tag.class, FacesContext.class
})
public class LoadBundleHandlerTest
{

	private FacesContext facesContext;
	private Map<String, Object> requestMap;
	private UIViewRoot root;

	@Before
	public void setUp() throws Exception
	{
		PowerMock.mockStatic(FacesContext.class);
		facesContext = EasyMock.createMock(FacesContext.class);
		root = EasyMock.createMock(UIViewRoot.class);
		EasyMock.expect(facesContext.getViewRoot()).andReturn(root);
		EasyMock.expect(root.getLocale()).andReturn(Locale.ENGLISH);
		requestMap = new HashMap<String, Object>();
	}

	@Test
	public void testAddResourceBundleMapToFacesContextRequestMap() throws IOException
	{
		simulateTagWithAttributes("pgmsgs", "pages.index.loadBundle");
		assertNotNull(requestMap.get("pgmsgs"));
	}

	
	@Test
	public void testAddFrenchResourceBundleMap() throws IOException
	{
		EasyMock.reset(root);
		EasyMock.expect(root.getLocale()).andReturn(Locale.FRENCH);
		simulateTagWithAttributes("pgmsgs", "pages.index.loadBundle");
		ResourceBundleMap map = (ResourceBundleMap) requestMap.get("pgmsgs");
		
		assertEquals("test text french", map.get("section1.txt"));
	}

	@Ignore
	@Test(expected = NullPointerException.class)
	public void testAddNonExistentResourceBundleMap() throws IOException
	{
		simulateTagWithAttributes("pgmsgs", "nonexistent");
	}

	private void simulateTagWithAttributes(String var, String basename) throws IOException
	{
		Tag tag = PowerMock.createMock(Tag.class);
		TagAttributes tagAttributes = EasyMock.createMock(TagAttributes.class);
		TagAttribute basenameTagAttribute = EasyMock.createMock(TagAttribute.class);
		TagAttribute varTagAttribute = EasyMock.createMock(TagAttribute.class);
		TagConfig config = EasyMock.createMock(TagConfig.class);
		EasyMock.expect(config.getTagId()).andReturn("testId");
		EasyMock.expect(config.getNextHandler()).andReturn(null);
		EasyMock.expect(config.getTag()).andReturn(tag);
		EasyMock.expect(tag.getAttributes()).andReturn(tagAttributes).times(2);
		EasyMock.expect(tagAttributes.get("basename")).andReturn(basenameTagAttribute);
		EasyMock.expect(tagAttributes.get("var")).andReturn(varTagAttribute);

		FaceletContext ctx = EasyMock.createMock(FaceletContext.class);

		EasyMock.expect(ctx.getFacesContext()).andReturn(facesContext);
		EasyMock.expect(varTagAttribute.getValue(ctx)).andReturn(var);
		EasyMock.expect(basenameTagAttribute.getValue(ctx)).andReturn(basename);

		ExternalContext ec = EasyMock.createMock(ExternalContext.class);
		EasyMock.expect(facesContext.getExternalContext()).andReturn(ec);

		EasyMock.expect(ec.getRequestMap()).andReturn(requestMap);



		UIComponent parent = EasyMock.createMock(UIComponent.class);
		PowerMock.replayAll(tag, tagAttributes, config, varTagAttribute, basenameTagAttribute, ctx, ec, facesContext, root);

		LoadBundleHandler lbh = new LoadBundleHandler(config);
		lbh.apply(ctx, parent);
	}
}
