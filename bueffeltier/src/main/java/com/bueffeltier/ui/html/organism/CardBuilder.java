package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.UlTag;

public class CardBuilder
{
	private List<DomContent> bodyContent;
	private List<String> cssClasses;

	private ImagePositionDV imagePosition;
	private SpacingPropertyDV spacingProperty;
	private SpacingSizeDV spacingSize;
	private SpacingSidesDV spacingSides;

	private HeadlineDV titleHeadlineType;
	private String text;
	private String titleText;
	private String imageSource;
	private String imageAlt;
	private String subtitle;
	private String width;
	private String footerTitle;
	private String loremText = "Lorem ipsum dolor sit amet, consetetur sadipscing"
	    + " elitr, sed diam nonumy eirmod tempor invidunt"
	    + " ut labore et dolore magna aliquyam erat, sed"
	    + " diam voluptua. At vero eos et accusam et"
	    + " justo duo dolores et ea rebum. Stet clita"
	    + " kasd gubergren, no sea takimata sanctus est"
	    + " Lorem ipsum dolor sit amet.";

	private boolean withLorem;
	private boolean withImage;
	private boolean withHeader;

	private CardBuilder()
	{
	}

	public static CardBuilder create()
	{
		return new CardBuilder();
	}

//	public CardBuilder withStretchedLink()
//	{
//		if (cssClasses == null)
//		{
//			cssClasses = new ArrayList<>();
//		}
//		cssClasses.add("stretched-link");
//		return this;
//	}

	public CardBuilder withSpacing(
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

	public CardBuilder withDomContent(DomContent... domContent)
	{
		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}
		bodyContent.addAll(Arrays.asList(domContent));
		return this;
	}

	public CardBuilder addLinkToBodyContent(String link, String text)
	{
		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}
		bodyContent
		    .add(a().withClass("card-link").withHref(link).withText(text));
		return this;
	}

	public CardBuilder withTitle(HeadlineDV titleType, String titleText)
	{
		this.titleHeadlineType = titleType;
		this.titleText = titleText;
		return this;
	}

	public CardBuilder
	    withTitle(HeadlineDV titleType, String titleText, boolean asHeader)
	{
		this.titleHeadlineType = titleType;
		this.titleText = titleText;
		this.withHeader = asHeader;
		return this;
	}

	public CardBuilder withText(String text)
	{
		this.text = text;
		return this;
	}

	public CardBuilder withLorem()
	{
		this.withLorem = true;
		return this;
	}

	// TODO sveng 16.02.2023: sinnvoll?
	public CardBuilder withWidth(String rem)
	{
		this.width = "width: " + rem + "rem;";
		return this;
	}

	public CardBuilder
	    withImage(String src, String alt, ImagePositionDV imagePosition)
	{
		this.imagePosition = imagePosition;
		this.withImage = true;
		this.imageSource = src;
		this.imageAlt = alt;
		return this;
	}

	public CardBuilder withSubtitle(String subtitle)
	{
		this.subtitle = subtitle;
		return this;
	}

	public CardBuilder withHeader(String headerTitle)
	{
		this.titleText = headerTitle;
		this.withHeader = true;
		return this;
	}

	public CardBuilder withFooter(String footerTitle)
	{
		this.footerTitle = footerTitle;
		return this;
	}

	public CardBuilder withList(String[] listItems)
	{
		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}

		UlTag list = ul().withClass("list-group list-group-flush");

//		List<LiTag> liTags = new ArrayList<>();

		for (String item : listItems)
		{
			list.with(li(item).withClass("list-group-item"));
		}

		this.bodyContent.add(list);

		return this;
	}

	public DomContent build()
	{
		if (withLorem)
		{
			this.text = loremText;
		}
		return div(
		    getHeader(), //
		    getTopImage(), //
		    getCardBody(), //
		    getBottomImage(), //
		    getFooter()

		).withClass(buildClassString())//
		    .withStyle("width. 18rem;");
		// todo widht einbeziehen
	}

	private String buildClassString()
	{
		String classString = null;

		if (this.spacingProperty != null)
		{
			classString = "card " + buildSpacingClassString();

		} else
		{
			classString = "card";
		}

		return classString;
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

	private DomContent getHeader()
	{
		DomContent header = null;

		if (this.titleText != null && withHeader && subtitle == null)
		{
			header = getTitleAsHeader();
		}

		if (this.titleText != null && withHeader && subtitle != null)
		{
			header = div(getTitleAsHeader(), getSubTitleAsHeader());
		}

//		// Wenn ein regulärer Header gesetzt wurde:
//		if (headerTitle != null && titleIsHeader == false && subtitle == null)
//		{
//			header = div(headerTitle).withClass("card-header");
//		}
//
//		if (titleHeadlineType != null && titleIsHeader && subtitle != null)
//		{
//			header = div(getTitleAsHeader(), getSubTitleAsHeader());
//		}

		return header;
	}

	private DomContent getFooter()
	{
		DomContent footer = null;

		if (footerTitle != null)
		{
			footer = div(footerTitle).withClass("card-footer");
		}

		return footer;
	}

	private DomContent getTopImage()
	{
		DomContent topImage = null;

		if (imagePosition != null)
		{
			if (imagePosition.equals(ImagePositionDV.TOP))
			{
				topImage = getImage();
			}
		}

		return topImage;
	}

	private DomContent getBottomImage()
	{
		DomContent bottomImage = null;

		if (imagePosition != null)
		{
			if (this.imagePosition.equals(ImagePositionDV.BOTTOM))
			{
				bottomImage = getImage();
			}
		}

		return bottomImage;
	}

	private DomContent getCardBody()
	{
		DomContent cardBody = null;

		if (bodyContent == null)
		{
			bodyContent = new ArrayList<>();
		}
//todo: layout BasicHorizontalLayoutManager oder vertikal einbauen
		cardBody = div(
		    getTitle(), //
		    getSubtitle(), //
		    getMainText(), //
		    each(bodyContent, content -> content) //
		).withClass("card-body");

		return cardBody;
	}

	private DomContent getMainText()
	{
		DomContent mainText = null;

		if (StringUtils.isNotBlank(text))
		{
			mainText = p(text).withClass("card-text");
		}

		return mainText;
	}

	private DomContent getSubtitle()
	{
		DomContent subtitleTag = null;

		if (this.subtitle != null && this.titleHeadlineType != null)
		{
			subtitleTag = getSubtitleTag();
		}

		return subtitleTag;
	}

	private DomContent getImage()
	{
		DomContent image = null;

		if (this.withImage)
		{
			image = img().withClass("card-img-top").withAlt(imageAlt)
			    .withSrc(imageSource);
		}
		return image;
	}

	private DomContent getTitle()
	{
		DomContent headline = null;

		if (titleHeadlineType != null && withHeader == false)
		{
			if (StringUtils.isBlank(titleText))
			{
				titleText = "Headline";
			}

			switch (this.titleHeadlineType) {

			case H1:
				headline = h1(titleText).withClass("card-title");
				break;
			case H2:
				headline = h2(titleText).withClass("card-title");
				;
				break;
			case H3:
				headline = h3(titleText).withClass("card-title");
				;
				break;
			case H4:
				headline = h4(titleText).withClass("card-title");
				;
				break;
			case H5:
				headline = h5(titleText).withClass("card-title");
				;
				break;
			case H6:
				headline = h6(titleText).withClass("card-title");
				;
				break;
			}
		}
		return headline;
	}

	private DomContent getTitleAsHeader()
	{
		DomContent headline = null;

		if (titleHeadlineType != null)
		{
			if (StringUtils.isBlank(titleText))
			{
				titleText = "Headline";
			}

			switch (this.titleHeadlineType) {

			case H1:
				headline = h1(titleText).withClass("card-title")
				    .withCondClass(withHeader, "card-header");
				break;
			case H2:
				headline = h2(titleText).withClass("card-title")
				    .withCondClass(withHeader, "card-header");
				;
				break;
			case H3:
				headline = h3(titleText).withClass("card-title")
				    .withCondClass(withHeader, "card-header");
				;
				break;
			case H4:
				headline = h4(titleText).withClass("card-title")
				    .withCondClass(withHeader, "card-header");
				;
				break;
			case H5:
				headline = h5(titleText).withClass("card-title")
				    .withCondClass(withHeader, "card-header");
				;
				break;
			case H6:
				headline = h6(titleText).withClass("card-title");
				;
				break;
			}
		}
		return headline;
	}

	private DomContent getSubtitleTag()
	{
		DomContent subtitleTag = null;

		if (titleHeadlineType != null && withHeader == false)
		{
			switch (this.titleHeadlineType) {

			case H1:
				subtitleTag = h2(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				break;
			case H2:
				subtitleTag = h3(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H3:
				subtitleTag = h4(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H4:
				subtitleTag = h5(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H5:
				subtitleTag = h6(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H6:
				subtitleTag = h6(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			}
		}

		return subtitleTag;
	}

	private DomContent getSubTitleAsHeader()
	{
		DomContent subtitleTag = null;

		if (titleHeadlineType != null && withHeader == false)
		{
			switch (this.titleHeadlineType) {

			case H1:
				subtitleTag = h2(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				break;
			case H2:
				subtitleTag = h3(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H3:
				subtitleTag = h4(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H4:
				subtitleTag = h5(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H5:
				subtitleTag = h6(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			case H6:
				subtitleTag = h6(subtitle)
				    .withClass("card-subtitle mb-2 text-muted");
				;
				break;
			}
		}

		return subtitleTag;
	}

//	private String buildClasses()
//	{
//		StringBuilder classBuilder = new StringBuilder();
//
//		classBuilder.append("btn");
//
//		// TODO sveng 14.02.2023: common builder (einer für alle oder
//		// commonPreBuilder)
//		if (color != null)
//		{
//			classBuilder.append(" btn-");
//			if (asOutline)
//			{
//				classBuilder.append("outline-");
//			}
//			classBuilder.append(color.toString());
//		}
//
//		for (String classString : classes)
//		{
//			classBuilder.append(" ");
//			classBuilder.append(classString.toString());
//		}
//		return classBuilder.toString();
//	}

	public enum HeadlineDV
	{
		H1, H2, H3, H4, H5, H6
	}

	public enum ImagePositionDV
	{
		TOP, BOTTOM, LEFT, RIGHT
	}
}
