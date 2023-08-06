package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.div;

import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.RowBuilder.RowAlignmentDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;

public class FooterNavigationBuilder
{
	private boolean withSaveOption;
	private boolean withSaveAndCloseOption;
	private boolean withAbortOption;
	private boolean withReturnOption;
	private RowAlignmentDV rowAlignment;
	private String saveContainerId;

	private FooterNavigationBuilder()
	{
		//
	}

	public static FooterNavigationBuilder create()
	{
		return new FooterNavigationBuilder();
	}

	public FooterNavigationBuilder withAlignment(RowAlignmentDV alignment)
	{
		this.rowAlignment = alignment;

		return this;
	}

	public FooterNavigationBuilder withSaveOption(String saveContainerId)
	{
		this.saveContainerId = saveContainerId;
		this.withSaveOption = true;
		return this;
	}

	public FooterNavigationBuilder withSaveAndCloseOption()
	{
		withSaveAndCloseOption = true;
		return this;
	}

	public FooterNavigationBuilder withAbortOption()
	{
		withAbortOption = true;
		return this;
	}

	public FooterNavigationBuilder withReturnOption()
	{
		withReturnOption = true;
		return this;
	}

	public DomContent build()
	{
		DivTag footerNav = new DivTag();

		DivTag buttonsNav = new DivTag();
		buttonsNav
		    .withClass("d-flex flex-column flex-sm-row justify-content-sm-end");

		footerNav.with(buttonsNav);

		if (withSaveAndCloseOption)
		{
			buttonsNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Speichern und Schließen")//
			        .withName("saveAndClose")//
			        .withClass("mb-1 ms-sm-1 text-nowrap")//
//			        .withSpacing(
//			            SpacingPropertyDV.MARGIN, SpacingSidesDV.START,
//			            SpacingSizeDV.ONE
//			        )//
			        .build()
			);
		}

		if (withSaveOption)
		{
			buttonsNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Speichern")//
			        .withName("save")//
			        .withClass("btn-save mb-1 ms-sm-1 text-nowrap")//
			        .withData("save-container-id", this.saveContainerId)//
//			        .withSpacing(
//			            SpacingPropertyDV.MARGIN, SpacingSidesDV.START,
//			            SpacingSizeDV.ONE
//			        )//
			        .build()
			);
		}

		// TODO sveng 02.08.2023: Margins der Buttons je nachdem, welcher am
		// Ende steht anpassen.

		if (withAbortOption)
		{
			buttonsNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Abbrechen")//
			        .withName("abort")//
			        .withClass("mb-1 ms-sm-1 text-nowrap")//
//			        .withSpacing(
//			            SpacingPropertyDV.MARGIN, SpacingSidesDV.START,
//			            SpacingSizeDV.ONE
//			        )//
			        .build()
			);
		}

		if (withReturnOption)
		{
			buttonsNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Zurück")//
			        .withName("back")//
			        .withClass("mb-1 ms-sm-1 text-nowrap")//
//			        .withSpacing(
//			            SpacingPropertyDV.MARGIN, SpacingSidesDV.START,
//			            SpacingSizeDV.ONE
//			        )//
			        .build()
			);
		}

		return div(footerNav.withClass("my-4"));
	}
}
