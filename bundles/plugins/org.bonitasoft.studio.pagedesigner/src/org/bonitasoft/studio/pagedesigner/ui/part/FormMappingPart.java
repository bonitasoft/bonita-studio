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

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.widget.ContentAssistText;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormMappingPart {

    private EMFDataBindingContext context;

    @Inject
    private PageFlowAdaptableSelectionProvider selectionProvider;

    @PostConstruct
    public void createControls(final Composite parent) {
        context = new EMFDataBindingContext();
        final FormToolkit toolkit = new FormToolkit(parent.getDisplay());

        final Composite mainComposite = toolkit.createComposite(parent);
        final StackLayout mainStackLayout = new StackLayout();
        mainComposite.setLayout(mainStackLayout);

        final Composite noContentComposite = toolkit.createComposite(mainComposite);
        noContentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 15).create());

        toolkit.createLabel(noContentComposite, "Form mapping is only available on Pool and Human task elements.");

        final Composite contentComposite = toolkit.createComposite(mainComposite);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        final IObservableValue eObjectSelectionObservable = ViewersObservables.observeSingleSelection(selectionProvider);
        eObjectSelectionObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Object eObject = event.diff.getNewValue();
                if (eObject != null) {
                    mainStackLayout.topControl = contentComposite;
                } else {
                    mainStackLayout.topControl = noContentComposite;
                }
                mainComposite.layout();
            }
        });
        final Object value = eObjectSelectionObservable.getValue();
        mainStackLayout.topControl = value != null ? contentComposite : noContentComposite;

        final Form form = toolkit.createForm(contentComposite);
        toolkit.decorateFormHeading(form);
        form.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        form.setText("Form Mapping");

        final Composite body = form.getBody();
        body.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 10, 10).create());

        final Button pageDesignerRadio = toolkit.createButton(body, "Page designer", SWT.RADIO);
        pageDesignerRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());
        final Button externalRadio = toolkit.createButton(body, "External URL", SWT.RADIO);
        externalRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final SelectObservableValue externalObservable = new SelectObservableValue(Boolean.class);
        externalObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(pageDesignerRadio));
        externalObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(externalRadio));

        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);

        context.bindValue(externalObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        formMappingObservable,
                        ProcessPackage.Literals.FORM_MAPPING__EXTERNAL));

        final Composite stackedComposite = toolkit.createComposite(body);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        final StackLayout stackLayout = new StackLayout();
        stackedComposite.setLayout(stackLayout);

        final Composite pageDesignerMappingComposite = createPageDesignerMappingComposite(toolkit, stackedComposite);
        final Composite urlMappingComposite = createURLMappingComposite(toolkit, stackedComposite);
        externalObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Boolean newValue = (Boolean) event.diff.getNewValue();
                if (newValue) {
                    stackLayout.topControl = urlMappingComposite;
                } else {
                    stackLayout.topControl = pageDesignerMappingComposite;
                }
                stackedComposite.layout();
            }
        });
        final Object externalValue = externalObservable.getValue();
        stackLayout.topControl = externalValue != null && (Boolean) externalValue ? urlMappingComposite : pageDesignerMappingComposite;
    }

    private Composite createPageDesignerMappingComposite(final FormToolkit toolkit, final Composite stackedComposite) {
        final Composite composite = toolkit.createComposite(stackedComposite);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 0, 10, 0).create());
        final Label label = toolkit.createLabel(composite, "Target form");
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        final ContentAssistText targetFormText = new ContentAssistText(composite, new ExpressionLabelProvider(), SWT.BORDER);
        targetFormText.setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).create());
        targetFormText.setProposalEnabled(true);
        targetFormText.getAutocompletion().setFilteredExpressionType(Arrays.asList(ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.PARAMETER_TYPE, ExpressionConstants.DOCUMENT_TYPE, ExpressionConstants.DOCUMENT_REF_TYPE));
        targetFormText.getAutocompletion().setCreateShortcutZone(true);
        targetFormText.getAutocompletion().setProposals(
                new Expression[] { ExpressionHelper.createConstantExpression("form-name", "form-id1", String.class.getName()) });

        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);
        context.bindValue(SWTObservables.observeText(targetFormText.getTextControl(), SWT.Modify),
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(), formMappingObservable, ProcessPackage.Literals.FORM_MAPPING__TARGET_FORM));
        return composite;
    }

    private Composite createURLMappingComposite(final FormToolkit toolkit, final Composite stackedComposite) {
        final Composite composite = toolkit.createComposite(stackedComposite);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 0, 10, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        final Label label = toolkit.createLabel(composite, "URL");
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final Text urlText = toolkit.createText(composite, "", SWT.BORDER);
        urlText.setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).create());

        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING);

        context.bindValue(SWTObservables.observeText(urlText, SWT.Modify), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                formMappingObservable,
                ProcessPackage.Literals.FORM_MAPPING__URL));
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
