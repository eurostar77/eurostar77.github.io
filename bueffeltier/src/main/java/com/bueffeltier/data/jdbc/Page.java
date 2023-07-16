package com.bueffeltier.data.jdbc;

import java.util.List;
import java.util.Objects;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class Page implements java.io.Serializable
{
	// TODO sveng 13.11.2022: Layoutbereiche der Seite abbilden.
	private List<ArticleJDBCFlat> mainArticles;
	private List<ArticleJDBCFlat> headerArticles;
	private List<ArticleJDBCFlat> footerArticles;

	private long id;
	private String htmlTitle;
	private String path;
	private int layout;
	private int permission;
	private String forwardTo;
	private String author;
	private String cacheTime;
	private boolean createSitemap;
	private String cssClass;
	private String description;
	private String pageType;
	private boolean hideInNav;
	private boolean includeCache;
	private boolean includeLayout;
	private boolean isProtected;
	private String keywords;
	private String language;
	private String lastVersion;
	private boolean published;
	private String sitemapName;
	private boolean noFollow;
	private boolean noIndex;
	private String internalName;
	private String urlAlias;
	private int level; // todo: in datenbank speichern und in pageTree nutzen!

	public Page()
	{

	}

	// todo
	public Page getCreate(String internalName)
	{
		return new Page(internalName);
	}

	public Page(String internalName)
	{
//		init();
		initForJDBC();
		this.internalName = internalName;
	}

	private void initForJDBC()
	{
		// todo: methode init sinnvoll?
		htmlTitle = "";
		internalName = "";
		layout = 1;
		urlAlias = "";
		forwardTo = "";
	}

	private void init()
	{
		// todo: methode init sinnvoll?
		htmlTitle = "";
		internalName = "";
		layout = 1;
		urlAlias = "";
		forwardTo = "";
	}

	public List<ArticleJDBCFlat> getMainArticles()
	{
		return mainArticles;
	}

	public void setMainArticles(List<ArticleJDBCFlat> articles)
	{
		this.mainArticles = articles;
	}

	public List<ArticleJDBCFlat> getHeaderArticles()
	{
		return headerArticles;
	}

	public void setHeaderArticles(List<ArticleJDBCFlat> headerArticles)
	{
		this.headerArticles = headerArticles;
	}

	public List<ArticleJDBCFlat> getFooterArticles()
	{
		return footerArticles;
	}

	public void setFooterArticles(List<ArticleJDBCFlat> footerArticles)
	{
		this.footerArticles = footerArticles;
	}

	/**
	 *
	 * @return
	 */
	public long getId()
	{
		return this.id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

//	public void addArticle(Article article)
//	{
//		articles.add(article);
//		article.setPage(this);
//	}
//
//	public void removeArticle(Article article)
//	{
//		articles.remove(article);
//		article.setPage(null);
//	}

//	public List<Article> getArticles()
//	{
//		return articles;
//	}
//
//	public void setArticles(List<Article> articles)
//	{
//		this.articles = articles;
//	}

	/**
	 *
	 * @return
	 */
	public String getHtmlTitle()
	{
		return this.htmlTitle;
	}

	// todo: rückgabewert = String title?
	/**
	 *
	 * @param htmlTitle
	 */
	public void setHtmlTitle(String htmlTitle)
	{
		this.htmlTitle = htmlTitle.replaceAll(" ", "").replaceAll("ä", "ae")
				.replaceAll("ö", "oe").replaceAll("ü", "ue");
	}

	/**
	 *
	 * @return
	 */
	public String getUrlAlias()
	{
		return urlAlias;
	}

	/**
	 *
	 * @param urlAlias
	 */
	public void setUrlAlias(String urlAlias)
	{
		// todo: bindestriche einfügen, länge erstmal egal.egal
		this.urlAlias = urlAlias;
	}

	/**
	 *
	 * @return
	 */
	public String getInternalName()
	{
		return internalName;
	}

	/**
	 *
	 * @param internalName
	 */
	public void setInternalName(String internalName)
	{
		this.internalName = internalName;
	}

	/**
	 *
	 * @return
	 */
	public int getLayout()
	{
		return this.layout;
	}

	/**
	 *
	 * @param layout
	 */
	public void setLayout(int layout)
	{
		this.layout = layout;
	}

	/**
	 *
	 * @return
	 */
	public String getForwardDestination()
	{
		return this.forwardTo;
	}

	// todo: doppelt!
	/**
	 *
	 * @param forwardDestination
	 */
	public void setForwardDestination(String forwardDestination)
	{
		this.forwardTo = forwardDestination;
	}

	/**
	 *
	 * @return
	 */
	public int getPermission()
	{
		return this.permission;
	}

	/**
	 *
	 * @param permission
	 */
	public void setPermission(int permission)
	{
		this.permission = permission;
	}

	/**
	 *
	 * @return
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 *
	 * @param level
	 */
	public void setLevel(int level)
	{
		this.level = level;
	}

	// todo: doppelt!
	/**
	 *
	 * @return
	 */
	public String getForwardTo()
	{
		return forwardTo;
	}

	/**
	 *
	 * @param forwardTo
	 */
	public void setForwardTo(String forwardTo)
	{
		this.forwardTo = forwardTo;
	}

	/**
	 *
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 *
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 *
	 * @return
	 */
	public String getKeywords()
	{
		return keywords;
	}

	/**
	 *
	 * @param keywords
	 */
	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	/**
	 *
	 * @return
	 */
	public String getAuthor()
	{
		return author;
	}

	/**
	 *
	 * @param author
	 */
	public void setAuthor(String author)
	{
		this.author = author;
	}

	/**
	 *
	 * @return
	 */
	public String getLanguage()
	{
		return language;
	}

	/**
	 *
	 * @param language
	 */
	public void setLanguage(String language)
	{
		this.language = language;
	}

	/**
	 *
	 * @return
	 */
	public boolean noIndex()
	{
		return this.noIndex;
	}

	/**
	 *
	 * @param noIndex
	 */
	public void setNoIndex(boolean noIndex)
	{
		this.noIndex = noIndex;
	}

	/**
	 *
	 * @return
	 */
	public boolean createSitemap()
	{
		return createSitemap;
	}

	/**
	 *
	 * @param createSitemap
	 */
	public void createSitemap(boolean createSitemap)
	{
		this.createSitemap = createSitemap;
	}

	/**
	 *
	 * @return
	 */
	public String getSitemapName()
	{
		return sitemapName;
	}

	/**
	 *
	 * @param sitemapName
	 */
	public void setSitemapName(String sitemapName)
	{
		this.sitemapName = sitemapName;
	}

	/**
	 *
	 * @return
	 */
	public boolean isProtected()
	{
		return isProtected;
	}

	/**
	 *
	 * @param isProtected
	 */
	public void setProtected(boolean isProtected)
	{
		this.isProtected = isProtected;
	}

	/**
	 *
	 * @return
	 */
	public boolean includeLayout()
	{
		return includeLayout;
	}

	/**
	 *
	 * @param includeLayout
	 */
	public void setIncludeLayout(boolean includeLayout)
	{
		this.includeLayout = includeLayout;
	}

	/**
	 *
	 * @return
	 */
	public String getCacheTime()
	{
		return cacheTime;
	}

	/**
	 *
	 * @param cacheTime
	 */
	public void setCacheTime(String cacheTime)
	{
		this.cacheTime = cacheTime;
	}

	/**
	 *
	 * @return
	 */
	public boolean includeCache()
	{
		return includeCache;
	}

	/**
	 *
	 * @param includeCache
	 */
	public void setIncludeCache(boolean includeCache)
	{
		this.includeCache = includeCache;
	}

	/**
	 *
	 * @return
	 */
	public boolean noFollow()
	{
		return noFollow;
	}

	/**
	 *
	 * @param noFollow
	 */
	public void setNoFollow(boolean noFollow)
	{
		this.noFollow = noFollow;
	}

	/**
	 *
	 * @return
	 */
	public String getCssClass()
	{
		return cssClass;
	}

	/**
	 *
	 * @param cssClass
	 */
	public void setCssClass(String cssClass)
	{
		this.cssClass = cssClass;
	}

	/**
	 *
	 * @return
	 */
	public boolean isHiddenInNav()
	{
		return hideInNav;
	}

	/**
	 *
	 * @param hide
	 */
	public void setHideInNav(boolean hide)
	{
		this.hideInNav = hide;
	}

	/**
	 *
	 * @return
	 */
	public boolean isPublished()
	{
		return published;
	}

	/**
	 *
	 * @param published
	 */
	public void setPublished(boolean published)
	{
		this.published = published;
	}

	/**
	 *
	 * @return
	 */
	public String getLastVersion()
	{
		return lastVersion;
	}

	/**
	 *
	 * @param lastVersion
	 */
	public void setLastVersion(String lastVersion)
	{
		this.lastVersion = lastVersion;
	}

	/**
	 *
	 * @return
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 *
	 * @param path
	 */
	public void setPath(String path)
	{
		this.path = path;
	}

	/**
	 *
	 * @return
	 */
	public String getPageType()
	{
		return pageType;
	}

	/**
	 *
	 * @param pageType
	 */
	public void setPageType(String pageType)
	{
		this.pageType = pageType;
	}

	@Override
	public String toString()
	{
		return "Page{" + "id=" + id + ", htmlTitle=" + htmlTitle + ", urlAlias="
				+ urlAlias + ", internalName=" + internalName + ", layout="
				+ layout + ", forwardTo=" + forwardTo + ", permission="
				+ permission + ", parentId=" + ", order=" + ", level=" + level
				+ ", description=" + description + ", keywords=" + keywords
				+ ", author=" + author + ", language=" + language
				+ ", noFollow=" + noFollow + ", noIndex=" + noIndex
				+ ", createSitemap=" + createSitemap + ", sitemapName="
				+ sitemapName + ", isProtected=" + isProtected
				+ ", includeLayout=" + includeLayout + ", cacheTime="
				+ cacheTime + ", includeCache=" + includeCache + ", cssClass="
				+ cssClass + ", hide=" + hideInNav + ", published=" + published
				+ ", lastVersion=" + lastVersion + ", path=" + path
				+ ", pageType=" + pageType + '}';
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(
				// articles,
				author, cacheTime, createSitemap, cssClass, description,
				forwardTo, hideInNav, htmlTitle, id, includeCache,
				includeLayout, internalName, isProtected, keywords, language,
				lastVersion, layout, level, noFollow, noIndex, pageType, path,
				permission, published, sitemapName, urlAlias
		);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		return
//				Objects.equals(articles, other.articles)
//				&& 
		Objects.equals(author, other.author)
				&& Objects.equals(cacheTime, other.cacheTime)
				&& createSitemap == other.createSitemap
				&& Objects.equals(cssClass, other.cssClass)
				&& Objects.equals(description, other.description)
				&& Objects.equals(forwardTo, other.forwardTo)
				&& hideInNav == other.hideInNav
				&& Objects.equals(htmlTitle, other.htmlTitle) && id == other.id
				&& includeCache == other.includeCache
				&& includeLayout == other.includeLayout
				&& Objects.equals(internalName, other.internalName)
				&& isProtected == other.isProtected
				&& Objects.equals(keywords, other.keywords)
				&& Objects.equals(language, other.language)
				&& Objects.equals(lastVersion, other.lastVersion)
				&& layout == other.layout && level == other.level
				&& noFollow == other.noFollow && noIndex == other.noIndex
				&& Objects.equals(pageType, other.pageType)
				&& Objects.equals(path, other.path)
				&& permission == other.permission
				&& published == other.published
				&& Objects.equals(sitemapName, other.sitemapName)
				&& Objects.equals(urlAlias, other.urlAlias);
	}

}
