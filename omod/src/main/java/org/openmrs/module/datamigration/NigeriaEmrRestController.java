package org.openmrs.module.datamigration;

import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Once OwaRestController is deleted, this class should be renamed to OwaRestController
 */
@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + NigeriaEmrRestController.NG_NAMESPACE)
public class NigeriaEmrRestController extends MainResourceController {
	
	public static final String NG_NAMESPACE = "/migration";
	
	@Override
	public String getNamespace() {
		return RestConstants.VERSION_1 + NG_NAMESPACE;
	}
	
}
