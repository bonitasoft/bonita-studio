/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.preference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.apache.maven.cli.configuration.SettingsXmlConfigurationProcessor;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.MavenPlugin;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.sonatype.plexus.components.cipher.PlexusCipher;
import org.sonatype.plexus.components.cipher.PlexusCipherException;
import org.sonatype.plexus.components.sec.dispatcher.DefaultSecDispatcher;

public class MavenPasswordManager {

    private File securityFile;
    private SAXBuilder builder = new SAXBuilder();
    private XMLOutputter xmlOutput = new XMLOutputter();

    protected File getSecurityFile() {
        if (securityFile == null) {
            securityFile = new File(SettingsXmlConfigurationProcessor.USER_MAVEN_CONFIGURATION_HOME,
                    "settings-security.xml");

            if (!securityFile.exists()) {
                try {
                    securityFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(
                            String.format("An error occured while creating '%s'.", securityFile.toString()),
                            e);
                }
            }
        }
        return securityFile;
    }

    /**
     * This is what is done by the maven embedder to encrypte master pwd -> mvn --encrypt-master-password <password>
     * see https://github.com/apache/maven/blob/master/maven-embedder/src/main/java/org/apache/maven/cli/MavenCli.java
     */
    public String encryptMasterPassword(String password) {
        try {
            if (password != null && !password.isEmpty()) {
                return getEncrypter().encryptAndDecorate(password, DefaultSecDispatcher.SYSTEM_PROPERTY_SEC_LOCATION);
            }
            return password;
        } catch (PlexusCipherException | CoreException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is what is done by the maven embedder to encrypte pwd -> mvn --encrypt-password <password>
     * see https://github.com/apache/maven/blob/master/maven-embedder/src/main/java/org/apache/maven/cli/MavenCli.java
     */
    public String encryptPassword(String password) {
        try {
            if (password != null && !password.isEmpty()) {
                Optional<String> currentMasterPassword = getCurrentMasterPassword();
                if (currentMasterPassword.isPresent()) {
                    PlexusCipher encrypter = getEncrypter();
                    String masterPasswd = encrypter.decryptDecorated(currentMasterPassword.get(),
                            DefaultSecDispatcher.SYSTEM_PROPERTY_SEC_LOCATION);
                    return encrypter.encryptAndDecorate(password, masterPasswd);
                }
                throw new IllegalStateException("A mester password is required to perform encryption.");
            }
            return password;
        } catch (PlexusCipherException | CoreException e) {
            throw new RuntimeException(e);
        }
    }

    protected PlexusCipher getEncrypter() throws CoreException {
        return MavenPlugin.getMaven().lookup(PlexusCipher.class);
    }

    public void updateMasterPassword(String encryptedNewValue) {
        try {
            Document document;
            try {
                document = builder.build(getSecurityFile());
                Element rootNode = document.getRootElement();
                rootNode.getChild("master").setText(encryptedNewValue);
            } catch (JDOMException | IllegalStateException e) {
                // Current file is empty / has errors -> write it from scratch
                BonitaStudioLog.warning(String.format(
                        "File '%s' is empty / unreadable -> It will be rewritten from scratch to save the new encrypted maven master password.",
                        getSecurityFile().toString()), ApplicationPlugin.PLUGIN_ID);
                Element rootNode = new Element("settingsSecurity");
                rootNode.addContent(new Element("master").setText(encryptedNewValue));
                document = new Document(rootNode);
            }
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(getSecurityFile()));
        } catch (IOException e) {
            throw new RuntimeException("An error occured while updating master password", e);
        }
    }

    public Optional<String> getCurrentMasterPassword() {
        try {
            Document document = builder.build(getSecurityFile());
            Element rootNode = document.getRootElement();
            return Optional.ofNullable(rootNode.getChildText("master"));
        } catch (IOException e) {
            throw new RuntimeException("An error occured while retrieving master password", e);
        } catch (JDOMException | IllegalStateException e) {
            // File not found, file empty -> ignored
        }
        return Optional.empty();
    }

}
