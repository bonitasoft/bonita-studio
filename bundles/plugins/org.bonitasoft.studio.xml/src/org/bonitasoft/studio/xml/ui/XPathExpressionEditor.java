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
package org.bonitasoft.studio.xml.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.DataStyledTreeLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.provider.SelectionAwareExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.xml.Messages;
import org.bonitasoft.studio.xml.api.XPathReturnType;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.bonitasoft.studio.xml.ui.XSDContentProvider.Append;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDParticle;

/**
 * @author Romain Bioteau
 *
 */
public class XPathExpressionEditor extends SelectionAwareExpressionEditor implements IExpressionEditor {

    private Expression editorInputExpression;

    private Composite mainComposite;

    private TableViewer viewer;

    private XSDContentProvider provider;

    private String dataName;

    private TreeViewer xsdViewer;

    private ComboViewer typeCombo;

    private Text text;

    private boolean isPageFlowContext = false;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#createExpressionEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public Control createExpressionEditor(final Composite parent, final EMFDataBindingContext ctx) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(new GridLayout(2, true));

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
        viewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                if (!event.getSelection().isEmpty()) {
                    xsdViewer.expandAll();
                    XPathExpressionEditor.this.fireSelectionChanged();
                }
            }
        });

        createXPathChooser(mainComposite);
        createReturnTypeComposite(parent);

        return mainComposite;
    }

    protected void createReturnTypeComposite(final Composite parent) {
        final Composite typeComposite = new Composite(parent, SWT.NONE);
        typeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final GridLayout gl = new GridLayout(2, false);
        gl.marginWidth = 0;
        gl.marginHeight = 0;
        typeComposite.setLayout(gl);

        final Label typeLabel = new Label(typeComposite, SWT.NONE);
        typeLabel.setText(Messages.returnType);
        typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());

        typeCombo = new ComboViewer(typeComposite, SWT.BORDER | SWT.READ_ONLY);
        typeCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());
        typeCombo.setContentProvider(new ArrayContentProvider());
        typeCombo.setInput(XPathReturnType.getAvailableReturnTypes());
        typeCombo.setSelection(new StructuredSelection(XPathReturnType.XPATH_STRING));

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return !xsdViewer.getSelection().isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionEditor#okPressed()
     */
    @Override
    public void okPressed() {
    }

    @Override
    public void bindExpression(final EMFDataBindingContext dataBindingContext,
            final EObject context, final Expression inputExpression, final ViewerFilter[] filters, final ExpressionViewer expressionViewer) {
        editorInputExpression = inputExpression;
        final Set<Data> input = new HashSet<Data>();
        final IExpressionProvider provider = ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
        for (final Expression e : provider.getExpressions(context)) {

            final EObject data = e.getReferencedElements().get(0);
            if (data instanceof XMLData) {
                input.add((XMLData) data);
            }
        }
        viewer.setInput(input);

        final IObservableValue nameObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
        final IObservableValue contentObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT);
        final IObservableValue returnTypeObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
        final IObservableValue referenceObservable = EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS);

        final UpdateValueStrategy selectionToName = new UpdateValueStrategy();
        final IConverter nameConverter = new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object data) {
                if (data instanceof Data) {
                    final XMLData xmlData = (XMLData) data;
                    return xmlData.getName() + " - " + editorInputExpression.getContent();
                } else if (data instanceof String) {
                    final XMLData xmlData = (XMLData) ((IStructuredSelection) viewer.getSelection()).getFirstElement();

                    return xmlData.getName() + " - " + data;

                }
                return null;
            }

        };
        selectionToName.setConverter(nameConverter);

        final UpdateValueStrategy selectionToContent = new UpdateValueStrategy();
        final IConverter contentConverter = new Converter(Object.class, String.class) {

            @Override
            public Object convert(final Object value) {
                if (!xsdViewer.getSelection().isEmpty()) {
                    return computeXPath((ITreeSelection) xsdViewer.getSelection());
                }
                final Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
                if (selection instanceof Data) {
                    return ((Data) selection).getName();
                }
                return null;
            }

        };
        selectionToContent.setConverter(contentConverter);

        final UpdateValueStrategy contentToSelection = new UpdateValueStrategy();
        final IConverter methodToSelectionConverter = new Converter(String.class, Object.class) {

            @Override
            public Object convert(final Object xPathExpression) {
                if (xPathExpression instanceof String) {
                    final ITreeSelection selection = new org.eclipse.jface.viewers.TreeSelection(createTreePath((String) xPathExpression,
                            (XSDContentProvider) xsdViewer.getContentProvider()));
                    return selection.getFirstElement();
                }
                return null;
            }

        };
        contentToSelection.setConverter(methodToSelectionConverter);

        final UpdateValueStrategy selectionToReturnType = new UpdateValueStrategy();
        selectionToReturnType.setConverter(new Converter(Object.class, String.class) {

            @Override
            public Object convert(final Object element) {
                if (editorInputExpression.isReturnTypeFixed()) {
                    return returnTypeObservable.getValue();
                } else {
                    return XPathReturnType.getType(element);
                }

            }

        });

        final UpdateValueStrategy selectionToReferencedData = new UpdateValueStrategy();
        final IConverter referenceConverter = new Converter(Data.class, List.class) {

            @Override
            public Object convert(final Object data) {
                if (data != null) {
                    final XMLData xmlData = (XMLData) data;
                    dataName = xmlData.getName();
                    final String namespace = xmlData.getNamespace();
                    final String element = xmlData.getType();
                    final XSDRepositoryStore xsdStore = RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class);
                    final XSDElementDeclaration root = xsdStore.findElementDeclaration(namespace, element);
                    final XSDElementDeclaration existingElement = ((XSDContentProvider) xsdViewer.getContentProvider()).getElement();
                    if (existingElement == null || !existingElement.equals(root)) {
                        ((XSDContentProvider) xsdViewer.getContentProvider()).setElement(root);
                    }
                    return Collections.singletonList(data);
                } else {
                    return Collections.emptyList();
                }
            }

        };
        selectionToReferencedData.setConverter(referenceConverter);

        final UpdateValueStrategy referencedDataToSelection = new UpdateValueStrategy();
        final IConverter referencetoDataConverter = new Converter(List.class, Data.class) {

            @Override
            public Object convert(final Object dataList) {
                final Data d = ((List<Data>) dataList).get(0);
                final Collection<Data> inputData = (Collection<Data>) viewer.getInput();
                for (final Data data : inputData) {
                    if (data.getName().equals(d.getName()) && data.getDataType().getName().equals(d.getDataType().getName())) {
                        final XMLData xmlData = (XMLData) data;
                        dataName = xmlData.getName();
                        final String namespace = xmlData.getNamespace();
                        final String element = xmlData.getType();
                        final XSDRepositoryStore xsdStore = RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class);
                        final XSDElementDeclaration root = xsdStore.findElementDeclaration(namespace, element);
                        ((XSDContentProvider) xsdViewer.getContentProvider()).setElement(root);
                        return data;
                    }
                }
                return null;
            }

        };
        referencedDataToSelection.setConverter(referencetoDataConverter);

        final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        });
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(viewer), referenceObservable, selectionToReferencedData,
                referencedDataToSelection);
        final ISWTObservableValue observeText = SWTObservables.observeText(text, SWT.Modify);
        dataBindingContext.bindValue(observeText, nameObservable, selectionToName, new UpdateValueStrategy(
                UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(observeText, contentObservable);

        dataBindingContext.bindValue(SWTObservables.observeEnabled(xsdViewer.getTree()), ViewersObservables.observeSingleSelection(viewer),
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER), enableStrategy);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(xsdViewer), contentObservable, selectionToContent, contentToSelection);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(xsdViewer), returnTypeObservable, selectionToReturnType,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));

        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo), returnTypeObservable);
    }

    private void createXPathChooser(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final GridLayout gl = new GridLayout(1, false);
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        composite.setLayout(gl);
        final Label label = new Label(composite, SWT.WRAP);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 30).create());
        label.setText(Messages.selectElementLabel);

        xsdViewer = new TreeViewer(composite);
        provider = new XSDContentProvider(true);
        xsdViewer.setComparer(new IElementComparer() {

            @Override
            public int hashCode(final Object element) {
                return element.hashCode();
            }

            @Override
            public boolean equals(final Object a, final Object b) {
                if (a instanceof XSDFeature && b instanceof XSDFeature) {
                    final String aName = ((XSDFeature) a).getName();
                    final String bName = ((XSDFeature) b).getName();
                    if (aName.equals(bName)) {
                        return EcoreUtil.equals(((XSDFeature) a).getType(), ((XSDFeature) b).getType());
                    }
                }
                return a.equals(b);
            }
        });
        xsdViewer.setContentProvider(provider);
        final XSDLabelProvider labelProvider = new XSDLabelProvider();
        xsdViewer.setLabelProvider(new DecoratingLabelProvider(labelProvider, labelProvider));
        final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.minimumHeight = 100;
        xsdViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 150).create());
        xsdViewer.setInput(new Object());

        text = new Text(composite, SWT.WRAP | SWT.BORDER);
        text.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 40).create());

        xsdViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final ITreeSelection selection = (ITreeSelection) xsdViewer.getSelection();
                //                final String xpath = computeXPath(selection, false);
                //                if (dataName != null) {
                //                    if (xpath == null || xpath.isEmpty()) {
                //                        text.setText(dataName);
                //                    } else {
                //                        text.setText(xpath);
                //                    }
                //                }
                text.redraw();
                //                typeCombo.setSelection(new StructuredSelection(XPathReturnType.getType(selection.getFirstElement())));
                XPathExpressionEditor.this.fireSelectionChanged();
            }
        });

    }

    private TreePath createTreePath(String details, final XSDContentProvider provider) {
        final String[] pieces = details.split("\\" + BonitaConstants.XPATH_VAR_SEPARATOR);
        boolean append = false;
        if (pieces.length > 1 && pieces[1].equals(BonitaConstants.XPATH_APPEND_FLAG)) {
            append = true;
        }
        details = pieces[0];
        final List<String> segments = new ArrayList<String>();
        for (final String segment : details.split("/")) {
            if (segment.length() != 0 && !segment.equals("text()")) {
                if (segment.startsWith("@")) {
                    segments.add(segment.substring(1));
                } else if (segment.contains("[")) {
                    segments.add(segment.substring(0, segment.indexOf("[")));
                } else {
                    segments.add(segment);
                }
            }
        }
        final List<Object> res = new ArrayList<Object>();
        Object current = XSDContentProvider.WHOLE_XML;
        res.add(current);
        for (final String segment : segments) {
            for (final Object item : provider.getChildren(current)) {
                if (item instanceof XSDNamedComponent && ((XSDNamedComponent) item).getName().equals(segment)) {
                    current = item;
                    res.add(current);
                    break;
                }
            }
        }
        if (append) {
            res.add(Append.newInstance(current));
        }
        return new TreePath(res.toArray());
    }

    /**
     * @param selection
     * @return
     */
    public static String computeXPath(final ITreeSelection selection) {
        return computeXPath(selection, false);
    }

    public static String computeXPath(final ITreeSelection selection, final boolean useQualifiedName) {
        if (selection.getPaths().length == 0) {
            return "";
        }

        final TreePath path = selection.getPaths()[0];
        return computeXPath(path, useQualifiedName);
    }

    /**
     * @param path
     * @return
     */
    public static String computeXPath(final TreePath path) {
        return computeXPath(path, false);
    }

    public static String computeXPath(final TreePath path, final boolean useQualifiedName) {
        final StringBuilder pathBuilder = new StringBuilder();
        for (int i = 1; i < path.getSegmentCount(); i++) {
            if (path.getSegment(i) instanceof XSDContentProvider.Append) {
                continue;
            }

            pathBuilder.append('/');
            final XSDNamedComponent item = (XSDNamedComponent) path.getSegment(i);
            if (item instanceof XSDAttributeDeclaration) {
                pathBuilder.append('@');
            }
            if (useQualifiedName) {
                pathBuilder.append(item.getQName());
            } else {
                pathBuilder.append(item.getName());
            }
            if (item instanceof XSDElementDeclaration) {
                final XSDElementDeclaration element = (XSDElementDeclaration) item;
                if (element.getContainer() instanceof XSDParticle) {
                    final XSDParticle particle = (XSDParticle) element.getContainer();
                    if (particle.getMaxOccurs() < 0 || particle.getMinOccurs() > 1) {
                        pathBuilder.append("[1]");
                    }
                }
            }
        }
        if (path.getLastSegment() instanceof XSDElementDeclaration &&
                ((XSDElementDeclaration) path.getLastSegment()).getType().getSimpleType() != null) {
            pathBuilder.append("/text()");
        }
        if (path.getLastSegment() instanceof XSDContentProvider.Append) {
            pathBuilder.append(BonitaConstants.XPATH_VAR_SEPARATOR + BonitaConstants.XPATH_APPEND_FLAG);
        }
        return pathBuilder.toString();
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
