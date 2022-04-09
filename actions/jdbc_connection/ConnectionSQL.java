package jdbc_connection;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


public class ConnectionSQL {
	    public static Connection getConnectionSQL() {
	    	String hostName="localhost";
			String sqlInstanceName="THUPHUOC\\SQLEXPRESS";
			String dbName="automationfc";
			String userName="thuphuoc";
			String password="thuphuoc8";
	    	return getConnectionSQL(hostName, sqlInstanceName, dbName,userName,password);
	}
	    public static Connection getConnectionSQL(String hostName,String sqlInstanceName, String dbName, String userName, String password) {
	    	Connection conn= null;
	    	try {
//	    		Class.forName("com.sql.jdbc.Driver");
	    		String connectionUrl = "jdbc:jtds:sqlserver://"+hostName+":1455/" +dbName+";instance="+ sqlInstanceName;
	    		conn=DriverManager.getConnection(connectionUrl,userName,password);
	    	}
	    	catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    	return conn;
	    	
	    }
}
