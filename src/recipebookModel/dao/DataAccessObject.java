
// Object for creating database connection with Haaga-Helia mariaDB, requires a running ssh-connetion

package recipebookModel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataAccessObject {

	protected static Connection getConnection() {
		Connection connection = null;
		
		// Insert valid username and password to access the database
		
		String username = "USERNAME";  
		String password = "PASSWORD";
		String url = "jdbc:mysql://localhost:3306/a1800602";
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
	protected static void close(Statement stmt, Connection connection) {
		close(null, stmt, connection);
	}
	protected static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}