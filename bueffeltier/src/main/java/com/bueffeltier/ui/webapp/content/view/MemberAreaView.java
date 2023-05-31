package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.UserDaoJDBC;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ButtonBuilder.ButtonTagTypeDV;
import com.bueffeltier.ui.html.organism.CardBuilder;
import com.bueffeltier.ui.html.organism.CardBuilder.HeadlineDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.BreakpointDV;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.RowBuilder;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class MemberAreaView extends AbstractView
{

	private static MemberAreaView instance;

	private UserDaoJDBC userDaoJDBC = UserDaoJDBC.getInstance();

	private MemberAreaView()
	{
		super();
	}

	public static MemberAreaView getInstance()
	{
		if (instance == null)
		{
			instance = new MemberAreaView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		UserJDBC user = null;

		try
		{
			user = (UserJDBC) request.getAttribute("user");

		} catch (Exception e)
		{
			boolean fail = true;
		}

//		setContainerTagClassName("settings");

		return div()//
		    .with(adminOptions(request))
		    .with(userAccountSettings(user, request));
	}

	/*
	 * Login Status: 0 visitor 1 user 2 member 3 premium 4 admin
	 */
	private DomContent
	    userAccountSettings(UserJDBC user, HttpServletRequest request)
	{
		return div().withClass("card-body")//
//		    .with(h3("User-Optionen:")).withClass(
//		        "division"
//		    )
//		    .with(
//		        p("Mein Profil"),
//		        p(user.getName() + ", Status: " + user.getPermission())
//		    )
		    .condWith(
		        //
		        this.getRequestPermission(request) >= 0, //
		        CardBuilder.create()//
		            .withTitle(HeadlineDV.H3, "Lern-Einstellungen", true)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
		                SpacingSizeDV.THREE
		            )//
		            .withDomContent(
		                RowBuilder.create()//
		                    .withDomContent(
		                        ColumnBuilder.create()//
		                            .withDomContent(
		                                p(
		                                    "Setze Lernziele, lege dein Lerntempo fest. "
		                                )
		                            )//
		                            .withGridWidth(GridWidthDV.TEN)
		                            .withBreakpoint(BreakpointDV.MD)//
		                            .build()
		                    )//
		                    .withDomContent(
		                        ColumnBuilder.create()//
		                            .withDomContent(
		                                ButtonBuilder.create()//
		                                    .withTagType(ButtonTagTypeDV.A)//
		                                    .withText("Öffnen")
		                                    .withHref(
		                                        "/bueffeltier/member-learning-settings"
		                                    )//
		                                    .withClass("stretched-link")//
		                                    .build()
		                            )//
		                            .withGridWidth(GridWidthDV.TWO)
		                            .withBreakpoint(BreakpointDV.MD)//
		                            .build()
		                    )//
		                    .build()
		            ).build()
		    )

		    .condWith(
		        //
		        this.getRequestPermission(request) >= 0, //
		        CardBuilder.create()//
		            .withTitle(HeadlineDV.H3, "Konto-Einstellungen", true)//
		            .withSpacing(
		                SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
		                SpacingSizeDV.THREE
		            )//
		            .withDomContent(
		                RowBuilder.create()//
		                    .withDomContent(
		                        ColumnBuilder.create()//
		                            .withDomContent(
		                                p(
		                                    "Setze Lernziele, lege dein Lerntempo fest. "
		                                )
		                            )//
		                            .withGridWidth(GridWidthDV.TEN)
		                            .withBreakpoint(BreakpointDV.MD)//
		                            .build()
		                    )//
		                    .withDomContent(
		                        ColumnBuilder.create()//
		                            .withDomContent(
		                                ButtonBuilder.create()//
		                                    .withTagType(ButtonTagTypeDV.A)//
		                                    .withText("Öffnen")
		                                    .withHref(
		                                        "/bueffeltier/member-account-settings"
		                                    )//
		                                    .withClass("stretched-link")//
		                                    .build()
		                            )//
		                            .withGridWidth(GridWidthDV.TWO)
		                            .withBreakpoint(BreakpointDV.MD)//
		                            .build()
		                    )//
		                    .build()
		            ).build()
		    )

//		    .condWith(
//		        this.getRequestPermission(request) >= 0,
//		        p(
//		            a("Notizen verwalten")
//		                .attr("href", "/bueffeltier/member-notes")
//		        )
//		    )
//		    .condWith(
//		        this.getRequestPermission(request) >= 0,
//		        p(
//		            a("Nachrichten verwalten")
//		                .attr("href", "/bueffeltier/member-messages")
//		        )
//		    )
//		    .with(
//		        p(
//		            a("Delete Account").attr("class", "active")
//		                .attr("href", "/bueffeltier/delete-account")
//		        )
//		    )//
//		    .with(p("upgrade auf user oder premium"))//
		    .with(
		        // p("Lernstand (auch ungespeichert, Option: jetzt
		        // speichern)"),
		        // //
//		        p("Zeige user-bewertung (soziale kontrolle)"), //
//		        p("Newsletter"), //
//		        p("Gespeicherter Lernstand"), //
//		        p("Lernziel + Datum gespeichert"), //
//		        p("Aufgaben-Benachrichtigung"), //
//		        p("Persönlicher Betreuer und Anruf"), //
//		        p("Mitglieder-Telefon-Vermittlung zum Lernen im Auto"), //
//		        p("Cookie-Einstellungen, Ansicht-Einstellungen") //
//		        p("Chat"), //
//		        p("Forum"), //
//		        p("premium-auswertung"), p("Effizienz pro Aufgabentyp"), //
//		        p("author Optionen:"), //
//		        p("ggf. Kosten sparen, oder verdienen."), //
//		        p("Artikel verwalten")
		    );//
//		.withClass("card");
	}

	private DomContent adminOptions(HttpServletRequest request)
	{
		if (getRequestPermission(request) >= 5)
		{
			return div(
			    CardBuilder.create()//
			        .withTitle(HeadlineDV.H3, "Seitenverwaltung", true)//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
			            SpacingSizeDV.THREE
			        )//
			        .withDomContent(
			            RowBuilder.create()//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            p(
			                                "Seiten, Artikel und Module anlegen und verwalten. "
			                            )
			                        )//
			                        .withGridWidth(GridWidthDV.TEN)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            ButtonBuilder.create()//
			                                .withTagType(ButtonTagTypeDV.A)//
			                                .withText("Öffnen")
			                                .withHref(
			                                    "/bueffeltier/site-structure"
			                                )//
			                                .withClass("stretched-link")//
			                                .build()
			                        )//
			                        .withGridWidth(GridWidthDV.TWO)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .build()
			        )//
			        .build(),
			    //
			    CardBuilder.create()//
			        .withTitle(HeadlineDV.H3, "App-Einstellungen", true)//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
			            SpacingSizeDV.THREE
			        )//
			        .withDomContent(
			            RowBuilder.create()//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            p(
			                                "Verhalten der App anpassen, z.B. Datenbankverbindung, Sicherheit, usw..."
			                            )
			                        )//
			                        .withGridWidth(GridWidthDV.TEN)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            ButtonBuilder.create()//
			                                .withTagType(ButtonTagTypeDV.A)//
			                                .withText("Öffnen")
			                                .withHref(
			                                    "/bueffeltier/app-settings"
			                                )//
			                                .withClass("stretched-link")//
			                                .build()
			                        )//
			                        .withGridWidth(GridWidthDV.TWO)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .build()
			        ).build(),
			    //
			    CardBuilder.create()//
			        .withTitle(HeadlineDV.H3, "Ressourcen", true)//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
			            SpacingSizeDV.THREE
			        )//
			        .withDomContent(
			            RowBuilder.create()//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            p(
			                                "Bilder, Videos und Dokumente bereitstellen und verwalten."
			                            )
			                        )//
			                        .withGridWidth(GridWidthDV.TEN)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            ButtonBuilder.create()//
			                                .withTagType(ButtonTagTypeDV.A)//
			                                .withText("Öffnen")
			                                .withHref("/bueffeltier/ressources")//
			                                .withClass("stretched-link")//
			                                .build()
			                        )//
			                        .withGridWidth(GridWidthDV.TWO)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .build()
			        ).build(),
			    //
			    CardBuilder.create()//
			        .withTitle(HeadlineDV.H3, "Mitglieder-Verwaltung", true)//
			        .withSpacing(
			            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
			            SpacingSizeDV.THREE
			        )//
			        .withDomContent(
			            RowBuilder.create()//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            p("Mitglieder anzeigen und verwalten.")
			                        )//
			                        .withGridWidth(GridWidthDV.TEN)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .withDomContent(
			                    ColumnBuilder.create()//
			                        .withDomContent(
			                            ButtonBuilder.create()//
			                                .withTagType(ButtonTagTypeDV.A)//
			                                .withText("Öffnen")
			                                .withHref(
			                                    "/bueffeltier/user-administration"
			                                )//
			                                .withClass("stretched-link")//
			                                .build()
			                        )//
			                        .withGridWidth(GridWidthDV.TWO)
			                        .withBreakpoint(BreakpointDV.MD)//
			                        .build()
			                )//
			                .build()
			        ).build()
//			    div(
//			        p(
//			            a("Site Structure")
//			                .attr("href", "/bueffeltier/site-structure")
//			        ),
//			        p(
//			            a("App Settings")
//			                .attr("href", "/bueffeltier/app-settings")
//			        ),
//			        p(a("Ressources").attr("href", "/bueffeltier/ressources")),
//			        p(
//			            a("Benutzerverwaltung")
//			                .attr("href", "/bueffeltier/user-administration")
//			        )
//					// h4(E - Mail - Settings
//					// :),
//					// p()
//			    ).withClass("card-body")
			);
		}
		return null;
	}

//    private Tag dddd()
//    {
//
//    }
	/**
	 *
	 * @return
	 */
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