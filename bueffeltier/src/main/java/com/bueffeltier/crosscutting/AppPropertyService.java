package com.bueffeltier.crosscutting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Lädt Eigenschaften und Einstellungen der App aus der properies Datei. TODO:
 * Wie speichere ich Settings? key, value
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class AppPropertyService
{
	private static AppPropertyService instance;

	private static Properties properties;

	private String filePath = "C:\\git-repository\\bueffeltier\\application.properties";

	private AppPropertyService()
	{
		properties = new Properties();
		try
		{
			load();

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			// TODO sveng 25.11.2022: Fehlerseite laden.
		}
	}

	public static AppPropertyService getInstance()
	{
		if (instance == null)
		{
			instance = new AppPropertyService();
		}
		return instance;
	}

	public void load() throws FileNotFoundException, IOException
	{
		try (InputStream inputStream = new FileInputStream(filePath))
		{
			properties.load(inputStream);
		}
		// TODO sveng 25.11.2022: Fehlerseite laden
	}

	public void save() throws FileNotFoundException, IOException
	{
		try (final OutputStream outputstream = new FileOutputStream(filePath))
		{
			properties.store(outputstream, "File Updated");

		} catch (IOException e)
		{
			System.err.println("Error saving properties: " + e.getMessage());
			throw e;
		}
		// TODO sveng 25.11.2022: fehlermeldung bei fail
	}

	public void setStringProperty(String key, String value)
	{
		properties.setProperty(key, value);
	}

	public void setBooleanProperty(String key, String value)
	{
		if (value.equals("true") || value.equals("false"))
		{
			properties.setProperty(key, "true");
		}
	}

	public boolean hasProperty(String key)
	{
		return properties.containsKey(key);
	}

//	private Set<String> getAllPropertyNames()
//	{
//		return properties.stringPropertyNames();
//	}

	public Set<Entry<Object, Object>> getAll()
	{
		return properties.entrySet();
	}

	public boolean containsKey(String key)
	{
		return properties.containsKey(key);
	}

	private String buildMulitProperyString(List<String> propertyList)
	{
		StringBuilder propertyBuilder = new StringBuilder();

		for (String propertyString : propertyList)
		{
			propertyBuilder.append(propertyString).append(";");
		}

		return propertyBuilder.toString();
	}

	// private List<String> splitMultiPropertyString(String value)
//	{
//		return Arrays.asList(value.split(";"));
//	}

	/*
	 * Properties List:
	 */

	public void setHostName(String value)
	{
		properties.setProperty("hostName", value);
	}

	public String getHostName()
	{
		return properties.getProperty("hostName");
	}

	public void setDbUsername(String value)
	{
		properties.setProperty("dbUsername", value);
	}

	public static String getDbUsername()
	{
		return properties.getProperty("dbUsername");
	}

	public void setDbUrl(String value)
	{
		properties.setProperty("dbUrl", value);
	}

	public static String getDbUrl()
	{
		return properties.getProperty("dbUrl");
	}

	public void setFaviconPath(String value)
	{
		properties.setProperty("faviconPath", value);
	}

	public String getFaviconPath()
	{
		return properties.getProperty("faviconPath");
	}

	public void setServletContextPath(String value)
	{
		properties.setProperty("servletContextPath", value);
	}

	public String getServletContextPath()
	{
		return properties.getProperty("servletContextPath");
	}

	public void setUrlRewritingEnabled(boolean value)
	{
		if (value == true)
		{
			properties.setProperty("isUrlRewritingEnabled", "true");
		} else
		{
			properties.setProperty("isUrlRewritingEnabled", "false");
		}
	}

	public boolean isUrlRewritingEnabled()
	{
		boolean allowUrlRewriting = false;

		if (properties.getProperty("isUrlRewritingEnabled") != null
		    && properties.getProperty("isUrlRewritingEnabled").equals("true"))
		{
			allowUrlRewriting = true;
		}
		return allowUrlRewriting;
	}

	public void setProductionModeActive(boolean value)
	{
		if (value == true)
		{
			properties.setProperty("isProductionModeActive", "true");
		} else
		{
			properties.setProperty("isProductionModeActive", "false");
		}
	}

	public boolean isProductionModeActive()
	{
		boolean isInProductionMode = false;

		if (properties.getProperty("isProductionModeActive") != null
		    && properties.getProperty("isProductionModeActive").equals("true"))
		{
			isInProductionMode = true;
		}
		return isInProductionMode;
	}

	public void setHeaderImageAltName(String value)
	{
		properties.setProperty("headerImageAltName", value);
	}

	public String getHeaderImageAltName()
	{
		return properties.getProperty("headerImageAltName");
	}

	public void setHeaderImagePath(String value)
	{
		properties.setProperty("headerImagePath", value);
	}

	public String getHeaderImagePath()
	{
		return properties.getProperty("headerImagePath");
	}

	public void setDbPassword(String value)
	{
		properties.setProperty("dbPassword", value);
	}

	public static String getDbPassword()
	{
		return properties.getProperty("dbPassword");
	}

	public void setHtmlLang(String value)
	{
		properties.setProperty("htmlLang", value);
	}

	public String getHtmlLang()
	{
		return properties.getProperty("htmlLang");
	}

	public void setHtmlScriptPaths(List<String> value)
	{
		properties
		    .setProperty("htmlScriptPaths", buildMulitProperyString(value));
	}

	public List<String> getHtmlScriptPaths()
	{
		return Arrays
		    .asList(properties.getProperty("htmlScriptPaths").split(";"));
	}

	public void removeHtmlScriptPath(String value)
	{
		List<String> scriptElements = Arrays
		    .asList(properties.getProperty("htmlScriptPaths").split(";"));

		scriptElements.removeIf(e -> e.equals(value));

		properties.setProperty(
		    "htmlScriptPaths", buildMulitProperyString(scriptElements)
		);
	}

	public void setHtmlStylesheetTags(List<String> value)
	{
		properties
		    .setProperty("htmlStylesheetTags", buildMulitProperyString(value));
	}

	public List<String> getHtmlStylesheetTags()
	{
		return Arrays
		    .asList(properties.getProperty("htmlStylesheetTags").split(";"));
	}

	public void removeHtmlStylesheetTags(String value)
	{
		List<String> scriptElements = Arrays
		    .asList(properties.getProperty("htmlStylesheetTags").split(";"));

		scriptElements.removeIf(e -> e.equals(value));

		properties.setProperty(
		    "htmlStylesheetTags", buildMulitProperyString(scriptElements)
		);
	}

	public void setRegisterConfirmPageHtmlTitle(String value)
	{
		properties.setProperty("registerConfirmPageHtmlTitle", value);
	}

	public String getRegisterConfirmPageHtmlTitle()
	{
		return properties.getProperty("registerConfirmPageHtmlTitle");
	}

	public void setLoginPageServletPath(String value)
	{
		properties.setProperty("loginPageServletPath", value);
	}

	public String getLoginPageServletPath()
	{
		return properties.getProperty("loginPageServletPath");
	}

	public void setRootPagePath(String value)
	{
		properties.setProperty("rootPagePath", value);
	}

	public String getRootPagePath()
	{
		return properties.getProperty("rootPagePath");
	}

	public void setHMACSecret(String value)
	{
		properties.setProperty("HMACSecret", value);
	}

	public String getHMACSecret()
	{
		return properties.getProperty("HMACSecret");
	}

	public void setAESSecret(String value)
	{
		properties.setProperty("AESSecret", value);
	}

	public String getAESSecret()
	{
		return properties.getProperty("AESSecret");
	}

	public void setAccountActivationMailSubject(String value)
	{
		properties.setProperty("accountActivationMailSubject", value);
	}

	public String getAccountActivationMailSubject()
	{
		return properties.getProperty("accountActivationMailSubject");
	}

	public void setAccountActivationMailMessage1(String value)
	{
		properties.setProperty("accountActivationMailMessage1", value);
	}

	public String getAccountActivationMailMessage1()
	{
		return properties.getProperty("accountActivationMailMessage1");
	}

	public void setAccountActivationMailMessage2(String value)
	{
		properties.setProperty("accountActivationMailMessage2", value);
	}

	public String getAccountActivationMailMessage2()
	{
		return properties.getProperty("accountActivationMailMessage2");
	}

	public void setAccountActivationMailMessage3(String value)
	{
		properties.setProperty("accountActivationMailMessage3", value);
	}

	public String getAccountActivationMailMessage3()
	{
		return properties.getProperty("accountActivationMailMessage3");
	}

	public void setJwtIssuer(String value)
	{
		properties.setProperty("jwtIssuer", value);
	}

	public String getJwtIssuer()
	{
		return properties.getProperty("jwtIssuer");
	}

	public void setJwtSubject(String value)
	{
		properties.setProperty("jwtSubject", value);
	}

	public String getJwtSubject()
	{
		return properties.getProperty("jwtSubject");
	}

	public void setJwtAudience(String value)
	{
		properties.setProperty("jwtAudience", value);
	}

	public String getJwtAudience()
	{
		return properties.getProperty("jwtAudience");
	}

	public void setJwtExperiationInMinutes(long value)
	{
		properties.setProperty("jwtExperiationInMinutes", Long.toString(value));
	}

	public long getJwtExperiationInMinutes()
	{
		return Long
		    .parseLong(properties.getProperty("jwtExperiationInMinutes"));
	}

	public void setJwtNotBeforeTime(String value)
	{
		properties.setProperty("jwtNotBeforeTime", value);
	}

	public String getJwtNotBeforeTime()
	{
		return properties.getProperty("jwtNotBeforeTime");
	}

	public void setActivationKeyExperiationTimeInMinutes(String value)
	{
		properties.setProperty("activationKeyExperiationTimeInMinutes", value);
	}

	public String getActivationKeyExperiationTimeInMinutes()
	{
		return properties.getProperty("headerImagePath");
	}

	public void setAlertBrowserCookiesDisabledMessage(String value)
	{
		properties.setProperty("alertBrowserCookiesDisabledMessage", value);
	}

	public String getAlertBrowserCookiesDisabledMessage()
	{
		return properties.getProperty("alertBrowserCookiesDisabledMessage");
	}

	public void setMobileBrowserAppLayoutColor(String value)
	{
		properties.setProperty("mobileBrowserAppLayoutColor", value);
	}

	public String getMobileBrowserAppLayoutColor()
	{
		return properties.getProperty("mobileBrowserAppLayoutColor");
	}

	public void setHtmlMetaCharset(String value)
	{
		properties.setProperty("htmlMetaCharset", value);
	}

	public String getHtmlMetaCharset()
	{
		return properties.getProperty("htmlMetaCharset");
	}

	public void setServletCharacterEncoding(String value)
	{
		properties.setProperty("servletCharacterEncoding", value);
	}

	public String getServletCharacterEncoding()
	{
		return properties.getProperty("servletCharacterEncoding");
	}

	public void setIsServletInHttpsMode(boolean value)
	{
		if (value == true)
		{
			properties.setProperty("isServletInHttpsMode", "true");
		} else
		{
			properties.setProperty("isServletInHttpsMode", "false");
		}
	}

	public boolean getIsServletInHttpsMode()
	{
		boolean IsServletInHttpsMode = false;

		if (properties.getProperty("isServletInHttpsMode").equals("true"))
		{
			IsServletInHttpsMode = true;
		}
		return IsServletInHttpsMode;
	}

	public void setServletCookieDomain(String value)
	{
		properties.setProperty("servletCookieDomain", value);
	}

	public String getServletCookieDomain()
	{
		return properties.getProperty("servletCookieDomain");
	}

	public void setServletCookiePath(String value)
	{
		properties.setProperty("servletCookiePath", value);
	}

	public String getServletCookiePath()
	{
		return properties.getProperty("servletCookiePath");
	}

	public void setPageContentRegisterConfirmedText(String value)
	{
		properties.setProperty("pageContentRegisterConfirmedText", value);
	}

	public String getPageContentRegisterConfirmedText()
	{
		return properties.getProperty("pageContentRegisterConfirmedText");
	}

	public void setServletHomePagePath(String value)
	{
		properties.setProperty("servletHomePagePath", value);
	}

	public String getServletHomePagePath()
	{
		return properties.getProperty("servletHomePagePath");
	}

	public void setServletLoginPagePath(String value)
	{
		properties.setProperty("servletLoginPagePath", value);
	}

	public String getServletLoginPagePath()
	{
		return properties.getProperty("servletLoginPagePath");
	}

	public void setGoogleEmailServiceApplicationName(String value)
	{
		properties.setProperty("googleEmailServiceApplicationName", value);
	}

	public String getGoogleEmailServiceApplicationName()
	{
		return properties.getProperty("googleEmailServiceApplicationName");
	}

	public void setGoogleEmailServiceEMailAddress(String value)
	{
		properties.setProperty("googleEmailServiceEMailAddress", value);
	}

	public String getGoogleEmailServiceEMailAddress()
	{
		return properties.getProperty("googleEmailServiceEMailAddress");
	}

	public void setServletSessionMode(String value)
	{
		properties.setProperty("servletSessionMode", value);
	}

	public String getServletSessionMode()
	{
		return properties.getProperty("servletSessionMode");
	}

	public void setRessourcesRootFolder(String value)
	{
		properties.setProperty("ressourcesRootFolder", value);
	}

	public String getRessourcesRootFolder()
	{
		return properties.getProperty("ressourcesRootFolder");
	}

	public List<String> getKeysInSection(String section)
	{
		List<String> keys = new ArrayList<>();

		for (String key : properties.stringPropertyNames())
		{
			if (key.startsWith(section + "."))
			{
				keys.add(key);
			}
		}

		return keys;
	}

	// TODO sveng 29.04.2023: Ich brauche diese Methode nur, weil ich nicht
	// weiß, von welchem Typen die jeweilige Property ist.
	// Deshalb sollte ich den Typen in der Property (im key?) verschlüsseln.
	public void setPropertyValueX(String key, Object value)
	{
		switch (key) {

		case "allowUrlRewriting":
			setUrlRewritingEnabled(Boolean.parseBoolean((String) value));
			break;
		case "isInProductionMod":
			setProductionModeActive(Boolean.parseBoolean((String) value));
			break;
		case "servletContextPath":
			setServletContextPath((String) value);
			break;
		case "servletSessionMode":
			setServletSessionMode((String) value);
			break;
		case "servletCharacterEncoding":
			setServletCharacterEncoding((String) value);
			break;
		case "isServletInHttpsMode":
			setIsServletInHttpsMode(Boolean.parseBoolean((String) value));
			break;
		case "servletCookieDomain":
			setServletCookieDomain((String) value);
			break;
		case "servletCookiePath":
			setServletCookieDomain((String) value);
			break;
		case "dbPassword":
			setDbPassword((String) value);
			break;
		case "dbUrl":
			setDbUrl((String) value);
			break;
		case "dbUsername":
			setDbUsername((String) value);
			break;
		case "faviconPath":
			setFaviconPath((String) value);
			break;
		case "htmlLang":
			setHtmlLang((String) value);
			break;
		case "htmlScriptPaths[]":

			List<String> values = (List<String>) value;
			List<String> validValues = new ArrayList<>();

			for (String v : values)
			{
				if (isValidJSPath(v))
				{
					validValues.add(v);
				}
			}

			setHtmlScriptPaths(validValues);

			break;

		case "htmlStylesheetPaths[]":
			setHtmlStylesheetTags((List<String>) value);
			break;
		case "headerImagePath":
			setHeaderImagePath((String) value);
			break;
		case "mobileBrowserAppLayoutColor":
			setMobileBrowserAppLayoutColor((String) value);
			break;
		case "htmlMetaCharset":
			setHtmlMetaCharset((String) value);
			break;
		case "registerConfirmPageHtmlTitle":
			setRegisterConfirmPageHtmlTitle((String) value);
			break;
		case "alertBrowserCookiesDisabledMessage":
			setAlertBrowserCookiesDisabledMessage((String) value);
			break;
		case "servletHomePagePath":
			setServletHomePagePath((String) value);
			break;
		case "loginPageServletPath":
			setLoginPageServletPath((String) value);
			break;
		case "rootPagePath":
			setRootPagePath((String) value);
			break;
		case "HMACSecret":
			setHMACSecret((String) value);
			break;
		case "jwtIssuer":
			setJwtIssuer((String) value);
			break;
		case "jwtSubject":
			setJwtSubject((String) value);
			break;
		case "jwtAudience":
			setJwtAudience((String) value);
			break;
		case "jwtExperiationInMinutes":
			setJwtExperiationInMinutes(Long.parseLong((String) value));
			break;
		case "jwtNotBeforeTime":
			setJwtNotBeforeTime((String) value);
			break;
		case "activationKeyExperiationTimeInMinutes":
			setActivationKeyExperiationTimeInMinutes((String) value);
			break;
		case "accountActivationMailSubject":
			setAccountActivationMailSubject((String) value);
			break;
		case "accountActivationMailMessage1":
			setAccountActivationMailMessage1((String) value);
			break;
		case "accountActivationMailMessage2":
			setAccountActivationMailMessage2((String) value);
			break;
		case "accountActivationMailMessage3":
			setAccountActivationMailMessage3((String) value);
			break;
		case "pageContentRegisterConfirmedText":
			setPageContentRegisterConfirmedText((String) value);
			break;
		case "googleEmailServiceApplicationName":
			setGoogleEmailServiceApplicationName((String) value);
			break;
		case "googleEmailServiceEMailAddress":
			setGoogleEmailServiceEMailAddress((String) value);
			break;
		}

		try
		{
			save();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
		}
	}

	// TODO sveng 29.04.2023: Eingabe-Validierungen in eigenen Service!
	public boolean isValidJSPath(String path)
	{
		try
		{
			new URL(path);
			return true;
		} catch (MalformedURLException e)
		{
			return false;
		}
	}

	public enum PropertySectionDV
	{
		SERVLET("servlet"), //
		DATABASE("database"), //
		HTML("html"), //
		HOSTING("hosting"), //
		WEBAPP("webapp"), //
		SECURITY("security"), //
		ACCOUNT_ACTIVATION("account-activation"), //
		EMAIL("email");//

		private String value;

		PropertySectionDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}

		public String getValue()
		{
			return value;
		}

		public static List<String> getAllValues()
		{
			List<String> valuesList = new ArrayList<>();
			for (PropertySectionDV section : PropertySectionDV.values())
			{
				valuesList.add(section.getValue());
			}
			return valuesList;
		}
	}
}
