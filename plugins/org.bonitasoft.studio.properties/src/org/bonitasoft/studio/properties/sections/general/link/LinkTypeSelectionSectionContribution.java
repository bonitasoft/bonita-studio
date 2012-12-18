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
package org.bonitasoft.studio.properties.sections.general.link;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.diagram.custom.BonitaNodesElementTypeResolver;
import org.bonitasoft.studio.model.process.LinkEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class LinkTypeSelectionSectionContribution implements IExtensibleGridPropertySectionContribution {

	public static final String CATCH_LINK = Messages.catchLink;
	public static final String THROW_LINK = Messages.throwLink;

	private Combo combo;
	private LinkEvent link;
	private GraphicalEditPart node;
	private TabbedPropertySheetPage tabbedPropertySheetPage;


	public LinkTypeSelectionSectionContribution(TabbedPropertySheetPage tabbedPropertySheetPage) {
		this.tabbedPropertySheetPage = tabbedPropertySheetPage ;
	}

	public void refresh() {
		if (link != null && combo != null) {
			if (link.eClass().equals(ProcessPackage.Literals.CATCH_LINK_EVENT)) {
				combo.setText(CATCH_LINK);
			} else if (link.eClass().equals(ProcessPackage.Literals.THROW_LINK_EVENT)) {
				combo.setText(THROW_LINK);
			}
		}
	}

	/**
	 * @param aTabbedPropertySheetPage
	 * @param parent
	 */
	private void createLinkSelectionCombo(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
		combo = new Combo(parent, SWT.READ_ONLY);
		combo.add(CATCH_LINK);
		combo.add(THROW_LINK);

		combo.addListener(SWT.Modify, new Listener() {


			public void handleEvent(Event event) {
				if(toBeConverted()){
					EClass targetEClass = getTargetEClass();
					IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
					if(editor instanceof ProcessDiagramEditor){
						node = (GraphicalEditPart) ((IStructuredSelection)((ProcessDiagramEditor)editor).getDiagramGraphicalViewer().getSelection()).getFirstElement() ;
						GraphicalEditPart newEP = GMFTools.convert(targetEClass, node, new BonitaNodesElementTypeResolver(),GMFTools.PROCESS_DIAGRAM);
						setEObject(newEP.resolveSemanticElement());
						tabbedPropertySheetPage.selectionChanged(editor, ((IStructuredSelection)((ProcessDiagramEditor)editor).getDiagramGraphicalViewer().getSelection()));
					}
				}

			}

			/**
			 * @return
			 */
			private EClass getTargetEClass() {

				if (combo.getText().equals(CATCH_LINK)) {
					return ProcessPackage.Literals.CATCH_LINK_EVENT;
				} else if (combo.getText().equals(THROW_LINK)) {
					return ProcessPackage.Literals.THROW_LINK_EVENT;
				}


				return ProcessPackage.Literals.CATCH_LINK_EVENT;
			}

			/**
			 * @return
			 */
			private boolean toBeConverted() {
				return ! link.eClass().equals(getTargetEClass());
			}

		});
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
	 */
	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection page) {
		composite.setLayout(new RowLayout());
		createLinkSelectionCombo(composite, widgetFactory);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#getLabel()
	 */
	public String getLabel() {
		return Messages.linkType;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof LinkEvent;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
	 */
	public void setEObject(EObject object) {
		this.link = (LinkEvent)object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
	 */
	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
	 */
	public void setSelection(ISelection selection) {
		this.node = (GraphicalEditPart) ((IStructuredSelection)selection).getFirstElement();
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
	 */
	public void dispose() {
	}

}