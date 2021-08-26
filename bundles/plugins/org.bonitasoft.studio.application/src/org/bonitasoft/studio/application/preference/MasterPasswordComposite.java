/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.preference;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;

public class MasterPasswordComposite extends Composite {

    public MasterPasswordComposite(Composite parent, MavenPasswordManager passwordManager,
            IObservableValue<String> masterPwdObservable, DataBindingContext ctx) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);

        var link = new Link(this, SWT.NONE);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        link.setText(Messages.encryptionLink);
        link.addListener(SWT.Selection,
                new OpenSystemBrowserListener("https://maven.apache.org/guides/mini/guide-encryption.html"));

        passwordManager.getCurrentMasterPassword().ifPresent(masterPwdObservable::setValue);

        var textWidget = new TextWidget.Builder()
                .withLabel(Messages.encryptionMasterPassword)
                .labelAbove()
                .withTootltip(Messages.updateMasterPasswordWarning)
                .fill()
                .grabHorizontalSpace()
                .transactionalEdit((oldValue, newValue) -> passwordManager.updateMasterPassword(newValue))
                .bindTo(masterPwdObservable)
                .withTargetToModelStrategy(convertUpdateValueStrategy()
                        .withConverter(ConverterBuilder.<String, String> newConverter()
                                .fromType(String.class)
                                .toType(String.class)
                                .withConvertFunction(passwordManager::encryptMasterPassword)
                                .create())
                        .create())
                .withModelToTargetStrategy(updateValueStrategy().create())
                .inContext(ctx)
                .useNativeRender()
                .createIn(this);
        textWidget.getControl().getParent().setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);
    }

}
