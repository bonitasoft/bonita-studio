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
package org.bonitasoft.studio.properties.sections.general;

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.UTF8InputValidator;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


public class VersionGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    private AbstractProcess process;
    private TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext context;


    @Override
    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection page) {
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        context = new EMFDataBindingContext();

        UpdateValueStrategy versionUpdate = new UpdateValueStrategy();
        versionUpdate.setAfterGetValidator(new EmptyInputValidator(Messages.GeneralSection_Version));
        versionUpdate.setBeforeSetValidator(new UTF8InputValidator(Messages.GeneralSection_Version));

        new TextWidget.Builder()
                .widthHint(100)
                .inContext(context)
                .withDelay(400)
                .bindTo(EMFEditObservables.observeValue(editingDomain, process,
                        ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION))
                .withTargetToModelStrategy(versionUpdate)
                .readOnly(process instanceof MainProcess || process instanceof Pool)
                .adapt(widgetFactory)
                .createIn(composite);
    }

    @Override
    public String getLabel() {
        return Messages.GeneralSection_Version;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setEObject(EObject object) {
        if(object instanceof Lane){
            process = (AbstractProcess) ((Lane) object).eContainer();
        }else{
            process = (AbstractProcess) object;
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    @Override
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof AbstractProcess || eObject instanceof Lane;
    }

    @Override
    public void setSelection(ISelection selection) {
        // NOTHING
    }

    @Override
    public void dispose() {
        if(context != null){
            context.dispose() ;
        }
    }

}
