/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.designer.core;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import javax.annotation.PostConstruct;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class DeleteBDMEventHandler implements EventHandler {

    private static final String BDM_DELETED_TOPIC = "bdm/deleted";

    @PostConstruct
    public void registerHandler(IEventBroker eventBroker) {
        eventBroker.subscribe(BDM_DELETED_TOPIC, this);
    }

    @Override
    public void handleEvent(final Event event) {
        execute();
    }

    private void execute() {
        try {
            HttpClientFactory.INSTANCE.send(HttpRequest.newBuilder(URI.create(String.format("http://%s:%s/bdm",
                    InetAddress.getLoopbackAddress().getHostAddress(),
                    InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)
                            .get(BonitaPreferenceConstants.DATA_REPOSITORY_PORT, "-1"))))
                    .timeout(Duration.ofSeconds(10))
                    .DELETE().build(), BodyHandlers.discarding());
            BonitaStudioLog.info("BDM has been deleted from the Data Repository service", UIDesignerPlugin.PLUGIN_ID);
        } catch (IOException | InterruptedException e) {
            BonitaStudioLog.error("An error occured while deleting the BDM from the Data Repository service", e);
        }
    }

}
