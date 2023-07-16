package com.bueffeltier.ui.html.organism;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.IconBuilder.IconTypeDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.ATag;
import j2html.tags.specialized.ButtonTag;
import j2html.tags.specialized.ITag;
import j2html.tags.specialized.InputTag;
import j2html.tags.specialized.SpanTag;

public final class ButtonBuilder
{
	private ButtonTagTypeDV tagType;
	private ButtonTypeDV inputType;

	private ColorDV color;

	private List<String> classes = new ArrayList<>();
	private List<String> ids;

	private String href;
	private String value;
	private String text;
	private String secondaryText;
	private String name;
	private String jsFunction;

	private boolean asOutline;
	private boolean disabled;
	private boolean asToggle;
	private boolean isToggleSelected;
	private boolean isChecked;
	private boolean autofocus;
	private boolean selected;
	private boolean onclick;
	private SpacingPropertyDV spacingProperty;
	private SpacingSizeDV spacingSize;
	private SpacingSidesDV spacingSides;
	private String imageSource;
	private String imageWidth;
	private IconTypeDV icon;
	private int iconFontSizeOpt;
	private String style;
	private String formAction;

	private ButtonBuilder()
	{
		//
	}

	public static ButtonBuilder create()
	{
		return new ButtonBuilder();
	}

	public ButtonBuilder isToggled(boolean selected)
	{
		this.asToggle = true;

		if (selected)
		{
			classes.add("active");
		}

		return this;
	}

	public ButtonBuilder withColor(ColorDV color)
	{
		this.color = color;
		return this;
	}

	public ButtonBuilder withSpacing(
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

	public ButtonBuilder withSpacingProperty(SpacingPropertyDV property)
	{
		this.spacingProperty = property;

		return this;
	}

	public ButtonBuilder withSpacingSize(SpacingSizeDV size)
	{
		this.spacingSize = size;

		return this;
	}

	public ButtonBuilder withSpacingSides(SpacingSidesDV sides)
	{
		this.spacingSides = sides;

		return this;
	}

	public ButtonBuilder withHref(String href)
	{
		this.tagType = ButtonTagTypeDV.A;
		this.href = href;
		return this;
	}

	public ButtonBuilder asOutline()
	{
		this.asOutline = true;
		return this;
	}

	public ButtonBuilder withSize(ButtonSizeDV size)
	{
		classes.add(size.toString());
		return this;
	}

//	public ButtonBuilder3 asBlockType(BlockButtonDV blockType)
//	{
//		classes.add("d-grid gap-2");
//		return this;
//	}

	public ButtonBuilder disabled()
	{
		this.disabled = true;
		return this;
	}

	public ButtonBuilder noWrap(boolean wrapText)
	{
		classes.add("text-nowrap");
		return this;
	}

	public ButtonBuilder withSecondaryText(String secondaryText)
	{
		this.secondaryText = secondaryText;
		return this;
	}

	public ButtonBuilder withName(String name)
	{
		this.name = name;
		return this;
	}

	public ButtonBuilder withValue(String value)
	{
		this.value = value;
		return this;
	}

	public ButtonBuilder withClass(String className)
	{
		// TODO sveng 14.02.2023: für welche TagTypen?
		this.classes.add(className);
		return this;
	}

	public ButtonBuilder withCondClass(boolean cond, String className)
	{
		// TODO sveng 14.02.2023: für welche TagTypen?
		if (cond)
		{
			this.classes.add(className);
		}
		return this;
	}

	public ButtonBuilder withId(String id)
	{
		if (ids == null)
		{
			ids = new ArrayList<>();
		}
		this.ids.add(id);
		return this;
	}

	public ButtonBuilder isChecked(Boolean isChecked)
	{
//		buttonTag.withText(text);
		return this;
	}

	public ButtonBuilder autofocus(boolean autofocus)
	{
//		buttonTag.withText(text);
		return this;
	}

	public ButtonBuilder isSelected(boolean isSelected)
	{
//		buttonTag.withText(text);
		return this;
	}

	/**
	 * Nicht mehr verwenden, um JS unabhängig vom HTML zu machen.
	 * 
	 * @param jsFunction
	 * @return
	 */
	@Deprecated
	public ButtonBuilder withOncLick(String jsFunction)
	{
		this.jsFunction = jsFunction;
		return this;
	}

	/**
	 * "submit" - submits a form to the server <br>
	 * "reset" - reset a form to its initial values <br>
	 * "button" - define custom behavior with JavaScript <br>
	 * 
	 * @param inputType
	 * @return
	 */
	public ButtonBuilder withButtonType(ButtonTypeDV inputType)
	{
		this.inputType = inputType;
		return this;
	}

	public ButtonBuilder withTagType(ButtonTagTypeDV type)
	{
		this.tagType = type;
		return this;
	}

	public ButtonBuilder withText(String text)
	{
		this.text = text;
		return this;
	}

	public ButtonBuilder withImage(String source, String width, String textOpt)
	{
		this.imageSource = source;
		this.imageWidth = width;

		if (StringUtils.isNotBlank(textOpt))
		{
			this.text = textOpt;
		}

		return this;
	}

	public ButtonBuilder withStyle(String style)
	{
		this.style = style;
		return this;
	}

	public ButtonBuilder withIcon(IconTypeDV icon, int fontSizeOpt)
	{
		this.iconFontSizeOpt = fontSizeOpt;
		this.icon = icon;
		return this;
	}

	@Deprecated
	public ButtonBuilder withFormAction(String formAction)
	{
		this.formAction = formAction;
		return this;
	}

	public DomContent build()
	{
		if (tagType == null)
		{
			tagType = ButtonTagTypeDV.BUTTON;
		}

		DomContent tag;

		if (tagType.equals(ButtonTagTypeDV.A))
		{
			tag = buildATag();

		} else if (tagType.equals(ButtonTagTypeDV.INPUT))
		{
			tag = buildInputTag();

		} else
		{
			tag = buildButtonTag();
		}

		return tag;

//		return tag.withClass(getClassString());
	}

	private ButtonTag buildButtonTag()
	{
		ButtonTag buttonTag = new ButtonTag();

		if (inputType == null)
		{
			inputType = ButtonTypeDV.BUTTON;
		}

		DomContent imageTag = null;

		if (imageSource != null && imageWidth != null)
		{
			if (StringUtils.isNotBlank(text))
			{
				imageTag = ImageBuilder.create()//
				    .withSource(imageSource)//
				    .withWidth(imageWidth).withText(text)//
				    .build();
			} else
			{
				imageTag = ImageBuilder.create()//
				    .withSource(imageSource)//
				    .withWidth(imageWidth)//
				    .build();
			}
		}

		if (imageTag != null)
		{
			buttonTag.with(imageTag);

			buttonTag.withStyle("display: flex; align-items: center;");

		}

		if (icon != null)
		{
			ITag iconTag = IconBuilder.create()//
			    .withIcon(icon)//
			    .withFontSize(iconFontSizeOpt)//
			    .build();

			SpanTag spanTag;

			if (text != null)
			{
				spanTag = new SpanTag();

				spanTag.withText(text);

				spanTag.withStyle("margin-left: 10px;");

				buttonTag.with(iconTag, spanTag);

			} else
			{
				buttonTag.with(iconTag);
			}

			buttonTag.withStyle("display: flex; align-items: center;");
		}

		if (imageSource == null && icon == null)
		{
			buttonTag.withText(text);
		}

		buttonTag//
		    .withCondName(name != null, name)//
		    .withClasses(buildClasses())//
		    .withCondId(ids != null, buildIds())
		    .condAttr(disabled, "aria-disabled", "true")
		    .condAttr(asToggle, "data-bs-toggle", "button")
		    .condAttr(isToggleSelected, "aria-pressed", "true") //
		    .condAttr(
		        inputType != null && inputType.equals(ButtonTypeDV.SUBMIT),
		        "type", "submit"
		    ) //
		    .condAttr(
		        inputType != null && inputType.equals(ButtonTypeDV.RESET),
		        "type", "reset"
		    ) //
		    .condAttr(
		        inputType == null || inputType.equals(ButtonTypeDV.BUTTON),
		        "type", "button"
		    ) //
		    .condAttr(value != null, "value", value)
		    .condAttr(jsFunction != null, "onclick", jsFunction)
		    .condAttr(formAction != null, "formaction", formAction);

		return buttonTag;
	}

	private ATag buildATag()
	{
		return new ATag() //
		    .withText(text) //
		    .withClasses(buildClasses())//
		    .withCondId(ids != null, buildIds())
		    .condAttr(disabled, "aria-disabled", "true")
		    .condAttr(asToggle, "data-bs-toggle", "button")
		    .condAttr(isToggleSelected, "aria-pressed", "true")
		    .attr("role", "button")
		    .condAttr(href != null && !disabled, "href", href);
	}

	private InputTag buildInputTag()
	{
		return new InputTag() //
		    .withValue(text) //
		    .withClasses(buildClasses())//
		    .withCondId(ids != null, buildIds())
		    .condAttr(disabled, "aria-disabled", "true")
		    .condAttr(asToggle, "data-bs-toggle", "button")
		    .condAttr(isToggleSelected, "aria-pressed", "true")
		    .condAttr(
		        inputType != null && inputType.equals(ButtonTypeDV.SUBMIT),
		        "type", "submit"
		    ) //
		    .condAttr(
		        inputType != null && inputType.equals(ButtonTypeDV.RESET),
		        "type", "reset"
		    ) //
		    .condAttr(
		        inputType == null || inputType.equals(ButtonTypeDV.BUTTON),
		        "type", "button"
		    ) //
		    .condAttr(value != null, "value", value);
	}

	private String buildClasses()
	{
		StringBuilder classBuilder = new StringBuilder();

		classBuilder.append("btn");

		// TODO sveng 14.02.2023: common builder (einer für alle oder
		// commonPreBuilder)
		if (color != null)
		{
			classBuilder.append(" btn-");

			if (asOutline)
			{
				classBuilder.append("outline-");
			}

			classBuilder.append(color.toString());

		} else
		{
			classBuilder.append(" btn-");

			if (asOutline)
			{
				classBuilder.append("outline-");
			}

			classBuilder.append(ColorDV.PRIMARY.toString());
		}

		if (StringUtils.isNotBlank(buildSpacingClassString()))
		{
			classBuilder.append(" ");

			classBuilder.append(buildSpacingClassString());
		}

		for (String classString : classes)
		{
			classBuilder.append(" ");
			classBuilder.append(classString.toString());
		}
		return classBuilder.toString();
	}

	private String buildIds()
	{
		StringBuilder idBuilder = new StringBuilder();

		if (ids != null && ids.size() > 0)
		{
			for (String idString : ids)
			{
				idBuilder.append(idString.toString());
				idBuilder.append(" ");
			}

			idBuilder.deleteCharAt(idBuilder.length() - 1);
		}

		return idBuilder.toString();
	}

	// TODO sveng 03.03.2023: methode aus anderen klassen vereinen.
	private String buildSpacingClassString()
	{
		String spacingClassString = null;

		if (spacingProperty != null)
		{
			StringBuilder builder = new StringBuilder();

			// if (spacingProperty == null)
//			{
//				builder.append("m");
			//
//			} else
//			{
			builder.append(spacingProperty.toString());
//			}

//			if (spacingSides == null)
//			{
//				builder.append("b");

//			} else
//			{
			builder.append(spacingSides.toString());
//			}

			builder.append("-");

//			if (spacingSize == null)
//			{
//				builder.append("3");

//			} else
//			{
			builder.append(spacingSize.toString());
//			}	

			spacingClassString = builder.toString();
		}

		return spacingClassString;
	}

	public enum ColorDV
	{
		PRIMARY("primary"), //
		SECONDARY("secondary"), //
		SUCCESS("success"), //
		DANGER("danger"), //
		WARNING("warning"), //
		INFO("info"), //
		LIGHT("light"), //
		DARK("dark"), //
		LINK("link");

		private final String color;

		ColorDV(String color)
		{
			this.color = color;
		}

		@Override
		public String toString()
		{
			return color;
		}
	}

	public enum ButtonSizeDV
	{
		LARGE("btn-lg"), SMALL("btn-sm");

		private final String value;

		ButtonSizeDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}

	public enum BlockButtonDV
	{
		HORIZONTAL, VERTICAL_LEFT, VERTICAL_RIGHT, CENTER_4, CENTER_6
	}

	public enum ButtonTypeDV
	{
		SUBMIT, RESET, BUTTON
	}

	public enum ButtonTagTypeDV
	{
		BUTTON, INPUT, A
	}
}
