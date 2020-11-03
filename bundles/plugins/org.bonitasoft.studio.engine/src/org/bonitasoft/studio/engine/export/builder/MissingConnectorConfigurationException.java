package org.bonitasoft.studio.engine.export.builder;

import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;

public class MissingConnectorConfigurationException extends RuntimeException {

    public MissingConnectorConfigurationException(Connector connector, ConnectableElement element) {
       super(String.format("Connector '%s' on '%s' has an invalid configuration. Remove this connector from the process.", connector.getName(),element.getName()));
    }

}
