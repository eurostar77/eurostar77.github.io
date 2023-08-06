package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.H2Tag;

public class Accordion
{
	// TODO sveng 29.07.2023: collapsed in der class und aria expanded per js
	// setzen.
	// unterscheide open all = zeige alle geöffnet und allowAllOpen
	// füge offene karte ab der zweiten kart hinzu, mache das auch in js!
	// füge js verhalten für open varianten und copy hinzu hinzu!

	private String baseId;
	private int totalItemCount;
	private boolean removeBackgroundColor;
	private boolean openAll = false;

	public Accordion(String idBaseName)
	{
		this.baseId = idBaseName + "Accordion";
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

	public Accordion expandAll()
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

	public DomContent buildItemHeaderH2(String text)
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
		String ariaExpandedValue = "false";
		String classString = "accordion-button collapsed dynamic-id";

		if (!openAll)
		{
			// todo: hier variante ab dem zweiten button, wegen der
			// ausgeblendeten
			// prototype card.
			if (totalItemCount == 0)
			{
				ariaExpandedValue = "true";
				classString = "accordion-button dynamic-id";

			}
//			else
//			{
//				ariaExpandedValue = "false";
//				classString = "accordion-button collapsed dynamic-id";
//			}
		}

		return h2(
		    button(
		        //
		        span(text).withClass("dynamic-id")
		    )//
		        .withClass(classString)//
		        .withType("button")//
		        .withData("bs-toggle", "collapse")//
		        .withData(
		            "bs-target", "#" + baseId + "Collapse" + totalItemCount
		        )//
		        .attr("aria-expanded", ariaExpandedValue)//
		        .condAttr(totalItemCount != 1, "aria-expanded", "false")//
		        .attr("aria-controls", baseId + "Collapse" + totalItemCount)//

		)//
		    .withClass("accordion-header " + bsHeaderClass + " dynamic-id")//
		    .withId(baseId + "Heading" + totalItemCount);
	}

	public DomContent buildItemBody(DomContent... domContent)
	{
		String collapseClassString = "accordion-collapse collapse";

//		if (!openAll)
//		{
//			if (totalItemCount == 1)
//			{
//				collapseClassString = "accordion-collapse collapse show";
//
//			} else
//			{
//				collapseClassString = "accordion-collapse collapse";
//			}
//		}

		return div(
		    //
		    div(
		        //
		        domContent
		    ).withClass("accordion-body")
		)//
		    .withClass(collapseClassString + " dynamic-id")//
		    .withId(baseId + "Collapse" + totalItemCount)//
		    .attr("aria-labelledby", baseId + "Heading" + totalItemCount)//
		    .withData("bs", "heading" + totalItemCount)//
		    .withCondData(!openAll, "bs-parent", "#" + baseId)//
		;
	}
}
