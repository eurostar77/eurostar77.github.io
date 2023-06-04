//package com.bueffeltier.data.hibernate.entity;
//
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.Type;
//
///**
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//@Entity(name = "Article")
//@Table(name = "article", catalog = "bueffeltier")
//public class Article implements java.io.Serializable
//{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@ManyToOne(fetch = FetchType.LAZY)
////	@JoinColumn(name = "id", nullable = false)
//	private Page page;
//
//	@Column(name = "parent_id", nullable = true, length = 10)
//	private int parentId;
//
//	@Id
////	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue
////	@Column(name = "id", unique = true, nullable = false, length = 10)
//	private int id;
//
//	@Column(name = "tr_order", nullable = true, length = 10)
//	private int order;
//
//	@Column(name = "tr_title", nullable = true, length = 255)
//	private String title;
//
//	@Column(name = "tr_author", nullable = true, length = 45)
//	private String author;
//
//	@Column(name = "tr_teaser_text", nullable = true, length = 255)
//	private String teaserText;
//
//	@Column(name = "tr_teaser_image_path", nullable = true, length = 255)
//	private String teaserImagePath;
//
////    @Column(name = "tr_last_edit_date", nullable = true, length = 8)
////    private String lastEditDate;
//	@Type(type = "org.hibernate.type.NumericBooleanType")
//	@Column(columnDefinition = "TINYINT", name = "tr_show_teaser", nullable = false, length = 1)
//	private Boolean showTeaser;
//
//	@Type(type = "org.hibernate.type.NumericBooleanType")
//	@Column(columnDefinition = "TINYINT", name = "tr_is_published", nullable = false, length = 1)
//	private Boolean isPublished;
//
//	@Column(name = "tr_keywords", nullable = true, length = 255)
//	private String keywords;
//
//	@Column(name = "tr_teaser_css_class", nullable = true, length = 255)
//	private String teaserCssClass;
//
//	@Column(name = "tr_teaser_css_id", nullable = true, length = 255)
//	private String teaserCssId;
//
//	@Column(name = "tr_css_class", nullable = true, length = 255)
//	private String cssClass;
//
//	@Column(name = "tr_css_id", nullable = true, length = 255)
//	private String cssId;
//
//	@Column(name = "tr_container_tag", nullable = true, length = 255)
//	private String containerTag;
//
//	/**
//	 *
//	 */
//	public Article()
//	{
//		init();
//	}
//
//	/**
//	 *
//	 * @param title
//	 */
//	public Article(String title)
//	{
//		init();
//		this.title = title;
//	}
//
//	private void init()
//	{
//		// todo: methode init sinnvoll?
//		this.id = 0;
//		this.parentId = 0;
//		this.order = 0;
//		this.title = "";
//		this.author = "";
//		this.teaserText = "";
//		this.teaserImagePath = "";
//		this.showTeaser = false;
//		this.containerTag = "article";
//		this.showTeaser = false;
//		this.isPublished = false;
//	}
//
//	public Page getPage()
//	{
//		return page;
//	}
//
//	public void setPage(Page page)
//	{
//		this.page = page;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public int getId()
//	{
//		return this.id;
//	}
//
//	/**
//	 *
//	 * @param nummer
//	 */
//	public void setId(int nummer)
//	{
//		this.id = nummer;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public int getParentId()
//	{
//		return this.parentId;
//	}
//
//	/**
//	 *
//	 * @param parentId
//	 */
//	public void setParentId(int parentId)
//	{
//		this.parentId = parentId;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getTitle()
//	{
//		return this.title;
//	}
//
//	/**
//	 *
//	 * @param title
//	 */
//	public void setTitle(String title)
//	{
//		this.title = title;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public int getOrder()
//	{
//		return order;
//	}
//
//	/**
//	 *
//	 * @param order
//	 */
//	public void setOrder(int order)
//	{
//		this.order = order;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getAuthor()
//	{
//		return author;
//	}
//
//	/**
//	 *
//	 * @param author
//	 */
//	public void setAuthor(String author)
//	{
//		this.author = author;
//	}
//
////    public String getLastEditDate()
////    {
////        return lastEditDate;
////    }
////
////    public void setLastEditDate(String lastEditDate)
////    {
////        this.lastEditDate = lastEditDate;
////    }
//	/**
//	 *
//	 * @return
//	 */
//	public String getTeaserText()
//	{
//		return this.teaserText;
//	}
//
//	/**
//	 *
//	 * @param teaserText
//	 */
//	public void setTeaserText(String teaserText)
//	{
//		this.teaserText = teaserText;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getTeaserImagePath()
//	{
//		return this.teaserImagePath;
//	}
//
//	/**
//	 *
//	 * @param teaserImagePath
//	 */
//	public void setTeaserImagePath(String teaserImagePath)
//	{
//		this.teaserImagePath = teaserImagePath;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public boolean showTeaser()
//	{
//		return showTeaser;
//	}
//
//	/**
//	 *
//	 * @param showTeaser
//	 */
//	public void setShowTeaser(boolean showTeaser)
//	{
//		this.showTeaser = showTeaser;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public boolean isPublished()
//	{
//		return isPublished;
//	}
//
//	/**
//	 *
//	 * @param isPublished
//	 */
//	public void setPublished(boolean isPublished)
//	{
//		this.isPublished = isPublished;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getKeywords()
//	{
//		return keywords;
//	}
//
//	/**
//	 *
//	 * @param keywords
//	 */
//	public void setKeywords(String keywords)
//	{
//		this.keywords = keywords;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getTeaserCssClass()
//	{
//		return teaserCssClass;
//	}
//
//	/**
//	 *
//	 * @param teaserCssClass
//	 */
//	public void setTeaserCssClass(String teaserCssClass)
//	{
//		this.teaserCssClass = teaserCssClass;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getTeaserCssId()
//	{
//		return teaserCssId;
//	}
//
//	/**
//	 *
//	 * @param teaserCssId
//	 */
//	public void setTeaserCssId(String teaserCssId)
//	{
//		this.teaserCssId = teaserCssId;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getCssClass()
//	{
//		return cssClass;
//	}
//
//	/**
//	 *
//	 * @param cssClass
//	 */
//	public void setCssClass(String cssClass)
//	{
//		this.cssClass = cssClass;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getCssId()
//	{
//		return cssId;
//	}
//
//	/**
//	 *
//	 * @param cssId
//	 */
//	public void setCssId(String cssId)
//	{
//		this.cssId = cssId;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	public String getContainerTag()
//	{
//		return containerTag;
//	}
//
//	/**
//	 *
//	 * @param containerTag
//	 */
//	public void setContainerTag(String containerTag)
//	{
//		this.containerTag = containerTag;
//	}
//
//	@Override
//	public String toString()
//	{
//		return "Article{" + "\n" + "parentId=" + parentId + "\n" + ", id=" + id
//				+ "\n" + ", order=" + order + "\n" + ", title=" + title + "\n"
//				+ ", author=" + author + "\n"
//				// + ", lastEditDate=" + lastEditDate + "\n"
//				+ ", showTeaser=" + showTeaser + "\n" + ", isPublished="
//				+ isPublished + "\n" + ", keywords=" + keywords + "\n"
//				+ ", teaserCssClass=" + teaserCssClass + "\n" + ", teaserCssId="
//				+ teaserCssId + "\n" + ", cssClass=" + cssClass + "\n"
//				+ ", cssId=" + cssId + "\n" + ", containerTag=" + containerTag
//				+ "\n" + '}';
//	}
//
//	@Override
//	public int hashCode()
//	{
//		return Objects.hash(
//				author, containerTag, cssClass, cssId, id, isPublished,
//				keywords, order, page, parentId, showTeaser, teaserCssClass,
//				teaserCssId, teaserImagePath, teaserText, title
//		);
//	}
//
//	@Override
//	public boolean equals(Object obj)
//	{
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Article other = (Article) obj;
//		return Objects.equals(author, other.author)
//				&& Objects.equals(containerTag, other.containerTag)
//				&& Objects.equals(cssClass, other.cssClass)
//				&& Objects.equals(cssId, other.cssId) && id == other.id
//				&& Objects.equals(isPublished, other.isPublished)
//				&& Objects.equals(keywords, other.keywords)
//				&& order == other.order && Objects.equals(page, other.page)
//				&& parentId == other.parentId
//				&& Objects.equals(showTeaser, other.showTeaser)
//				&& Objects.equals(teaserCssClass, other.teaserCssClass)
//				&& Objects.equals(teaserCssId, other.teaserCssId)
//				&& Objects.equals(teaserImagePath, other.teaserImagePath)
//				&& Objects.equals(teaserText, other.teaserText)
//				&& Objects.equals(title, other.title);
//	}
//
//}
