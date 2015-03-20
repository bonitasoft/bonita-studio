/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.wizard.CreateVariableProposalListener;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class DataExpressionEditor extends SelectionAwareExpressionEditor
        implements IExpressionEditor {

    private TableViewer viewer;

    private GridLayout gridLayout;

    private Expression editorInputExpression;

    private Composite mainComposite;

    private Text typeText;

    private IObservableValue returnTypeObservable = null;

    private Button addExpressionButton;

    private boolean isPageFlowContext = false;

    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        gridLayout = new GridLayout(1, false);
        mainComposite.setLayout(gridLayout);

        viewer = new TableViewer(mainComposite, SWT.FULL_SELECTION | SWT.BORDER
                | SWT.SINGLE | SWT.V_SCROLL);

        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(100, false));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());

        final TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.NONE);
        final TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);

        final TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(column);

        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setLabelProvider(new DataStyledTreeLabelProvider());

        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    DataExpressionEditor.this.fireSelectionChanged();
                }
            }
        });

        createReturnTypeComposite(parent);

        addExpressionButton = new Button(parent, SWT.FLAT);
        addExpressionButton.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.LEFT, SWT.CENTER).hint(85, SWT.DEFAULT).create());
        addExpressionButton.setText(Messages.addData);

        return mainComposite;
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        final GridLayout gl = new GridLayout(2, false);
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        typeComposite.setLayout(gl);

        final Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.FILL, SWT.CENTER).create());

        typeText = new Text(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .align(SWT.FILL, SWT.CENTER).indent(10, 0).create());
    }

    protected void handleSpecificDatatypeEdition(final Data data) {
        if (gridLayout.numColumns > 1) {
            mainComposite.getChildren()[1].dispose();
            gridLayout.numColumns--;
            viewer.getTable().setLayoutData(
                    GridDataFactory.fillDefaults().grab(true, true).create());
            mainComposite.layout();
        }

    }

    private void expressionButtonListener(final EObject context, final ExpressionViewer expressionViewer,
            final ViewerFilter[] filters) throws CoreException {
        for (final IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements("org.bonitasoft.studio.expression.proposalListener")) {
            final String expressionTypeLink = element.getAttribute("type");
            if (expressionTypeLink.equals(ExpressionConstants.VARIABLE_TYPE)) {
                final IProposalListener proposalListener = (IProposalListener) element.createExecutableExtension("providerClass");
                if (proposalListener.isRelevant(context) && proposalListener instanceof CreateVariableProposalListener) {
                    expressionViewer.getContentProposal().addNewData(proposalListener);
                    fillViewerData(context, filters);
                    return;
                }
            }
        }
    }

    private void fillViewerData(final EObject context, final ViewerFilter[] filters) {
        final Set<Data> input = new HashSet<Data>();
        final IExpressionProvider provider = ExpressionEditorService.getInstance()
                .getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
        final Set<Expression> expressions = provider.getExpressions(context);
        final Set<Expression> filteredExpressions = new HashSet<Expression>();
        if (expressions != null) {
            filteredExpressions.addAll(expressions);
            if (input != null && filters != null) {
                for (final Expression exp : expressions) {
                    for (final ViewerFilter filter : filters) {
                        if (filter != null
                                && !filter.select(viewer, context, exp)) {
                            filteredExpressions.remove(exp);
                        }
                    }
                }
            }
        }
        for (final Expression e1 : filteredExpressions) {
            if (editorInputExpression.isReturnTypeFixed()) {
                if (compatibleReturnType(editorInputExpression, e1)) {
                    input.add((Data) e1.getReferencedElements().get(0));
                }
            } else {
                input.add((Data) e1.getReferencedElements().get(0));
            }
        }
        viewer.setInput(input);
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext,
            final EObject context, final Expression inputExpression, final ViewerFilter[] filters, final ExpressionViewer expressionViewer) {

        final EObject finalContext = context;
        if (context instanceof Widget && ModelHelper.getPageFlow((Widget) context) instanceof Pool) {
            addExpressionButton.setEnabled(false);
        }
        final ViewerFilter[] finalFilters = filters;
        addExpressionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                try {
                    expressionButtonListener(finalContext, expressionViewer, finalFilters);
                } catch (final CoreException e1) {
                    BonitaStudioLog.error(e1);
                }
            }
        });

        editorInputExpression = inputExpression;
        fillViewerData(context, filters);

        final IObservableValue contentObservable = EMFObservables
                .observeValue(inputExpression,
                        ExpressionPackage.Literals.EXPRESSION__CONTENT);
        final IObservableValue nameObservable = EMFObservables.observeValue(
                inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        returnTypeObservable = EMFObservables.observeValue(inputExpression,
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final IObservableValue referenceObservable = EMFObservables.observeValue(
                inputExpression,
                ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        final UpdateValueStrategy selectionToName = new UpdateValueStrategy();
        final IConverter nameConverter = new Converter(Data.class, String.class) {

            @Override
            public Object convert(final Object data) {
                return ((Data) data).getName();
            }

        };
        selectionToName.setConverter(nameConverter);

        final UpdateValueStrategy selectionToContent = new UpdateValueStrategy();
        final IConverter contentConverter = new Converter(Data.class, String.class) {

            @Override
            public Object convert(final Object data) {
                return ((Data) data).getName();
            }

        };
        selectionToContent.setConverter(contentConverter);

        final UpdateValueStrategy selectionToReturnType = new UpdateValueStrategy();
        final IConverter returnTypeConverter = new Converter(Data.class, String.class) {

            @Override
            public Object convert(final Object data) {
                return org.bonitasoft.studio.common.DataUtil
                        .getTechnicalTypeFor((Data) data);
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
        final IConverter referencetoDataConverter = new Converter(List.class,
                Data.class) {

            @Override
            public Object convert(final Object dataList) {
                final List<Data> list = (List<Data>) dataList;
                if (!list.isEmpty()) {
                    final Data d = list.get(0);
                    final Collection<Data> inputData = (Collection<Data>) viewer
                            .getInput();
                    for (final Data data : inputData) {
                        if (data.getName().equals(d.getName())
                                && data.getDataType().getName()
                                        .equals(d.getDataType().getName())) {
                            return data;
                        }
                    }
                }
                return null;
            }

        };
        referencedDataToSelection.setConverter(referencetoDataConverter);

        dataBindingContext.bindValue(ViewersObservables
                .observeSingleSelection(viewer), nameObservable,
                selectionToName, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(ViewersObservables
                .observeSingleSelection(viewer), contentObservable,
                selectionToContent, new UpdateValueStrategy(
                        UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                ViewersObservables.observeSingleSelection(viewer),
                returnTypeObservable, selectionToReturnType,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(
                ViewersObservables.observeSingleSelection(viewer),
                referenceObservable, selectionToReferencedData,
                referencedDataToSelection);
        dataBindingContext.bindValue(
                SWTObservables.observeText(typeText, SWT.Modify),
                returnTypeObservable);

        if (context instanceof DateFormField) {
            handleDateFormFieldBinding();
        }

    }

    protected void handleDateFormFieldBinding() {
        final ControlDecoration cd = new ControlDecoration(typeText, SWT.TOP | SWT.LEFT);
        // cd.setImage(Pics.getImage(PicsConstants.error)) ;
        cd.setImage(PlatformUI.getWorkbench().getSharedImages()
                .getImage(ISharedImages.IMG_OBJS_WARN_TSK));
        cd.setDescriptionText("It is recommanded to use Return type ad String or Date for Date Widget");
        cd.setShowOnlyOnFocus(false);
        updateTypeTextControlDecorationVisibility(cd);

        returnTypeObservable
                .addValueChangeListener(new IValueChangeListener() {

                    @Override
                    public void handleValueChange(final ValueChangeEvent event) {
                        updateTypeTextControlDecorationVisibility(cd);
                    }

                });
    }

    protected void updateTypeTextControlDecorationVisibility(final ControlDecoration cd) {
        final String typeAsString = typeText.getText();
        if (Date.class.getName().equals(typeAsString)
                || String.class.getName().equals(
                        typeAsString)) {
            cd.hide();
        } else {
            cd.show();
        }
    }

    @Override
    public boolean canFinish() {
        return !viewer.getSelection().isEmpty();
    }

    @Override
    public void okPressed() {
        if (!editorInputExpression.getContent().equals(
                editorInputExpression.getName())) {
            editorInputExpression.setName(editorInputExpression.getContent());
        }
    }

    @Override
    public Control getTextControl() {
        return null;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (returnTypeObservable != null) {
            returnTypeObservable.dispose();
        }
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
