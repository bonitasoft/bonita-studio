/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.general;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Mickael Istria
 */
public class ActivateValidationPropertySectionContribution implements
        IExtensibleGridPropertySectionContribution {

    private Button checkbox;
    private MainProcess process;
    private TransactionalEditingDomain editingDomain;

    private EMFDataBindingContext context;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
     */
    public void createControl(Composite composite,
            TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection page) {
        composite
                .setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        composite.setLayout(new RowLayout());
        checkbox = widgetFactory.createButton(composite,
                Messages.GeneralSection_ActivateValidation, SWT.CHECK); //$NON-NLS-1$

        if (process != null) {
            checkbox.setSelection(process.isEnableValidation());
        }
        checkbox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (!checkbox.getSelection()) {
                    IFile target = process.eResource() != null ? WorkspaceSynchronizer
                            .getFile(process.eResource()) : null;
                    if (target != null) {
                        ProcessMarkerNavigationProvider.deleteMarkers(target);
                    }
                }
            }
        });
        context = new EMFDataBindingContext();
        createBinding(context);

    }

    protected void createBinding(EMFDataBindingContext context) {
        ISWTObservableValue observable = SWTObservables
                .observeSelection(checkbox);
        context.bindValue(observable, EMFEditObservables.observeValue(
                editingDomain, process,
                ProcessPackage.Literals.MAIN_PROCESS__ENABLE_VALIDATION));
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
        return " ";
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#refresh()
     */
    public void refresh() {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(EObject object) {
        this.process = (MainProcess) object;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof MainProcess;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(ISelection selection) {
        // NOTHING
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
    }

}
