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

import java.util.Optional;

import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Lane;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.databinding.validator.EAttributeValidatorFactory;
import org.bonitasoft.studio.common.ui.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.ui.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.ui.widgets.GTKStyleHandler;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class VersionGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    private AbstractProcess process;
    private TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext context;

    @Override
    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection page) {
        context = new EMFDataBindingContext();

        Text text = new Text(composite, GTKStyleHandler.removeBorderFlag(SWT.BORDER));
        text.setLayoutData(GridDataFactory.swtDefaults().hint(160, SWT.DEFAULT).grab(false, false).create());
        if (!GTKStyleHandler.isGTK3()) {
            widgetFactory.adapt(text, true, true);
        }
        text.setEnabled(false);

        var observable = WidgetProperties.text(SWT.Modify).observe(text);
        context.bindValue(observable,
                EMFEditObservables.observeValue(editingDomain, process,
                        ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION));

        // add validation strategy
        final MultiValidator validationStatusProvider = versionValidationStatusProvider(observable);
        context.addValidationStatusProvider(validationStatusProvider);
    }

    protected MultiValidator versionValidationStatusProvider(final IObservableValue versionTextObservable) {
        // rely on validator for version attribute contributed by the validation plugin
        Optional<EAttributeValidatorFactory> validatorFactory = EAttributeValidatorFactory
                .findForAttribute(ProcessPackage.Literals.ABSTRACT_PROCESS__VERSION);
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                Optional<IValidator> validator = validatorFactory.map(f -> f.create(process.eClass()));
                return validator.map(v -> v.validate(versionTextObservable.getValue())).orElse(Status.OK_STATUS);
            }
        };
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
        if (object instanceof Lane) {
            process = (AbstractProcess) ((Lane) object).eContainer();
        } else {
            process = (AbstractProcess) object;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.general.IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.
     * TransactionalEditingDomain)
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
        if (context != null) {
            context.dispose();
        }
    }

}
