/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.HtmlTemplateGenerator;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 */
public class TemplateGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    /**
     * @author Baptiste Mesta
     *
     */
    private final class GenerateFormTemplate implements IRunnableWithProgress {
        private String filePath;

        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
            monitor.setTaskName(Messages.templateGenerationInProgress);
            filePath = ((HtmlTemplateGenerator) ExporterService.getInstance().getExporterService(SERVICE_TYPE.HtmlTemplateGenerator)).createXhtmlTemplate(form, false,null,false);
        }

        public String getFilePath(){
            return filePath;
        }
    }

    private TransactionalEditingDomain editingDomain;
    private Form form;
    private final GridDataFactory layoutForIsSetImages = GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).hint(28, 28);
    private Label isSetLabel;
    private Button editTemplateButton;
    private Button clear;

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
        GridData gdc = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gdc.widthHint = 300;
        composite.setLayoutData(gdc);
        composite.setLayout(new GridLayout(4, false));

        isSetLabel = widgetFactory.createLabel(composite, "");//$NON-NLS-1$
        isSetLabel.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
        layoutForIsSetImages.applyTo(isSetLabel);

        editTemplateButton = new Button(composite, SWT.FLAT);
        editTemplateButton.setText(Messages.Edit);
        editTemplateButton.setLayoutData(GridDataFactory.fillDefaults().create());
        clear = new Button(composite, SWT.FLAT);
        clear.setText(Messages.Clear);
        clear.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 1, 1));
        final Button useDefaultTemplate = new Button(composite, SWT.FLAT);
        useDefaultTemplate.setText(Messages.Restore);
        useDefaultTemplate.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 1, 1));

        editTemplateButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                AssociatedFile htmlTemplate = form.getHtmlTemplate();
                if (htmlTemplate != null) {
                    IFile file = WebTemplatesUtil.getIFile(htmlTemplate.getPath());
                    if (file != null && file.exists()) {
                        try {

                            FileEditorInput fileEditorInput = new FileEditorInput(file){
                                @Override
                                public String getName() {
                                    return form.getName();
                                }
                            };
                            IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), fileEditorInput,
                                    IDE.getEditorDescriptor(file.getName()).getId(), true);
                        } catch (PartInitException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }
                }
            }
        });
        clear.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                boolean confirm = true;
                AssociatedFile htmlTemplate = form.getHtmlTemplate();
                if (htmlTemplate != null && htmlTemplate.getPath() != null && !htmlTemplate.getPath().isEmpty()) {
                    File template = WebTemplatesUtil.getFile(htmlTemplate.getPath());
                    if (template != null && template.exists()) {
                        confirm = MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.confirm_title,
                                Messages.confirm_remove_template);
                    }
                }
                if(confirm){
                    editingDomain.getCommandStack().execute(new SetCommand(editingDomain, form, ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE, null));
                    ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                    String processUUID = ModelHelper.getEObjectID(ModelHelper.getParentProcess(form));
                    ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID);
                    file.removeResource(htmlTemplate.getPath());
                }
                setPathIsFilled(false);
            }
        });
        useDefaultTemplate.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                boolean confirm = true;
                AssociatedFile htmlTemplate = form.getHtmlTemplate();
                if (htmlTemplate != null && htmlTemplate.getPath() != null && !htmlTemplate.getPath().isEmpty()) {
                    File template = WebTemplatesUtil.getFile(htmlTemplate.getPath());
                    if (template != null && template.exists()) {
                        confirm = MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.confirm_title,
                                Messages.confirm_replace_template);
                    }
                }
                if (confirm) {
                    try {
                        restoreTemplate();
                        setPathIsFilled(true);
                    } catch (Exception e1) {
                        new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.error, Messages.error_apply_template,
                                e1).open();
                        BonitaStudioLog.error(e1);
                        setPathIsFilled(false);
                    }
                }
            }

        });

    }

    /**
     * @throws IOException
     * @throws InterruptedException
     * @throws InvocationTargetException
     * 
     */
    protected void restoreTemplate() throws IOException, InvocationTargetException, InterruptedException {

        GenerateFormTemplate generateForm = new GenerateFormTemplate();
        PlatformUI.getWorkbench().getProgressService().busyCursorWhile(generateForm);
        String filePath = generateForm.getFilePath();
        File from = new File(filePath);
        ApplicationResourceRepositoryStore resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
        String processUUID = ModelHelper.getEObjectID(ModelHelper.getParentProcess(form));
        ApplicationResourceFileStore file = (ApplicationResourceFileStore) resourceStore.getChild(processUUID);
        if (file == null) {
            file = (ApplicationResourceFileStore) resourceStore.createRepositoryFileStore(processUUID) ;
        }
        String res = file.setPageTemplate(filePath, form);
        AssociatedFile af = ProcessFactory.eINSTANCE.createAssociatedFile();
        af.setPath(res);
        editingDomain.getCommandStack().execute(new SetCommand(editingDomain, form, ProcessPackage.Literals.RESOURCE_CONTAINER__HTML_TEMPLATE, af));
        from.delete();
    }

    public String getLabel() {
        return Messages.ResourceSection_HTMLTemplate;
    }

    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof Form;
    }

    public void refresh() {
        // reload html template
        AssociatedFile htmlT = form.getHtmlTemplate();
        setPathIsFilled(htmlT != null && htmlT.getPath() != null && !htmlT.getPath().isEmpty());

    }

    /**
     * @param b
     */
    private void setPathIsFilled(boolean isFilled) {
        if(isSetLabel != null){
            if (isFilled) {
                isSetLabel.setImage(Pics.getImage(PicsConstants.greenCheck20));
            } else {
                isSetLabel.setImage(Pics.getImage(PicsConstants.empty20));
            }
        }
        if (editTemplateButton != null) {
            editTemplateButton.setEnabled(isFilled);
        }
        if (clear != null) {
            clear.setEnabled(isFilled);
        }
    }

    public void setEObject(EObject object) {
        form = (Form) object;
    }

    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;

    }

    public void setSelection(ISelection selection) {

    }

    public void dispose() {
    }

}
