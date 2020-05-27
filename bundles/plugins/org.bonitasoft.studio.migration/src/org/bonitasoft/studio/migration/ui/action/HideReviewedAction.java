/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.ui.action;

import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.model.report.Change;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * @author Romain Bioteau
 *
 */
public class HideReviewedAction extends Action {

	private StructuredViewer viewer;
	private ViewerFilter reviewFilter = new ViewerFilter() {

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if(element instanceof Change){
				return !((Change) element).isReviewed();
			}
			return false;
		}
	};
	public HideReviewedAction(){
		super(Messages.hideReviewedAction,Action.AS_CHECK_BOX);
		setToolTipText(Messages.hideReviewedAction);
		setChecked(false);
	}

	public void setViewer(StructuredViewer viewer){
		this.viewer = viewer;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.ui.actions.AbstractToggleLinkingAction#run()
	 */
	@Override
	public void run() {
		if(viewer != null){
			if(isChecked()){
				viewer.addFilter(reviewFilter);
			}else{
				viewer.removeFilter(reviewFilter);
			}
			viewer.refresh();
		}
	}


}
