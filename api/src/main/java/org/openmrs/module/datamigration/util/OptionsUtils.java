package org.openmrs.module.datamigration.util;

import java.util.Properties;

import org.openmrs.module.datamigration.NmrsConnection;

public class OptionsUtils {
	
	public static NmrsConnection getNmrsConnectionDetails(Properties props) {
		
		NmrsConnection result = new NmrsConnection();
		
		try {
			result.setUsername(props.getProperty("connection.username"));
			result.setPassword(props.getProperty("connection.password"));
			result.setUrl(props.getProperty("connection.url"));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
		
	}
}
