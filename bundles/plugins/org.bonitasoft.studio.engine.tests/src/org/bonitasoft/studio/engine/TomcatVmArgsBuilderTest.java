/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.InetAddress;
import java.net.URI;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.engine.preferences.EnginePreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TomcatVmArgsBuilderTest {

    @Mock
    protected RepositoryAccessor repositoryAccessor;
    protected TomcatVmArgsBuilder tomcatVmArgsBuilder;
    private final File dbLocation = new File("dbLocation");
    @Mock
    private IPreferenceStore preferenceStore;

    @Before
    public void setUp() throws Exception {
        tomcatVmArgsBuilder = spy(new TomcatVmArgsBuilder(repositoryAccessor, preferenceStore));
        doNothing().when(tomcatVmArgsBuilder).addStudioHealthCheckEndpointProperties(any(StringBuilder.class));
        doReturn("test.bonita.product.application.id").when(tomcatVmArgsBuilder).getProductApplicationId();
        doReturn(dbLocation).when(tomcatVmArgsBuilder).getDBLocation();
    }

    @Test
    public void declareThePropertiesTheWatchdogWarReads() throws Exception {
        // pins the cross-module contract as literals, not constants: the watchdog WAR cannot
        // share them (separate build world), and a producer-side rename would silently disarm
        // it - see StudioWatchdogListener.HEALTHCHECK_ENDPOINT / POLLING_INTERVAL
        doCallRealMethod().when(tomcatVmArgsBuilder).addStudioHealthCheckEndpointProperties(any(StringBuilder.class));
        // a non-zero port: without it the preference reads 0 in this workspace and the test would
        // only ever exercise the disarm branch, leaving the nominal URL - the part that breaks
        // silently - unasserted
        var args = new StringBuilder();
        var store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        // restored, not reset to default: this store outlives the test in a shared Tycho JVM
        var previousPort = store.getInt(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT);
        store.setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, 12345);

        try {
            tomcatVmArgsBuilder.addStudioHealthCheckEndpointProperties(args);
        } finally {
            store.setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, previousPort);
        }

        assertThat(args.toString())
                // the PATH is part of the contract too: HealthCheckServerManager registers
                // /api/workspace/status/, and a typo here would disarm the watchdog silently
                .contains("-Dorg.bonitasoft.studio.healthcheck.endpoint=http://")
                .contains(":12345/api/workspace/status/")
                .contains("-Dorg.bonitasoft.studio.healthcheck.interval=");
        // and the emitted value must be parseable by the consumer, whatever the IP stack: an
        // unbracketed IPv6 literal renders an authority URI cannot read a host from
        assertThat(URI.create(emittedEndpoint(args.toString())).getHost()).isNotNull();
    }

    private static String emittedEndpoint(String args) {
        var marker = "-Dorg.bonitasoft.studio.healthcheck.endpoint=";
        var start = args.indexOf(marker) + marker.length();
        var end = args.indexOf(' ', start);
        return end < 0 ? args.substring(start) : args.substring(start, end);
    }

    @Test
    public void should_emit_the_watchdog_endpoint_after_the_user_tomcat_extra_params() throws Exception {
        // the last -D wins, so the watchdog endpoint must be emitted AFTER TOMCAT_EXTRA_PARAMS:
        // otherwise a user-supplied endpoint redirects the probe at another loopback port, which
        // either answers 200 forever (the orphan survives a Studio death) or never answers (a
        // healthy runtime dies after three strikes). Asserted through getVMArgs, since that is
        // the only place the two land in the same string
        doCallRealMethod().when(tomcatVmArgsBuilder).addStudioHealthCheckEndpointProperties(any(StringBuilder.class));
        var spoofed = "-Dorg.bonitasoft.studio.healthcheck.endpoint=http://127.0.0.1:9/hijacked";
        when(preferenceStore.getString(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS)).thenReturn(spoofed);
        var store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        var previousPort = store.getInt(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT);
        store.setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, 12345);

        String args;
        try {
            args = tomcatVmArgsBuilder.getVMArgs("");
        } finally {
            store.setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, previousPort);
        }

        assertThat(args).contains(spoofed).contains(":12345/api/workspace/status/");
        assertThat(args.indexOf(":12345/api/workspace/status/"))
                .as("the watchdog endpoint must be the last -D of the two")
                .isGreaterThan(args.indexOf(spoofed));
    }

    @Test
    public void bracketAnIpv6LoopbackInTheHealthcheckEndpoint() throws Exception {
        // the branch CI never runs: it prefers IPv4, so without a direct assertion the bracketing
        // could be reverted with the suite still green - and the unbracketed form silently
        // disarms the watchdog because URI reads no host from that authority
        assertThat(TomcatVmArgsBuilder.healthCheckEndpointHost(InetAddress.getByName("::1")))
                .isEqualTo("[0:0:0:0:0:0:0:1]");
        assertThat(TomcatVmArgsBuilder.healthCheckEndpointHost(InetAddress.getByName("127.0.0.1")))
                .isEqualTo("127.0.0.1");
    }

    @Test
    public void declareADisarmingEndpointWhenTheHealthcheckPortIsUnset() throws Exception {
        // the deliberate second case: no healthcheck server means the preference reads 0, the
        // watchdog disarms loudly on its side, and the Studio log carries the misconfiguration
        doCallRealMethod().when(tomcatVmArgsBuilder).addStudioHealthCheckEndpointProperties(any(StringBuilder.class));
        var args = new StringBuilder();
        var store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        var previousPort = store.getInt(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT);
        store.setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, 0);

        try {
            tomcatVmArgsBuilder.addStudioHealthCheckEndpointProperties(args);
        } finally {
            store.setValue(BonitaPreferenceConstants.HEALTHCHECK_SERVER_PORT, previousPort);
        }

        assertThat(args.toString()).contains(":0/api/workspace/status/");
    }

    @Test
    public void testGetVMArgsContainsRegisterWebPropertyToYesWhenPropertySet() throws Exception {
        System.setProperty(TomcatVmArgsBuilder.BONITA_WEB_REGISTER, "1");
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains("-Dbonita.web.register=1");
    }

    @Test
    public void testGetVMArgsContainsRegisterWebPropertyToYesWhenPropertyNotSet() throws Exception {
        System.clearProperty(TomcatVmArgsBuilder.BONITA_WEB_REGISTER);
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains("-Dbonita.web.register=1");
    }

    @Test
    public void testGetVMArgsContainsRegisterWebPropertyToNoWhenPropertySet() throws Exception {
        System.setProperty(TomcatVmArgsBuilder.BONITA_WEB_REGISTER, "0");
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains("-Dbonita.web.register=0");
    }

    @Test
    public void testLoggingPropertyCanBeOverriden() throws Exception {
        final String overridenValue = "-Dlog4j.configurationFile=test";
        when(preferenceStore.getString(EnginePreferenceConstants.TOMCAT_EXTRA_PARAMS)).thenReturn(overridenValue);
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains(overridenValue)
                .containsOnlyOnce("log4j.configurationFile");
    }

    @Test
    public void testLoggingPropertyDefaultValueWithTomcatArgs() throws Exception {
        final String overridenValue = "plop";
        System.setProperty("tomcat.extra.params", overridenValue);
        assertThat(tomcatVmArgsBuilder.getVMArgs(""))
                .contains("-Dlog4j.configurationFile=")
                .contains("log4j2-appenders.xml")
                .contains("log4j2-loggers.xml");
    }

    @Test
    public void testLoggingPropertyDefaultValueWithoutTomcatArgs() throws Exception {
        System.clearProperty("tomcat.extra.params");
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains("-Dlog4j.configurationFile=")
                .contains("log4j2-appenders.xml")
                .contains("log4j2-loggers.xml");
    }

    @Test
    public void testXMXCanBeOverriden() throws Exception {
        when(preferenceStore.getInt(EnginePreferenceConstants.TOMCAT_XMX_OPTION)).thenReturn(1024);
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains("-Xmx").containsOnlyOnce("1024m");
    }

    @Test
    public void should_launch_tomcat_with_bonita_csrf_cookie_path() throws Exception {
        assertThat(tomcatVmArgsBuilder.getVMArgs("")).contains("-Dbonita.csrf.cookie.path=\"/\"");
    }
}
