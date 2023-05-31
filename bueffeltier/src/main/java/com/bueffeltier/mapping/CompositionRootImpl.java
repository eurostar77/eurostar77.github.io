package com.bueffeltier.mapping;

import java.util.HashMap;
import java.util.Map;

import com.bueffeltier.logic.foundation.pagetree.SiteRepository;
import com.bueffeltier.ui.webapp.RequestService;
import com.bueffeltier.ui.webapp.ViewBuilder;
import com.bueffeltier.ui.webapp.content.ActionRegistry;
import com.bueffeltier.ui.webapp.content.ViewRegistry;

public class CompositionRootImpl implements CompositionRoot
{
	private Map<String, Object> dependencies = new HashMap<>();

	public CompositionRootImpl()
	{
		buildRoot();
	}

	private void buildRoot()
	{
		ActionRegistry actionRegistry = ActionRegistry.getInstance();
		dependencies.put("ActionRegistry", actionRegistry);
		// JDBCRoot:
		// build singletons
//		DBUpdateService dbUpdateService = DBUpdateService.getInstance();

		ViewRegistry viewHandlerRegister = ViewRegistry.getInstance();

		SiteRepository pageTree = SiteRepository.getInstance();

		dependencies.put("PageTree", pageTree);

		ViewBuilder viewBuilder = ViewBuilder.getInstance();

		ViewRegistry contentController = ViewRegistry.getInstance();

		RequestService requestService = RequestService.getInstance();

		ActionRegistry actionHandlerRegister = ActionRegistry.getInstance();

		dependencies.put("ViewService", requestService);

		// HibernateRoot:
		// build singletons
//		PageDaoJDBCFlatImpl pageDaoFlatImpl = PageDaoJDBCFlatImpl
//				.getInstance(MaterialTablesDV.PAGE, null);
//
//		PageTreeRepoFlatJDBC pageTree = new PageTreeServiceJDBCFlatImpl(
//				pageDaoFlatImpl
//		);
//		dependencies.put("PageTree", pageTree);
//
//		ViewService viewService = new ViewServiceImpl(pageTree);
//		dependencies.put("ViewService", viewService);
	}

	@Override
	public RequestService getRoot()
	{
		return (RequestService) dependencies.get("ViewService");

		// Build composition tree:

//		dependencies.put(ViewService, ViewServiceImpl);
	}

//	@Override
//	public <T> void addMapping(T contract, T implementation)
//	{
//		dependencies.put(contract, implementation);
//
//	}

//	@Override
//	public <T> T getImplementation(T contract)
//	{
//		
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public <T> void addMapping(T contract, T implementation)
//	{
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	public U getImplementation(T contract)
//	{
	// TODO Auto-generated method stub

//		Affirm.notNull(model);
//		U u = 
//		U u = dependencies.get(contract);
//		Affirm.notNull(t);

//		return new ViewServiceImpl();

//		return null;
//	}

//	@Override
//	public void addMapping(Object contract, Object implementation)
//	{
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public Object getImplementation(String type)
	{
		Object impl = dependencies.get(type);
		return impl;
	}
}
