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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.eclipse.compare.IContentChangeListener;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.internal.ContentChangeNotifier;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;

public abstract class CompareScript extends EObjectNode implements IStreamContentAccessor, IStructureComparator, IEditableContent, ITypedElement, IAdaptable,
        IContentChangeNotifier {

    private final List<Object> children = new ArrayList<Object>();

    private ContentChangeNotifier changeNotifier;

    protected final ScriptContainer<?> script;

    private final AdapterFactoryLabelProvider labelProvider;

    CompareScript(final ScriptContainer<?> script, final AdapterFactoryLabelProvider labelProvider) {
        super(script.getModelElement(), labelProvider);
        this.script = script;
        this.labelProvider = labelProvider;
    }

    @Override
    public Image getImage() {
        return labelProvider.getImage(script.getModelElement());
    }

    @Override
    public String getName() {
        return script.getName();
    }

    @Override
    public String getType() {
        return ITypedElement.TEXT_TYPE;
    }

    @Override
    public Object[] getChildren() {
        return children.toArray();
    }

    public void addChild(final Object child) {
        children.add(child);
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public ITypedElement replace(final ITypedElement arg0, final ITypedElement arg1) {
        return null;
    }

    @Override
    public Object getAdapter(final Class adapter) {
        return Platform.getAdapterManager().getAdapter(this, adapter);
    }

    @Override
    public void addContentChangeListener(final IContentChangeListener listener) {
        if (changeNotifier == null) {
            changeNotifier = new ContentChangeNotifier(this);
        }
        changeNotifier.addContentChangeListener(listener);

    }

    @Override
    public void removeContentChangeListener(final IContentChangeListener listener) {
        if (changeNotifier != null) {
            changeNotifier.removeContentChangeListener(listener);
        }

    }

    public ContentChangeNotifier getContentChangeNotifier() {
        return changeNotifier;
    }

}
