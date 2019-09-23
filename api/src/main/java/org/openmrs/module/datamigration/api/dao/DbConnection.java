package org.openmrs.module.datamigration.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.openmrs.module.datamigration.NmrsConnection;
import org.openmrs.module.datamigration.util.OptionsUtils;
import org.openmrs.util.OpenmrsUtil;

public class DbConnection {
	
	public Connection Connection() {
		Connection connection;
		Properties props = OpenmrsUtil.getRuntimeProperties("openmrs");
		if (props == null)
			props = OpenmrsUtil.getRuntimeProperties("openmrs-standalone");
		
		try {
			NmrsConnection nmrsConnection = new NmrsConnection();
			
			try {
				nmrsConnection.setUsername(props.getProperty("connection.username"));
				nmrsConnection.setPassword(props.getProperty("connection.password"));
				nmrsConnection.setUrl(props.getProperty("connection.url"));
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			connection = DriverManager.getConnection(nmrsConnection.getUrl(), nmrsConnection.getUsername(),
			    nmrsConnection.getPassword());
			return connection;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Connection staticConnection() {
		Connection connection;
		
		try {
			connection = (Connection) DriverManager.getConnection(
			    "jdbc:mysql://127.0.0.1:3306/openmrs?zeroDateTimeBehavior=convertToNull", "root", "Nu66et");
			return connection;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
