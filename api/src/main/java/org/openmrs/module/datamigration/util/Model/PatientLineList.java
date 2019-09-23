package org.openmrs.module.datamigration.util.Model;

import java.util.Date;

public class PatientLineList {
	
	String PatientId;
	
	String PatientName;
	
	Integer countOfPharmacyEncounter;
	
	Integer countOfLabEncounter;
	
	Date dateOfFirstEncounter;
	
	Date dateOfLastEncounter;
	
	Date DateOfFirstARVPickup;
	
	String firstDocumentedRegimen;
	
	String lastDocumentedRegimen;
	
	String lastDocumentedViralLoadResult;
	
	public String getPatientId() {
		return PatientId;
	}
	
	public void setPatientId(String patientId) {
		PatientId = patientId;
	}
	
	public String getPatientName() {
		return PatientName;
	}
	
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	
	public Integer getCountOfPharmacyEncounter() {
		return countOfPharmacyEncounter;
	}
	
	public void setCountOfPharmacyEncounter(Integer countOfPharmacyEncounter) {
		this.countOfPharmacyEncounter = countOfPharmacyEncounter;
	}
	
	public Integer getCountOfLabEncounter() {
		return countOfLabEncounter;
	}
	
	public void setCountOfLabEncounter(Integer countOfLabEncounter) {
		this.countOfLabEncounter = countOfLabEncounter;
	}
	
	public Date getDateOfFirstEncounter() {
		return dateOfFirstEncounter;
	}
	
	public void setDateOfFirstEncounter(Date dateOfFirstEncounter) {
		this.dateOfFirstEncounter = dateOfFirstEncounter;
	}
	
	public Date getDateOfLastEncounter() {
		return dateOfLastEncounter;
	}
	
	public void setDateOfLastEncounter(Date dateOfLastEncounter) {
		this.dateOfLastEncounter = dateOfLastEncounter;
	}
	
	public Date getDateOfFirstARVPickup() {
		return DateOfFirstARVPickup;
	}
	
	public void setDateOfFirstARVPickup(Date dateOfFirstARVPickup) {
		DateOfFirstARVPickup = dateOfFirstARVPickup;
	}
	
	public String getFirstDocumentedRegimen() {
		return firstDocumentedRegimen;
	}
	
	public void setFirstDocumentedRegimen(String firstDocumentedRegimen) {
		this.firstDocumentedRegimen = firstDocumentedRegimen;
	}
	
	public String getLastDocumentedRegimen() {
		return lastDocumentedRegimen;
	}
	
	public void setLastDocumentedRegimen(String lastDocumentedRegimen) {
		this.lastDocumentedRegimen = lastDocumentedRegimen;
	}
	
	public String getLastDocumentedViralLoadResult() {
		return lastDocumentedViralLoadResult;
	}
	
	public void setLastDocumentedViralLoadResult(String lastDocumentedViralLoadResult) {
		this.lastDocumentedViralLoadResult = lastDocumentedViralLoadResult;
	}
}
