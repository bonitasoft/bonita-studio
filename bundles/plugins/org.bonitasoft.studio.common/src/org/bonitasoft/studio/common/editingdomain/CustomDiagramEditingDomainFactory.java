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
package org.bonitasoft.studio.common.editingdomain;

import java.util.Map;

import org.bonitasoft.studio.common.editingdomain.transaction.AlwaysValidTransaction;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;

public class CustomDiagramEditingDomainFactory extends DiagramEditingDomainFactory {

    private static CustomDiagramEditingDomainFactory instance = new CustomDiagramEditingDomainFactory();

    private final Map<Object, Object> undoRedoOptions = new java.util.HashMap<Object, Object>(
            AlwaysValidTransaction.DEFAULT_UNDO_REDO_OPTIONS);

    public static WorkspaceEditingDomainFactory getInstance() {
        return instance;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory#createEditingDomain(org.eclipse.emf.ecore.resource.ResourceSet,
     * org.eclipse.core.commands.operations.IOperationHistory)
     */
    @Override
    public TransactionalEditingDomain createEditingDomain(final ResourceSet rset, final IOperationHistory history) {
        final WorkspaceCommandStackImpl stack = new CustomWorkspaceCommandStack(history);

        final TransactionalEditingDomain result = new DiagramEditingDomain(
                new ComposedAdapterFactory(
                        ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
                stack,
                rset) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl#startTransaction(boolean, java.util.Map)
             */
            @Override
            public InternalTransaction startTransaction(final boolean readOnly, final Map<?, ?> options) throws InterruptedException {
                final InternalTransaction result = new AlwaysValidTransaction(this, readOnly, options);
                result.start();
                return result;
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl#getUndoRedoOptions()
             */
            @Override
            public Map<Object, Object> getUndoRedoOptions() {
                return undoRedoOptions;
            }
        };

        mapResourceSet(result);
        configure(result);
        return result;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory#createEditingDomain(org.eclipse.core.commands.operations.IOperationHistory)
     */
    @Override
    public TransactionalEditingDomain createEditingDomain(final IOperationHistory history) {
        final WorkspaceCommandStackImpl stack = new CustomWorkspaceCommandStack(history);
        final TransactionalEditingDomain result = new DiagramEditingDomain(
                new ComposedAdapterFactory(
                        ComposedAdapterFactory.Descriptor.Registry.INSTANCE),
                stack) {

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl#startTransaction(boolean, java.util.Map)
             */
            @Override
            public InternalTransaction startTransaction(final boolean readOnly, final Map<?, ?> options) throws InterruptedException {
                final InternalTransaction result = new AlwaysValidTransaction(this, readOnly, options);
                result.start();
                return result;
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl#getUndoRedoOptions()
             */
            @Override
            public Map<Object, Object> getUndoRedoOptions() {
                return undoRedoOptions;
            }
        };

        mapResourceSet(result);
        configure(result);
        return result;
    }

}
