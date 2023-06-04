//package com.bueffeltier.ui.html.organism;
//
//import static j2html.TagCreator.div;
//
//import j2html.tags.DomContent;
//import j2html.tags.specialized.DivTag;
//
//public class FormInputBuilder // TODO sveng 21.02.2023: InputGroupBuilder?
//{
//
//	public FormInputBuilder withHelpText(String helpText)
//	{
//		this.helpText = helpText;
//		return this;
//	}
//
//	public DomContent build(String id)
//	{
//		this.inputLabelId = id;
//		// TODO sveng 25.02.2023: Precondition prüfen:
//		// inputType != null
//
//		return div(
//		    getInput(), //
//		    // <- Col
//		    getTextArea(), //
//		    getInputHelpLineOpt()//
//
//			//
//		)//
////				.withClass("my-3")//
//		    .withClass(buildROWColumnClassString())// todo: class nur wenn ?
////				.withCondClass(
////						gridWidth != null, // todo
////											// methd.
////						spacingSides + "-" + spacingSize
////				)//
//		;
//	}
//
//	private DivTag getInputHelpLineOpt()
//	{
//		DivTag divTag = null;
//
//		if (helpText != null)
//		{
//			divTag = div(helpText).withClass("form-text"); // todo: setter?
//		}
//
//		return divTag;
//	}
//}
