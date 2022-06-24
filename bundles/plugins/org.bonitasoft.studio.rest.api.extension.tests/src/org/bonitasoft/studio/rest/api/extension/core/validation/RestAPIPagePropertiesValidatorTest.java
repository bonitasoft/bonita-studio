/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.assertj.core.data.MapEntry;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.maven.builder.validator.Location;
import org.bonitasoft.studio.maven.builder.validator.LocationResolver;
import org.bonitasoft.studio.maven.builder.validator.StatusWithLocation;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestAPIPagePropertiesValidatorTest {

    @Mock
    private RestAPIExtensionRepositoryStore repositoryStore;
    @Mock
    private IFile pagePropertyFile;
    @Mock
    private UniquePathTemplateValidator uniquePathTemplateValidator;
    @Mock
    private IProject project;

    @Before
    public void setUp() throws Exception {
        when(pagePropertyFile.getProject()).thenReturn(project);
        when(project.getName()).thenReturn("myRestAPI");
    }

    @Test
    public void should_fail_when_a_mandatory_property_is_not_set() throws Exception {
        final RestAPIPagePropertiesValidator validator = newValidator(asProperties());

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_not_fail_when_all_properties_are_set() throws Exception {
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties());

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isOK();
    }

    @Test
    public void should_fail_when_a_property_has_no_value_and_store_error_location() throws Exception {
        final Properties defaultPageProperties = defaultPageProperties();
        defaultPageProperties.setProperty("displayName", "");
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties);

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isNotOK();
        for (final IStatus s : status.getChildren()) {
            if (!s.isOK()) {
                assertThat(s).isInstanceOf(StatusWithLocation.class);
                final Location location = ((StatusWithLocation) s).getLocation();
                assertThat(location).isNotNull();
                assertThat(location.getLength()).isGreaterThan(0);
            }
        }
    }

    @Test
    public void should_validate_templatePath_unicity() throws Exception {
        final Properties defaultPageProperties = defaultPageProperties();
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties);

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isOK();
        verify(validator).validatePathTemplateUnicity(eq(project), notNull());
    }

    @Test
    public void should_fail_if_http_method_is_not_valid() throws Exception {
        final Properties defaultPageProperties = defaultPageProperties();
        defaultPageProperties.setProperty("restAPIName.method", "DO");
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties);

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_if_contentType_is_not_apiExtension() throws Exception {
        final Properties defaultPageProperties = defaultPageProperties();
        defaultPageProperties.setProperty("contentType", "page");
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties);

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isNotOK();
    }

    @Test
    public void should_not_validate_templatePath_unicity_if_apiName_is_not_defined() throws Exception {
        final Properties defaultPageProperties = defaultPageProperties();
        defaultPageProperties.setProperty("apiExtensions", "");
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties);

        final MultiStatus status = validator.validate(pagePropertyFile);

        StatusAssert.assertThat(status).isNotOK();
        verify(validator, never()).validatePathTemplateUnicity(eq(project), notNull());
    }

    @Test
    public void should_delegate_pathTemplate_validation() throws Exception {
        final Properties defaultPageProperties = defaultPageProperties();
        defaultPageProperties.setProperty("apiExtensions", "");
        final RestAPIPagePropertiesValidator validator = newValidator(defaultPageProperties);
        doCallRealMethod().when(validator).validatePathTemplateUnicity(eq(project), any(LocationResolver.class));

        final LocationResolver locationResolver = mock(LocationResolver.class);
        final RestAPIExtensionFileStore fStore = mock(RestAPIExtensionFileStore.class);
        when(repositoryStore.getChild("myRestAPI", true)).thenReturn(fStore);
        final MultiStatus multiStatus = multiStatus(IStatus.OK);
        when(uniquePathTemplateValidator.doValidatePathTemplate(fStore)).thenReturn(multiStatus);

        validator.validatePathTemplateUnicity(project, locationResolver);

        verify(uniquePathTemplateValidator).doValidatePathTemplate(fStore);
    }

    private MultiStatus multiStatus(final int severity) {
        final MultiStatus status = mock(MultiStatus.class);
        if (severity == IStatus.OK) {
            when(status.isOK()).thenReturn(true);
        }
        return status;
    }

    private Properties asProperties(final MapEntry... mapEntries) {
        final Properties properties = new Properties();
        for (final MapEntry me : mapEntries) {
            properties.put(me.key, me.value);
        }
        return properties;
    }

    private Properties defaultPageProperties() {
        return asProperties(entry("displayName", "Rest API Name"),
                entry("name", "restAPIName"),
                entry("description", "Rest API Name"),
                entry("contentType", "apiExtension"),
                entry("apiExtensions", "restAPIName"),
                entry("restAPIName.pathTemplate", "resource/add"),
                entry("restAPIName.permissions", "myPermission"),
                entry("restAPIName.classFileName", "Index.groovy"),
                entry("restAPIName.method", "GET"));
    }

    private final RestAPIPagePropertiesValidator newValidator(final Properties properties) throws IOException, CoreException, BadLocationException {
        final RestAPIPagePropertiesValidator validator = spy(new RestAPIPagePropertiesValidator(repositoryStore, uniquePathTemplateValidator));
        doReturn(properties).when(validator).pageProperties(pagePropertyFile);
        final Document document = new Document();
        document.set(getPropertyAsString(properties));
        doReturn(document).when(validator).toDocument(pagePropertyFile);
        doReturn(ValidationStatus.ok()).when(validator).validatePathTemplateUnicity(eq(project), any(LocationResolver.class));
        doReturn(ValidationStatus.ok()).when(validator).validateFileExists(anyString(), anyString(), eq(project), any(LocationResolver.class));
        return validator;
    }

    private String getPropertyAsString(final Properties prop) {
        final StringWriter writer = new StringWriter();
        prop.list(new PrintWriter(writer));
        return writer.getBuffer().toString();
    }
}
