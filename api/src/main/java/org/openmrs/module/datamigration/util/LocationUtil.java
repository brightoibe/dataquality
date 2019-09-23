package org.openmrs.module.datamigration.util;

import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.datamigration.util.Model.Address;
import org.openmrs.module.datamigration.util.Model.Facility;
import org.openmrs.module.datamigration.util.Model.Migration;

public abstract class LocationUtil {
	
	public static Location InsertLocation(Facility facility/*, Address address*/) {
		try {
			Location location = new Location();
			location.setAddress1(facility.getFacilityName());
			location.setName(facility.getFacilityName());
			location.setCountry("Nigeria");
			location.setCityVillage(facility.getLga());
			location.setCountyDistrict(facility.getState());
			
			//check if location exists
			Location l = Context.getLocationService().getLocation(location.getName());
			if (l != null) {
				return l;
			}
			Context.getLocationService().saveLocation(location);
			return location;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
