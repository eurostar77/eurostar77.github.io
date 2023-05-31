package com.bueffeltier.logic.foundation.pagetree;

import com.bueffeltier.data.jdbc.PageJDBCFlat;

public interface PageTreeRepoFlatHibernate
{
	PageJDBCFlat createPage();

	void write(PageJDBCFlat page);

	PageJDBCFlat read(String path);

	PageJDBCFlat read(long id);

	void update(PageJDBCFlat page);

	void delete(PageJDBCFlat page);

	PageJDBCFlat getHomePage();

	private void writeSitemap()
	{
	};
}
