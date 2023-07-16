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
public class AppSettingsAction extends AbstractAction
{
	private static AppSettingsAction instance;

	private AppPropertyService service = AppPropertyService.getInstance();

	private AppSettingsAction(HttpServletRequest request)
	{
		super();
	}

	public static AppSettingsAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new AppSettingsAction(request);
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
				forwardViewData(request, "settingsViewShowSection", "servlet");
				break;

			case "database":
				forwardViewData(request, "settingsViewShowSection", "database");
				break;

			case "html":
				forwardViewData(request, "settingsViewShowSection", "html");
				break;

			case "hosting":
				forwardViewData(request, "settingsViewShowSection", "hosting");
				break;

			case "webapp":
				forwardViewData(request, "settingsViewShowSection", "webapp");
				break;

			case "security":
				forwardViewData(request, "settingsViewShowSection", "security");
				break;

			case "account-activation":
				forwardViewData(
				    request, "settingsViewShowSection", "accountActivation"
				);
				break;

			case "email":
				forwardViewData(request, "settingsViewShowSection", "email");
				break;
			}
		}

		forwardViewData(request, "fileExplorerShowFolder", activeSection);

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
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
