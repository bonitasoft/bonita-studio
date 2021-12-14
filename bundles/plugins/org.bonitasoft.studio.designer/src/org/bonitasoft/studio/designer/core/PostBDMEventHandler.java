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
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostBDMEventHandler implements EventHandler {

    private static final String FILE_CONTENT = "fileContent";
    private static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";
    private ObjectMapper objectMapper = new ObjectMapper();

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
        Map<String, Object> content = new HashMap<>();
        content.put("bdmXml", fileContent);

        try {
            var response = HttpClientFactory.INSTANCE.send(HttpRequest.newBuilder(URI.create(String.format("http://%s:%s/bdm",
                    InetAddress.getByName(null).getHostAddress(),
                    InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)
                            .get(BonitaPreferenceConstants.DATA_REPOSITORY_PORT, "-1"))))
                    .timeout(Duration.ofSeconds(10))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofByteArray(objectMapper.writeValueAsBytes(content)))
                    .build(), BodyHandlers.discarding());
            int statusCode = response.statusCode();
            if(statusCode >= 200 && statusCode < 300) {
                BonitaStudioLog.info("BDM has been publish into Data Repository service", UIDesignerPlugin.PLUGIN_ID);
            } else {
                BonitaStudioLog.error("An error occured while publishing the BDM into Data Repository service. Server response status is "+ statusCode, UIDesignerPlugin.PLUGIN_ID);
            }
        } catch (IOException | InterruptedException e) {
            BonitaStudioLog.error("An error occured while publishing the BDM into Data Repository service", e);
        }
    }

}
