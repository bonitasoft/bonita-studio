/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.properties.sections.lane;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Mickael Istria
 *
 */
public class LaneDescriptionSectionContribution implements IExtensibleGridPropertySectionContribution {

	private Text text;
	private Element element;
	private TransactionalEditingDomain editingDomain;

	
	public LaneDescriptionSectionContribution(
			TabbedPropertySheetPage tabbedPropertySheetPage) {
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
	 */
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection page) {

		composite.setLayout(new RowLayout());
		text = widgetFactory.createText(composite, "", SWT.BORDER | SWT.MULTI);
		RowData rd = new RowData();
		rd.width = 300 ;
		rd.height = 60 ;
		text.setLayoutData(rd);
		if (element != null && element.getDocumentation() != null) {
			text.setText(element.getDocumentation());
		}
		
		text.addModifyListener(new ModifyListener() {
			
			public void modifyText(ModifyEvent e) {
				editingDomain.getCommandStack().execute(new SetCommand(editingDomain, element, ProcessPackage.Literals.ELEMENT__DOCUMENTATION, text.getText()));
			}
		});

	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.GeneralSection_Description;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#refresh()
	 */
	public void refresh() {
		
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
	 */
	public void setEObject(EObject object) {
		this.element = (Element)object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof Element;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		// NOTHING
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
