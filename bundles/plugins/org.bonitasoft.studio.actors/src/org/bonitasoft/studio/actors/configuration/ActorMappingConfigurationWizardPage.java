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
package org.bonitasoft.studio.actors.configuration;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.action.ExportActorMappingAction;
import org.bonitasoft.studio.actors.action.ImportActorMappingAction;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.SelectGroupsWizard;
import org.bonitasoft.studio.actors.ui.wizard.SelectMembershipsWizard;
import org.bonitasoft.studio.actors.ui.wizard.SelectRolesWizard;
import org.bonitasoft.studio.actors.ui.wizard.SelectUsersWizard;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingAdapterFactory;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 */
public class ActorMappingConfigurationWizardPage extends WizardPage
        implements ISelectionChangedListener, IProcessConfigurationWizardPage, IDoubleClickListener {

    private Configuration configuration;
    private AbstractProcess process;
    private final ComposedAdapterFactory adapterFactory;
    private TreeViewer mappingTree;
    private Button groupButton;
    private Button roleButton;
    private Button membershipButton;
    private Button userButton;
    private Optional<Organization> deployedOrganization;

    public ActorMappingConfigurationWizardPage() {
        super(ActorMappingConfigurationWizardPage.class.getName());
        setTitle(Messages.actorMappingTitle);
        setDescription(Messages.actorMappingDesc);
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ActorMappingAdapterFactory());
    }

    @Override
    public void createControl(final Composite parent) {
        deployedOrganization = Optional.ofNullable(RepositoryManager.getInstance()
                .getRepositoryStore(OrganizationRepositoryStore.class)
                .getChild(String.format("%s.organization", new ActiveOrganizationProvider().getActiveOrganization()), true))
                .map(OrganizationFileStore::getContent);
        
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(5, 2).create());

        final Label descriptionLabel = new Label(mainComposite, SWT.WRAP);
        descriptionLabel.setText(getDescription());
        descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).span(2, 1).create());

        createMappingList(mainComposite);
        createImportExportButtons(mainComposite);
        setControl(mainComposite);
    }

    private void createImportExportButtons(final Composite mainComposite) {
        final Composite composite = new Composite(mainComposite, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createImportButton(composite);
        createExportButton(composite);
    }

    protected void createExportButton(final Composite composite) {
        final Button exportButton = new Button(composite, SWT.PUSH);
        exportButton.setText(Messages.exportActorMappingFile);
        exportButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
        exportButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ExportActorMappingAction action = new ExportActorMappingAction();
                action.setConfiguration(configuration);
                action.setProcess(process);
                action.run();
            }
        });
    }

    protected void createImportButton(final Composite composite) {
        final Button importButton = new Button(composite, SWT.PUSH);
        importButton.setText(Messages.importActorMappingFile);
        importButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
        importButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ImportActorMappingAction action = new ImportActorMappingAction();
                action.setConfiguration(configuration);
                action.setProcess(process);
                action.run();
                mappingTree.refresh();
                getContainer().updateMessage();
            }
        });
    }

    private void createMappingList(final Composite parent) {
        final Composite listComposite = new Composite(parent, SWT.NONE);
        listComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        listComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 0).spacing(5, 3).create());

        createMappingTree(listComposite);

        final Composite buttonComposite = new Composite(listComposite, SWT.NONE);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

        createGroupButton(buttonComposite);
        createRoleButton(buttonComposite);
        createMembershipButton(buttonComposite);
        createUserButton(buttonComposite);

        updateButtons();
    }

    protected void createUserButton(final Composite buttonComposite) {
        userButton = new Button(buttonComposite, SWT.FLAT);
        userButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        userButton.setText(Messages.addUser);
        userButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                userAction();
                getContainer().updateMessage();
            }
        });
    }

    protected void createMembershipButton(final Composite buttonComposite) {
        membershipButton = new Button(buttonComposite, SWT.FLAT);
        membershipButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        membershipButton.setText(Messages.addMembershipEtc);
        membershipButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                membershipAction();
                getContainer().updateMessage();
            }
        });
    }

    protected void createRoleButton(final Composite buttonComposite) {
        roleButton = new Button(buttonComposite, SWT.FLAT);
        roleButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        roleButton.setText(Messages.addRole);
        roleButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                roleAction();
                getContainer().updateMessage();
            }
        });
    }

    protected void createGroupButton(final Composite buttonComposite) {
        groupButton = new Button(buttonComposite, SWT.FLAT);
        groupButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        groupButton.setText(Messages.addGroup);
        groupButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                groupAction();
                getContainer().updateMessage();
            }
        });
    }

    protected void createMappingTree(final Composite listComposite) {
        mappingTree = new TreeViewer(listComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE | SWT.V_SCROLL);
        mappingTree.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mappingTree.setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {

            @Override
            public Object[] getElements(final Object object) {
                if (object instanceof Collection) {
                    return ((Collection) object).toArray();
                }
                return super.getElements(object);
            }
        });
        mappingTree.setLabelProvider(new ActorMappingStyledTreeLabelProvider(adapterFactory, deployedOrganization));
        mappingTree.addSelectionChangedListener(this);
        mappingTree.addDoubleClickListener(this);
        mappingTree.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return (element instanceof String || element instanceof MembershipType)
                        || ((ITreeContentProvider) mappingTree.getContentProvider()).hasChildren(element);
            }
        });
    }

    protected void userAction() {
        final ActorMapping mapping = getSelectedActor();
        final SelectUsersWizard wizard = new SelectUsersWizard(mapping);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.open();
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                mappingTree.refresh();
                mappingTree.expandToLevel(mapping.getUsers(), 2);
            }
        });

    }

    protected void membershipAction() {
        final ActorMapping mapping = getSelectedActor();
        final SelectMembershipsWizard wizard = new SelectMembershipsWizard(mapping);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.open();
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                mappingTree.refresh();
                mappingTree.expandToLevel(mapping.getMemberships(), 2);
            }
        });

    }

    protected void roleAction() {
        final ActorMapping mapping = getSelectedActor();
        final SelectRolesWizard wizard = new SelectRolesWizard(mapping);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.open();
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                mappingTree.refresh();
                mappingTree.expandToLevel(mapping.getRoles(), 2);
            }
        });

    }

    protected void groupAction() {
        final ActorMapping mapping = getSelectedActor();
        final SelectGroupsWizard wizard = new SelectGroupsWizard(mapping);
        final WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.open();
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                mappingTree.refresh();
                mappingTree.expandToLevel(mapping.getGroups(), 2);
            }
        });
    }

    private ActorMapping getSelectedActor() {
        final TreePath treePath = ((ITreeSelection) mappingTree.getSelection()).getPaths()[0];
        return (ActorMapping) treePath.getFirstSegment();
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        updateButtons();
    }

    @Override
    public void updatePage(final AbstractProcess process, final Configuration configuration) {
        this.configuration = configuration;
        this.process = process;
        if (this.configuration != null && mappingTree != null && !mappingTree.getTree().isDisposed()
                && this.configuration.getActorMappings() != null) {
            final List<ActorMapping> mappings = this.configuration.getActorMappings().getActorMapping();
            mappingTree.setInput(mappings);
            mappingTree.expandAll();
            updateButtons();
        }
    }

    private boolean hasUnknownElement(ActorMapping mapping) {
        return mapping.getGroups().getGroup().stream().anyMatch(this::isUnknownGroup)
                || mapping.getRoles().getRole().stream().anyMatch(this::isUnknownRole)
                || mapping.getMemberships().getMembership().stream()
                        .anyMatch(membership -> isUnknownGroup(membership.getGroup()) || isUnknownRole(membership.getRole()))
                || mapping.getUsers().getUser().stream().anyMatch(this::isUnknownUser);
    }

    private boolean isUnknownGroup(String group) {
        return !deployedOrganization.isPresent() || deployedOrganization.get().getGroups().getGroup().stream()
                .map(this::getGroupPath).noneMatch(group::equals);
    }

    private boolean isUnknownRole(String role) {
        return !deployedOrganization.isPresent()
                || deployedOrganization.get().getRoles().getRole().stream().map(Role::getName).noneMatch(role::equals);
    }

    private boolean isUnknownUser(String user) {
        return !deployedOrganization.isPresent()
                || deployedOrganization.get().getUsers().getUser().stream().map(User::getUserName).noneMatch(user::equals);
    }

    private String getGroupPath(Group group) {
        return String.format("%s/%s", group.getParentPath() == null ? "" : group.getParentPath(), group.getName());
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            updateButtons();
        }
    }

    private void updateButtons() {
        groupButton.setEnabled(!mappingTree.getSelection().isEmpty());
        roleButton.setEnabled(!mappingTree.getSelection().isEmpty());
        userButton.setEnabled(!mappingTree.getSelection().isEmpty());
        membershipButton.setEnabled(!mappingTree.getSelection().isEmpty());
    }

    @Override
    public String isConfigurationPageValid(final Configuration conf) {
        if (conf != null && conf.getActorMappings() != null) {
            for (final ActorMapping mapping : conf.getActorMappings().getActorMapping()) {
                if (mappingIsEmpty(mapping)) {
                    return Messages.bind(Messages.actorHasNoMapping, mapping.getName());
                }
                if (hasUnknownElement(mapping)) {
                    return Messages.unknownActorMappingElement;
                }
            }

        }
        return null;
    }

    private boolean mappingIsEmpty(final ActorMapping mapping) {
        boolean hasGroup = false;
        if (mapping.getGroups() != null) {
            hasGroup = !mapping.getGroups().getGroup().isEmpty();
        }
        boolean hasUser = false;
        if (mapping.getUsers() != null) {
            hasUser = !mapping.getUsers().getUser().isEmpty();
        }
        boolean hasRole = false;
        if (mapping.getRoles() != null) {
            hasRole = !mapping.getRoles().getRole().isEmpty();
        }
        boolean hasMembership = false;
        if (mapping.getMemberships() != null) {
            hasMembership = !mapping.getMemberships().getMembership().isEmpty();
        }
        return !hasGroup && !hasMembership && !hasUser && !hasRole;
    }

    @Override
    public Image getConfigurationImage() {
        return Pics.getImage("actors_mapping.png", ActorsPlugin.getDefault());
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        final TreePath treePath = ((ITreeSelection) event.getSelection()).getPaths()[0];
        for (int i = treePath.getSegmentCount() - 1; i >= 0; i--) {
            final Object selection = treePath.getSegment(i);
            if (selection instanceof Users) {
                userAction();
            } else if (selection instanceof Membership) {
                membershipAction();
            } else if (selection instanceof Groups) {
                groupAction();
            } else if (selection instanceof Roles) {
                roleAction();
            }

        }
    }

}
