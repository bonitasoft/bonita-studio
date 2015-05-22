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
package org.bonitasoft.studio.designer.ui.property.section.control;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class FormMappingRadioGroup extends Composite implements BonitaPreferenceConstants {

    private final SelectObservableValue mappingTypeObservable;
    private final InternalMappingComposite pageDesignerMappingComposite;
    private final URLMappingComposite urlMappingComposite;
    private final CustomStackLayout stackLayout;
    private final Composite legacyComposite;
    private final Label info;

    public FormMappingRadioGroup(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final IEclipsePreferences preferenceStore,
            final RepositoryAccessor repositoryAccessor, final FormReferenceExpressionValidator formReferenceExpressionValidator,
            final CreateOrEditFormProposalListener createOrEditFormListener) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(10, 10, 10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Composite infoComposite = widgetFactory.createComposite(this);
        infoComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        infoComposite.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, true).hint(200, SWT.DEFAULT).create());
        info = widgetFactory.createLabel(infoComposite, "", SWT.WRAP);
        info.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Button pageDesignerRadio = widgetFactory.createButton(this, Messages.uiDesignerLabel, SWT.RADIO);
        pageDesignerRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final Button externalRadio = widgetFactory.createButton(this, Messages.externalURL, SWT.RADIO);
        externalRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final Button legacyRadio = widgetFactory.createButton(this, Messages.legacyForm, SWT.RADIO);
        legacyRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        mappingTypeObservable = new SelectObservableValue(FormMappingType.class);
        mappingTypeObservable.addOption(FormMappingType.INTERNAL, SWTObservables.observeSelection(pageDesignerRadio));
        mappingTypeObservable.addOption(FormMappingType.URL, SWTObservables.observeSelection(externalRadio));
        mappingTypeObservable.addOption(FormMappingType.LEGACY, SWTObservables.observeSelection(legacyRadio));

        final Composite stackedComposite = widgetFactory.createComposite(this);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());

        stackLayout = new CustomStackLayout(stackedComposite);
        stackedComposite.setLayout(stackLayout);

        legacyComposite = widgetFactory.createComposite(stackedComposite);
        pageDesignerMappingComposite = new InternalMappingComposite(stackedComposite, widgetFactory, preferenceStore, repositoryAccessor,
                formReferenceExpressionValidator, createOrEditFormListener);
        urlMappingComposite = new URLMappingComposite(stackedComposite, widgetFactory);
        widgetFactory.adapt(this);
    }

    private Control compositeFor(final FormMappingType mappingType) {
        switch (mappingType) {
            case URL:
                return urlMappingComposite;
            case INTERNAL:
                return pageDesignerMappingComposite;
            case LEGACY:
            default:
                return legacyComposite;
        }
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(PojoObservables.observeValue(stackLayout, "topControl"), mappingTypeObservable, neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(mappingTypeToCompositeConverter()).create());
        context.bindValue(mappingTypeObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        formMappingObservable,
                        ProcessPackage.Literals.FORM_MAPPING__TYPE));

        pageDesignerMappingComposite.doBindControl(context, formMappingObservable);
        urlMappingComposite.doBindControl(context, formMappingObservable);
        doBindInfo(context, formMappingObservable);
    }

    /**
     * @param context
     * @param formMappingObservable
     */
    protected void doBindInfo(final DataBindingContext context, final IObservableValue formMappingObservable) {
        final UpdateValueStrategy infoStrategy = new UpdateValueStrategy();
        infoStrategy.setConverter(createInfoConverter(formMappingObservable));
        context.bindValue(SWTObservables.observeText(info), mappingTypeObservable, null, infoStrategy);
    }

    /**
     * @param formMappingObservable
     * @return
     */
    protected Converter createInfoConverter(final IObservableValue formMappingObservable) {
        return new Converter(FormMappingType.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject != null) {
                    final FormMappingType type = (FormMappingType) fromObject;
                    final EObject context = ((FormMapping) formMappingObservable.getValue()).eContainer();
                    final EReference formMappingFeature = ((FormMapping) formMappingObservable.getValue()).eContainmentFeature();
                    switch (type) {
                        case INTERNAL:
                            return getUIDesignerMessage(context, formMappingFeature);
                        case URL:
                            return getURLMessage(context, formMappingFeature);
                        case LEGACY:
                            return getLegacyMessage(context, formMappingFeature);
                    }
                }
                return null;
            }

        };
    }

    private String getUIDesignerMessage(final EObject context, final EReference formMappingFeature) {
        if (formMappingFeature.equals(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING)) {
            return Messages.overviewUIDesignerInfo;
        }
        return context instanceof Task ? Messages.stepUIDesignerInfo : Messages.processUIDesignerInfo;
    }

    private String getURLMessage(final EObject context, final EReference formMappingFeature) {
        if (formMappingFeature.equals(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING)) {
            return Messages.overviewURLInfo;
        }
        return context instanceof Task ? Messages.stepURLInfo : Messages.processURLInfo;
    }

    private String getLegacyMessage(final EObject context, final EReference formMappingFeature) {
        if (formMappingFeature.equals(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING)) {
            return Messages.overviewLegacyInfo;
        }
        return context instanceof Task ? Messages.stepLegacyInfo : Messages.processLegacyInfo;
    }

    private IConverter mappingTypeToCompositeConverter() {
        return new Converter(FormMappingType.class, Composite.class) {

            @Override
            public Object convert(final Object mappingType) {
                return compositeFor((FormMappingType) mappingType);
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    @Override
    public void dispose() {
        if (pageDesignerMappingComposite != null) {
            pageDesignerMappingComposite.dispose();
        }
        if (urlMappingComposite != null) {
            urlMappingComposite.dispose();
        }
        super.dispose();
    }
}
