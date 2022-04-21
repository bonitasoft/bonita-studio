/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.common.properties.Well;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class InfoMessageComposite extends Composite {

    private final Well well;

    public InfoMessageComposite(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory,
            int severity) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 0, 10, 0).create());
        well = new Well(this, null, widgetFactory, severity);
        well.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        widgetFactory.adapt(this);
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable,
            final FormMappingType type) {
        doBindInfo(context, formMappingObservable, type);
    }

    protected void doBindInfo(final DataBindingContext context, final IObservableValue formMappingObservable,
            final FormMappingType type) {
        final UpdateValueStrategy infoStrategy = new UpdateValueStrategy();
        infoStrategy.setConverter(new InfoMessageConverter(type));

        context.bindValue(well.labelObservable(), formMappingObservable, null, infoStrategy);
        context.bindValue(well.severityObservable(),
                formMappingObservable,
                null,
                updateValueStrategy()
                        .withConverter(ConverterBuilder.<FormMapping, Integer> newConverter()
                                .fromType(FormMapping.class)
                                .toType(Integer.class)
                                .withConvertFunction(mapping -> convertToSeverity(mapping, type))
                                .create())
                        .create());
    }

    private Integer convertToSeverity(FormMapping mapping, FormMappingType formMappingType) {
        if (mapping != null) {
            final EObject context = mapping.eContainer();
            switch (formMappingType) {
                case NONE:
                    return context instanceof Task ? IStatus.WARNING : IStatus.INFO;
                case INTERNAL:
                case URL:
                default:
                    return IStatus.INFO;
            }
        }
        return IStatus.INFO;
    }
}
