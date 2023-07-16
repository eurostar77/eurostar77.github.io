package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.crosscutting.AppPropertyService.PropertySectionDV;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTagTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTypeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ColorDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.FormControlBuilder.FormInputTypeDV;
import com.bueffeltier.ui.html.organism.LabelBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;

public class AppSettingsView extends AbstractView
{
	private static AppSettingsView instance;

	private static AppPropertyService service = AppPropertyService
	    .getInstance();

	private AppSettingsView()
	{
		super();
	}

	public static AppSettingsView getInstance()
	{
		if (instance == null)
		{
			instance = new AppSettingsView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
//		Set<Entry<Object, Object>> entries = appPropertyService.getAll();

		String currentSection = getViewDataValueOpt(
		    request, "settingsViewShowSection"
		);

		if (StringUtils.isBlank(currentSection))
		{
			currentSection = "servlet";
		}

		return div(
		    RowBuilder.create()//
		        .withDomContent(
		            getLeftColumn(element, currentSection), //
		            getRightColumn(element, currentSection)
		        )//
		        .build()
			//

		)//
//		    .withStyle(
//		        //
//		        """
//		            @media (max-width: 767px) {
//		              .sidebar {
//		                overflow-y: auto;
//		                white-space: nowrap;
//		              }
//		              .sidebar .btn-group {
//		                display: inline-block;
//		              }
//		            }
//
//		            @media (min-width: 768px) {
//		              .btn-group-vertical>.btn {
//		                display: block;
//		                width: 100%;
//		              }
//		            }
//		                """
//		    )//
		    .withClass("container-fluid");
	}

	private DomContent
	    getLeftColumn(ElementJDBCFlat element, String currentSection)
	{
		return div(
		    getButtonListGroup(element, currentSection)//
		).withClass("col-lg-3 col-md-4 d-flex flex-column bg-light sidebar");//
	}

	private DomContent
	    getButtonListGroup(ElementJDBCFlat element, String currentSection)
	{
		List<String> sections = PropertySectionDV.getAllValues();

		DivTag listGroup = new DivTag();
		listGroup.withClass("list-group list-group-vertical")//
		    .attr("role", "group");

		for (int i = 0; i < sections.size(); i++)
		{
			listGroup.with(
			    ButtonBuilder.create()//
			        .withButtonType(ButtonTypeDV.SUBMIT)//
			        .withName(sections.get(i))//
			        .withValue(sections.get(i))//
			        .withText(sections.get(i))//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BLANK,
			            SpacingSizeDV.ONE
			        )//
			        .withClass("list-group-item list-group-item-action")//
			        .withCondClass(
			            sections.get(i).equals(currentSection), "active"
			        )//
			        .withTagType(ButtonTagTypeDV.BUTTON)//
			        .build()
			);
		}

		return form(element.getType(), listGroup);
	}

	private DomContent
	    getRightColumn(ElementJDBCFlat element, String currentSection)
	{
		return form(
		    element.getType(),

		    getSettingsPanel(currentSection)

		).withClass("col-lg-9 col-md-8 p-3").withId("APP_SETTINGS");
	}

	private DomContent getSettingsPanel(String currentSection)
	{
		DomContent row = null;

		switch (currentSection) {
		case "servlet":
			row = getServletSettingsPanel();
			break;

		case "database":
			row = getDatabaseSettingsPanel(currentSection);
			break;

		case "html":
			row = getHtmlSettingsPanel(currentSection);
			break;

		case "hosting":
			row = getHostingSettingsPanel(currentSection);
			break;

		case "webapp":
			row = getWebappSettingsPanel(currentSection);
			break;

		case "security":
			row = getSecuritySettingsPanel(currentSection);
			break;

		case "accountActivation":
			row = getAccountActivationSettingsPanel(currentSection);
			break;

		case "email":
			row = getEmailSettingsPanel(currentSection);
			break;

		}

		return div(
		    // script(
//		        """
//		               function sendData() {
//		              var xhr = new XMLHttpRequest();
//		              var url = "http://localhost:8080/bueffeltier/api?;
//		              var formData = new FormData(document.getElementById("APP_SETTINGS"));
//
//		              xhr.onreadystatechange = function() {
//		                if (xhr.readyState == 4 && xhr.status == 200) {
//		                  alert(xhr.responseText);
//		                }
//		              };
//
//		              xhr.open("POST", url, true);
//		              xhr.send(formData);
//		            }
//		               """
//		    ), //
		    row, ButtonBuilder.create()//
		        .withTagType(ButtonTagTypeDV.BUTTON)//
		        .withButtonType(ButtonTypeDV.SUBMIT)//
		        .withText("Speichern")//
		        .withClass("float-end")//
		        .build()
		)//
//		    .withClass("col-lg-9 col-md-8 p-3 col-12")
		;
	}

	private DomContent
	    getBooleanRowEntry(String key, boolean value, boolean disabled)
	{
		return RowBuilder.create().withDomContent(

		    ColumnBuilder.create()//
		        .withGridWidth(GridWidthDV.FOUR)
		        .withDomContent(
		            LabelBuilder.create()//
		                .build(key, "ip-" + key)
		        ).build(), //

		    ColumnBuilder.create()//
		        .withGridWidth(GridWidthDV.EIGHT)//
		        .withDomContent(
		            FormControlBuilder.create()
		                .withType(FormInputTypeDV.CHECKBOX)//
		                .withName(key)//
		                .isChecked(value)//
		                .withId("ip-" + key)//
		                .disabled(disabled)//
		                .build()
		        ).build()

		).build();
	}

	private DomContent
	    getColorChooserRowEntry(String key, String value, boolean disabled)
	{
		return RowBuilder.create().withDomContent(

		    ColumnBuilder.create()//
		        .withGridWidth(GridWidthDV.AUTO)
		        .withDomContent(
		            LabelBuilder.create()//
		                .build(key, "ip-" + key)
		        ).build(), //

		    ColumnBuilder.create()//
		        .withGridWidth(GridWidthDV.AUTO)//
		        .withDomContent(
		            FormControlBuilder.create()//
		                .withType(FormInputTypeDV.COLOR)//
		                .withName(key)//
		                .withId("ip-" + key)//
		                .disabled(disabled)//
		                .build()
		        ).build()

		).build();
	}

	private DomContent
	    getVerticalStringEntry(String key, String value, boolean disabled)
	{
		return div(
		    LabelBuilder.create()//
		        .build(key, "ip-" + key),
		    FormControlBuilder.create()//
		        .withName(key)//
		        .withValue(value)//
		        .withType(FormInputTypeDV.TEXT)//
		        .withId("ip-" + key)//
		        .build()
		);
	}

	private DomContent
	    getVerticalFileEntry(String key, String value, boolean disabled)
	{
		return div(
		    LabelBuilder.create()//
		        .build(key, "ip-" + key),
		    FormControlBuilder.create()//
		        .withName(key)//
		        .withValue(value)//
		        .withType(FormInputTypeDV.FILE)//
		        .openInternalFC(true)//
		        .withId("ip-" + key)//
		        .build()
		);
	}

	// TODO sveng 22.04.2023: Als eigenes Gui ELement festlegen. Das gilt für
	// alle hier verwendeten Form-Einträge.
	private DomContent getStringListEntry(
	    String key,
	    List<String> values,
	    boolean disabled,
	    int maxLength
	)
	{
		List<DivTag> listEntries = new ArrayList<>();

		for (int i = 0; i < values.size(); i++)
		{
			listEntries.add(div(FormControlBuilder.create()//
//			            .withName(key + "[" + i + "]")//
			    .withName(key + "[]")//
			    .withValue(StringEscapeUtils.escapeHtml4(values.get(i)))//
			    .withType(FormInputTypeDV.TEXT)//
			    .build(),
			    div(
			        ButtonBuilder.create()//
			            .withText("-")//
			            .withColor(ColorDV.SECONDARY)//
			            .asOutline()//
			            .withClass("input-group-del-btn")//
			            .withId("btn-" + i)//
			            .build()
			    ).withClass("input-group-append")

			).withClass("input-group"));
		}

		return div(
		    LabelBuilder.create()//
		        .build(key, "ip-" + key),
		    each(listEntries, e -> e), //
		    RowBuilder.create()//
		        .withSpacing(
		            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
		            SpacingSizeDV.THREE
		        )//
		        .withDomContent(
		            //
		            ColumnBuilder.create()//
//		                .withGridWidth(GridWidthDV.AUTO)//
		                .withClass("text-end")//
		                .withDomContent(
		                    ButtonBuilder.create()//
		                        .withText("Eintrag hinzufügen")//
		                        .withColor(ColorDV.SECONDARY)//
		                        .withClass("input-group-add-btn")//
//		                        .withClass("float-end")//
		                        .asOutline()//
		                        .build()
		                )//
		                .build()

		        )//
		        .build()
		)//
		    .withClass("inputFieldsContainer")//
		    .withId("inputFieldsContainer")
		    .withData("maxLength", Integer.toString(maxLength))//
		;
	}

	private DomContent getServletSettingsPanel()
	{
		return div(

		    getBooleanRowEntry(
		        "property:allowUrlRewriting", service.isUrlRewritingEnabled(),
		        true
		    ),
		    getBooleanRowEntry(
		        "property:isInProductionMode", service.isProductionModeActive(),
		        false
		    ),
		    getVerticalStringEntry(
		        "property:servletContextPath", service.getServletContextPath(),
		        false
		    ),
		    getVerticalStringEntry(
		        "property:servletSessionMode", service.getServletSessionMode(),
		        false
		    ),
		    getVerticalStringEntry(
		        "property:servletCharacterEncoding",
		        service.getServletCharacterEncoding(), false
		    ),
		    getBooleanRowEntry(
		        "property:isServletInHttpsMode",
		        service.getIsServletInHttpsMode(), false
		    ),
		    getVerticalStringEntry(
		        "property:servletCookieDomain",
		        service.getServletCookieDomain(), false
		    ),
		    getVerticalStringEntry(
		        "property:servletCookiePath", service.getServletCookiePath(),
		        false
		    )
		);
	}

	private DomContent getDatabaseSettingsPanel(String currentSection)
	{
		return div(

		    getVerticalStringEntry(
		        "property:dbPassword", service.getDbPassword(), false
		    ), //
		    getVerticalStringEntry(
		        //
		        "property:dbUrl", service.getDbUrl(), false//
		    ),
		    getVerticalStringEntry(
		        //
		        "property:dbUsername", service.getDbUsername(), false//
		    )
		);
	}

	private DomContent getHtmlSettingsPanel(String currentSection)
	{
		return div(

		    getVerticalStringEntry(
		        //
		        "property:faviconPath", service.getFaviconPath(), false//
		    ), //
		    getVerticalStringEntry(
		        //
		        "property:htmlLang", service.getHtmlLang(), false//
		    ), //
		    getStringListEntry(
		        "property:htmlScriptPaths", service.getHtmlScriptPaths(), false,
		        7
		    ),
		    getStringListEntry(
		        "property:htmlStylesheetTags", service.getHtmlStylesheetTags(),
		        false, 7
		    ),
		    getVerticalStringEntry(
		        //
		        "property:headerImageAltName", service.getHeaderImageAltName(),
		        false//
		    ), //
		    getVerticalFileEntry(
		        "property:headerImagePath", service.getHeaderImagePath(), false//
		    ), //
		    getColorChooserRowEntry(
		        "property:mobileBrowserAppLayoutColor",
		        service.getMobileBrowserAppLayoutColor(), false
		    ),
		    getVerticalStringEntry(
		        "property:htmlMetaCharset", service.getHtmlMetaCharset(), false//
		    ) //
		);
	}

	private DomContent getHostingSettingsPanel(String currentSection)
	{
		return div(
		    getVerticalStringEntry("hostName", service.getHostName(), false)
		);
	}

	private DomContent getWebappSettingsPanel(String currentSection)
	{
		return div(
		    getVerticalStringEntry(
		        //
		        "property:registerConfirmPageHtmlTitle",
		        service.getRegisterConfirmPageHtmlTitle(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:alertBrowserCookiesDisabledMessage",
		        service.getAlertBrowserCookiesDisabledMessage(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:servletHomePagePath",
		        service.getServletHomePagePath(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:loginPageServletPath",
		        service.getLoginPageServletPath(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:rootPagePath", service.getRootPagePath(), false
		    )
		);
	}

	private DomContent getSecuritySettingsPanel(String currentSection)
	{
		return div(
		    getVerticalStringEntry(
		        //
		        "property:HMACSecret", service.getHMACSecret(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:jwtIssuer", service.getJwtIssuer(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:jwtSubject", service.getJwtSubject(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:jwtAudience", service.getJwtAudience(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:jwtExperiationInMinutes",
		        Double.toString(service.getJwtExperiationInMinutes()), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:jwtNotBeforeTime", service.getJwtNotBeforeTime(),
		        false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:activationKeyExperiationTimeInMinutes",
		        service.getActivationKeyExperiationTimeInMinutes(), false
		    )
		);
	}

	private DomContent getAccountActivationSettingsPanel(String currentSection)
	{
		return div(

		    getVerticalStringEntry(
		        //
		        "property:accountActivationMailSubject",
		        service.getAccountActivationMailSubject(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:accountActivationMailMessage1",
		        service.getAccountActivationMailMessage1(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:accountActivationMailMessage2",
		        service.getAccountActivationMailMessage2(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:accountActivationMailMessage3",
		        service.getAccountActivationMailMessage3(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:pageContentRegisterConfirmedText",
		        service.getPageContentRegisterConfirmedText(), false
		    )
		);
	}

	private DomContent getEmailSettingsPanel(String currentSection)
	{
		return div(
		    getVerticalStringEntry(
		        //
		        "property:googleEmailServiceApplicationName",
		        service.getGoogleEmailServiceApplicationName(), false
		    ),
		    getVerticalStringEntry(
		        //
		        "property:googleEmailServiceEMailAddress",
		        service.getGoogleEmailServiceEMailAddress(), false
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
		throw new UnsupportedOperationException("Not supported yet.");

	}

	@Override
	protected void setContainerTag()
	{
		throw new UnsupportedOperationException("Not supported yet.");

	}
}