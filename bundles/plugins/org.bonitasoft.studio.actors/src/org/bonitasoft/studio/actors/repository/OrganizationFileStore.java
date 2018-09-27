/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.repository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.actors.ui.handler.DeployOrganizationHandler;
import org.bonitasoft.studio.actors.ui.wizard.ManageOrganizationWizard;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class OrganizationFileStore extends EMFFileStore implements IDeployable {

    private static final String DEPLOY_ORGA_CMD = "org.bonitasoft.studio.organization.publish";

    public OrganizationFileStore(final String fileName, final OrganizationRepositoryStore store) {
        super(fileName, store);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#
     * getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return getContent().getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#
     * getIcon()
     */
    @Override
    public Image getIcon() {
        return getParentStore().getIcon();
    }

    @Override
    public Organization getContent() {
        final DocumentRoot root = (DocumentRoot) super.getContent();
        if (root != null) {
            return root.getOrganization();
        }
        return null;
    }

    @Override
    protected void doSave(final Object content) {
        if (content instanceof Organization) {
            final Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            final DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
            root.setOrganization((Organization) EcoreUtil.copy((EObject) content));
            emfResource.getContents().add(root);
            try {
                final Map<Object, Object> options = new HashMap<>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                options.put(XMLResource.OPTION_XML_VERSION, "1.0");
                if (emfResource instanceof XMLResourceImpl) {
                    options.putAll(((XMLResourceImpl) emfResource).getDefaultSaveOptions());
                }
                emfResource.save(options);
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public void export(final String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        final Organization organization = getContent();
        final DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
        final Organization exportedCopy = EcoreUtil.copy(organization);
        exportedCopy.setName(null);
        exportedCopy.setDescription(null);
        root.setOrganization(exportedCopy);
        try {
            final File to = new File(targetAbsoluteFilePath);
            if (targetAbsoluteFilePath.endsWith(".xml")) {
                to.getParentFile().mkdirs();
            } else {
                to.mkdirs();
            }

            File target = null;
            if (to.isDirectory()) {
                final String targetFilename = organization.getName() + ".xml";
                target = new File(to, targetFilename);
            } else {
                target = to;
            }
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(target.getName())) {
                    PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
                } else {
                    return;
                }
            }

            final Resource resource = new XMLResourceImpl(URI.createFileURI(target.getAbsolutePath()));
            resource.getContents().add(root);
            final Map<String, Object> options = new HashMap<>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE);
            final XMLProcessor processor = new OrganizationXMLProcessor();
            Files.write(processor.saveToString(resource, options).getBytes("UTF-8"), target);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

    }

    @Override
    protected IWorkbenchPart doOpen() {
        final Wizard newWizard = new ManageOrganizationWizard(getContent());
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), newWizard) {

            @Override
            protected Point getInitialSize() {
                return new Point(1200, 800);
            }

        };
        dialog.open();
        return null;
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    public boolean isCorrectlySyntaxed() {
        if (getContent() == null) {
            return false;
        }
        return true;
    }

    @Override
    public void deploy() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(DeployOrganizationHandler.DEPLOY_ORGA_PARAMETER_NAME, getName());
        executeCommand(DEPLOY_ORGA_CMD, parameters);
    }
}
