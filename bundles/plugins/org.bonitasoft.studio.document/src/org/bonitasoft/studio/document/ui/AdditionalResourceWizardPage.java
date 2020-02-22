/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.validator.AdditionalResourceBarPathValidator;
import org.bonitasoft.studio.model.process.AdditionalResource;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class AdditionalResourceWizardPage extends WizardPage {

    private Pool pool;
    private AdditionalResource workingCopy;
    private AdditionalResource originalAdditionalResource;
    private EMFDataBindingContext ctx;

    public AdditionalResourceWizardPage(Pool pool, AdditionalResource workingCopy,
            AdditionalResource originalAdditionalResource) {
        super(AdditionalResourceWizardPage.class.getName());
        this.pool = pool;
        this.workingCopy = workingCopy;
        this.originalAdditionalResource = originalAdditionalResource;

        setTitle(Messages.newAdditionalResource);
        setDescription(Messages.newAdditionalResourceDescription);
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.swtDefaults().spacing(LayoutConstants.getSpacing().x, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        
        ctx = new EMFDataBindingContext();
        
        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(EMFObservables.observeValue(workingCopy,
                        ProcessPackage.Literals.ADDITIONAL_RESOURCE__NAME))
                .withValidator(new AdditionalResourceBarPathValidator(pool, originalAdditionalResource))
                .inContext(ctx)
                .createIn(composite)
                .setFocus();

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .heightHint(100)
                .bindTo(EMFObservables.observeValue(workingCopy,
                        ProcessPackage.Literals.ADDITIONAL_RESOURCE__DESCRIPTION))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        setControl(composite);
        WizardPageSupport.create(this, ctx);
    }

}
