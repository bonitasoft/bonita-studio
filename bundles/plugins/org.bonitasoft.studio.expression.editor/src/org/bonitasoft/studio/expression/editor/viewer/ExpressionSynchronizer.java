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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class ExpressionSynchronizer {

    private final Expression source;
    private final Expression target;
    private final EditingDomain editingDomain;

    public ExpressionSynchronizer(final EditingDomain editingDomain, final Expression source, final Expression target) {
        Assert.isNotNull(target);
        Assert.isNotNull(source);
        this.source = source;
        this.target = target;
        this.editingDomain = editingDomain;
    }

    public synchronized void synchronize(final CompoundCommand cc) {

        if (editingDomain != null) {
            final AbstractCommand cmd = new AbstractCommand() {

                @Override
                public void redo() {

                }

                @Override
                public boolean canUndo() {
                    return false;
                }

                @Override
                public boolean canExecute() {
                    return true;
                }

                @Override
                public void execute() {
                    for (final EStructuralFeature feature : source.eClass().getEAllStructuralFeatures()) {
                        target.eSet(feature, source.eGet(feature));
                    }
                }
            };
            cc.append(cmd);
            editingDomain.getCommandStack().execute(cc);
        } else {
            for (final EStructuralFeature feature : source.eClass().getEAllStructuralFeatures()) {
                target.eSet(feature, source.eGet(feature));
            }
        }
    }

}
