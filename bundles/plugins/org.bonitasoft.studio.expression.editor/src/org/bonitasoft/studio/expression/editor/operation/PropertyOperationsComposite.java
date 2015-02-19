
/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.operation;

import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Romain Bioteau
 *
 */
public class PropertyOperationsComposite extends OperationsComposite {

	public PropertyOperationsComposite(
			TabbedPropertySheetPage tabbedPropertySheetPage,
			Composite mainComposite, ViewerFilter actionExpressionFilter,
			ViewerFilter storageExpressionFilter) {
		super(tabbedPropertySheetPage, mainComposite, actionExpressionFilter,
				storageExpressionFilter);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.operation.OperationsComposite#refresh()
	 */
	@Override
	public void refresh() {
		Composite shell = parent.getParent().getParent().getParent().getParent();
		shell.layout(true,true);
		parent.layout(true,true);
		layout(true,true);
		if (tabbedPropertySheetPage != null) {
			tabbedPropertySheetPage.resizeScrolledComposite();
		}
	}



	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
	}

}
