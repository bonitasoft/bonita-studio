/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.lookandfeel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore.ResourceType;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.resources.SaveAsTemplateWizard;
import org.bonitasoft.studio.properties.sections.resources.SelectLocalTemplateWizard;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 */
public class LookAndFeelPropertySection extends AbstractBonitaDescriptionSection {

    private Button saveAsTemplate;
    private Label globalConsultationIsSet;
    private Label errorIsSet;
    protected Label hostPageIsSet;
    private Label processIsSet;
    private Label globalPageIsSet;
    private final List<LookAndFeelPropertySectionListener> listeners = new ArrayList<LookAndFeelPropertySectionListener>();

    ResourceContainer resourceContainer;
    private Label basedOnValue;
    private final GridDataFactory layoutForIsSetImages = GridDataFactory
            .swtDefaults().hint(28, 28);

    @Override
    protected void createContent(final Composite parent) {
        listeners.clear();
        final Composite templates = createTemplates(parent);
        createButtonsTemplate(templates);

        // Bug number : WEB-1636
        // If uncomment this line, don't forget to uncomment other related in
        // this file
        // hostPageIsSet = createTemplate(templates, ResourceType.HOST_PAGE);

        processIsSet = createTemplate(templates, ResourceType.PROCESS_TEMPLATE);
        globalPageIsSet = createTemplate(templates,
                ResourceType.GLOBAL_PAGE_TEMPLATE);
        globalConsultationIsSet = createTemplate(templates,
                ResourceType.GLOBAL_CONSULTATION_TEMPLATE);
        errorIsSet = createTemplate(templates, ResourceType.ERROR_TEMPLATE);
    }

    /**
     * @param templates
     */
    private void createButtonsTemplate(final Composite templates) {
        final Composite buttonsComposite = getWidgetFactory().createComposite(
                templates);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults()
                .span(6, 1).create());
        buttonsComposite.setLayout(new GridLayout(4, false));
        final Button useLocalTemplate = getWidgetFactory().createButton(
                buttonsComposite, Messages.localTemplate, SWT.FLAT);
        useLocalTemplate.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                selectLocalTemplate();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent arg0) {
            }
        });

        saveAsTemplate = getWidgetFactory().createButton(buttonsComposite,
                Messages.ResourceSection_SaveAsTemplate, SWT.FLAT);
        saveAsTemplate.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SaveAsTemplateWizard wizard = new SaveAsTemplateWizard();
                final Shell shell = PlatformUI.getWorkbench()
                        .getActiveWorkbenchWindow().getShell();
                final CustomWizardDialog dialog = new CustomWizardDialog(shell,
                        wizard, Messages.save);
                if (dialog.open() == Dialog.OK) {
                    final ApplicationResourceRepositoryStore store = RepositoryManager
                            .getInstance().getRepositoryStore(
                                    ApplicationResourceRepositoryStore.class);
                    final String processUUID = ModelHelper
                            .getEObjectID(getAbstractProcess());
                    final ApplicationResourceFileStore webTemplateArtifact = store
                            .getChild(processUUID);
                    try {
                        WebTemplatesUtil.convertWebTemplateToTheme(
                                webTemplateArtifact, wizard.getTemplateName(),
                                wizard.getPreviewPath(),
                                new NullProgressMonitor());
                    } catch (final Exception e1) {
                        new BonitaErrorDialog(shell, Messages.Error,
                                Messages.saveAsTemplate_error, e1).open();
                        BonitaStudioLog.error(e1);
                    }
                }
            }
        });

        final Label basedOnLabel = getWidgetFactory().createLabel(buttonsComposite,
                "");
        basedOnLabel.setText(Messages.ResourceSection_BasedOnLookAndFeel);

        basedOnValue = getWidgetFactory().createLabel(buttonsComposite, "");

    }

    private Composite createTemplates(final Composite parent) {
        final Composite templates = getWidgetFactory().createComposite(parent);
        final GridLayout gl = new GridLayout(6, false);
        gl.horizontalSpacing = 10;
        templates.setLayout(gl);
        return templates;
    }

    /**
     * @param templates
     */
    private Label createTemplate(final Composite templates,
            final ResourceType templateType) {

        final CLabel templateLabel = getWidgetFactory().createCLabel(templates,
                getLabelFor(templateType), SWT.CENTER);
        templateLabel.setLayoutData(new GridData(GridData.BEGINNING,
                GridData.FILL, false, false, 1, 1));
        final ControlDecoration hint = new ControlDecoration(templateLabel, SWT.RIGHT);
        hint.setImage(Pics.getImage(PicsConstants.hint));
        hint.setDescriptionText(getHintFor(templateType));

        // the path to the html template
        final Label isSetLabel = getWidgetFactory().createLabel(templates, "");
        isSetLabel.setLayoutData(new GridData(GridData.FILL, GridData.CENTER,
                true, false, 1, 1));
        layoutForIsSetImages.applyTo(isSetLabel);
        final Button clear = getWidgetFactory().createButton(templates, "", SWT.FLAT);//$NON-NLS-1$
        clear.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false,
                false, 1, 1));
        clear.setImage(Pics.getImage(PicsConstants.remove));
        clear.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                setPathIsFilled(getIsSetImage(templateType), false);
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), resourceContainer,
                                getFeature(templateType), null));
            }
        });
        // change the html template button
        final Button changeTemplate = getWidgetFactory().createButton(templates,
                Messages.Browse, SWT.FLAT);
        GridDataFactory.swtDefaults().applyTo(changeTemplate);
        final BrowseForPageListener browseForPageListener = new BrowseForPageListener(
                templateType, getFeature(templateType), isSetLabel);
        changeTemplate.addListener(SWT.Selection, browseForPageListener);
        // At this point, getEObject, getAbstractProcess & so on return "null"
        // Then listener is incomplete and must its eObject must be set later
        listeners.add(browseForPageListener);

        final Button editTemplateButton = getWidgetFactory().createButton(
                templates, Messages.Edit, SWT.FLAT);
        GridDataFactory.swtDefaults().applyTo(editTemplateButton);
        final EditPageListener editPageListener = new EditPageListener(templateType);
        editTemplateButton.addSelectionListener(editPageListener);
        isSetLabel.setData(editTemplateButton);
        listeners.add(editPageListener);
        // download template
        final Button downloadTemplate = getWidgetFactory().createButton(templates,
                Messages.Download, SWT.FLAT);
        GridDataFactory.swtDefaults().applyTo(downloadTemplate);
        downloadTemplate.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                downloadDefaultTemplate(templateType, null);
            }

        });
        return isSetLabel;
    }

    private String getHintFor(final ResourceType templateType) {
        switch (templateType) {
            case PROCESS_TEMPLATE:
                return Messages.ResourceSection_ProcessTemplate_hint;
            case ERROR_TEMPLATE:
                return Messages.ResourceSection_ErrorTemplate_hint;
                // case HOST_PAGE:
                // return Messages.ResourceSection_HostPageTemplate_hint;
            case GLOBAL_PAGE_TEMPLATE:
                return Messages.ResourceSection_PageTemplate_hint;
            case GLOBAL_CONSULTATION_TEMPLATE:
                return Messages.ResourceSection_ViewTemplate_hint;
        }
        return null;
    }

    /**
     * @param templateType
     * @return
     */
    protected EStructuralFeature getFeature(final ResourceType templateType) {
        switch (templateType) {
            case PROCESS_TEMPLATE:
                return ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE;
            case ERROR_TEMPLATE:
                return ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE;
                // case HOST_PAGE:
                // return ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE;
            case GLOBAL_PAGE_TEMPLATE:
                return ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE;
            case GLOBAL_CONSULTATION_TEMPLATE:
                return ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE;
        }
        return null;
    }

    /**
     * @param templateType
     * @return
     */
    protected Label getIsSetImage(final ResourceType templateType) {
        switch (templateType) {
            case PROCESS_TEMPLATE:
                return processIsSet;
            case ERROR_TEMPLATE:
                return errorIsSet;
                // case HOST_PAGE:
                // return hostPageIsSet;
            case GLOBAL_PAGE_TEMPLATE:
                return globalPageIsSet;
            case GLOBAL_CONSULTATION_TEMPLATE:
                return globalConsultationIsSet;
        }
        return null;
    }

    private String getLabelFor(final ResourceType templateType) {
        switch (templateType) {
            case PROCESS_TEMPLATE:
                return Messages.ResourceSection_ProcessTemplate;
            case ERROR_TEMPLATE:
                return Messages.ResourceSection_ErrorTemplate;
                // case HOST_PAGE:
                // return Messages.ResourceSection_HostPageTemplate;
            case GLOBAL_PAGE_TEMPLATE:
                return Messages.ResourceSection_PageTemplate;
            case GLOBAL_CONSULTATION_TEMPLATE:
                return Messages.ResourceSection_ViewTemplate;
        }
        return null;
    }

    /**
     * @param path
     * @return
     */
    protected boolean isEditable(final String path) {
        if (path != null && path.length() > 0) {
            final File file = WebTemplatesUtil.getFile(path);
            if (file != null && file.exists() && !file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    protected void selectLocalTemplate() {
        final SelectLocalTemplateWizard wizard = new SelectLocalTemplateWizard();
        final Shell shell = getPart().getSite().getShell();
        final int res = new WizardDialog(shell, wizard) {

            @Override
            protected org.eclipse.swt.graphics.Point getInitialSize() {
                return new org.eclipse.swt.graphics.Point(650, 500);
            };
        }.open();
        if (res == Dialog.OK) {
            final AbstractProcess process = getAbstractProcess();
            final ApplicationLookNFeelFileStore selectedTheme = wizard
                    .getSelectedTemplate();
            if (MessageDialog.openQuestion(shell,
                    Messages.ResourceSection_OverWrite_title,
                    Messages.ResourceSection_OverWrite_msg)) {
                final CompoundCommand cc = WebTemplatesUtil.createAddTemplateCommand(
                        getEditingDomain(), process, selectedTheme,
                        new NullProgressMonitor());
                getEditingDomain().getCommandStack().execute(cc);
                refresh();
            }
        }
    }

    protected AbstractProcess getAbstractProcess() {
        final EObject eo = getEObject();
        if (eo instanceof AbstractProcess) {
            return (AbstractProcess) eo;
        } else {
            return ModelHelper.getParentProcess(eo);
        }
    }

    /**
     * if the EObject has changed refresh all
     */
    @Override
    public void refresh() {
        super.refresh();
        if (getEObject() != null) {
            for (final LookAndFeelPropertySectionListener listener : listeners) {
                listener.setProcess(getAbstractProcess());
                listener.setEditDomain(getEditingDomain());
            }

            // if it's a lane take the eContainer (the pool)
            if (getEObject() instanceof Lane) {
                resourceContainer = (ResourceContainer) getEObject()
                        .eContainer();
            } else if (getEObject() instanceof ResourceContainer) {
                resourceContainer = (ResourceContainer) getEObject();
            }
            final String basedOnLookAndFeel = ((AbstractProcess) resourceContainer)
                    .getBasedOnLookAndFeel();

            basedOnValue
                    .setText(basedOnLookAndFeel != null ? basedOnLookAndFeel
                            : "");

            // reload html template
            AssociatedFile template = ((AbstractProcess) resourceContainer)
                    .getProcessTemplate();
            setPathIsFilled(processIsSet,
                    template != null && template.getPath() != null);

            // reload page template
            template = ((AbstractProcess) resourceContainer).getPageTemplate();
            setPathIsFilled(globalPageIsSet,
                    template != null && template.getPath() != null);

            // reload consultation template
            template = ((AbstractProcess) resourceContainer)
                    .getConsultationTemplate();
            setPathIsFilled(globalConsultationIsSet, template != null
                    && template.getPath() != null);

            // reload error template
            template = ((AbstractProcess) resourceContainer).getErrorTemplate();
            setPathIsFilled(errorIsSet, template != null
                    && template.getPath() != null);

            // template = ((AbstractProcess) resourceContainer).getHostPage();
            // setPathIsFilled(hostPageIsSet,
            // template != null && template.getPath() != null);

            // reload resources folder+files
            final List<AssociatedFile> toRemoveFolders = new ArrayList<AssociatedFile>();
            final List<AssociatedFile> toRemoveFiles = new ArrayList<AssociatedFile>();
            final CompoundCommand cc = new CompoundCommand();
            if (toRemoveFolders.size() > 0) {
                cc.append(new RemoveCommand(
                        getEditingDomain(),
                        resourceContainer,
                        ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS,
                        toRemoveFolders));
            }
            if (toRemoveFiles.size() > 0) {
                cc.append(new RemoveCommand(
                        getEditingDomain(),
                        resourceContainer,
                        ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES,
                        toRemoveFiles));
            }
            if (!cc.isEmpty()) {
                getEditingDomain().getCommandStack().execute(cc);
            }
        }
        basedOnValue.getParent().getParent().layout(true, true);
    }

    /**
     * download login page from bonita.war
     *
     * @param name
     *        resource name
     * @param outFile
     *        can be null if you want FileDialog to choose
     */
    public static void downloadDefaultTemplate(final ResourceType templateType,
            String outFile) {
        final LookNFeelRepositoryStore lookNFeelStore = RepositoryManager
                .getInstance().getRepositoryStore(
                        LookNFeelRepositoryStore.class);
        final String themeId = BonitaStudioPreferencesPlugin.getDefault()
                .getPreferenceStore()
                .getString(BonitaPreferenceConstants.DEFAULT_APPLICATION_THEME);
        final ApplicationLookNFeelFileStore fileStore = (ApplicationLookNFeelFileStore) lookNFeelStore
                .getChild(themeId);
        File askedFile = null;
        switch (templateType) {
            case PROCESS_TEMPLATE:
                askedFile = fileStore.getProcessTemplate();
                break;
            case ERROR_TEMPLATE:
                askedFile = fileStore.getErrorTemplate();
                break;
            // case HOST_PAGE:
            // askedFile = fileStore.getHostPage();
            // break;
            case GLOBAL_PAGE_TEMPLATE:
                askedFile = fileStore.getGlobalPageTemplate();
                break;
            case GLOBAL_CONSULTATION_TEMPLATE:
                askedFile = fileStore.getConsultationTemplate();
                break;
            default:
                break;
        }
        if (askedFile != null) {
            if (outFile == null) {
                final FileDialog fd = new FileDialog(Display.getCurrent()
                        .getActiveShell(), SWT.SAVE);
                fd.setFileName(askedFile.getName());//$NON-NLS-1$
                outFile = fd.open();
            }
            if (outFile != null) {
                try {
                    final File template = new File(outFile);
                    template.delete();
                    FileUtil.copy(askedFile, template);
                } catch (final FileNotFoundException e) {
                    BonitaStudioLog.log(e.getMessage());
                } catch (final IOException e) {
                    BonitaStudioLog.log(e.getMessage());
                }
            }
        }
    }

    @Override
    public void dispose() {
        listeners.clear();
        super.dispose();
    }

    /**
     * @param isSetLabel
     * @param isFilled
     *        TODO
     */
    static void setPathIsFilled(final Label isSetLabel, final boolean isFilled) {
        final Object data = isSetLabel.getData();
        if (data instanceof Button) {
            ((Button) data).setEnabled(isFilled);
        }
        if (isFilled) {
            isSetLabel.setImage(Pics.getImage(PicsConstants.greenCheck20));
        } else {
            isSetLabel.setImage(Pics.getImage(PicsConstants.empty20));
        }
    }

    @Override
    public String getSectionDescription() {
        return Messages.looknfeelPropertySectionDescription;
    }

}
