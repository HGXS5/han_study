package cn.han.workdemo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@fileName   : PropertyManager.java
 *@description: TODO
 */
public class PropertyManager {
	//private static String SPERATOR = ClassLoader.getSystemResource("").toString();
	private static PropertyManager manager = null;
	private static Object managerLock = new Object();
	private static String propsName = "/application.properties";
	public static String getProperty(String name) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.getProp(name);
	}
	public static void setProperty(String name, String value) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		manager.setProp(name, value);
	}
	public static void deleteProperty(String name) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		manager.deleteProp(name);
	}
	public static Enumeration<?> propertyNames() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propNames();
	}
	public static boolean propertyFileIsReadable() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileIsReadable();
	}
	public static boolean propertyFileIsWritable() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileIsWritable();
	}
	public static void reloadProperties() {
		if (manager != null) {
			manager.reloadProp();
		}
	}
	public static boolean propertyFileExists() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileExists();
	}
	private Properties properties = null;
	private Object propertiesLock = new Object();
	private String resourceURI;
	private PropertyManager(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	protected String getProp(String name) {
		if (properties == null) {
			synchronized (propertiesLock) {
				if (properties == null) {
					loadProps();
				}
			}
		}
		String property = properties.getProperty(name);
		if (property == null) {
			return null;
		} else {
			return property.trim();
		}
	}
	protected void setProp(String name, String value) {
		synchronized (propertiesLock) {
			if (properties == null) {
				loadProps();
			}
			properties.setProperty(name, value);
			saveProps();
		}
	}
	protected void deleteProp(String name) {
		synchronized (propertiesLock) {
			if (properties == null) {
				loadProps();
			}
			properties.remove(name);
			saveProps();
		}
	}
	protected Enumeration<?> propNames() {
		if (properties == null) {
			synchronized (propertiesLock) {
				if (properties == null) {
					loadProps();
				}
			}
		}
		return properties.propertyNames();
	}
	protected void reloadProp() {
		if (properties != null) {
			synchronized (propertiesLock) {
				if (properties != null) {
					loadProps();
				}
			}
		}
	}
	private void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(resourceURI);

			/******/
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));   
			String s = null;  
			String str = "";
			properties.load(getClass().getResourceAsStream(resourceURI));
			
			while((s=bf.readLine())!= null)   
			{
				String regex = "\\$\\{[^\\$\\{\\}]+\\}";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(s);
				while(matcher.find())
				{
					String tempBe = matcher.group();
					String tempAF = tempBe.substring(2,tempBe.length()-1);
					s = s.replaceFirst(regex, properties.getProperty(tempAF));
				}		
				
				str = str.concat(s).concat("\n");
			}

			InputStream  inputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			properties.load(bufferedReader);
			/******/
			
		} catch (Exception e) {
			System.err
					.println("Error reading Application properties in PropertyManager.loadProps() "
							+ e);
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
	private void saveProps() {
		String path = "";
		OutputStream out = null;
		try {
			path = getClass().getResource(resourceURI).toString().substring(5).replaceAll("%20"," ");

			out = new FileOutputStream(path);
			properties.store(out, "application.properties -- "
					+ (new java.util.Date()));
		} catch (Exception ioe) {
			System.err
					.println("There was an error writing application.properties to "
							+ path
							+ ". "
							+ "Ensure that the path exists and that the Application process has permission "
							+ "to write to it -- " + ioe);
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
	public boolean propFileIsReadable() {
		try {
			getClass().getResourceAsStream(resourceURI);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean propFileExists() {
		String path = getProp("path");
		if (path == null) {
			return false;
		}
		File file = new File(path);
		if (file.isFile()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean propFileIsWritable() {
		String path = getProp("path");
		File file = new File(path);
		if (file.isFile()) {

			if (file.canWrite()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(PropertyManager.getProperty("ChongQing_VoOD_WebPublish_url"));
	}
}
