/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.configuration;

import java.util.Collections;

import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.osgi.util.NLS;

public class ConfigurationValidator implements IValidator<Configuration> {

    private AbstractProcess process;

    public ConfigurationValidator(AbstractProcess process) {
        this.process = process;
    }

    @Override
    public IStatus validate(Configuration configuration) {
        MultiStatus status = newMultiStatus();
        status.addAll(validateParameters(configuration, true));
        status.addAll(validateDefinitionMappings(configuration));
        status.addAll(validateFormMappings());
        return status;
    }

    private IStatus validateFormMappings() {
        ModelSearch modelSearch = new ModelSearch(() -> Collections.singletonList(process));
        return modelSearch.getAllItemsOfType(process, FormMapping.class).stream()
                .filter(mapping -> formMappingType(
                        mapping) != org.bonitasoft.engine.form.FormMappingType.PROCESS_OVERVIEW)
                .filter(this::emptyInternalMapping)
                .map(mapping -> ValidationStatus.error(statusMessage(
                        String.format(Messages.missingInternalFormMapping, 
                                containerName(mapping), 
                                containerType(mapping))
                        ,true)))
                .collect(StatusCollectors.toMultiStatus());
    }

    private String containerName(FormMapping formMapping) {
        return ((Element) formMapping.eContainer()).getName();
    }
    
    private String formType(FormMapping formMapping) {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING.equals(formMapping.eContainingFeature()) ? "instantiation form" : "overview form" ;
    }
    
    private String containerType(FormMapping formMapping) {
        return formMapping.eContainer() instanceof Task ? "task" : formType(formMapping);
    }

    private org.bonitasoft.engine.form.FormMappingType formMappingType(final FormMapping formMapping) {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING.equals(formMapping.eContainingFeature())
                ? mappingScope(formMapping)
                : org.bonitasoft.engine.form.FormMappingType.PROCESS_OVERVIEW;
    }

    private org.bonitasoft.engine.form.FormMappingType mappingScope(final FormMapping formMapping) {
        return formMapping.eContainer() instanceof Pool ? org.bonitasoft.engine.form.FormMappingType.PROCESS_START :  org.bonitasoft.engine.form.FormMappingType.TASK;
    }

    private boolean emptyInternalMapping(FormMapping mapping) {
        return mapping.getType() == FormMappingType.INTERNAL 
                && (mapping.getTargetForm() == null || !mapping.getTargetForm().hasContent());
    }

    private IStatus validateDefinitionMappings(Configuration configuration) {
        return configuration.getDefinitionMappings().stream()
                .filter(mapping -> mapping.getImplementationId() == null || mapping.getImplementationVersion() == null)
                .map(mapping -> ValidationStatus.error(
                        statusMessage(NLS.bind(Messages.invalidImplementationFor, mapping.getDefinitionId()), true)))
                .collect(StatusCollectors.toMultiStatus());
    }

    public IStatus validateParameters(Configuration configuration, boolean forceValue) {
        return configuration.getParameters().stream()
                .map(parameter -> validateParameter(parameter, forceValue))
                .collect(StatusCollectors.toMultiStatus());
    }

    public IStatus validateParameter(Parameter p, boolean forceValue) {
        final String input = p.getValue();
        final String typeName = p.getTypeClassname();
        if (input == null || input.isEmpty()) {
            return forceValue
                    ? ValidationStatus.error(statusMessage(NLS.bind(Messages.missingParameterValue, p.getName()), true))
                    : ValidationStatus.warning(NLS.bind(Messages.missingParameterValue, p.getName()));
        } else if (typeName.equals(Integer.class.getName())) {
            try {
                Integer.parseInt(input);
            } catch (final NumberFormatException e) {
                return ValidationStatus
                        .error(statusMessage(NLS.bind(Messages.invalidIntegerForParameter, p.getName()), forceValue));
            }
        } else if (typeName.equals(Double.class.getName())) {
            try {
                Double.parseDouble(input);
            } catch (final NumberFormatException e) {
                return ValidationStatus
                        .error(statusMessage(NLS.bind(Messages.invalidDoulbeForParameter, p.getName()), forceValue));
            }
        }
        return ValidationStatus.ok();
    }

    private String statusMessage(String message, boolean prependProcessName) {
        String result = "";
        if (prependProcessName) {
            result = String.format("%s (%s): ", process.getName(), process.getVersion());
        }
        return result + message;
    }

    private MultiStatus newMultiStatus() {
        return new MultiStatus(ConfigurationPlugin.PLUGIN_ID, 0, "");
    }

}
