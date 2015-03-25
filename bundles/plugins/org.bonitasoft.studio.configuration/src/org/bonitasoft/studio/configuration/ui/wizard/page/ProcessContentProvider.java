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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class ProcessContentProvider implements ITreeContentProvider {

    public static final String OTHER_PROCESSES = Messages.otherProcesses;
    private MainProcess diagram;
    private final DiagramRepositoryStore diagramStore;

    public ProcessContentProvider() {
        diagramStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(final Object element) {
        if (element.equals(OTHER_PROCESSES)) {
            final List<Object> result = new ArrayList<Object>();
            for (final DiagramFileStore file : diagramStore.getChildren()) {
                if (!ModelHelper.getAllItemsOfType((EObject) file.getContent(), ProcessPackage.Literals.ABSTRACT_PROCESS).isEmpty()) {
                    result.add(file.getContent());
                }
            }
            return result.toArray();
        } else if (element instanceof MainProcess) {
            final List<Object> result = new ArrayList<Object>();
            for (final EObject process : ModelHelper.getAllItemsOfType((EObject) element, ProcessPackage.Literals.ABSTRACT_PROCESS)) {
                if (!process.equals(element)) {
                    result.add(process);
                }
            }
            return result.toArray();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(final Object element) {
        final List<Object> rootElement = new ArrayList<Object>();
        if (element instanceof MainProcess && diagram == null) {
            diagram = (MainProcess) element;
            rootElement.add(element);
            rootElement.add(OTHER_PROCESSES);
        } else if (!(element instanceof MainProcess) && diagram == null) {
            rootElement.add(OTHER_PROCESSES);
        } else {
            return getChildren(element);
        }

        return (Object[]) element;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    @Override
    public Object getParent(final Object element) {
        if (element.equals(diagram) || element.equals(OTHER_PROCESSES)) {
            return null;
        } else if (element instanceof MainProcess) {
            return OTHER_PROCESSES;
        } else if (element instanceof AbstractProcess) {
            return ModelHelper.getMainProcess((EObject) element);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    @Override
    public boolean hasChildren(final Object element) {
        if (element.equals(OTHER_PROCESSES)) {
            return !diagramStore.isEmpty();
        } else if (element instanceof MainProcess) {
            return true;
        } else {
            return false;
        }
    }

}
