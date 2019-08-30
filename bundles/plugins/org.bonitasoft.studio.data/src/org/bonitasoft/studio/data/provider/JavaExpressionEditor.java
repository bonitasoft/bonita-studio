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
package org.bonitasoft.studio.data.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.corext.template.java.SignatureUtil;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class JavaExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private TableViewer viewer;

    private Expression editorInputExpression;

    private Composite mainComposite;

    private Text typeText;

    private TreeViewer javaTreeviewer;

    private Data data;

    private ITreeContentProvider contentProvider;

    private boolean isPageFlowContext = false;

    private MultiValidator validationStatusProvider;

    private EMFDataBindingContext dataBindingContext;

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).create());

        final Label filler = (new Label(mainComposite, SWT.NONE));
        filler.setLayoutData(
                GridDataFactory.fillDefaults().indent(0, -LayoutConstants.getSpacing().y + 1).span(2, 1).create());

        viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);

        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.NONE);
        final TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);

        final TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(column);

        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new DataStyledTreeLabelProvider());

        viewer.addPostSelectionChangedListener(event -> {
            if (!event.getSelection().isEmpty()) {
                javaTreeviewer.getTree().setEnabled(true);

                data = (Data) ((IStructuredSelection) event.getSelection()).getFirstElement();
                String className = null;

                if (data.isMultiple()) {
                    className = List.class.getName();
                } else if (data instanceof JavaObjectData) {
                    className = ((JavaObjectData) data).getClassName();
                }
                if (className != null) {
                    javaTreeviewer.setInput(className);
                    if (editorInputExpression != null && editorInputExpression.isReturnTypeFixed()) {
                        javaTreeviewer.addFilter(showOnlyMethodWithReturnType(editorInputExpression.getReturnType()));
                    }
                    javaTreeviewer.expandAll();
                    javaTreeviewer.getTree().setFocus();
                    javaTreeviewer.getTree().getShell().layout(true, true);
                    JavaExpressionEditor.this.fireSelectionChanged();
                }
            }
        });

        createBrowseJavaObjectForReadExpression(mainComposite);
        createReturnTypeComposite(parent);

        return mainComposite;
    }

    protected ViewerFilter showOnlyMethodWithReturnType(final String returnType) {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                if (element instanceof IMethod) {
                    try {
                        return returnType.equals(toQualifiedName(element));
                    } catch (final JavaModelException e) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        typeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());

        typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());

    }

    protected void createBrowseJavaObjectForReadExpression(final Composite composite) {
        final Composite res = new Composite(composite, SWT.NONE);
        res.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        res.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(LayoutConstants.getSpacing().x, 0).create());

        final Label label = new Label(res, SWT.NONE);
        label.setText(Messages.browseJava);
        javaTreeviewer = new TreeViewer(res, SWT.SINGLE | SWT.BORDER);
        javaTreeviewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        javaTreeviewer.setLabelProvider(new JavaUILabelProvider() {

            @Override
            public String getText(final Object item) {
                if (item instanceof IMethod) {
                    try {
                        return super.getText(item) + " - " + toQualifiedName(item);
                    } catch (final JavaModelException e) {
                        BonitaStudioLog.error(e);
                        return null;
                    }
                } else {
                    return super.getText(item);
                }
            }

        });

        javaTreeviewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final ITreeSelection selection = (ITreeSelection) event.getSelection();
                if (!selection.isEmpty()) {
                    JavaExpressionEditor.this.fireSelectionChanged();
                    javaTreeviewer.getTree().setFocus();
                }
            }

        });

        javaTreeviewer.getTree().setEnabled(false);
    }

    private String toQualifiedName(final Object item) throws JavaModelException {
        return SignatureUtil.stripSignatureToFQN(((IMethod) item).getReturnType());
    }

    protected ITreeContentProvider getContentProvider() {
        return contentProvider;
    }

    protected void setContentProvider(final ITreeContentProvider provider) {
        contentProvider = provider;
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext, final EObject context,
            final Expression inputExpression,
            final ViewerFilter[] filters,
            final ExpressionViewer expressionViewer) {
        this.dataBindingContext = dataBindingContext;
        editorInputExpression = inputExpression;
        setContentProvider(new PojoBrowserContentProvider());
        javaTreeviewer.setContentProvider(getContentProvider());

        final Set<Data> input = new HashSet<>();
        final IExpressionProvider provider = ExpressionProviderService.getInstance()
                .getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
        for (final Expression e : provider.getExpressions(context)) {
            if (acceptExpression(expressionViewer, e, context, filters)) {
                final Data data = (Data) e.getReferencedElements().get(0);
                if (data instanceof JavaObjectData || data.isMultiple()) {
                    input.add(data);
                }
            }
        }
        viewer.setInput(input);

        final IObservableValue contentObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        final IObservableValue nameObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__NAME);
        final IObservableValue returnTypeObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final IObservableValue referenceObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        final UpdateValueStrategy selectionToName = new UpdateValueStrategy();
        final IConverter nameConverter = new Converter(Data.class, String.class) {

            @Override
            public Object convert(final Object data) {
                if (data instanceof Data) {
                    return ((Data) data).getName() + " - "
                            + NamingUtils.getSimpleName(((JavaObjectData) data).getClassName()) + "#"
                            + editorInputExpression.getContent();
                } else if (data instanceof IMethod) {
                    final JavaObjectData data2 = (JavaObjectData) editorInputExpression.getReferencedElements().get(0);
                    return data2.getName() + " - " + NamingUtils.getSimpleName(data2.getClassName()) + "#"
                            + ((IMethod) data).getElementName();
                }
                return null;
            }

        };
        selectionToName.setConverter(nameConverter);

        final UpdateValueStrategy selectionToContent = new UpdateValueStrategy();
        final IConverter contentConverter = new Converter(Object.class, String.class) {

            @Override
            public Object convert(final Object value) {
                if (value instanceof IMethod) {
                    return ((IMethod) value).getElementName();
                }
                final Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
                if (selection instanceof Data) {
                    return ((Data) selection).getName();
                }
                return null;
            }

        };
        selectionToContent.setConverter(contentConverter);

        final UpdateValueStrategy methodToSelection = new UpdateValueStrategy();
        final IConverter methodToSelectionConverter = new Converter(String.class, IMethod.class) {

            @Override
            public Object convert(final Object methodName) {
                final IType type = ((PojoBrowserContentProvider) getContentProvider()).getType();
                if (type != null) {
                    try {
                        for (final IMethod method : type.getMethods()) {
                            if (method.getElementName().equals(methodName)) {
                                return method;
                            }
                        }
                    } catch (final JavaModelException e) {

                    }
                }
                return null;
            }

        };
        methodToSelection.setConverter(methodToSelectionConverter);

        final UpdateValueStrategy selectionToReturnType = new UpdateValueStrategy();
        final IConverter returnTypeConverter = new Converter(IType.class, String.class) {

            @Override
            public Object convert(final Object iType) {
                if (!editorInputExpression.isReturnTypeFixed()) {
                    if (iType instanceof IMethod) {
                        String typeErasure;
                        try {
                            typeErasure = Signature.getTypeErasure(((IMethod) iType).getReturnType());
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                            return Object.class.getName();
                        }
                        final IType declaringType = ((IMethod) iType).getDeclaringType();
                        return JDTMethodHelper.retrieveQualifiedType(typeErasure, declaringType);
                    } else if (iType instanceof IType) {
                        return ((IType) iType).getFullyQualifiedName();
                    }
                } else {
                    return returnTypeObservable.getValue();
                }
                return null;
            }

        };
        selectionToReturnType.setConverter(returnTypeConverter);

        final UpdateValueStrategy selectionToReferencedData = new UpdateValueStrategy();
        final IConverter referenceConverter = new Converter(Data.class, List.class) {

            @Override
            public Object convert(final Object data) {
                if (data != null) {
                    return Collections.singletonList(data);
                } else {
                    return Collections.emptyList();
                }
            }

        };
        selectionToReferencedData.setConverter(referenceConverter);

        final UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy();
        final IConverter referencetoDataConverter = new Converter(List.class, Data.class) {

            @SuppressWarnings("unchecked")
            @Override
            public Object convert(final Object dataList) {
                if (!((List<Data>) dataList).isEmpty()) {
                    final Data d = ((List<Data>) dataList).get(0);
                    final Collection<Data> inputData = (Collection<Data>) viewer.getInput();
                    for (final Data data : inputData) {
                        if (data.getName().equals(d.getName())
                                && data.getDataType().getName().equals(d.getDataType().getName())) {
                            return data;
                        }
                    }
                }
                return null;
            }

        };
        referencedDataToSelection.setConverter(referencetoDataConverter);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), referenceObservable,
                selectionToReferencedData,
                referencedDataToSelection);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), nameObservable, selectionToName,
                new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        final IViewerObservableValue javaViewerSingleSelection = ViewersObservables.observeSingleSelection(javaTreeviewer);
        dataBindingContext.bindValue(javaViewerSingleSelection, nameObservable, selectionToName, new UpdateValueStrategy(
                UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(javaViewerSingleSelection, contentObservable, selectionToContent, methodToSelection);
        dataBindingContext.bindValue(javaViewerSingleSelection, returnTypeObservable, selectionToReturnType,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(SWTObservables.observeText(typeText, SWT.Modify), returnTypeObservable);
        validationStatusProvider = new MultiValidator() {

            @Override
            protected IStatus validate() {
                return javaViewerSingleSelection.getValue() instanceof IMethod ? ValidationStatus.ok()
                        : ValidationStatus.error("");
            }
        };
        dataBindingContext.addValidationStatusProvider(validationStatusProvider);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor#dispose()
     */
    @Override
    public void dispose() {
        if (validationStatusProvider != null) {
            dataBindingContext.removeValidationStatusProvider(validationStatusProvider);
            validationStatusProvider.dispose();
        }
        super.dispose();
    }

    private boolean acceptExpression(final ExpressionViewer viewer, final Expression e, final EObject context,
            final ViewerFilter[] filters) {
        if (filters != null) {
            for (final ViewerFilter f : filters) {
                if (!f.select(viewer, context, e)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canFinish() {
        return !viewer.getSelection().isEmpty() && !javaTreeviewer.getSelection().isEmpty()
                && ((IStructuredSelection) javaTreeviewer.getSelection()).getFirstElement() instanceof IMethod;
    }

    @Override
    public void okPressed() {

    }

    @Override
    public Control getTextControl() {
        return null;
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
