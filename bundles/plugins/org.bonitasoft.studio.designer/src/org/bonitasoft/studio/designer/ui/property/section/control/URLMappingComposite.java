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

import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class URLMappingComposite extends Composite implements BonitaPreferenceConstants {

    private static final int WIDTH_HINT = 300;
    private final Text urlText;
    private final Label info;

    public URLMappingComposite(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.swtDefaults().numColumns(2).extendedMargins(10, 0, 10, 0).create());

        final Label label = widgetFactory.createLabel(this, Messages.url);
        label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.CENTER).create());

        urlText = widgetFactory.createText(this, "", SWT.BORDER);
        urlText.setLayoutData(GridDataFactory.swtDefaults().hint(WIDTH_HINT, SWT.DEFAULT).create());

        info = widgetFactory.createLabel(this, "", SWT.WRAP);
        info.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).align(SWT.FILL, SWT.CENTER).create());

        widgetFactory.adapt(this);
    }

    public void doBindControl(final DataBindingContext context, final IObservableValue formMappingObservable) {
        context.bindValue(SWTObservables.observeText(urlText, SWT.Modify), CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                formMappingObservable,
                ProcessPackage.Literals.FORM_MAPPING__URL));
        doBindInfo(context, formMappingObservable);
    }

    protected void doBindInfo(final DataBindingContext context, final IObservableValue formMappingObservable) {
        final UpdateValueStrategy infoStrategy = new UpdateValueStrategy();
        infoStrategy.setConverter(new InfoMessageConverter(FormMappingType.URL));
        context.bindValue(SWTObservables.observeText(info), formMappingObservable, null, infoStrategy);
    }
}
