package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import j2html.tags.DomContent;

public class ListGroupBuilder
{
	private List<DomContent> listGroupItemContent;

	private ListGroupBuilder()
	{
	}

	public static ListGroupBuilder create()
	{
		return new ListGroupBuilder();
	}

	/**
	 * Man kann DomContent hinzufügen, d.h. ein ListItem mit Label und input,
	 * oder beliebigen DomCOntent. Die Klasse "list-group-item" wird automatisch
	 * hinzugefügt.
	 */
	public ListGroupBuilder withX(DomContent domContent)
	{
		if (listGroupItemContent != null)
		{
			listGroupItemContent.add(domContent);
		} else
		{
			listGroupItemContent = new ArrayList<>();
			listGroupItemContent.add(domContent);

		}
		return this;
	}

	public DomContent build()
	{
		return ul(
		    //
		    each(listGroupItemContent, c -> li(c).withClass("list-group-item"))
		).withClass("list-group");
	}
}
