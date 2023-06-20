package com.bueffeltier.ui.html.molecule;

import static j2html.TagCreator.*;

import org.apache.commons.lang3.StringUtils;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class customTag
{

	/**
	 *
	 */
	public customTag()
	{

	}

	public static DomContent
	    submitButton(String text, String name, String value)
	{
		return button().withText(text).withName(name).withValue(value)
		    .withType("submit");
	}

	public static DomContent
	    submitButton(String text, String name, String value, String idOpt)
	{
		if (StringUtils.isNotBlank(idOpt))
		{
			return button().withText(text).withName(name).withValue(value)
			    .withType("submit").withId(idOpt);
		} else
		{
			return button().withText(text).withName(name).withValue(value)
			    .withType("submit");
		}
	}

	/**
	 * OnClickButton
	 * 
	 * @param text
	 * @param name
	 * @param function
	 * @return
	 */
	public static DomContent
	    onClickButton(String text, String name, String function)
	{
		return button().withText(text).withName(name).withType("button")
		    .attr("onclick", function);
	}

	// todo: param onclick umbenennen!
	/**
	 *
	 * @param text
	 * @param name
	 * @param value
	 * @param enabled
	 * @param articleId
	 * @param onclick
	 * @return
	 */
	public static DomContent clientSideActionButtonSwitchable(
	    String text,
	    String name,
	    String value,
	    boolean enabled,
	    long articleId,
	    String onclick
	)
	{
		if (!enabled)
		{
			return button().withText(text).withName(name).withValue(value).attr(
			    "type = \"button\" onclick=\"" + onclick + "(" + articleId
			        + ")\""
			); // !
//                .attr("type = \"button\" onclick=\"document.showDialog(\'" + articleId + "\')\""); // !
//                .attr("type = \"button\" onclick=\"showDialog()\""); // !
		} else
		{
			return button().withText(text).withName(name).withValue(value).attr(
			    "disabled=\"disabled\" type = \"button\" onclick=\"showDialog()\""
			);
		}
	}

	/**
	 *
	 * @param name
	 * @param value
	 * @param id
	 * @return
	 */
	public static DomContent inputText(String name, String value, String id)
	{
		// todo: id vom System generieren und verwalten!
		if (value != null && !"".equals(value))
		{
			return input().withType("text").withName(name).withValue(value)
			    .withId(id);
		} else
		{
			return input().withType("text").withName(name).withId(id);
		}
	}
}
