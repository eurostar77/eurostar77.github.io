package com.bueffeltier.ui.html.molecule;

import static j2html.TagCreator.*;

import java.util.List;

import com.bueffeltier.ui.html.organism.LabelBuilder;

import j2html.tags.DomContent;
import j2html.tags.Tag;

public class Bootstrap
{

	/*
	 * Classes: Grid: d-grid gap-2 Stacked: d-md-block Width: col-6 Center
	 * Horizontaly: mx-auto
	 */
	public static DomContent bsButtonGroup(List<DomContent> tag, String classes)
	{
		String classesOut;
		if (classes.isBlank())
		{
			classesOut = "btn-group";
		} else
		{
			classesOut = "btn-group " + classes;
		}

		return div(each(tag, dc -> dc)).withClass(classesOut);
	}

	/**
	 *
	 * @param text
	 * @param name
	 * @param value
	 * @return
	 */
	public static Tag bsList(String text, String name, String value)
	{
		// ul = Unordered List
		// ol = Ordered List
		// dl = Description List
		return button().withText(text).withName(name).withValue(value)
		    .withType("submit");
	}

	/**
	 *
	 * @param text
	 * @param name
	 * @param value
	 * @return
	 */
	public static Tag bsAddress(String text, String name, String value)
	{

		return button().withText(text).withName(name).withValue(value)
		    .withType("submit");
	}

	/**
	 * 
	 * 
	 * 
	 * @param text
	 * @param name
	 * @param value
	 * @return
	 */
	public static Tag bsLead(String text, String name, String value)
	{

		return button().withText(text).withName(name).withValue(value)
		    .withType("submit");
	}

	/**
	 * Image
	 * 
	 * @param text
	 * @param name
	 * @param value
	 * @return
	 */
	public static Tag bsImage(String text, String name, String value)
	{
		// Responsive:
		// Images in Bootstrap are made responsive with .img-fluid. This applies
		// max-width: 100%; and height: auto;
		// to the image so that it scales with the parent width.
		// <img src="..." class="img-fluid" alt="...">

		// thumbnail: In addition to our border-radius utilities,
		// you can use .img-thumbnail to give an image a rounded 1px border
		// appearance.
		// <img src="..." class="img-thumbnail" alt="...">
		// allign:
		// Align images with the helper float classes or text alignment classes.
		// block-level images can be centered using the .mx-auto margin utility
		// class.
		// <img src="..." class="rounded float-start" alt="...">
		// <img src="..." class="rounded float-end" alt="...">
		//
		// <img src="..." class="rounded mx-auto d-block" alt="...">
		//
		// <div class="text-center">
		// <img src="..." class="rounded" alt="...">
		// </div>
		//
		//
		/*
		 * Picture: If you are using the <picture> element to specify multiple
		 * <source> elements for a specific <img>, make sure to add the .img-*
		 * classes to the <img> and not to the <picture> tag. <picture> <source
		 * srcset="..." type="image/svg+xml"> <img src="..."
		 * class="img-fluid img-thumbnail" alt="..."> </picture>
		 * 
		 * 
		 * 
		 * 
		 */
		return div();
	}

	private static Tag getRowWithHeader(List<String> row)
	{
		List<String> sublist = row.subList(1, row.size());
		return tr(
		    th(row.get(0)).attr("scope", "row"), each(sublist, item -> td(item))
		);
	}

	private static Tag getRowWithoutHeader(List<String> row)
	{
		return tr(each(row, item -> td(item)));
	}

	/**
	 * Table todo: row classes mit indexangabe, td/th classes mit indexangabe
	 * todo: td colspan="2", table-active todo: zellen einzeln ansprechbar:
	 * vertical align todo: tablehead ansprechbar: .table-light or .table-dark
	 * to make theads appear light or dark gray.todo: table foot hinzufügen
	 * Klassen: <br>
	 * Farben: (table, row, th, td)<br>
	 * table-primary<br>
	 * table-secondary<br>
	 * table-success<br>
	 * table-danger<br>
	 * table-warning<br>
	 * table-info<br>
	 * table-light<br>
	 * table-dark<br>
	 * 
	 * table-striped<br>
	 * table-hover<br>
	 * 
	 * Highlight a table row or cell by adding a table-active class.table-active
	 * on row or cell.<br>
	 * Borders:<br>
	 * table-bordered<br>
	 * border-primary<br>
	 * borderless<br>
	 * Small tables:<br>
	 * table-sm<br>
	 * <br>
	 * Table Head:<br>
	 * table-light<br>
	 * table-dark<br>
	 * Überschrift:<br>
	 * caption-top<br>
	 * Responsive:<br>
	 * table-responsive<br>
	 * maximum breakpoint: table-responsive{-sm|-md|-lg|-xl|-xxl}<br>
	 * overflow-y: hidden<br>
	 * 
	 * @param tHeadItems
	 * @param tbodyItems
	 * @param showRowHeaders
	 * @param tblClasses
	 * @param caption
	 * @return
	 */
	public static Tag bsTable(
	    List<String> tHeadItems,
	    List<List<String>> tbodyItems,
	    boolean showRowHeaders,
	    String tblClasses,
	    String caption
	)
	{
		int currentRow = 0;
		return table(
		    iff(caption != null && !caption.equals(""), caption(caption)),
		    thead(
		        // todo: iff showTHead
		        tr(
		            each(
		                tHeadItems,
		                theadItem -> th(theadItem).attr("scope", "col")
		            )
		        )
		    ),
		    tbody(
		        each(
		            tbodyItems,
		            row -> iffElse(
		                showRowHeaders, getRowWithHeader(row),
		                getRowWithoutHeader(row)
		            )
		        )
		    )
		).attr("class", tblClasses);
	}

	/**
	 * todo: da bs form immer händisch erstellt werden muss, löschen?
	 * 
	 * @return
	 */
	public static Tag bsForm()
	{
		return form(
		    div(
		        LabelBuilder.create()
		            .build("Email address", "exampleInputEmail1"),
		        input().attr("type", "email").attr("class", "form-control")
		            .attr("id", "exampleInputEmail1")
		            .attr("aria-describedby", "emailHelp"),
		        div("We'll never share your email with anyone else.")
		            .attr("id", "emailHelp").attr("class", "form-text")
		    ).attr("class", "mb-3"),
		    div(
		        label("Password").attr("for", "exampleInputPassword1")
		            .attr("class", "form-label"),
		        input().attr("type", "password").attr("class", "form-control")
		            .attr("id", "exampleInputPassword1")
		    ).attr("class", "mb-3"),
		    div(
		        input().attr("type", "checkbox")
		            .attr("class", "form-check-input")
		            .attr("id", "exampleCheck1"),
		        label("Check me out").attr("class", "form-check-label")
		            .attr("for", "exampleCheck1")
		    ).attr("class", "mb-3 form-check"),
		    button("Submit").attr("type", "submit")
		        .attr("class", "btn btn-primary")
		);
	}

	/**
	 * Accordion Array übergeben: headerText, buttonText,
	 * itemText/itemContentTag
	 * 
	 * @param accordionId
	 * @return
	 */
	public static Tag bsAccordion(String accordionId)
	{
		return div(
		    div(
		        h2(
		            button(" Accordion Item #1 ")
		                .attr("class", "accordion-button")
		                .attr("type", "button")
		                .attr("data-bs-toggle", "collapse")
		                .attr("data-bs-target", "#collapseOne")
		                .attr("aria-expanded", "true")
		                .attr("aria-controls", "collapseOne")
		        ).attr("class", "accordion-header").attr("id", "headingOne"),
		        div(div(code(".fff")
				// strong("This is the first item's accordion body."),
				// " It is shown by default, until the collapse plugin
				// adds the appropriate classes that we use to style
				// each element. These classes control the overall
				// appearance, as well as the showing and hiding via CSS
				// transitions. You can modify any of this with custom
				// CSS or overriding our default variables. It's also
				// worth noting that just about any HTML can go within
				// the ",
				//// code(".accordion-body"),
				// ", though the transition does limit overflow. "
		        ).attr("class", "accordion-body")).attr("id", "collapseOne")
		            .attr("class", "accordion-collapse collapse show")
		            .attr("aria-labelledby", "headingOne")
		            .attr("data-bs-parent", "#accordionExample")
		    ).attr("class", "accordion-item"),
		    div(
		        h2(
		            button(" Accordion Item #2 ")
		                .attr("class", "accordion-button collapsed")
		                .attr("type", "button")
		                .attr("data-bs-toggle", "collapse")
		                .attr("data-bs-target", "#collapseTwo")
		                .attr("aria-expanded", "false")
		                .attr("aria-controls", "collapseTwo")
		        ).attr("class", "accordion-header").attr("id", "headingTwo"),
		        div(
		            div(
		                // strong("This is the second item's
		                // accordion body."),
		                // " It is hidden by default, until the
		                // collapse plugin adds the appropriate
		                // classes that we use to style each
		                // element. These classes control the
		                // overall appearance, as well as the
		                // showing and hiding via CSS transitions.
		                // You can modify any of this with custom
		                // CSS or overriding our default variables.
		                // It's also worth noting that just about
		                // any HTML can go within the ",
		                // code(".accordion-body"),
		                // ", though the transition does limit
		                // overflow. "
		            ).attr("class", "accordion-body")
		        ).attr("id", "collapseTwo")
		            .attr("class", "accordion-collapse collapse")
		            .attr("aria-labelledby", "headingTwo")
		            .attr("data-bs-parent", "#accordionExample")
		    ).attr("class", "accordion-item"),
		    div(
		        h2(
		            button(" Accordion Item #3 ")
		                .attr("class", "accordion-button collapsed")
		                .attr("type", "button")
		                .attr("data-bs-toggle", "collapse")
		                .attr("data-bs-target", "#collapseThree")
		                .attr("aria-expanded", "false")
		                .attr("aria-controls", "collapseThree")
		        ).attr("class", "accordion-header").attr("id", "headingThree"),
		        div(
		            div(
		                // strong("This is the third item's
		                // accordion body."),
		                // " It is hidden by default, until the
		                // collapse plugin adds the appropriate
		                // classes that we use to style each
		                // element. These classes control the
		                // overall appearance, as well as the
		                // showing and hiding via CSS transitions.
		                // You can modify any of this with custom
		                // CSS or overriding our default variables.
		                // It's also worth noting that just about
		                // any HTML can go within the ",
		                // code(".accordion-body"),
		                // ", though the transition does limit
		                // overflow. "
		            ).attr("class", "accordion-body")
		        ).attr("id", "collapseThree")
		            .attr("class", "accordion-collapse collapse")
		            .attr("aria-labelledby", "headingThree")
		            .attr("data-bs-parent", "#accordionExample")
		    ).attr("class", "accordion-item")
		).attr("class", "accordion").attr("id", accordionId);

	}

	public static Tag bsTextInputWithLabel(
	    String labelText,
	    String name,
	    String value,
	    String type
	)
	{
		return div(
		    input()
		        // .attr("type", "email")
		        .attr("class", "form-control").attr("id", "floatingInput")
		        .attr("name", name).attr("value", value).attr("type", type),
		    // .attr("placeholder", "name@example.com"),
		    label(labelText).attr("for", "floatingInput")
		).attr("class", "form-floating mb-3");
	}
}
