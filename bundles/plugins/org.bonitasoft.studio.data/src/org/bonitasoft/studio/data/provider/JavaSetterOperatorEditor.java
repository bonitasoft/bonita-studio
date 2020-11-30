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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.provider.IOperatorEditor;
import org.bonitasoft.studio.groovy.ui.JDTMethodHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.corext.template.java.SignatureUtil;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class JavaSetterOperatorEditor implements IOperatorEditor {

    private TreeViewer javaTreeviewer;
    private final List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IOperatorEditor#appliesTo(java.lang.String)
     */
    @Override
    public boolean appliesTo(final String operatorType) {
        return ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operatorType);
    }

    @Override
    public boolean appliesTo(final Expression expression) {
        return expression != null
                && expression.getContent() != null
                && !expression.getContent().isEmpty()
                && ExpressionConstants.VARIABLE_TYPE.equals(expression.getType())
                && !expression.getReferencedElements().isEmpty()
                && expression.getReferencedElements().get(0) instanceof JavaObjectData;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IOperatorEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return isSetterOrDataSelected((ITreeSelection) javaTreeviewer.getSelection());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IOperatorEditor#createOpeartorEditor(org.eclipse.swt.widgets.Composite,
     * org.bonitasoft.studio.model.expression.Operator, org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public Composite createOpeartorEditor(final Composite parent, final Operator operator, final Expression sourceExpression) {
        final Composite client = new Composite(parent, SWT.NONE);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        client.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final JavaObjectData data = (JavaObjectData) sourceExpression.getReferencedElements().get(0);

        javaTreeviewer = new TreeViewer(client, SWT.SINGLE | SWT.BORDER);
        javaTreeviewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        javaTreeviewer.setContentProvider(new JavaSetterContentProvider());
        javaTreeviewer.setLabelProvider(new JavaUILabelProvider() {

            @Override
            public String getText(final Object item) {
                if (item instanceof IMethod) {
                    try {
                        return super.getText(item) + " - " + SignatureUtil.stripSignatureToFQN(((IMethod) item).getReturnType());
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
                    final boolean isValid = isSetterOrDataSelected(selection);
                    if (isValid) {
                        final TreePath path = selection.getPaths()[0];
                        final IMethod item = (IMethod) path.getSegment(path.getSegmentCount() - 1);
                        operator.setExpression(item.getElementName());
                        operator.getInputTypes().clear();
                        for (final String type : item.getParameterTypes()) {
                            final String qualifiedType = retrieveQualifiedType(item, type);
                            operator.getInputTypes().add(qualifiedType);
                        }
                    }
                }
                fireSelectionChange(event);
            }

            protected String retrieveQualifiedType(final IMethod item, final String type) {
                final String typeErasure = Signature.getTypeErasure(type);
                final IType declaringType = item.getDeclaringType();
                return JDTMethodHelper.retrieveQualifiedType(typeErasure, declaringType);
            }
        });
        
        String className = null;
        if (data.isMultiple()) {
            className = List.class.getName();
        } else if (data instanceof JavaObjectData) {
            className = data.getClassName();
        }
        if (className != null) {
            javaTreeviewer.setInput(className);
            javaTreeviewer.getTree().setFocus();
            javaTreeviewer.expandAll();
            final IMethod selectedMethod = getJavaSelectionFromContent(data, operator);
            if (selectedMethod != null) {
                javaTreeviewer.setSelection(new StructuredSelection(selectedMethod));
            }
        }
        return client;
    }
    
    @Override
    public StructuredViewer getViewer() {
        return javaTreeviewer;
    }

    protected void fireSelectionChange(final SelectionChangedEvent event) {
        for (final ISelectionChangedListener l : listeners) {
            l.selectionChanged(event);
        }
    }

    protected String generateJavaAdditionalPath(final ITreeSelection selection) {
        if (selection == null) {
            return "";
        }
        final TreePath path = selection.getPaths()[0];
        if (path.getSegmentCount() == 1) {
            return "";
        }
        final StringBuilder res = new StringBuilder();
        final Object item = path.getSegment(path.getSegmentCount() - 1);
        res.append(((IJavaElement) item).getElementName());
        return res.toString();
    }

    private boolean isSetterOrDataSelected(final ITreeSelection selection) {
        if (selection.getFirstElement() instanceof IMethod) {
            final IMethod method = (IMethod) selection.getFirstElement();
            try {
                return method.getParameterNames().length == 1;
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
                return false;
            }
        } else if (selection.getFirstElement() instanceof String) {
            return true;
        } else {
            return false;
        }
    }

    private IMethod getJavaSelectionFromContent(final JavaObjectData data, final Operator operator) {
        String className = null;
        if (data != null) {
            if (data.isMultiple()) {
                className = List.class.getName();
            } else if (data instanceof JavaObjectData) {
                className = data.getClassName();
            }
            if (className != null) {
                final String content = operator.getExpression();
                if (content != null && !content.isEmpty()) {

                    final IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
                    IType type = null;
                    try {
                        type = project.findType(className);
                        for (final IMethod m : type.getMethods()) {
                            final String method = m.getElementName();
                            if (method.equals(content)) {
                                return m;
                            }
                        }
                    } catch (final JavaModelException e) {
                        BonitaStudioLog.error(e);
                    }

                }
            }
        }
        return null;
    }

    @Override
    public void addSelectionChangedListener(final ISelectionChangedListener listener) {
        listeners.add(listener);
    }

    @Override
    public ISelection getSelection() {
        return javaTreeviewer.getSelection();
    }

    @Override
    public void removeSelectionChangedListener(final ISelectionChangedListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setSelection(final ISelection selection) {
        javaTreeviewer.setSelection(selection);
    }

}
