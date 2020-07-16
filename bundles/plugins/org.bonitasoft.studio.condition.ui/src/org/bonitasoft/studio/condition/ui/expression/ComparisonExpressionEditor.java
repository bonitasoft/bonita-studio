/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.condition.ui.expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.jface.databinding.converter.BooleanInverserConverter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.condition.conditionModel.ConditionModelPackage;
import org.bonitasoft.studio.condition.conditionModel.Expression_ProcessRef;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.scoping.ConditionModelGlobalScopeProvider;
import org.bonitasoft.studio.condition.ui.i18n.Messages;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.condition.validation.ConditionModelJavaValidator;
import org.bonitasoft.studio.expression.editor.ExpressionEditorPlugin;
import org.bonitasoft.studio.expression.editor.constant.ConstantTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.constant.ExpressionReturnTypeContentProvider;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.SelectDependencyDialog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory.Builder;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;

import com.google.inject.Injector;

/**
 * @author Aurelie Zara
 * @author Romain Bioteau
 */
public class ComparisonExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private ComboViewer typeCombo;

    private Button automaticResolutionButton;

    protected Button removeDependencyButton;

    private Section dependencySection;

    private TableViewer dependenciesViewer;

    private Button addDependencyButton;

    private EmbeddedEditor comparisonEditor;

    private final EObject context;

    private final ComposedAdapterFactory adapterFactory;

    private final AdapterFactoryLabelProvider adapterLabelProvider;

    private Expression inputExpression;

    private XtextResource resource;

    private boolean isPageFlowContext = false;

    public ComparisonExpressionEditor(final EObject context) {
        this.context = context;
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        final Injector injector = ConditionModelActivator.getInstance()
                .getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
        if (context != null && context.eResource() != null) {
            final ConditionModelJavaValidator validator = injector.getInstance(ConditionModelJavaValidator.class);
            validator.setCurrentResourceSet(context.eResource().getResourceSet());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createExpressionEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createHeader(mainComposite);
        createComparisonEditor(mainComposite);
        createDependanciesResolutionComposite(mainComposite);
        createReturnTypeComposite(mainComposite);
        return mainComposite;
    }

    protected void createHeader(final Composite parent) {
        final Composite header = new Composite(parent, SWT.NONE);
        header.setLayout(GridLayoutFactory.fillDefaults().create());
        header.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Label autocompleteMessage = new Label(header, SWT.WRAP);
        autocompleteMessage.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        autocompleteMessage.setText(Messages.autocompleteMessage);
        autocompleteMessage.setFont(BonitaStudioFontRegistry.getItalicFont());
        final Label supportedOperators = new Label(header, SWT.WRAP);
        supportedOperators.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        supportedOperators.setText(Messages.comparisonSupportedOperators);
        supportedOperators.setFont(BonitaStudioFontRegistry.getItalicFont());

    }

    protected void createComparisonEditor(final Composite parent) {
        final IEditedResourceProvider resourceProvider = new IEditedResourceProvider() {

            @Override
            public XtextResource createResource() {
                try {
                    final Injector injector = ConditionModelActivator.getInstance().getInjector(
                            ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
                    resource = (XtextResource) new XtextComparisonExpressionLoader(
                            injector.getInstance(ConditionModelGlobalScopeProvider.class),
                            new ModelSearch(Collections::emptyList), new ProjectXtextResourceProvider(injector))
                                    .loadResource("", context);
                    resource.setValidationDisabled(false);
                    return resource;
                } catch (final Exception e) {
                    BonitaStudioLog.error(e, ExpressionEditorPlugin.PLUGIN_ID);
                    return null;
                }
            }
        };
        final ConditionModelActivator activator = ConditionModelActivator.getInstance();
        final Injector injector = activator
                .getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
        final EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);

        final Builder buildEditor = factory.newEditor(resourceProvider);
        comparisonEditor = buildEditor.withParent(parent);
        comparisonEditor.createPartialEditor(true);
        comparisonEditor.getDocument().set("");
        comparisonEditor.getViewer().getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        comparisonEditor.getViewer().addTextListener(new ITextListener() {

            @Override
            public void textChanged(final TextEvent event) {
                comparisonEditor.getViewer().getControl().notifyListeners(SWT.Modify, new Event());
            }
        });

        comparisonEditor.getDocument().addModelListener(new IXtextModelListener() {

            @Override
            public void modelChanged(final XtextResource resource) {
                if (inputExpression.isAutomaticDependencies()) {
                    updateDependencies(resource);
                }
            }
        });
    }

    protected void createDependanciesResolutionComposite(final Composite parent) {
        final Composite dependanciesComposite = new Composite(parent, SWT.NONE);
        dependanciesComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());
        dependanciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        automaticResolutionButton = new Button(dependanciesComposite, SWT.CHECK);
        automaticResolutionButton.setText(Messages.automaticResolution);
        automaticResolutionButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        automaticResolutionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (automaticResolutionButton.getSelection()) {
                    removeDependencyButton.setEnabled(false);
                    final String content = comparisonEditor.getDocument().get();
                    comparisonEditor.getDocument().set(content);
                }
                dependencySection.setExpanded(!automaticResolutionButton.getSelection());
            }
        });

        dependencySection = new Section(dependanciesComposite, Section.NO_TITLE);
        dependencySection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        dependencySection.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Composite dependenciesComposite = new Composite(dependencySection, SWT.NONE);
        dependenciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dependenciesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        dependenciesViewer = new TableViewer(dependenciesComposite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
        dependenciesViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 40).create());
        dependenciesViewer.setContentProvider(new ArrayContentProvider());
        dependenciesViewer.setLabelProvider(adapterLabelProvider);
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

        dependencySection.setClient(dependenciesComposite);
        automaticResolutionButton.setSelection(true);

    }

    protected void updateDependencies(final XtextResource resource) {
        inputExpression.getReferencedElements().clear();
        final Operation_Compare compareOp = (Operation_Compare) resource.getContents().get(0);
        if (compareOp != null) {
            final List<Expression_ProcessRef> references = ModelHelper.getAllItemsOfType(compareOp,
                    ConditionModelPackage.Literals.EXPRESSION_PROCESS_REF);
            for (final Expression_ProcessRef ref : references) {
                final EObject dep = ComparisonExpressionUtil.getResolvedDependency(context, ref);
                inputExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(dep));
            }
        }
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite returnTypeComposite = new Composite(parent, SWT.NONE);
        returnTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        returnTypeComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.BOTTOM).create());

        final Label typeLabel = new Label(returnTypeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);

        typeCombo = new ComboViewer(returnTypeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        typeCombo.setContentProvider(new ExpressionReturnTypeContentProvider());
        typeCombo.setLabelProvider(new ConstantTypeLabelProvider());
        typeCombo.setInput(new Object());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#bindExpression(org.eclipse.emf.databinding.EMFDataBindingContext,
     * org.eclipse.emf.ecore.EObject, org.bonitasoft.studio.model.expression.Expression, org.eclipse.jface.viewers.ViewerFilter[])
     */
    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext,
            final EObject context, final Expression inputExpression,
            final ViewerFilter[] viewerTypeFilters, final ExpressionViewer expressionViewer) {
        this.inputExpression = inputExpression;
        final IObservableValue contentModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        final IObservableValue nameModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__NAME);
        final IObservableValue dependenciesModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);
        final IObservableValue autoDepsModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__AUTOMATIC_DEPENDENCIES);
        final IObservableValue returnTypeModelObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final ISWTObservableValue observeText = WidgetProperties.text(SWT.Modify)
                .observe(comparisonEditor.getViewer().getControl());
        dataBindingContext.bindValue(observeText, nameModelObservable);
        dataBindingContext.bindValue(observeText, contentModelObservable);
        dataBindingContext.bindValue(ViewersObservables.observeInput(dependenciesViewer), dependenciesModelObservable);

        final UpdateValueStrategy opposite = new UpdateValueStrategy();
        opposite.setConverter(new BooleanInverserConverter());

        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton), autoDepsModelObservable);
        dataBindingContext.bindValue(SWTObservables.observeSelection(automaticResolutionButton),
                SWTObservables.observeEnabled(addDependencyButton), opposite,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        dependencySection.setExpanded(!automaticResolutionButton.getSelection());

        addDependencyButton.setEnabled(!inputExpression.isAutomaticDependencies());
        ControlDecorationSupport
                .create(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo),
                        returnTypeModelObservable), SWT.LEFT);
        typeCombo.getCombo().setEnabled(!inputExpression.isReturnTypeFixed());

        final ExpressionContentProvider provider = ExpressionContentProvider.getInstance();

        final Set<Expression> filteredExpressions = new HashSet<Expression>();
        final Expression[] expressions = provider.getExpressions(context);
        if (expressions != null) {
            filteredExpressions.addAll(Arrays.asList(expressions));
            if (context != null && viewerTypeFilters != null) {
                for (final Expression exp : expressions) {
                    for (final ViewerFilter filter : viewerTypeFilters) {
                        if (filter != null && !filter.select(comparisonEditor.getViewer(), context, exp)) {
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
                        filteredExpressions,
                        ComparisonExpressionEditor.this.inputExpression.getReferencedElements());
                dialog.open();
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#dispose()
     */
    @Override
    public void dispose() {
        if (resource != null) {
            resource.unload();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#okPressed()
     */
    @Override
    public void okPressed() {

    }

    @Override
    public Control getTextControl() {
        return comparisonEditor.getViewer().getTextWidget();
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
