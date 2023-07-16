package com.bueffeltier.logic.foundation.pagetree;

import com.bueffeltier.data.jdbc.Page;

public interface PageTreeRepoFlatJDBC
{
	Page createPage();

	void write(Page page);

	Page read(String path);

	Page read(long id);

	void update(Page page);

	void delete(Page page);

	Page getHomePage();

	private void writeSitemap()
	{
	};
}
