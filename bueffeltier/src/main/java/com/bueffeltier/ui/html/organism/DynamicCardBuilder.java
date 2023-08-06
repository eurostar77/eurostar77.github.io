package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.RowBuilder.RowAlignmentDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;

public class DynamicCardBuilder
{
	private List<DivTag> cards;
	private DivTag prototypeCard;
	private boolean withUpAndDownBtns;
	private int maxLength = 100;
	private String classString;
	private String idString;
	private String addBtnText;
	private String headlineBase = "Karte ";
	private Accordion accordion;
	private Integer cardCount = 1;
	private boolean withAccordion = true;
	private boolean expandAll = false;

	private DynamicCardBuilder()
	{
	}

	public static DynamicCardBuilder create()
	{
		return new DynamicCardBuilder();
	}

	public DynamicCardBuilder withCards(List<DivTag> cards)
	{
		if (this.cards == null)
		{
			this.cards = new ArrayList<>();
		}
		this.cards.addAll(cards);
		return this;
	}

	/**
	 * Prototype darf keine values enthalten. Ids, Arias und ähnliche müssen mit
	 * dem Suffix 0 bezeichnet werden.
	 */
	public DynamicCardBuilder withPrototypeCard(DivTag prototype)
	{
		this.prototypeCard = prototype;
		return this;
	}

	public DynamicCardBuilder withUpAndDownButtons(boolean withUpAndDownBtns)
	{
		this.withUpAndDownBtns = true;
		return this;
	}

	public DynamicCardBuilder withMaxLength(int maxLength)
	{
		this.maxLength = maxLength;
		return this;
	}

	public DynamicCardBuilder withClass(String classString)
	{
		this.classString = classString;
		return this;
	}

	public DynamicCardBuilder withId(String idString)
	{
		this.idString = idString;
		return this;
	}

	public DynamicCardBuilder withAddBtnText(String addBtnText)
	{
		this.addBtnText = addBtnText;
		return this;
	}

	public DynamicCardBuilder withHeadline(String headline)
	{
		this.headlineBase = headline + " ";
		return this;
	}

//	public DynamicCardBuilder withAccordion()
//	{
//		return this;
//	}
	public DynamicCardBuilder expandAll()
	{
		this.expandAll = true;
		return this;
	}

	public DivTag build(String identifier)
	{
		this.accordion = new Accordion(identifier);
		if (expandAll)
		{
			accordion.expandAll();
		}

		if (prototypeCard != null & cards != null)
		{
			return div(

			    prototypeCardContainer(),

			    dynamicCardsHeader(),

			    dynamicCardsContainer()

			)//
			    .withClass("dynamic-cards-main")//
			    .withData(
			        "dynamic-cards-max-length", Integer.toString(maxLength)
			    );

		} else
		{
			return null;
		}
	}

	private DomContent dynamicCardsHeader()
	{
		return div(

		    ButtonBuilder.create()//
		        .withColor(ColorDV.PRIMARY)//
		        .withSpacing(
		            SpacingPropertyDV.MARGIN, SpacingSidesDV.Y,
		            SpacingSizeDV.THREE
		        )//
		        .withClass("btn-main-add-dynamic-card")//
		        .withText(addBtnText)//
		        .build()

		);
	}

	private DomContent prototypeCardContainer()
	{
		return div(
		    //
		    buildPrototypeCard(accordion, prototypeCard)
		)//
		    .withClass("dynamic-cards-prototype")//
		    .withStyle("display: none;");
	}

	private DomContent dynamicCardsContainer()
	{
		return accordion.buildAccordionContainer(
		    each(
		        //
		        cards, card -> //
				buildVisibleCard(card)
		    )//
		).withClass("accordion dynamic-cards-cards");
	}

	private DomContent
	    buildPrototypeCard(Accordion accordion, DomContent prototypeContent)
	{
		return buildCard(prototypeContent, 0);
	}

	private DomContent buildVisibleCard(DomContent visibleContent)
	{
		return buildCard(visibleContent, cardCount);
	}

	private DomContent buildCard(DomContent cardContent, Integer cardCount)
	{
		return div(
		    //
		    accordion.buildAccordionItem(
		        accordion.buildItemHeaderH2(headlineBase + cardCount),
		        accordion.buildItemBody(
		            cardContent//
		        )
		    )
		)//
		    .with(addButtons())//
		    .withClass("dynamic-card");
	}

	private DomContent addButtons()
	{
		return RowBuilder.create()//
		    .withAlignment(RowAlignmentDV.END)
		    .withDomContent(
		        ColumnBuilder.create()//
		            .withGridWidth(GridWidthDV.AUTO)
		            .withDomContent(
		                ButtonBuilder.create()//
		                    .withColor(ColorDV.PRIMARY)//
		                    .withClass("btn-add-dynamic-card")//
		                    .withText(addBtnText)//
		                    .build()
		            )//
		            .build()
		    )//
		    .withDomContent(
		        ColumnBuilder.create()//
		            .withGridWidth(GridWidthDV.AUTO)
		            .withDomContent(
		                ButtonBuilder.create()//
		                    .withColor(ColorDV.DANGER)//
//		                    .asOutline()//
		                    .withClass("btn-del-dynamic-card")//
		                    .withText("Löschen")//
		                    .build()
		            )//
		            .build()
		    )//
//		    .withDomContent(
//		        ColumnBuilder.create()//
//		            .withGridWidth(GridWidthDV.AUTO)
//		            .withDomContent(
//		                ButtonBuilder.create()//
//		                    .withColor(ColorDV.WARNING)//
////		                    .asOutline()//
//		                    .withClass("btnRemove")//
//		                    .withName("remove")//
//		                    .withText("Entfernen")//
//		                    .build()
//		            )//
//		            .build()
//		    )//
		    .withClass("my-3")//
		    .build();
	}
}
