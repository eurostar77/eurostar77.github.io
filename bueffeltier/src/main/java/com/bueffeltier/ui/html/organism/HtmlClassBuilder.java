package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.div;

import j2html.tags.DomContent;

public class HtmlClassBuilder
{
	private HtmlClassBuilder()
	{
	}

	public static HtmlClassBuilder create()
	{
		return new HtmlClassBuilder();
	}

	public HtmlClassBuilder withX(DomContent domContent)
	{
		return this;
	}

	public DomContent build()
	{
		return div(

		).withClass("");
	}
}
