/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.core.constraint.BuildMVELExpressionJob;
import org.bonitasoft.studio.contract.core.constraint.ConstraintInputIndexer;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.contentassist.ContractInputCompletionProposalComputer;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.part.FileEditorInput;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintExpressionWizardPage extends WizardPage implements IDocumentListener {

    private static final String MVEL_BASICS_URL = "http://mvel.codehaus.org/Language+Guide+for+2.0";

    private final ContractConstraint constraint;
    private FileEditorInput input;
    private final List<ContractInput> inputs;
    private IAnnotationModel annotationModel;
    private final BuildMVELExpressionJob buildJob;
    private ConstraintInputIndexer inputIndexer;
    private SourceViewer sourceViewer;
    private IObservableValue expressionContentObservable;
    private IObservableList inputsObservable;
    private GroovyViewer groovyViewer;

    public ContractConstraintExpressionWizardPage(final ContractConstraint constraint, final List<ContractInput> inputs) {
        super(ContractConstraintExpressionWizardPage.class.getName());
        setDescription(Messages.constraintEditorDescription);
        this.constraint = constraint;
        this.inputs = inputs;
        buildJob = new BuildMVELExpressionJob(inputs);
    }

    protected FileEditorInput createTmpGroovyResource(final ContractConstraint constraint) {
        final IFile file = RepositoryManager.getInstance().getCurrentRepository().getProject().getFile("tmp" + System.currentTimeMillis() + ".groovy");

        try {
            String expression = constraint.getExpression();
            if (expression == null) {
                expression = "";
            }
            file.create(new ByteArrayInputStream(expression.getBytes(Charset.forName("UTF-8"))), IResource.FORCE, null);
        } catch (final CoreException e) {
            e.printStackTrace();
        }
        return new FileEditorInput(file);
    }


    @Override
    public void dispose() {
        final IFile resource = (IFile) input.getAdapter(IFile.class);
        try {
            resource.delete(true, null);
        } catch (final CoreException e) {
            BonitaStudioLog.error("Failed to delete temporary groovy file", e, ContractPlugin.PLUGIN_ID);
        }
        if (groovyViewer != null) {
            groovyViewer.dispose();
        }
        super.dispose();
    }

    protected SourceViewer getSourceViewer() {
        return groovyViewer.getSourceViewer();
    }

    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext context = new EMFDataBindingContext();
        final Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout());
        final GroovyEditor editor = new MVELEditor();
        input = createTmpGroovyResource(constraint);
        groovyViewer = new GroovyViewer(container, input, editor);
        inputIndexer = new ConstraintInputIndexer(inputs, editor.getGroovyCompilationUnit());

        getSourceViewer().getTextWidget().setData(ContractInputCompletionProposalComputer.INPUTS, inputs);
        getSourceViewer().getDocument().addDocumentListener(this);
        annotationModel = getSourceViewer().getAnnotationModel();

        setControl(container);
        expressionContentObservable = EMFObservables.observeValue(constraint, ProcessPackage.Literals.CONTRACT_CONSTRAINT__EXPRESSION);
        inputsObservable = EMFObservables.observeList(constraint, ProcessPackage.Literals.CONTRACT_CONSTRAINT__INPUT_NAMES);
        context.addValidationStatusProvider(new ConstraintExpressionEditorValidator(expressionContentObservable, inputsObservable));
        WizardPageSupport.create(this, context);
    }


    @Override
    public void documentAboutToBeChanged(final DocumentEvent event) {
        //NOTHING TO DO
    }

    @Override
    public void documentChanged(final DocumentEvent event) {
        final String expression = event.getDocument().get();
        expressionContentObservable.setValue(expression);
        buildJob.setExpression(expression);
        buildJob.schedule();
        buildJob.addJobChangeListener(new UpdateCompilerAnnotationListener(annotationModel));

        inputIndexer.setExpression(expression);
        inputIndexer.addJobChangeListener(new UpdateInputReferenceListener(inputsObservable));
        inputIndexer.schedule();
    }


    @Override
    public void performHelp() {
        URL url = null;
        try {
            url = new URL(MVEL_BASICS_URL);
        } catch (final MalformedURLException e) {
            BonitaStudioLog.error("Invalid URL format for :" + MVEL_BASICS_URL, e, ContractPlugin.PLUGIN_ID);
        }
        IWebBrowser browser = null;
        try {
            browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL, null, null, null);
            browser.openURL(url);
        } catch (final PartInitException e) {
            BonitaStudioLog.error("Failed to oepn browser to display contract constraint expression help content", e, ContractPlugin.PLUGIN_ID);
        }
    }
}
