/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms;

import java.io.File;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.EntryPageFlowType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.resources.ResourcePropertySection;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class ConfirmationPropertySection extends AbstractBonitaDescriptionSection {

    private Button changeConfirmation;
    private Text confirmationPath;
    private ExpressionViewer confirmationMessage;
    protected SelectionListener widgetClickedListener = new SelectionListener() {

        @Override
        public void widgetSelected(final SelectionEvent e) {

            if (e.getSource().equals(confirmationPath) || e.getSource().equals(changeConfirmation)) {
                selectConfirmPageTemplate();
            }
        }

        /**
         *
         */
        private void selectConfirmPageTemplate() {
            Text textField = null;
            textField = confirmationPath;

            final FileDialog fd = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.OPEN);
            fd.setFilterExtensions(new String[] { "*.html", "*.htm", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            if (textField.getText() != null) {
                final File temp = new File(textField.getText());
                if (temp.exists()) {
                    fd.setFilterPath(temp.getParent());
                }
            }
            String res = fd.open();
            if (res != null) {
                final ApplicationResourceRepositoryStore resourceStore = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
                final AbstractProcess process = ModelHelper.getParentProcess(getEObject()) ;
                final String processUUID = ModelHelper.getEObjectID(process) ;
                ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID);
                if (artifact == null) {
                    artifact = (ApplicationResourceFileStore) resourceStore.createRepositoryFileStore(processUUID);
                }
                res = artifact.setConfirmationTemplate(res, getPageFlow());
                textField.setText(res);

                final AssociatedFile af = ProcessFactory.eINSTANCE.createAssociatedFile();
                af.setPath(res);

                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), getPageFlow(), ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_TEMPLATE, af));

            }
        }

        @Override
        public void widgetDefaultSelected(final SelectionEvent e) {

        }
    };
    private EMFDataBindingContext context;
    private PageFlow pageFlow;


    public ConfirmationPropertySection() {

    }

    protected void createConfPanel(final Composite parent) {
        final Composite confPanel = getWidgetFactory().createComposite(parent, SWT.NONE);
        confPanel.setLayout(new GridLayout(1, false));
        confPanel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        createTemplate(confPanel);

    }

    private void createTemplate(final Composite parent) {
        final Composite templates = getWidgetFactory().createComposite(parent);
        templates.setLayout(new GridLayout(4, false));
        final GridData gridD = new GridData(SWT.FILL, SWT.TOP, true, false, 1, 2);
        templates.setLayoutData(gridD);
        gridD.widthHint = 200;
        final CLabel confirmationLabel = getWidgetFactory().createCLabel(templates, Messages.FormsSection_ConfirmationTemplate, SWT.CENTER);
        confirmationLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.FILL, true, false, 1, 1));
        // download template
        final Button download = getWidgetFactory().createButton(templates, Messages.Download, SWT.FLAT);
        download.setLayoutData(new GridData(GridData.END, GridData.FILL, false, false, 1, 1));
        download.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                ResourcePropertySection.downloadDefaultTemplate("bonita_default_confirm.html", "WEB-INF/classes/html/");
            }
        });
        // change the confirmation template button
        changeConfirmation = getWidgetFactory().createButton(templates, Messages.Browse, SWT.FLAT);
        changeConfirmation.setLayoutData(new GridData(GridData.END, GridData.FILL, false, false, 1, 1));
        changeConfirmation.addSelectionListener(widgetClickedListener);

        // clear
        final Button clear2 = getWidgetFactory().createButton(templates, Messages.Clear, SWT.FLAT);
        clear2.setLayoutData(new GridData(GridData.END, GridData.FILL, false, false, 1, 1));
        clear2.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                confirmationPath.setText(""); //$NON-NLS-1$
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), eObject, ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_TEMPLATE, null));

            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }
        });

        // the path to the html template
        confirmationPath = getWidgetFactory().createText(templates, ""); //$NON-NLS-1$
        confirmationPath.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 4, 1));
        confirmationPath.setEditable(false);
        confirmationPath.setToolTipText(Messages.confirmationPathTooltip);

        final Label confirmationMessageLabel = getWidgetFactory().createLabel(templates, Messages.confirmationMessage);
        confirmationMessageLabel.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 4, 1));
        confirmationMessage = new ExpressionViewer(templates, SWT.BORDER, getWidgetFactory(), getEditingDomain(), ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_MESSAGE);
        confirmationMessage.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 4, 1));
        confirmationMessage.setMessage(Messages.confirmationMessageTooltip);

    }

    private PageFlow getPageFlow() {
        return pageFlow;
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        PageFlow tempPageFlow = null;
        if (getEObject() instanceof Lane) {
            tempPageFlow = (PageFlow) getEObject().eContainer();
        }
        if (getEObject() instanceof PageFlow) {
            tempPageFlow = (PageFlow) getEObject();
        }
        if (tempPageFlow != null/* && lastEObject != tempPageFlow */) {
            // lastEObject = tempPageFlow;
            pageFlow = tempPageFlow;
        }
        if (context != null) {
            context.dispose();
        }

        confirmationPath.removeSelectionListener(widgetClickedListener);
        final AssociatedFile confTemplate = getPageFlow().getConfirmationTemplate();
        confirmationPath.setText(confTemplate != null ? confTemplate.getPath() : ""); //$NON-NLS-1$
        confirmationPath.addSelectionListener(widgetClickedListener);

        context = new EMFDataBindingContext();
        Expression confirmationMessageExpression = getPageFlow().getConfirmationMessage();
        if(confirmationMessageExpression == null){
            confirmationMessageExpression = ExpressionFactory.eINSTANCE.createExpression();
            getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getPageFlow(), ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_MESSAGE, confirmationMessageExpression));
        }
        context.bindValue(
                ViewerProperties.singleSelection().observe(confirmationMessage),
                EMFEditProperties.value(getEditingDomain(), ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_MESSAGE).observe(pageFlow));
        confirmationMessage.setInput(pageFlow);
        refreshConfirmationEnablement();
    }

    private void refreshConfirmationEnablement() {
        final PageFlow pageFlow = getPageFlow();
        final EList<Form> formList = pageFlow.getForm();
        boolean containsViewForm = false;
        if (pageFlow instanceof ViewPageFlow) {
            containsViewForm = ((ViewPageFlow) pageFlow).getViewForm().isEmpty();
        }
        if (pageFlow instanceof AbstractProcess) {
            final AbstractProcess process = (AbstractProcess) pageFlow;
            confirmationMessage.getControl().setEnabled(!formList.isEmpty()
                    || !containsViewForm
                    || !process.getRecapForms().isEmpty()
                    || EntryPageFlowType.REDIRECT.equals(pageFlow.getEntryPageFlowType()));
        } else {
            confirmationMessage.getControl().setEnabled(!formList.isEmpty()
                    || !containsViewForm
                    || EntryPageFlowType.REDIRECT.equals(pageFlow.getEntryPageFlowType()));
        }
    }

    @Override
    public String getSectionDescription() {
        return String.format("%s\n%s", Messages.confirmationPropertySectionDescription,org.bonitasoft.studio.common.Messages.deprecatedLegacyMode);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getDescriptionSeverity()
     */
    @Override
    protected int getDescriptionSeverity() {
        return IStatus.WARNING;
    }
    
    @Override
    protected void createContent(final Composite parent) {
        final Composite composite = getWidgetFactory().createComposite(parent);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createConfPanel(composite);
    }

}
