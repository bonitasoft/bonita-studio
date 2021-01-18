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
package org.bonitasoft.studio.identity.organization.editor.control.overview;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.overview.OverviewFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.repository.OrganizationFileStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.identity.organization.validator.OrganizationNameValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class OverviewEditionControl {

    private OverviewFormPage formPage;
    private IObservableValue<Organization> organizationObservable;
    private DataBindingContext ctx;
    private Section section;

    public OverviewEditionControl(Composite parent, OverviewFormPage formPage,
            IObservableValue<Organization> organizationObservable, DataBindingContext ctx) {
        this.formPage = formPage;
        this.organizationObservable = organizationObservable;
        this.ctx = ctx;

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.displayOrganizationOverviewPageTitle);

        Composite mainComposite = formPage.getToolkit().createComposite(section);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());

        createNameField(mainComposite);
        createDescriptionField(mainComposite);

        section.setClient(mainComposite);
    }

    private void createNameField(Composite parent) {
        IObservableValue<String> nameObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                organizationObservable, OrganizationPackage.Literals.ORGANIZATION__NAME);
        OrganizationNameValidator nameValidator = new OrganizationNameValidator(formPage);
        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .widthHint(300)
                .transactionalEdit((oldName, newName) -> renameOrgaFile(newName))
                .bindTo(nameObservable)
                .withTargetToModelStrategy(convertUpdateValueStrategy().withValidator(nameValidator).create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private void renameOrgaFile(String newName) {
        OrganizationRepositoryStore repositoryStore = getRepositoryStore();
        String fileName = formPage.getEditor().getEditorInput().getName();
        OrganizationFileStore fileStore = repositoryStore.getChild(fileName, true);

        if (formPage.isDirty()) {
            BonitaStudioLog.info(Messages.organizationSavedBeforeRename, IdentityPlugin.PLUGIN_ID);
            formPage.doSave(new NullProgressMonitor());
        }
        fileStore.rename(String.format("%s.%s", newName, OrganizationRepositoryStore.ORGANIZATION_EXT));
        formPage.doSave(new NullProgressMonitor());
    }

    private OrganizationRepositoryStore getRepositoryStore() {
        return formPage.getRepositoryAccessor()
                .getRepositoryStore(OrganizationRepositoryStore.class);
    }

    private void createDescriptionField(Composite parent) {
        IObservableValue<String> descriptionObservable = EMFObservables.observeDetailValue(ctx.getValidationRealm(),
                organizationObservable, OrganizationPackage.Literals.ORGANIZATION__DESCRIPTION);

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .widthHint(400)
                .heightHint(100)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

}
