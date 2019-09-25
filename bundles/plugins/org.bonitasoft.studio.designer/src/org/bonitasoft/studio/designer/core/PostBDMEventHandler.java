/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.designer.core;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class PostBDMEventHandler implements EventHandler {

    private static final String FILE_CONTENT = "fileContent";
    private static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";

    @PostConstruct
    public void registerHandler(IEventBroker eventBroker) {
        eventBroker.subscribe(BDM_DEPLOYED_TOPIC, this);
    }

    @Override
    public void handleEvent(final Event event) {
        execute(event);
    }

    private void execute(final Event event) {
        final String fileContent = (String) event.getProperty(FILE_CONTENT);
        Map<String, String> content = new HashMap<>();
        content.put("bdmXml", fileContent);
        try {
            Representation response = new ClientResource(String.format("http://localhost:%s/bdm",
                    InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)
                            .get(BonitaPreferenceConstants.DATA_REPOSITORY_PORT, "-1")))
                                    .post(new JacksonRepresentation<Object>(content));
            BonitaStudioLog.info("BDM has been publish into Data Repository service", UIDesignerPlugin.PLUGIN_ID);
        } catch (ResourceException e) {
            BonitaStudioLog.error("An error occured while publishing the BDM into Data Repository service", e);
        }
        
    }

}
