package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.i;

import j2html.tags.specialized.ITag;

public class IconBuilder
{
	private IconTypeDV icon;
	private int fontSize;

	private IconBuilder()
	{
	}

	public static IconBuilder create()
	{
		return new IconBuilder();
	}

	public IconBuilder withIcon(IconTypeDV icon)
	{
		this.icon = icon;
		return this;
	}

	public IconBuilder withFontSize(int fontSize)
	{
		this.fontSize = fontSize;
		return this;
	}

	public ITag build()
	{
		return i(

		)//
		    .withClass(icon.toString())//
		    .withCondStyle(fontSize > 0, "font-size: " + fontSize + "px;")//
		;
	}

	public enum IconTypeDV
	{
		OPEN_FOLDER("fas fa-folder-open"), //
		FOLDER("far fa-folder"), //
		FILE("far fa-file"), //
		FILE_CHECK("fas fa-file-circle-check"), //
		EDIT("fas fa-pencil-alt"), //
		DELETE("fas fa-trash"), //
		HOME("fas fa-home"), //
		LEVEL_UP("fas fa-level-up-alt"),//
		//
		;

		private final String value;

		IconTypeDV(String value)
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
