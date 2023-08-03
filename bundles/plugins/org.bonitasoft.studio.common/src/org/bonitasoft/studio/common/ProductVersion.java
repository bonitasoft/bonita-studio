/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.repository.internal.DefaultVersionRangeResolver;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

/**
 * @author Romain Bioteau
 */
public class ProductVersion {
    
    private static final Properties BUILD_PROPERTIES = new Properties();
    static {
        try(InputStream is = ProductVersion.class.getResourceAsStream("/build_info.properties")){
            BUILD_PROPERTIES.load(is);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //OEM Variable to be use for redirect urls
    public static final String REDIRECT_URL_PRODUCT_ID = "bos";
    public static final String VERSION_6_0_0_ALPHA = "6.0.0-Alpha";
    public static final String CURRENT_VERSION = manifestVersion(true);

    public static final String CURRENT_YEAR = BUILD_PROPERTIES.getProperty("build.year");

    public static final String BUILD_ID = trimDot(System.getProperty("eclipse.buildId", null));
    public static final String BRANDING_VERSION_RAW = BUILD_PROPERTIES.getProperty("branding.version", null);
    public static final String BRANDING_VERSION = BRANDING_VERSION_RAW != null
            ? BRANDING_VERSION_RAW.replaceAll("-.*", "")
            : null;
    public static final String BONITA_RUNTIME_VERSION = BUILD_PROPERTIES.getProperty("bonita-runtime.version");

    public static boolean sameVersion(final String version) {
        return CURRENT_VERSION.equals(version);
    }

    private static String trimDot(String label) {
        return label != null && label.lastIndexOf(".") == label.length() - 1 ? label.substring(0, label.length() - 1)
                : label;
    }

    private static String manifestVersion(boolean stripQualifier) {
        Bundle bundle = FrameworkUtil.getBundle(ProductVersion.class);
        if (bundle == null) {
            String implementationVersion = BUILD_PROPERTIES.getProperty("studio.version");
            return stripQualifier ? stripSnaphot(implementationVersion) : implementationVersion;
        }
        Version version = bundle.getVersion();
        return stripQualifier ? String.format("%s.%s.%s", version.getMajor(), version.getMinor(), version.getMicro())
                : convertQualifierToMavenFormat(version);
    }

    private static String convertQualifierToMavenFormat(Version version) {
        return hasTimestampQualifier(version)
                ? String.format("%s.%s.%s-SNAPSHOT", version.getMajor(), version.getMinor(), version.getMicro())
                : version.toString();
    }

    static boolean hasTimestampQualifier(Version version) {
        String qualifier = version.getQualifier();
        return qualifier != null && !qualifier.isEmpty()
                && (qualifier.matches("[0-9].*$") || "qualifier".equals(qualifier));
    }

    private static String stripSnaphot(String version) {
        if (version.endsWith("-SNAPSHOT")) {
            return version.substring(0, version.indexOf("-SNAPSHOT"));
        }
        return version;
    }

    public static boolean sameMinorVersion(final String version) {
        if (version == null) {
            return false;
        }
        final String minor = CURRENT_VERSION.substring(0, CURRENT_VERSION.lastIndexOf("."));
        final String[] split = version.split("\\.");
        String testedVersion = version;
        if (split.length > 2) {
            testedVersion = split[0] + "." + split[1];
        }
        return minor.equals(testedVersion);
    }

    public static boolean canBeMigrated(final String version) {
        if (version == null) {
            return false;
        }
        Version current = new Version("0.0.0");
        try {
            current = Version.parseVersion(version);
        } catch (final IllegalArgumentException e) {

        }
        final Version productVersion = new Version(CURRENT_VERSION);
        return current.compareTo(productVersion) < 0;
    }

    public static boolean canBeImported(final String version) {
        if (version == null) {
            return true;
        }
        final DefaultArtifactVersion initialVersion = new DefaultArtifactVersion("6.0.0");
        DefaultArtifactVersion current = new DefaultArtifactVersion("0.0.0");
        try {
            current = new DefaultArtifactVersion(version);
        } catch (final IllegalArgumentException e) {
            return false;
        }
        return minorVersion(version).compareTo(minorVersion(CURRENT_VERSION)) <= 0
                && current.compareTo(initialVersion) >= 0;
    }

    public static String minorVersion() {
        final DefaultArtifactVersion productVersion = new DefaultArtifactVersion(CURRENT_VERSION);
        return String.format("%s.%s", productVersion.getMajorVersion(), productVersion.getMinorVersion());
    }

    public static boolean isBefore780Version(String version) {
        try {
            return new DefaultArtifactVersion(version).compareTo(new DefaultArtifactVersion("7.8.0")) < 0;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isBefore(String currentVersion, String referenceVersion) {
        try {
            return new DefaultArtifactVersion(currentVersion)
                    .compareTo(new DefaultArtifactVersion(referenceVersion)) < 0;
        } catch (Exception e) {
            return true;
        }
    }

    public static String maintenanceVersion() {
        return String.valueOf(new Version(CURRENT_VERSION).getMicro());
    }

    public static Version minorVersion(String version) {
        DefaultArtifactVersion v = new DefaultArtifactVersion(version);
        return new Version(v.getMajorVersion(), v.getMinorVersion(), 0);
    }

    public static String toMinorVersionString(Version version) {
        return String.format("%s.%s", version.getMajor(), version.getMinor());
    }

    public static String mavenVersion() {
        return manifestVersion(false);
    }
    
    
    public static boolean isInRange(String semver) {
        var minVersion = new DefaultArtifactVersion("7.12.0");
        var maxVersion = new DefaultArtifactVersion("8.0.0");
        var version = new DefaultArtifactVersion(semver);
        if(version.compareTo(minVersion) < 0 ) {
            return false;
        }else if(version.compareTo(maxVersion) > 0 ) {
            return false;
        }
        return true;
    }
}
