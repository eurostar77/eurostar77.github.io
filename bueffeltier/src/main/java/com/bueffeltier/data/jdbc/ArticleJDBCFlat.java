package com.bueffeltier.data.jdbc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ArticleJDBCFlat implements java.io.Serializable
{
// TODO sveng 01.11.2022: in materialien alle felder initialisieren
	private List<ElementJDBCFlat> elements;
	private long id;
	private long parentId;
	private int sorting;
	private String title;
	private String author;
	private String teaserText;
	private String teaserImagePath;
	private boolean showTeaser;
	private LocalDateTime lastEditDate;
	private boolean isPublished;
	private String keywords;
	private String teaserCssClass;
	private String teaserCssId;
	private String cssClass;
	private String cssId;
	private String containerTag;
	private LocalDateTime createTime;
	private java.time.LocalDateTime showFrom;
	private java.time.LocalDateTime showUntil;
	private String layoutColumn;
	private String pagePath;

	/**
	 *
	 */
	public ArticleJDBCFlat()
	{
		init();
	}

	/**
	 *
	 * @param title
	 */
	public ArticleJDBCFlat(String title)
	{
		init();
		this.title = title;
	}

	private void init()
	{
		// todo: methode init sinnvoll?
		this.id = 0;
		this.parentId = 0;
		this.sorting = 0;
		this.title = "";
		this.author = "";
		this.teaserText = "";
		this.teaserImagePath = "";
		this.showTeaser = false;
		this.containerTag = "article";
		this.showTeaser = false;
		this.isPublished = false;
	}

	public List<ElementJDBCFlat> getElements()
	{
		return elements;
	}

	public void setElements(List<ElementJDBCFlat> elements)
	{
		this.elements = elements;
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
	 * @param nummer
	 */
	public void setId(long nummer)
	{
		this.id = nummer;
	}

	/**
	 *
	 * @return
	 */
	public long getParentId()
	{
		return this.parentId;
	}

	/**
	 *
	 * @param parentId
	 */
	public void setParentId(long parentId)
	{
		this.parentId = parentId;
	}

	/**
	 *
	 * @return
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 *
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 *
	 * @return
	 */
	public int getSorting()
	{
		return sorting;
	}

	/**
	 *
	 * @param order
	 */
	public void setSorting(int order)
	{
		this.sorting = order;
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

//    public String getLastEditDate()
//    {
//        return lastEditDate;
//    }
//
//    public void setLastEditDate(String lastEditDate)
//    {
//        this.lastEditDate = lastEditDate;
//    }
	/**
	 *
	 * @return
	 */
	public String getTeaserText()
	{
		return this.teaserText;
	}

	/**
	 *
	 * @param teaserText
	 */
	public void setTeaserText(String teaserText)
	{
		this.teaserText = teaserText;
	}

	/**
	 *
	 * @return
	 */
	public String getTeaserImagePath()
	{
		return this.teaserImagePath;
	}

	/**
	 *
	 * @param teaserImagePath
	 */
	public void setTeaserImagePath(String teaserImagePath)
	{
		this.teaserImagePath = teaserImagePath;
	}

	/**
	 *
	 * @return
	 */
	public boolean showTeaser()
	{
		return showTeaser;
	}

	/**
	 *
	 * @param showTeaser
	 */
	public void setShowTeaser(boolean showTeaser)
	{
		this.showTeaser = showTeaser;
	}

	public LocalDateTime getLastEditDate()
	{
		return lastEditDate;
	}

	public void setLastEditDate(LocalDateTime lastEditDate)
	{
		this.lastEditDate = lastEditDate;
	}

	/**
	 *
	 * @return
	 */
	public boolean isPublished()
	{
		return isPublished;
	}

	/**
	 *
	 * @param isPublished
	 */
	public void setPublished(boolean isPublished)
	{
		this.isPublished = isPublished;
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
	public String getTeaserCssClass()
	{
		return teaserCssClass;
	}

	/**
	 *
	 * @param teaserCssClass
	 */
	public void setTeaserCssClass(String teaserCssClass)
	{
		this.teaserCssClass = teaserCssClass;
	}

	/**
	 *
	 * @return
	 */
	public String getTeaserCssId()
	{
		return teaserCssId;
	}

	/**
	 *
	 * @param teaserCssId
	 */
	public void setTeaserCssId(String teaserCssId)
	{
		this.teaserCssId = teaserCssId;
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
	public String getCssId()
	{
		return cssId;
	}

	/**
	 *
	 * @param cssId
	 */
	public void setCssId(String cssId)
	{
		this.cssId = cssId;
	}

	/**
	 *
	 * @return
	 */
	public String getContainerTag()
	{
		return containerTag;
	}

	/**
	 *
	 * @param containerTag
	 */
	public void setContainerTag(String containerTag)
	{
		this.containerTag = containerTag;
	}

	public LocalDateTime getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(LocalDateTime createDateTime)
	{
		this.createTime = createDateTime;
	}

	public java.time.LocalDateTime getShowFrom()
	{
		return showFrom;
	}

	public void setShowFrom(java.time.LocalDateTime showFrom)
	{
		this.showFrom = showFrom;
	}

	public java.time.LocalDateTime getShowUntil()
	{
		return showUntil;
	}

	public void setShowUntil(java.time.LocalDateTime showUntil)
	{
		this.showUntil = showUntil;
	}

	public String getLayoutColumn()
	{
		return layoutColumn;
	}

	public void setLayoutColumn(String layoutColumn)
	{
		this.layoutColumn = layoutColumn;
	}

	public String getPagePath()
	{
		return pagePath;
	}

	public void setPagePath(String pagePath)
	{
		this.pagePath = pagePath;
	}

	public boolean isShowTeaser()
	{
		return showTeaser;
	}

	@Override
	public String toString()
	{
		return "Article{" + "\n" + "parentId=" + parentId + "\n" + ", id=" + id
				+ "\n" + ", order=" + sorting + "\n" + ", title=" + title + "\n"
				+ ", author=" + author + "\n"
				// + ", lastEditDate=" + lastEditDate + "\n"
				+ ", showTeaser=" + showTeaser + "\n" + ", isPublished="
				+ isPublished + "\n" + ", keywords=" + keywords + "\n"
				+ ", teaserCssClass=" + teaserCssClass + "\n" + ", teaserCssId="
				+ teaserCssId + "\n" + ", cssClass=" + cssClass + "\n"
				+ ", cssId=" + cssId + "\n" + ", containerTag=" + containerTag
				+ "\n" + '}';
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(
				author, containerTag, cssClass, cssId, id, isPublished,
				keywords, sorting, parentId, showTeaser, teaserCssClass,
				teaserCssId, teaserImagePath, teaserText, title
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
		ArticleJDBCFlat other = (ArticleJDBCFlat) obj;
		return Objects.equals(author, other.author)
				&& Objects.equals(containerTag, other.containerTag)
				&& Objects.equals(cssClass, other.cssClass)
				&& Objects.equals(cssId, other.cssId) && id == other.id
				&& Objects.equals(isPublished, other.isPublished)
				&& Objects.equals(keywords, other.keywords)
				&& parentId == other.parentId
				&& Objects.equals(showTeaser, other.showTeaser)
				&& Objects.equals(teaserCssClass, other.teaserCssClass)
				&& Objects.equals(teaserCssId, other.teaserCssId)
				&& Objects.equals(teaserImagePath, other.teaserImagePath)
				&& Objects.equals(teaserText, other.teaserText)
				&& Objects.equals(title, other.title);
	}

}
