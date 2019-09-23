package org.openmrs.module.datamigration;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.module.datamigration.util.Model.Demographics;

public class AnyThing extends BaseOpenmrsMetadata {
	
	//@JsonProperty("demographics")
	private Demographics demographics;
	
	public Demographics getDemographics() {
		return demographics;
	}
	
	public void setDemographics(Demographics demographics) {
		this.demographics = demographics;
	}
	
	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
}
