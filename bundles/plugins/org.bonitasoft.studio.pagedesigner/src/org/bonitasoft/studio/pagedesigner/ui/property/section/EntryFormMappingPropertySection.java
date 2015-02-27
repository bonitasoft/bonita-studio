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
package org.bonitasoft.studio.pagedesigner.ui.property.section;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EReference;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class EntryFormMappingPropertySection extends AbstractBonitaDescriptionSection {

    private EMFDataBindingContext context;
    private final EObjectAdaptableSelectionProvider selectionProvider;

    public EntryFormMappingPropertySection() {
        selectionProvider = new PageFlowAdaptableSelectionProvider();
    }

    public EntryFormMappingPropertySection(final EObjectAdaptableSelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
    }

    @Override
    public void createControls(final Composite parent, final org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage aTabbedPropertySheetPage) {
        context = new EMFDataBindingContext();
        final TabbedPropertySheetWidgetFactory toolkit = aTabbedPropertySheetPage.getWidgetFactory();

        final Composite contentComposite = toolkit.createComposite(parent);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 10, 10).create());

        final Button pageDesignerRadio = toolkit.createButton(contentComposite, "Page designer", SWT.RADIO);
        pageDesignerRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());
        final Button externalRadio = toolkit.createButton(contentComposite, "External URL", SWT.RADIO);
        externalRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final SelectObservableValue externalObservable = new SelectObservableValue(Boolean.class);
        externalObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(pageDesignerRadio));
        externalObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(externalRadio));

        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                getFormMappingFeature());

        context.bindValue(externalObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        formMappingObservable,
                        ProcessPackage.Literals.FORM_MAPPING__EXTERNAL));

        final Composite stackedComposite = toolkit.createComposite(contentComposite);
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

        final ExpressionViewer targetFormExpressionViewer = new ExpressionViewer(composite, SWT.BORDER, (TabbedPropertySheetWidgetFactory) toolkit);
        targetFormExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).create());
        targetFormExpressionViewer.setExpressionProposalLableProvider(new FormReferenceProposalLabelProvider());
        targetFormExpressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.FORM_REFERENCE_TYPE }));

        final IObservableValue formMappingObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                getFormMappingFeature());

        context.bindValue(ViewersObservables.observeInput(targetFormExpressionViewer), formMappingObservable);
        context.bindValue(ViewersObservables.observeSingleSelection(targetFormExpressionViewer),
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
                getFormMappingFeature());

        context.bindValue(SWTObservables.observeText(urlText, SWT.Modify), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                formMappingObservable,
                ProcessPackage.Literals.FORM_MAPPING__URL));
        return composite;
    }

    protected EReference getFormMappingFeature() {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING;
    }

    @Override
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return null;
    }

}
