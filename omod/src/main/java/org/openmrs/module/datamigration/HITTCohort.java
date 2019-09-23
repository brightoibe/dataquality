/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.datamigration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author The Bright
 */
public class HITTCohort {
	
	/**
	 * @return the dataArr
	 */
	public List<String[]> getDataArr() {
		return dataArr;
	}
	
	/**
	 * @param dataArr the dataArr to set
	 */
	public void setDataArr(List<String[]> dataArr) {
		this.dataArr = dataArr;
	}
	
	public HITTCohort() {
		
	}
	
	public HITTCohort(int id, String name, Set<Integer> ptsSet) {
		this.cohortID = id;
		this.cohortName = name;
		this.patientSet.addAll(ptsSet);
	}
	
	/**
	 * @return the patientSet
	 */
	public Set<Integer> getPatientSet() {
		return patientSet;
	}
	
	/**
	 * @param patientSet the patientSet to set
	 */
	public void setPatientSet(Set<Integer> patientSet) {
		this.patientSet = patientSet;
	}
	
	/**
	 * @return the cohortName
	 */
	public String getCohortName() {
		return cohortName;
	}
	
	/**
	 * @param cohortName the cohortName to set
	 */
	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}
	
	/**
	 * @return the cohortID
	 */
	public int getCohortID() {
		return cohortID;
	}
	
	/**
	 * @param cohortID the cohortID to set
	 */
	public void setCohortID(int cohortID) {
		this.cohortID = cohortID;
	}
	
	/**
	 * @return the lineListingFileName
	 */
	public String getLineListingFileName() {
		return lineListingFileName;
	}
	
	/**
	 * @param lineListingFileName the lineListingFileName to set
	 */
	public void setLineListingFileName(String lineListingFileName) {
		this.lineListingFileName = lineListingFileName;
	}
	
	private Set<Integer> patientSet = new HashSet<Integer>();
	
	private String cohortName;
	
	private int cohortID;
	
	private String lineListingFileName;
	
	private List<String[]> dataArr;
}
