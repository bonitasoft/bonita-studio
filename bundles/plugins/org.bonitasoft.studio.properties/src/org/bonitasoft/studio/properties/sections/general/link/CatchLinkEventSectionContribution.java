/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

package org.bonitasoft.studio.properties.sections.general.link;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class CatchLinkEventSectionContribution implements IExtensibleGridPropertySectionContribution {

    private CatchLinkEvent catchLinkElement;
    private ListViewer list;

    @Override
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
	    final ExtensibleGridPropertySection extensibleGridPropertySection) {
	list = new ListViewer(composite, SWT.BORDER | SWT.V_SCROLL);
	list.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(200, 55).grab(true, false).create());
	list.setLabelProvider(new LinkLabelProvider());
	list.setContentProvider(ArrayContentProvider.getInstance());

	list.setInput(catchLinkElement.getFrom());
    }

    @Override
    public void dispose() {
    }

    @Override
    public String getLabel() {
	return Messages.fromLinksLabel;
    }

    @Override
    public boolean isRelevantFor(final EObject eObject) {
	return eObject instanceof CatchLinkEvent;
    }

    @Override
    public void refresh() {
    }

    @Override
    public void setEObject(final EObject object) {
	catchLinkElement = (CatchLinkEvent) object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.
     * transaction.TransactionalEditingDomain )
     */
    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.
     * viewers.ISelection)
     */
    @Override
    public void setSelection(final ISelection selection) {
    }

}
