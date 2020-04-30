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
package org.bonitasoft.studio.expression.editor.provider;

import java.util.List;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.DialogTray;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Romain Bioteau
 * 
 */
public interface IExpressionEditor extends IBonitaVariableContext {

    Control createExpressionEditor(Composite parent, EMFDataBindingContext dataBindingContext);

    Control createExpressionEditor(Composite contentComposite, EMFDataBindingContext ctx, boolean isPassword);

    void bindExpression(EMFDataBindingContext dataBindingContext, EObject context, Expression inputExpression, ViewerFilter[] viewerTypeFilters,
            ExpressionViewer viewer);

    boolean canFinish();

    void addListener(Listener listener);

    void dispose();

    void okPressed();

    List<Listener> getListeners();

    boolean provideDialogTray();

    DialogTray createDialogTray();

    Control getTextControl();

    IObservable getContentObservable();

}
