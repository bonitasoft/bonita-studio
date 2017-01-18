/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.wizard;

import java.util.Optional;
import java.util.function.Supplier;

import org.bonitasoft.studio.ui.databinding.NoMessageWizardPageSupport;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class WizardPageBuilder {

    public static WizardPageBuilder newPage() {
        return new WizardPageBuilder();
    }

    private String title;
    private String description;
    private ControlSupplier controlSupplier;
    private Optional<DataBindingContext> context = Optional.empty();
    private boolean disposeContext = true;

    private WizardPageBuilder() {

    }

    /**
     * The {@link Supplier} of the {@link DataBindingContext} to use with this page.
     * 
     * @see {@link org.bonitasoft.studio.ui.wizard.WizardPageBuilder#keepDatabindingContextAlive() keepDatabindingContextAlive}
     */
    public WizardPageBuilder withDatabindingContext(DataBindingContext context) {
        this.context = Optional.ofNullable(context);
        return this;
    }

    /**
     * The page title
     */
    public WizardPageBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * The page description
     */
    public WizardPageBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * The {@link ControlSupplier} responsible of creating a {@link Control} for this {@link WizardPage}
     */
    public WizardPageBuilder withControl(ControlSupplier controlSupplier) {
        this.controlSupplier = controlSupplier;
        return this;
    }

    /**
     * The {@link DataBindingContext} supplied will not be disposed by the {@link WizardPage}
     */
    public WizardPageBuilder keepDatabindingContextAlive() {
        this.disposeContext = false;
        return this;
    }

    /**
     * Create an instance of {@link WizardPage} for this {@link WizardPageBuilder}
     */
    public WizardPage asPage() {
        final WizardPage page = new WizardPage(title) {

            private DataBindingContext ctx;

            @Override
            public void createControl(Composite parent) {
                ctx = context.orElse(new DataBindingContext());
                NoMessageWizardPageSupport.create(this, ctx);
                setControl(controlSupplier.createControl(parent, ctx));
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.dialogs.DialogPage#dispose()
             */
            @Override
            public void dispose() {
                if (ctx != null && disposeContext) {
                    ctx.dispose();
                }
                super.dispose();
            }
        };
        page.setTitle(title);
        page.setDescription(description);
        return page;
    }

}
