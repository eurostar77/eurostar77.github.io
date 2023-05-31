package com.bueffeltier.data.hibernate.entity;
// Generated 21.10.2018 03:11:17 by Hibernate Tools 4.3.1

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
@Entity
@Table(name = "tl_content", catalog = "bueffeltier")
public class Content implements java.io.Serializable
{

	@Column(name = "tr_parent_id", nullable = true, length = 10)
	private int parentId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tr_id", unique = true, nullable = false, length = 10)
	private int id;

	@Column(name = "tr_order", nullable = true, length = 10)
	private int order;

	@Column(name = "tr_type", nullable = true, length = 32)
	private String type;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_dynamic_html", nullable = true, length = 1)
	private boolean dynamicHtml;

	@Column(name = "tr_html", nullable = true)
	private String html;

	@Column(name = "tr_headline_type", nullable = false, length = 1)
	private int headlineType;

	@Column(name = "tr_headline_text", nullable = true, length = 255)
	private String headlineText;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_guests_only", nullable = true, length = 1)
	private boolean guestsOnly;

	@Column(name = "tr_css_id", nullable = true, length = 255)
	private String cssId;

	@Column(name = "tr_css_class", nullable = true, length = 255)
	private String cssClass;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(columnDefinition = "TINYINT", name = "tr_invisible", nullable = true, length = 1)
	private boolean hide;

	@Column(name = "tr_show_from")
//    @Temporal(TemporalType.TIMESTAMP)
	private java.time.LocalDateTime showFrom;

	@Column(name = "tr_show_until")
	private java.time.LocalDateTime showUntil;

	/**
	 *
	 */
	public Content()
	{

	}

	/**
	 *
	 * @param html
	 */
	public Content(String html)
	{
		this.html = html;
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
		return this.order;
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
	public String getHtml()
	{
		return this.html;
	}

	/**
	 *
	 * @param html
	 */
	public void setHtml(String html)
	{
		this.html = html;
	}

	/**
	 *
	 * @return
	 */
	public String getType()
	{
		return this.type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	public boolean isDynamicHtml()
	{
		return dynamicHtml;
	}

	public void setDynamicHtml(boolean dynamicHtml)
	{
		this.dynamicHtml = dynamicHtml;
	}

	/**
	 *
	 * @return
	 */
	public int getHeadlineType()
	{
		return headlineType;
	}

	/**
	 *
	 * @param headlineType
	 */
	public void setHeadlineType(int headlineType)
	{
		this.headlineType = headlineType;
	}

	/**
	 *
	 * @return
	 */
	public String getHeadlineText()
	{
		return headlineText;
	}

	/**
	 *
	 * @param headlineText
	 */
	public void setHeadlineText(String headlineText)
	{
		this.headlineText = headlineText;
	}

	/**
	 *
	 * @return
	 */
	public boolean guestsOnly()
	{
		return guestsOnly;
	}

	/**
	 *
	 * @param guestsOnly
	 */
	public void setGuestsOnly(boolean guestsOnly)
	{
		this.guestsOnly = guestsOnly;
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
	public boolean isHidden()
	{
		return hide;
	}

	/**
	 *
	 * @param hide
	 */
	public void hide(boolean hide)
	{
		this.hide = hide;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getShowFrom()
	{
		return showFrom;
	}

	/**
	 *
	 * @param showFrom
	 */
	public void setShowFrom(LocalDateTime showFrom)
	{
		this.showFrom = showFrom;
	}

	/**
	 *
	 * @return
	 */
	public LocalDateTime getShowUntil()
	{
		return showUntil;
	}

	/**
	 *
	 * @param showUntil
	 */
	public void setShowUntil(LocalDateTime showUntil)
	{
		this.showUntil = showUntil;
	}
}
