package com.bueffeltier.ui.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bueffeltier.data.DBUpdateService;
import com.bueffeltier.data.hibernate.HibernateUtil;
import com.bueffeltier.data.jdbc.DBUtils;
import com.bueffeltier.data.jdbc.MaterialTablesDV;
import com.bueffeltier.mapping.CompositionRoot;
import com.bueffeltier.mapping.CompositionRootImpl;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
// TODO sveng 04.02.2023: context root richtig?
@WebServlet(urlPatterns = "/asyncontext", asyncSupported = true)
//@WebServlet(urlPatterns = "/")
public class Servlet extends HttpServlet
{
	private CompositionRoot compositionRoot;

	private RequestService requestService;

	private static final long serialVersionUID = 1L;

	DBUpdateService dBUpdateService = DBUpdateService.getInstance();

	@Override
	public void init() throws ServletException
	{
		super.init();

		System.out.println("");
		System.out
		    .println(" _                    __   __       _  _    _       ");
		System.out.println("| |                  / _| / _|     | || |  (_)   ");
		System.out.println(
		    "| |__   _   _   ___ | |_ | |_  ___ | || |_  _   ___  _ __ "
		);
		System.out.println(
		    "| '_ \\ | | | | / _ \\|  _||  _|/ _ \\| || __|| | / _ \\| '__|"
		);
		System.out.println(
		    "| |_) || |_| ||  __/| |  | | |  __/| || |_ | ||  __/| | "
		);
		System.out.println(
		    "|_.__/  \\__,_| \\___||_|  |_|  \\___||_| \\__||_| \\___||_|"
		);
		System.out.println("");

		////////////////////////////////////////////////////////////////////////
		/*
		 * Hier können ApplicationProperties hinzugefügt werden. Nach
		 * Hinzufügen, wieder auskommentieren!
		 */
//		appPropertyService.setServletContextPath(
//				this.getServletContext().getContextPath()
//		);
//		try
//		{
//			appPropertyService.save(this);
//		} catch (FileNotFoundException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		////////////////////////////////////////////////////////////////////////

		compositionRoot = new CompositionRootImpl();

		HibernateUtil.getInstance();

		requestService = compositionRoot.getRoot();

		try
		{
			DBUtils.executeDDLStatement(MaterialTablesDV.PAGE.getCreate());
			DBUtils.executeDDLStatement(MaterialTablesDV.ARTICLE.getCreate());
			DBUtils.executeDDLStatement(MaterialTablesDV.ELEMENT.getCreate());
			DBUtils.executeDDLStatement(MaterialTablesDV.USER.getCreate());
			DBUtils.executeDDLStatement(MaterialTablesDV.INFO_BIT.getCreate());

		} catch (SQLException e)
		{
			System.out.println("Tabellen konnten nicht angelegt werden.");
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void destroy()
	{
		super.destroy();

		// timer.cancel();

		HibernateUtil.closeEntityManagerFactory();
	}

//    @Override
//    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
//    }

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	{
		final AsyncContext asyncContext = request.startAsync();

		request.setAttribute("asyncContext", asyncContext);

		// asyncContext.addListener(new AsyncListenerImpl(), request, response);
		// asyncContext.setTimeout(10 * 60 * 1000); // 10 Min. // todo in app
		// einstellungen . vs debug mode
		asyncContext.setTimeout(10 * 60 * 1000); // 10 Min.

		asyncContext.start(new Runnable()
		{
			@Override
			public void run()
			{
				requestService.processRequest(request);

				asyncContext.complete();
			}
		});
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
	{
		final AsyncContext asyncContext = request.startAsync();

		request.setAttribute("asyncContext", asyncContext);

		// asyncContext.addListener(new AsyncListenerImpl(), request, response);
		asyncContext.setTimeout(10 * 60 * 1000); // 10 Min.

		asyncContext.start(new Runnable()
		{
			@Override
			public void run()
			{
				requestService.processRequest(request);

				asyncContext.complete();
			}
		});
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo()
	{
		return "Short description";
	}// </editor-fold>

	private class AsyncListenerImpl implements AsyncListener
	{

		@Override
		public void onComplete(AsyncEvent event) throws IOException
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onTimeout(AsyncEvent event) throws IOException
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onError(AsyncEvent event) throws IOException
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartAsync(AsyncEvent event) throws IOException
		{
			// TODO Auto-generated method stub

		}

	}
}
