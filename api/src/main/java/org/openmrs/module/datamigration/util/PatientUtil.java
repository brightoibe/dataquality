package org.openmrs.module.datamigration.util;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.openmrs.*;
import org.openmrs.annotation.Logging;
import org.openmrs.api.context.Context;
import org.openmrs.module.datamigration.util.Model.Address;
import org.openmrs.module.datamigration.util.Model.Identifier;
import org.openmrs.module.datamigration.util.Model.Migration;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public abstract class PatientUtil {
	
	public static Patient InsertPatient(final Migration delegate, Location location) {
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Patient patient = new Patient();
			String pepfarId = "";
			//handle patient identifiers
			Set<PatientIdentifier> patientIdentifiers = new TreeSet<PatientIdentifier>();
			for (Identifier _id: delegate.getIdentifiers()) {
				PatientIdentifier patientIdentifier = new PatientIdentifier();
				patientIdentifier.setIdentifier(_id.getIdentifier());
				patientIdentifier.setLocation(location);
				patientIdentifier.setIdentifierType(Context.getPatientService().getPatientIdentifierType(_id.getIdentifierType()));
				patientIdentifier.setPreferred(_id.getPreferred());
				patientIdentifiers.add(patientIdentifier);
				if(_id.getIdentifierType() == 4)
					pepfarId = _id.getIdentifier();
			}
			//handle patient
			patient.setIdentifiers(patientIdentifiers);
			
			patient.addName(new PersonName(delegate.getGivenName(), delegate.getMiddleName(), delegate.getSurname()));
			patient.setBirthdate(dateFormat.parse(delegate.getBirthDate()));
			patient.setGender(delegate.getGender());
			patient.setDead(Boolean.parseBoolean(delegate.getDead()));
			patient.setDeathDate(delegate.getDeathDate() != null ? dateFormat.parse(delegate.getDeathDate()) : null);
			//patient.setCauseOfDeath(delegate.getCauseOfDeath());
			patient.setDateCreated(delegate.getDateCreated());

			Set<PersonAddress> addresses = new TreeSet<>();
			PersonAddress address = new PersonAddress();

			address.setCountry(delegate.getAddress().getCountry());
			address.setCityVillage(delegate.getAddress().getCityVillage());
			address.setAddress1(delegate.getAddress().getAddress1());
			address.setAddress2(delegate.getAddress().getAddress2());
			address.setAddress3(delegate.getAddress().getAddress3());
			address.setLatitude(delegate.getAddress().getLatitude());
			address.setLongitude(delegate.getAddress().getLongitude());
			address.setStateProvince(delegate.getAddress().getStateProvince());
			address.setPostalCode(delegate.getAddress().getPostalCode());

			addresses.add(address);
			patient.setAddresses(addresses);

			/*Set<PersonAttribute> attributes = new TreeSet<>();
			PersonAttribute attribute = new PersonAttribute();
			attribute.setAttributeType(Context.getPersonService().getPersonAttributeType(8));
			attribute.setValue(delegate.getPhone());
			attributes.add(attribute);
			patient.setAttributes(attributes);*/
			
			//check if the patient exists
			String finalPepfarId = pepfarId;
			Patient p = Context.getPatientService().getAllPatients().stream()
					.filter(x-> x.getPatientIdentifier(4) != null &&
							x.getPatientIdentifier(4).getIdentifier().equals(finalPepfarId))
					.findFirst().orElse(null);

			if(p != null)
				return p;

			//handle patient save to openmrs
			Context.getPatientService().savePatient(patient);
			return patient;
		}
		catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(PatientUtil.class).log(Priority.ERROR, e.getMessage());
			return null;
		}
	}
}
