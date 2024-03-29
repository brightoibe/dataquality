/* * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.datamigration;

import org.openmrs.module.datamigration.util.FactoryUtils;
import org.openmrs.module.datamigration.util.Model.Migration;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + NigeriaEmrRestController.NG_NAMESPACE, supportedClass = Migration.class, supportedOpenmrsVersions = {
        "1.9.*", "1.10.*", "1.11.*", "1.12.*", "2.0.*", "2.1.*", "2.2.*", "2.3.*" })
public class MigrationResource extends MetadataDelegatingCrudResource<Migration> {
	
	@Override
	protected NeedsPaging<MigrationResource> doGetAll(RequestContext context) {
		return null;
	}
	
	@Override
	protected PageableResult doSearch(RequestContext context) {
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		DelegatingResourceDescription description = null;
		if (rep instanceof RefRepresentation) {
			description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("prefix");
			description.addProperty("givenName");
			description.addProperty("middleName");
			description.addProperty("surname");
			description.addProperty("lastName");
			description.addProperty("birthDate");
			description.addProperty("gender");
			description.addProperty("birthdateEstimated");
			description.addProperty("dead");
			description.addProperty("deathDate");
			description.addProperty("dateCreated");
			description.addProperty("causeOfDeath");
			description.addProperty("phone");
			description.addProperty("facility", Representation.REF);
			description.addProperty("address", Representation.REF);
			description.addProperty("identifiers", Representation.REF);
			description.addProperty("encounters", Representation.REF);
			
			description.addSelfLink();
		} else if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addSelfLink();
			if (rep instanceof DefaultRepresentation) {
				description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			}
		}
		return description;
	}
	
	@PropertyGetter("display")
	public String getDisplay(Migration logentry) {
		//return null;
		String ret = logentry.getGivenName() + " inserted";
		return ret;
	}
	
	@Override
	public Migration newDelegate() throws ResourceDoesNotSupportOperationException {
		return new Migration();
	}
	
	@Override
	public Migration save(Migration delegate) throws ResourceDoesNotSupportOperationException {
		
		try {
			FactoryUtils factoryUtils = new FactoryUtils();
			factoryUtils.PatientUtils(delegate);
		}
		catch (Exception ex) {
			try {
				throw ex;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return delegate;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		
		description.addProperty("prefix");
		description.addProperty("givenName");
		description.addProperty("middleName");
		description.addProperty("surname");
		description.addProperty("lastName");
		description.addProperty("birthDate");
		description.addProperty("gender");
		description.addProperty("birthdateEstimated");
		description.addProperty("dead");
		description.addProperty("deathDate");
		description.addProperty("dateCreated");
		description.addProperty("causeOfDeath");
		description.addProperty("facility");
		description.addProperty("address");
		description.addProperty("phone");
		description.addProperty("identifiers", Representation.REF);
		description.addProperty("encounters", Representation.REF);
		return description;
	}
	
	@Override
	public Migration getByUniqueId(String uniqueId) {
		return null;
	}
	
	@Override
	public void purge(Migration delegate, RequestContext context) throws ResourceDoesNotSupportOperationException {
	}
	
	@Override
	public Object update(String uuid, SimpleObject updateBody, RequestContext context) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	/*@Override
	public Model getCREATEModel(Representation rep){
		return new ModelImpl()
				.property("facility", new RefProperty("#/definition/FacilityCreate"));
	}*/
}
