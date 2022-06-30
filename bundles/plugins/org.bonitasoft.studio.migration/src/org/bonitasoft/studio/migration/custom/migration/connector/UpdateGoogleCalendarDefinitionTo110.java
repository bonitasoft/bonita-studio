package org.bonitasoft.studio.migration.custom.migration.connector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;

public class UpdateGoogleCalendarDefinitionTo110 extends UpdateConnectorDefinitionMigration {

    private static final Set<String> GOOGLE_CALENDAR_DEFINITIONS = new HashSet<>();
    static {
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-create-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-get-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-delete-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-move-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-update-event");
    }

    @Override
    protected String getNewDefinitionVersion() {
        return "1.1.0";
    }

    @Override
    protected String getOldDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    protected boolean shouldUpdateVersion(final String defId) {
        return GOOGLE_CALENDAR_DEFINITIONS.contains(defId);
    }

    @Override
    protected void updateConfiguration(Instance connectorConfigInstance, Model model) {
        super.updateConfiguration(connectorConfigInstance, model);

        List<Instance> parameters = connectorConfigInstance.get("parameters");
        Instance authModeParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
        authModeParameter.set("key", "authMode");
        authModeParameter.set("expression",  StringToExpressionConverter.createExpressionInstance(model,
                "P12", "P12", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true));
        parameters.add(authModeParameter);
    }
    
}
