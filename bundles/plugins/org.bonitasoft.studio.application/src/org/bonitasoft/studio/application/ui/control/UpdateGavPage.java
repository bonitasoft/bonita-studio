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
package org.bonitasoft.studio.application.ui.control;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class UpdateGavPage extends ImportExtensionPage {

    private Path dependencyPath;

    public UpdateGavPage(MavenRepositoryRegistry mavenRepositoryRegistry,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler,
            Optional<Dependency> extensionToUpdate,
            Path dependencyPath,
            Optional<Boolean> isLocal) {
        super(mavenRepositoryRegistry, mavenModel, extensionTypeHandler, extensionToUpdate, isLocal);
        dependency.setVersion(extensionToUpdate.map(Dependency::getVersion).orElse(null));
        this.dependencyPath = dependencyPath;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults()
                .margins(10, 10)
                .extendedMargins(0, 0, 0, 30)
                .create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createMavenCoordinatesGroup(mainComposite, ctx);

        return mainComposite;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        super.pageChanged(event);
        Display.getDefault().asyncExec(() -> createDependencyLookup(dependencyPath.toFile()));
    }

    @Override
    protected Supplier<Boolean> createGavEditableSupplier() {
        return () -> true;
    }

    @Override
    protected ComputedValue<Boolean> createGavVisibleComputedValue() {
        return new ComputedValue<>() {

            @Override
            protected Boolean calculate() {
                return true;
            }
        };
    }

    @Override
    protected IStatus computeDependencyAlreadyExistsStatus() {
        return ValidationStatus.ok();
    }

}
