package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ArticleEditorAction extends AbstractAction
{
	private static ArticleEditorAction instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private ArticleEditorAction()
	{
		super();
	}

	public static ArticleEditorAction getInstance()
	{
		if (instance == null)
		{
			instance = new ArticleEditorAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		// TODO sveng 12.07.2023: footerActions importieren?

		String backAction = request.getParameter("backAction");
		if (backAction != null)
		{
			forwardToPage(request, "/site-structure");
		}

		String saveAction = request.getParameter("save");
		if (saveAction != null)
		{
			doSave(request);
			forwardToPage(request, "/edit-article");
		}

		String saveAndBackAction = request.getParameter("saveAndBackAction");
		if (saveAndBackAction != null)
		{
			doSave(request);
			forwardToPage(request, "/site-structure");
		}
	}

	private void doSave(HttpServletRequest request)
	{
		ArticleJDBCFlat article = siteRepository
		    .readArticle(Long.parseLong(request.getParameter("aRef")));

		String parentId = request.getParameter("parentId");
		if (parentId != null)
		{
			article.setAuthor(parentId);
		}

		String sorting = request.getParameter("sorting");
		if (sorting != null)
		{
			article.setAuthor(sorting);
		}

		String title = request.getParameter("title");
		if (title != null)
		{
			article.setAuthor(title);
		}

		String author = request.getParameter("author");
		if (author != null)
		{
			article.setAuthor(author);
		}

		String teaserText = request.getParameter("teaserText");
		if (teaserText != null)
		{
			article.setAuthor(teaserText);
		}

		String teaserImagePath = request.getParameter("teaserImagePath");
		if (teaserImagePath != null)
		{
			article.setAuthor(teaserImagePath);
		}

		String searchWords = request.getParameter("searchWords");
		if (searchWords != null)
		{
			article.setAuthor(searchWords);
		}

		String showTeaser = request.getParameter("showTeaser");
		if (showTeaser != null)
		{
			article.setAuthor(showTeaser);
		}

		String lastEditDate = request.getParameter("lastEditDate");
		if (lastEditDate != null)
		{
			article.setAuthor(lastEditDate);
		}

		String isPublished = request.getParameter("isPublished");
		if (isPublished != null)
		{
			article.setAuthor(isPublished);
		}

		String keywords = request.getParameter("keywords");
		if (keywords != null)
		{
			article.setAuthor(keywords);
		}

		String containerTag = request.getParameter("containerTag");
		if (containerTag != null)
		{
			article.setAuthor(containerTag);
		}

		String teaserCssId = request.getParameter("teaserCssId");
		if (teaserCssId != null)
		{
			article.setAuthor(teaserCssId);
		}

		String teaserCssClass = request.getParameter("teaserCssClass");
		if (teaserCssClass != null)
		{
			article.setAuthor(teaserCssClass);
		}

		String cssId = request.getParameter("cssId");
		if (cssId != null)
		{
			article.setAuthor(cssId);
		}

		String cssClass = request.getParameter("cssClass");
		if (cssClass != null)
		{
			article.setAuthor(cssClass);
		}

		String createTime = request.getParameter("createTime");
		if (createTime != null)
		{
			article.setAuthor(createTime);
		}

		String showFrom = request.getParameter("showFrom");
		if (showFrom != null)
		{
			article.setAuthor(showFrom);
		}

		String showUntil = request.getParameter("showUntil");
		if (showUntil != null)
		{
			article.setAuthor(showUntil);
		}

		String layoutColumn = request.getParameter("layoutColumn");
		if (layoutColumn != null)
		{
			article.setAuthor(layoutColumn);
		}

		String pagePath = request.getParameter("pagePath");
		if (pagePath != null)
		{
			article.setAuthor(pagePath);
		}

//		siteRepository.updateArticle(article);
	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		// TODO Auto-generated method stub

	}
}
