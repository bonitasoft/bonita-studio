/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.configuration.ui.wizard.EnvironmentWizard;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */
public class EnvironmentFileStore extends EMFFileStore<Environment> {

    private DefaultConfigurationStyler defaultConfigurationStyler;

    public EnvironmentFileStore(String fileName, IRepositoryStore<EnvironmentFileStore> store) {
        super(fileName, store);
        defaultConfigurationStyler = new DefaultConfigurationStyler();
    }

    @Override
    public String getDisplayName() {
        try {
            return getContent().getName();
        } catch (ReadFileStoreException e) {
           return getName();
        }
    }

    @Override
    protected void doSave(Object content) {
        if (content instanceof Environment) {
            Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            emfResource.getContents().add(EcoreUtil.copy((EObject) content));
            Map<String, String> options = new HashMap<>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            try {
                emfResource.save(options);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        EnvironmentWizard wizard = new EnvironmentWizard();
        wizard.setSelection(new StructuredSelection(getDisplayName()));
        new WizardDialog(Display.getDefault().getActiveShell(), wizard).open();
        return null;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("environment.png", ConfigurationPlugin.getDefault());
    }

    private boolean isDefault() {
        String defaultConfiguration = ConfigurationPlugin.getDefault().getPreferenceStore()
                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        return Objects.equals(defaultConfiguration, getDisplayName());
    }

    @Override
    public StyledString getStyledString() {
        StyledString styledString = super.getStyledString();
        if (isDefault()) {
            styledString.append(String.format("  (%s)", Messages.activeConfiguration), defaultConfigurationStyler);
        }
        return styledString;
    }
}
