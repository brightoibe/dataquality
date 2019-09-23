/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.datamigration.fragment.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openmrs.module.datamigration.CohortMaster;
import org.openmrs.module.datamigration.HITTCohort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

/**
 * @author The Bright
 */
@Controller
@RequestMapping(value = "/download.page")
public class FileDownloadController {
	
	@RequestMapping(value = "/download.page")
	public void downloadCSV(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String id = request.getParameter("cohort");
		int cohortID = Integer.parseInt(id);
		CohortMaster cohortMaster = new CohortMaster();
		HITTCohort cohort = cohortMaster.getHITTCohort(cohortID);
		String csvFileName = "Cohort" + id + ".csv";
		
		response.setContentType("text/csv");
		
		// creates mock data
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);
		
		List<String[]> listString = cohort.getDataArr();
		
		// uses the Super CSV API to generate CSV data from the model data
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] header = { "PepfarID" };
		
		csvWriter.writeHeader(header);
		if (listString != null && !listString.isEmpty()) {
			for (String[] ele : listString) {
				csvWriter.write(ele[0], header);
			}
		}
		
		csvWriter.close();
	}
	
}
