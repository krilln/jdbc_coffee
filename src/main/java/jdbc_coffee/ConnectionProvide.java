package jdbc_coffee;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvide {
	public static Connection getConnection() throws SQLException {
		return MyDataSource.getInstance().getDataSource().getConnection();
	}
	
}
