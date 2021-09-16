/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.extension.properties.PagePropertyConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.osgi.util.NLS;

import com.google.common.base.Strings;
import com.google.common.io.Files;

public class PagePropertiesValidator extends TypedValidator<IFile, MultiStatus> implements PagePropertyConstants {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final static Set<String> MANDATORY_PROPERTIES = new HashSet<>();
    private List<PagePropertyValidator> validators = new ArrayList<>();

    static {
        MANDATORY_PROPERTIES.add(DISPLAY_NAME);
        MANDATORY_PROPERTIES.add(NAME);
        MANDATORY_PROPERTIES.add(DESCRIPTION);
        MANDATORY_PROPERTIES.add(CONTENT_TYPE);
    }
    
    public PagePropertiesValidator(List<PagePropertyValidator> validators) {
        this.validators = validators;
    }

    @Override
    protected MultiStatus doValidate(final IFile pagePropertyFile) {
        final MultiStatus status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK, "", null);
        try {
            final Document document = toDocument(pagePropertyFile);
            final LocationResolver locationResolver = new LocationResolver(document);
            final Properties pageProperties = pageProperties(pagePropertyFile);
            status.addAll(validateProperties(pageProperties, MANDATORY_PROPERTIES, null, pagePropertyFile.getProject(),
                    locationResolver));
            validators.stream()
                    .map(validator -> validator.validate(pageProperties,locationResolver))
                    .collect(Collectors.toList())
                    .forEach(status::add);
        } catch (IOException | CoreException | BadLocationException e) {
            BonitaStudioLog.error(e);
        }
        return status;
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
        document.set(Files.toString(pagePropertyFile.getLocation().toFile(), UTF_8));
        return document;
    }

    private IStatus validateProperties(final Properties pageProperties, final Set<String> keySet, final String prefix,
            final IProject project,
            final LocationResolver locationResolver)
            throws BadLocationException {
        final MultiStatus status = new MultiStatus(RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK, "", null);
        for (final String key : keySet) {
            status.add(validateProperty(pageProperties, prefix, key, project, locationResolver));
        }
        return status;
    }

    private String propertyKey(final String prefix, final String key) {
        return Strings.isNullOrEmpty(prefix) ? key : prefix + "." + key;
    }

    private IStatus validateProperty(final Properties pageProperties, final String prefix, final String keyId,
            final IProject project,
            final LocationResolver locationResolver)
            throws BadLocationException {
        final String key = propertyKey(prefix, keyId);
        if (!pageProperties.containsKey(key)) {
            return new StatusWithLocation(NLS.bind(Messages.pagePropertyKeyMissing, key));
        } else if (Strings.isNullOrEmpty(pageProperties.getProperty(key))) {
            return new StatusWithLocation(NLS.bind(Messages.pagePropertyValueMissing, key),
                    locationResolver.getLocation(key));
        }
        return ValidationStatus.ok();
    }


}
