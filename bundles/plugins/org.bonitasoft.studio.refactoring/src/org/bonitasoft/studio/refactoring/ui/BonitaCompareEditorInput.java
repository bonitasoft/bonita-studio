/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.refactoring.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.IContentChangeListener;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.DiffTreeViewer;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.IDiffElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class BonitaCompareEditorInput extends CompareEditorInput {

    private List<Expression> newExpressions;

    private List<Expression> oldExpressions;

    private DiffNode root;

    private DiffTreeViewer viewer;

    private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);

    private RefactoringOperationType operationType;

    private String elementName;

    private String newName;

    private boolean applyChanges = false;

    private boolean canBeContainedInScript = true;

    public BonitaCompareEditorInput(final CompareConfiguration configuration, final List<Expression> oldExpressions, final List<Expression> newExpressions,
            final RefactoringOperationType operationType,
            final String elementName, final String newName, final boolean canBeContainedInScript) {
        super(configuration);
        this.oldExpressions = oldExpressions;
        this.newExpressions = newExpressions;
        this.elementName = elementName;
        this.operationType = operationType;
        this.newName = newName;
        this.canBeContainedInScript = canBeContainedInScript;
        root = new DiffNode(Differencer.NO_CHANGE) {

            @Override
            public boolean hasChildren() {
                return true;
            }
        };

    }

    @Override
    protected Object prepareInput(final IProgressMonitor arg0)
            throws InvocationTargetException, InterruptedException {
        setDirty(true);
        if (!oldExpressions.isEmpty() && !newExpressions.isEmpty() && oldExpressions.size() == newExpressions.size()) {
            buildTree();
            return root;
        }
        return null;

    }

    @Override
    public void setTitle(final String title) {
        if (operationType == RefactoringOperationType.UPDATE) {
            super.setTitle(Messages.bind(Messages.refactorTitle, elementName));
        } else {
            super.setTitle(Messages.bind(Messages.removeTitle, elementName));
        }
    }

    @Override
    public void setStatusMessage(final String message) {
        super.setStatusMessage(message);
    }

    @Override
    public Control createContents(final Composite parent) {
        final CLabel label = new CLabel(parent, SWT.NONE);
        final String lastArgumentWithConditionOrNot = canBeContainedInScript ? " " + Messages.reviewChangesMessageWithConditionPlace : "";
        if (operationType == RefactoringOperationType.UPDATE) {
            label.setText(Messages.bind(Messages.reviewChangesMessageRefactoring, new String[] { elementName, newName, lastArgumentWithConditionOrNot }));
        } else {
            label.setText(Messages.bind(Messages.reviewChangesMessageRemoving, elementName, lastArgumentWithConditionOrNot));
        }
        label.setImage(Display.getCurrent().getSystemImage(SWT.ICON_WARNING));
        return super.createContents(parent);
    }

    @Override
    public Viewer createDiffViewer(final Composite parent) {
        viewer = new DiffTreeViewer(parent, getCompareConfiguration());
        viewer.addOpenListener(new IOpenListener() {

            @Override
            public void open(final OpenEvent event) {
                final IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                final DiffNode obj = (DiffNode) sel.getFirstElement();
                obj.setDontExpand(false);
            }
        });
        viewer.setFilters(getFilters());
        viewer.setLabelProvider(new DiffTreeLabelProvider());
        viewer.setContentProvider(new BonitaScriptRefactoringContentProvider());
        viewer.setInput(root);
        viewer.expandAll();

        return viewer;
    }

    @Override
    public String getTitle() {
        if (operationType == RefactoringOperationType.UPDATE) {
            return Messages.bind(Messages.refactorTitle, elementName);
        } else {
            return Messages.bind(Messages.removeTitle, elementName);
        }
    }

    public DiffTreeViewer getViewer() {
        return viewer;
    }

    public DiffNode getRoot() {
        return root;
    }

    public void setRoot(final DiffNode root) {
        this.root = root;
    }

    private ViewerFilter[] getFilters() {
        return new ViewerFilter[] { new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement,
                    final Object element) {
                return true;
            }
        }
        };
    }

    @Override
    public boolean canRunAsJob() {
        return true;
    }

    public void buildTree() {
        for (int i = 0; i < oldExpressions.size(); i++) {
            final Expression oldExpression = oldExpressions.get(i);
            final CompareScript left = new CompareScript(oldExpression.getName(), oldExpression);
            left.setElement(oldExpression);
            left.setImage(adapterFactoryLabelProvider.getImage(oldExpression));
            final Expression newExpression = newExpressions.get(i);

            final CompareScript right = new CompareScript(newExpression.getName(), newExpression);
            right.setElement(newExpression);
            right.addContentChangeListener(new IContentChangeListener() {

                @Override
                public void contentChanged(final IContentChangeNotifier compareScript) {
                    if (compareScript instanceof CompareScript) {
                        setDirty(true);
                        if (getViewer() == null || getViewer().getControl().isDisposed()) {
                            return;
                        }
                        getViewer().refresh(true);
                    }
                }
            });
            right.setImage(adapterFactoryLabelProvider.getImage(newExpression));
            final DiffNode leaf = new DiffNode(null, Differencer.CHANGE, null, left, right);
            final DiffNode poolNode = buildPathNodes(oldExpression.eContainer(), leaf);
            if (((CompareScript) poolNode.getAncestor()).getElement() instanceof Pool && root.getChildren().length == 0) {
                root.add(poolNode);
            }
        }
    }

    private DiffNode buildPathNodes(final EObject container, final DiffNode node) {
        final DiffNode parentNode = new DiffNode(Differencer.NO_CHANGE);
        node.setParent(parentNode);
        parentNode.add(node);
        CompareScript ancestor = null;
        final String name = adapterFactoryLabelProvider.getText(container);
        final Expression expr = ExpressionHelper.createConstantExpression(name, String.class.getName());
        expr.setName(name);
        ancestor = new CompareScript(expr.getName(), expr);
        ancestor.setElement(container);
        ancestor.setImage(adapterFactoryLabelProvider.getImage(container));
        parentNode.setAncestor(ancestor);

        if (insertParentNode(parentNode)) {
            return parentNode;
        }
        if (container instanceof Pool) {
            return parentNode;
        }
        if (container instanceof ConnectorParameter) {
            return buildPathNodes(container.eContainer().eContainer(), parentNode);
        }
        return buildPathNodes(container.eContainer(), parentNode);
    }

    private void getAllNodes(final List<DiffNode> nodes, final DiffNode root) {
        if (root.hasChildren()) {
            nodes.addAll((Collection<? extends DiffNode>) Arrays.asList(root.getChildren()));
            for (final IDiffElement child : root.getChildren()) {
                getAllNodes(nodes, (DiffNode) child);
            }
        }

    }

    private boolean insertParentNode(final DiffNode nodeToInsert) {
        final List<DiffNode> nodes = new ArrayList<DiffNode>();
        getAllNodes(nodes, root);
        for (final DiffNode node : nodes) {
            if (node.getAncestor() != null
                    && ((CompareScript) node.getAncestor()).getElement().equals(((CompareScript) nodeToInsert.getAncestor()).getElement())) {
                addChildrenToParent(node, nodeToInsert.getChildren());
                return true;
            }
        }
        return false;
    }

    private void addChildrenToParent(final DiffNode parent, final IDiffElement[] children) {
        for (final IDiffElement child : children) {
            parent.add(child);
        }
    }

    @Override
    public void saveChanges(final IProgressMonitor monitor) throws CoreException {
        super.saveChanges(monitor);
        applyChanges = true;
    }

    public boolean applyChanges() {
        return applyChanges;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    @Override
    public Object getCompareResult() {
        return super.getCompareResult();
    }

}
