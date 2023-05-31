package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import j2html.tags.DomContent;
import j2html.tags.specialized.ButtonTag;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.ScriptTag;

public class ModalBuilder
{
	private List<DomContent> buttons;
	private List<DomContent> bodyContent;
	private List<String> openFunctionParameterValues = new ArrayList<>();

	private String title;
	private String message;
	private FullscreenDV fullscreenType;
	private SizeOptDV size;
	private boolean noFade;

	private String abortFunctionName;
	private String approveFunctionName;

	private String abortFunctionScript;
	private String approveFunctionScript;

	private boolean hasAbortButton;
	private boolean hasApproveButton;
	private String approveButtonText;
	private String openFunctionName;
	private String mainScript;
	private boolean withFunctionIdParameter;
	private String openFunctionParameterName;

	/*
	 * TODO: modal hauptfunktion, openModal kann auch im hauptscript festgelegt
	 * werden.
	 */
	private ModalBuilder()
	{
	}

	public static ModalBuilder create()
	{
		return new ModalBuilder();
	}

	public ModalBuilder showFullscreen(FullscreenDV fullscreenType)
	{
		this.fullscreenType = fullscreenType;
		return this;
	}

	public ModalBuilder withSizeOpt(SizeOptDV size)
	{
		this.size = size;
		return this;
	}

	public ModalBuilder noFade()
	{
		this.noFade = true;
		return this;
	}

	public ModalBuilder withAbortButton()
	{
		this.hasAbortButton = true;
		return this;
	}

	public ModalBuilder withApproveButton(String btnText)
	{
		this.hasApproveButton = true;
		this.approveButtonText = btnText;
		return this;
	}

	public ModalBuilder withMessage(String message)
	{
		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}
		bodyContent.add(0, p(message));
		return this;
	}

	public ModalBuilder withAbortFunctionName(String functionName)
	{
		this.abortFunctionName = functionName;
		return this;
	}

	public ModalBuilder withAbortFunctionScript(String code)
	{
		this.abortFunctionScript = code;
		return this;
	}

//	public ModalBuilder withMainScript(String code)
//	{
//		this.mainScript = code;
//		return this;
//	}

	public ModalBuilder withApproveFunctionName(String functionName)
	{
		this.approveFunctionName = functionName;
		return this;
	}

	public ModalBuilder withApproveFunctionScript(String code)
	{
		this.approveFunctionScript = code;
		return this;
	}

	public ModalBuilder withOpenFunctionParameter(String parameterName)
	{
		this.withFunctionIdParameter = true;
		this.openFunctionParameterName = parameterName;
		return this;
	}

	public ModalBuilder withDomContent(DomContent... domContent)
	{
		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}

		for (DomContent dc : domContent)
		{
			bodyContent.add(dc);
		}

		return this;
	}

	/**
	 * TODO: call myModal.handleUpdate() to update height of dialog box.
	 * 
	 * @param title
	 * @param message
	 * @param id
	 * @param ariaLabelledById
	 * @param ariaDescribedByOpt Beschreibender Text für Screenreader.
	 * @return
	 */
	public DomContent build(
	    String title,
	    String id,
	    String ariaLabelledById,
	    String ariaDescribedByTextOpt,
	    String openFunctionName
		// todo openFunctionParameterName
	)
	{
		this.openFunctionName = openFunctionName;
//		if (dataAttrs.length > 0)
//		{
//			for (int i = 0; i < dataAttrs.length; i++)
//			{
//				openFunctionParameterValues.add(dataAttrs[i]);
//			}
//		}

		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}

		DivTag divTag = div(
		    //
		    div(
		        //
		        div(
		            div(
		                //
		                h5(title).withClass("modal-title")
		                    .withId(ariaLabelledById), // todo: methode mit
		                // null
		                button()//
		                    .withType("button")//
		                    .condAttr(
		                        this.abortFunctionName != null, "onclick",
		                        abortFunctionName
		                    )//
		                    .withClass("btn-close")
		                    .attr("data-bs-dismiss", "modal")
		                    .attr("aria-label", "Close")
		            ).withClass("modal-header"), //
		            div(
		                //
		                each(bodyContent, bc -> bc)
		            ).withClass("modal-body"), //
		            div(
		                //
//		                each(buttons, button -> button)
		                getAbortButtonOpt(), //
		                getApproveButtonOpt() //
//		                ButtonBuilder.create().withText("Speichern")//
//		                    .build()
		            ).withClass("modal-footer")
					//
		        ).withClass("modal-content"), //
		        //
		        getAbortScriptOpt(), //
		        getApproveScriptOpt(), //
		        getMainScriptOpt()
			//
		    ).withClass(buildModalDialogClassString())//
		    , getModalOpenScript(id)
		)//
		    .withClass(buildModalClassString())//
		    .withId(id)//
		    .attr("tabindex", "-1")//
		    .attr("aria-labelledby", ariaLabelledById)//
		    .condAttr(
		        ariaDescribedByTextOpt != null, "aria-describedby",
		        ariaDescribedByTextOpt
		    )//
		    .attr("aria-hidden", "true");

//		for (int i = 0; i < openFunctionParameterValues.size(); i++)
//		{
//			divTag.withData("param-" + i, openFunctionParameterValues.get(i));
//		}

		return divTag;
	}

	private ScriptTag getModalOpenScript(String id)
	{
		if (withFunctionIdParameter)
		{
			return

			script(
			    //
			    "var " + this.openFunctionParameterName + ";\n" + "\n"
			        + "function "
			        + addParameterNameToOpenFunction(
			            openFunctionName, openFunctionParameterName
			        )
					// dann
					// definieren
			        + "{\n"//
			        + "  const modal = document.getElementById('" + id + "');\n"
			        + "  const modalInstance = new bootstrap.Modal(modal);\n"
			        + "  " + this.openFunctionParameterName + " = "
			        + this.openFunctionParameterName + "Param;\n"
			        + "  modalInstance.show();\n"//
			        + "}"
			);
		} else
		{
			return

			script(
			    //
			    "function " + this.openFunctionName + "{\n"//
			        + "  const modal = document.getElementById('" + id + "');\n"
			        + "  const modalInstance = new bootstrap.Modal(modal);\n"
			        + "  modalInstance.show();\n"//
			        + "}"
			);
		}

	}

	private String addParameterNameToOpenFunction(
	    String originalString,
	    String parameterName
	)
	{
//		originalString.substring(0, originalString.indexOf('('));

		return originalString.substring(0, originalString.indexOf('(')) + "("
		    + parameterName + "Param)";

//		int indexOfClosingBracket = originalString.indexOf(')');
//
//		if (indexOfClosingBracket == -1)
//		{
//			if (originalString.endsWith("("))
//			{
//				originalString = originalString
//				    .substring(0, originalString.length() - 1);
//			}
//
//			originalString += "(" + "'" + parameterName + "'" + ")";
//
//			return originalString;
//
//		} else
//		{
//			// Insert parameter string in single quotes before closing bracket
//			String modifiedString = originalString
//			    .substring(0, indexOfClosingBracket) + "('" + parameterName
//			    + "')" + originalString.substring(indexOfClosingBracket);
//			return modifiedString;
//		}
	}

	private ButtonTag getAbortButtonOpt()
	{
		ButtonTag abortBtn = null;

		if (hasAbortButton)
		{
			abortBtn = (ButtonTag) ButtonBuilder.create()//
			    .withOncLick(this.abortFunctionName)//
			    .withText("Abbrechen")//
			    .build();
			abortBtn.attr("data-bs-dismiss", "modal");
			abortBtn.attr("aria-label", "Close");
		}

		return abortBtn;
	}

	private ButtonTag getApproveButtonOpt()
	{
		ButtonTag approveBtn = null;

		if (hasApproveButton)
		{
			approveBtn = (ButtonTag) ButtonBuilder.create()//
			    .withOncLick(this.approveFunctionName)//
			    .withText(approveButtonText)//
			    .build();
			approveBtn.attr("data-bs-dismiss", "modal");
			approveBtn.attr("aria-label", "Approve");
		}

		return approveBtn;
	}

	private ScriptTag getAbortScriptOpt()
	{
		ScriptTag script = null;

		if (this.abortFunctionScript != null)
		{
			script = script(abortFunctionScript);
		}

		return script;
	}

	private ScriptTag getApproveScriptOpt()
	{
		ScriptTag script = null;

		if (this.approveFunctionScript != null)
		{
			script = script(approveFunctionScript);
		}

		return script;
	}

	private ScriptTag getMainScriptOpt()
	{
//		ScriptTag script = null;
//
//		if (this.approveFunctionScript != null)
//		{
//			script = script(approveFunctionScript);
//		}
//
//		return script;
		return null;
	}

	private String buildModalClassString()
	{
		String result = null;

		if (noFade)
		{
			result = "modal";
		} else
		{
			result = "modal fade";
		}

		return result;
	}

	private String buildModalDialogClassString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("modal-dialog");

		if (this.size != null)
		{
			builder.append(" ");

			builder.append(size.toString());
		}

		if (this.fullscreenType != null)
		{
			builder.append(" ");

			builder.append(fullscreenType.toString());
		}

		return builder.toString();
	}

	public enum FullscreenDV
	{
		ALWAYS("modal-fullscreen"), //
		SM_DOWN("modal-fullscreen-sm-down"), //
		MD_DOWN("modal-fullscreen-md-down"), //
		LG_DOWN("modal-fullscreen-lg-down"), //
		XL_DOWN("modal-fullscreen-xl-down"), //
		XXL_DOWN("modal-fullscreen-xxl-down");//

		private String value;

		FullscreenDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return this.value;
		}
	}

	public enum SizeOptDV
	{
		SM("modal-sm"), //
		LG("modal-lg"), //
		XL("modal-xl");//

		private String value;

		SizeOptDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return this.value;
		}
	}
}
