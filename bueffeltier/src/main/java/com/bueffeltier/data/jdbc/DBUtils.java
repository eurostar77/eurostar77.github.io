package com.bueffeltier.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils
{
	// TODO sveng 29.05.2023: aus settings laden
	private final static String URL = "jdbc:mariadb://localhost:3306/bueffeltier?serverTimezone=MET";
//	private final static String URL = "jdbc:mariadb://localhost:3306/bueffeltier";
	private final static String USERNAME = "k404691q_root";
	private final static String PASSWORD = "ATzUIPMdKNezrc6";

	public static Connection getConnection()
	{

		Connection conn = null;

		try
		{
			try
			{
				// TODO sveng 29.05.2023: setting und default
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				System.out.println("Driver Class not found. sg");
			}
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Successfully connected to database.");
		} catch (SQLException e)
		{
			// todo: LogUtil.log("Das Herstellen der JDBC-Connection ist
			// fehlgeschlagen.);
			// LogUtil.log(LevelSEVERE, e.get.Message());
			e.printStackTrace();
		} finally
		{

		}

		return conn;
	}

	public static void executeDDLStatement(String sql) throws SQLException
	{
		String lowerCaseString = sql.toLowerCase();

		boolean validDDL = lowerCaseString.contains("create");

		validDDL |= lowerCaseString.contains("alter");
		validDDL |= lowerCaseString.contains("create or alter");
		validDDL |= lowerCaseString.contains("create or replace");
		validDDL |= lowerCaseString.contains("recreate");
		validDDL |= lowerCaseString.contains("drop");

		if (validDDL)
		{
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			statement.execute(sql);
			conn.close();
			// todo:
//			LogUtils.log(Level.INFO, "Das Statement "+sql+" wurde erfolgreich ausgeführt.");
		} else
		{
			// todo:
//			LogUtils.log(Level.SEVERE, "keine DDL!");
			throw new SQLException();
		}

	}
}
