
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Romain Bioteau
 *
 */
public class WizardPageOperationsComposite extends OperationsComposite {

	public WizardPageOperationsComposite(
			TabbedPropertySheetPage tabbedPropertySheetPage,
			Composite mainComposite, ViewerFilter actionExpressionFilter,
			ViewerFilter storageExpressionFilter) {
		super(tabbedPropertySheetPage, mainComposite, actionExpressionFilter,
				storageExpressionFilter);
	}
	
	public WizardPageOperationsComposite(TabbedPropertySheetPage tabbedPropertySheetPage,
			Composite mainComposite, ViewerFilter actionExpressionFilter,
			ViewerFilter storageExpressionFilter,boolean isPageFlowContext) {
		super(tabbedPropertySheetPage, mainComposite, actionExpressionFilter,
				storageExpressionFilter,isPageFlowContext);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.operation.OperationsComposite#refresh()
	 */
	@Override
	public void refresh() {
		Composite shell = mainComposite.getShell();
		Point compositesize = mainComposite.getSize();
		Point newcompositesize = mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		Point defaultSize = shell.getSize();
		Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		if(compositesize.y < newcompositesize.y){
			shell.setSize(defaultSize.x, size.y);
		}
		shell.layout(true,true);

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
