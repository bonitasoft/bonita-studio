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
package org.bonitasoft.studio.pagedesigner.ui.property.section.control;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class FormMappingRadioGroup extends Composite implements BonitaPreferenceConstants {

    private final SelectObservableValue externalObservable;
    private final PageDesignerMappingComposite pageDesignerMappingComposite;
    private final URLMappingComposite urlMappingComposite;
    private final CustomStackLayout stackLayout;

    public FormMappingRadioGroup(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final IEclipsePreferences preferenceStore) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).extendedMargins(10, 10, 10, 10).create());

        final Button pageDesignerRadio = widgetFactory.createButton(this, Messages.pageDesigner, SWT.RADIO);
        pageDesignerRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());
        final Button externalRadio = widgetFactory.createButton(this, Messages.externalURL, SWT.RADIO);
        externalRadio.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        externalObservable = new SelectObservableValue(Boolean.class);
        externalObservable.addOption(Boolean.FALSE, SWTObservables.observeSelection(pageDesignerRadio));
        externalObservable.addOption(Boolean.TRUE, SWTObservables.observeSelection(externalRadio));

        final Composite stackedComposite = widgetFactory.createComposite(this);
        stackedComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

        stackLayout = new CustomStackLayout(stackedComposite);
        stackedComposite.setLayout(stackLayout);

        pageDesignerMappingComposite = new PageDesignerMappingComposite(stackedComposite, widgetFactory, preferenceStore);
        urlMappingComposite = new URLMappingComposite(stackedComposite, widgetFactory);

        final Object externalValue = externalObservable.getValue();
        stackLayout.setTopControl(externalValue != null && (Boolean) externalValue ? urlMappingComposite : pageDesignerMappingComposite);

        widgetFactory.adapt(this);
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(PojoObservables.observeValue(stackLayout, "topControl"), externalObservable, neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(booleanToCompositeConverter()).create());
        context.bindValue(externalObservable,
                CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                        formMappingObservable,
                        ProcessPackage.Literals.FORM_MAPPING__EXTERNAL));
        pageDesignerMappingComposite.doBindControl(context, formMappingObservable);
        urlMappingComposite.doBindControl(context, formMappingObservable);
    }

    private IConverter booleanToCompositeConverter() {
        return new Converter(Boolean.class, Composite.class) {

            @Override
            public Object convert(final Object isExternal) {
                return isExternal != null && (Boolean) isExternal ? urlMappingComposite : pageDesignerMappingComposite;
            }
        };
    }
}
