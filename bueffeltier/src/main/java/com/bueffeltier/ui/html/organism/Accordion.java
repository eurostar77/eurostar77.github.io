package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.H2Tag;

public class Accordion
{
	private String baseId;
	private int totalItemCount;
	private boolean removeBackgroundColor;
	private boolean openAll = false;

	public Accordion(String baseId)
	{
		this.baseId = baseId + "Accordion";
	}

	/*
	 * Removes the default background-color, some borders, and some rounded
	 * corners to render accordions edge-to-edge with their parent container.
	 */
	public Accordion flush()
	{
		removeBackgroundColor = true;
		return this;
	}

	public Accordion openAll()
	{
		this.openAll = true;
		return this;
	}

	public DivTag buildAccordionContainer(DomContent... domContent)
	{
		return div(
		    //
		    domContent
		)//
		    .withClass(buildClassString())//
		    .withId(baseId);
	}

	private String buildClassString()
	{
		String classString;

		if (!removeBackgroundColor)
		{
			classString = "accordion";
		} else
		{
			classString = "accordion accordion-flush";
		}

		return classString;
	}

	public DivTag buildAccordionItem(DomContent... domContent)
	{
		this.totalItemCount++;
		return div(
		    //
		    domContent
		).withClass("accordion-item");
	}

	public H2Tag buildHeaderH2(String text)
	{
		return buildHeader(text, "h2");
	}

//	public H2Tag buildHeaderH3(String text)
//	{
//		return buildHeader(text, "h3");
//	}
//
//	public H2Tag buildHeaderH4(String text)
//	{
//		return buildHeader(text, "h4");
//	}

	private H2Tag buildHeader(String text, String bsHeaderClass)
	{
		String ariaExpandedValue = "true";
		String classString = "accordion-button";

		if (!openAll)
		{
			if (totalItemCount == 0)
			{
				ariaExpandedValue = "true";
				classString = "accordion-button";

			} else
			{
				ariaExpandedValue = "false";
				classString = "accordion-button collapsed";
			}
		}

		return h2(
		    button(
		        //
		        i().withClass("bi bi-chevron-down"), //
		        text(text)
		    )//
		        .withClass(classString)//
		        .withType("button")//
		        .withData("bs-toggle", "collapse")//
		        .withData(
		            "bs-target", "#" + baseId + "Collapse" + totalItemCount
		        )//
		        .attr("aria-expanded", ariaExpandedValue)//
		        .condAttr(totalItemCount != 1, "aria-expanded", "false")//
		        .attr("aria-controls", baseId + "Collapse" + totalItemCount)
		)//
		    .withClass("accordion-header " + bsHeaderClass)//
		    .withId(baseId + "Heading" + totalItemCount);
	}

	public DomContent buildBody(DomContent... domContent)
	{
		String collapseClassString = "accordion-collapse collapse show";

		if (!openAll)
		{
			if (totalItemCount == 0)
			{
				collapseClassString = "accordion-collapse collapse show";

			} else
			{
				collapseClassString = "accordion-collapse collapse";
			}
		}

		return div(
		    //
		    div(
		        //
		        domContent
		    ).withClass("accordion-body")
		)//
		    .withClass(collapseClassString)//
		    .withId(baseId + "Collapse" + totalItemCount)//
		    .attr("aria-labelledby", baseId + "Heading" + totalItemCount)//
		    .withData("bs", "heading" + totalItemCount);
	}
}
