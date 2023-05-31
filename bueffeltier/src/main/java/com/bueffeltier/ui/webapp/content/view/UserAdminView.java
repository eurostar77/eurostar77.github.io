package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.div;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.data.jdbc.UserDaoJDBC;
import com.bueffeltier.data.jdbc.UserJDBC;
import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;
import com.bueffeltier.ui.html.organism.ButtonBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder;
import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;
import com.bueffeltier.ui.html.organism.FormControlBuilder;
import com.bueffeltier.ui.html.organism.RowBuilder;
import com.bueffeltier.ui.html.organism.TableBuilder;
import com.bueffeltier.ui.html.organism.TableBuilder.TableColumnTypeDV;
import com.bueffeltier.ui.html.organism.TableRow;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class UserAdminView extends AbstractView
{
	private static UserAdminView instance;

	private UserDaoJDBC userDaoJDBC = UserDaoJDBC.getInstance();

	private UserAdminView()
	{
		super();
	}

	public static UserAdminView getInstance()
	{
		if (instance == null)
		{
			instance = new UserAdminView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		List<UserJDBC> users = userDaoJDBC.readAll();

		List<TableRow> rows = new ArrayList<>();

		for (UserJDBC user : users)
		{
			DomContent id = FormControlBuilder.create()
			    .withValue(Long.toString(user.getId())).build();
			DomContent name = FormControlBuilder.create()
			    .withValue(user.getName()).build();
			DomContent email = FormControlBuilder.create()
			    .withValue(user.getEmail()).build();

			List<Object> row = Arrays.asList(id, name, email);

			rows.add(new TableRow(row));
		}

		return form(

		    element.getType(),

		    RowBuilder.create()//
		        .withSpacing(
		            SpacingPropertyDV.MARGIN, SpacingSidesDV.BOTTOM,
		            SpacingSizeDV.FOUR
		        )
		        .withDomContent(
		            ColumnBuilder.create().withGridWidth(GridWidthDV.ONE)
		                .withClass("text-start").withDomContent(div()).build(),
		            ColumnBuilder.create().withGridWidth(GridWidthDV.FOUR)
		                .withClass("text-start")
		                .withDomContent(
		                    FormControlBuilder.create()
		                        .withId("filterSearchInput").build()
		                ).build(),
		            ColumnBuilder.create().withGridWidth(GridWidthDV.TWO)
		                .withClass("text-start")
		                .withDomContent(
		                    ButtonBuilder.create().withText("Suchen").build()
		                ).build(),
		            ColumnBuilder.create().withGridWidth(GridWidthDV.FOUR)
		                .withClass("text-end")
		                .withDomContent(
		                    ButtonBuilder.create().withText("Neuer Benutzer")
		                        .build()
		                ).build(),
		            ColumnBuilder.create().withGridWidth(GridWidthDV.ONE)
		                .withClass("text-end").withDomContent(div()).build()
		        ).build(),

		    TableBuilder//
		        .create()//
		        .withColumn(
		            "ID", TableColumnTypeDV.FORMCONTROL, GridWidthDV.TWO
		        )
		        .withColumn(
		            "Name", TableColumnTypeDV.FORMCONTROL, GridWidthDV.FIVE
		        )
		        .withColumn(
		            "E-Mail", TableColumnTypeDV.FORMCONTROL, GridWidthDV.FIVE
		        )

		        .withRows(rows).build()//

//		ArrayList<String> tHeadItems = new ArrayList<>();
//		tHeadItems.add("ID");
//		tHeadItems.add("Vorname");
//		tHeadItems.add("E-Mail");
//
//		ArrayList<List<String>> tBodyItems = new ArrayList<>();
//
//		for (int i = 0; i < users.size(); i++)
//		{
//
//			ArrayList<String> row = new ArrayList<>();
//			row.add(Long.toString(users.get(i).getId()));
//			row.add(users.get(i).getName());
//			row.add(users.get(i).getEmail());
//			tBodyItems.add(row);
//		}
//
//		return div(
//		    h1("User Administration"),
//		    div(
//		        Bootstrap.bsTable(
//		            tHeadItems, tBodyItems, true,
//		            "table table-light table-striped", ""
//		        )
//		    )
		);
		// alle user anzeigen
		// filter anbieten
		// suchfeld anbieten

	}

	/**
	 *
	 * @return
	 */
	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 *
	 * @return
	 */
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
