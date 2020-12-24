/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.ui.wizard.page;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.identity.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class RolesWizardPage extends AbstractOrganizationWizardPage implements ValidatorConstants {

    final class UniqueRoleNameValidator implements IValidator {

        @Override
        public IStatus validate(final Object value) {
            for (final Role role : roleList) {
                if (!role.equals(roleSingleSelectionObservable.getValue())) {
                    if (role.getName().equals(value)) {
                        return ValidationStatus.error(Messages.roleNameAlreadyExists);
                    }
                }
            }
            return Status.OK_STATUS;
        }
    }

    private final List<Membership> roleMemberShips = new ArrayList<>();
    private IViewerObservableValue roleSingleSelectionObservable;

    public RolesWizardPage() {
        super(RolesWizardPage.class.getName());
        setTitle(Messages.displayRolesPageTitle);
        setDescription(Messages.displayRolesPageDesc);
    }

    @Override
    protected void configureViewer(final StructuredViewer viewer) {

        final TableViewer roleTableViewer = (TableViewer) viewer;
        final Table table = roleTableViewer.getTable();

        roleSingleSelectionObservable = ViewersObservables.observeSingleSelection(getViewer());
        roleSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Role selectedRole = (Role) event.diff.getNewValue();
                final boolean isSelectedRole = selectedRole != null;
                setControlEnabled(getInfoGroup(), isSelectedRole);
                roleMemberShips.clear();
                for (final Membership m : membershipList) {
                    if (Objects.equals(selectedRole.getName(), m.getRoleName())) {
                        roleMemberShips.add(m);
                    }
                }
            }
        });

        addNameColumn(roleTableViewer);

        addDisplayNameColumn(roleTableViewer);

        addDescriptionColumn(roleTableViewer);

        addTableColumLayout(table);

        if (roleList != null && getViewer() != null) {
            getViewer().setInput(roleList);
        }

    }

    private void addDescriptionColumn(final TableViewer tViewer) {
        final TableViewerColumn column = new TableViewerColumn(tViewer, SWT.FILL);
        column.getColumn().setText(Messages.description);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((Role) element).getDescription();
            }
        });

        column.getColumn().setWidth(90);
        column.getColumn().setMoveable(false);
        column.getColumn().setResizable(true);
    }

    private void addDisplayNameColumn(final TableViewer tViewer) {
        final TableViewerColumn column = new TableViewerColumn(tViewer, SWT.FILL);
        column.getColumn().setText(Messages.displayName);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((Role) element).getDisplayName();
            }
        });

        column.getColumn().setWidth(90);
        column.getColumn().setMoveable(false);
        column.getColumn().setResizable(true);
    }

    private void addNameColumn(final TableViewer tViewer) {
        final TableViewerColumn column = new TableViewerColumn(tViewer, SWT.FILL);
        final TableColumn nameColumn = column.getColumn();
        column.getColumn().setText(Messages.roleName);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((Role) element).getName();
            }
        });

        column.getColumn().setWidth(90);
        column.getColumn().setMoveable(false);
        column.getColumn().setResizable(true);

        final TableColumnSorter sorter = new TableColumnSorter(tViewer);
        sorter.setColumn(nameColumn);
    }

    @Override
    public void setOrganization(final Organization organization) {
        super.setOrganization(organization);
        if (organization != null && getViewer() != null) {
            getViewer().setInput(roleList);
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
    }

    @Override
    protected void configureInfoGroup(final Group group) {

        group.setText(Messages.details);

        Composite detailsComposite = new Composite(group, SWT.NONE);
        detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        detailsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 5).spacing(10, 5).create());
        IViewerObservableValue<Object> selection = ViewerProperties.singleSelection().observe(getViewer());
        context.bindValue(WidgetProperties.visible().observe(detailsComposite), new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return selection.getValue() != null;
            }
        });

        createNameField(detailsComposite);

        createDisplayNameField(detailsComposite);

        createDescriptionField(detailsComposite);

        setControlEnabled(getInfoGroup(), false);
    }

    private void createDescriptionField(final Composite group) {
        final Label descriptionLabel = new Label(group, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create());
        descriptionLabel.setText(Messages.description);

        final Text roleDescriptionText = new Text(group, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        roleDescriptionText
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 80).create());
        roleDescriptionText.setMessage(Messages.descriptionHint);
        roleDescriptionText.setTextLimit(255);

        final IObservableValue roleDescriptionValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                roleSingleSelectionObservable, OrganizationPackage.Literals.ROLE__DESCRIPTION);
        context.bindValue(SWTObservables.observeText(roleDescriptionText, SWT.Modify), roleDescriptionValue);
        roleDescriptionValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                handleRoleDescriptionChange(event);
            }
        });
    }

    private void createDisplayNameField(final Composite group) {
        final Label displayNameLabel = new Label(group, SWT.NONE);
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        displayNameLabel.setText(Messages.displayName);

        final Text displayNamedText = new Text(group, SWT.BORDER);
        displayNamedText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final IObservableValue roleDisplayNameValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                roleSingleSelectionObservable, OrganizationPackage.Literals.ROLE__DISPLAY_NAME);
        final Binding binding = context.bindValue(SWTObservables.observeText(displayNamedText, SWT.Modify),
                roleDisplayNameValue,
                updateValueStrategy().withValidator(maxLengthValidator(Messages.displayName, LONG_FIELD_MAX_LENGTH))
                        .create(),
                null);
        ControlDecorationSupport.create(binding, SWT.LEFT);

        roleDisplayNameValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent arg0) {
                handleRoleDisplayNameChange(arg0);

            }
        });
    }

    private void createNameField(final Composite group) {
        final Label roleName = new Label(group, SWT.NONE);
        roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        roleName.setText(Messages.name + " *");

        final Text roleNameText = new Text(group, SWT.BORDER);
        roleNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(130, SWT.DEFAULT).create());

        final IObservableValue roleNameValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                roleSingleSelectionObservable, OrganizationPackage.Literals.ROLE__NAME);
        final Binding binding = context.bindValue(SWTObservables.observeText(roleNameText, SWT.Modify),
                roleNameValue,
                updateValueStrategy().withValidator(multiValidator()
                        .addValidator(mandatoryValidator(Messages.name))
                        .addValidator(maxLengthValidator(Messages.name, LONG_FIELD_MAX_LENGTH))
                        .addValidator(new UniqueRoleNameValidator()).create()).create(),
                null);

        ControlDecorationSupport.create(binding, SWT.LEFT, group, new ControlDecorationUpdater() {

            @Override
            protected void update(final ControlDecoration decoration, final IStatus status) {
                if (roleSingleSelectionObservable.getValue() != null) {
                    super.update(decoration, status);
                }
            }
        });
        roleNameValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                handleRoleNameChange(event);
            }
        });
    }

    private void handleRoleNameChange(final ValueChangeEvent event) {
        Object value = roleSingleSelectionObservable.getValue();
        final Role role = (Role) value;
        if (value != null) {
            if (role != null) {
                if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                    getViewer().refresh(role);
                }
                final String newRoleName = (String) event.diff.getNewValue();
                roleMemberShips.stream().forEach(m -> m.setRoleName(newRoleName));
            }
        }
    }

    private void handleRoleDescriptionChange(final ValueChangeEvent event) {
        final Role role = (Role) roleSingleSelectionObservable.getValue();
        final Role oldRole = EcoreUtil.copy(role);
        final Object oldValue = event.diff.getOldValue();
        if (oldValue != null) {
            oldRole.setName(oldValue.toString());

            if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                getViewer().refresh(role);
            }
        }
    }

    private void handleRoleDisplayNameChange(final ValueChangeEvent event) {
        final Role role = (Role) roleSingleSelectionObservable.getValue();
        final Role oldRole = EcoreUtil.copy(role);
        final Object oldValue = event.diff.getOldValue();
        if (oldValue != null) {
            if (oldRole != null) {
                oldRole.setDisplayName(oldValue.toString());
            }

            if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                getViewer().refresh(role);
            }
        }
    }

    @Override
    protected void addButtonSelected() {
        final Role role = OrganizationFactory.eINSTANCE.createRole();
        role.setName(generateRolename());
        role.setDisplayName(role.getName());
        roleList.add(role);
        getViewer().setInput(roleList);
        getViewer().setSelection(new StructuredSelection(role));
    }

    private String generateRolename() {
        final Set<String> names = new HashSet<>();
        for (final Role r : roleList) {
            names.add(r.getName());
        }

        return NamingUtils.generateNewName(names, Messages.defaultRoleName, 1);
    }

    @Override
    protected void removeButtonSelected() {
        for (final Object sel : ((IStructuredSelection) getViewer().getSelection()).toList()) {
            if (sel instanceof Role) {
                roleList.remove(sel);
            }
        }
        getViewer().setInput(roleList);
        selectionChanged(new SelectionChangedEvent(getViewer(), new StructuredSelection()));
    }

    @Override
    protected boolean viewerSelect(final Object element, final String searchQuery) {
        if (searchQuery == null || searchQuery.isEmpty()
                || ((Role) element).getName() != null
                        && ((Role) element).getName().toLowerCase().contains(searchQuery.toLowerCase())
                || ((Role) element).getDisplayName() != null
                        && ((Role) element).getDisplayName().toLowerCase().contains(searchQuery.toLowerCase())
                || ((Role) element).getDescription() != null
                        && ((Role) element).getDescription().toLowerCase().contains(searchQuery.toLowerCase())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete() || roleSingleSelectionObservable.getValue() == null;
    }

}
