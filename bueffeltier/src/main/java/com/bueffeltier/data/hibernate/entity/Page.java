package com.bueffeltier.data.hibernate.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
@Entity(name = "Page")
@Table(name = "page", catalog = "bueffeltier")
public class Page implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "tr_parent_id", nullable = true, length = 10)
	private int parentId;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue
//	@Column(name = "id", unique = true, nullable = false, length = 10)
	private int id;

	@OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Article> articles;

	@Column(name = "tr_order", nullable = true, length = 10)
	private int order;

//	@Column(name = "tr_title", unique = true, nullable = true, length = 255)
	@Column(name = "tr_title", nullable = true, length = 255)
	private String htmlTitle;

	@Column(name = "tr_path", nullable = true, length = 255)
	private String path;

	@Column(name = "tr_layout", nullable = true, length = 10)
	private int layout;

	@Column(name = "tr_permission", nullable = true, columnDefinition = "int default 0", length = 10)
	private int permission;

	@Column(name = "tr_forward_to", nullable = true, length = 255)
	private String forwardTo;

	@Column(name = "tr_author", nullable = true, length = 255)
	private String author;

	@Column(name = "tr_cache_time", nullable = true, length = 10)
	private String cacheTime;

//	@JdbcTypeCode(java.sql.Types.BOOLEAN)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_create_sitemap", nullable = true, length = 1)
	private boolean createSitemap;

	@Column(name = "tr_css_class", nullable = true, length = 255)
	private String cssClass;

	@Column(name = "tr_description", nullable = true, length = 255)
	private String description;

	// todo: in int ändern!
	@Column(name = "tr_page_type", nullable = true, length = 255)
	private String pageType;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_hide_in_nav", nullable = true, length = 1)
	private boolean hideInNav;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_include_cache", nullable = true, length = 1)
	private boolean includeCache;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_include_layout", nullable = true, length = 1)
	private boolean includeLayout;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_protected", nullable = true, length = 1)
	private boolean isProtected;

	@Column(name = "tr_keywords", nullable = true, length = 255)
	private String keywords;

	@Column(name = "tr_language", nullable = true, length = 2)
	private String language;

	@Column(name = "tr_last_version", nullable = true, length = 255)
	private String lastVersion;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_published", nullable = true, length = 1)
	private boolean published;

	@Column(name = "tr_sitemap_name", nullable = true, length = 32)
	private String sitemapName;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_no_follow", nullable = true, length = 1)
	private boolean noFollow;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_no_index", nullable = true, length = 1)
	private boolean noIndex;

	@Column(name = "tr_name", nullable = true, length = 255)
	private String internalName;

	@Column(name = "tr_url_alias", nullable = true, length = 255)
	private String urlAlias;

	@Transient
	private int level; // todo: in datenbank speichern und in pageTree nutzen!

	/**
	 *
	 */
	public Page()
	{

	}

	/**
	 *
	 * @param internalName
	 */
	public Page(String internalName)
	{
		init();
		this.internalName = internalName;
	}

	private void init()
	{
		// todo: methode init sinnvoll?
		this.htmlTitle = "";
		this.internalName = "";
		this.layout = 1;
		this.urlAlias = "";
		this.forwardTo = "";
	}

	/**
	 *
	 * @return
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	public void addArticle(Article article)
	{
		articles.add(article);
		article.setPage(this);
	}

	public void removeArticle(Article article)
	{
		articles.remove(article);
		article.setPage(null);
	}

	public List<Article> getArticles()
	{
		return articles;
	}

	public void setArticles(List<Article> articles)
	{
		this.articles = articles;
	}

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
		// TODO sveng 29.10.2022: prüfen der strings gehört in den TreeService,
		// oder in einen anderen Service.
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
	public int getParentId()
	{
		return this.parentId;
	}

	/**
	 *
	 * @param parentId
	 */
	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	/**
	 *
	 * @return
	 */
	public int getOrder()
	{
		return order;
	}

	/**
	 *
	 * @param order
	 */
	public void setOrder(int order)
	{
		this.order = order;
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
	public void setCreateSitemap(boolean createSitemap)
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
	public boolean isIncludeLayout()
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
	public boolean isHideInNav()
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
				+ permission + ", parentId=" + parentId + ", order=" + order
				+ ", level=" + level + ", description=" + description
				+ ", keywords=" + keywords + ", author=" + author
				+ ", language=" + language + ", noFollow=" + noFollow
				+ ", noIndex=" + noIndex + ", createSitemap=" + createSitemap
				+ ", sitemapName=" + sitemapName + ", isProtected="
				+ isProtected + ", includeLayout=" + includeLayout
				+ ", cacheTime=" + cacheTime + ", includeCache=" + includeCache
				+ ", cssClass=" + cssClass + ", hide=" + hideInNav
				+ ", published=" + published + ", lastVersion=" + lastVersion
				+ ", path=" + path + ", pageType=" + pageType + '}';
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(
				articles, author, cacheTime, createSitemap, cssClass,
				description, forwardTo, hideInNav, htmlTitle, id, includeCache,
				includeLayout, internalName, isProtected, keywords, language,
				lastVersion, layout, level, noFollow, noIndex, order, pageType,
				parentId, path, permission, published, sitemapName, urlAlias
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
		return Objects.equals(articles, other.articles)
				&& Objects.equals(author, other.author)
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
				&& order == other.order
				&& Objects.equals(pageType, other.pageType)
				&& parentId == other.parentId
				&& Objects.equals(path, other.path)
				&& permission == other.permission
				&& published == other.published
				&& Objects.equals(sitemapName, other.sitemapName)
				&& Objects.equals(urlAlias, other.urlAlias);
	}

}
