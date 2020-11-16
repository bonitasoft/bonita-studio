/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.validation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.builder.PagePropertyConstants;
import org.bonitasoft.studio.maven.builder.validator.Location;
import org.bonitasoft.studio.maven.builder.validator.LocationResolver;
import org.bonitasoft.studio.maven.builder.validator.StatusWithLocation;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Strings;
import com.google.common.io.Files;

public class RestAPIPagePropertiesValidator extends TypedValidator<IFile, MultiStatus>
        implements PagePropertyConstants {

    private static final Set<String> MANDATORY_PROPERTIES = new HashSet<>();

    static {
        MANDATORY_PROPERTIES.add(DISPLAY_NAME);
        MANDATORY_PROPERTIES.add(NAME);
        MANDATORY_PROPERTIES.add(DESCRIPTION);
        MANDATORY_PROPERTIES.add(CONTENT_TYPE);
        MANDATORY_PROPERTIES.add(API_EXTENSIONS);
    }

    private static final Set<String> API_PROPERTIES = new HashSet<>();

    static {
        API_PROPERTIES.add(PATH_TEMAPLTE);
        API_PROPERTIES.add(PERMISSIONS);
        API_PROPERTIES.add(CLASS_FILENAME);
        API_PROPERTIES.add(CLASS_NAME);
        API_PROPERTIES.add(METHOD);
    }

    private static final List<String> HTTP_METHODS = new ArrayList<>();

    static {
        HTTP_METHODS.add(HTTPMethod.GET.name());
        HTTP_METHODS.add(HTTPMethod.PATCH.name());
        HTTP_METHODS.add(HTTPMethod.PUT.name());
        HTTP_METHODS.add(HTTPMethod.DELETE.name());
        HTTP_METHODS.add(HTTPMethod.POST.name());
        HTTP_METHODS.add(HTTPMethod.HEAD.name());
        HTTP_METHODS.add(HTTPMethod.OPTIONS.name());
        HTTP_METHODS.add(HTTPMethod.TRACE.name());
    }

    private final RestAPIExtensionRepositoryStore repositoryStore;
    private final UniquePathTemplateValidator uniquePathTemplateValidator;

    public RestAPIPagePropertiesValidator(final RestAPIExtensionRepositoryStore repositoryStore,
            final UniquePathTemplateValidator uniquePathTemplateValidator) {
        this.repositoryStore = repositoryStore;
        this.uniquePathTemplateValidator = uniquePathTemplateValidator;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator#doValidate(java.lang.Object)
     */
    @Override
    protected MultiStatus doValidate(final IFile pagePropertyFile) {
        final MultiStatus status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK, "", null);
        try {
            final Document document = toDocument(pagePropertyFile);
            final LocationResolver locationResolver = new LocationResolver(document);
            final Properties pageProperties = pageProperties(pagePropertyFile);
            status.addAll(validateProperties(pageProperties, MANDATORY_PROPERTIES, null, pagePropertyFile.getProject(),
                    locationResolver));
            final String apiNames = pageProperties.getProperty(API_EXTENSIONS);
            if (!Strings.isNullOrEmpty(apiNames)) {
                for (final String apiName : apiNames.split(",")) {
                    final String name = apiName.trim();
                    status.addAll(
                            validateProperties(pageProperties, API_PROPERTIES, name, pagePropertyFile.getProject(),
                                    locationResolver));
                }
                status.add(validatePathTemplateUnicity(pagePropertyFile.getProject(), locationResolver));
            }
        } catch (IOException | CoreException | BadLocationException e) {
            BonitaStudioLog.error(e);
        }
        return status;
    }

    protected IStatus validatePathTemplateUnicity(final IProject project, final LocationResolver locationResolver)
            throws BadLocationException {
        final MultiStatus result = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK, "", null);
        final MultiStatus status = uniquePathTemplateValidator
                .doValidatePathTemplate(repositoryStore.getChild(project.getName(), true));
        if (!status.isOK()) {
            for (final IStatus s : status.getChildren()) {
                if (s instanceof PathTemplateErrorStatus) {
                    for (final Location location : locationResolver
                            .findLocationsMatching("." + PATH_TEMAPLTE + "[\\s]*=[\\s]*"
                                    + ((PathTemplateErrorStatus) s).getPathTemplate().getPath() + "(?!.)", true)) {
                        result.add(new StatusWithLocation(s.getMessage(), location));
                    }
                }
            }
        }
        return result;
    }

    protected Properties pageProperties(final IFile pagePropertyFile) throws IOException, CoreException {
        try (InputStream is = pagePropertyFile.getContents()) {
            final Properties properties = new Properties();
            properties.load(is);
            return properties;
        }
    }

    protected Document toDocument(final IFile pagePropertyFile) throws IOException {
        final Document document = new Document();
        document.set(Files.toString(pagePropertyFile.getLocation().toFile(), StandardCharsets.UTF_8));
        return document;
    }

    private IStatus validateProperties(final Properties pageProperties, final Set<String> keySet, final String prefix,
            final IProject project,
            final LocationResolver locationResolver)
            throws BadLocationException {
        final MultiStatus status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK, "", null);
        for (final String key : keySet) {
            IStatus propertyStatus = validateProperty(pageProperties, prefix, key, project, locationResolver);
            if(Stream.of(status.getChildren()).map(IStatus::getMessage).noneMatch(propertyStatus.getMessage()::equals)){
                status.add(propertyStatus);
            }
        }
        return status;
    }

    private String propertyKey(final String prefix, final String key) {
        return Strings.isNullOrEmpty(prefix) ? key : prefix + "." + key;
    }

    private IStatus validateProperty(final Properties pageProperties, final String prefix, final String keyId,
            final IProject project,
            final LocationResolver locationResolver) {
        final String key = propertyKey(prefix, keyId);
        if (!pageProperties.containsKey(key)) {
            if(propertyKey(prefix,CLASS_FILENAME).equals(key) && pageProperties.containsKey(propertyKey(prefix,CLASS_NAME))) {
                return ValidationStatus.ok();
            }
            if(propertyKey(prefix,CLASS_NAME).equals(key) && pageProperties.containsKey(propertyKey(prefix,CLASS_FILENAME))) {
                return ValidationStatus.ok();
            }
            if(CLASS_FILENAME.equals(keyId)) {
                return new StatusWithLocation(NLS.bind(Messages.pagePropertyKeyMissing, propertyKey(prefix,CLASS_NAME)));
            }
            return new StatusWithLocation(NLS.bind(Messages.pagePropertyKeyMissing, key));
        } else if (Strings.isNullOrEmpty(pageProperties.getProperty(key))) {
            return new StatusWithLocation(NLS.bind(Messages.pagePropertyValueMissing, key),
                    locationResolver.getLocation(key));
        }
        if (Objects.equals(key, propertyKey(prefix, METHOD))) {
            return validateMethodValue(key, pageProperties.getProperty(key), locationResolver);
        }
        if (Objects.equals(key, propertyKey(prefix, CLASS_FILENAME))) {
            return validateFileExists(key, pageProperties.getProperty(key), project, locationResolver);
        }
        if (Objects.equals(key, propertyKey(prefix, CLASS_NAME))) {
            return validateClassExists(key, pageProperties.getProperty(key), project, locationResolver);
        }
        if (Objects.equals(key, CONTENT_TYPE)) {
            return Objects.equals("apiExtension", pageProperties.getProperty(CONTENT_TYPE)) ? ValidationStatus.ok()
                    : new StatusWithLocation(Messages.invalidRestAPIContentType, locationResolver.getLineLocation(key));
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateFileExists(final String key, final String filePath, final IProject project,
            final LocationResolver locationResolver) {
        final IPath path = Path.fromOSString("src/main/groovy/" + filePath);
        final IFile file = project.getFile(path);
        if (!file.exists()) {
            return new StatusWithLocation(NLS.bind(Messages.classFileNotFound, path),
                    locationResolver.getLineLocation(key));
        }
        return ValidationStatus.ok();
    }

    protected IStatus validateClassExists(final String key, final String className, final IProject project,
            final LocationResolver locationResolver) {
        IJavaProject javaProject = JavaCore.create(project);
        if (javaProject != null) {
            try {
                javaProject.findType(className);
            } catch (JavaModelException e) {
                return new StatusWithLocation(NLS.bind(Messages.classNotFound, className),
                        locationResolver.getLineLocation(key));
            }
        }
        return ValidationStatus.ok();
    }

    private IStatus validateMethodValue(final String key, final String method,
            final LocationResolver locationResolver) {
        if (!HTTP_METHODS.contains(method)) {
            return new StatusWithLocation(NLS.bind(Messages.invalidMethodName, method, HTTP_METHODS),
                    locationResolver.getLineLocation(key));
        }
        if (!HTTPMethod.GET.name().equals(method)) {
            return new StatusWithLocation(IStatus.WARNING, Messages.useGETMethodWarning,
                    locationResolver.getLineLocation(key));
        }
        return ValidationStatus.ok();
    }

}
