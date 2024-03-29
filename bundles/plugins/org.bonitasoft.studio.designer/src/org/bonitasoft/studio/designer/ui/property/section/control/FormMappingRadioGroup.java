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

import static org.bonitasoft.studio.common.ui.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.ui.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.bpm.model.process.FormMappingType;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.ui.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.ui.widgets.CustomStackLayout;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class FormMappingRadioGroup implements BonitaPreferenceConstants {

    private final SelectObservableValue mappingTypeObservable;
    private final InternalMappingComposite pageDesignerMappingComposite;
    private final URLMappingComposite urlMappingComposite;
    private final CustomStackLayout stackLayout;
    private final InfoMessageComposite noneComposite;
    private Composite mainComposite;

    public FormMappingRadioGroup(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory,
            final IEclipsePreferences preferenceStore,
            final RepositoryAccessor repositoryAccessor,
            final FormReferenceExpressionValidator formReferenceExpressionValidator,
            final CreateOrEditFormProposalListener createOrEditFormListener) {
        this.mainComposite = widgetFactory.createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(4).extendedMargins(10, 10, 10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Button uiDesignerRadio = widgetFactory.createButton(mainComposite, Messages.uiDesignerLabel, SWT.RADIO);
        uiDesignerRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final Button externalRadio = widgetFactory.createButton(mainComposite, Messages.externalURL, SWT.RADIO);
        externalRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        final Button noneRadio = widgetFactory.createButton(mainComposite, Messages.noForm, SWT.RADIO);
        noneRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        mappingTypeObservable = new SelectObservableValue(FormMappingType.class);
        mappingTypeObservable.addOption(FormMappingType.NONE, WidgetProperties.buttonSelection().observe(noneRadio));
        mappingTypeObservable.addOption(FormMappingType.INTERNAL,
                WidgetProperties.buttonSelection().observe(uiDesignerRadio));
        mappingTypeObservable.addOption(FormMappingType.URL, WidgetProperties.buttonSelection().observe(externalRadio));

        final Composite stackedComposite = widgetFactory.createComposite(mainComposite);
        stackedComposite.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).span(4, 1).create());

        stackLayout = new CustomStackLayout(stackedComposite);
        stackedComposite.setLayout(stackLayout);

        noneComposite = new InfoMessageComposite(stackedComposite, widgetFactory, IStatus.INFO);
        pageDesignerMappingComposite = new InternalMappingComposite(stackedComposite, widgetFactory,
                repositoryAccessor, formReferenceExpressionValidator, createOrEditFormListener);
        urlMappingComposite = new URLMappingComposite(stackedComposite, widgetFactory);
    }

    private Control compositeFor(final FormMappingType mappingType) {
        switch (mappingType) {
            case URL:
                return urlMappingComposite;
            case INTERNAL:
                return pageDesignerMappingComposite;
            case NONE:
            default:
                return noneComposite;
        }
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(PojoProperties.value("topControl").observe(stackLayout), mappingTypeObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(mappingTypeToCompositeConverter()).create());
        context.bindValue(mappingTypeObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        formMappingObservable,
                        ProcessPackage.Literals.FORM_MAPPING__TYPE));

        pageDesignerMappingComposite.doBindControl(context, formMappingObservable);
        urlMappingComposite.doBindControl(context, formMappingObservable);
        noneComposite.doBindControl(context, formMappingObservable, FormMappingType.NONE);
    }

    private IConverter mappingTypeToCompositeConverter() {
        return new Converter(FormMappingType.class, Composite.class) {

            @Override
            public Object convert(final Object mappingType) {
                return mappingType != null ? compositeFor((FormMappingType) mappingType) : noneComposite;
            }
        };
    }
}
