package com.bueffeltier.data.jdbc;

import java.util.List;

public interface CRUDService<T>
{
	T create(String name);

	void createAndPersist(T material);

	T read(long id);

	void update(T... materials);

	void delete(T material);

	List<T> getAllData();

}
