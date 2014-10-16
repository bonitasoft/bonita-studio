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
import java.util.ResourceBundle;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.contract.ContractPlugin;
import org.bonitasoft.studio.contract.core.constraint.BuildMVELExpressionJob;
import org.bonitasoft.studio.contract.core.constraint.ConstraintInputIndexer;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.contract.ui.property.constraint.edit.editor.contentassist.ContractInputCompletionProposalComputer;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
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
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;


/**
 * @author Romain Bioteau
 *
 */
public class ContractConstraintExpressionWizardPage extends WizardPage implements IDocumentListener {

    private static final String MVEL_BASICS_URL = "http://mvel.codehaus.org/Language+Guide+for+2.0";
    private final ContractConstraint constraint;
    private final FileEditorInput input;
    private IHandlerActivation fHandlerActivation;
    private final List<ContractInput> inputs;
    private IAnnotationModel annotationModel;
    private final BuildMVELExpressionJob buildJob;
    private ConstraintInputIndexer inputIndexer;
    private SourceViewer sourceViewer;
    private IObservableValue expressionContentObservable;
    private IObservableList inputsObservable;

    public ContractConstraintExpressionWizardPage(final ContractConstraint constraint, final List<ContractInput> inputs) {
        super(ContractConstraintExpressionWizardPage.class.getName());
        setDescription(Messages.constraintEditorDescription);
        this.constraint = constraint;
        input = createTmpGroovyResource(constraint);
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

    protected void enableContextAssitShortcut(final SourceViewer sourceViewer) {
        final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
        fHandlerActivation = handlerService.activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, new AbstractHandler() {

            @Override
            public Object execute(final ExecutionEvent event) throws ExecutionException {
                sourceViewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                return null;
            }
        });
    }

    protected void disableContextAssitShortcut() {
        if (fHandlerActivation != null) {
            final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getAdapter(IHandlerService.class);
            if (handlerService != null) {
                handlerService.deactivateHandler(fHandlerActivation);
            }
            fHandlerActivation.clearResult();
        }
    }


    @Override
    public void dispose() {
        final IFile resource = (IFile) input.getAdapter(IFile.class);
        try {
            resource.delete(true, null);
        } catch (final CoreException e) {
            BonitaStudioLog.error("Failed to delete temporary groovy file", e, ContractPlugin.PLUGIN_ID);
        }
        disableContextAssitShortcut();
        super.dispose();
    }

    protected SourceViewer getSourceViewer() {
        return sourceViewer;
    }

    protected void setSourceViewer(final SourceViewer sourceViewer) {
        this.sourceViewer = sourceViewer;
    }

    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext context = new EMFDataBindingContext();
        final Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout());
        final GroovyEditor editor = new MVELEditor();
        try {
            editor.getDocumentProvider().connect(input);
        } catch (final CoreException e1) {
            e1.printStackTrace();
        }
        try {
            editor.init(new DummyEditorSite(getShell(), editor), input);
        } catch (final PartInitException e) {
            e.printStackTrace();
        }
        editor.createPartControl(container);
        editor.createJavaSourceViewerConfiguration();
        setSourceViewer((SourceViewer) editor.getViewer());
        inputIndexer = new ConstraintInputIndexer(inputs, editor.getGroovyCompilationUnit());

        getSourceViewer().getTextWidget().setData(ContractInputCompletionProposalComputer.INPUTS, inputs);
        getSourceViewer().getDocument().addDocumentListener(this);
        annotationModel = getSourceViewer().getAnnotationModel();

        addKeybindings(editor, getSourceViewer());
        enableContextAssitShortcut(getSourceViewer());
        setControl(container);
        expressionContentObservable = EMFObservables.observeValue(constraint, ProcessPackage.Literals.CONTRACT_CONSTRAINT__EXPRESSION);
        inputsObservable = EMFObservables.observeList(constraint, ProcessPackage.Literals.CONTRACT_CONSTRAINT__INPUT_NAMES);
        context.addValidationStatusProvider(new ConstraintExpressionEditorValidator(expressionContentObservable, inputsObservable));
        WizardPageSupport.create(this, context);
    }

    protected void addKeybindings(final GroovyEditor editor, final SourceViewer viewer) {
        viewer.getTextWidget().addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(final KeyEvent e) {
                if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'z') {
                    final TextOperationAction action = new TextOperationAction(
                            ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Undo.", editor, ITextOperationTarget.UNDO); //$NON-NLS-1$ //$NON-NLS-2$
                    action.run();
                } else if ((e.stateMask == SWT.CTRL || e.stateMask == SWT.COMMAND) && e.keyCode == 'y') {
                    final TextOperationAction action = new TextOperationAction(
                            ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Redo.", editor, ITextOperationTarget.REDO); //$NON-NLS-1$ //$NON-NLS-2$
                    action.run();
                }

            }

            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    final TextOperationAction action = new TextOperationAction(
                            ResourceBundle.getBundle("org.eclipse.ui.texteditor.ConstructedEditorMessages"), "Editor.Delete.", editor, ITextOperationTarget.DELETE); //$NON-NLS-1$ //$NON-NLS-2$
                    action.run();
                }
            }
        });
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
        IWebBrowser browser = null;
        try {
            browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(IWorkbenchBrowserSupport.AS_EXTERNAL, null, null, null);
        } catch (final PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //$NON-NLS-1$
        URL url = null;
        try {
            url = new URL(MVEL_BASICS_URL);
        } catch (final MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            browser.openURL(url);
        } catch (final PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
