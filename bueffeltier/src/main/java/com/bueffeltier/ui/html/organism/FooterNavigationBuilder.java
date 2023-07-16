package com.bueffeltier.ui.html.organism;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
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

	public FooterNavigationBuilder withSaveOption()
	{
		withSaveOption = true;
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

		if (withSaveAndCloseOption)
		{
			footerNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Speichern und Schließen")//
			        .withName("saveAndClose")//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.ONE
			        )//
			        .build()
			);
		}

		if (withSaveOption)
		{
			footerNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Speichern")//
			        .withName("save")//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.ONE
			        )//
			        .build()
			);
		}

		if (withAbortOption)
		{
			footerNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Abbrechen")//
			        .withName("abort")//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.ONE
			        )//
			        .build()
			);
		}

		if (withReturnOption)
		{
			footerNav.with(
			    //
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withText("Zurück")//
			        .withName("back")//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.ONE
			        )//
			        .build()
			);
		}

		return

		RowBuilder.create()//
//		    .withAlignment(RowAlignmentDV.END)
		    .withDomContent(
		        ColumnBuilder.create()//
		            .withDomContent(
		                //
		                footerNav.withClass(buildClassString())
		            )//
		            .build()
		    ).build();

	}

	private String buildClassString()
	{
		StringBuilder resultBuilder = new StringBuilder();

		resultBuilder.append("container");

		if (rowAlignment != null)
		{
//			resultBuilder.append(" ");
//			resultBuilder.append(rowAlignment.toString());
			resultBuilder.append(" text-end");
		}

		return resultBuilder.toString();
	}
}
