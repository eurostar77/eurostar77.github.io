package com.bueffeltier.mapping;

import com.bueffeltier.ui.webapp.RequestService;

//public interface CompositionRoot<T, U>
public interface CompositionRoot
{
//	void addMapping(T contract, T implementation);

//	<T, U> U get(T model);
//	<T> T getImplementation(T contract);

	Object getImplementation(String type);

	RequestService getRoot();
}
