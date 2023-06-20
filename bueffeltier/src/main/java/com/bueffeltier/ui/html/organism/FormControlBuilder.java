package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.InputTag;
import j2html.tags.specialized.TextareaTag;

public class FormControlBuilder
{
	List<String> cssClasses;

	private SpacingPropertyDV spacingProperty;
	private SpacingSidesDV spacingSides;
	private SpacingSizeDV spacingSize;
	private FormInputTypeDV inputType;
	private String name;
	private String value;
	private String rows;
	private boolean readonly;
	private boolean isDisabled;
	private boolean readonlyAsPlainText;
	private String cssId;
	private boolean isChecked;

	private String oninput;

	private boolean openInternalFC;

	private String text;

	private FormControlBuilder()
	{
		//
	}

	public static FormControlBuilder create()
	{
		return new FormControlBuilder();
	}

	// TODO sveng 22.02.2023: für welche elemente?
	public FormControlBuilder withValue(String value)
	{
		this.value = value;
		return this;
	}

	public FormControlBuilder withName(String name)
	{
		this.name = name;
		return this;
	}

	public FormControlBuilder withText(String text)
	{
		this.text = text;
		return this;
	}

	public FormControlBuilder withOninput(String oninput)
	{
		this.oninput = oninput;
		return this;
	}

	public FormControlBuilder isChecked(boolean isChecked)
	{
		this.isChecked = isChecked;
		return this;
	}

	public FormControlBuilder withRows(int rows)
	{
		this.rows = Integer.toString(rows);
		return this;
	}

	public FormControlBuilder readonly(boolean asPlainText)
	{
		this.readonlyAsPlainText = asPlainText;
		this.readonly = true;
		return this;
	}

	public FormControlBuilder disabled(boolean disabled)
	{
		this.isDisabled = disabled;
		return this;
	}

	public FormControlBuilder withType(FormInputTypeDV formInputType)
	{
		this.inputType = formInputType;
		return this;
	}

	public FormControlBuilder openInternalFC(boolean openInternalFC)
	{
		this.openInternalFC = openInternalFC;
		return this;
	}

	public FormControlBuilder withSpacing(
	    SpacingPropertyDV property,
	    SpacingSidesDV sides,
	    SpacingSizeDV size
	)
	{
		this.spacingProperty = property;
		this.spacingSize = size;
		this.spacingSides = sides;

		return this;
	}

	public FormControlBuilder withSpacingProperty(SpacingPropertyDV property)
	{
		this.spacingProperty = property;

		return this;
	}

	public FormControlBuilder withSpacingSize(SpacingSizeDV size)
	{
		this.spacingSize = size;

		return this;
	}

	public FormControlBuilder withSpacingSides(SpacingSidesDV sides)
	{
		this.spacingSides = sides;

		return this;
	}

	public FormControlBuilder withClass(String cssClass)
	{
		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}

		this.cssClasses.add(cssClass);

		return this;
	}

	public FormControlBuilder withId(String cssId)
	{
		this.cssId = cssId;
		return this;
	}

	public DomContent build()
	{
		if (inputType == null)
		{
			inputType = FormInputTypeDV.TEXT;
		}

		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}

		cssClasses.add(buildFormControlClassString());

		cssClasses.add(buildSpacingClassString());

		if (openInternalFC)
		{
			cssClasses.add("fc-internal");
		}

		return getFormControl();

	}

	private String buildFormControlClassString()
	{
		String result = null;

		if (inputType.equals(FormInputTypeDV.CHECKBOX))
		{
			result = "form-check-input";

		} else if (inputType.equals(FormInputTypeDV.COLOR))
		{
			result = getFormControlBaseClassString() + " form-control-color";
		} else
		{
			result = getFormControlBaseClassString();
		}

		return result;
	}

	private String getFormControlBaseClassString()
	{
		if (readonlyAsPlainText)
		{
			return "form-control-plaintext";

		} else
		{
			return "form-control";
		}
	}

	private DomContent getFormControl()
	{
		DomContent formControl = null;

		if (!inputType.equals(FormInputTypeDV.TEXTAREA))
		{
			formControl = getInputTag();

		} else
		{
			formControl = getTextArea();
		}

		return formControl;
	}

	private InputTag getInputTag()
	{
		InputTag inputTag = null;

		inputTag = input()//
		    .withType(inputType.toString())//
		    .withCondName(name != null, name)//
		    .condAttr(readonly, "readonly", null)//
		    .condAttr(oninput != null, "oninput", oninput)//
		    .withCondValue(value != null, value)//
		    .withClass(buildClassString())//
		    .withCondId(cssId != null, cssId)//
		    .condAttr(
		        isChecked && this.inputType.equals(FormInputTypeDV.CHECKBOX),
		        "checked", ""
		    )//
		    .condAttr(
		        inputType.equals(FormInputTypeDV.FILEMULTIPLE), "multiple", null
		    )//
		    .withCondDisabled(isDisabled)//
		    .attr("aria-describedby", inputType); // todo: aria

		return inputTag;
	}

	private TextareaTag getTextArea()
	{
		TextareaTag textArea = null;

		textArea = textarea()//
		    .withText(text)//
		    .withClass(buildClassString())//
		    .condAttr(readonly, "readonly", null)//
		    .condAttr(oninput != null, "oninput", oninput)//
		    .condAttr(rows != null, "rows", rows)//
//					.withText(readonly, value)// todo: hier text, bei input value!
		    // todo: value bei checkbox true oder false?
		    .withCondDisabled(isDisabled)//
		    .withCondId(cssId != null, cssId)
		    .attr("aria-describedby", inputType); // todo: aria
		;
		return textArea;
	}

	private String buildSpacingClassString()
	{
		StringBuilder builder = new StringBuilder();

		if (spacingProperty == null)
		{
			builder.append("m");

		} else
		{
			builder.append(spacingProperty.toString());
		}

		if (spacingSides == null)
		{
			builder.append("b");

		} else
		{
			builder.append(spacingSides.toString());
		}

		builder.append("-");

		if (spacingSize == null)
		{
			builder.append("3");

		} else
		{
			builder.append(spacingSize.toString());
		}

		return builder.toString();
	}

	private String buildClassString()
	{
		StringBuilder builder = new StringBuilder();

		if (cssClasses != null && cssClasses.size() > 0)
		{
			for (String cssClass : cssClasses)
			{
				builder.append(cssClass);
				builder.append(" ");
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	public enum FormInputTypeDV
	{
		EMAIL("email"), //
		PASSWORD("password"), //
		CHECKBOX("checkbox"), //
		TEXT("text"), //
		TEXTAREA("textarea"), //
		FILE("file"), //
		FILEMULTIPLE("file"), //
		COLOR("color"), //
		DATALIST(null),//
		;

		private final String value;

		FormInputTypeDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}
}
