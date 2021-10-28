/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.rest.api.extension.core.validation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingDefinition;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingTarget;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.BusinessArchiveValidatorProvider;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.PathTemplate;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionDescriptor;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class FormsCustomPermissionsValidator implements IValidator<BusinessArchive> {

    @PostConstruct
    public void register() {
        BusinessArchiveValidatorProvider.getInstance().addValidator(this);
    }
    
    private Set<String> listPermissions(Properties properties) {
        Set<String> permissionsValues = new HashSet<String>();
        for (Object value : properties.values()) {
            String permissionRule = (String) value;
            if (permissionRule.trim().startsWith("[") && permissionRule.trim().endsWith("]")) {
                permissionRule = permissionRule.trim().substring(1,
                        permissionRule.trim().length() - 1);
            }
            permissionsValues.addAll(Stream.of(permissionRule.split(",")).map(String::trim)
                    .collect(Collectors.toSet()));
        }
        return permissionsValues;
    }

    private Properties toProperties(byte[] content) {
        Properties properties = new Properties();
        if (content != null) {
            try (ByteArrayInputStream is = new ByteArrayInputStream(content)) {
                properties.load(is);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return properties;
    }

    private byte[] getCustomPermissionMappingContent() {
        try {
            return BOSEngineManager.getInstance(AbstractRepository.NULL_PROGRESS_MONITOR)
                    .getTenantConfigResourceContent(BOSEngineManager.CUSTOM_PERMISSIONS_MAPPING_PROPERTIES,
                            AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    private byte[] getProvidedPermissionMappingContent() {
        try {
            return BOSEngineManager.getInstance(AbstractRepository.NULL_PROGRESS_MONITOR)
                    .getTenantConfigResourceContent("resources-permissions-mapping.properties",
                            AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    protected WebPageRepositoryStore getWebPageRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(WebPageRepositoryStore.class);
    }

    protected RestAPIExtensionRepositoryStore getRestAPIExtensionRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class);
    }

    @Override
    public IStatus validate(BusinessArchive businessArchive) {
        FormMappingModel formMappingModel = businessArchive.getFormMappingModel();
        MultiStatus status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, -1, null, null);
        for (FormMappingDefinition formMapping : formMappingModel.getFormMappings()) {
            if (formMapping.getTarget() == FormMappingTarget.INTERNAL && formMapping.getForm() != null) {
                String targetForm = formMapping.getForm();
                Optional<WebPageFileStore> webPageFileStore = getWebPageRepositoryStore().findByPageId(targetForm);
                if (webPageFileStore.isPresent()) {
                    WebPageFileStore pageFileStore = webPageFileStore.get();
                    Collection<String> pageResources = pageFileStore.getPageResources();
                    Properties customMappingProperties = toProperties(getCustomPermissionMappingContent());
                    Properties providedMappingProperties = toProperties(getProvidedPermissionMappingContent());
                    for (String resource : pageResources) {
                        Optional<RestAPIExtensionFileStore> restResource = getRestAPIExtensionRepositoryStore()
                                .findByRestResource(resource);
                        if (restResource.isPresent()) {
                            try {
                                RestAPIExtensionDescriptor restAPIExtensionDescriptor = restResource.get().getContent();
                                String[] resourceSplitted = resource.split("\\|");
                                Collection<String> permissions = restAPIExtensionDescriptor
                                        .findAPINameForPathTemplate(
                                                new PathTemplate(resourceSplitted[1].substring("extension/".length()),
                                                        resourceSplitted[0].trim()))
                                        .map(restAPIExtensionDescriptor::getPermissions)
                                        .orElse(Collections.emptySet());
                                Set<String> permissionsValues = listPermissions(customMappingProperties);
                                permissionsValues.addAll(listPermissions(providedMappingProperties));
                                Set<String> missingPermissionMapping = new HashSet<>();
                                for (String p : permissions) {
                                    if (!permissionsValues.contains(p)) {
                                        missingPermissionMapping.add(p);
                                    }
                                }
                                if(!missingPermissionMapping.isEmpty()) {
                                    String displayName = pageFileStore.getDisplayName();
                                    String formName = displayName == null || displayName.isEmpty()
                                            ? pageFileStore.getName()
                                            : displayName;
                                    status.add(ValidationStatus.warning(String.format(Messages.formPermissionNotConfigured,
                                                formName, missingPermissionMapping.toString())));
                                }
                            } catch (ReadFileStoreException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    }
                }
            }
        }
        return status;
    }

}
