/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.datamigration;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PersonAddress;
import org.openmrs.api.EncounterService;
import org.openmrs.api.ObsService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.azeckoski.reflectutils.DateUtils;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.openmrs.PatientIdentifier;
import org.openmrs.module.datamigration.HITTCohort;
import org.openmrs.util.OpenmrsUtil;

/**
 * @author The Bright
 */
public class CohortMaster {

    private FileManager manager;

    public final static int STARTED_ART_LAST_6MONTHS_COHORT = 1;

    public final static int DOCUMENTED_EDUCATIONAL_STATUS_COHORT = 2;

    public final static int DOCUMENTED_MARITAL_STATUS_COHORT = 3;

    public final static int EVER_ENROLLED_IN_CARE_COHORT = 4;

    public final static int ACTIVE_COHORT = 5;

    public final static int CLINIC_VISIT_LAST_6MONTHS_COHORT = 6;

    public final static int DOCUMENTED_OCCUPATIONAL_STATUS_COHORT = 7;

    public final static int DOCUMENTED_DIAGNOSIS_STATUS_COHORT = 8;

    public final static int DOCUMENTED_SEX_COHORT = 9;

    public final static int DOCUMENTED_ADDRESS_COHORT = 10;

    public final static int ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT = 11;

    public final static int ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT = 12;

    public final static int ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT = 13;

    public final static int STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB = 14;

    public final static int DOCUMENTED_DOB_COHORT = 15;

    public final static int STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX = 16;

    public final static int STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE = 17;

    public final static int STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT = 18;

    public final static int PICKED_UP_ARV_DRUG_LAST_6MONTHS_COHORT = 19;

    public final static int DOCUMENTED_ART_START_DATE_COHORT = 20;

    public final static int DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT = 21;

    public final static int DOCUMENTED_CD4_LAST_6MONTHS = 22;

    public final static int STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT = 23;

    public final static int DOCUMENTED_WEIGHT_LAST_6MONTHS = 24;

    public final static int CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH = 25;

    public final static int PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS = 26;

    public final static int PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC = 27;

    public final static int DOCUMENTED_WHO_LAST_6MONTHS = 28;

    public final static int CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO = 29;

    public final static int DOCUMENTED_TB_STATUS_LAST_6MONTHS = 30;

    public final static int CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS = 31;

    public final static int LAST_ARV_PHARMACY_PICKUP_COHORT = 32;

    public final static int LAST_ARV_PHARMACY_PICKUP_WITH_DURATION = 33;

    public final static int LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY = 34;

    public final static int LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN = 35;

    public final static int LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS = 36;

    public final static int VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT = 37;

    public final static int VIRAL_LOAD_ELIGIBLE_COHORT = 38;

    public final static int VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE = 39;

    public final static int VIRAL_LOAD_ELIGIBLE_WITH_SAMPLE_COLLECTION = 40;

    public final static int NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA = 41;

    /*
	   Concept IDs
     */
    private final static int EDUCATIONAL_STATUS_CONCEPT = 1712;

    private final static int MARITAL_STATUS_CONCEPT = 1054;

    private final static int OCCUPATIONAL_STATUS_CONCEPT = 1542;

    private final static int ART_START_DATE_CONCEPT = 159599;

    private final static int DATE_CONFIRMED_POSITIVE = 160554;

    private final static int HIV_ENROLLMENT_FORM = 23;

    private final static int PHARMACY_FORM_ID = 27;

    private final static int REGIMEN_LINE_CONCEPT = 165708;

    private final static int CD4_COUNT_CONCEPT = 5497;

    private final static int CD4_PERCENT_CONCEPT = 730;

    private final static int WEIGHT_CONCEPT = 5089;

    private final static int MUAC_CONCEPT = 165935;

    private final static int WHO_CONCEPT = 5356;

    private final static int TB_STATUS_CONCEPT = 1659;

    private final static int VIRAL_LOAD_CONCEPT = 856;

    private final static int DATE_SAMPLE_COLLECTED_CONCEPT = 159951;

    private final static int DURATION_CONCEPT = 160856;

    private final static int ARV_GROUPING_CONCEPT = 162240;

    private final static int ARV_REGIMEN_DURATION = 159368;

    private final static int ARV_COMMENCEMENT_FORM = 53;

    private Map<Integer, String> indicatorNamesMap = new HashMap<Integer, String>();

    private Map<Integer, Set<Integer>> cohortDictionary = new HashMap<Integer, Set<Integer>>();

    public CohortMaster() {
        loadCohortDictionary();
        loadIndicatorNamesDictionary();
        manager = new FileManager();
    }

    public static String getLineListingFolderPath(String fileName) {
        String folder;
        String appDataDir = OpenmrsUtil.getApplicationDataDirectory();
        if (!appDataDir.endsWith(System.getProperty("file.separator"))) {
            appDataDir = appDataDir + System.getProperty("file.separator");
        }
        appDataDir = appDataDir + "linelist";
        if (!appDataDir.endsWith(System.getProperty("file.separator"))) {
            appDataDir = appDataDir + System.getProperty("file.separator");
        }
        appDataDir = appDataDir + fileName;
        /*folder = (String) Context.getAdministrationService().getGlobalProperty("databasebackup.folderPath", "backup");
		if (folder.startsWith("./")) folder = folder.substring(2);
		if (!folder.startsWith("/") && folder.indexOf(":")==-1) folder = appDataDir + folder;
		folder = folder.replaceAll( "/", "\\" + System.getProperty("file.separator"));
		if (!folder.endsWith(System.getProperty("file.separator"))) 
			folder = folder + System.getProperty("file.separator");*/

        return appDataDir;
    }

    public Set<Integer> buildCohortByObs(int conceptID) {
        Set<Integer> patientSet = new HashSet<Integer>();
        ObsService obsService = Context.getObsService();
        PatientService patientServce = Context.getPatientService();
        List<Patient> patientList = patientServce.getAllPatients();
        List<Obs> obsList = null;
        for (Patient patient : patientList) {
            obsList = obsService.getObservationsByPerson(patient);
            if (obsList != null && !obsList.isEmpty()) {
                for (Obs obs : obsList) {
                    if (obs.getConcept().getConceptId() == conceptID) {
                        patientSet.add(patient.getPatientId());
                    }
                }
            }

        }
        return patientSet;
    }

    public Set<Integer> buildCohortByConceptID(int conceptID) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        EncounterService encounterService = Context.getEncounterService();
        List<Obs> obsList = null;
        List<Patient> patientList = null;
        List<Encounter> encounterList = null;
        patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            encounterList = encounterService.getEncountersByPatient(pts);
            for (Encounter enc : encounterList) {
                obsList = new ArrayList<Obs>(enc.getAllObs());
                for (Obs obs : obsList) {
                    if (obs.getConcept().getConceptId() == conceptID) {
                        patientSet.add(obs.getPersonId());
                    }
                }
            }
        }

        return patientSet;
    }

    public Set<Integer> buildCohortByObsDate(int conceptID, Date startDate, Date endDate) {
        Set<Integer> patientSet = new HashSet<Integer>();
        ObsService obsService = Context.getObsService();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        List<Obs> obsList = null;
        DateTime startDateTime, endDateTime;
        //endDateTime=new DateTime(new Date());

        for (Patient patient : patientList) {
            obsList = obsService.getObservationsByPerson(patient);
            if (obsList != null && !obsList.isEmpty()) {
                for (Obs ele : obsList) {
                    if (ele.getConcept().getConceptId() == conceptID
                            && isBetweenDate(startDate, endDate, ele.getObsDatetime())) {
                        patientSet.add(patient.getPatientId());
                    }
                }
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortByObs(Integer[] conceptArr, Date startDate, Date endDate) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        ObsService obsService = Context.getObsService();
        List<Patient> patientList = patientService.getAllPatients();
        List<Obs> obsList = null;
        List<Integer> conceptIDList = new ArrayList<Integer>(Arrays.asList(conceptArr));
        for (Patient patient : patientList) {
            obsList = obsService.getObservationsByPerson(patient);
            if (obsList != null && !obsList.isEmpty()) {
                for (Obs ele : obsList) {
                    if (conceptIDList.contains(ele.getConcept().getConceptId())) {
                        if (isBetweenDate(startDate, endDate, ele.getObsDatetime())) {
                            patientSet.add(patient.getPatientId());
                        }
                    }
                }
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortByConceptID(int conceptID, Date startDate, Date endDate) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        EncounterService encounterService = Context.getEncounterService();
        List<Obs> obsList = null;
        List<Patient> patientList = null;
        List<Encounter> encounterList = null;
        patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            encounterList = encounterService.getEncountersByPatient(pts);
            for (Encounter enc : encounterList) {
                if (isBetweenDate(startDate, endDate, enc.getEncounterDatetime())) {
                    obsList = new ArrayList<Obs>(enc.getAllObs());
                    for (Obs obs : obsList) {
                        if (obs.getConcept().getConceptId() == conceptID) {
                            patientSet.add(obs.getPersonId());
                        }
                    }
                }
            }
        }

        return patientSet;

    }

    public Set<Integer> buildCohortByEncounter(Date startDate, Date endDate) {
        Set<Integer> patientSet = new HashSet<Integer>();
        List<Encounter> encounterList = new ArrayList<Encounter>();
        EncounterService encounterService = Context.getEncounterService();
        PatientService patientService = Context.getPatientService();
        DateTime startDateTime, endDateTime;
        endDateTime = new DateTime(endDate);
        startDateTime = new DateTime(startDate);

        List<Patient> patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            encounterList = encounterService.getEncountersByPatient(pts);
            for (Encounter enc : encounterList) {
                //encounterDateTime = new DateTime(enc.getEncounterDatetime());
                if (isBetweenDate(startDateTime.toDate(), endDateTime.toDate(), enc.getEncounterDatetime())) {
                    patientSet.add(enc.getPatient().getPatientId());
                }
            }
        }

        return patientSet;
    }

    public Set<Integer> buildCohortByEncounterInLast(int numberOfMonths) {
        Set<Integer> patientSet = new HashSet<Integer>();
        EncounterService encounterService = Context.getEncounterService();
        PatientService patientService = Context.getPatientService();
        Date startDate = null, endDate = new Date();
        DateTime startDateTime, endDateTime;
        endDateTime = new DateTime(endDate);
        startDateTime = endDateTime.minusMonths(numberOfMonths);
        List<Encounter> encounterList = new ArrayList<Encounter>();
        List<Patient> patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            encounterList = encounterService.getEncountersByPatient(pts);
            for (Encounter enc : encounterList) {
                //encounterDateTime = new DateTime(enc.getEncounterDatetime());
                if (isBetweenDate(startDateTime.toDate(), endDateTime.toDate(), enc.getEncounterDatetime())) {
                    patientSet.add(enc.getPatient().getPatientId());
                }
            }
        }

        return patientSet;

    }

    public Set<Integer> buildCohortByHIVEnrollment() {
        Set<Integer> patientSet = new HashSet<Integer>();
        EncounterService encounterService = Context.getEncounterService();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        List<Encounter> encounterList = null;
        for (Patient patient : patientList) {
            encounterList = encounterService.getEncountersByPatient(patient);
            for (Encounter enc : encounterList) {
                if (enc != null && enc.getForm() != null && enc.getForm().getFormId() == HIV_ENROLLMENT_FORM) {
                    patientSet.add(patient.getPatientId());
                }
            }
        }

        return patientSet;
    }

    public Set<Integer> buildCohortByGender(String gender) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            if (StringUtils.equalsIgnoreCase(pts.getGender(), gender)) {
                patientSet.add(pts.getPatientId());
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortByDocumentedGender() {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            if (StringUtils.isNotEmpty(pts.getPerson().getGender())) {
                patientSet.add(pts.getPatientId());
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortByDocumentedDOB() {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        for (Patient pts : patientList) {
            if (pts.getPerson().getBirthdate() != null) {
                patientSet.add(pts.getPatientId());
            }
        }
        return patientSet;
    }

    public List<String[]> generateLineListCSVData(Set<Integer> patientSet, int id) {
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        String[] data = null;
        List<String[]> dataArr = new ArrayList<String[]>();
        for (Patient pts : patientList) {
            if (patientSet.contains(pts.getPatientId())) {
                data = new String[]{pts.getPatientIdentifier(4).getIdentifier()};
                dataArr.add(data);
            }
        }
        String fileName = "Cohort" + id + ".csv";
        fileName = getLineListingFolderPath(fileName);

        return dataArr;
    }

    public Set<Integer> buildCohortByActive() {
        Set<Integer> patientSet = new HashSet<Integer>();
        EncounterService encounterService = Context.getEncounterService();
        PatientService patientService = Context.getPatientService();
        Date startDate = null, endDate = new Date();
        DateTime startDateTime, endDateTime;
        endDateTime = new DateTime(endDate);
        startDateTime = endDateTime.minusMonths(4);
        List<Encounter> encounterList = new ArrayList<Encounter>();
        List<Patient> patientList = patientService.getAllPatients();
        //DateTime encounterDateTime = null;
        for (Patient pts : patientList) {
            encounterList = encounterService.getEncountersByPatient(pts);
            for (Encounter enc : encounterList) {
                //encounterDateTime = new DateTime(enc.getEncounterDatetime());
                if (isBetweenDate(startDateTime.toDate(), endDateTime.toDate(), enc.getEncounterDatetime())) {
                    patientSet.add(enc.getPatient().getPatientId());
                }
            }
        }
        //encounterList.addAll(encounterService.getEncounters(startDateTime.toDate(), endDateTime.toDate()));

        return patientSet;
    }

    public HITTCohort buildCohort(int id, String cohortName, Set<Integer> patientSet) {
        HITTCohort cohort = new HITTCohort(id, cohortName, patientSet);
        List<String[]> data = generateLineListCSVData(patientSet, id);
        cohort.setDataArr(data);
        return cohort;
    }

    public Boolean isBetweenDate(Date startDate, Date endDate, Date checkDate) {
        Interval interval = new Interval(new DateTime(startDate), new DateTime(endDate));
        return interval.contains(new DateTime(checkDate));
    }

    public Set<Integer> buildCohortByAge(int startAge, int stopAge) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        int age = 0;
        for (Patient pts : patientList) {
            age = pts.getAge();

            if (age >= startAge && age < stopAge) {
                patientSet.add(pts.getPatientId());
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortByAddress() {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        List<Patient> patientList = patientService.getAllPatients();
        Set<PersonAddress> addressSet = null;
        List<PersonAddress> personAddressList = null;
        PersonAddress personAddress = null;
        for (Patient pts : patientList) {
            addressSet = pts.getAddresses();
            if (addressSet != null && !addressSet.isEmpty()) {
                personAddressList = new ArrayList<PersonAddress>(addressSet);
                personAddress = personAddressList.get(0);
                if (personAddress != null && StringUtils.isNotEmpty(personAddress.getCityVillage())) {
                    patientSet.add(pts.getPatientId());
                }
            }
        }
        return patientSet;
    }

    public Encounter getLastEncounterForForm(int formID, List<Encounter> patientEncounterList) {
        Encounter encounter = null;
        int lastIndex = 0;
        List<Encounter> sortedEncounterList = null;
        if (patientEncounterList != null && !patientEncounterList.isEmpty()) {
            sortedEncounterList = patientEncounterList.stream().filter(ele -> ele.getForm() != null && ele.getForm().getFormId() == formID).sorted(Comparator.comparing(Encounter::getEncounterDatetime)).collect(Collectors.toList());
        }
        if (sortedEncounterList != null && !sortedEncounterList.isEmpty()) {
            lastIndex = sortedEncounterList.size() - 1;
            encounter = sortedEncounterList.get(lastIndex);
        }
        return encounter;
    }

    public boolean hasARVConcept(Encounter encounter) {
        boolean ans = false;
        Set<Obs> obsSet = encounter.getAllObs();

        return ans;
    }

    public boolean containsConceptID(int conceptID, List<Obs> obsList) {
        boolean ans = false;
        if (obsList != null && !obsList.isEmpty()) {
            for (Obs ele : obsList) {
                if (ele.getConcept().getConceptId() == conceptID) {
                    ans = true;
                    break;
                }
            }
        }
        return ans;
    }

    public boolean containsAnyOfConceptIDs(Integer[] conceptIDArr, List<Obs> obsList) {
        boolean ans = false;
        if (obsList != null && !obsList.isEmpty() && conceptIDArr != null && conceptIDArr.length > 0) {
            for (Integer ele : conceptIDArr) {
                if (containsConceptID(ele, obsList)) {
                    ans = true;
                }
            }
        }
        return ans;
    }

    public Set<Obs> retriveGroupingConcepts(List<Obs> obsList, Integer[] targetConceptIDs) {
        Set<Obs> obsSet = new HashSet<Obs>();
        List<Integer> targetIDList = null;
        if (obsList != null && targetConceptIDs != null && !obsList.isEmpty()) {
            targetIDList = new ArrayList<Integer>();
            targetIDList.addAll(Arrays.asList(targetConceptIDs));
            for (Obs obs : obsList) {
                if (targetIDList.contains(obs.getConcept().getConceptId())) {
                    obsSet.add(obs);
                }
            }
        }

        return obsSet;
    }

    public Set<Integer> buildCohortOfViralLoadEligibleWithResultCohort() {
        Set<Integer> patientSet = new HashSet<Integer>();
        DateTime startDateTime = null, endDateTime = null;
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(12);

        return patientSet;
    }

    public Set<Integer> buildCohortOfPatientsWithARVLastPickup() {
        PatientService patientService = Context.getPatientService();
        EncounterService encounterService = Context.getEncounterService();
        List<Patient> allPatientList = patientService.getAllPatients();
        List<Encounter> encounterList = null;
        List<Obs> obsListForLastVisit = null;
        Set<Integer> patientSet = new HashSet<Integer>();
        Encounter encounter = null;
        for (Patient patient : allPatientList) {
            encounterList = encounterService.getEncountersByPatient(patient);
            encounter = getLastEncounterForForm(PHARMACY_FORM_ID, encounterList);
            if (encounter != null) {
                obsListForLastVisit = new ArrayList<Obs>(encounter.getAllObs());
                if (containsConceptID(ARV_GROUPING_CONCEPT, obsListForLastVisit)) {
                    patientSet.add(patient.getPatientId());
                }
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortOfPatientsWithARVPickupWithConcept(Integer[] targetConceptIDArr) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        EncounterService encounterService = Context.getEncounterService();
        List<Patient> allPatientList = patientService.getAllPatients();
        List<Encounter> encounterList = null;
        List<Obs> obsListForLastVisit = null;
        //Set<Obs> obsGroupingSet = null;
        //Set<Obs> obsGroupMembers = null;
        Encounter encounter = null;
        for (Patient patient : allPatientList) {
            encounterList = encounterService.getEncountersByPatient(patient);
            encounter = getLastEncounterForForm(PHARMACY_FORM_ID, encounterList);
            //Integer[] targetConceptIDs = {ARV_GROUPING_CONCEPT,164506,164513,165702,164507,164514,165703};
            if (encounter != null) {
                obsListForLastVisit = new ArrayList<Obs>(encounter.getAllObs());
                if (containsAnyOfConceptIDs(targetConceptIDArr, obsListForLastVisit)) {
                    patientSet.add(patient.getPatientId());
                }
            }
        }
        return patientSet;
    }

    public Set<Integer> buildCohortOfPatientsWithARVPickupWithGroupMemberConcept(int conceptID) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        EncounterService encounterService = Context.getEncounterService();
        List<Patient> allPatientList = patientService.getAllPatients();
        List<Encounter> encounterList = null;
        List<Obs> obsListForLastVisit = null;
        Set<Obs> obsGroupingSet = null;
        Set<Obs> obsGroupMembers = null;
        Encounter encounter = null;
        for (Patient patient : allPatientList) {
            encounterList = encounterService.getEncountersByPatient(patient);
            encounter = getLastEncounterForForm(PHARMACY_FORM_ID, encounterList);
            Integer[] targetConceptIDs = {ARV_GROUPING_CONCEPT};
            if (encounter != null) {
                obsListForLastVisit = new ArrayList<Obs>(encounter.getAllObs());
                if (containsConceptID(ARV_GROUPING_CONCEPT, obsListForLastVisit)) {
                    obsGroupingSet = retriveGroupingConcepts(obsListForLastVisit, targetConceptIDs);
                    if (obsGroupingSet != null && !obsGroupingSet.isEmpty()) {
                        for (Obs obs : obsGroupingSet) {
                            obsGroupMembers = obs.getGroupMembers();
                            if (containsConceptID(conceptID, new ArrayList<Obs>(obsGroupMembers))) {
                                patientSet.add(patient.getPatientId());
                            }
                        }
                    }
                }
            }
        }
        return patientSet;

    }

    public Set<Integer> buildCohortOfPatientsWithARVPickupWithGroupMemberConceptWithValue(int conceptID, double valueLimit) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        EncounterService encounterService = Context.getEncounterService();
        List<Patient> allPatientList = patientService.getAllPatients();
        List<Encounter> encounterList = null;
        List<Obs> obsListForLastVisit = null;
        Set<Obs> obsGroupingSet = null;
        Set<Obs> obsGroupMembers = null;
        Encounter encounter = null;
        for (Patient patient : allPatientList) {
            encounterList = encounterService.getEncountersByPatient(patient);
            encounter = getLastEncounterForForm(PHARMACY_FORM_ID, encounterList);
            Integer[] targetConceptIDs = {ARV_GROUPING_CONCEPT};
            Obs obsMember = null;
            double valueNumeric = 0.0;
            if (encounter != null) {
                obsListForLastVisit = new ArrayList<Obs>(encounter.getAllObs());
                if (containsConceptID(ARV_GROUPING_CONCEPT, obsListForLastVisit)) {
                    obsGroupingSet = retriveGroupingConcepts(obsListForLastVisit, targetConceptIDs);
                    if (obsGroupingSet != null && !obsGroupingSet.isEmpty()) {
                        for (Obs obs : obsGroupingSet) {
                            obsGroupMembers = obs.getGroupMembers();
                            if (containsConceptID(conceptID, new ArrayList<Obs>(obsGroupMembers))) {
                                //patientSet.add(patient.getPatientId());
                                obsMember = extractObs(conceptID, new ArrayList<Obs>(obsGroupMembers));
                                if (obsMember != null) {
                                    valueNumeric = obsMember.getValueNumeric();
                                    if (valueNumeric > valueLimit) {
                                        patientSet.add(patient.getPatientId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return patientSet;

    }

    public Encounter getFirstEncounterForForm(int formID, List<Encounter> patientEncounterList) {
        Encounter encounter = null;
        int firstIndex = 0;
        List<Encounter> sortedEncounterList = null;
        if (patientEncounterList != null && !patientEncounterList.isEmpty()) {
            sortedEncounterList = patientEncounterList.stream().filter(ele -> ele.getForm() != null && ele.getForm().getFormId() == formID).sorted(Comparator.comparing(Encounter::getEncounterDatetime)).collect(Collectors.toList());
        }
        if (sortedEncounterList != null && !sortedEncounterList.isEmpty()) {
            firstIndex = 0;
            encounter = sortedEncounterList.get(firstIndex);
        }

        return encounter;
    }

    public Obs getLastObs(int conceptID, List<Obs> patientObsList) {
        Obs obs = null;
        List<Obs> lastObsList = null;
        if (patientObsList != null && !patientObsList.isEmpty()) {
            lastObsList = patientObsList.stream().filter(ele -> ele.getObsId() == conceptID).sorted(Comparator.comparing(Obs::getObsDatetime))
                    .collect(Collectors.toList());
        }

        if (lastObsList != null && lastObsList.size() > 0) {
            int lastIndex = lastObsList.size() - 1;
            obs = lastObsList.get(lastIndex);
        }

        return obs;
    }

    public Set<Integer> buildCohortOfViralLoadEligible() {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        ObsService obsService = Context.getObsService();
        List<Patient> patientList = patientService.getAllPatients();
        List<Obs> obsList = null;
        DateTime startDateTime = null, endDateTime = null, artStartDateTime = null;
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(12);
        Obs obsLastViralLoad, obsARTStartDate = null;
        for (Patient patient : patientList) {
            obsList = obsService.getObservationsByPerson(patient);
            obsLastViralLoad = getLastObs(VIRAL_LOAD_CONCEPT, obsList);
            obsARTStartDate = extractObs(ART_START_DATE_CONCEPT, obsList);
            if (obsARTStartDate != null) {
                artStartDateTime = new DateTime(obsARTStartDate.getValueDate());
                int monthsDiff = Months.monthsBetween(artStartDateTime, endDateTime).getMonths();
                //if (obsLastViralLoad != null) {
                if (monthsDiff >= 6) {
                    //& !isBetweenDate(startDateTime.toDate(), endDateTime.toDate(), obsLastViralLoad.getObsDatetime())) {
                    patientSet.add(patient.getPatientId());
                }
                //}
            }

        }

        return patientSet;
    }

    public Obs getFirstObs(int conceptID, List<Obs> patientObsList) {
        Obs obs = null;
        List<Obs> lastObsList = null;
        if (patientObsList != null && !patientObsList.isEmpty()) {
            lastObsList = patientObsList.stream().filter(ele -> ele.getObsId() == conceptID).sorted(Comparator.comparing(Obs::getObsDatetime))
                    .collect(Collectors.toList());
        }

        if (lastObsList != null && lastObsList.size() > 0) {
            int firstIndex = 0;
            obs = lastObsList.get(firstIndex);
        }

        return obs;
    }

    public List<Encounter> extractEncounters(Integer[] formIDArr, List<Encounter> encounterList) {
        List<Integer> targetEncounterTypeList = new ArrayList<Integer>();
        targetEncounterTypeList.addAll(Arrays.asList(formIDArr));
        List<Encounter> targetEncounters = new ArrayList<Encounter>();
        if (encounterList != null && !encounterList.isEmpty()) {
            for (Encounter enc : encounterList) {
                if (enc != null && enc.getForm() != null && targetEncounterTypeList != null
                        && !targetEncounterTypeList.isEmpty() && targetEncounterTypeList.contains(enc.getForm().getFormId())) {
                    targetEncounters.add(enc);
                }
            }
        }
        return targetEncounters;
    }

    public Set<Integer> buildCohortByDateConcept(int conceptID, Integer[] formIDArr, Date startDate, Date endDate) {
        Set<Integer> patientSet = new HashSet<Integer>();
        PatientService patientService = Context.getPatientService();
        //EncounterService encounterService = Context.getEncounterService();
        ObsService obsService = Context.getObsService();
        //obsService.getObservationsByPerson(person)
        List<Patient> patientList = patientService.getAllPatients();
        //List<Encounter> encounterList = null, targetEncounterList = null;
        List<Obs> obsList = null;
        Obs obs = null;
        for (Patient pts : patientList) {
            //encounterList = encounterService.getEncountersByPatient(pts);
            obsList = obsService.getObservationsByPerson(pts);
            obs = extractObs(conceptID, obsList);
            if (obs != null) {
                if (isBetweenDate(startDate, endDate, obs.getValueDate())) {
                    patientSet.add(pts.getPatientId());
                    //break;
                }
            }

        }
        return patientSet;
    }

    public static Set<Integer> interset(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> ansSet = new HashSet<Integer>();
        ansSet = Sets.intersection(set1, set2);
        return ansSet;
    }

    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> ansSet = new HashSet<Integer>();
        ansSet = Sets.union(set1, set2);
        return ansSet;
    }

    public static Obs extractObs(int conceptID, List<Obs> obsList) {
        if (obsList == null) {
            return null;
        }
        return obsList.stream().filter(ele -> ele.getConcept().getConceptId() == conceptID).findFirst().orElse(null);
    }

    public static Set<Integer> minus(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> ansSet = new HashSet<Integer>();
        ansSet = Sets.difference(set1, set2);
        return ansSet;
    }

    public void loadIndicatorNamesDictionary() {
        indicatorNamesMap.put(ACTIVE_COHORT, "Number of active patients");
        indicatorNamesMap.put(ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT,
                "Number of All active  patients with a documented educational status");
        indicatorNamesMap.put(ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT,
                "Proportion of all active patients with a documented marital status ");
        indicatorNamesMap.put(STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB,
                "Proportion of patients newly started on ART in the last 6 months with documented age and/or Date of Birth");
    }

    public void loadCohortDictionary() {
        DateTime startDateTime = null, endDateTime = null;
        Set<Integer> pediatricCohort, childrenCohort, activePatientCohort, documentedEducationalStatusCohort, documentedMaritalStatusCohort, documentedOccupationalStatusCohort;
        Set<Integer> answerSet = new HashSet<Integer>();
        activePatientCohort = buildCohortByActive();

        pediatricCohort = buildCohortByAge(0, 15);
        childrenCohort = buildCohortByAge(0, 5);

        //Educational Status
        documentedEducationalStatusCohort = buildCohortByConceptID(EDUCATIONAL_STATUS_CONCEPT);
        answerSet = interset(activePatientCohort, documentedEducationalStatusCohort);
        cohortDictionary.put(ACTIVE_COHORT, minus(activePatientCohort, childrenCohort));
        cohortDictionary
                .put(DOCUMENTED_EDUCATIONAL_STATUS_COHORT, minus(documentedEducationalStatusCohort, childrenCohort));
        cohortDictionary.put(ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT, minus(answerSet, childrenCohort));
        //Marital Status Cohort
        documentedMaritalStatusCohort = buildCohortByConceptID(MARITAL_STATUS_CONCEPT);
        answerSet = interset(minus(activePatientCohort, pediatricCohort),
                minus(documentedMaritalStatusCohort, pediatricCohort));
        cohortDictionary.put(DOCUMENTED_MARITAL_STATUS_COHORT, minus(documentedMaritalStatusCohort, pediatricCohort));
        cohortDictionary.put(ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT, answerSet);
        //Occupational Status
        documentedOccupationalStatusCohort = buildCohortByConceptID(OCCUPATIONAL_STATUS_CONCEPT);
        answerSet = interset(minus(activePatientCohort, pediatricCohort),
                minus(documentedOccupationalStatusCohort, pediatricCohort));
        cohortDictionary.put(DOCUMENTED_OCCUPATIONAL_STATUS_COHORT,
                minus(documentedOccupationalStatusCohort, pediatricCohort));
        cohortDictionary.put(ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT, answerSet);
        //Newly started on ART Last 6 Months with documented DOB
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> newlyStartedARTLast6MonthsCohort, patientsWithDocumentedAgeCohort;
        Integer[] formIDArr = {23, 56};
        newlyStartedARTLast6MonthsCohort = buildCohortByDateConcept(ART_START_DATE_CONCEPT, formIDArr,
                startDateTime.toDate(), endDateTime.toDate());
        patientsWithDocumentedAgeCohort = buildCohortByDocumentedDOB();
        answerSet = interset(newlyStartedARTLast6MonthsCohort, patientsWithDocumentedAgeCohort);
        cohortDictionary.put(STARTED_ART_LAST_6MONTHS_COHORT, newlyStartedARTLast6MonthsCohort);
        cohortDictionary.put(STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB, answerSet);

        /*
                  -Proportion of patients newly started on ART in the last 6 months with registered address/LGA of residence 
                    -Cohort of patients newly started on ART in last 6 months
                    -Cohort of patients with documented LGA
                    -Proportion of patients newly started on ART in last 6 months with documented LGA
         */
        Set<Integer> patientsWithDocumentedLGA, patientsNewlyStartedARTInLast6MonthsWithDocumentedLGA;
        patientsWithDocumentedLGA = buildCohortByAddress();
        patientsNewlyStartedARTInLast6MonthsWithDocumentedLGA = interset(patientsWithDocumentedLGA, newlyStartedARTLast6MonthsCohort);
        cohortDictionary.put(NEWLY_STARTED_ON_ART_WITH_DOCUMENTED_LGA, patientsNewlyStartedARTInLast6MonthsWithDocumentedLGA);
        
        
        //Newly started on ART Last 6 Months with documented Gender
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> patientsWithDocumentedSexCohort;
        //newlyStartedARTLast6MonthsCohort = buildCohortByDateConcept(ART_START_DATE_CONCEPT, startDateTime.toDate(),
        //    endDateTime.toDate());
        patientsWithDocumentedSexCohort = buildCohortByDocumentedGender();
        answerSet = interset(newlyStartedARTLast6MonthsCohort, patientsWithDocumentedSexCohort);
        cohortDictionary.put(DOCUMENTED_SEX_COHORT, patientsWithDocumentedSexCohort);
        cohortDictionary.put(STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX, answerSet);

        //Newly started on ART Last 6 Months with documented DateConfirmedPositive
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> patientsWithDocumentedDateConfirmedPositiveCohort;
        //newlyStartedARTLast6MonthsCohort = buildCohortByDateConcept(ART_START_DATE_CONCEPT, startDateTime.toDate(),
        //    endDateTime.toDate());
        patientsWithDocumentedDateConfirmedPositiveCohort = buildCohortByConceptID(DATE_CONFIRMED_POSITIVE,
                startDateTime.toDate(), endDateTime.toDate());
        answerSet = interset(newlyStartedARTLast6MonthsCohort, patientsWithDocumentedDateConfirmedPositiveCohort);
        cohortDictionary.put(DOCUMENTED_DIAGNOSIS_STATUS_COHORT, patientsWithDocumentedDateConfirmedPositiveCohort);
        cohortDictionary.put(STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE, answerSet);

        //Proportion of patients newly started on ART in the last 6 months with documented HIV enrollment date
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> patientsWithDocumentedHIVEnrollmentDateCohort;
        //newlyStartedARTLast6MonthsCohort = buildCohortByDateConcept(ART_START_DATE_CONCEPT, startDateTime.toDate(),
        //    endDateTime.toDate());
        patientsWithDocumentedHIVEnrollmentDateCohort = buildCohortByHIVEnrollment();
        answerSet = interset(newlyStartedARTLast6MonthsCohort, patientsWithDocumentedHIVEnrollmentDateCohort);
        cohortDictionary.put(EVER_ENROLLED_IN_CARE_COHORT, patientsWithDocumentedHIVEnrollmentDateCohort);
        cohortDictionary.put(STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT, answerSet);

        //Proportion of patients newly started on ART in the last 6 months with documented ART Start Date
        Set<Integer> patientWithDocumentedARTStartDateCohort, patientsWithARVPickupLast6MonthsCohort;
        patientsWithARVPickupLast6MonthsCohort = buildCohortByConceptID(REGIMEN_LINE_CONCEPT, startDateTime.toDate(),
                endDateTime.toDate());
        formIDArr = new Integer[]{HIV_ENROLLMENT_FORM, ARV_COMMENCEMENT_FORM};
        //patientWithDocumentedARTStartDateCohort=buildCohortByDateConcept(ART_START_DATE_CONCEPT,formIDArr, startDateTime.toDate(),
        //endDateTime.toDate());
        answerSet = interset(patientsWithARVPickupLast6MonthsCohort, newlyStartedARTLast6MonthsCohort);
        cohortDictionary.put(DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT, answerSet);
        cohortDictionary.put(DOCUMENTED_ART_START_DATE_COHORT, newlyStartedARTLast6MonthsCohort);
        cohortDictionary.put(PICKED_UP_ARV_DRUG_LAST_6MONTHS_COHORT, patientsWithARVPickupLast6MonthsCohort);

        //Proportion of patients newly started on ART in the last 6 months with documented CD4 result
        //Newly Start ART Last 6 Months Cohort -STARTED_ART_LAST_6MONTHS_COHORT
        //Documented CD4 Count Cohort 6 Months Cohort -DOCUMENTED_CD4_LAST_6MONTHS
        //Newly Started ART with Documented CD4 Last 6 Months - STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT
        Set<Integer> documentedCD4CountInLast6MonthsCohort, documentedCD4NewStartOnARTLast6MonthsCohort;
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Integer[] cd4ConceptArr = {CD4_COUNT_CONCEPT, CD4_PERCENT_CONCEPT};
        documentedCD4CountInLast6MonthsCohort = buildCohortByObs(cd4ConceptArr, startDateTime.toDate(),
                endDateTime.toDate());
        documentedCD4NewStartOnARTLast6MonthsCohort = interset(documentedCD4CountInLast6MonthsCohort,
                newlyStartedARTLast6MonthsCohort);
        cohortDictionary.put(STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT, documentedCD4NewStartOnARTLast6MonthsCohort);
        cohortDictionary.put(DOCUMENTED_CD4_LAST_6MONTHS, documentedCD4CountInLast6MonthsCohort);

        //Proportion of patients with a clinic visit in the last 6 months that had documented weight
        //Clinic visit last 6 months Cohort
        //Documented Weight last 6 months
        //Clinica Visit last 6 months documented weight
        Set<Integer> clinicVisitLast6Months, documentedWeightLast6Months, clinicVisitDocumentedWeightLast6Months;
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        clinicVisitLast6Months = buildCohortByEncounter(startDateTime.toDate(), endDateTime.toDate());
        documentedWeightLast6Months = buildCohortByConceptID(WEIGHT_CONCEPT, startDateTime.toDate(), endDateTime.toDate());
        clinicVisitDocumentedWeightLast6Months = interset(clinicVisitLast6Months, documentedWeightLast6Months);
        cohortDictionary.put(CLINIC_VISIT_LAST_6MONTHS_COHORT, clinicVisitLast6Months);
        cohortDictionary.put(CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH, clinicVisitDocumentedWeightLast6Months);

        //Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC
        //Pediatric Cohort
        //Clinic Visit Last 6 months
        //Documented MUAC Last 6 months
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> documentedMUACInLast6Months, pediatricClinicVisitLast6Months, pediatricMUACLast6Months;
        //pediatricCohort = buildCohortByAge(0, 15);
        pediatricClinicVisitLast6Months = interset(pediatricCohort, clinicVisitLast6Months);
        documentedMUACInLast6Months = buildCohortByConceptID(MUAC_CONCEPT, startDateTime.toDate(), endDateTime.toDate());
        pediatricMUACLast6Months = interset(documentedMUACInLast6Months, pediatricCohort);
        cohortDictionary.put(PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS, pediatricClinicVisitLast6Months);
        cohortDictionary.put(PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC, pediatricMUACLast6Months);

        //Proportion of patients with a clinic visit in the last 6 months that had documented WHO clinical stage
        //Clinic Visit Last 6 Months
        //Documented WHO Last Clinic Visit
        //Clinic Visit Last 6 Months Documented WHO
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> documentedWHOLast6Months, clinicVisitLast6MonthsDocumentedWHO;
        documentedWHOLast6Months = buildCohortByConceptID(WHO_CONCEPT, startDateTime.toDate(), endDateTime.toDate());
        clinicVisitLast6MonthsDocumentedWHO = interset(documentedWHOLast6Months, clinicVisitLast6Months);
        cohortDictionary.put(CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO, clinicVisitLast6MonthsDocumentedWHO);

        //Proportion of patients with a clinic visit in the last 6 months that had documented TB status
        //Clinic Visit Last 6 Months
        //Documented TB Status Last Clinic Visit
        //Clinic Visit Last 6 Months Documented TB Status
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(6);
        Set<Integer> documentedTBStatusLast6Months, clinicVisitLast6MonthsDocumentedTBStatus;
        documentedTBStatusLast6Months = buildCohortByConceptID(TB_STATUS_CONCEPT, startDateTime.toDate(),
                endDateTime.toDate());
        clinicVisitLast6MonthsDocumentedTBStatus = interset(documentedTBStatusLast6Months, clinicVisitLast6Months);
        cohortDictionary.put(CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS, clinicVisitLast6MonthsDocumentedTBStatus);

        //Proportion of patients with a documented regimen duration in the last drug refill visit
        //Patients with ARV Pickup Last Visit
        //Patients with ARV Pickup Last Visit Having Duration
        //endDateTime = new DateTime(new Date());
        //startDateTime = endDateTime.minusMonths(6);
        Set<Integer> arvPickupLastVisitCohort, arvPickupLastVisitDocumentedDuration;
        arvPickupLastVisitCohort = buildCohortOfPatientsWithARVLastPickup();
        arvPickupLastVisitDocumentedDuration = buildCohortOfPatientsWithARVPickupWithGroupMemberConcept(ARV_REGIMEN_DURATION);
        //clinicVisitLast6MonthsDocumentedTBStatus = interset(documentedTBStatusLast6Months, clinicVisitLast6Months);
        cohortDictionary.put(LAST_ARV_PHARMACY_PICKUP_COHORT, arvPickupLastVisitCohort);
        cohortDictionary.put(LAST_ARV_PHARMACY_PICKUP_WITH_DURATION, arvPickupLastVisitDocumentedDuration);

        //Proportion of patients with a documented regimen quantity in the last drug refill visit
        //Patients with ARV Pickup Last Visit
        //Patients with ARV Pickup Last Visit Having Quantity
        Set<Integer> arvPickupLastVisitDocumentedQuantity;
        arvPickupLastVisitDocumentedQuantity = buildCohortOfPatientsWithARVPickupWithGroupMemberConcept(ARV_REGIMEN_DURATION);
        cohortDictionary.put(LAST_ARV_PHARMACY_PICKUP_WITH_DURATION, arvPickupLastVisitDocumentedQuantity);

        //Proportion of patients with documented ART regimen in the last drug refill visit
        //Patients with ARV Pickup Last Visit
        //Patients with ARV Pickup Last Visit Having Documented Regimen
        Set<Integer> arvPickupLastVisitDocumentedRegimen;
        Integer[] targetVariables = {ARV_GROUPING_CONCEPT, 164506, 164513, 165702, 164507, 164514, 165703};
        arvPickupLastVisitDocumentedRegimen = buildCohortOfPatientsWithARVPickupWithConcept(targetVariables);
        cohortDictionary.put(LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN, arvPickupLastVisitDocumentedRegimen);

        //Proportion of patients with a regimen duration more than six(6) months  in the last drug refill visit
        //Patients with ARV Pickup Last Visit
        //Patients with ARV Pickup Last Visit Having Documented Regimen duration more than 6 months
        Set<Integer> arvPickupLastVisitRegimenDurationMore6Months;
        //Integer[] targetVariables = { ARV_GROUPING_CONCEPT, 164506, 164513, 165702, 164507, 164514, 165703 };
        arvPickupLastVisitRegimenDurationMore6Months = buildCohortOfPatientsWithARVPickupWithGroupMemberConceptWithValue(
                ARV_REGIMEN_DURATION, 180);
        cohortDictionary.put(LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS,
                arvPickupLastVisitRegimenDurationMore6Months);

        //Proportion of eligible patients with documented Viral Load results done in the last one year
        //Eligible patients Cohort (LastViralLoadDate>12Months,SampleNotCollected)
        //Eligible patients with documented Viral Load resuls in last one year
        endDateTime = new DateTime(new Date());
        startDateTime = endDateTime.minusMonths(12);
        Set<Integer> viralLoadEligiblePatients, viralLoadEligibleWithDocumentedResults12Months;
        //Integer[] targetVariables = { ARV_GROUPING_CONCEPT, 164506, 164513, 165702, 164507, 164514, 165703 };
        viralLoadEligibleWithDocumentedResults12Months = buildCohortByObsDate(VIRAL_LOAD_CONCEPT, startDateTime.toDate(),
                endDateTime.toDate());
        viralLoadEligiblePatients = buildCohortOfViralLoadEligible();
        cohortDictionary.put(VIRAL_LOAD_ELIGIBLE_COHORT, viralLoadEligiblePatients);
        cohortDictionary.put(VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT, viralLoadEligibleWithDocumentedResults12Months);
        /*
		        Proportion of patients with Viral Load result
		        that had documented specimen collection date
		          -- Everybody with viral Load Result
		          -- Everybody with viral Load Result and has Sample Collection Date
         */
        Set<Integer> eligibleWithSampleCollectionDateCohort, viralLoadResultEverCohort, viralLoadResultWithSampleCollectionCohort, patientsWithSampleCollectionDateCohort;
        viralLoadResultEverCohort = buildCohortByObs(VIRAL_LOAD_CONCEPT);
        patientsWithSampleCollectionDateCohort = buildCohortByObs(DATE_SAMPLE_COLLECTED_CONCEPT);
        viralLoadResultWithSampleCollectionCohort = interset(viralLoadEligiblePatients,
                patientsWithSampleCollectionDateCohort);
        eligibleWithSampleCollectionDateCohort = interset(viralLoadResultWithSampleCollectionCohort,
                viralLoadEligiblePatients);
        cohortDictionary.put(VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE, viralLoadResultWithSampleCollectionCohort);
        cohortDictionary.put(VIRAL_LOAD_ELIGIBLE_WITH_SAMPLE_COLLECTION, eligibleWithSampleCollectionDateCohort);

    }

    public int countCohort(int cohortID) {
        int size = 0;
        if (cohortDictionary.containsKey(cohortID)) {
            size = cohortDictionary.get(cohortID).size();
        }
        return size;

    }

    public Set<Integer> getCohort(int cohortID) {
        Set<Integer> patientSet = new HashSet<Integer>();
        if (cohortDictionary.containsKey(cohortID)) {
            patientSet = cohortDictionary.get(cohortID);
        }
        return patientSet;
    }

    public HITTCohort getHITTCohort(int cohortID) {
        HITTCohort cohort = buildCohort(cohortID, "test", getCohort(cohortID));
        return cohort;
    }

    public String getIndicatorName(int cohortID) {
        String indicatorName = "";
        if (indicatorNamesMap.containsKey(cohortID)) {
            indicatorName = indicatorNamesMap.get(cohortID);
        }
        return indicatorName;
    }

    public double getPercentage(double numerator, double denominator) {
        double ans = numerator / denominator;
        ans = ans * 100;
        return ans;
    }

}
