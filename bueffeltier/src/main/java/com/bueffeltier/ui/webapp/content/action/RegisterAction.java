package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.foundation.AuthPermissionService;
import com.bueffeltier.logic.foundation.UserRepository;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RegisterAction extends AbstractAction
{
	private static RegisterAction instance;

	private UserRepository userRepository = UserRepository.getInstance();

	private AuthPermissionService authPermissionService = AuthPermissionService
			.getInstance();

	private RegisterAction()
	{
		super();
	}

	public static RegisterAction getInstance()
	{
		if (instance == null)
		{
			instance = new RegisterAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		// todo: prüfen, ob js, dann ajax

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("e-mail");

		if (isInputValid(name, password, email))
		{
			if (authPermissionService
					.register(request, name, email, password, false))
			{
				forwardToPage(request, "/register-submitted");

			} else
			{
				forwardToPage(request, "/register");
			}
		} else
		{
			// Eingaben ungültig
			// todo: Brauchbare Meldung bauen.
			forwardToPage(request, "/register");
		}
	}

	private boolean isInputValid(String name, String email, String password)
	{
		boolean valid = !name.isBlank();
		valid &= !name.isEmpty();
		valid &= !email.isBlank();
		valid &= !email.isEmpty();
		valid &= !password.isBlank();
		valid &= !password.isEmpty();

		return valid;
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
