package util;

import java.sql.DriverManager;
import java.sql.Connection;

public class DatabaseUtil {
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
