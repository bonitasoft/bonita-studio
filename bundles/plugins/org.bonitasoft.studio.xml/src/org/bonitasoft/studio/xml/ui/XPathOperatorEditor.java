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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.xml.ui;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.provider.IOperatorEditor;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.bonitasoft.studio.xml.ui.XSDContentProvider.Append;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDParticle;


/**
 * @author Romain Bioteau
 *
 */
public class XPathOperatorEditor implements IOperatorEditor {

    private Text xpathText;
    private final List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();
    private TreeViewer xsdViewer;

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IOpeartorEditor#appliesTo(java.lang.String)
     */
    @Override
    public boolean appliesTo(String operatorType) {
        return ExpressionConstants.XPATH_UPDATE_OPERATOR.equals(operatorType);
    }

    @Override
    public boolean appliesTo(Expression expression) {
        return expression != null 
        		&& expression.getContent() != null
        		&& !expression.getContent().isEmpty() 
        		&& ExpressionConstants.VARIABLE_TYPE.equals(expression.getType())
        		&& !expression.getReferencedElements().isEmpty()
        		&& expression.getReferencedElements().get(0) instanceof XMLData;
    }
    
    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IOpeartorEditor#canFinish()
     */
    @Override
    public boolean canFinish() {
        return xpathText != null && !xpathText.getText().isEmpty();
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IOpeartorEditor#createOpeartorEditor(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public Composite createOpeartorEditor(Composite parent,final Operator operator,final Expression sourceExpression) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 5).create());

        final XMLData data = (XMLData) sourceExpression.getReferencedElements().get(0) ;
        String namespace = data.getNamespace() ;
        String element = data.getType();
        XSDRepositoryStore xsdStore = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class) ;

        XSDElementDeclaration root = xsdStore.findElementDeclaration(namespace, element);
        final XSDContentProvider provider =   new XSDContentProvider(true) ;
        provider.setElement(root);

        xsdViewer = new TreeViewer(composite);
        xsdViewer.setContentProvider(provider);
        XSDLabelProvider labelProvider = new XSDLabelProvider();
        xsdViewer.setLabelProvider(new DecoratingLabelProvider(labelProvider, labelProvider));
        xsdViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        xsdViewer.setInput(new Object());

        String content = operator.getExpression() ;
        if(content == null){
            content = "" ;
        }
        xsdViewer.setSelection(new StructuredSelection(createTreePath(content, provider)));

        xpathText = new Text(composite, SWT.WRAP | SWT.BORDER);
        xpathText.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 40).grab(true, false).create());
        xpathText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                operator.setExpression(xpathText.getText()) ;
            }
        });


        xsdViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ITreeSelection selection = (ITreeSelection) xsdViewer.getSelection();
                String xpath = computeXPath(selection, false);
                if(xpath == null || xpath.isEmpty()){
                    xpathText.setText(data.getName());
                }else{
                    xpathText.setText(xpath);
                }

                xpathText.redraw();
                fireSelectionChange(event) ;
            }
        });
        return composite ;
    }
    
    @Override
    public StructuredViewer getViewer() {
        return xsdViewer;
    }



    protected String computeXPath(ITreeSelection selection) {
        return computeXPath(selection, false);
    }

    protected String computeXPath(ITreeSelection selection, boolean useQualifiedName) {
        if (selection.getPaths().length == 0) {
            return "";
        }

        TreePath path = selection.getPaths()[0];
        return computeXPath(path, useQualifiedName);
    }



    protected String computeXPath(TreePath path, boolean useQualifiedName) {
        StringBuilder pathBuilder = new StringBuilder();
        for (int i = 1; i < path.getSegmentCount(); i++) {
            if (path.getSegment(i) instanceof XSDContentProvider.Append) {
                continue;
            }

            pathBuilder.append('/');
            XSDNamedComponent item = (XSDNamedComponent)path.getSegment(i);
            if (item instanceof XSDAttributeDeclaration) {
                pathBuilder.append('@');
            }
            if(useQualifiedName){
                pathBuilder.append(item.getQName());
            } else {
                pathBuilder.append(item.getName());
            }
            if (item instanceof XSDElementDeclaration) {
                XSDElementDeclaration element = (XSDElementDeclaration)item;
                if (element.getContainer() instanceof XSDParticle) {
                    XSDParticle particle = (XSDParticle)element.getContainer();
                    if (particle.getMaxOccurs() < 0 || particle.getMinOccurs() > 1) {
                        pathBuilder.append("[1]");
                    }
                }
            }
        }
        if (path.getLastSegment() instanceof XSDElementDeclaration &&
                ((XSDElementDeclaration)path.getLastSegment()).getType().getSimpleType() != null) {
            pathBuilder.append("/text()");
        }
        if (path.getLastSegment() instanceof XSDContentProvider.Append) {
            pathBuilder.append(BonitaConstants.XPATH_VAR_SEPARATOR + BonitaConstants.XPATH_APPEND_FLAG);
        }
        return pathBuilder.toString();
    }

    protected TreePath createTreePath(String details, XSDContentProvider provider) {
        String[] pieces = details.split("\\" + BonitaConstants.XPATH_VAR_SEPARATOR);
        boolean append = false;
        if (pieces.length > 1 && pieces[1].equals(BonitaConstants.XPATH_APPEND_FLAG)) {
            append = true;
        }
        details = pieces[0];
        List<String> segments = new ArrayList<String>();
        for (String segment : details.split("/")) {
            if (segment.length() != 0 && ! segment.equals("text()")) {
                if (segment.startsWith("@")) {
                    segments.add(segment.substring(1));
                } else if (segment.contains("[")) {
                    segments.add(segment.substring(0, segment.indexOf("[")));
                } else {
                    segments.add(segment);
                }
            }
        }
        List<Object> res = new ArrayList<Object>();
        Object current = XSDContentProvider.WHOLE_XML;
        res.add(current);
        for (String segment : segments) {
            for (Object item : provider.getChildren(current)) {
                if (item instanceof XSDNamedComponent && ((XSDNamedComponent)item).getName().equals(segment)) {
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

    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        listeners.add(listener) ;
    }

    protected void fireSelectionChange(SelectionChangedEvent event) {
        for(ISelectionChangedListener l : listeners){
            l.selectionChanged(event) ;
        }
    }

    @Override
    public ISelection getSelection() {
        return xsdViewer.getSelection();
    }

    @Override
    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        listeners.remove(listener) ;
    }

    @Override
    public void setSelection(ISelection selection) {
        xsdViewer.setSelection(selection) ;
    }
}
