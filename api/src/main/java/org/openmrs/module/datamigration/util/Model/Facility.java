package org.openmrs.module.datamigration.util.Model;

import org.openmrs.BaseOpenmrsMetadata;

public class Facility extends BaseOpenmrsMetadata {
	
	private String facilityName;
	
	private String datimCode;
	
	private String lga;
	
	private String State;
	
	public String getFacilityName() {
		return facilityName;
	}
	
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	
	public String getDatimCode() {
		return datimCode;
	}
	
	public void setDatimCode(String datimCode) {
		this.datimCode = datimCode;
	}
	
	public String getLga() {
		return lga;
	}
	
	public void setLga(String lga) {
		this.lga = lga;
	}
	
	public String getState() {
		return State;
	}
	
	public void setState(String state) {
		State = state;
	}
	
	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
}
