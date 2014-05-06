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
package org.bonitasoft.studio.refactoring.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.compare.IContentChangeListener;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.internal.ContentChangeNotifier;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

public class CompareScript implements IStreamContentAccessor, IStructureComparator, IEditableContent, ITypedElement, IAdaptable, IContentChangeNotifier {

    private String name;

    private EObject content;

    private List<Object> children;

    private ContentChangeNotifier changeNotifier;

    private Image image;

    private EObject element;

    CompareScript(String name, EObject content) {
        this.name = name;
        this.content = content;
        children = new ArrayList<Object>();
    }

    @Override
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return ITypedElement.TEXT_TYPE;
    }

    @Override
    public InputStream getContents() throws CoreException {
        if (content instanceof Expression) {
            return new ByteArrayInputStream(((Expression) content).getContent().getBytes());
        }
        return null;
    }

    public Expression getContentExpression() {
        if (content instanceof Expression) {
            return (Expression) content;
        }
        return null;
    }

    @Override
    public Object[] getChildren() {
        return children.toArray();
    }

    public void addChild(Object child) {
        children.add(child);
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public ITypedElement replace(ITypedElement arg0, ITypedElement arg1) {
        return null;
    }

    @Override
    public void setContent(byte[] arg0) {
        if (content instanceof Expression) {
            ((Expression) content).setContent(new String(arg0));
        }
        if (changeNotifier != null) {
            changeNotifier.fireContentChanged();
        }
    }

    @Override
    public Object getAdapter(Class adapter) {
        return Platform.getAdapterManager().getAdapter(this, adapter);
    }

    @Override
    public void addContentChangeListener(IContentChangeListener listener) {
        if (changeNotifier == null) {
            changeNotifier = new ContentChangeNotifier(this);
        }
        changeNotifier.addContentChangeListener(listener);

    }

    @Override
    public void removeContentChangeListener(IContentChangeListener listener) {
        if (changeNotifier != null)
            changeNotifier.removeContentChangeListener(listener);

    }

    public ContentChangeNotifier getContentChangeNotifier() {
        return changeNotifier;
    }

    public EObject getElement() {
        return element;
    }

    public void setElement(EObject element) {
        this.element = element;
    }

}
