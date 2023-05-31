package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.domain.ContentFeeder;

import j2html.tags.DomContent;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ArticleFeederView extends AbstractView
{
	private static ArticleFeederView instance;

	private AppPropertyService appPropertyService;

	private ContentFeeder contentFeeder;

	private ArticleFeederView()
	{
		super();
		this.contentFeeder = ContentFeeder.getInstance();
		appPropertyService = AppPropertyService.getInstance();
	}

	public static ArticleFeederView getInstance()
	{
		if (instance == null)
		{
			instance = new ArticleFeederView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
//		setContainerTagClassName("teasers");
		// Ersten Artikel incl. Teaser erstellen!!!

		// Anzuzeigende Artikel-Teaser vorerst hier hart codieren und laden.
		// Artikel-Teaser später automatisch aus Course-Manager laden.
		// Anzuzeigende Teaser aus db beziehen.
		// User ArtikelWriterHandler erschaffen.
		// link auf artikelseite aus db laden
		// action command: ->lesen, los, üben, lernen, bestätigen
		// auch reine teaser möglich, ohne artikel dahinter?
//		ContentFeeder contentFeeder = new ContentFeeder();

		List<ArticleJDBCFlat> articles = // (ArrayList<ArticleJDBCFlat>)
		    contentFeeder.getArticles();
		return each(articles, article -> div(writeTeaser(article)));

	}

	private DomContent writeTeaser(ArticleJDBCFlat article)
	{
		String link = appPropertyService.getHostName()
		    + appPropertyService.getServletContextPath()
		    + article.getPagePath();

//		return CardBuilder.create()
//				.withImage(
//						appPropertyService.getServletContextPath()
//								+ article.getTeaserImagePath(),
//						"Lernkarten", ImagePositionDV.TOP
//				).withHeadline(HeadlineDV.H2, article.getTitle())
//				.withText(article.getTeaserText()).build();

		return div(
		    div(
		        img().withClass("teaserImg")
		            .attr(
		                "src",
		                appPropertyService.getServletContextPath()
		                    + article.getTeaserImagePath()
		            ).attr("alt", "")
		    ).withClass("teaserImgContainer"),
		    div(
		        h2(a(article.getTitle()).attr("title", ""))
		            .withClass("teaserHdl"),
		        p(article.getTeaserText()).withClass("teaserTxt"),
		        a("lesen...").attr("href", link).withClass("teaserLink")
		    ).withClass("teaserContainerInside")
		).withClass("teaser");
	}

	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void setCssId(String containerTagClassName)
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected void setContainerTag()
	{
		// TODO Auto-generated method stub
	}
}
