/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.datamigration.fragment.controller;

import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.datamigration.api.dao.DbConnection;
import org.openmrs.module.datamigration.util.FactoryUtils;
import org.openmrs.module.datamigration.util.Model.PatientLineList;
import org.openmrs.module.datamigration.util.Model.SummaryDashboard;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.openmrs.module.datamigration.CohortMaster;
import org.openmrs.module.datamigration.HITTCohort;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
//import liquibase.util.csv.opencsv.CSVWriter;
import com.opencsv.CSVWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

public class UsersFragmentController {
	
	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {

        DbConnection connection = new DbConnection();
        FactoryUtils factoryUtils = new FactoryUtils();
        Map<String, Integer> map = new HashMap<String, Integer>();
        List<SummaryDashboard> summaryDashboardList = factoryUtils.getEncounters();
        CohortMaster cohortMaster = new CohortMaster();

        List<PatientLineList> patientLineList = factoryUtils.getPatientsLineList();

        model.addAttribute("patientLineList", patientLineList);

        map.put("totalPatients", Context.getPatientService().getAllPatients().size());
        double numerator = 0.0, denominator = 0.0;
        String fileName = "";
        //Educational Status
        map.put("activepatientseducationalstatuscount", cohortMaster.getCohort(CohortMaster.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT).size());
        map.put("activepatientcount", cohortMaster.getCohort(CohortMaster.ACTIVE_COHORT).size());
        numerator = cohortMaster.countCohort(CohortMaster.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT);
        denominator = cohortMaster.countCohort(CohortMaster.ACTIVE_COHORT);
        map.put("percentageeducationalstatus", (int) cohortMaster.getPercentage(numerator, denominator));
        //HITTCohort cohortObj = cohortMaster.getHITTCohort(CohortMaster.ACTIVE_DOCUMENTED_EDUCATIONAL_STATUS_COHORT);
        //model.put("link", cohortObj.getLineListingFileName());
        //Marital Status
        map.put("activepatientsmaritalstatuscount", cohortMaster.getCohort(CohortMaster.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT).size());
        //map.put("activepatientcount",cohortMaster.getCohort(CohortMaster.ACTIVE_COHORT).size());
        numerator = cohortMaster.countCohort(CohortMaster.ACTIVE_DOCUMENTED_MARITAL_STATUS_COHORT);
        denominator = cohortMaster.countCohort(CohortMaster.ACTIVE_COHORT);
        map.put("percentagemaritalstatus", (int) cohortMaster.getPercentage(numerator, denominator));

        //Marital Status
        map.put("activepatientsoccupationalstatuscount", cohortMaster.getCohort(CohortMaster.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT).size());
        //map.put("activepatientcount",cohortMaster.getCohort(CohortMaster.ACTIVE_COHORT).size());
        numerator = cohortMaster.countCohort(CohortMaster.ACTIVE_DOCUMENTED_OCCUPATIONAL_STATUS_COHORT);
        denominator = cohortMaster.countCohort(CohortMaster.ACTIVE_COHORT);
        map.put("percentageoccupationalstatus", (int) cohortMaster.getPercentage(numerator, denominator));

        //Started ART Last 6 Months documented DOB
        numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DOB);
        denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
        map.put("startedartlast6monthscount", (int) denominator);
        map.put("startedartlast6monthscountdocumenteddob", (int) numerator);
        map.put("percentagestartedartlast6monthswithdocumenteddob", (int) cohortMaster.getPercentage(numerator, denominator));

        //Started ART Last 6 Months documented Gender
        numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_SEX);
        denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
        //map.put("startedartlast6monthscount",(int)denominator);
        map.put("startedartlast6monthscountdocumentedsex", (int) numerator);
        map.put("percentagestartedartlast6monthswithdocumentedsex", (int) cohortMaster.getPercentage(numerator, denominator));

        //Started ART Last 6 Months with Documented Date Confirmed Positive
        numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_DATECONFIRMED_POSITIVE);
        denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
        //map.put("startedartlast6monthscount",(int)denominator);
        map.put("startedartlast6monthscountdocumenteddateconfirmedpositive", (int) numerator);
        map.put("percentagestartedartlast6monthswithdocumenteddateconfirmedpositive", (int) cohortMaster.getPercentage(numerator, denominator));

        //Started ART Last 6 Months with Documented Enrollement Date
        numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_HIVENROLLMENT);
        denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
        //map.put("startedartlast6monthscount",(int)denominator);
        map.put("startedartlast6monthscountdocumentedenrollmentdate", (int) numerator);
        map.put("percentagestartedartlast6monthswithdocumenteddateenrollmentdate", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of patients newly started on ART in the last 6 months with documented ART Start Date
        numerator = cohortMaster.countCohort(CohortMaster.DOCUMENTED_ART_START_DATE_ARV_PICKUP_COHORT);
        denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
        //map.put("startedartlast6monthscount",(int)denominator);
        map.put("startedartlast6monthscountwithdrugpickup", (int) numerator);
        map.put("percentagestartedartlast6monthswithdrugpickup", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of patients newly started on ART in the last 6 months with documented CD4 result 
        numerator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_DOCUMENTED_CD4_COUNT);
        denominator = cohortMaster.countCohort(CohortMaster.STARTED_ART_LAST_6MONTHS_COHORT);
        //map.put("startedartlast6monthscount",(int)denominator);
        map.put("startedartlast6monthscountwithcd4count", (int) numerator);
        map.put("percentagestartedartlast6monthswithcd4count", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of patients with a clinic visit in the last 6 months that had documented weight
        numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WEIGH);
        denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);

        map.put("clinicvisitlast6monthscount", (int) denominator);
        map.put("clinicvisitlast6monthswithdocumentedweight", (int) numerator);
        map.put("percentagestclinicvisitlast6monthswithdocumentedweight", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC 
        numerator = cohortMaster.countCohort(CohortMaster.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_MUAC);
        denominator = cohortMaster.countCohort(CohortMaster.PEDIATRIC_CLINIC_VISIT_LAST_6MONTHS);

        map.put("pediatricclinicvisitlast6monthscount", (int) denominator);
        map.put("pediatricclinicvisitlast6monthswithdocumentedmuac", (int) numerator);
        map.put("percentagestclinicvisitlast6monthswithdocumentedmuac", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of pediatric patients with a clinic visit in the last 6 months that had documented MUAC 
        numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_WHO);
        denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);

        //map.put("pediatricclinicvisitlast6monthscount",(int)denominator);
        map.put("clinicvisitlast6monthswithdocumentedwho", (int) numerator);
        map.put("percentagestclinicvisitlast6monthswithdocumentedwho", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of patients with a clinic visit in the last 6 months that had documented TB status 
        numerator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_DOCUMENTED_TB_STATUS);
        denominator = cohortMaster.countCohort(CohortMaster.CLINIC_VISIT_LAST_6MONTHS_COHORT);

        //map.put("pediatricclinicvisitlast6monthscount",(int)denominator);
        map.put("clinicvisitlast6monthswithdocumentedtbstatus", (int) numerator);
        map.put("percentagestclinicvisitlast6monthswithdocumentedtbstatus", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of patients with a documented regimen duration in the last drug refill visit
        numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION);
        denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);

        map.put("lastarvpickupcohort", (int) denominator);
        map.put("lastarvpickupwithdurationcohort", (int) numerator);
        map.put("percentagelastarvpickupwithduration", (int) cohortMaster.getPercentage(numerator, denominator));

        //Proportion of patients with a documented regimen quantity in the last drug refill visit
        numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_QUANTITY);
        denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);

        //map.put("lastarvpickupcohort",(int)denominator);
        map.put("lastarvpickupwithquantitycohort", (int) numerator);
        map.put("percentagelastarvpickupwithquantity", (int) cohortMaster.getPercentage(numerator, denominator));
        
        //Proportion of patients with documented ART regimen in the last drug refill visit
        numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_REGIMEN);
        denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);

        //map.put("lastarvpickupcohort",(int)denominator);
        map.put("lastarvpickupwithregimencohort", (int) numerator);
        map.put("percentagelastarvpickupwithregimen", (int) cohortMaster.getPercentage(numerator, denominator));
        
        //Proportion of patients with a regimen duration more than six(6) months  in the last drug refill visit
        numerator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_WITH_DURATION_MORETHAN180DAYS);
        denominator = cohortMaster.countCohort(CohortMaster.LAST_ARV_PHARMACY_PICKUP_COHORT);

        //map.put("lastarvpickupcohort",(int)denominator);
        map.put("lastarvpickupwithregimendurationmorethan6monthscohort", (int) numerator);
        map.put("percentagelastarvpickupwithregimendurationmorethan6months", (int) cohortMaster.getPercentage(numerator, denominator));
        
        //Proportion of eligible patients with documented Viral Load results done in the last one year
        numerator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_WITH_DOCUMENTED_RESULT);
        denominator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_COHORT);

        map.put("viralloadeligible",(int)denominator);
        map.put("viralloadeligiblewithresultcohort", (int) numerator);
        map.put("percentageeligiblewithdocumentedresult", (int) cohortMaster.getPercentage(numerator, denominator));

        
        //Proportion of eligible patients with documented Viral Load results done in the last one year
        numerator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_RESULT_WITH_SAMPLE_COLLECTION_DATE);
        denominator = cohortMaster.countCohort(CohortMaster.VIRAL_LOAD_ELIGIBLE_WITH_SAMPLE_COLLECTION);

        map.put("viralloadeligiblewithsamplecollection",(int)denominator);
        map.put("viralloadresultwithsamplecollection", (int) numerator);
        map.put("percentageeligiblewithsamplecollected", (int) cohortMaster.getPercentage(numerator, denominator));
       
        SummaryDashboard summaryDashboard = summaryDashboardList.stream().filter(x -> x.getEncounterTypeID().equals(11)).findFirst().orElse(null);
        if (summaryDashboard != null) {
            map.put("totallLaboratoryEncounter", summaryDashboard.getCountOfEncounter());
        } else {
            map.put("totallLaboratoryEncounter", 0);
        }
        summaryDashboard = summaryDashboardList.stream().filter(x -> x.getEncounterTypeID().equals(13)).findFirst().orElse(null);
        if (summaryDashboard != null) {
            map.put("totalPharmacyEncounter", summaryDashboard.getCountOfEncounter());
        } else {
            map.put("totalPharmacyEncounter", 0);
        }
        summaryDashboard = summaryDashboardList.stream().filter(x -> x.getEncounterTypeID().equals(12)).findFirst().orElse(null);
        if (summaryDashboard != null) {
            map.put("totalCareCardEncounter", summaryDashboard.getCountOfEncounter());
        } else {
            map.put("totalCareCardEncounter", 0);
        }
        model.mergeAttributes(map);

    }
}
