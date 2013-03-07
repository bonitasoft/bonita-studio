/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.providers;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.observables.DocumentObservable;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.SelectDependencyDialog;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.BonitaVariableLabelProvider;
import org.bonitasoft.studio.groovy.ui.dialog.GroovyEditorDocumentationDialogTray;
import org.bonitasoft.studio.groovy.ui.dialog.TestGroovyScriptDialog;
import org.bonitasoft.studio.groovy.ui.job.ComputeScriptDependenciesJob;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.groovy.ui.viewer.TestGroovyScriptUtil;
import org.bonitasoft.studio.groovy.ui.wizard.ProcessVariableContentProvider;
import org.bonitasoft.studio.groovy.ui.wizard.ProcessVariableLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyScriptExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    protected Composite mainComposite;
    protected Expression inputExpression;
    protected SourceViewer sourceViewer;
    protected List<ScriptVariable> nodes;
    private TableViewer dependenciesViewer;
    private final ComposedAdapterFactory adapterFactory;
    private final AdapterFactoryLabelProvider adapterLabelProvider;
    private Button automaticResolutionButton;
    private Button addDependencyButton;
    private Button removeDependencyButton;
    private ComputeScriptDependenciesJob dependencyJob;
    protected GroovyViewer groovyViewer;
    protected EObject context;
    private IDocument document;
    protected ComboViewer dataCombo;
    private Section depndencySection;
    protected ComboViewer bonitaDataCombo;
    private final ViewerSorter comboSorter = new ViewerSorter(){
        @Override
        public int compare(Viewer viewer, Object e1, Object e2) {
            if(e1 instanceof ScriptVariable && e2 instanceof ScriptVariable){
                return super.compare(viewer, e1, e2);
            }
            if(e1 instanceof Object){
                return -1;
            }
            if(e2 instanceof Object){
                return 1;
            }
            return super.compare(viewer, e1, e2);
        }
    };

    public GroovyScriptExpressionEditor(){
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider  = new AdapterFactoryLabelProvider(adapterFactory) ;
    }
    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createExpressionEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public Control createExpressionEditor(Composite parent) {

        createDataChooserArea(parent);

        mainComposite = new Composite(parent,SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create()) ;
        mainComposite.setLayout(new FillLayout(SWT.VERTICAL)) ;

        createGroovyEditor(parent);
        createDependencyViewer(parent);

        return mainComposite;
    }

    protected void createDataChooserArea(Composite composite) {

        final Composite combosComposite = new Composite(composite, SWT.NONE);
        combosComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).create());
        combosComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        dataCombo = new ComboViewer(combosComposite, SWT.READ_ONLY | SWT.BORDER);
        dataCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dataCombo.setLabelProvider(new ProcessVariableLabelProvider());
        dataCombo.setContentProvider(new ProcessVariableContentProvider());
        dataCombo.setSorter(comboSorter);

        bonitaDataCombo = new ComboViewer(combosComposite, SWT.READ_ONLY| SWT.BORDER);
        bonitaDataCombo.setLabelProvider(new BonitaVariableLabelProvider());
        bonitaDataCombo.setContentProvider(new ProcessVariableContentProvider());
        bonitaDataCombo.setSorter(comboSorter);
        bonitaDataCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        bonitaDataCombo.getCombo().addListener(SWT.Modify,new Listener() {

            @Override
            public void handleEvent(Event event) {
                Object selected = ((IStructuredSelection)bonitaDataCombo.getSelection()).getFirstElement();
                if (selected == null || selected.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
                    return;
                }

                ScriptVariable f = (ScriptVariable) selected;
                StyledText control = groovyViewer.getSourceViewer().getTextWidget();
                try{
                    int offset = control.getCaretOffset();
                    String before = document.get(0, offset);
                    if(offset == document.get().length()){
                        document.set(before+f.getName());
                    }else{
                        String after = document.get().substring(offset, document.get().length());
                        document.set(before+f.getName()+after);
                    }

                    control.setCaretOffset(offset+f.getName().length());
                    control.setFocus();

                    bonitaDataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                }catch (Exception e1) {
                    GroovyPlugin.logError(e1);
                }
            }
        });


        dataCombo.getCombo().addListener(SWT.Modify,new Listener() {

            @Override
            public void handleEvent(Event event) {
                Object selected = ((IStructuredSelection)dataCombo.getSelection()).getFirstElement();
                if(selected != null){
                    if (selected.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
                        return;
                    }

                    ScriptVariable f = (ScriptVariable) selected;
                    StyledText control = groovyViewer.getSourceViewer().getTextWidget();
                    try{
                        int offset = control.getCaretOffset();
                        String before = document.get(0, offset);
                        if(offset == document.get().length()){
                            document.set(before+f.getName());
                        }else{
                            String after = document.get().substring(offset, document.get().length());
                            document.set(before+f.getName()+after);
                        }

                        control.setCaretOffset(offset+f.getName().length());
                        control.setFocus();

                        dataCombo.getCombo().setText(""); //$NON-NLS-1$
                        dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                    }catch (Exception e1) {
                        GroovyPlugin.logError(e1);
                    }
                }
            }
        });

    }




    protected void createDependencyViewer(Composite parent) {

        final Composite mainComposite = new Composite(parent,SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        automaticResolutionButton = new Button(mainComposite, SWT.CHECK) ;
        automaticResolutionButton.setText(Messages.automaticResolution) ;
        automaticResolutionButton.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).span(2, 1).create()) ;
        automaticResolutionButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(automaticResolutionButton.getSelection()){
                    removeDependencyButton.setEnabled(false) ;
                    dependencyJob.schedule() ;
                }
                depndencySection.setExpanded(!automaticResolutionButton.getSelection());
            }
        }) ;


        depndencySection = new Section(mainComposite,Section.NO_TITLE);
        depndencySection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        depndencySection.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());


        Composite dependenciesComposite = new Composite(depndencySection, SWT.NONE) ;
        dependenciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
        dependenciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create()) ;

        dependenciesViewer = new TableViewer(dependenciesComposite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI) ;
        dependenciesViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 40).create());
        dependenciesViewer.setContentProvider(new ArrayContentProvider()) ;
        dependenciesViewer.setLabelProvider(adapterLabelProvider) ;
        dependenciesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if(!event.getSelection().isEmpty() && !automaticResolutionButton.getSelection()){
                    removeDependencyButton.setEnabled(true) ;
                }

            }
        }) ;

        Composite addRemoveComposite = new Composite(dependenciesComposite, SWT.NONE) ;
        addRemoveComposite.setLayoutData(GridDataFactory.fillDefaults().create()) ;
        addRemoveComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create()) ;

        addDependencyButton = new Button(addRemoveComposite, SWT.FLAT) ;
        addDependencyButton.setText(Messages.add) ;
        addDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;


        removeDependencyButton = new Button(addRemoveComposite, SWT.FLAT) ;
        removeDependencyButton.setText(Messages.remove) ;
        removeDependencyButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                for(Object sel : ((IStructuredSelection)dependenciesViewer.getSelection()).toList()){
                    inputExpression.getReferencedElements().remove(sel) ;
                }
            }
        }) ;
        removeDependencyButton.setEnabled(false) ;
        removeDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;

        depndencySection.setClient(dependenciesComposite);
    }


    protected void createGroovyEditor(Composite parent) {

        groovyViewer = new GroovyViewer(mainComposite) ;
        groovyViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).hint(SWT.DEFAULT, 300).create()) ;
        sourceViewer = groovyViewer.getSourceViewer();
        document = groovyViewer.getDocument() ;

        final Composite buttonBarComposite = new Composite(parent,SWT.NONE) ;
        buttonBarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        buttonBarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create()) ;

        final Button testButton = new Button(buttonBarComposite,SWT.PUSH) ;
        testButton.setText(Messages.testButtonLabel) ;
        testButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END,SWT.FILL).create());
        testButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Map<String, Serializable> variables = TestGroovyScriptUtil.createVariablesMap(groovyViewer.getGroovyCompilationUnit(), nodes);
                if(variables.isEmpty()){
                    try {
                        TestGroovyScriptUtil.evaluateExpression(groovyViewer.getGroovyCompilationUnit().getSource(), inputExpression.getReturnType(), Collections.EMPTY_MAP);
                    } catch (JavaModelException e1) {
                        BonitaStudioLog.error(e1);
                    }
                }else{
                    new TestGroovyScriptDialog(Display.getDefault().getActiveShell(), nodes, groovyViewer.getGroovyCompilationUnit(),inputExpression.getReturnType(),variables).open() ;
                }
            }
        });

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return true ;
    }

    @Override
    public void bindExpression(EMFDataBindingContext dataBindingContext,final EObject context, Expression inputExpression,ViewerFilter[] filters) {
        this.inputExpression = inputExpression ;
        this.context = context ;

        IObservableValue dependenciesModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS) ;
        IObservableValue autoDepsModelObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__AUTOMATIC_DEPENDENCIES) ;

        inputExpression.setType(ExpressionConstants.SCRIPT_TYPE);
        inputExpression.setInterpreter(ExpressionConstants.GROOVY);

        groovyViewer.setContext(context,filters) ;
        nodes = groovyViewer.getFieldNodes() ;


        if (context == null && nodes == null) {
            dataCombo.getCombo().add(Messages.noProcessVariableAvailable);
            dataCombo.getCombo().setText(Messages.noProcessVariableAvailable);
            dataCombo.getCombo().setEnabled(false);
        }else if(nodes != null){
            dataCombo.setInput(nodes);
            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
            if(nodes.isEmpty()){
                dataCombo.getCombo().setEnabled(false);
            }
        }else{
            dataCombo.setInput(groovyViewer.getFieldNodes());
            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
            if(groovyViewer.getFieldNodes().isEmpty()){
                dataCombo.getCombo().setEnabled(false);
            }
        }

        bonitaDataCombo.setInput(GroovyUtil.getBonitaVariables(context));
        bonitaDataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));

        dataBindingContext.bindValue(ViewersObservables.observeInput(dependenciesViewer), dependenciesModelObservable) ;

        UpdateValueStrategy opposite = new UpdateValueStrategy() ;
        opposite.setConverter(new Converter(Boolean.class,Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                return !((Boolean)fromObject);
            }
        }) ;

        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), autoDepsModelObservable) ;
        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), SWTObservables.observeEnabled(addDependencyButton),opposite,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER)) ;
        depndencySection.setExpanded(!automaticResolutionButton.getSelection());

        addDependencyButton.setEnabled(!inputExpression.isAutomaticDependencies()) ;

        dependencyJob = new ComputeScriptDependenciesJob(sourceViewer.getDocument()) ;
        dependencyJob.setNodes(nodes) ;
        dependencyJob.setContext(context) ;
        final InputLengthValidator lenghtValidator = new InputLengthValidator("", GroovyViewer.MAX_SCRIPT_LENGTH);
        String content = inputExpression.getContent();
        if(content == null){
            content = "";
        }
        sourceViewer.getTextWidget().setText(content);
        sourceViewer.getDocument().addDocumentListener(new IDocumentListener() {

            @Override
            public void documentChanged(DocumentEvent event) {
                final String text = event.getDocument().get();
                if(lenghtValidator.validate(text).isOK()){
                    GroovyScriptExpressionEditor.this.inputExpression.setContent(text);
                }
                if(automaticResolutionButton.getSelection()){
                    dependencyJob.schedule() ;
                }

            }

            @Override
            public void documentAboutToBeChanged(DocumentEvent event) {}
        });

        dependencyJob.addJobChangeListener(new IJobChangeListener() {

            @Override
            public void sleeping(IJobChangeEvent event) {}

            @Override
            public void scheduled(IJobChangeEvent event) {}

            @Override
            public void running(IJobChangeEvent event) {}

            @Override
            public void done(IJobChangeEvent event) {
                if(dependencyJob != null && GroovyScriptExpressionEditor.this.inputExpression.isAutomaticDependencies()){
                    List<EObject> deps = dependencyJob.getDependencies(document.get()) ;
                    GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements().clear() ;
                    if(deps != null && !deps.isEmpty()){
                        GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements().addAll(deps) ;
                    }
                }
            }

            @Override
            public void awake(IJobChangeEvent event) {}

            @Override
            public void aboutToRun(IJobChangeEvent event) {}

        }) ;

        final ExpressionContentProvider provider = new ExpressionContentProvider() ;
        provider.setContext(context);

        final Set<Expression> filteredExpressions = new HashSet<Expression>() ;
        Expression[] expressions = provider.getExpressions();
        EObject input =  provider.getContext() ;
        if(expressions != null){
            filteredExpressions.addAll(Arrays.asList(expressions)) ;
            if(input != null && filters != null){
                for(Expression exp : expressions) {
                    for(ViewerFilter filter : filters){
                        if(filter != null && !filter.select(groovyViewer.getSourceViewer(), input, exp)){
                            filteredExpressions.remove(exp) ;
                        }
                    }
                }
            }
        }

        addDependencyButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SelectDependencyDialog dialog =	new SelectDependencyDialog(Display.getDefault().getActiveShell(),filteredExpressions, GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements()) ;
                dialog.open()  ;
            }
        }) ;
    }


    @Override
    public void dispose() {
        super.dispose();
        if(groovyViewer != null){
            groovyViewer.dispose() ;
        }
        if(dependencyJob != null){
            dependencyJob.cancel();
        }
    }

    @Override
    public void okPressed() {
        if(dependencyJob != null){
            dependencyJob.cancel();
            dependencyJob.schedule() ;
            try {
                dependencyJob.join();
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e) ;
            }
        }
    }

    @Override
    public boolean provideDialogTray() {
        return true;
    }

    @Override
    public DialogTray createDialogTray() {
        return new GroovyEditorDocumentationDialogTray(groovyViewer);
    }
    @Override
    public Control getTextControl() {
        return sourceViewer.getTextWidget();
    }

    @Override
    public IObservable getContentObservable() {
        return new DocumentObservable(sourceViewer);
    }
}
