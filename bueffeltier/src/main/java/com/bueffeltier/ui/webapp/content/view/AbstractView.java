package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.input;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.webapp.ViewDataService;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;

/**
 * todo: interne SiteStatistics erfassen die am häufigsten verwendeten elemente
 * und halten sie im speicher bereit.
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
abstract class AbstractView implements ViewHandler
{
	private ViewDataService viewDataService = ViewDataService.getInstance();

	// todo: ggf. container-tag und css-id als feld einfügen, mit standard-wert.
	public AbstractView()
	{

	}

	public String getViewDataValueOpt(HttpServletRequest request, String key)
	{
		return viewDataService.getValueOpt(request, key);
	}

//	public String
//	    getRequestParameter(HttpServletRequest request, String parameterName)
//	{
//		return request.getParameter(parameterName);
//	}

//	public Enumeration<String>
//	    getRequestParameterNames(HttpServletRequest request)
//	{
//		return request.getParameterNames();
//	}

	public int getRequestPermission(HttpServletRequest request)
	{
		return (int) request.getAttribute("permission");
	}

	@Override
	public long getContentId(ElementJDBCFlat element)
	{
		return element.getId();
	}

	@Override
	public abstract DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception;

	/**
	 * Wird von der App aufgerufen und ruft die writeHtml-Methode des jeweiligen
	 * View handlers auf. todo: Hier CSS-ID-Opt und Container-Tag-Opt abragen?
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public DomContent
	    writeInternalHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	// todo: throw exception!
	{
		// todo: verschiedene container-tags anbieten: form, div, ...
		// todo: auch in artikeln verscheidene container-tags anbieten.
		// todo: klassen und ids setzen.
		// todo: klassen und ids aus den content elementen noch einmal eine
		// content-typ- und -implementations-id hinzufügen?
		// case "div":
		// return div(getForm()).render();
		// Standardcontainer = "form" ?
		// default:
		// return article(getForm()).render();
		// return writeHtml().withClass(this.containerTagClassName);

		DomContent htmlContent = writeHtml(element, request);

		return htmlContent;
	}

	/**
	 * CSS-Id für dieses Content-Element setzen. todo: wie verwenden?
	 * 
	 * @param containerTagClassName
	 */
	protected abstract void setCssId(String containerTagClassName);

	/**
	 * Umschließenden Container-Tag für dieses COntent-Element setzen. todo: wie
	 * verwenden?
	 */
	protected abstract void setContainerTag();

	/**
	 * element.getType()
	 * 
	 * @param actionName
	 * @param domContent
	 * @return
	 */
	public static ContainerTag<?>
	    form(String actionName, DomContent... domContent)
	{
		// useStandardForm = false;
		// todo: der übergebene tag bleibt leer
		// TODO sveng 21.02.2023: für alle dom contents muss eine methode da
		// sein,
		// mit der man margin setzen kann (margin-bottom z.B.).
		// TODO sveng 21.02.2023: margin analog für fieldsets.
		return new ContainerTag<>("form")//
		    .attr("method=\"post\"")//
		    .attr("autocomplete", "on")//
		    .attr("action", "Servlet").with(
		        input()//
		            .withType("hidden")//
		            .withName("action")//
		            .withValue(actionName)//
		    )//
		    .with(domContent);//
	}

	@Override
	public abstract DomContent writeHtmlControls();

	@Override
	public DomContent writeInternalHtmlControlls()
	{
		return writeHtmlControls();
	}

	@Override
	public abstract String writeCss();

}
