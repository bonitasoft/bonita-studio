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
package org.bonitasoft.studio.actors.ui.wizard.page;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.regExpValidator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.validator.ValidatorConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 */
public class GroupsWizardPage extends AbstractOrganizationWizardPage implements ValidatorConstants {

    final class GroupParentPathLengthValidator implements IValidator {

        private final IObservableValue groupParentPathValue;
        InputLengthValidator lengthValidator = new InputLengthValidator(Messages.groupPath, LONG_FIELD_MAX_LENGTH);

        GroupParentPathLengthValidator(final IObservableValue groupParentPathValue) {
            this.groupParentPathValue = groupParentPathValue;
        }

        @Override
        public IStatus validate(final Object value) {
            return lengthValidator.validate(groupParentPathValue.getValue());
        }
    }

    final class UniqueGroupNameValidator implements IValidator {

        @Override
        public IStatus validate(final Object value) {
            for (final org.bonitasoft.studio.actors.model.organization.Group g : groupList) {
                final org.bonitasoft.studio.actors.model.organization.Group selectedGroup = (org.bonitasoft.studio.actors.model.organization.Group) groupSingleSelectionObservable
                        .getValue();
                if (!g.equals(selectedGroup)) {
                    if (g.getName().equals(value)
                            && (g.getParentPath() != null && g.getParentPath().equals(selectedGroup.getParentPath())
                                    || g.getParentPath() == null && selectedGroup.getParentPath() == null)) {
                        return ValidationStatus.error(Messages.groupNameAlreadyExistsForLevel);
                    }
                }
            }
            return Status.OK_STATUS;
        }
    }

    private Button addSubGroupButton;
    private IViewerObservableValue groupSingleSelectionObservable;
    private String groupPath;

    public GroupsWizardPage() {
        super(GroupsWizardPage.class.getName());
        setTitle(Messages.displayGroupsPageTitle);
        setDescription(Messages.displayGroupsPageDesc);
    }

    @Override
    protected StructuredViewer createViewer(final Composite parent) {
        final FilteredTree fileredTree = new FilteredTree(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION, new PatternFilter(), true);
        ((Text) ((Composite) fileredTree.getChildren()[0]).getChildren()[0]).setMessage(Messages.search);
        fileredTree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).minSize(200, 200).create());
        fileredTree.getViewer().setContentProvider(new GroupContentProvider());
        fileredTree.forceFocus();

        groupSingleSelectionObservable = ViewersObservables.observeSingleSelection(fileredTree.getViewer());
        groupSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

            private final Adapter groupAdapter = new AdapterImpl() {

                /*
                 * (non-Javadoc)
                 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
                 */
                @Override
                public void notifyChanged(Notification notification) {
                    switch (notification.getFeatureID(org.bonitasoft.studio.actors.model.organization.Group.class)) {
                        case OrganizationPackage.GROUP__NAME:
                            handleGroupNameChange(notification);
                            break;
                        default:
                            break;
                    }
                    super.notifyChanged(notification);
                }
            };

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final org.bonitasoft.studio.actors.model.organization.Group previousGroup = (org.bonitasoft.studio.actors.model.organization.Group) event.diff
                        .getOldValue();
                if (previousGroup != null) {
                    previousGroup.eAdapters().remove(groupAdapter);
                }
                final org.bonitasoft.studio.actors.model.organization.Group selectedGroup = (org.bonitasoft.studio.actors.model.organization.Group) event.diff
                        .getNewValue();

                if (selectedGroup != null) {
                    setControlEnabled(getInfoGroup(), true);
                    selectedGroup.eAdapters().add(groupAdapter);
                    addSubGroupButton.setEnabled(true);
                } else {
                    addSubGroupButton.setEnabled(false);
                    setControlEnabled(getInfoGroup(), false);
                }

            }
        });

        return fileredTree.getViewer();
    }

    @Override
    protected void configureViewer(final StructuredViewer viewer) {
        viewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                final String displayName = ((org.bonitasoft.studio.actors.model.organization.Group) element).getDisplayName();
                if (displayName == null || displayName.isEmpty()) {
                    return ((org.bonitasoft.studio.actors.model.organization.Group) element).getName();
                }
                return displayName;
            }
        });

        if (groupList != null && getViewer() != null) {
            getViewer().setInput(groupList);
            ((TreeViewer) getViewer()).expandAll();
        }
    }

    @Override
    public void setOrganization(final Organization organization) {
        super.setOrganization(organization);
        if (organization != null && getViewer() != null) {
            getViewer().setInput(groupList);
            ((TreeViewer) getViewer()).expandAll();
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
    }

    protected void handleGroupNameChange(final Notification notification) {
        final org.bonitasoft.studio.actors.model.organization.Group group = (org.bonitasoft.studio.actors.model.organization.Group) notification.getNotifier();
        final org.bonitasoft.studio.actors.model.organization.Group oldGroup = EcoreUtil.copy(group);
        final Object oldValue = notification.getOldValue();
        if (oldValue != null) {
            if (oldGroup != null) {
                oldGroup.setName(oldValue.toString());
                final ITreeContentProvider provider = (ITreeContentProvider) getViewer().getContentProvider();
                final String oldPath = GroupContentProvider.getGroupPath(oldGroup) + GroupContentProvider.GROUP_SEPARATOR;
                final String newPath = GroupContentProvider.getGroupPath(group) + GroupContentProvider.GROUP_SEPARATOR;

                if (provider != null && provider.hasChildren(oldGroup)) {
                    for (final Object child : provider.getChildren(oldGroup)) {
                        final org.bonitasoft.studio.actors.model.organization.Group childGroup = (org.bonitasoft.studio.actors.model.organization.Group) child;
                        updateParentPath(childGroup, oldPath, newPath, provider);
                        final String parent = childGroup.getParentPath() + GroupContentProvider.GROUP_SEPARATOR;
                        String path = parent.replace(oldPath, newPath);
                        if (path.endsWith(GroupContentProvider.GROUP_SEPARATOR)) {
                            path = path.substring(0, path.length() - 1);
                        }
                        if (path.equals(GroupContentProvider.GROUP_SEPARATOR)) {
                            childGroup.setParentPath(null);
                        } else {
                            childGroup.setParentPath(path);
                        }
                    }
                }
                for (final Membership m : membershipList) {
                    if (withGroupParentPath(oldPath).apply(m)) {
                        String groupParentPath = m.getGroupParentPath();
                        if (groupParentPath != null && !groupParentPath.endsWith("/")) {
                            groupParentPath = groupParentPath + "/";
                        }
                        final String replace = groupParentPath.replace(oldPath, newPath);
                        m.setGroupParentPath(replace.substring(0, replace.length() - 1));
                    }
                }
                for (final Membership m : membershipList) {
                    if (withGroupName(oldValue.toString()).apply(m)) {
                        m.setGroupName(group.getName());
                    }
                }
            }
            if (group != null) {
                if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                    getViewer().refresh(group);
                }
            }
        }
    }

    private Predicate<Membership> withGroupName(final String name) {
        return new Predicate<Membership>() {

            @Override
            public boolean apply(Membership membership) {
                return Objects.equals(name, membership.getGroupName());
            }
        };
    }

    private Predicate<Membership> withGroupParentPath(final String path) {
        return new Predicate<Membership>() {

            @Override
            public boolean apply(Membership membership) {
                String groupParentPath = membership.getGroupParentPath();
                if (groupParentPath != null && !groupParentPath.endsWith("/")) {
                    groupParentPath = groupParentPath + "/";
                }
                return groupParentPath != null && groupParentPath.startsWith(path);
            }
        };
    }

    private void handleGroupDisplayName(final ValueChangeEvent event) {
        final org.bonitasoft.studio.actors.model.organization.Group group = (org.bonitasoft.studio.actors.model.organization.Group) groupSingleSelectionObservable
                .getValue();
        final org.bonitasoft.studio.actors.model.organization.Group oldGroup = EcoreUtil.copy(group);
        final Object oldValue = event.diff.getOldValue();
        if (oldValue != null) {
            if (oldGroup != null) {
                oldGroup.setDisplayName(oldValue.toString());
            }

            if (getViewer() != null && !getViewer().getControl().isDisposed()) {
                getViewer().refresh(group);
            }
        }

    }

    private void updateParentPath(final org.bonitasoft.studio.actors.model.organization.Group group, final String pathToReplace, final String newPath,
            final ITreeContentProvider provider) {
        if (provider.hasChildren(group)) {
            for (final Object child : provider.getChildren(group)) {
                final org.bonitasoft.studio.actors.model.organization.Group childGroup = (org.bonitasoft.studio.actors.model.organization.Group) child;
                updateParentPath(childGroup, pathToReplace, newPath, provider);
                String path = childGroup.getParentPath().replace(pathToReplace, newPath);
                if (path.endsWith(GroupContentProvider.GROUP_SEPARATOR)) {
                    path = path.substring(0, path.length() - 1);
                }
                if (path != null && path.equals(GroupContentProvider.GROUP_SEPARATOR)) {
                    childGroup.setParentPath(null);
                } else {
                    childGroup.setParentPath(path);
                }
            }
        }
    }

    @Override
    protected void configureInfoGroup(final Group group) {
        group.setText(Messages.details);
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 5).spacing(10, 5).create());

        createNameField(group);

        createDisplayNameField(group);

        createPathField(group);

        createDescriptionField(group);

        getViewer().setSelection(new StructuredSelection());
        setControlEnabled(getInfoGroup(), false);
    }

    private void createDescriptionField(final Group group) {
        final Label descriptionLabel = new Label(group, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create());
        descriptionLabel.setText(Messages.description);

        final Text groupDescriptionText = new Text(group, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        groupDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 80).create());

        final IObservableValue groupDescriptionValue = EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable,
                OrganizationPackage.Literals.GROUP__DESCRIPTION);
        context.bindValue(SWTObservables.observeText(groupDescriptionText, SWT.Modify), groupDescriptionValue);
    }

    private void createDisplayNameField(final Group group) {
        final Label displayNameLabel = new Label(group, SWT.NONE);
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        displayNameLabel.setText(Messages.displayName);

        final Text displayNamedText = new Text(group, SWT.BORDER);
        displayNamedText.setMessage(Messages.groupNameExample);
        displayNamedText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final IObservableValue displayNameValue = EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable,
                OrganizationPackage.Literals.GROUP__DISPLAY_NAME);
        final Binding binding = context.bindValue(SWTObservables.observeText(displayNamedText, SWT.Modify), displayNameValue,
                UpdateStrategyFactory.updateValueStrategy()
                        .withValidator(maxLengthValidator(Messages.displayName, LONG_FIELD_MAX_LENGTH)).create(),
                null);
        ControlDecorationSupport.create(binding, SWT.LEFT);

        displayNameValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                handleGroupDisplayName(event);
            }

        });

    }

    private void createPathField(final Group group) {
        final Label pathLabel = new Label(group, SWT.NONE);
        pathLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        pathLabel.setText(Messages.groupPath);

        final Text pathText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
        pathText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        context.bindValue(SWTObservables.observeText(pathText, SWT.Modify),
                EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable,
                        OrganizationPackage.Literals.GROUP__PARENT_PATH));
    }

    private void createNameField(final Group group) {
        final Label groupNameLabel = new Label(group, SWT.NONE);
        groupNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        groupNameLabel.setText(Messages.name + " *");

        final Text groupNameText = new Text(group, SWT.BORDER);
        groupNameText.setMessage(Messages.groupIdExample);
        groupNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(130, SWT.DEFAULT).create());

        final IObservableValue groupParentPathValue = EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable,
                OrganizationPackage.Literals.GROUP__PARENT_PATH);

        final GroupParentPathLengthValidator groupParentPathLengthValidator = new GroupParentPathLengthValidator(groupParentPathValue);
        final Binding binding = context.bindValue(SWTObservables.observeText(groupNameText, SWT.Modify),
                EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable, OrganizationPackage.Literals.GROUP__NAME),
                updateValueStrategy().withValidator(
                        multiValidator()
                                .addValidator(mandatoryValidator(Messages.name))
                                .addValidator(maxLengthValidator(Messages.name, GROUP_NAME_MAX_LENGTH))
                                .addValidator(regExpValidator(Messages.illegalCharacter, "^[^/]*$"))
                                .addValidator(new UniqueGroupNameValidator())
                                .addValidator(groupParentPathLengthValidator).create())
                        .create(),
                null);
        ControlDecorationSupport.create(binding, SWT.LEFT, group, new ControlDecorationUpdater() {

            @Override
            protected void update(final ControlDecoration decoration, final IStatus status) {
                if (groupSingleSelectionObservable.getValue() != null) {
                    super.update(decoration, status);
                }
            }
        });

    }

    @Override
    protected Button createButtons(final Composite buttonComposite) {
        final Button addGroupButton = new Button(buttonComposite, SWT.FLAT);
        addGroupButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addGroupButton.setText(Messages.addParentGroup);
        addGroupButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addRootGroupButtonSelected();
            }
        });

        addSubGroupButton = new Button(buttonComposite, SWT.FLAT);
        addSubGroupButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addSubGroupButton.setText(Messages.addSubGroup);
        addSubGroupButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addButtonSelected();
            }
        });

        final Button removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        removeButton.setText(Messages.remove);
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeButtonSelected();
            }
        });
        removeButton.setEnabled(false);
        return removeButton;
    }

    protected void addRootGroupButtonSelected() {
        getViewer().setSelection(new StructuredSelection());
        addButtonSelected();
    }

    @Override
    protected void addButtonSelected() {
        final org.bonitasoft.studio.actors.model.organization.Group parentGoup = (org.bonitasoft.studio.actors.model.organization.Group) ((IStructuredSelection) getViewer()
                .getSelection()).getFirstElement();
        final org.bonitasoft.studio.actors.model.organization.Group group = OrganizationFactory.eINSTANCE.createGroup();
        group.setName(generateGroupname());
        group.setDisplayName(group.getName());
        if (parentGoup != null) {
            if (parentGoup.getParentPath() == null) {
                group.setParentPath(GroupContentProvider.GROUP_SEPARATOR + parentGoup.getName());
            } else {
                group.setParentPath(parentGoup.getParentPath() + GroupContentProvider.GROUP_SEPARATOR + parentGoup.getName());
            }
        }
        groupList.add(group);
        getViewer().setInput(groupList);
        ((TreeViewer) getViewer()).expandAll();
        getViewer().setSelection(new StructuredSelection(group));
    }

    private String generateGroupname() {
        final Set<String> names = new HashSet<>();
        for (final org.bonitasoft.studio.actors.model.organization.Group g : groupList) {
            names.add(g.getName());
        }

        return NamingUtils.generateNewName(names, Messages.defaultGroupName, 1);
    }

    @Override
    protected void removeButtonSelected() {
        for (final Object sel : ((IStructuredSelection) getViewer().getSelection()).toList()) {
            if (sel instanceof org.bonitasoft.studio.actors.model.organization.Group) {
                final ITreeContentProvider contentProvider = (ITreeContentProvider) getViewer().getContentProvider();
                if (contentProvider.hasChildren(sel)) {
                    if (groupList.contains(sel)) {
                        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteGroupTitle, Messages.deleteGroupMsg)) {
                            removeChildren(contentProvider, sel);
                        } else {
                            return;
                        }
                    }
                }
                groupList.remove(sel);
            }
        }
        getViewer().setInput(groupList);
        selectionChanged(new SelectionChangedEvent(getViewer(), new StructuredSelection()));
        ((TreeViewer) getViewer()).expandAll();
    }

    private void removeChildren(final ITreeContentProvider contentProvider, final Object sel) {
        if (contentProvider.hasChildren(sel)) {
            for (final Object child : contentProvider.getChildren(sel)) {
                removeChildren(contentProvider, child);
                groupList.remove(child);
            }
        }
    }

    @Override
    protected boolean viewerSelect(final Object element, final String searchQuery) {
        return false;
    }

    public String getGroupPath() {
        return groupPath;
    }

    public void setGroupPath(final String groupPath) {
        this.groupPath = groupPath;
    }

    @Override
    public boolean isPageComplete() {
        return super.isPageComplete() || groupSingleSelectionObservable.getValue() == null;
    }
}
