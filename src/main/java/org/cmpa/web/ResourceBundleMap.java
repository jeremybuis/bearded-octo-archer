package org.cmpa.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.*;

public class ResourceBundleMap
		implements Map
{

	private final static class ResourceEntry
			implements Map.Entry
	{

		protected final String key;
		protected final String value;

		public ResourceEntry(String key, String value)
		{
			this.key = key;
			this.value = value;
		}

		@Override
		public Object getKey()
		{
			return this.key;
		}

		@Override
		public Object getValue()
		{
			return this.value;
		}

		@Override
		public Object setValue(Object value)
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public int hashCode()
		{
			return this.key.hashCode();
		}

		@Override
		public boolean equals(Object obj)
		{
			return (obj instanceof ResourceEntry && this.hashCode() == obj.hashCode());
		}
	}

	private Properties pageMessages;
	private String filePath;
	private String basename;

	public ResourceBundleMap(String path, Locale locale)
	{
		// String langExtension = ".properties";
		// if ( Locale.FRENCH.equals(locale) )
		// {
		// 	langExtension = "_fr.properties";
		// }
		String langExtension = (Locale.FRENCH.equals(locale)) ? "_fr.properties" : ".properties";

		basename = path;
		this.filePath = path.replace(".", "/").concat(langExtension);

		loadPropertiesFile();
		if ( pageMessages == null )
		{
			langExtension = ".properties";
			this.filePath = path.replace(".", "/").concat(langExtension);
			loadPropertiesFile();
		}		
	}
	
	private void loadPropertiesFile()
	{
		Properties p = null;
		try
		{
			URL url = Thread.currentThread().getContextClassLoader().getResource(this.filePath);
			File f = new File(url.toURI());
			
			p = new Properties();

			FileInputStream fis = new FileInputStream(f);
			p.load(fis);

			fis.close();

		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		this.pageMessages = p;
	}
	
	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key)
	{
		try
		{
			pageMessages.getProperty(key.toString());
			return true;
		}
		catch ( MissingResourceException e )
		{
			return false;
		}
	}

	@Override
	public boolean containsValue(Object value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Set entrySet()
	{
		Enumeration e = this.pageMessages.keys();
		Set s = new HashSet();
		String k;
		while ( e.hasMoreElements() )
		{
			k = (String) e.nextElement();
			s.add(new ResourceEntry(k, this.pageMessages.getProperty(k)));
		}
		return s;
	}

	@Override
	public Object get(Object key)
	{
		try
		{
			return this.pageMessages.getProperty((String) key);
		}
		catch ( java.util.MissingResourceException mre )
		{
			return "???" + key + "???";
		}
	}

	public String getBasename()
	{
		return basename;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public Set keySet()
	{
		Enumeration e = this.pageMessages.keys();
		Set s = new HashSet();
		while ( e.hasMoreElements() )
		{
			s.add(e.nextElement());
		}
		return s;
	}

	//saves the information into the map
	@Override
	public Object put(Object key, Object value)
	{	
		Object old = this.pageMessages.getProperty((String)key);
		this.pageMessages.setProperty((String)key, (String)value);

		return old;
	}
	
	//writes to the properties file
	public void save(){
		
		try {

			URL url = Thread.currentThread().getContextClassLoader().getResource(this.filePath);
			File f = new File(url.toURI());

			FileOutputStream fos = new FileOutputStream(f);
			
			this.pageMessages.store(fos, null);

			fos.close();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void putAll(Map t)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int size()
	{
		return this.keySet().size();
	}

	@Override
	public Collection values()
	{
		Enumeration e = this.pageMessages.keys();
		Set s = new HashSet();
		while ( e.hasMoreElements() )
		{
			s.add(this.pageMessages.getProperty((String) e.nextElement()));
		}
		return s;
	}
}