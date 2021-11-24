/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.extensions.Preference;

@Creatable
@Singleton
public class RestAPIAddressResolver implements BonitaPreferenceConstants {

    private static final String WAR_CONTEXT_NAME = "bonita";
    private IEclipsePreferences preferenceStore;

    public RestAPIAddressResolver() {
    }

    public RestAPIAddressResolver(final IEclipsePreferences preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    @PostConstruct
    public void init(@Preference(nodePath = "org.bonitasoft.studio.preferences") final IEclipsePreferences preferenceStore) {
        this.preferenceStore = preferenceStore;
    }

    private String host() {
        final String host = preferenceStore.get(CONSOLE_HOST, DEFAULT_HOST);
        checkNotNull(host);
        return host;
    }

    private String port() {
        final int port = preferenceStore.getInt(CONSOLE_PORT, DEFAULT_PORT);
        checkArgument(port > 0);
        return String.valueOf(port);
    }

    private String baseURL() {
        return "http://" + host() + ":" + port() + "/" + WAR_CONTEXT_NAME;
    }

    public String getAddress(String pathTemplate) {
        return isNullOrEmpty(pathTemplate) ? "" : baseURL() + "/API/extension/" + pathTemplate;
    }
}
