/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.providers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.jface.databinding.observables.DocumentObservable;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.dependencies.ui.dialog.ManageConnectorJarDialog;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.SelectDependencyDialog;
import org.bonitasoft.studio.groovy.GroovyPlugin;
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
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.nebula.jface.tablecomboviewer.TableComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.Section;

import com.google.common.collect.Lists;

/**
 * @author Romain Bioteau
 */
public class GroovyScriptExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor, IBonitaVariableContext {

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

    protected TableComboViewer dataCombo;

    private Section depndencySection;

    protected TableComboViewer bonitaDataCombo;

    private boolean isPageFlowContext = false;

    private final ViewerSorter comboSorter = new ViewerSorter() {

        @Override
        public int compare(final Viewer viewer, final Object e1, final Object e2) {
            return comboSorterFunction(e1, e2);
        }
    };

    private Button testButton;

    public GroovyScriptExpressionEditor() {
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
    }

    public int comboSorterFunction(final Object e1, final Object e2) {
        if (e1 instanceof ScriptVariable && e2 instanceof ScriptVariable) {
            return compareScriptVariables(e1, e2);
        } else if (e1 instanceof String && e2 instanceof ScriptVariable) {
            return compareLabelAndScriptVariable(e1, e2);
        } else if (e1 instanceof ScriptVariable && e2 instanceof String) {
            return compareScriptVariableAndLabel(e1, e2);
        } else if (e1 instanceof String && e2 instanceof String) {
            return e1.toString().compareTo(e2.toString());
        } else if (e1.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
            return -1;
        } else if (e2.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int compareScriptVariableAndLabel(final Object e1, final Object e2) {
        final String e1Category = ((ScriptVariable) e1).getCategory();
        if (e1Category != null) {
            if (e1Category.equalsIgnoreCase(e2.toString())) {
                return 1;
            } else {
                return e1Category.compareTo(e2.toString());
            }
        } else {
            return -1;
        }
    }

    private int compareLabelAndScriptVariable(final Object e1, final Object e2) {
        final String e2Category = ((ScriptVariable) e2).getCategory();
        if (e2Category != null) {
            if (e1.toString().equalsIgnoreCase(e2Category)) {
                return -1;
            } else {
                return e1.toString().compareTo(e2Category);
            }
        } else {
            return -1;
        }
    }

    private int compareScriptVariables(final Object e1, final Object e2) {
        final ScriptVariable e1sv = (ScriptVariable) e1;
        final ScriptVariable e2sv = (ScriptVariable) e2;
        if (e1sv.getCategory() == null) {
            return 1;
        } else if (e2sv.getCategory() == null) {
            return -1;
        } else {
            if (e1sv.getCategory().equals(e2sv.getCategory())) {
                return e1sv.getName().compareToIgnoreCase(e2sv.getName());
            } else {
                return compareScriptVariableAndLabel(e1, e2sv.getCategory());
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#
     * createExpressionEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {

        createDataChooserArea(parent);

        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        mainComposite.setLayout(new FillLayout(SWT.VERTICAL));

        createGroovyEditor(parent,true);
        createDependencyViewer(parent);

        return mainComposite;
    }

    protected void createDataChooserArea(final Composite composite) {

        final Composite combosComposite = new Composite(composite, SWT.NONE);
        combosComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).create());
        combosComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        dataCombo = new TableComboViewer(combosComposite, SWT.READ_ONLY | SWT.BORDER);
        dataCombo.getTableCombo().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 22).create());
        dataCombo.getTableCombo().defineColumns(1);
        dataCombo.setLabelProvider(new ProcessVariableLabelProvider());
        dataCombo.setContentProvider(new ProcessVariableContentProvider());
        dataCombo.setSorter(comboSorter);

        bonitaDataCombo = new TableComboViewer(combosComposite, SWT.READ_ONLY | SWT.BORDER);
        bonitaDataCombo.setLabelProvider(new BonitaVariableLabelProvider());
        bonitaDataCombo.setContentProvider(new ProcessVariableContentProvider());
        bonitaDataCombo.setSorter(comboSorter);
        bonitaDataCombo.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 22).create());

        bonitaDataCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object selected = ((IStructuredSelection) bonitaDataCombo.getSelection()).getFirstElement();
                if (selected == null || selected.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
                    return;
                }
                final ScriptVariable f = (ScriptVariable) selected;
                final StyledText control = groovyViewer.getSourceViewer().getTextWidget();
                try {
                    final int offset = control.getCaretOffset();
                    final String before = document.get(0, offset);
                    if (offset == document.get().length()) {
                        document.set(before + f.getName());
                    } else {
                        final String after = document.get().substring(offset, document.get().length());
                        document.set(before + f.getName() + after);
                    }
                    control.setCaretOffset(offset + f.getName().length());
                    control.setFocus();
                    bonitaDataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                } catch (final Exception e1) {
                    GroovyPlugin.logError(e1);
                }
            }
        });

        dataCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Object selected = ((IStructuredSelection) dataCombo.getSelection()).getFirstElement();
                if (selected != null) {
                    if (selected.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
                        return;
                    }
                    if (!(selected instanceof String)) {

                        final ScriptVariable f = (ScriptVariable) selected;
                        final StyledText control = groovyViewer.getSourceViewer().getTextWidget();
                        try {
                            final int offset = control.getCaretOffset();
                            final String before = document.get(0, offset);
                            if (offset == document.get().length()) {
                                document.set(before + f.getName());
                            } else {
                                final String after = document.get().substring(offset, document.get().length());
                                document.set(before + f.getName() + after);
                            }

                            control.setCaretOffset(offset + f.getName().length());
                            control.setFocus();

                            dataCombo.getTableCombo().setText(""); //$NON-NLS-1$
                            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                        } catch (final Exception e1) {
                            GroovyPlugin.logError(e1);
                        }
                    } else {
                        try {
                            dataCombo.getTableCombo().setText(""); //$NON-NLS-1$
                            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
                        } catch (final Exception e1) {
                            GroovyPlugin.logError(e1);
                        }
                    }
                }

            }
        });

    }

    protected void createDependencyViewer(final Composite parent) {

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        automaticResolutionButton = new Button(mainComposite, SWT.CHECK);
        automaticResolutionButton.setText(Messages.automaticResolution);
        automaticResolutionButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        automaticResolutionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (automaticResolutionButton.getSelection()) {
                    removeDependencyButton.setEnabled(false);
                }
                depndencySection.setExpanded(!automaticResolutionButton.getSelection());
            }
        });

        depndencySection = new Section(mainComposite, Section.NO_TITLE);
        depndencySection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        depndencySection.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Composite dependenciesComposite = new Composite(depndencySection, SWT.NONE);
        dependenciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dependenciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        dependenciesViewer = new TableViewer(dependenciesComposite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        dependenciesViewer.getControl().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 40).create());
        dependenciesViewer.setContentProvider(new ArrayContentProvider());
        dependenciesViewer.setLabelProvider(new DependencyLabelProvider(adapterLabelProvider));
        dependenciesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty() && !automaticResolutionButton.getSelection()) {
                    removeDependencyButton.setEnabled(true);
                }

            }
        });

        final Composite addRemoveComposite = new Composite(dependenciesComposite, SWT.NONE);
        addRemoveComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        addRemoveComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        addDependencyButton = new Button(addRemoveComposite, SWT.FLAT);
        addDependencyButton.setText(Messages.add);
        addDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create());

        removeDependencyButton = new Button(addRemoveComposite, SWT.FLAT);
        removeDependencyButton.setText(Messages.remove);
        removeDependencyButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                for (final Object sel : ((IStructuredSelection) dependenciesViewer.getSelection()).toList()) {
                    inputExpression.getReferencedElements().remove(sel);
                }
            }
        });
        removeDependencyButton.setEnabled(false);
        removeDependencyButton.setLayoutData(GridDataFactory.fillDefaults().create());

        depndencySection.setClient(dependenciesComposite);
    }

    protected void createGroovyEditor(final Composite parent,boolean restrictSciptSize) {
        groovyViewer = new GroovyViewer(mainComposite, isPageFlowContext,restrictSciptSize);
        groovyViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
        sourceViewer = groovyViewer.getSourceViewer();
        document = groovyViewer.getDocument();

        final Composite buttonBarComposite = new Composite(parent, SWT.NONE);
        buttonBarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        buttonBarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        testButton = new Button(buttonBarComposite, SWT.PUSH);
        testButton.setText(Messages.testButtonLabel);
        testButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL).create());
        testButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final Map<String, Serializable> variables = TestGroovyScriptUtil.createVariablesMap(
                        groovyViewer.getGroovyCompilationUnit(), nodes == null ? Lists.<ScriptVariable> newArrayList() : nodes);

                if (variables.isEmpty()) {
                    final ManageConnectorJarDialog mcjd = new ManageConnectorJarDialog(Display.getDefault().getActiveShell());
                    final int retCode = mcjd.open();
                    if (retCode == Window.OK) {
                        try {
                            TestGroovyScriptUtil.evaluateExpression(groovyViewer.getGroovyCompilationUnit().getSource(),
                                    inputExpression.getReturnType(), Collections.EMPTY_MAP, mcjd.getSelectedJars());
                        } catch (final JavaModelException e1) {
                            BonitaStudioLog.error(e1);
                        }
                    }
                } else {
                    new TestGroovyScriptDialog(Display.getDefault().getActiveShell(), nodes, groovyViewer
                            .getGroovyCompilationUnit(), inputExpression.getReturnType(), variables).open();
                }

            }
        });

    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish
     * ()
     */
    @Override
    public boolean canFinish() {
        return true;
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context, final Expression inputExpression,
            final ViewerFilter[] filters, final ExpressionViewer viewer) {
        this.inputExpression = inputExpression;
        this.context = context;

        final IObservableValue dependenciesModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);
        final IObservableValue autoDepsModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__AUTOMATIC_DEPENDENCIES);

        inputExpression.setType(ExpressionConstants.SCRIPT_TYPE);
        inputExpression.setInterpreter(ExpressionConstants.GROOVY);

        groovyViewer.setContext(viewer, context, filters, viewer.getExpressionNatureProvider());
        nodes = new ArrayList<ScriptVariable>(groovyViewer.getFieldNodes());

        if (context == null && nodes == null) {
            dataCombo.add(Messages.noProcessVariableAvailable);
            dataCombo.getTableCombo().setText(Messages.noProcessVariableAvailable);
            dataCombo.getTableCombo().setEnabled(false);
        } else if (nodes != null) {
            dataCombo.setInput(nodes);
            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
            if (nodes.isEmpty()) {
                dataCombo.getTableCombo().setEnabled(false);
            }
        } else {
            dataCombo.setInput(groovyViewer.getFieldNodes());
            dataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));
            if (groovyViewer.getFieldNodes().isEmpty()) {
                dataCombo.getTableCombo().setEnabled(false);
            }
        }
        bonitaDataCombo.setInput(groovyViewer.getProvidedVariables(context, filters));
        bonitaDataCombo.setSelection(new StructuredSelection(ProcessVariableContentProvider.SELECT_ENTRY));

        dataBindingContext.bindValue(ViewersObservables.observeInput(dependenciesViewer), dependenciesModelObservable);

        final UpdateValueStrategy opposite = new UpdateValueStrategy();
        opposite.setConverter(new BooleanInverserConverter());

        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), autoDepsModelObservable);
        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), SWTObservables
                .observeEnabled(addDependencyButton), opposite, new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        autoDepsModelObservable.addChangeListener(new IChangeListener() {

            @Override
            public void handleChange(final ChangeEvent event) {
                if ((Boolean) autoDepsModelObservable.getValue()) {
                    if (dependencyJob != null) {
                        dependencyJob.schedule();
                    }
                }
            }
        });
        depndencySection.setExpanded(!automaticResolutionButton.getSelection());

        addDependencyButton.setEnabled(!inputExpression.isAutomaticDependencies());

        dependencyJob = new ComputeScriptDependenciesJob(groovyViewer.getGroovyCompilationUnit());
        dependencyJob.setContext(context);
        nodes.addAll(groovyViewer.getProvidedVariables(context, filters));
        dependencyJob.setNodes(nodes);

        final InputLengthValidator lenghtValidator = new InputLengthValidator("", GroovyViewer.MAX_SCRIPT_LENGTH);
        String content = inputExpression.getContent();
        if (content == null) {
            content = "";
        }
        sourceViewer.getTextWidget().setText(content);
        sourceViewer.getDocument().addDocumentListener(new IDocumentListener() {

            @Override
            public void documentChanged(final DocumentEvent event) {
                final String text = event.getDocument().get();
                if (lenghtValidator.validate(text).isOK()) {
                    GroovyScriptExpressionEditor.this.inputExpression.setContent(text);
                }
                if (automaticResolutionButton.getSelection()) {
                    dependencyJob.schedule();
                }

            }

            @Override
            public void documentAboutToBeChanged(final DocumentEvent event) {
            }
        });

        dependencyJob.addJobChangeListener(new IJobChangeListener() {

            @Override
            public void sleeping(final IJobChangeEvent event) {
            }

            @Override
            public void scheduled(final IJobChangeEvent event) {
            }

            @Override
            public void running(final IJobChangeEvent event) {
            }

            @Override
            public void done(final IJobChangeEvent event) {
                if (dependencyJob != null && GroovyScriptExpressionEditor.this.inputExpression.isAutomaticDependencies()) {
                    final List<EObject> deps = dependencyJob.getDependencies(document.get());
                    GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements().clear();
                    if (deps != null && !deps.isEmpty()) {
                        GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements().addAll(deps);
                    }
                }
            }

            @Override
            public void awake(final IJobChangeEvent event) {
            }

            @Override
            public void aboutToRun(final IJobChangeEvent event) {
            }

        });

        final ExpressionContentProvider provider = ExpressionContentProvider.getInstance();
        final Set<Expression> filteredExpressions = new HashSet<Expression>();
        final Expression[] expressions = provider.getExpressions(context);
        if (expressions != null) {
            filteredExpressions.addAll(Arrays.asList(expressions));
            if (context != null && filters != null) {
                for (final Expression exp : expressions) {
                    for (final ViewerFilter filter : filters) {
                        if (filter != null && !filter.select(groovyViewer.getSourceViewer(), context, exp)) {
                            filteredExpressions.remove(exp);
                        }
                    }
                }
            }
        }

        addDependencyButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final SelectDependencyDialog dialog = new SelectDependencyDialog(Display.getDefault().getActiveShell(),
                        filteredExpressions, GroovyScriptExpressionEditor.this.inputExpression.getReferencedElements());
                dialog.open();
            }
        });

        final UpdateValueStrategy evaluateStrategy = new UpdateValueStrategy();
        evaluateStrategy.setConverter(new Converter(String.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject == null || fromObject.toString().isEmpty()) {
                    return false;
                }
                return true;
            }
        });
        dataBindingContext.bindValue(SWTObservables.observeEnabled(testButton),
                SWTObservables.observeText(groovyViewer.getSourceViewer().getTextWidget(), SWT.Modify), null,
                evaluateStrategy);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (dependencyJob != null) {
            dependencyJob.cancel();
            try {
                dependencyJob.join();
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (groovyViewer != null) {
            groovyViewer.dispose();
        }
    }

    @Override
    public void okPressed() {
        if (dependencyJob != null) {
            dependencyJob.cancel();
            dependencyJob.schedule();
            try {
                dependencyJob.join();
            } catch (final InterruptedException e) {
                BonitaStudioLog.error(e);
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

    @Override
    public boolean isPageFlowContext() {
        return isPageFlowContext;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {
        this.isPageFlowContext = isPageFlowContext;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }
}
