package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.organism.Accordion;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;

import j2html.tags.DomContent;

public class ElementsView extends AbstractView
{
	private static ElementsView instance;

	private ElementsView()
	{
		super();
	}

	public static ElementsView getInstance()
	{
		if (instance == null)
		{
			instance = new ElementsView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		return div(
		    // /*
//				 * Button
//				 */
//				ButtonBuilder.create().withText("Button").build(), br(),
//				/*
//				 * With Color
//				 */
//				ButtonBuilder.create().withText("Primary")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).build(),
//				br(),
//				ButtonBuilder.create().withText("Secondary")
//						.withColor(ButtonBuilder.ColorDV.SECONDARY).build(),
//				br(),
//				ButtonBuilder.create().withText("Success")
//						.withColor(ButtonBuilder.ColorDV.SUCCESS).build(),
//				br(),
//				ButtonBuilder.create().withText("Danger")
//						.withColor(ButtonBuilder.ColorDV.DANGER).build(),
//				br(),
//				ButtonBuilder.create().withText("Warning")
//						.withColor(ButtonBuilder.ColorDV.WARNING).build(),
//				br(),
//				ButtonBuilder.create().withText("Info")
//						.withColor(ButtonBuilder.ColorDV.INFO).build(),
//				br(),
//				ButtonBuilder.create().withText("Light")
//						.withColor(ButtonBuilder.ColorDV.LIGHT).build(),
//				br(),
//				ButtonBuilder.create().withText("Dark")
//						.withColor(ButtonBuilder.ColorDV.DARK).build(),
//				br(),
//				ButtonBuilder.create().withText("Link")
//						.withColor(ButtonBuilder.ColorDV.LINK).build(),
//				br(),
//				/*
//				 * Outline Buttons
//				 */
//				ButtonBuilder.create().withText("Primary")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Secondary")
//						.withColor(ButtonBuilder.ColorDV.SECONDARY).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Success")
//						.withColor(ButtonBuilder.ColorDV.SUCCESS).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Danger")
//						.withColor(ButtonBuilder.ColorDV.DANGER).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Warning")
//						.withColor(ButtonBuilder.ColorDV.WARNING).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Info")
//						.withColor(ButtonBuilder.ColorDV.INFO).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Light")
//						.withColor(ButtonBuilder.ColorDV.LIGHT).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Dark")
//						.withColor(ButtonBuilder.ColorDV.DARK).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Link")
//						.withColor(ButtonBuilder.ColorDV.LINK).outline()
//						.build(),
//				br(),
//				/*
//				 * Size
//				 */
//				ButtonBuilder.create().withText("Primary")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY)
//						.withSize(ButtonSizeDV.LARGE).build(),
//				br(),
//				ButtonBuilder.create().withText("Secondary")
//						.withColor(ButtonBuilder.ColorDV.SECONDARY)
//						.withSize(ButtonSizeDV.SMALL).build(),
//				br(),
//				/*
//				 * Disabled
//				 */
//				ButtonBuilder.create().withText("Primary disabled")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).disabled()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Secondary disabled")
//						.withColor(ButtonBuilder.ColorDV.SECONDARY).disabled()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Primary outline disabled")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).disabled()
//						.outline().build(),
//				br(),
//				ButtonBuilder.create().withText("Secondary outline disabled")
//						.withColor(ButtonBuilder.ColorDV.SECONDARY).disabled()
//						.outline().build(),
//				br(),
//				/*
//				 * Toggle
//				 */
//				ButtonBuilder.create().withText("Toggle button")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY)
//						.isToggled(false).build(),
//				br(),
//				ButtonBuilder.create().withText("Active toggle button")
//						.isToggled(true)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).build(),
//				br(),
//				ButtonBuilder.create().withText("Disabled toggle button")
//						.isToggled(false)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).disabled()
//						.build(),
//				br(),
//				/*
//				 * Anchor
//				 */
//				ButtonBuilder.create().withText("Link")
//						.withButtonTagType(ButtonTagTypeDV.A)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).build(),
//				br(),
//				ButtonBuilder.create().withText("Link Outline")
//						.withButtonTagType(ButtonTagTypeDV.A)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Link Large")
//						.withButtonTagType(ButtonTagTypeDV.A)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY)
//						.withSize(ButtonSizeDV.LARGE).build(),
//				br(),
//				ButtonBuilder.create().withText("Link Disabled")
//						.withButtonTagType(ButtonTagTypeDV.A)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).disabled()
//						.build(),
//				br(),
//				/*
//				 * Input Types
//				 */
//				ButtonBuilder.create().withText("Input")
//						.withButtonTagType(ButtonTagTypeDV.INPUT)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).build(),
//				br(),
//				ButtonBuilder.create().withText("Submit")
//						.withButtonTagType(ButtonTagTypeDV.INPUT)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Reset")
//						.withButtonTagType(ButtonTagTypeDV.INPUT)
//						.withColor(ButtonBuilder.ColorDV.PRIMARY)
//						.withSize(ButtonSizeDV.LARGE).build(),
//				br(),
//				/*
//				 * Button Types
//				 */
//				ButtonBuilder.create().withText("Input")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).build(),
//				br(),
//				ButtonBuilder.create().withText("Submit")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY).outline()
//						.build(),
//				br(),
//				ButtonBuilder.create().withText("Reset")
//						.withColor(ButtonBuilder.ColorDV.PRIMARY)
//						.withSize(ButtonSizeDV.LARGE).build(),
//				br(),
//				/*
//				 * Cards
//				 */
//				CardBuilder.create()//
////						.withLorem()//
//						.withText(
//								"Lorem ipsum dolor sit amet, consetetur sadipscing"
//										+ " elitr, sed diam nonumy eirmod tempor invidunt"
//										+ " ut labore et dolore magna aliquyam erat, sed"
//										+ " diam voluptua. At vero eos et accusam et"
//										+ " justo duo dolores et ea rebum. Stet clita"
//										+ " kasd gubergren, no sea takimata sanctus est"
//										+ " Lorem ipsum dolor sit amet."
//						).withTitle(HeadlineDV.H5, "Headline 5", false)
//						.withImage(
//								"/bueffeltier/images/teaser/education-2835912_640.jpg",
//								"Test", ImagePositionDV.BOTTOM
//						)
//						.withDomContent(
//								ButtonBuilder.create()
//										.withColor(ColorDV.PRIMARY)
//										.withHref("/bueffeltier")
//										.withText("Button").build()
//						).withSubtitle("Subtitle")
//						.addLinkToBodyContent("www.google.com", "Google")
//						.build(),
//				br(),
//				/*
//				 * Card Header aus Title
//				 */
//				CardBuilder.create()//
////				.withLorem()//
//						.withText(
//								"Lorem ipsum dolor sit amet, consetetur sadipscing"
//										+ " elitr, sed diam nonumy eirmod tempor invidunt"
//										+ " ut labore et dolore magna aliquyam erat, sed"
//										+ " diam voluptua. At vero eos et accusam et"
//										+ " justo duo dolores et ea rebum. Stet clita"
//										+ " kasd gubergren, no sea takimata sanctus est"
//										+ " Lorem ipsum dolor sit amet."
//						).withTitle(HeadlineDV.H5, "Headline 5", true)
//						.withDomContent(
//								ButtonBuilder.create()
//										.withColor(ColorDV.PRIMARY)
//										.withHref("/bueffeltier")
//										.withText("Button").build()
//						).withSubtitle("Subtitle")
//						.addLinkToBodyContent("www.google.com", "Google")
//						.build(),
//				br(),
//				/*
//				 * 
//				 */
//				CardBuilder.create()
//						.withList(
//								new String[] { "Liste 1", "Liste 2", "Liste 3" }
//						).build(),
//				br(),
//				/*
//				 * Form Inputs
//				 */
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.EMAIL)//
//						.withLabel("E-Mail", "emailInput")//
//						.withHelpText(
//								"We'll never share your email with anyone else. (i)"
//						).build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.PASSWORD)//
//						.withLabel("Password", "password-input")//
//						.withHelpText(
//								"Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji. (i)"
//						).build(),
//				FormInputBuilder.create()//
//						.disabled()//
//						.withType(FormInputTypeDV.PASSWORD)//
//						.withLabel("Password", "password-input")//
//						.withHelpText(
//								"Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji. (i)"
//						).build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.CHECKBOX)//
//						.withLabel("Checkbox", "checkbox-input")//
//						.build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.TEXT)//
//						.withLabel("Text Input", "text-input")//
//						.build(),
//				FormInputBuilder.create()//
//						.withRows(5).withType(FormInputTypeDV.TEXTAREA)//
//						.withLabel("TextArea Input", "textarea-input")//
//						.build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.TEXT)//
//						.withLabel("Text Input Readonly", "text-input-readonly")//
//						.withValue("Readonly Text")//
//						.readonly()//
//						.build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.FILE)//
//						.withLabel("File Input", "file-input")//
//						.build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.FILEMULTIPLE)//
//						.withLabel("File Input Multiple", "file-input-multiple")//
//						.build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.FILEMULTIPLE)//
//						.disabled()
//						.withLabel("File Input Disabled", "file-input-disabled")//
//						.build(),
//				FormInputBuilder.create()//
//						.withType(FormInputTypeDV.COLOR)//
//						.withLabel("File Input Color", "file-input-color")//
//						.build(),

		    /*
		     * Form Inputs Layout: Vertical
		     */
		    form("", //

//		        RowBuilder.create()//
//		            .withDomContent(

		        ColumnBuilder.create()//
		            .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		            .withDomContent(
		                LabelBuilder.create().build("Label X", "label-x"),
		                FormControlBuilder.create()//
		                    .withId("label-x")//
		                    .build(),
		                LabelBuilder.create().build("Label X", "label-x"),
		                FormControlBuilder.create()//
		                    .withType(FormInputTypeDV.TEXT)//
		                    .withId("label-x")//
		                    .build()
		            )//
		            .build()
//		    )//
//		            .build()
		    ).withClass("mb-3"),
		    /*
		     * Form Inputs Layout: Horizontal
		     */
		    form(
		        "", //
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        LabelBuilder.create()

		                            .build("Label X", "label-x")
		                    )//
		                    .build(),
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.TEXT)//
		                            .withId("label-x")//
		                            .build()
		                    )//

		                    .build()
		            )//

		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.FOUR)//
		                    .withDomContent(
		                        LabelBuilder.create()
		                            .build("Label X", "label-x")
		                    )//
		                    .build(),
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.TEXT)//
		                            .withId("label-x")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(

		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.CHECKBOX)//
		                            .withId("checkbox-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.COLOR)//
		                            .withId("color-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.DATALIST)//
		                            .withId("datalist-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.EMAIL)//
		                            .withId("email-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.FILE)//
		                            .withId("file-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.FILEMULTIPLE)//
		                            .withId("filemultiple-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.PASSWORD)//
		                            .withId("password-1")//
		                            .build()
		                    )//
		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.TEXT)//
		                            .withId("text-2")//
		                            .build()//
		                    )//

		                    .build()
		            )//
		            .build(),
		        RowBuilder.create()//
		            .withDomContent(
		                ColumnBuilder.create()//
		                    .withGridWidth(ColumnBuilder.GridWidthDV.SIX)//
		                    .withDomContent(
		                        FormControlBuilder.create()//
		                            .withType(FormInputTypeDV.TEXTAREA)//
		                            .withId("textare-1")//
		                            .build()
		                    )//
		                    .build()

		            )//
		            .build()
		    ).withClass("mb-3"),

		    accordion()

		);
	}

	private DomContent accordion()
	{
		Accordion accordion = new Accordion("TestAccordion");
		accordion.expandAll();

		return accordion.buildAccordionContainer(
		    accordion.buildAccordionItem(
		        accordion.buildItemHeaderH2("Header"), //
		        accordion.buildItemBody(div(p("Text")))
		    ),
		    accordion.buildAccordionItem(
		        accordion.buildItemHeaderH2("Header"), //
		        accordion.buildItemBody(div(p("Text")))
		    ),
		    accordion.buildAccordionItem(
		        accordion.buildItemHeaderH2("Header"), //
		        accordion.buildItemBody(div(p("Text")))
		    )
		);
	}

	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void setCssId(String containerTagClassName)
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected void setContainerTag()
	{
		// TODO Auto-generated method stub
	}
}