/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.ui.part;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormMappingPart {

    private EMFDataBindingContext context;

    @Inject
    private EObjectAdaptableSelectionProvider selectionProvider;

    @PostConstruct
    public void createControls(final Composite parent) {
        context = new EMFDataBindingContext();
        final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

        final Composite mainComposite = toolkit.createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Form form = toolkit.createForm(mainComposite);
        form.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        form.setText("Form Mapping");

        final Composite body = form.getBody();
        body.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final Button pageDesignerRadio = toolkit.createButton(body, "Page designer", SWT.RADIO);
        final Button externalRadio = toolkit.createButton(body, "External URL", SWT.RADIO);

        final Composite stackedComposite = toolkit.createComposite(body);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final StackLayout stackLayout = new StackLayout();
        stackedComposite.setLayout(stackLayout);

        final Composite pageDesignerMappingComposite = createPageDesignerMappingComposite(toolkit, stackedComposite);

    }

    private Composite createPageDesignerMappingComposite(final FormToolkit toolkit, final Composite stackedComposite) {
        final Composite composite = toolkit.createComposite(stackedComposite);
        final Label label = toolkit.createLabel(composite, "Form id");
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
        context.bindValue(SWTObservables.observeText(label, SWT.Modify),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), formMappingObservable, ProcessPackage.Literals.FORM_MAPPING__TARGET_FORM));
        return composite;
    }

    private Composite createURLMappingComposite(final FormToolkit toolkit, final Composite stackedComposite) {
        final Composite composite = toolkit.createComposite(stackedComposite);
        final Label label = toolkit.createLabel(composite, "URL");
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        context.bindValue(SWTObservables.observeText(label, SWT.Modify), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.ELEMENT__NAME));
        return composite;
    }

    @Focus
    public void onFocus() {

    }

    @PreDestroy
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
    }

    @Inject
    public void selectionChanged(@Optional @Named(IServiceConstants.ACTIVE_SELECTION) final ISelection selection) {
        selectionProvider.setSelection(selection);
    }

}
