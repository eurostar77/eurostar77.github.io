package com.bueffeltier.data.jdbc;
// Generated 21.10.2018 03:11:17 by Hibernate Tools 4.3.1

import java.time.LocalDateTime;

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ElementJDBCFlat implements java.io.Serializable
{

	private long id;
	private long parentId;
	private int sorting;
	private String type;
	private boolean isDynamicHtml;
	private String html;
	private int headlineType;
	private String headlineText;
	private boolean guestsOnly;
	private String cssId;
	private String cssClass;
	private boolean hide;
	private java.time.LocalDateTime showFrom;
	private java.time.LocalDateTime showUntil;

	/**
	 *
	 */
	public ElementJDBCFlat()
	{

	}

	/**
	 *
	 * @param html
	 */
	public ElementJDBCFlat(String html)
	{
		this.html = html;
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
	public int getSorting()
	{
		return this.sorting;
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
		return isDynamicHtml;
	}

	public void setDynamicHtml(boolean dynamicHtml)
	{
		this.isDynamicHtml = dynamicHtml;
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
	public void setHidden(boolean hide)
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
