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
package org.bonitasoft.studio.actors.ui.handler;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.stream.Stream;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.OrganizationLabelProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator.Builder;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
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

    private OrganizationFileStore fileStore;
    private final OrganizationRepositoryStore organizationStore;
    private String username;

    public DeployOrganizationControlSupplier(String username, OrganizationRepositoryStore organizationStore) {
        this.organizationStore = organizationStore;
        this.username = username;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.wizard.ControlSupplier#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.jface.wizard.IWizardContainer,
     * org.eclipse.core.databinding.DataBindingContext)
     */
    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(
                GridLayoutFactory.swtDefaults().numColumns(1).spacing(LayoutConstants.getSpacing().x, 10).create());

        TableViewer viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 120).create());
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(30));
        layout.addColumnData(new ColumnWeightData(70));
        viewer.getTable().setLayout(layout);
        viewer.getTable().setLinesVisible(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.setContentProvider(ArrayContentProvider.getInstance());

        TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);
        final TableColumn nameColumn = column.getColumn();
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

        final TableColumnSorter sorter = new TableColumnSorter(viewer);
        sorter.setColumn(nameColumn);

        IObservableValue<OrganizationFileStore> fileStoreObservable = PojoProperties.value("fileStore").observe(this);

        SimpleContentProposalProvider proposalProvider = new SimpleContentProposalProvider(usernames());
        proposalProvider.setFiltering(true);

        ctx.bindValue(ViewersObservables.observeSingleSelection(viewer),
                fileStoreObservable,
                updateValueStrategy()
                        .withValidator(value -> value == null ? ValidationStatus.error("") : ValidationStatus.ok()).create(),
                updateValueStrategy().create());

        viewer.setInput(organizationStore.getChildren());

        TextWidget widget = new TextWidget.Builder()
                .withLabel(Messages.defaultUser)
                .grabHorizontalSpace()
                .fill()
                .labelAbove()
                .withProposalProvider(proposalProvider)
                .withTootltip(Messages.defaultUserTooltip)
                .bindTo(PojoProperties.value("username", String.class).observe(this))
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(defaultUserValidator()))
                .inContext(ctx)
                .createIn(mainComposite);

        fileStoreObservable.addValueChangeListener(event -> {
            organizationChanged(event.diff.getNewValue().getContent(), ctx, proposalProvider);
            //re-evaluate validators for selected organization
            widget.getValueBinding().validateTargetToModel();
        });

        return mainComposite;
    }

    protected void organizationChanged(Organization organization, DataBindingContext ctx,
            SimpleContentProposalProvider proposalProvider) {
        proposalProvider.setProposals(usernames());
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
        return fileStore == null ? new String[0] : fileStore.getContent()
                .getUsers()
                .getUser()
                .stream()
                .map(User::getUserName)
                .toArray(String[]::new);
    }

    public OrganizationFileStore getFileStore() {
        return fileStore;
    }

    public void setFileStore(OrganizationFileStore file) {
        this.fileStore = file;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
