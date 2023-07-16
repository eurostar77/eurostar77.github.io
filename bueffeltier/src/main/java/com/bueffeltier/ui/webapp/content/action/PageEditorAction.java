package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.data.jdbc.Page;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class PageEditorAction extends AbstractAction
{
	private static PageEditorAction instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private PageEditorAction()
	{
		super();
	}

	public static PageEditorAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new PageEditorAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		String article = request.getParameter("article");
		if (article != null)
		{
			forwardViewData(
			    request, "editArticle", request.getParameter("article")
			);
			forwardToPage(request, "/edit-article");
		}

		String saveAndClose = request.getParameter("saveAndClose");
		if (saveAndClose != null)
		{
			Page page = siteRepository
			    .read(Long.parseLong(request.getParameter("pRef")));
			doSave(request, page);
			forwardToPage(request, "/site-strucure");
		}

		String save = request.getParameter("save");
		if (save != null)
		{
			Page page = siteRepository
			    .read(Long.parseLong(request.getParameter("pRef")));
			doSave(request, page);
			forwardViewData(request, "pageId", Long.toString(page.getId()));
			forwardToPage(request, "/edit-page");
		}

		String back = request.getParameter("back");
		if (back != null)
		{
			forwardToPage(request, "/site-structure");
		}
	}

	private void doSave(HttpServletRequest request, Page page)
	{
		String author = request.getParameter("author");
		if (author != null)
		{
			page.setAuthor(author);
		}

		String internalTitle = request.getParameter("internalTitle");
		if (internalTitle != null)
		{
			page.setInternalName(internalTitle);
		}

		String pageAlias = request.getParameter("pageAlias");
		if (pageAlias != null)
		{
			page.setUrlAlias(pageAlias);
		}

		String pagePath = request.getParameter("pagePath");
		if (pagePath != null)
		{
			page.setPath(request.getParameter("pagePath"));
		}

		String pageType = request.getParameter("pageType");
		if (pageType != null)
		{
			String pageTypeParam = request.getParameter("pageType");

			switch (pageTypeParam) {

			case "RESPOND":
				page.setPageType("RESPOND");
				break;
			case "INTERNAL_FWD":
				// TODO sveng 06.07.2023: forward_to abfragen
				page.setPageType("INTERNAL_FWD");
				break;
			case "EXTERNAL_FWD":
				page.setPageType("EXTERNAL_FWD");
				break;
			case "403":
				page.setPageType("403");
				break;
			case "404":
				page.setPageType("404");
				break;
			}
		}

		String htmlTitle = request.getParameter("htmlTitle");
		if (htmlTitle != null)
		{
			page.setHtmlTitle(request.getParameter("htmlTitle"));
		}

		String description = request.getParameter("description");
		if (description != null)
		{
			page.setDescription(request.getParameter("description"));
		}

		String keywords = request.getParameter("keywords");
		if (keywords != null)
		{
			page.setKeywords(request.getParameter("keywords"));
		}

		String noindex = request.getParameter("noindex");
		if (noindex != null && "true".equals(request.getParameter("noindex")))
		{
			page.setNoIndex(true);

		} else
		{
			page.setNoIndex(false);
		}

		String nofollow = request.getParameter("nofollow");
		if (nofollow != null && "true".equals(request.getParameter("nofollow")))
		{
			page.setNoFollow(true);

		} else
		{
			page.setNoFollow(false);
		}

		String permission = request.getParameter("permission");
		if (permission != null)
		{
			page.setPermission(
			    Integer.parseInt(request.getParameter("permission"))
			);
		}

		String layout = request.getParameter("layout");
		if (layout != null)
		{
			page.setLayout(1); // todo: Layouts anbieten!
		}

		String cacheTime = request.getParameter("cacheTime");
		if (StringUtils.isNoneBlank(cacheTime))
		{
			page.setCacheTime(cacheTime);

		} else
		{
			page.setCacheTime("0");
		}

		String noSearch = request.getParameter("noSearch");
		if (noSearch != null)
		{
//					page.setHideInSearch(
//						Boolean.parseBoolean(request.getParameter("no-search"))
//					);
		}
//				
		String addToSitemap = request.getParameter("addToSitemap");
		if (addToSitemap != null
		    && "true".equals(request.getParameter("addToSitemap")))
		{
			page.createSitemap(true);

		} else
		{
			page.createSitemap(false);
		}

		String hideInNav = request.getParameter("hideInNav");
		if (hideInNav != null
		    && "true".equals(request.getParameter("hideInNav")))
		{
			page.setHideInNav(true);

		} else
		{
			page.setHideInNav(false);
		}

		String publish = request.getParameter("publish");
		if (publish != null && "true".equals(request.getParameter("publish")))
		{
			page.setPublished(true);

		} else
		{
			page.setPublished(false);
		}

		siteRepository.update(page);
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		// TODO Auto-generated method stub

	}
}
