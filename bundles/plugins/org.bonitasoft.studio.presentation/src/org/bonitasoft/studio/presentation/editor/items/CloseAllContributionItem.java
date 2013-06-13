/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.bonitasoft.studio.presentation.editor.items;

import org.bonitasoft.studio.presentation.PresentationImages;
import org.bonitasoft.studio.presentation.editor.BonitaEditorStackPresentation;
import org.bonitasoft.studio.presentation.i18n.Messages;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * @since 3.0
 */
public class CloseAllContributionItem extends ContributionItem {
	private BonitaEditorStackPresentation presentation; 
	
	private SelectionAdapter selectionListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			presentation.close(presentation.getParts());
		}
	};
	
	public CloseAllContributionItem(BonitaEditorStackPresentation presentation) {
		this.presentation = presentation;
	}

    public void dispose() {
    	super.dispose();
        presentation = null;
        selectionListener = null;
    }
    
    public void fill(Menu menu, int index) {
    	if (presentation.getParts().length > 1) {
			MenuItem item = new MenuItem(menu, SWT.NONE, index);
			item.setText(Messages.closeAllLabel);
			item.setImage(PresentationImages.getImage(PresentationImages.CLOSE_ALL));
			item.addSelectionListener(selectionListener);
    	}
    }
    
    public boolean isDynamic() {
    	// Returning true here ensures that the fill(...) method will be called whenever
    	// the context menu opens
        return true;
    }
}
