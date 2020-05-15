/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.ui.property.section;

import javax.inject.Inject;

import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.designer.ui.property.section.control.CreateOrEditFormProposalListener;
import org.bonitasoft.studio.designer.ui.property.section.control.FormMappingRadioGroup;
import org.bonitasoft.studio.designer.ui.property.section.control.FormReferenceExpressionValidator;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */

public class EntryFormMappingPropertySection extends AbstractBonitaDescriptionSection {

    private EMFDataBindingContext context;

    @Inject
    private PageFlowAdaptableSelectionProvider selectionProvider;

    @Inject
    @Preference(nodePath = "org.bonitasoft.studio.preferences")
    private IEclipsePreferences preferenceStore;

    @Inject
    private RepositoryAccessor repositoryAccessor;

    @Inject
    private CreateOrEditFormProposalListener createOrEditListener;

    private FormMappingRadioGroup formMappingRadioGroup;

    @Override
    protected void createContent(final Composite parent) {
        context = new EMFDataBindingContext();
        final FormReferenceExpressionValidator formReferenceExpressionValidator = new FormReferenceExpressionValidator(repositoryAccessor,
                getFormMappingFeature());
        formMappingRadioGroup = new FormMappingRadioGroup(parent, getWidgetFactory(), preferenceStore,
                repositoryAccessor, formReferenceExpressionValidator, createOrEditListener);
        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                getFormMappingFeature());
        formMappingRadioGroup.doBindControl(context, formMappingObservable);

    }

    protected EReference getFormMappingFeature() {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING;
    }

    @Override
    public void dispose() {
        if (formMappingRadioGroup != null) {
            formMappingRadioGroup.dispose();
        }
        if (context != null) {
            context.dispose();
        }
        super.dispose();
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
    }
    
    @Override
    public String getSectionDescription() {
        return Messages.entryFormMappingDescription;
    }

}
