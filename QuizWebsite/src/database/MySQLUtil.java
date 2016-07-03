package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUtil {

	public static int getLastInsertId(Connection con) throws SQLException {
		int id = 0;
		Statement stmt = (Statement) con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
		rs.next();
		id = rs.getInt("last_insert_id()");
		rs.close();
		return id;
	}
	
}
