package org.openmrs.module.datamigration.util.Model;

import org.openmrs.BaseOpenmrsMetadata;

import java.util.LinkedHashSet;
import java.util.Set;

public class Identifiers extends BaseOpenmrsMetadata {

	Set<Identifier> identifiers = new LinkedHashSet<>();

	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	@Override
	public Integer getId() {
		return null;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
}
