package org.openmrs.module.datamigration.util;

import org.openmrs.*;
import org.openmrs.api.context.Context;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openmrs.module.datamigration.util.Operations.isNullOrEmpty;

public abstract class ObsChildrenUtil {
	
	public static Obs InsertObsChild(org.openmrs.module.datamigration.util.Model.ObsChildren _o, Obs _obsChild,
	        Encounter encounter, Location location, Patient patient) {
		
		try {
			Obs obs = new Obs();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Concept concept = Context.getConceptService().getConcept(_o.getConceptId());
			String obsValue = isNullOrEmpty(_o.getValue()) ? _o.getValue() : " ";
			Date dateVlue;
			
			switch (concept.getDatatype().getId()) {
				case 1: //Numeric
					obs.setValueNumeric(Double.parseDouble(obsValue));
					break;
				case 2: //Coded
					obs.setValueCoded(Context.getConceptService().getConcept(obsValue));
					break;
				case 3: //Text
					obs.setValueText(obsValue);
					break;
				case 4: //N/A
					obs.setValueText(obsValue);
					break;
				case 6: //Date
					dateVlue = dateFormat.parse(obsValue);
					obs.setValueDatetime(dateVlue);
					break;
				case 8: //Datetime
					dateVlue = dateFormat.parse(obsValue);
					obs.setValueDatetime(dateVlue);
					break;
				case 10://boolean
					boolean booleanValue = Boolean.parseBoolean(obsValue);
					obs.setValueBoolean(booleanValue);
				default:
					obs.setValueText("");
					break;
			}
			obs.setConcept(concept);
			obs.setComment("");
			obs.setEncounter(encounter);
			obs.setObsDatetime(encounter.getEncounterDatetime());
			obs.setLocation(location);
			obs.setObsGroup(_obsChild);
			obs.setPerson(patient);
			return obs;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
