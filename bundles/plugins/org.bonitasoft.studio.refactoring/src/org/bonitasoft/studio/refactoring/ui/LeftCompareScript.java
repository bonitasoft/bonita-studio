/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.bonitasoft.studio.refactoring.core.script.ScriptContainer;
import org.eclipse.compare.IContentChangeNotifier;
import org.eclipse.compare.IEditableContent;
import org.eclipse.compare.IStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.IStructureComparator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

public class LeftCompareScript extends CompareScript implements IStreamContentAccessor, IStructureComparator, IEditableContent, ITypedElement, IAdaptable,
        IContentChangeNotifier {

    protected LeftCompareScript(final ScriptContainer<?> script, final AdapterFactoryLabelProvider labelProvider) {
        super(script, labelProvider);
    }

    @Override
    public InputStream getContents() throws CoreException {
        return new ByteArrayInputStream(script.getScript().getBytes());
    }

    @Override
    public void setContent(final byte[] content) {
        // do nothing
    }

}
