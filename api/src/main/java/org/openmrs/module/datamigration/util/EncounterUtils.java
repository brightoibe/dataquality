package org.openmrs.module.datamigration.util;

import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.datamigration.util.Model.Migration;
import org.openmrs.module.datamigration.util.Model.ObsChildren;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import static org.openmrs.module.datamigration.util.Operations.isNullOrEmpty;

public abstract class EncounterUtils {
	
	/*	public static void InsertEncounter(Migration delegate, Location location, Patient patient) throws ParseException {

	        for (org.openmrs.module.datamigration.util.Model.Encounter e : delegate.getEncounters()) {
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                ArrayList<Encounter> encounters = new ArrayList<Encounter>();

	                //check for the visit id before inserting a new one
	                Date encounterDate = dateFormat.parse(e.getEncounterDate());
	                Visit visit = Context.getVisitService().getVisitsByPatient(patient).stream()
	                        .filter(m -> m.getStartDatetime() != null && m.getStartDatetime()
	                                .equals(encounterDate))
	                        .findFirst().orElse(null);

	                if (visit == null) {
	                    //create the visit
	                    visit = new Visit(patient, Context.getVisitService().getVisitType(1), encounterDate);
	                    visit.setStopDatetime(encounterDate);
	                    visit.setLocation(location);
	                    visit.setPatient(patient);
	                }
	                visit = Context.getVisitService().saveVisit(visit);
	                Encounter encounter;
	                //false is to exclude voided encounters.
	                encounter = Context.getEncounterService().getEncountersByVisit(visit, false).stream()
	                        .filter(m -> m.getVisit().getStartDatetime().equals(encounterDate)).findFirst().orElse(null);
	                if (encounter == null) {
	                    encounter = new Encounter();
	                    encounter.setVisit(visit);
	                    encounter.setForm(Context.getFormService().getForm(e.getFormTypeId()));
	                    encounter.setEncounterType(Context.getEncounterService().getEncounterType(
	                            e.getEncounterTypeId()));
	                    encounter.setLocation(location);
	                    encounter.setPatient(patient);
	                    encounter.setEncounterDatetime(encounterDate);

	                    String familyName, givenName, middleName;
	                    givenName = e.getProvider().getGivenName();
	                    middleName = e.getProvider().getMiddleName().trim();
	                    familyName = e.getProvider().getSurname().trim();
	                    Provider provider;
	                    Person person = new Person();
	                    //check if provider name is empty and choose Super User by default
	                    if (!isNullOrEmpty(givenName) && !isNullOrEmpty(familyName)) {

	                        Set<PersonName> personNames = new TreeSet<>();
	                        personNames.add(new PersonName("Admin", "A", "Admin"));
	                        person.setNames(personNames);

	                    } else if(isNullOrEmpty(givenName) || isNullOrEmpty(familyName)){
	                        Set<PersonName> personNames = new TreeSet<>();
	                        personNames.add(new PersonName(givenName, middleName, familyName));
	                        person.setNames(personNames);
	                    }
	                    //TODO: check if person exists in db
	                    Context.getPersonService().savePerson(person);
	                    provider = new Provider();
	                    provider.setPerson(person);
	                    //check if the provider is already in the db;
	                    provider = Context.getProviderService().getAllProviders().stream().filter(m -> m.getPerson().getFamilyName().equals(familyName)
	                            && m.getPerson().getGivenName().equals(givenName)).findFirst().orElse(null);
	                    if(provider != null){
	                        Context.getProviderService().saveProvider(provider);
	                        encounter.setProvider(Context.getEncounterService().getEncounterRole(2), provider);
	                        encounter = Context.getEncounterService().saveEncounter(encounter);
	                    }
	                }
	                //inserting obs
	                for (org.openmrs.module.datamigration.util.Model.Obs _o : e.getObs()) {
	                    if (Context.getConceptService().getConcept(_o.getConceptId()) != null) {
	                        Obs obs = new Obs();

	                        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
	                        Concept concept = Context.getConceptService().getConcept(_o.getConceptId());
	                        String obsValue = isNullOrEmpty(_o.getValue()) ? _o.getValue() : null;
	                        Date dateVlue;

	                        switch (concept.getDatatype().getConceptDatatypeId()) {
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
	                                obs.setValueCoded(Context.getConceptService().getConcept(obsValue));
	                                break;
	                            case 6: //Date
	                                dateVlue = dateFormat1.parse(obsValue);
	                                obs.setValueDatetime(dateVlue);
	                                break;
	                            case 8: //Datetime
	                                dateVlue = dateFormat1.parse(obsValue);
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
	                        obs.setPerson(patient);
	                        Context.getObsService().saveObs(obs, "");
	                        *//*if (_o.getObsChildren().size() > 0) {
	                            Obs obs = ObsUtil.InsertObs(_o, encounter, location, patient);
	                            Obs groupObs = Context.getObsService().saveObs(obs, "");
	                            //inserting obs children
	                            for (ObsChildren obsChild : _o.getObsChildren()) {
	                                Obs obschild = ObsChildrenUtil.InsertObsChild(obsChild, groupObs, encounter, location, patient);
	                                Context.getObsService().saveObs(obschild, "");
	                            }
	                          } else {
	                            Obs obs = ObsUtil.InsertObs(_o, encounter, location, patient);
	                            Context.getObsService().saveObs(obs, "");
	                          }*//*
	                             }
	                             }
	                             //return encounter;
	                             } catch (Exception ex) {
	                             ex.printStackTrace();
	                             throw ex;
	                             //return null;
	                             }
	                             }
	                             }*/
	
	public static void InsertEncounter(Migration delegate, Location location, Patient patient) throws ParseException {
        for (org.openmrs.module.datamigration.util.Model.Encounter e : delegate.getEncounters()) {

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                Date encounterDate = dateFormat.parse(e.getEncounterDate());
                Visit visit = Context.getVisitService().getVisitsByPatient(patient).stream()
                        .filter(m -> m.getStartDatetime() != null && m.getStartDatetime()
                                .equals(encounterDate))
                        .findFirst().orElse(null);

                if (visit == null) {
                    //create the visit
                    visit = new Visit(patient, Context.getVisitService().getVisitType(1), encounterDate);
                    visit.setStopDatetime(encounterDate);
                    visit.setLocation(location);
                    visit.setPatient(patient);
                }
                visit = Context.getVisitService().saveVisit(visit);

                //provider
                Provider provider;
                Person person = new Person();
                Set<PersonName> personNames = new TreeSet<>();
                personNames.add(new PersonName("Admin", "A", "Admin"));
                person.setNames(personNames);

                //check if person exists in db
                Context.getPersonService().savePerson(person);

                //check if the provider is already in the db;
                provider = Context.getProviderService().getAllProviders().stream().filter(m -> m.getPerson().getFamilyName().equals("Admin")
                        && m.getPerson().getGivenName().equals("Admin")).findFirst().orElse(null);
                if(provider == null){
                    provider = new Provider();
                    provider.setPerson(person);
                    Context.getProviderService().saveProvider(provider);
                }

                Encounter encounter;
                //false is to exclude voided encounters.
                encounter = Context.getEncounterService().getEncountersByVisit(visit, false).stream()
                        .filter(m -> m.getVisit().getStartDatetime().equals(encounterDate) && m.getEncounterType().getEncounterTypeId() == e.getEncounterTypeId()).findFirst().orElse(null);
                if (encounter == null) {
                    encounter = new Encounter();
                    encounter.setVisit(visit);
                    encounter.setForm(Context.getFormService().getForm(e.getFormTypeId()));
                    encounter.setEncounterType(Context.getEncounterService().getEncounterType(
                            e.getEncounterTypeId()));
                    //encounter.setObs(obsSet);
                    encounter.setLocation(location);
                    encounter.setPatient(patient);
                    encounter.setEncounterDatetime(encounterDate);
                    encounter.setProvider(Context.getEncounterService().getEncounterRole(2), provider);
                }

                Context.getEncounterService().saveEncounter(encounter);

                for (org.openmrs.module.datamigration.util.Model.Obs _o : e.getObs()) {
                    if (Context.getConceptService().getConcept(_o.getConceptId()) != null) {
                        if (_o.getObsChildren().size() > 0) {
                            Obs obs = ObsUtil.InsertObs(_o, encounter, location, patient);
                            Obs groupObs = Context.getObsService().saveObs(obs, "");
                            //inserting obs children
                            for (ObsChildren obsChild : _o.getObsChildren()) {
                                Obs obschild = ObsChildrenUtil.InsertObsChild(obsChild, groupObs, encounter, location, patient);
                                Context.getObsService().saveObs(obschild, "");
                            }
                        } else {
                            Obs obs = ObsUtil.InsertObs(_o, encounter, location, patient);
                            Context.getObsService().saveObs(obs, "");
                        }
                    }
                }

                // return encounter;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }
}
