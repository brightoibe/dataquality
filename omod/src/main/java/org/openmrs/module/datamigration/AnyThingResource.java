/* * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.datamigration;

import org.openmrs.module.datamigration.api.dao.DbConnection;
import org.openmrs.module.datamigration.util.FactoryUtils;
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

import java.util.Properties;

@Resource(name = RestConstants.VERSION_1 + NigeriaEmrRestController.NG_NAMESPACE + "/anything", supportedClass = AnyThing.class, supportedOpenmrsVersions = {
        "1.9.*", "1.10.*", "1.11.*", "1.12.*", "2.0.*", "2.1.*", "2.2.*", "2.3.*" })
public class AnyThingResource extends MetadataDelegatingCrudResource<AnyThing> {
	
	@Override
	protected NeedsPaging<AnyThingResource> doGetAll(RequestContext context) {
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
			description.addProperty("json");
			description.addSelfLink();
		} else if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("json");
			description.addSelfLink();
			if (rep instanceof DefaultRepresentation) {
				description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			}
		}
		return description;
	}
	
	@PropertyGetter("display")
	public String getDisplay(AnyThing logentry) {
		return null;
	}
	
	@Override
	public AnyThing newDelegate() throws ResourceDoesNotSupportOperationException {
		return new AnyThing();
	}
	
	@Override
	public AnyThing save(AnyThing delegate) throws ResourceDoesNotSupportOperationException {
		Properties runtimeProperties = new Properties();
		runtimeProperties.setProperty("connection.username", "root");
		runtimeProperties.setProperty("connection.password", "P@ssw0rd");
		runtimeProperties.setProperty("connection.url",
		    "jdbc:mysql://127.0.0.1:3306/apindb?zeroDateTimeBehavior=convertToNull");
		
		/*DbConnection connection = new DbConnection();
		FactoryUtils factoryUtils = new FactoryUtils();
		factoryUtils.PatientUtils(connection.Connection(runtimeProperties));*/
		//System.out.println(delegate.getJson());
		
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("json");
		return description;
	}
	
	@Override
	public AnyThing getByUniqueId(String uniqueId) {
		return null;
	}
	
	@Override
	public void purge(AnyThing delegate, RequestContext context) throws ResourceDoesNotSupportOperationException {
	}
	
	@Override
	public Object update(String uuid, SimpleObject updateBody, RequestContext context) throws ResponseException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
}
