package interoperability;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Connect {
	
	static final String JDBC_DRIVER= "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/personal_project?serverTimezone=UTC&useSSL=false";
	static final String USER = "root";
	static final String PASS= "password";
	
	public Connection accessDB() {
		
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		try {
		    conn= DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}	
}
