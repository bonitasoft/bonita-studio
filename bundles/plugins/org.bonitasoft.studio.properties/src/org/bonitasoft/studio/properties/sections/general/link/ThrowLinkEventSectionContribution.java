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

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.bpm.model.process.CatchLinkEvent;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.ui.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class ThrowLinkEventSectionContribution implements
        IExtensibleGridPropertySectionContribution {

    private ComboViewer combo;
    private ThrowLinkEvent throwLinkElement;
    private DataBindingContext context;
    private TransactionalEditingDomain editingDomain;

    @Override
    public void createControl(final Composite composite,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection extensibleGridPropertySection) {
        combo = new ComboViewer(composite, SWT.READ_ONLY);
        combo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(200, SWT.DEFAULT).create());
        combo.setLabelProvider(new LinkLabelProvider());
        combo.setContentProvider(ArrayContentProvider.getInstance());

        final List<CatchLinkEvent> events = new ArrayList<CatchLinkEvent>();
        ModelHelper.findAllCatchLinks(ModelHelper.getParentProcess(throwLinkElement), events);
        combo.setInput(events);
        if (throwLinkElement != null && throwLinkElement.getTo() != null) {
            combo.getCombo().setText(throwLinkElement.getTo().getName());
        }
        if (context != null) {
            context.dispose();
        }
        context = new DataBindingContext();
        final IViewerObservableValue comboViewerObservable = ViewerProperties.singleSelection().observe(combo);
        final IObservableValue toObservableValue = EMFEditObservables.observeValue(editingDomain, throwLinkElement,
                ProcessPackage.Literals.THROW_LINK_EVENT__TO);
        context.bindValue(comboViewerObservable, toObservableValue);

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.gotoLabel;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof ThrowLinkEvent;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        throwLinkElement = (ThrowLinkEvent) object;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain
     * )
     */
    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(final ISelection selection) {
    }

}
