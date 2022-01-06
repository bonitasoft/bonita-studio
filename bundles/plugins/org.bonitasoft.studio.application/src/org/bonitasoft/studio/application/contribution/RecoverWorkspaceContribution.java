/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.contribution;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RecoverWorkspaceContribution {

    private static final String WORKSPACE_RECOVERY_MODE_PROPERTY = "workspace.recovery.mode";
    private static final String PROJECT_DESCRIPTOR_FILE = ".project";

    public void execute() {
        boolean revoveryModeEnabled = isRevoveryModeEnabled();
        if (revoveryModeEnabled) {
            BonitaStudioLog.warning("WORKSPACE RECOVERY MODE IS ENABLED", ApplicationPlugin.PLUGIN_ID);
        }
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        var projectsOutOfSync = findOutOfSyncProjects(workspace);
        if (!projectsOutOfSync.isEmpty()) {
            if (revoveryModeEnabled) {
                importProjectsOutOfSync(workspace, projectsOutOfSync);
            } else {
                BonitaStudioLog.warning(listProjectsOutOfSync(projectsOutOfSync), ApplicationPlugin.PLUGIN_ID);
                BonitaStudioLog.warning(String.format(
                        "You can enable the WORKSPACE RECOVERY MODE to fix above issues.%nSet the %s to true in .ini file matching the Studio executable name and restart.",
                        WORKSPACE_RECOVERY_MODE_PROPERTY), ApplicationPlugin.PLUGIN_ID);
            }
        }
    }

    private void importProjectsOutOfSync(IWorkspace workspace, List<File> projectsOutOfSync) {
        for (File projectFolder : projectsOutOfSync) {
            IProject project = workspace.getRoot().getProject(projectFolder.getName());
            if (!project.exists()) {
                BonitaStudioLog.warning(String.format("Importing '%s' into the workspace...", projectFolder.getName()),
                        ApplicationPlugin.PLUGIN_ID);
                try {
                    project.create(new NullProgressMonitor());
                    project.close(new NullProgressMonitor());
                    BonitaStudioLog.warning(String.format("'%s' has been imported.", projectFolder.getName()),
                            ApplicationPlugin.PLUGIN_ID);
                } catch (CoreException e) {
                    BonitaStudioLog.error(String.format("Failed to import '%s'", projectFolder.getName()), e);
                }
            }
        }
    }

    private List<File> findOutOfSyncProjects(IWorkspace workspace) {
        IWorkspaceRoot root = workspace.getRoot();
        return Stream.of(workspace.getRoot().getLocation().toFile().listFiles())
                .filter(File::isDirectory)
                .filter(folder -> new File(folder, PROJECT_DESCRIPTOR_FILE).exists()
                        && hasNature(new File(folder, PROJECT_DESCRIPTOR_FILE), BonitaProjectNature.NATURE_ID))
                .filter(folder -> !root.getProject(folder.getName()).exists())
                .collect(Collectors.toList());
    }

    private String listProjectsOutOfSync(List<File> projectsOutOfSync) {
        var sb = new StringBuilder("Some inconsistencies between the file system and the workspace has been found:\n");
        projectsOutOfSync.forEach(project -> sb.append(String
                .format("- %s has been found on the file system but not in the workspace.%n", project.getName())));
        return sb.toString();
    }

    private boolean isRevoveryModeEnabled() {
        return Objects.equals(Boolean.parseBoolean(System.getProperty(WORKSPACE_RECOVERY_MODE_PROPERTY)),
                Boolean.TRUE);
    }

    private boolean hasNature(File descriptorFile, String natureId) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        try (InputStream is = java.nio.file.Files.newInputStream(descriptorFile.toPath())) {
            Document document = asXMLDocument(is);
            NodeList natures = (NodeList) xPath.evaluate("//projectDescription/natures/nature/text()", document,
                    XPathConstants.NODESET);
            for (int i = 0; i < natures.getLength(); ++i) {
                Node item = natures.item(i);
                if (Objects.equals(item.getTextContent(), natureId)) {
                    return true;
                }
            }
        } catch (IOException | XPathExpressionException e) {
            BonitaStudioLog.error(e);
        }
        return false;
    }

    private static Document asXMLDocument(InputStream source) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(source));
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return null;
        }
    }

}
