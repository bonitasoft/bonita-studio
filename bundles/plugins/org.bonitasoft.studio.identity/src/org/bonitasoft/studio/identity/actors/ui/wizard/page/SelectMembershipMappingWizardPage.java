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
package org.bonitasoft.studio.identity.actors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.ui.provider.content.GroupContentProvider;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 */
public class SelectMembershipMappingWizardPage extends SelectOrganizationWizardPage {

    private final ActorMapping mapping;
    private List<Role> roles;
    private List<Group> groups;
    private Composite viewersComposite;

    public SelectMembershipMappingWizardPage(final ActorMapping mapping) {
        super();
        setTitle(Messages.selectMembershipTitle);
        setDescription(Messages.selectMembershipDescription);
        this.mapping = mapping;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);

        final Composite mainComposite = (Composite) getControl();
        viewersComposite = new Composite(mainComposite, SWT.NONE);
        viewersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        viewersComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(5).margins(0, 0).equalWidth(false).create());

        createMembershipComposite(viewersComposite);
    }

    private void createMembershipComposite(final Composite viewersComposite) {
        final org.bonitasoft.studio.model.actormapping.Membership membership = mapping.getMemberships();
        if (membership != null) {
            for (final MembershipType mType : membership.getMembership()) {
                final Label groupName = new Label(viewersComposite, SWT.NONE);
                groupName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
                groupName.setText(Messages.groupName);

                final Combo groupNameCombo = new Combo(viewersComposite, SWT.BORDER | SWT.READ_ONLY);
                groupNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create());
                groupNameCombo.addModifyListener(new ModifyListener() {

                    @Override
                    public void modifyText(final ModifyEvent e) {
                        final String path = groupNameCombo.getText();
                        if (!path.isEmpty()) {
                            mType.setGroup(path);
                        } else {
                            mType.setGroup("");
                        }
                        getContainer().updateButtons();
                    }
                });

                groupNameCombo.add("");
                for (final Group g : groups) {
                    groupNameCombo.add(GroupContentProvider.getGroupPath(g));
                }

                if (mType.getGroup() != null && !mType.getGroup().isEmpty()) {
                    groupNameCombo.add(mType.getGroup());
                    groupNameCombo.setText(mType.getGroup());
                } else {
                    groupNameCombo.setText("");
                }

                final Label roleName = new Label(viewersComposite, SWT.NONE);
                roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
                roleName.setText(Messages.role);

                final Combo roleNameCombo = new Combo(viewersComposite, SWT.BORDER | SWT.READ_ONLY);
                roleNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create());
                roleNameCombo.add("");
                for (final Role r : roles) {
                    roleNameCombo.add(r.getName());
                }

                roleNameCombo.addModifyListener(new ModifyListener() {

                    @Override
                    public void modifyText(final ModifyEvent e) {
                        final String roleName = roleNameCombo.getText();
                        if (!roleName.isEmpty()) {
                            mType.setRole(roleName);
                        } else {
                            mType.setRole("");
                        }
                        getContainer().updateButtons();
                    }
                });

                if (mType.getRole() != null && !mType.getRole().isEmpty()) {
                    roleNameCombo.add(mType.getRole());
                    roleNameCombo.setText(mType.getRole());
                } else {
                    roleNameCombo.setText("");
                }

                final Button removeMembershipButton = new Button(viewersComposite, SWT.FLAT);
                removeMembershipButton.setImage(Pics.getImage("delete.png"));
                removeMembershipButton.setToolTipText(Messages.delete);
                removeMembershipButton.setLayoutData(GridDataFactory.swtDefaults().create());
                removeMembershipButton.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(final SelectionEvent e) {
                        membership.getMembership().remove(mType);
                        refreshComposite();
                        getContainer().updateButtons();
                    }
                });
            }
        }

        final Button addMembershipButton = new Button(viewersComposite, SWT.PUSH);
        addMembershipButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(5, 1).align(SWT.END, SWT.CENTER).create());
        addMembershipButton.setText(Messages.addMembership);
        addMembershipButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addMembershipAction();
                refreshComposite();
            }
        });

    }

    protected void refreshComposite() {
        if (viewersComposite != null) {
            for (final Control c : viewersComposite.getChildren()) {
                c.dispose();
            }
            createMembershipComposite(viewersComposite);
            viewersComposite.getShell().layout(true, true);
        }
    }

    protected void addMembershipAction() {
        final MembershipType m = ActorMappingFactory.eINSTANCE.createMembershipType();
        if (mapping.getMemberships() == null) {
            mapping.setMemberships(ActorMappingFactory.eINSTANCE.createMembership());
        }
        mapping.getMemberships().getMembership().add(m);
        getContainer().updateButtons();
    }

    @Override
    protected void refreshOrganization(final Organization organization) {
        if (organization != null) {
            if (organization.getGroups() == null) {
                organization.setGroups(OrganizationFactory.eINSTANCE.createGroups());
            }
            if (organization.getRoles() == null) {
                organization.setRoles(OrganizationFactory.eINSTANCE.createRoles());
            }

            groups = organization.getGroups().getGroup();
            roles = organization.getRoles().getRole();
            refreshComposite();
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final IRepositoryFileStore fstore = (IRepositoryFileStore) ((IStructuredSelection) event.getSelection()).getFirstElement();
        try {
            refreshOrganization((Organization) fstore.getContent());
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error("Failed read organization content", e);
        }

    }

    @Override
    public boolean isPageComplete() {
        setErrorMessage(null);
        setDescription(getDescription());
        for (final MembershipType membership : mapping.getMemberships().getMembership()) {
            if (membership.getGroup() == null || membership.getGroup().isEmpty()) {
                setErrorMessage(Messages.incompleteMembership);
                return false;
            }
            if (membership.getRole() == null || membership.getRole().isEmpty()) {
                setErrorMessage(Messages.incompleteMembership);
                return false;
            }
        }
        return super.isPageComplete();
    }

}
