package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.logic.foundation.UserRepository;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RegisterConfirmAction extends AbstractAction
{

	private static RegisterConfirmAction instance;

	UserRepository userRepository = UserRepository.getInstance();

	private RegisterConfirmAction()
	{
		super();
	}

	public static RegisterConfirmAction getInstance()
	{
		if (instance == null)
		{
			instance = new RegisterConfirmAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		// http://localhost:8080/bueffeltier/register-confirm?activate=TvH0g1lc8CWQS5AyEmJ4FRCy6tAhTnvU
		String key = request.getParameter("activate");

		try
		{
			UserJDBC user = userRepository.readByActivationKey(key);

			user.setActivationKey(null);
			user.setActive(true);

			forwardToPage(request, "/register-confirm");

			userRepository.update(user);

			// todo: Settings
			request.setAttribute(
					"pageMessage",
					"Sie haben sich erffolgreich registriert. Sie können sich einloggen."
			);

		} catch (Exception e)
		{
			// todo: fehlerseite oder fehlermessage

			forwardToPage(request, "/register-confirm");

			// todo: Settings
			request.setAttribute(
					"pageMessage", "Hier ist etwas schief gelaufen!"
			);
			// todo: catch?
			// fehlerseite
		}
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		// TODO Auto-generated method stub

	}
}
