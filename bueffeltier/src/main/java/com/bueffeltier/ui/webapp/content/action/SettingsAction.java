package com.bueffeltier.ui.webapp.content.action;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class SettingsAction extends AbstractAction
{
	private static SettingsAction instance;

	private AppPropertyService service = AppPropertyService.getInstance();

	private SettingsAction(HttpServletRequest request)
	{
		super();
	}

	public static SettingsAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new SettingsAction(request);
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		writeProperties(request);

		String activeSection = null;

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();

			switch (actionParameterName) {

			case "servlet":
				request.setAttribute("settingsViewShowSection", "servlet");
				break;

			case "database":
				request.setAttribute("settingsViewShowSection", "database");
				break;

			case "html":
				request.setAttribute("settingsViewShowSection", "html");
				break;

			case "hosting":
				request.setAttribute("settingsViewShowSection", "hosting");
				break;

			case "webapp":
				request.setAttribute("settingsViewShowSection", "webapp");
				break;

			case "security":
				request.setAttribute("settingsViewShowSection", "security");
				break;

			case "account-activation":
				request.setAttribute(
				    "settingsViewShowSection", "account-activation"
				);
				break;

			case "email":
				request.setAttribute("settingsViewShowSection", "email");
				break;
			}
		}

		request.setAttribute("fileExplorerShowFolder", activeSection);

		forwardToPage(request, "/app-settings");
	}

	private void writeProperties(HttpServletRequest request)
	{
		Map<String, String[]> parameterMap = request.getParameterMap();

		for (String parameterName : parameterMap.keySet())
		{
			if (parameterName.startsWith("property:"))
			{
				String propertyKey = parameterName.substring(9);

				String[] parameterValues = parameterMap.get(parameterName);

				if (propertyKey.endsWith("[]"))
				{
//					for (int i = 0; i < parameterValues.length; i++)
//					{
//						String value = parameterValues[i];

					service.setPropertyValueX(
					    propertyKey, Arrays.asList(parameterValues)
					);
//					}
				} else
				{
					String value = parameterValues[0];

					service.setPropertyValueX(propertyKey, value);
				}
			}
		}
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
