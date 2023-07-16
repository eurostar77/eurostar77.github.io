package com.bueffeltier.ui.webapp;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

public class ActionExecutorService
{
	private ViewDataService viewDataService = ViewDataService.getInstance();

	private static final int MAX_RETRIES = 3;
	private static final long RETRY_DELAY = 5000;

	private ActionExecutorService()
	{

	}

	private static class SingletonHolder
	{
		private static final ActionExecutorService instance = new ActionExecutorService();
	}

	public static ActionExecutorService getInstance()
	{
		return SingletonHolder.instance;
	}

	public void execute(
	    HttpServletRequest request,
	    Class<? extends com.bueffeltier.ui.webapp.content.action.Action> actionClass
	) throws Exception
	{
		com.bueffeltier.ui.webapp.content.action.Action action = null;

		int retryCount = 0;

		boolean executed = false;

		// Löschen der Action-to-View Daten vor jeder neuen Action.
		viewDataService.clearReceivedData(request);

		while (retryCount < MAX_RETRIES && !executed)
		{
			try
			{
//				action = actionClass.getDeclaredConstructor(HttpServletRequest.class).newInstance(request);

				action = (com.bueffeltier.ui.webapp.content.action.Action) actionClass
				    .getMethod("getInstance", HttpServletRequest.class)
				    .invoke(actionClass, request);

				action.execute(request);

				executed = true;

			} catch (Exception e)
			{
				retryCount++;

				waitBeforeRetry();
			}
		}

		if (!executed)
		{
			throw new Exception(
			    "Die aufgerufene Aktion konnte nicht erfolgreich ausgeführt werden."
			);
		}
	}

	private void waitBeforeRetry()
	{
		try
		{
			TimeUnit.MILLISECONDS.sleep(RETRY_DELAY);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}