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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.ExtensibleWizard;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.AbstractDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.preferences.pages.BonitaAdvancedPreferencePage;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractDefinitionWizard extends ExtensibleWizard {

    private static final String DEF_EXT = "def";

    private boolean editMode = false;

    private IRepositoryFileStore fileStore;

    private final ConnectorDefinition definitionWorkingCopy;

    private DefinitionInformationWizardPage infoPage;

    private final Properties messages;

    protected ConnectorDefinition originalDefinition;

    private DefinitionI18NWizardPage i18nPage;

    private final DefinitionResourceProvider messageProvider;

    private final AbstractDefinitionRepositoryStore<? extends IRepositoryFileStore> defStore;

    public AbstractDefinitionWizard(final String windowTitle,
            final AbstractDefinitionRepositoryStore<? extends IRepositoryFileStore> defStore,
            final DefinitionResourceProvider messageProvider) {
        Assert.isTrue(defStore instanceof IDefinitionRepositoryStore);
        setWindowTitle(windowTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        definitionWorkingCopy = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        definitionWorkingCopy.setVersion("1.0.0");
        this.defStore = defStore;
        this.messageProvider = messageProvider;
        messages = new Properties();
    }

    public AbstractDefinitionWizard(final String windowTitle, final ConnectorDefinition definition,
            final AbstractDefinitionRepositoryStore<? extends IRepositoryFileStore> defStore,
            final DefinitionResourceProvider messageProvider) {
        Assert.isTrue(defStore instanceof IDefinitionRepositoryStore);
        setWindowTitle(windowTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        editMode = true;
        originalDefinition = definition;
        this.defStore = defStore;
        fileStore = defStore.getChild(
                NamingUtils.toConnectorDefinitionFilename(definition.getId(), definition.getVersion(), true), true);
        definitionWorkingCopy = EcoreUtil.copy(definition);
        this.messageProvider = messageProvider;
        messages = messageProvider.getDefaultMessageProperties(definition);
        setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
        final List<ConnectorDefinition> existinfDefinitions = new ArrayList<>();
        for (final ConnectorDefinition def : ((IDefinitionRepositoryStore) defStore).getDefinitions()) {
            if (originalDefinition == null || !(def.getId().equals(originalDefinition.getId())
                    && def.getVersion().equals(originalDefinition.getVersion()))) {
                existinfDefinitions.add(def);
            }
        }

        infoPage = new DefinitionInformationWizardPage(definitionWorkingCopy, messages, existinfDefinitions,
                Pics.getImage("connector.png"), messageProvider);
        if (originalDefinition != null) {
            infoPage.setDisplayName(messageProvider.getConnectorDefinitionLabel(originalDefinition));
            infoPage.setDefinitionDescription(messageProvider.getConnectorDefinitionDescription(originalDefinition));
        }
        addPage(infoPage);
        addPage(new InputsWizardPage(definitionWorkingCopy));
        addPage(new DefinitionPageWizardPage(definitionWorkingCopy, messages, messageProvider));

        addOutputPage();

        i18nPage = new DefinitionI18NWizardPage(definitionWorkingCopy, originalDefinition,
                messageProvider.getExistingLocale(originalDefinition));
        addPage(i18nPage);
    }

    protected void addOutputPage() {
        addPage(new OutputsWizardPage(definitionWorkingCopy));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        final Input faultyMandatoryInput = isDefinitionValid();
        if (faultyMandatoryInput != null) {
            MessageDialog.openError(Display.getCurrent().getActiveShell(),
                    Messages.bind(Messages.inputMandatoryErrorTitle, faultyMandatoryInput.getName()),
                    Messages.bind(Messages.inputMandatoryError, faultyMandatoryInput.getName()));
            return false;
        } else {
            final String defId = NamingUtils.toConnectorDefinitionFilename(definitionWorkingCopy.getId(),
                    definitionWorkingCopy.getVersion(), false);
            final String defFileName = defId + "." + DEF_EXT;

            if (editMode) {
                if (!editConnectorDefinition()) {
                    return false;
                }
            }
            final File imageFile = infoPage.getIconImageFile();
            if (imageFile != null) {
                final IFolder targetFoler = defStore.getResource();
                final IFile iconFile = targetFoler.getFile(definitionWorkingCopy.getIcon());
                try {
                    if (iconFile.exists()
                            && !iconFile.getLocation().toFile().getAbsolutePath().equals(imageFile.getAbsolutePath())) {
                        iconFile.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
                    }
                    BufferedImage image = ImageIO.read(imageFile);
                    image = FileUtil.resizeImage(image, 16);
                    ImageIO.write(image, "PNG", iconFile.getLocation().toFile());
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                    try {
                        iconFile.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
                    } catch (final CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
            fileStore = defStore.createRepositoryFileStore(defFileName);
            fileStore.save(definitionWorkingCopy);
            messageProvider.setConnectorDefinitionLabel(messages, infoPage.getDisplayName());
            messageProvider.setConnectorDefinitionDescription(messages, infoPage.getDefinitionDescription());
            messageProvider.saveMessagesProperties(definitionWorkingCopy, messages);

            reloadCategories();
            final List<IFile> filesToOpen = openPropertiesEditor(i18nPage.getSelectedLocales());

            Display.getDefault().asyncExec(new Runnable() {

                @Override
                public void run() {
                    for (final IFile toOpen : filesToOpen) {
                        try {
                            IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), toOpen);
                        } catch (final PartInitException e) {
                            BonitaStudioLog.error(e);
                        }
                    }
                }
            });

            return true;
        }
    }

    protected boolean editConnectorDefinition() {
        final IPreferenceStore preferenceStore = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        boolean editAnyway = true;
        if (!preferenceStore.getBoolean(BonitaAdvancedPreferencePage.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING)) {
            final MessageDialogWithPrompt dialog = MessageDialogWithPrompt.openOkCancelConfirm(
                    Display.getDefault().getActiveShell(),
                    Messages.confirmConnectorDefEditionTitle,
                    Messages.confirmConnectorDefEditionMsg,
                    Messages.doNotDisplayAgain,
                    false,
                    preferenceStore,
                    BonitaAdvancedPreferencePage.HIDE_CONNECTOR_DEFINITION_CHANGE_WARNING);
            editAnyway = dialog.getReturnCode() == Dialog.OK;
        }

        if (!editAnyway) {
            getContainer().showPage(getPages()[0]);
            return false;
        }

        final String oldDefId = NamingUtils.toConnectorDefinitionFilename(originalDefinition.getId(),
                originalDefinition.getVersion(), false);
        final String defId = NamingUtils.toConnectorDefinitionFilename(definitionWorkingCopy.getId(),
                definitionWorkingCopy.getVersion(), false);
        String oldFileName = oldDefId + "." + DEF_EXT;
        final String defFileName = defId + "." + DEF_EXT;
        if (!oldDefId.equals(defId)) {
            final String oldId = oldDefId + ".properties";
            try {
                defStore.getResource().getFile(oldId).delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        final Set<Locale> existingLocales = messageProvider.getExistingLocale(originalDefinition);
        for (final Locale l : existingLocales) {
            try {
                for (final IResource r : defStore.getResource().members()) {
                    if (r.getFileExtension() != null && r.getFileExtension().equals("properties")) {
                        final String resourceName = r.getName();

                        if (!oldDefId.equals(defId)) {
                            final String oldLocaleFile = oldDefId + "_" + l.toString() + ".properties";
                            if (resourceName.equals(oldLocaleFile)) {
                                final String newLocaleFile = defId + "_" + l.toString() + ".properties";
                                IPath tarhetPath = r.getFullPath().removeLastSegments(1);
                                tarhetPath = tarhetPath.append(newLocaleFile);
                                r.move(tarhetPath, true, AbstractRepository.NULL_PROGRESS_MONITOR);
                            }
                        }
                    }
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

        IRepositoryFileStore oldDefFilseStore = defStore.getChild(oldFileName, true);
        if (!oldFileName.equals(defFileName)) {
            if (oldDefFilseStore != null) {
                oldDefFilseStore.delete();
            }
        } else if (oldDefFilseStore == null) {
            final ConnectorDefinition oldef = defStore.getDefinition(originalDefinition.getId(),
                    originalDefinition.getVersion());
            oldFileName = URI.decode(oldef.eResource().getURI().lastSegment());
            oldDefFilseStore = defStore.getChild(oldFileName, true);
            if (oldDefFilseStore != null) {
                oldDefFilseStore.delete();
            }
        }
        return true;
    }

    private void reloadCategories() {
        boolean reloadCategories = false;
        for (final Category c : definitionWorkingCopy.getCategory()) {
            if (!messageProvider.getAllCategories().contains(c)) {
                reloadCategories = true;
                break;
            }
        }
        if (reloadCategories) {
            messageProvider.loadDefinitionsCategories(null);
        }
    }

    protected List<IFile> openPropertiesEditor(final Set<Locale> selectedLocales) {
        final String defId = NamingUtils.toConnectorDefinitionFilename(definitionWorkingCopy.getId(),
                definitionWorkingCopy.getVersion(), false);
        final List<IFile> filesToOpen = new ArrayList<>();
        for (final Locale l : selectedLocales) {
            try {
                for (final IResource r : defStore.getResource().members()) {
                    if (r.getFileExtension() != null && r.getFileExtension().equals("properties")) {
                        final String resourceName = r.getName();
                        final String localFile = defId + "_" + l.toString() + ".properties";
                        if (resourceName.equals(localFile)) {
                            final Properties oldProperties = new Properties();
                            try {
                                oldProperties.load(((IFile) r).getContents());
                                for (final Object key : messages.keySet()) {
                                    if (oldProperties.get(key) == null) {
                                        oldProperties.put(key, messages.get(key));
                                    }
                                }
                                final FileOutputStream fos = new FileOutputStream(r.getLocation().toFile());
                                oldProperties.store(fos, null);
                                fos.close();
                                r.refreshLocal(IResource.DEPTH_ZERO, AbstractRepository.NULL_PROGRESS_MONITOR);
                                filesToOpen.add((IFile) r);
                            } catch (final IOException e) {
                                BonitaStudioLog.error(e);
                            }

                        }
                    }
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

        final Set<Locale> existingLocales = messageProvider.getExistingLocale(definitionWorkingCopy);
        selectedLocales.removeAll(existingLocales);
        for (final Locale l : selectedLocales) {
            try {
                final String localFile = defId + "_" + l.toString() + ".properties";
                final IFile file = defStore.getResource().getFile(localFile);
                final FileOutputStream fos = new FileOutputStream(file.getLocation().toFile());
                messages.store(fos, null);
                fos.close();
                file.refreshLocal(IResource.DEPTH_ZERO, AbstractRepository.NULL_PROGRESS_MONITOR);
                filesToOpen.add(file);
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        return filesToOpen;
    }

    public ConnectorDefinition getOriginalDefinition() {
        return originalDefinition;
    }

    private Input isDefinitionValid() {
        if (definitionWorkingCopy != null) {
            for (final Input input : definitionWorkingCopy.getInput()) {
                if (input.isMandatory() && (input.getDefaultValue() == null || input.getDefaultValue().isEmpty())) {
                    boolean isPageContainingInput = false;
                    for (final Page page : definitionWorkingCopy.getPage()) {
                        isPageContainingInput = isPageContainingInput || isPageContainingInput(input, page.getWidget());
                    }
                    if (!isPageContainingInput) {
                        return input;
                    }
                }

            }
        }
        return null;
    }

    private boolean isPageContainingInput(final Input input, final List<Component> components) {
        for (final Component component : components) {
            if (component instanceof WidgetComponent) {
                final WidgetComponent widget = (WidgetComponent) component;
                if (widget.getInputName().equals(input.getName())) {
                    return true;
                }
            } else if (component instanceof Group) {
                return isPageContainingInput(input, ((Group) component).getWidget());
            }
        }
        return false;
    }

}
