/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.actors.ui.control;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.OrganizationLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator.Builder;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;

public class DeployOrganizationControlSupplier implements ControlSupplier {

    private IObservableValue<OrganizationFileStore> fileStoreObservable = new WritableValue<>();
    private OrganizationRepositoryStore organizationRepositoryStore;
    private IObservableValue<String> usernameObservable = new WritableValue<>();
    private ActiveOrganizationProvider activeOrganizationprovider;
    protected Optional<Organization> orgaToDeploy;

    public DeployOrganizationControlSupplier(String username, OrganizationRepositoryStore organizationStore,
            Optional<Organization> orgaToDeploy) {
        this.organizationRepositoryStore = organizationStore;
        this.orgaToDeploy = orgaToDeploy;
        this.activeOrganizationprovider = new ActiveOrganizationProvider();
        usernameObservable.setValue(username);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.swtDefaults().spacing(LayoutConstants.getSpacing().x, 10).create());

        if (!orgaToDeploy.isPresent()) {
            createOrganizationViewer(mainComposite, fileStoreObservable, ctx);
        }
        createDefaultUserTextWidget(ctx, mainComposite, fileStoreObservable);
        initializeOrganizationFileStore();

        return mainComposite;
    }

    private void initializeOrganizationFileStore() {
        String orgaName = orgaToDeploy.map(Organization::getName).orElse(activeOrganizationprovider.getActiveOrganization());
        organizationRepositoryStore.getChildren().stream()
                .filter(orga -> Objects.equals(orga.getDisplayName(), orgaName))
                .findFirst()
                .ifPresent(fileStoreObservable::setValue);
    }

    private void createDefaultUserTextWidget(DataBindingContext ctx, final Composite mainComposite,
            IObservableValue<OrganizationFileStore> fileStoreObservable) {
        SimpleContentProposalProvider proposalProvider = new SimpleContentProposalProvider(usernames());
        proposalProvider.setFiltering(true);

        TextWidget widget = new TextWidget.Builder()
                .withLabel(Messages.defaultUser)
                .grabHorizontalSpace()
                .fill()
                .labelAbove()
                .withProposalProvider(proposalProvider)
                .withTootltip(Messages.defaultUserTooltip)
                .bindTo(usernameObservable)
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(defaultUserValidator()))
                .inContext(ctx)
                .createIn(mainComposite);

        fileStoreObservable.addValueChangeListener(event -> {
            try {
                Organization content = event.diff.getNewValue().getContent();
                organizationChanged(content, proposalProvider);
                widget.getValueBinding().validateTargetToModel();
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.warning(e.getMessage(), ActorsPlugin.PLUGIN_ID);
            }
        });
    }

    protected void organizationChanged(Organization organization, SimpleContentProposalProvider proposalProvider) {
        proposalProvider.setProposals(usernames());
    }

    private void createOrganizationViewer(Composite mainComposite,
            IObservableValue<OrganizationFileStore> fileStoreObservable, DataBindingContext ctx) {
        TableViewer viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 120).create());
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(30));
        layout.addColumnData(new ColumnWeightData(70));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(ArrayContentProvider.getInstance());

        TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);
        TableColumn nameColumn = column.getColumn();
        column.getColumn().setText(Messages.name);
        column.setLabelProvider(new OrganizationLabelProvider());

        column = new TableViewerColumn(viewer, SWT.FILL);
        column.getColumn().setText(Messages.description);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                try {
                    return ((Organization) ((IRepositoryFileStore) element).getContent()).getDescription();
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed read organization content", e);
                }
                return null;
            }
        });

        TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(nameColumn);

        ctx.bindValue(ViewersObservables.observeSingleSelection(viewer),
                fileStoreObservable,
                updateValueStrategy()
                        .withValidator(value -> value == null ? ValidationStatus.error("") : ValidationStatus.ok()).create(),
                updateValueStrategy().create());

        viewer.setInput(organizationRepositoryStore.getChildren());
    }

    protected Builder defaultUserValidator() {
        return new MultiValidator.Builder()
                .havingValidators(new EmptyInputValidator(Messages.defaultUser))
                .havingValidators(this::userExistsInOrganization);
    }

    private IStatus userExistsInOrganization(Object user) {
        if (Stream.of(usernames()).noneMatch(user::equals)) {
            return ValidationStatus.error(Messages.bind(Messages.UserDoesntExistError, user));
        }
        return ValidationStatus.ok();
    }

    private String[] usernames() {
        try {
            return fileStoreObservable.getValue() == null
                    ? new String[0]
                    : fileStoreObservable.getValue().getContent()
                            .getUsers()
                            .getUser()
                            .stream()
                            .map(User::getUserName)
                            .toArray(String[]::new);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), ActorsPlugin.PLUGIN_ID);
            return new String[0];
        }
    }

    public OrganizationFileStore getFileStore() {
        return fileStoreObservable.getValue();
    }

    public String getUsername() {
        return usernameObservable.getValue();
    }

}
